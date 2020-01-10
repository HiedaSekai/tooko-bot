package tookox.core.client

import td.TdApi.*

open class TdHandler : TdAbsHandler {

    private lateinit var _client: TdClient

    override val sudo: TdClient get() = _client

    override fun onLoad(client: TdClient) {

        _client = client

        onLoad()

    }

    override fun onLoad() = Unit

    override suspend fun onLogin() = super.onLogin()

    override suspend fun onLogout() = Unit

    override suspend fun onDestroy() = Unit

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) = Unit

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = Unit

    override suspend fun onMessageSendAcknowledged(chatId: Long, messageId: Long) = Unit

    override suspend fun onMessageSendSucceeded(message: Message, oldMessageId: Long) = Unit

    override suspend fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) = Unit

    override suspend fun onMessageContent(chatId: Long, messageId: Long, newContent: MessageContent) = Unit

    override suspend fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup?) = Unit

    override suspend fun onMessageViews(chatId: Long, messageId: Long, views: Int) = Unit

    override suspend fun onMessageContentOpened(chatId: Long, messageId: Long) = Unit

    override suspend fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int) = Unit

    override suspend fun onNewChat(chat: Chat) = Unit

    override suspend fun onChatTitle(chatId: Long, title: String) = Unit

    override suspend fun onChatPhoto(chatId: Long, photo: ChatPhoto?) = Unit

    override suspend fun onChatPermissions(chatId: Long, permissions: ChatPermissions) = Unit

    override suspend fun onChatLastMessage(chatId: Long, lastMessage: Message?, order: Long) = Unit

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

    override suspend fun onChatDraftMessage(chatId: Long, draftMessage: DraftMessage?, order: Long) = Unit

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

    override suspend fun onFileGenerationStart(generationId: Long, originalPath: String?, destinationPath: String, conversion: String) = Unit

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

    override suspend fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: Location?, query: String, offset: String) = Unit

    override suspend fun onNewChosenInlineResult(senderUserId: Int, userLocation: Location?, query: String, resultId: String, inlineMessageId: String) = Unit

    override suspend fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address) = Unit

    override suspend fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String?, orderInfo: OrderInfo?) = Unit

    override suspend fun onNewCustomEvent(event: String) = Unit

    override suspend fun onNewCustomQuery(id: Long, data: String, timeout: Int) = Unit

    override suspend fun onPoll(poll: Poll) = Unit
}