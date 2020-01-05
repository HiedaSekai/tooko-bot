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
    var authing by AtomicBoolean(false)
    var authed by AtomicBoolean(false)
    var stop by AtomicBoolean(false)
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

    open fun start(): Boolean = runBlocking {

        check(!start) { "已经启动过." }

        start = true

        authing = true

        addHandler(this@TdClient)

        launch { receiveQueries() }

        while (authing) delay(100)

        authed

    }

    open fun stop() = runBlocking {

        check(start) { "未启动." }

        check(!closed) { "已销毁." }

        handlers.forEach { it.onDestroy() }

        sendRaw(Close())

        while (!closed) delay(100)

    }

    fun destroy() {

        if (!stop) stop()

        check(!closed) { "重复销毁." }

        closed = true

        Client.destroyNativeClient(clientId)

    }

    fun <T : TdAbsHandler> findHandler(clazz: KClass<T>): T {

        for (handler in handlers) {

            @Suppress("UNCHECKED_CAST")
            if (clazz.isInstance(handler)) return handler as T

        }

        error("Hanlder ${clazz.java.name} not found !")

    }

    override fun onAuthorizationState(authorizationState: AuthorizationState) {

        if (authorizationState is AuthorizationStateWaitTdlibParameters) {

            sendUnit(SetTdlibParameters(options.build())).onError(defaultLog::warn)

        } else if (authorizationState is AuthorizationStateWaitEncryptionKey) {

            sendRaw(if (Env.PASSWORD.isBlank()) {

                CheckDatabaseEncryptionKey()

            } else {

                CheckDatabaseEncryptionKey(Env.PASSWORD.toByteArray())

            })

        } else if (authorizationState is AuthorizationStateReady) {

            send<User>(GetMe()) {

                me = it

                authing = false

                authed = true

                for (handler in handlers) handler.onLogin()

            }.onError(::onAuthorizationFailed)

        } else if (authorizationState is AuthorizationStateLoggingOut) {

            for (handler in handlers) handler.onLogout()

        } else if (authorizationState is AuthorizationStateClosed) {

            closed = true

        }

    }

    open fun onAuthorizationFailed(ex: TdException) {

        authing = false

    }

    override fun onMessageSendSucceeded(message: Message, oldMessageId: Long) {

        try {

            val callback = messages.remove(oldMessageId) ?: return

            callback.postResult(message)

        } catch (e: Exception) {

            defaultLog.error(e, "TdError - Sync")

        }
    }

    override fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) {

        val callback = messages.remove(oldMessageId) ?: return

        try {

            callback.postError(TdException(Error(errorCode, errorMessage)))

        } catch (e: Exception) {

            defaultLog.error(e, "TdError - Sync")

        }

    }

    override fun <T : Object> sync(function: TdApi.Function): T {

        val responseAtomicReference = AtomicReference<Any>()

        val executedAtomicBoolean = AtomicBoolean(false)

        send<T>(function, 1) {

            responseAtomicReference.set(it)

            executedAtomicBoolean.set(true)

        } onError {

            responseAtomicReference.set(it)

            executedAtomicBoolean.set(true)

        }

        return runBlocking {

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

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Object> send(function: TdApi.Function, stackIgnore: Int, block: ((T) -> Unit)?): TdCallback<T> {

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

    fun sendRaw(requestId: Long, function: TdApi.Function) {

        Client.nativeClientSend(clientId, requestId, function)

    }

    fun receiveQueries() {

        while (!stop) {

            val MAX_EVENTS = 1000

            val eventIds = LongArray(MAX_EVENTS)
            val eventObjs = arrayOfNulls<Object>(MAX_EVENTS)

            val resultCount = Client.nativeClientReceive(clientId, eventIds, eventObjs, 314.15)

            if (resultCount == 0) continue

            (0 until resultCount).forEach { index ->

                val requestId = eventIds[index]
                val eventObj = eventObjs[index]!!

                if (requestId != 0L) {

                    if (!callbacks.containsKey(requestId)) return@forEach

                    val callback = callbacks.remove(requestId)!!

                    GlobalScope.launch {

                        runCatching {

                            if (eventObj is Error) {

                                callback.postError(TdException(eventObj as Error))

                            } else {

                                callback.postResult(eventObj)

                            }

                        }.onFailure {

                            defaultLog.error(it, "TdError - Sync")

                        }

                    }


                } else {

                    events.launch {

                        handlers.forEach {

                            runCatching {

                                it.onEvent(eventObj)

                            }.onFailure {

                                if (it is Finish) return@launch

                                defaultLog.error(it, "TdError - Sync")

                            }

                        }

                    }

                }

            }
        }

    }

    companion object {

        @Suppress("EXPERIMENTAL_API_USAGE")
        val events = CoroutineScope(newSingleThreadContext("Tooko Events Task"))

        /*

        val postAdd = LinkedList<TdClient>()
        val postDestroy = LinkedList<TdClient>()
        val mainTimer = Timer("Mian Timer")
        val clients = LinkedList<TdClient>()

        val eventTask = Thread {

            while (!Env.STOP.get()) {

                synchronized(postAdd) {

                    val iter = postAdd.iterator()

                    while (iter.hasNext()) {

                        val toAdd = iter.next()

                        clients.add(toAdd)

                        toAdd.status = true

                        iter.remove()

                    }

                }

                synchronized(postDestroy) {

                    val iter = postDestroy.iterator()

                    while (iter.hasNext()) {

                        val toDestroy = iter.next()

                        clients.remove(toDestroy)

                        toDestroy.status = false

                        iter.remove()

                    }

                }

                if (clients.isEmpty()) {

                    ThreadUtil.safeSleep(1000)

                    continue

                }

                for (client in clients) {

                    val responseList = client.td.receive(0.0).forEach { event: Client.Event ->

                        if (event.requestId != 0L && client.callbacks.containsKey(event.requestId)) {

                            val callback = client.callbacks.remove(event.requestId)!!

                            GlobalScope.launch {

                                runCatching {

                                    if (event.event is Error) {

                                        callback.postError(TdException(event.event as Error))

                                    } else {

                                        callback.postResult(event.event)

                                    }

                                }.onFailure {

                                    defaultLog.error(it, "TdError - Sync")

                                }

                            }


                        } else {

                            events.launch {

                                client.handlers.forEach {

                                    runCatching {

                                        it.onEvent(event.event)

                                    }.onFailure {

                                        if (it is Finish) return@launch

                                        defaultLog.error(it, "TdError - Sync")

                                    }

                                }

                            }

                        }

                    }

                }



            }

        }


         */

    }

    override fun onLoad() = Unit

    override fun onLogin() = Unit

    override fun onLogout() = Unit

    override fun onDestroy() = Unit

    override fun onNewMessage(userId: Int, chatId: Long, message: Message) = Unit

    override fun onMessageSendAcknowledged(chatId: Long, messageId: Long) = Unit

    override fun onMessageContent(chatId: Long, messageId: Long, newContent: MessageContent) = Unit

    override fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup?) = Unit

    override fun onMessageViews(chatId: Long, messageId: Long, views: Int) = Unit

    override fun onMessageContentOpened(chatId: Long, messageId: Long) = Unit

    override fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int) = Unit

    override fun onNewChat(chat: Chat) = Unit

    override fun onChatTitle(chatId: Long, title: String) = Unit

    override fun onChatPhoto(chatId: Long, photo: ChatPhoto) = Unit

    override fun onChatPermissions(chatId: Long, permissions: ChatPermissions) = Unit

    override fun onChatLastMessage(chatId: Long, lastMessage: Message, order: Long) = Unit

    override fun onChatOrder(chatId: Long, order: Long) = Unit

    override fun onChatIsPinned(chatId: Long, isPinned: Boolean, order: Long) = Unit

    override fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean) = Unit

    override fun onChatIsSponsored(chatId: Long, isSponsored: Boolean, order: Long) = Unit

    override fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean) = Unit

    override fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int) = Unit

    override fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long) = Unit

    override fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int) = Unit

    override fun onChatNotificationSettings(chatId: Long, notificationSettings: ChatNotificationSettings) = Unit

    override fun onScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings) = Unit

    override fun onChatPinnedMessage(chatId: Long, pinnedMessageId: Long) = Unit

    override fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long) = Unit

    override fun onChatDraftMessage(chatId: Long, draftMessage: DraftMessage, order: Long) = Unit

    override fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int) = Unit

    override fun onNotification(notificationGroupId: Int, notification: Notification) = Unit

    override fun onNotificationGroup(notificationGroupId: Int, type: NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<Notification>, removedNotificationIds: IntArray) = Unit

    override fun onActiveNotifications(groups: Array<NotificationGroup>) = Unit

    override fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean) = Unit

    override fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) = Unit

    override fun onUserChatAction(chatId: Long, userId: Int, action: ChatAction) = Unit

    override fun onUserStatus(userId: Int, status: UserStatus) = Unit

    override fun onUser(user: User) = Unit

    override fun onBasicGroup(basicGroup: BasicGroup) = Unit

    override fun onSupergroup(supergroup: Supergroup) = Unit

    override fun onSecretChat(secretChat: SecretChat) = Unit

    override fun onUserFullInfo(userId: Int, userFullInfo: UserFullInfo) = Unit

    override fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: BasicGroupFullInfo) = Unit

    override fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: SupergroupFullInfo) = Unit

    override fun onServiceNotification(type: String, content: MessageContent) = Unit

    override fun onFile(file: File) = Unit

    override fun onFileGenerationStart(generationId: Long, originalPath: String, destinationPath: String, conversion: String) = Unit

    override fun onFileGenerationStop(generationId: Long) = Unit

    override fun onCall(call: Call) = Unit

    override fun onUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules) = Unit

    override fun onUnreadMessageCount(unreadCount: Int, unreadUnmutedCount: Int) = Unit

    override fun onUnreadChatCount(unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int) = Unit

    override fun onOption(name: String, value: OptionValue) = Unit

    override fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray) = Unit

    override fun onTrendingStickerSets(stickerSets: StickerSets) = Unit

    override fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray) = Unit

    override fun onFavoriteStickers(stickerIds: IntArray) = Unit

    override fun onSavedAnimations(animationIds: IntArray) = Unit

    override fun onSelectedBackground(forDarkTheme: Boolean, background: Background?) = Unit

    override fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<LanguagePackString>) = Unit

    override fun onConnectionState(state: ConnectionState) = Unit

    override fun onTermsOfService(termsOfServiceId: String, termsOfService: TermsOfService) = Unit

    override fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: Location, query: String, offset: String) = Unit

    override fun onNewChosenInlineResult(senderUserId: Int, userLocation: Location, query: String, resultId: String, inlineMessageId: String) = Unit

    override fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    override fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address) = Unit

    override fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String, orderInfo: OrderInfo) = Unit

    override fun onNewCustomEvent(event: String) = Unit

    override fun onNewCustomQuery(id: Long, data: String, timeout: Int) = Unit

    override fun onPoll(poll: Poll) = Unit

}