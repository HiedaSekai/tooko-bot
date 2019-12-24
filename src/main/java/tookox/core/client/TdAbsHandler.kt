package tookox.core.client

import tooko.td.TdApi.*

interface TdAbsHandler {

    fun onLoad(client: TdClient) {}

    fun onLogin() {}

    fun onLogout() {}

    fun onDestroy() {}

    fun onEvent(event: Object) {

        if (event is UpdateAuthorizationState) {
            onAuthorizationState(event.authorizationState)
        } else if (event is UpdateNewMessage) {
            onNewMessage(event.message.senderUserId, event.message.chatId, event.message)
        } else if (event is UpdateMessageSendAcknowledged) {
            onMessageSendAcknowledged(event.chatId, event.messageId)
        } else if (event is UpdateMessageSendSucceeded) {
            onMessageSendSucceeded(event.message, event.oldMessageId)
        } else if (event is UpdateMessageSendFailed) {
            onMessageSendFailed(event.message, event.oldMessageId, event.errorCode, event.errorMessage)
        } else if (event is UpdateMessageContent) {
            onMessageContent(event.chatId, event.messageId, event.newContent)
        } else if (event is UpdateMessageEdited) {
            onMessageEdited(event.chatId, event.messageId, event.editDate, event.replyMarkup)
        } else if (event is UpdateMessageViews) {
            onMessageViews(event.chatId, event.messageId, event.views)
        } else if (event is UpdateMessageContentOpened) {
            onMessageContentOpened(event.chatId, event.messageId)
        } else if (event is UpdateMessageMentionRead) {
            onMessageMentionRead(event.chatId, event.messageId, event.unreadMentionCount)
        } else if (event is UpdateNewChat) {
            onNewChat(event.chat)
        } else if (event is UpdateChatTitle) {
            onChatTitle(event.chatId, event.title)
        } else if (event is UpdateChatPhoto) {
            onChatPhoto(event.chatId, event.photo)
        } else if (event is UpdateChatPermissions) {
            onChatPermissions(event.chatId, event.permissions)
        } else if (event is UpdateChatLastMessage) {
            onChatLastMessage(event.chatId, event.lastMessage, event.order)
        } else if (event is UpdateChatOrder) {
            onChatOrder(event.chatId, event.order)
        } else if (event is UpdateChatIsPinned) {
            onChatIsPinned(event.chatId, event.isPinned, event.order)
        } else if (event is UpdateChatIsMarkedAsUnread) {
            onChatIsMarkedAsUnread(event.chatId, event.isMarkedAsUnread)
        } else if (event is UpdateChatIsSponsored) {
            onChatIsSponsored(event.chatId, event.isSponsored, event.order)
        } else if (event is UpdateChatDefaultDisableNotification) {
            onChatDefaultDisableNotification(event.chatId, event.defaultDisableNotification)
        } else if (event is UpdateChatReadInbox) {
            onChatReadInbox(event.chatId, event.lastReadInboxMessageId, event.unreadCount)
        } else if (event is UpdateChatReadOutbox) {
            onChatReadOutbox(event.chatId, event.lastReadOutboxMessageId)
        } else if (event is UpdateChatUnreadMentionCount) {
            onChatUnreadMentionCount(event.chatId, event.unreadMentionCount)
        } else if (event is UpdateChatNotificationSettings) {
            onChatNotificationSettings(event.chatId, event.notificationSettings)
        } else if (event is UpdateScopeNotificationSettings) {
            onScopeNotificationSettings(event.scope, event.notificationSettings)
        } else if (event is UpdateChatPinnedMessage) {
            onChatPinnedMessage(event.chatId, event.pinnedMessageId)
        } else if (event is UpdateChatReplyMarkup) {
            onChatReplyMarkup(event.chatId, event.replyMarkupMessageId)
        } else if (event is UpdateChatDraftMessage) {
            onChatDraftMessage(event.chatId, event.draftMessage, event.order)
        } else if (event is UpdateChatOnlineMemberCount) {
            onChatOnlineMemberCount(event.chatId, event.onlineMemberCount)
        } else if (event is UpdateNotification) {
            onNotification(event.notificationGroupId, event.notification)
        } else if (event is UpdateNotificationGroup) {
            onNotificationGroup(event.notificationGroupId, event.type, event.chatId, event.notificationSettingsChatId, event.isSilent, event.totalCount, event.addedNotifications, event.removedNotificationIds)
        } else if (event is UpdateActiveNotifications) {
            onActiveNotifications(event.groups)
        } else if (event is UpdateHavePendingNotifications) {
            onHavePendingNotifications(event.haveDelayedNotifications, event.haveUnreceivedNotifications)
        } else if (event is UpdateDeleteMessages) {
            onDeleteMessages(event.chatId, event.messageIds, event.isPermanent, event.fromCache)
        } else if (event is UpdateUserChatAction) {
            onUserChatAction(event.chatId, event.userId, event.action)
        } else if (event is UpdateUserStatus) {
            onUserStatus(event.userId, event.status)
        } else if (event is UpdateUser) {
            onUser(event.user)
        } else if (event is UpdateBasicGroup) {
            onBasicGroup(event.basicGroup)
        } else if (event is UpdateSupergroup) {
            onSupergroup(event.supergroup)
        } else if (event is UpdateSecretChat) {
            onSecretChat(event.secretChat)
        } else if (event is UpdateUserFullInfo) {
            onUserFullInfo(event.userId, event.userFullInfo)
        } else if (event is UpdateBasicGroupFullInfo) {
            onBasicGroupFullInfo(event.basicGroupId, event.basicGroupFullInfo)
        } else if (event is UpdateSupergroupFullInfo) {
            onSupergroupFullInfo(event.supergroupId, event.supergroupFullInfo)
        } else if (event is UpdateServiceNotification) {
            onServiceNotification(event.type, event.content)
        } else if (event is UpdateFile) {
            onFile(event.file)
        } else if (event is UpdateFileGenerationStart) {
            onFileGenerationStart(event.generationId, event.originalPath, event.destinationPath, event.conversion)
        } else if (event is UpdateFileGenerationStop) {
            onFileGenerationStop(event.generationId)
        } else if (event is UpdateCall) {
            onCall(event.call)
        } else if (event is UpdateUserPrivacySettingRules) {
            onUserPrivacySettingRules(event.setting, event.rules)
        } else if (event is UpdateUnreadMessageCount) {
            onUnreadMessageCount(event.unreadCount, event.unreadUnmutedCount)
        } else if (event is UpdateUnreadChatCount) {
            onUnreadChatCount(event.unreadCount, event.unreadUnmutedCount, event.markedAsUnreadCount, event.markedAsUnreadUnmutedCount)
        } else if (event is UpdateOption) {
            onOption(event.name, event.value)
        } else if (event is UpdateInstalledStickerSets) {
            onInstalledStickerSets(event.isMasks, event.stickerSetIds)
        } else if (event is UpdateTrendingStickerSets) {
            onTrendingStickerSets(event.stickerSets)
        } else if (event is UpdateRecentStickers) {
            onRecentStickers(event.isAttached, event.stickerIds)
        } else if (event is UpdateFavoriteStickers) {
            onFavoriteStickers(event.stickerIds)
        } else if (event is UpdateSavedAnimations) {
            onSavedAnimations(event.animationIds)
        } else if (event is UpdateSelectedBackground) {
            onSelectedBackground(event.forDarkTheme, event.background)
        } else if (event is UpdateLanguagePackStrings) {
            onLanguagePackStrings(event.localizationTarget, event.languagePackId, event.strings)
        } else if (event is UpdateConnectionState) {
            onConnectionState(event.state)
        } else if (event is UpdateTermsOfService) {
            onTermsOfService(event.termsOfServiceId, event.termsOfService)
        } else if (event is UpdateNewInlineQuery) {
            onNewInlineQuery(event.id, event.senderUserId, event.userLocation, event.query, event.offset)
        } else if (event is UpdateNewChosenInlineResult) {
            onNewChosenInlineResult(event.senderUserId, event.userLocation, event.query, event.resultId, event.inlineMessageId)
        } else if (event is UpdateNewCallbackQuery) {
            handleNewCallbackQuery(event.id, event.senderUserId, event.chatId, event.messageId, event.chatInstance, event.payload)
        } else if (event is UpdateNewInlineCallbackQuery) {
            onNewInlineCallbackQuery(event.id, event.senderUserId, event.inlineMessageId, event.chatInstance, event.payload)
        } else if (event is UpdateNewShippingQuery) {
            onNewShippingQuery(event.id, event.senderUserId, event.invoicePayload, event.shippingAddress)
        } else if (event is UpdateNewPreCheckoutQuery) {
            onNewPreCheckoutQuery(event.id, event.senderUserId, event.currency, event.totalAmount, event.invoicePayload, event.shippingOptionId, event.orderInfo)
        } else if (event is UpdateNewCustomEvent) {
            onNewCustomEvent(event.event)
        } else if (event is UpdateNewCustomQuery) {
            onNewCustomQuery(event.id, event.data, event.timeout)
        } else if (event is UpdatePoll) {
            onPoll(event.poll)
        }

    }

    fun onAuthorizationState(authorizationState: AuthorizationState) {}

    fun onNewMessage(userId: Int, chatId: Long, message: Message) {}

    fun onMessageSendAcknowledged(chatId: Long, messageId: Long) {}

    fun onMessageSendSucceeded(message: Message, oldMessageId: Long) {}

    fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) {}

    fun onMessageContent(chatId: Long, messageId: Long, newContent: MessageContent) {}

    fun onMessageEdited(chatId: Long, messageId: Long, editDate: Int, replyMarkup: ReplyMarkup) {}

    fun onMessageViews(chatId: Long, messageId: Long, views: Int) {}

    fun onMessageContentOpened(chatId: Long, messageId: Long) {}

    fun onMessageMentionRead(chatId: Long, messageId: Long, unreadMentionCount: Int) {}

    fun onNewChat(chat: Chat) {}

    fun onChatTitle(chatId: Long, title: String) {}

    fun onChatPhoto(chatId: Long, photo: ChatPhoto) {}

    fun onChatPermissions(chatId: Long, permissions: ChatPermissions) {}

    fun onChatLastMessage(chatId: Long, lastMessage: Message, order: Long) {}

    fun onChatOrder(chatId: Long, order: Long) {}

    fun onChatIsPinned(chatId: Long, isPinned: Boolean, order: Long) {}

    fun onChatIsMarkedAsUnread(chatId: Long, isMarkedAsUnread: Boolean) {}

    fun onChatIsSponsored(chatId: Long, isSponsored: Boolean, order: Long) {}

    fun onChatDefaultDisableNotification(chatId: Long, defaultDisableNotification: Boolean) {}

    fun onChatReadInbox(chatId: Long, lastReadInboxMessageId: Long, unreadCount: Int) {}

    fun onChatReadOutbox(chatId: Long, lastReadOutboxMessageId: Long) {}

    fun onChatUnreadMentionCount(chatId: Long, unreadMentionCount: Int) {}

    fun onChatNotificationSettings(chatId: Long, notificationSettings: ChatNotificationSettings) {}

    fun onScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings) {}

    fun onChatPinnedMessage(chatId: Long, pinnedMessageId: Long) {}

    fun onChatReplyMarkup(chatId: Long, replyMarkupMessageId: Long) {}

    fun onChatDraftMessage(chatId: Long, draftMessage: DraftMessage, order: Long) {}

    fun onChatOnlineMemberCount(chatId: Long, onlineMemberCount: Int) {}

    fun onNotification(notificationGroupId: Int, notification: Notification) {}

    fun onNotificationGroup(notificationGroupId: Int, type: NotificationGroupType, chatId: Long, notificationSettingsChatId: Long, isSilent: Boolean, totalCount: Int, addedNotifications: Array<Notification>, removedNotificationIds: IntArray) {}

    fun onActiveNotifications(groups: Array<NotificationGroup>) {}

    fun onHavePendingNotifications(haveDelayedNotifications: Boolean, haveUnreceivedNotifications: Boolean) {}

    fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) {}

    fun onUserChatAction(chatId: Long, userId: Int, action: ChatAction) {}

    fun onUserStatus(userId: Int, status: UserStatus) {}

    fun onUser(user: User) {}

    fun onBasicGroup(basicGroup: BasicGroup) {}

    fun onSupergroup(supergroup: Supergroup) {}

    fun onSecretChat(secretChat: SecretChat) {}

    fun onUserFullInfo(userId: Int, userFullInfo: UserFullInfo) {}

    fun onBasicGroupFullInfo(basicGroupId: Int, basicGroupFullInfo: BasicGroupFullInfo) {}

    fun onSupergroupFullInfo(supergroupId: Int, supergroupFullInfo: SupergroupFullInfo) {}

    fun onServiceNotification(type: String, content: MessageContent) {}

    fun onFile(file: File) {}

    fun onFileGenerationStart(generationId: Long, originalPath: String, destinationPath: String, conversion: String) {}

    fun onFileGenerationStop(generationId: Long) {}

    fun onCall(call: Call) {}

    fun onUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules) {}

    fun onUnreadMessageCount(unreadCount: Int, unreadUnmutedCount: Int) {}

    fun onUnreadChatCount(unreadCount: Int, unreadUnmutedCount: Int, markedAsUnreadCount: Int, markedAsUnreadUnmutedCount: Int) {}

    fun onOption(name: String, value: OptionValue) {}

    fun onInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray) {}

    fun onTrendingStickerSets(stickerSets: StickerSets) {}

    fun onRecentStickers(isAttached: Boolean, stickerIds: IntArray) {}

    fun onFavoriteStickers(stickerIds: IntArray) {}

    fun onSavedAnimations(animationIds: IntArray) {}

    fun onSelectedBackground(forDarkTheme: Boolean, background: Background) {}

    fun onLanguagePackStrings(localizationTarget: String, languagePackId: String, strings: Array<LanguagePackString>) {}

    fun onConnectionState(state: ConnectionState) {}

    fun onTermsOfService(termsOfServiceId: String, termsOfService: TermsOfService) {}

    fun onNewInlineQuery(id: Long, senderUserId: Int, userLocation: Location, query: String, offset: String) {}

    fun onNewChosenInlineResult(senderUserId: Int, userLocation: Location, query: String, resultId: String, inlineMessageId: String) {}

    fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) {}

    fun onNewInlineCallbackQuery(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) {}

    fun onNewShippingQuery(id: Long, senderUserId: Int, invoicePayload: String, shippingAddress: Address) {}

    fun onNewPreCheckoutQuery(id: Long, senderUserId: Int, currency: String, totalAmount: Long, invoicePayload: ByteArray, shippingOptionId: String, orderInfo: OrderInfo) {}

    fun onNewCustomEvent(event: String) {}

    fun onNewCustomQuery(id: Long, data: String, timeout: Int) {}

    fun onPoll(poll: Poll) {}

}