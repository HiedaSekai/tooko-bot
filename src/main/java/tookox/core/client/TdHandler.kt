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

    override suspend fun onLogin() = Unit

    override suspend fun onLogout() = Unit

    override suspend fun onDestroy() = Unit

    override suspend fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) = Unit

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: TdApi.Message) = Unit

    override suspend fun onMessageSendAcknowledged(chatId: Long, messageId: Long) = Unit

    override suspend fun onMessageSendSucceeded(message: TdApi.Message, oldMessageId: Long) = Unit

    override suspend fun onMessageSendFailed(message: TdApi.Message, oldMessageId: Long, errorCode: Int, errorMessage: String) = Unit

    override suspend fun onMessageContent(chatId: Long, messageId: Long, newContent: TdApi.MessageContent) = Unit

    override suspend fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: TdApi.ReplyMarkup?) = Unit

    override suspend fun onMessageViews(chatId: Long, messageId: Long, views: Int) = Unit

    override suspend fun onMessageContentOpened(chatId: Long, messageId: Long) = Unit

    override suspend fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int) = Unit

    override suspend fun onNewChat(chat: TdApi.Chat) = Unit

    override suspend fun onChatTitle(chatId: Long, title: String) = Unit

    override suspend fun onChatPhoto(chatId: Long, photo: TdApi.ChatPhoto) = Unit

    override suspend fun onChatPermissions(chatId: Long, permissions: TdApi.ChatPermissions) = Unit

    override suspend fun onChatLastMessage(chatId: Long, lastMessage: TdApi.Message, order: Long) = Unit

    override suspend fun onChatOrder(chatId: Long, order: Long) = Unit

    override suspend fun onChatIsPinned(chatId: Long, isPinned: Boolean, order: Long) = Unit

    override suspend fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean) = Unit

    override suspend fun onChatIsSponsored(chatId: Long, isSponsored: Boolean, order: Long) = Unit

    override suspend fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean) = Unit

    override suspend fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int) = Unit

    override suspend fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long) = Unit

    override suspend fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int) = Unit

    override suspend fun onChatNotificationSettings(chatId: Long, notificationSettings: TdApi.ChatNotificationSettings) = Unit

    override suspend fun onScopeNotificationSettings(scope: TdApi.NotificationSettingsScope, notificationSettings: TdApi.ScopeNotificationSettings) = Unit

    override suspend fun onChatPinnedMessage(chatId: Long, pinnedMessageId: Long) = Unit

    override suspend fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long) = Unit

    override suspend fun onChatDraftMessage(chatId: Long, draftMessage: TdApi.DraftMessage, order: Long) = Unit

    override suspend fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int) = Unit

    override suspend fun onNotification(notificationGroupId: Int, notification: TdApi.Notification) = Unit

    override suspend fun onNotificationGroup(notificationGroupId: Int, type: TdApi.NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<TdApi.Notification>, removedNotificationIds: IntArray) = Unit

    override suspend fun onActiveNotifications(groups: Array<TdApi.NotificationGroup>) = Unit

    override suspend fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean) = Unit

    override suspend fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) = Unit

    override suspend fun onUserChatAction(chatId: Long, userId: Int, action: TdApi.ChatAction) = Unit

    override suspend fun onUserStatus(userId: Int, status: TdApi.UserStatus) = Unit

    override suspend fun onUser(user: TdApi.User) = Unit

    override suspend fun onBasicGroup(basicGroup: TdApi.BasicGroup) = Unit

    override suspend fun onSupergroup(supergroup: TdApi.Supergroup) = Unit

    override suspend fun onSecretChat(secretChat: TdApi.SecretChat) = Unit

    override suspend fun onUserFullInfo(userId: Int, userFullInfo: TdApi.UserFullInfo) = Unit

    override suspend fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: TdApi.BasicGroupFullInfo) = Unit

    override suspend fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: TdApi.SupergroupFullInfo) = Unit

    override suspend fun onServiceNotification(type: String, content: TdApi.MessageContent) = Unit

    override suspend fun onFile(file: TdApi.File) = Unit

    override suspend fun onFileGenerationStart(generationId: Long, originalPath: String, destinationPath: String, conversion: String) = Unit

    override suspend fun onFileGenerationStop(generationId: Long) = Unit

    override suspend fun onCall(call: TdApi.Call) = Unit

    override suspend fun onUserPrivacySettingRules(setting: TdApi.UserPrivacySetting, rules: TdApi.UserPrivacySettingRules) = Unit

    override suspend fun onUnreadMessageCount(unreadCount: Int, unreadUnmutedCount: Int) = Unit

    override suspend fun onUnreadChatCount(unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int) = Unit

    override suspend fun onOption(name: String, value: TdApi.OptionValue) = Unit

    override suspend fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray) = Unit

    override suspend fun onTrendingStickerSets(stickerSets: TdApi.StickerSets) = Unit

    override suspend fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray) = Unit

    override suspend fun onFavoriteStickers(stickerIds: IntArray) = Unit

    override suspend fun onSavedAnimations(animationIds: IntArray) = Unit

    override suspend fun onSelectedBackground(forDarkTheme: Boolean, background: TdApi.Background?) = Unit

    override suspend fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<TdApi.LanguagePackString>) = Unit

    override suspend fun onConnectionState(state: TdApi.ConnectionState) = Unit

    override suspend fun onTermsOfService(termsOfServiceId: String, termsOfService: TdApi.TermsOfService) = Unit

    override suspend fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: TdApi.Location, query: String, offset: String) = Unit

    override suspend fun onNewChosenInlineResult(senderUserId: Int, userLocation: TdApi.Location, query: String, resultId: String, inlineMessageId: String) = Unit

    override suspend fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: TdApi.Address) = Unit

    override suspend fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String, orderInfo: TdApi.OrderInfo) = Unit

    override suspend fun onNewCustomEvent(event: String) = Unit

    override suspend fun onNewCustomQuery(id: Long, data: String, timeout: Int) = Unit

    override suspend fun onPoll(poll: TdApi.Poll) = Unit
}