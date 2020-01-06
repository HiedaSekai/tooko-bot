package tookox.core.client

import kotlinx.coroutines.CoroutineScope
import tooko.td.Client
import tooko.td.TdApi
import tooko.td.TdApi.*
import tooko.td.client.TdException
import java.util.*
import kotlin.collections.HashMap

interface TdAbsHandler {

    companion object {

        infix fun <T : Object> syncRaw(function: TdApi.Function): T {

            val result = Client.nativeClientExecute(function)

            if (result is Error) {

                throw TdException(result)

            } else {

                @Suppress("UNCHECKED_CAST")
                return result as T

            }

        }

    }

    val sudo: TdClient


    fun onLoad(client: TdClient)

    fun onLoad()

    suspend fun onLogin()

    suspend fun onLogout()

    suspend fun onDestroy()

    suspend fun onAuthorizationState(authorizationState: AuthorizationState)

    suspend fun onNewMessage(userId: Int, chatId: Long, message: Message)

    suspend fun onMessageSendAcknowledged(chatId: Long, messageId: Long)

    suspend fun onMessageSendSucceeded(message: Message, oldMessageId: Long)

    suspend fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String)

    suspend fun onMessageContent(chatId: Long, messageId: Long, newContent: MessageContent)

    suspend fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup?)

    suspend fun onMessageViews(chatId: Long, messageId: Long, views: Int)

    suspend fun onMessageContentOpened(chatId: Long, messageId: Long)

    suspend fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int)

    suspend fun onNewChat(chat: Chat)

    suspend fun onChatTitle(chatId: Long, title: String)

    suspend fun onChatPhoto(chatId: Long, photo: ChatPhoto)

    suspend fun onChatPermissions(chatId: Long, permissions: ChatPermissions)

    suspend fun onChatLastMessage(chatId: Long, lastMessage: Message, order: Long)

    suspend fun onChatOrder(chatId: Long, order: Long)

    suspend fun onChatIsPinned(chatId: Long, isPinned: Boolean, order: Long)

    suspend fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean)

    suspend fun onChatIsSponsored(chatId: Long, isSponsored: Boolean, order: Long)

    suspend fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean)

    suspend fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int)

    suspend fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long)

    suspend fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int)

    suspend fun onChatNotificationSettings(chatId: Long, notificationSettings: ChatNotificationSettings)

    suspend fun onScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings)

    suspend fun onChatPinnedMessage(chatId: Long, pinnedMessageId: Long)

    suspend fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long)

    suspend fun onChatDraftMessage(chatId: Long, draftMessage: DraftMessage, order: Long)

    suspend fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int)

    suspend fun onNotification(notificationGroupId: Int, notification: Notification)

    suspend fun onNotificationGroup(notificationGroupId: Int, type: NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<Notification>, removedNotificationIds: IntArray)

    suspend fun onActiveNotifications(groups: Array<NotificationGroup>)

    suspend fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean)

    suspend fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean)

    suspend fun onUserChatAction(chatId: Long, userId: Int, action: ChatAction)

    suspend fun onUserStatus(userId: Int, status: UserStatus)

    suspend fun onUser(user: User)

    suspend fun onBasicGroup(basicGroup: BasicGroup)

    suspend fun onSupergroup(supergroup: Supergroup)

    suspend fun onSecretChat(secretChat: SecretChat)

    suspend fun onUserFullInfo(userId: Int, userFullInfo: UserFullInfo)

    suspend fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: BasicGroupFullInfo)

    suspend fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: SupergroupFullInfo)

    suspend fun onServiceNotification(type: String, content: MessageContent)

    suspend fun onFile(file: File)

    suspend fun onFileGenerationStart(generationId: Long, originalPath: String, destinationPath: String, conversion: String)

    suspend fun onFileGenerationStop(generationId: Long)

    suspend fun onCall(call: Call)

    suspend fun onUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules)

    suspend fun onUnreadMessageCount(unreadCount: Int, unreadUnmutedCount: Int)

    suspend fun onUnreadChatCount(unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int)

    suspend fun onOption(name: String, value: OptionValue)

    suspend fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray)

    suspend fun onTrendingStickerSets(stickerSets: StickerSets)

    suspend fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray)

    suspend fun onFavoriteStickers(stickerIds: IntArray)

    suspend fun onSavedAnimations(animationIds: IntArray)

    suspend fun onSelectedBackground(forDarkTheme: Boolean, background: Background?)

    suspend fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<LanguagePackString>)

    suspend fun onConnectionState(state: ConnectionState)

    suspend fun onTermsOfService(termsOfServiceId: String, termsOfService: TermsOfService)

    suspend fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: Location, query: String, offset: String)

    suspend fun onNewChosenInlineResult(senderUserId: Int, userLocation: Location, query: String, resultId: String, inlineMessageId: String)

    suspend fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    suspend fun handleNewInlineCallbackQuery(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    suspend fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address)

    suspend fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String, orderInfo: OrderInfo)

    suspend fun onNewCustomEvent(event: String)

    suspend fun onNewCustomQuery(id: Long, data: String, timeout: Int)

    suspend fun onPoll(poll: Poll)

    fun <T : Object> send(function: TdApi.Function, stackIgnore: Int = 0, block: (suspend CoroutineScope.(T) -> Unit)? = null): TdCallback<T> = sudo.send(function, stackIgnore, block)

    infix fun sendUnit(function: TdApi.Function) = sudo.send<Object>(function)

    infix fun sendRaw(function: TdApi.Function) = sudo.sendRaw(function)

    suspend infix fun <T : Object> sync(function: TdApi.Function): T = sudo.sync(function)

    suspend infix fun syncUnit(function: TdApi.Function) = sudo.sync<Object>(function)

    val Message.delete get() = DeleteMessages(chatId, longArrayOf(id), true)
    val Message.deleteForSelf get() = DeleteMessages(chatId, longArrayOf(id), false)

    val Collection<Message>.deleteAll: List<DeleteMessages>
        get() {

            val messages = HashMap<Long, LinkedList<Message>>()

            forEach {

                var list = messages[it.chatId]

                if (list == null) {

                    list = LinkedList()

                    messages[it.chatId] = list

                }

                list.add(it)

            }

            return messages.map { (chatId, chatMessages) ->

                DeleteMessages(chatId, chatMessages.map { it.id }.toLongArray(), true)

            }

        }

    val Collection<Message>.deleteAllForSelf: List<DeleteMessages>
        get() {

            val messages = HashMap<Long, LinkedList<Message>>()

            forEach {

                var list = messages[it.chatId]

                if (list == null) {

                    list = LinkedList()

                    messages[it.chatId] = list

                }

                list.add(it)

            }

            return messages.map { (chatId, chatMessages) ->

                DeleteMessages(chatId, chatMessages.map { it.id }.toLongArray(), false)

            }

        }

}