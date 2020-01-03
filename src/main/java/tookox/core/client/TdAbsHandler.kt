package tookox.core.client

import tooko.td.TdApi
import tooko.td.TdApi.*

interface TdAbsHandler {

    val sudo: TdClient

    fun onLoad(client: TdClient)

    fun onLoad()

    fun onLogin()

    fun onLogout()

    fun onDestroy()

    fun onAuthorizationState(authorizationState: AuthorizationState)

    fun onNewMessage(userId: Int, chatId: Long, message: Message)

    fun onMessageSendAcknowledged(chatId: Long, messageId: Long)

    fun onMessageSendSucceeded(message: Message, oldMessageId: Long)

    fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String)

    fun onMessageContent(chatId: Long, messageId: Long, newContent: MessageContent)

    fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup)

    fun onMessageViews(chatId: Long, messageId: Long, views: Int)

    fun onMessageContentOpened(chatId: Long, messageId: Long)

    fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int)

    fun onNewChat(chat: Chat)

    fun onChatTitle(chatId: Long, title: String)

    fun onChatPhoto(chatId: Long, photo: ChatPhoto)

    fun onChatPermissions(chatId: Long, permissions: ChatPermissions)

    fun onChatLastMessage(chatId: Long, lastMessage: Message, order: Long)

    fun onChatOrder(chatId: Long, order: Long)

    fun onChatIsPinned(chatId: Long, isPinned: Boolean, order: Long)

    fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean)

    fun onChatIsSponsored(chatId: Long, isSponsored: Boolean, order: Long)

    fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean)

    fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int)

    fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long)

    fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int)

    fun onChatNotificationSettings(chatId: Long, notificationSettings: ChatNotificationSettings)

    fun onScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings)

    fun onChatPinnedMessage(chatId: Long, pinnedMessageId: Long)

    fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long)

    fun onChatDraftMessage(chatId: Long, draftMessage: DraftMessage, order: Long)

    fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int)

    fun onNotification(notificationGroupId: Int, notification: Notification)

    fun onNotificationGroup(notificationGroupId: Int, type: NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<Notification>, removedNotificationIds: IntArray)

    fun onActiveNotifications(groups: Array<NotificationGroup>)

    fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean)

    fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean)

    fun onUserChatAction(chatId: Long, userId: Int, action: ChatAction)

    fun onUserStatus(userId: Int, status: UserStatus)

    fun onUser(user: User)

    fun onBasicGroup(basicGroup: BasicGroup)

    fun onSupergroup(supergroup: Supergroup)

    fun onSecretChat(secretChat: SecretChat)

    fun onUserFullInfo(userId: Int, userFullInfo: UserFullInfo)

    fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: BasicGroupFullInfo)

    fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: SupergroupFullInfo)

    fun onServiceNotification(type: String, content: MessageContent)

    fun onFile(file: File)

    fun onFileGenerationStart(generationId: Long, originalPath: String, destinationPath: String, conversion: String)

    fun onFileGenerationStop(generationId: Long)

    fun onCall(call: Call)

    fun onUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules)

    fun onUnreadMessageCount(unreadCount: Int, unreadUnmutedCount: Int)

    fun onUnreadChatCount(unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int)

    fun onOption(name: String, value: OptionValue)

    fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray)

    fun onTrendingStickerSets(stickerSets: StickerSets)

    fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray)

    fun onFavoriteStickers(stickerIds: IntArray)

    fun onSavedAnimations(animationIds: IntArray)

    fun onSelectedBackground(forDarkTheme: Boolean, background: Background?)

    fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<LanguagePackString>)

    fun onConnectionState(state: ConnectionState)

    fun onTermsOfService(termsOfServiceId: String, termsOfService: TermsOfService)

    fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: Location, query: String, offset: String)

    fun onNewChosenInlineResult(senderUserId: Int, userLocation: Location, query: String, resultId: String, inlineMessageId: String)

    fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    fun handleNewInlineCallbackQuery(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) = Unit

    fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address)

    fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String, orderInfo: OrderInfo)

    fun onNewCustomEvent(event: String)

    fun onNewCustomQuery(id: Long, data: String, timeout: Int)

    fun onPoll(poll: Poll)

    fun <T : Object> send(function: TdApi.Function, stackIgnore: Int = 0, block: ((T) -> Unit)? = null): TdCallback<T> = sudo.send(function, stackIgnore, block)

    fun sendRaw(function: TdApi.Function, stackIgnore: Int = 0, block: ((Object) -> Unit)? = null): TdCallback<Object> = send(function, stackIgnore, block)

    fun <T : Object> sync(function: TdApi.Function): T = sudo.sync(function)

    fun post(function: TdApi.Function) {
        sudo.sync<Object>(function)
    }

    val String.asHtml: FormattedText get() = sync(ParseTextEntities(this, TextParseModeHTML()))

    val String.asMarkdown: FormattedText get() = sync(ParseTextEntities(this, TextParseModeMarkdown()))

}