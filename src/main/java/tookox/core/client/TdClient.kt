package tookox.core.client

import kotlinx.coroutines.*
import tooko.main.Env
import tooko.td.Client
import tooko.td.TdApi
import tooko.td.TdApi.*
import tooko.td.client.TdException
import tookox.core.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import kotlin.reflect.KClass

open class TdClient(private val options: TdOptions) : TdAbsHandler {

    override val sudo get() = this

    override fun onLoad(client: TdClient) = onLoad()

    var handlers = LinkedList<TdAbsHandler>()

    var start by AtomicBoolean(false)
    var started by AtomicBoolean(false)
    var authing by AtomicBoolean(false)
    var auth by AtomicBoolean(false)
    var stop by AtomicBoolean(false)
    var stopped by AtomicBoolean(false)
    var closed by AtomicBoolean(false)

    private val clientId = Client.createNativeClient()
    private val requestId = AtomicLong(1)

    private val callbacks = ConcurrentHashMap<Long, TdCallback<*>>()
    private val messages = ConcurrentHashMap<Long, TdCallback<Message>>()

    lateinit var me: User

    fun addHandler(handler: TdAbsHandler) {

        handler.onLoad(this)

        handlers.add(handler)

    }

    fun clearHandlers() {

        handlers.clear()

    }

    open suspend fun start(waitForAuth: Boolean = true): Boolean {

        check(!start) { "已经启动过." }

        start = true

        authing = true

        addHandler(this)

        defaultLog.debug("START")

        if (!loopThreadInited) {

            loopThread = Thread(::loopEvents, "Tooko Event Loop")

            loopThread.start()

        }

        return if (waitForAuth) {

            while (auth || authing) {

                delay(100L)

            }

            auth

        } else {

            while (!started) delay(1000L)

            true

        }

    }

    open suspend fun stop() {

        check(started) { "未启动." }

        check(!stop) { "已静止过." }

        handlers.forEach { it.onDestroy() }

        sendRaw(Close())

        while (!closed) delay(100)

        Client.destroyNativeClient(clientId)

    }

    fun <T : TdAbsHandler> findHandler(clazz: KClass<T>): T {

        for (handler in handlers) {

            @Suppress("UNCHECKED_CAST")
            if (clazz.isInstance(handler)) return handler as T

        }

        error("Hanlder ${clazz.java.name} not found !")

    }

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) = coroutineScope<Unit> {

        if (authorizationState is AuthorizationStateWaitTdlibParameters) {

            sendUnit(SetTdlibParameters(options.build())) onError {

                defaultLog.warn(it)

            }

        } else if (authorizationState is AuthorizationStateWaitEncryptionKey) {

            sendRaw(if (Env.PASSWORD.isBlank()) {

                CheckDatabaseEncryptionKey()

            } else {

                CheckDatabaseEncryptionKey(Env.PASSWORD.toByteArray())

            })

        } else if (authorizationState is AuthorizationStateReady) {

            send<User>(GetMe()) { user ->

                me = user

                authing = false

                auth = true

                defaultLog.info("认证正常 : [ ${me.displayName} @${me.username} ]")

                for (handler in handlers) handler.onLogin()

            } onError {

                onAuthorizationFailed(it)

            }

        } else if (authorizationState is AuthorizationStateLoggingOut) {

            for (handler in handlers) handler.onLogout()

        } else if (authorizationState is AuthorizationStateClosed) {

            closed = true

        }

    }

    open suspend fun onAuthorizationFailed(ex: TdException) {

        authing = false

    }

    override suspend fun onMessageSendSucceeded(message: Message, oldMessageId: Long) {

        try {

            val callback = messages.remove(oldMessageId) ?: return

            callback.postResult(message)

        } catch (e: Exception) {

            defaultLog.error(e, "TdError - Sync")

        }

    }

    override suspend fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) {

        val callback = messages.remove(oldMessageId) ?: return

        try {

            callback.postError(TdException(Error(errorCode, errorMessage)))

        } catch (e: Exception) {

            defaultLog.error(e, "TdError - Sync")

        }

    }

    override suspend fun <T : Object> sync(function: TdApi.Function): T = withContext(Dispatchers.IO) {

        val responseAtomicReference = AtomicReference<Any>()

        val executedAtomicBoolean = AtomicBoolean(false)

        send<T>(function, 1) {

            responseAtomicReference.set(it)

            executedAtomicBoolean.set(true)

        } onError {

            responseAtomicReference.set(it)

            executedAtomicBoolean.set(true)

        }

        while (!executedAtomicBoolean.get()) {

            if (Env.STOP.get()) {

                throw TdException(Error(-1, "Server Stopped"))

            }

            delay(100L)

        }

        @Suppress("UNCHECKED_CAST")
        responseAtomicReference.get().apply {

            if (this is TdException) throw this

        } as T


    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Object> send(function: TdApi.Function, stackIgnore: Int, block: (suspend CoroutineScope.(T) -> Unit)?): TdCallback<T> {

        val requestId = requestId.getAndIncrement()

        return if (function is SendMessage && block != null) {

            TdCallback<Message> {

                messages[it.id] = TdCallback(2, block) as TdCallback<Message>

            } as TdCallback<T>

        } else {

            TdCallback(stackIgnore + 1, block)

        }.apply {

            callbacks[requestId] = this

            sendRaw(requestId, function)

        }

    }

    override fun sendRaw(function: TdApi.Function) {

        val requestId = requestId.getAndIncrement()

        sendRaw(requestId, function)

    }

    private fun sendRaw(requestId: Long, function: TdApi.Function) {

        Client.nativeClientSend(clientId, requestId, function)

    }

    companion object {

        @Suppress("EXPERIMENTAL_API_USAGE")
        val events = CoroutineScope(newSingleThreadContext("Tooko Events Task"))

        private val postAdd = LinkedList<TdClient>()
        private val postDestroy = LinkedList<TdClient>()
        val clients = LinkedList<TdClient>()

        private const val MAX_EVENTS = 1000

        lateinit var loopThread: Thread

        val loopThreadInited get() = ::loopThread.isInitialized

        fun loopEvents() = runBlocking(Dispatchers.IO) {

            while (true) {

                synchronized(postAdd) {

                    val iter = postAdd.iterator()

                    while (iter.hasNext()) {

                        val toAdd = iter.next()

                        clients.add(toAdd)

                        iter.remove()

                        toAdd.started = true

                    }

                }

                synchronized(postDestroy) {

                    val iter = postDestroy.iterator()

                    while (iter.hasNext()) {

                        val toDestroy = iter.next()

                        clients.remove(toDestroy)

                        toDestroy.stop = true

                        iter.remove()

                    }

                }

                if (clients.isEmpty()) {

                    delay(1000L)

                    continue

                }

                for (client in clients) {

                    val eventIds = LongArray(MAX_EVENTS)
                    val eventObjs = arrayOfNulls<Object>(MAX_EVENTS)

                    val resultCount = Client.nativeClientReceive(client.clientId, eventIds, eventObjs, 0.0)

                    if (resultCount == 0) continue

                    for (index in 0 until resultCount) {

                        val requestId = eventIds[index]
                        val eventObj = eventObjs[index]!!

                        if (requestId != 0L) {

                            if (!client.callbacks.containsKey(requestId)) continue

                            val callback = client.callbacks.remove(requestId)!!

                            launch(Dispatchers.Default) {

                                runCatching {

                                    if (eventObj is Error) {

                                        callback.postError(TdException(eventObj))

                                    } else {

                                        callback.postResult(eventObj)

                                    }

                                }.onFailure {

                                    defaultLog.error(it, "TdError - Sync")

                                }

                            }

                        } else {

                            events.launch {

                                launch(Dispatchers.Unconfined) proc@{

                                    for (it in client.handlers) {

                                        it.runCatching {

                                            when (eventObj) {

                                                is UpdateAuthorizationState -> onAuthorizationState(eventObj.authorizationState)
                                                is UpdateNewMessage -> onNewMessage(eventObj.message.senderUserId, eventObj.message.chatId, eventObj.message)
                                                is UpdateMessageSendAcknowledged -> onMessageSendAcknowledged(eventObj.chatId, eventObj.messageId)
                                                is UpdateMessageSendSucceeded -> onMessageSendSucceeded(eventObj.message, eventObj.oldMessageId)
                                                is UpdateMessageSendFailed -> onMessageSendFailed(eventObj.message, eventObj.oldMessageId, eventObj.errorCode, eventObj.errorMessage)
                                                is UpdateMessageContent -> onMessageContent(eventObj.chatId, eventObj.messageId, eventObj.newContent)
                                                is UpdateMessageEdited -> onMessageEdited(eventObj.chatId, eventObj.messageId, eventObj.editDate, eventObj.replyMarkup)
                                                is UpdateMessageViews -> onMessageViews(eventObj.chatId, eventObj.messageId, eventObj.views)
                                                is UpdateMessageContentOpened -> onMessageContentOpened(eventObj.chatId, eventObj.messageId)
                                                is UpdateMessageMentionRead -> onMessageMentionRead(eventObj.chatId, eventObj.messageId, eventObj.unreadMentionCount)
                                                is UpdateNewChat -> onNewChat(eventObj.chat)
                                                is UpdateChatTitle -> onChatTitle(eventObj.chatId, eventObj.title)
                                                is UpdateChatPhoto -> onChatPhoto(eventObj.chatId, eventObj.photo)
                                                is UpdateChatPermissions -> onChatPermissions(eventObj.chatId, eventObj.permissions)
                                                is UpdateChatLastMessage -> onChatLastMessage(eventObj.chatId, eventObj.lastMessage, eventObj.order)
                                                is UpdateChatOrder -> onChatOrder(eventObj.chatId, eventObj.order)
                                                is UpdateChatIsPinned -> onChatIsPinned(eventObj.chatId, eventObj.isPinned, eventObj.order)
                                                is UpdateChatIsMarkedAsUnread -> onChatIsMarkedAsUnread(eventObj.chatId, eventObj.isMarkedAsUnread)
                                                is UpdateChatIsSponsored -> onChatIsSponsored(eventObj.chatId, eventObj.isSponsored, eventObj.order)
                                                is UpdateChatDefaultDisableNotification -> onChatDefaultDisableNotification(eventObj.chatId, eventObj.defaultDisableNotification)
                                                is UpdateChatReadInbox -> onChatReadInbox(eventObj.chatId, eventObj.lastReadInboxMessageId, eventObj.unreadCount)
                                                is UpdateChatReadOutbox -> onChatReadOutbox(eventObj.chatId, eventObj.lastReadOutboxMessageId)
                                                is UpdateChatUnreadMentionCount -> onChatUnreadMentionCount(eventObj.chatId, eventObj.unreadMentionCount)
                                                is UpdateChatNotificationSettings -> onChatNotificationSettings(eventObj.chatId, eventObj.notificationSettings)
                                                is UpdateScopeNotificationSettings -> onScopeNotificationSettings(eventObj.scope, eventObj.notificationSettings)
                                                is UpdateChatPinnedMessage -> onChatPinnedMessage(eventObj.chatId, eventObj.pinnedMessageId)
                                                is UpdateChatReplyMarkup -> onChatReplyMarkup(eventObj.chatId, eventObj.replyMarkupMessageId)
                                                is UpdateChatDraftMessage -> onChatDraftMessage(eventObj.chatId, eventObj.draftMessage, eventObj.order)
                                                is UpdateChatOnlineMemberCount -> onChatOnlineMemberCount(eventObj.chatId, eventObj.onlineMemberCount)
                                                is UpdateNotification -> onNotification(eventObj.notificationGroupId, eventObj.notification)
                                                is UpdateNotificationGroup -> onNotificationGroup(eventObj.notificationGroupId, eventObj.type, eventObj.chatId, eventObj.notificationSettingsChatId, eventObj.isSilent, eventObj.totalCount, eventObj.addedNotifications, eventObj.removedNotificationIds)
                                                is UpdateActiveNotifications -> onActiveNotifications(eventObj.groups)
                                                is UpdateHavePendingNotifications -> onHavePendingNotifications(eventObj.haveDelayedNotifications, eventObj.haveUnreceivedNotifications)
                                                is UpdateDeleteMessages -> onDeleteMessages(eventObj.chatId, eventObj.messageIds, eventObj.isPermanent, eventObj.fromCache)
                                                is UpdateUserChatAction -> onUserChatAction(eventObj.chatId, eventObj.userId, eventObj.action)
                                                is UpdateUserStatus -> onUserStatus(eventObj.userId, eventObj.status)
                                                is UpdateUser -> onUser(eventObj.user)
                                                is UpdateBasicGroup -> onBasicGroup(eventObj.basicGroup)
                                                is UpdateSupergroup -> onSupergroup(eventObj.supergroup)
                                                is UpdateSecretChat -> onSecretChat(eventObj.secretChat)
                                                is UpdateUserFullInfo -> onUserFullInfo(eventObj.userId, eventObj.userFullInfo)
                                                is UpdateBasicGroupFullInfo -> onBasicGroupFullInfo(eventObj.basicGroupId, eventObj.basicGroupFullInfo)
                                                is UpdateSupergroupFullInfo -> onSupergroupFullInfo(eventObj.supergroupId, eventObj.supergroupFullInfo)
                                                is UpdateServiceNotification -> onServiceNotification(eventObj.type, eventObj.content)
                                                is UpdateFile -> onFile(eventObj.file)
                                                is UpdateFileGenerationStart -> onFileGenerationStart(eventObj.generationId, eventObj.originalPath, eventObj.destinationPath, eventObj.conversion)
                                                is UpdateFileGenerationStop -> onFileGenerationStop(eventObj.generationId)
                                                is UpdateCall -> onCall(eventObj.call)
                                                is UpdateUserPrivacySettingRules -> onUserPrivacySettingRules(eventObj.setting, eventObj.rules)
                                                is UpdateUnreadMessageCount -> onUnreadMessageCount(eventObj.unreadCount, eventObj.unreadUnmutedCount)
                                                is UpdateUnreadChatCount -> onUnreadChatCount(eventObj.unreadCount, eventObj.unreadUnmutedCount, eventObj.markedAsUnreadCount, eventObj.markedAsUnreadUnmutedCount)
                                                is UpdateOption -> onOption(eventObj.name, eventObj.value)
                                                is UpdateInstalledStickerSets -> onInstalledStickerSets(eventObj.isMasks, eventObj.stickerSetIds)
                                                is UpdateTrendingStickerSets -> onTrendingStickerSets(eventObj.stickerSets)
                                                is UpdateRecentStickers -> onRecentStickers(eventObj.isAttached, eventObj.stickerIds)
                                                is UpdateFavoriteStickers -> onFavoriteStickers(eventObj.stickerIds)
                                                is UpdateSavedAnimations -> onSavedAnimations(eventObj.animationIds)
                                                is UpdateSelectedBackground -> onSelectedBackground(eventObj.forDarkTheme, eventObj.background)
                                                is UpdateLanguagePackStrings -> onLanguagePackStrings(eventObj.localizationTarget, eventObj.languagePackId, eventObj.strings)
                                                is UpdateConnectionState -> onConnectionState(eventObj.state)
                                                is UpdateTermsOfService -> onTermsOfService(eventObj.termsOfServiceId, eventObj.termsOfService)
                                                is UpdateNewInlineQuery -> onNewInlineQuery(eventObj.id, eventObj.senderUserId, eventObj.userLocation, eventObj.query, eventObj.offset)
                                                is UpdateNewChosenInlineResult -> onNewChosenInlineResult(eventObj.senderUserId, eventObj.userLocation, eventObj.query, eventObj.resultId, eventObj.inlineMessageId)
                                                is UpdateNewCallbackQuery -> handleNewCallbackQuery(eventObj.id, eventObj.senderUserId, eventObj.chatId, eventObj.messageId, eventObj.chatInstance, eventObj.payload)
                                                is UpdateNewInlineCallbackQuery -> handleNewInlineCallbackQuery(eventObj.id, eventObj.senderUserId, eventObj.inlineMessageId, eventObj.chatInstance, eventObj.payload)
                                                is UpdateNewShippingQuery -> onNewShippingQuery(eventObj.id, eventObj.senderUserId, eventObj.invoicePayload, eventObj.shippingAddress)
                                                is UpdateNewPreCheckoutQuery -> onNewPreCheckoutQuery(eventObj.id, eventObj.senderUserId, eventObj.currency, eventObj.totalAmount, eventObj.invoicePayload, eventObj.shippingOptionId, eventObj.orderInfo)
                                                is UpdateNewCustomEvent -> onNewCustomEvent(eventObj.event)
                                                is UpdateNewCustomQuery -> onNewCustomQuery(eventObj.id, eventObj.data, eventObj.timeout)
                                                is UpdatePoll -> onPoll(eventObj.poll)

                                            }

                                        }.onFailure {

                                            if (it is Finish) return@proc

                                            defaultLog.error(it, "TdError - Sync")

                                        }

                                    }

                                }

                            }

                        }
                    }
                }

            }

        }

    }

    override fun onLoad() = Unit

    override suspend fun onLogin() = Unit

    override suspend fun onLogout() = Unit

    override suspend fun onDestroy() = Unit

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = Unit

    override suspend fun onMessageSendAcknowledged(chatId: Long, messageId: Long) = Unit

    override suspend fun onMessageContent(chatId: Long, messageId: Long, newContent: MessageContent) = Unit

    override suspend fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup?) = Unit

    override suspend fun onMessageViews(chatId: Long, messageId: Long, views: Int) = Unit

    override suspend fun onMessageContentOpened(chatId: Long, messageId: Long) = Unit

    override suspend fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int) = Unit

    override suspend fun onNewChat(chat: Chat) = Unit

    override suspend fun onChatTitle(chatId: Long, title: String) = Unit

    override suspend fun onChatPhoto(chatId: Long, photo: ChatPhoto) = Unit

    override suspend fun onChatPermissions(chatId: Long, permissions: ChatPermissions) = Unit

    override suspend fun onChatLastMessage(chatId: Long, lastMessage: Message, order: Long) = Unit

    override suspend fun onChatOrder(chatId: Long, order: Long) = Unit

    override suspend fun onChatIsPinned(chatId: Long, isPinned: Boolean, order: Long) = Unit

    override suspend fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean) = Unit

    override suspend fun onChatIsSponsored(chatId: Long, isSponsored: Boolean, order: Long) = Unit

    override suspend fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean) = Unit

    override suspend fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int) = Unit

    override suspend fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long) = Unit

    override suspend fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int) = Unit

    override suspend fun onChatNotificationSettings(chatId: Long, notificationSettings: ChatNotificationSettings) = Unit

    override suspend fun onScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings) = Unit

    override suspend fun onChatPinnedMessage(chatId: Long, pinnedMessageId: Long) = Unit

    override suspend fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long) = Unit

    override suspend fun onChatDraftMessage(chatId: Long, draftMessage: DraftMessage, order: Long) = Unit

    override suspend fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int) = Unit

    override suspend fun onNotification(notificationGroupId: Int, notification: Notification) = Unit

    override suspend fun onNotificationGroup(notificationGroupId: Int, type: NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<Notification>, removedNotificationIds: IntArray) = Unit

    override suspend fun onActiveNotifications(groups: Array<NotificationGroup>) = Unit

    override suspend fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean) = Unit

    override suspend fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) = Unit

    override suspend fun onUserChatAction(chatId: Long, userId: Int, action: ChatAction) = Unit

    override suspend fun onUserStatus(userId: Int, status: UserStatus) = Unit

    override suspend fun onUser(user: User) = Unit

    override suspend fun onBasicGroup(basicGroup: BasicGroup) = Unit

    override suspend fun onSupergroup(supergroup: Supergroup) = Unit

    override suspend fun onSecretChat(secretChat: SecretChat) = Unit

    override suspend fun onUserFullInfo(userId: Int, userFullInfo: UserFullInfo) = Unit

    override suspend fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: BasicGroupFullInfo) = Unit

    override suspend fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: SupergroupFullInfo) = Unit

    override suspend fun onServiceNotification(type: String, content: MessageContent) = Unit

    override suspend fun onFile(file: File) = Unit

    override suspend fun onFileGenerationStart(generationId: Long, originalPath: String, destinationPath: String, conversion: String) = Unit

    override suspend fun onFileGenerationStop(generationId: Long) = Unit

    override suspend fun onCall(call: Call) = Unit

    override suspend fun onUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules) = Unit

    override suspend fun onUnreadMessageCount(unreadCount: Int, unreadUnmutedCount: Int) = Unit

    override suspend fun onUnreadChatCount(unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int) = Unit

    override suspend fun onOption(name: String, value: OptionValue) = Unit

    override suspend fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray) = Unit

    override suspend fun onTrendingStickerSets(stickerSets: StickerSets) = Unit

    override suspend fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray) = Unit

    override suspend fun onFavoriteStickers(stickerIds: IntArray) = Unit

    override suspend fun onSavedAnimations(animationIds: IntArray) = Unit

    override suspend fun onSelectedBackground(forDarkTheme: Boolean, background: Background?) = Unit

    override suspend fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<LanguagePackString>) = Unit

    override suspend fun onConnectionState(state: ConnectionState) = Unit

    override suspend fun onTermsOfService(termsOfServiceId: String, termsOfService: TermsOfService) = Unit

    override suspend fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: Location, query: String, offset: String) = Unit

    override suspend fun onNewChosenInlineResult(senderUserId: Int, userLocation: Location, query: String, resultId: String, inlineMessageId: String) = Unit

    override suspend fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    override suspend fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address) = Unit

    override suspend fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String, orderInfo: OrderInfo) = Unit

    override suspend fun onNewCustomEvent(event: String) = Unit

    override suspend fun onNewCustomQuery(id: Long, data: String, timeout: Int) = Unit

    override suspend fun onPoll(poll: Poll) = Unit

}