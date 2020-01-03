package tookox.core.client

import tooko.td.TdApi

open class TdHandler : TdAbsHandler {

    private lateinit var _client: TdClient

    override val sudo: TdClient get() = _client

    override fun onLoad(client: TdClient) {

        _client = client

        onLoad()

    }

    override fun onLoad() = Unit

    override fun onLogin() = Unit

    override fun onLogout() = Unit

    override fun onDestroy() = Unit

    override fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) = Unit

    override fun onNewMessage(userId: Int, chatId: Long, message: TdApi.Message) = Unit

    override fun onMessageSendAcknowledged(chatId: Long, messageId: Long) = Unit

    override fun onMessageSendSucceeded(message: TdApi.Message, oldMessageId: Long) = Unit

    override fun onMessageSendFailed(message: TdApi.Message, oldMessageId: Long, errorCode: Int, errorMessage: String) = Unit

    override fun onMessageContent(chatId: Long, messageId: Long, newContent: TdApi.MessageContent) = Unit

    override fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: TdApi.ReplyMarkup) = Unit

    override fun onMessageViews(chatId: Long, messageId: Long, views: Int) = Unit

    override fun onMessageContentOpened(chatId: Long, messageId: Long) = Unit

    override fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int) = Unit

    override fun onNewChat(chat: TdApi.Chat) = Unit

    override fun onChatTitle(chatId: Long, title: String) = Unit

    override fun onChatPhoto(chatId: Long, photo: TdApi.ChatPhoto) = Unit

    override fun onChatPermissions(chatId: Long, permissions: TdApi.ChatPermissions) = Unit

    override fun onChatLastMessage(chatId: Long, lastMessage: TdApi.Message, order: Long) = Unit

    override fun onChatOrder(chatId: Long, order: Long) = Unit

    override fun onChatIsPinned(chatId: Long, isPinned: Boolean, order: Long) = Unit

    override fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean) = Unit

    override fun onChatIsSponsored(chatId: Long, isSponsored: Boolean, order: Long) = Unit

    override fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean) = Unit

    override fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int) = Unit

    override fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long) = Unit

    override fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int) = Unit

    override fun onChatNotificationSettings(chatId: Long, notificationSettings: TdApi.ChatNotificationSettings) = Unit

    override fun onScopeNotificationSettings(scope: TdApi.NotificationSettingsScope, notificationSettings: TdApi.ScopeNotificationSettings) = Unit

    override fun onChatPinnedMessage(chatId: Long, pinnedMessageId: Long) = Unit

    override fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long) = Unit

    override fun onChatDraftMessage(chatId: Long, draftMessage: TdApi.DraftMessage, order: Long) = Unit

    override fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int) = Unit

    override fun onNotification(notificationGroupId: Int, notification: TdApi.Notification) = Unit

    override fun onNotificationGroup(notificationGroupId: Int, type: TdApi.NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<TdApi.Notification>, removedNotificationIds: IntArray) = Unit

    override fun onActiveNotifications(groups: Array<TdApi.NotificationGroup>) = Unit

    override fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean) = Unit

    override fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) = Unit

    override fun onUserChatAction(chatId: Long, userId: Int, action: TdApi.ChatAction) = Unit

    override fun onUserStatus(userId: Int, status: TdApi.UserStatus) = Unit

    override fun onUser(user: TdApi.User) = Unit

    override fun onBasicGroup(basicGroup: TdApi.BasicGroup) = Unit

    override fun onSupergroup(supergroup: TdApi.Supergroup) = Unit

    override fun onSecretChat(secretChat: TdApi.SecretChat) = Unit

    override fun onUserFullInfo(userId: Int, userFullInfo: TdApi.UserFullInfo) = Unit

    override fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: TdApi.BasicGroupFullInfo) = Unit

    override fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: TdApi.SupergroupFullInfo) = Unit

    override fun onServiceNotification(type: String, content: TdApi.MessageContent) = Unit

    override fun onFile(file: TdApi.File) = Unit

    override fun onFileGenerationStart(generationId: Long, originalPath: String, destinationPath: String, conversion: String) = Unit

    override fun onFileGenerationStop(generationId: Long) = Unit

    override fun onCall(call: TdApi.Call) = Unit

    override fun onUserPrivacySettingRules(setting: TdApi.UserPrivacySetting, rules: TdApi.UserPrivacySettingRules) = Unit

    override fun onUnreadMessageCount(unreadCount: Int, unreadUnmutedCount: Int) = Unit

    override fun onUnreadChatCount(unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int) = Unit

    override fun onOption(name: String, value: TdApi.OptionValue) = Unit

    override fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray) = Unit

    override fun onTrendingStickerSets(stickerSets: TdApi.StickerSets) = Unit

    override fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray) = Unit

    override fun onFavoriteStickers(stickerIds: IntArray) = Unit

    override fun onSavedAnimations(animationIds: IntArray) = Unit

    override fun onSelectedBackground(forDarkTheme: Boolean, background: TdApi.Background?) = Unit

    override fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<TdApi.LanguagePackString>) = Unit

    override fun onConnectionState(state: TdApi.ConnectionState) = Unit

    override fun onTermsOfService(termsOfServiceId: String, termsOfService: TdApi.TermsOfService) = Unit

    override fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: TdApi.Location, query: String, offset: String) = Unit

    override fun onNewChosenInlineResult(senderUserId: Int, userLocation: TdApi.Location, query: String, resultId: String, inlineMessageId: String) = Unit

    override fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: TdApi.Address) = Unit

    override fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String, orderInfo: TdApi.OrderInfo) = Unit

    override fun onNewCustomEvent(event: String) = Unit

    override fun onNewCustomQuery(id: Long, data: String, timeout: Int) = Unit

    override fun onPoll(poll: TdApi.Poll) = Unit
}