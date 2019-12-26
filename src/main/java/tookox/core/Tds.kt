package tookox.core

import tooko.main.Fn
import tooko.main.Lang
import tooko.td.TdApi
import tookox.core.client.TdAbsHandler
import tookox.core.client.TdClient
import twitter4j.TwitterException

fun async(block: () -> Unit) = TdClient.asyncPool.execute(block)

val TdApi.User.displayName get() = "$firstName $lastName".trim()


fun <T : TdApi.Object> TdAbsHandler.execute(function: TdApi.Function): T = client.execute(function)
fun <T : TdApi.Object> TdAbsHandler.send(function: TdApi.Function, callback: (isOk: Boolean, result: T?, error: TdApi.Error?) -> Unit) = client.send(function, callback)

fun TdAbsHandler.postErr(chatId: Long, exception: Throwable): TdApi.Message {

    return if (exception is TwitterException) {

        postText(chatId, Fn.parseTwitterException(Lang.get(chatId), exception))

    } else {

        postText(chatId, "${exception.message}")

    }

}

fun TdAbsHandler.postText(chatId: Long, text: String, enableLinkPreview: Boolean = false): TdApi.Message {

    return execute(Fn.sendText(chatId, Fn.plainText(text), enableLinkPreview))

}

fun TdAbsHandler.postHtml(chatId: Long, text: String, enableLinkPreview: Boolean = false): TdApi.Message {

    return execute(Fn.sendText(chatId, Fn.parseHtml(text), enableLinkPreview))

}

fun TdAbsHandler.onEvent(event: TdApi.Object) {

    if (event is TdApi.UpdateAuthorizationState) {

        onAuthorizationState(event.authorizationState)

    } else if (event is TdApi.UpdateNewMessage) {

        onNewMessage(event.message.senderUserId, event.message.chatId, event.message)

    } else if (event is TdApi.UpdateMessageSendAcknowledged) {

        onMessageSendAcknowledged(event.chatId, event.messageId)

    } else if (event is TdApi.UpdateMessageSendSucceeded) {

        onMessageSendSucceeded(event.message, event.oldMessageId)

    } else if (event is TdApi.UpdateMessageSendFailed) {

        onMessageSendFailed(event.message, event.oldMessageId, event.errorCode, event.errorMessage)

    } else if (event is TdApi.UpdateMessageContent) {

        onMessageContent(event.chatId, event.messageId, event.newContent)

    } else if (event is TdApi.UpdateMessageEdited) {

        onMessageEdited(event.chatId, event.messageId, event.editDate, event.replyMarkup)

    } else if (event is TdApi.UpdateMessageViews) {

        onMessageViews(event.chatId, event.messageId, event.views)

    } else if (event is TdApi.UpdateMessageContentOpened) {

        onMessageContentOpened(event.chatId, event.messageId)

    } else if (event is TdApi.UpdateMessageMentionRead) {

        onMessageMentionRead(event.chatId, event.messageId, event.unreadMentionCount)

    } else if (event is TdApi.UpdateNewChat) {

        onNewChat(event.chat)

    } else if (event is TdApi.UpdateChatTitle) {

        onChatTitle(event.chatId, event.title)

    } else if (event is TdApi.UpdateChatPhoto) {

        onChatPhoto(event.chatId, event.photo)

    } else if (event is TdApi.UpdateChatPermissions) {

        onChatPermissions(event.chatId, event.permissions)

    } else if (event is TdApi.UpdateChatLastMessage) {

        onChatLastMessage(event.chatId, event.lastMessage, event.order)

    } else if (event is TdApi.UpdateChatOrder) {

        onChatOrder(event.chatId, event.order)

    } else if (event is TdApi.UpdateChatIsPinned) {

        onChatIsPinned(event.chatId, event.isPinned, event.order)

    } else if (event is TdApi.UpdateChatIsMarkedAsUnread) {

        onChatIsMarkedAsUnread(event.chatId, event.isMarkedAsUnread)

    } else if (event is TdApi.UpdateChatIsSponsored) {

        onChatIsSponsored(event.chatId, event.isSponsored, event.order)

    } else if (event is TdApi.UpdateChatDefaultDisableNotification) {

        onChatDefaultDisableNotification(event.chatId, event.defaultDisableNotification)

    } else if (event is TdApi.UpdateChatReadInbox) {

        onChatReadInbox(event.chatId, event.lastReadInboxMessageId, event.unreadCount)

    } else if (event is TdApi.UpdateChatReadOutbox) {

        onChatReadOutbox(event.chatId, event.lastReadOutboxMessageId)

    } else if (event is TdApi.UpdateChatUnreadMentionCount) {

        onChatUnreadMentionCount(event.chatId, event.unreadMentionCount)

    } else if (event is TdApi.UpdateChatNotificationSettings) {

        onChatNotificationSettings(event.chatId, event.notificationSettings)

    } else if (event is TdApi.UpdateScopeNotificationSettings) {

        onScopeNotificationSettings(event.scope, event.notificationSettings)

    } else if (event is TdApi.UpdateChatPinnedMessage) {

        onChatPinnedMessage(event.chatId, event.pinnedMessageId)

    } else if (event is TdApi.UpdateChatReplyMarkup) {

        onChatReplyMarkup(event.chatId, event.replyMarkupMessageId)

    } else if (event is TdApi.UpdateChatDraftMessage) {

        onChatDraftMessage(event.chatId, event.draftMessage, event.order)

    } else if (event is TdApi.UpdateChatOnlineMemberCount) {

        onChatOnlineMemberCount(event.chatId, event.onlineMemberCount)

    } else if (event is TdApi.UpdateNotification) {

        onNotification(event.notificationGroupId, event.notification)

    } else if (event is TdApi.UpdateNotificationGroup) {

        onNotificationGroup(event.notificationGroupId, event.type, event.chatId, event.notificationSettingsChatId, event.isSilent, event.totalCount, event.addedNotifications, event.removedNotificationIds)

    } else if (event is TdApi.UpdateActiveNotifications) {

        onActiveNotifications(event.groups)

    } else if (event is TdApi.UpdateHavePendingNotifications) {

        onHavePendingNotifications(event.haveDelayedNotifications, event.haveUnreceivedNotifications)

    } else if (event is TdApi.UpdateDeleteMessages) {

        onDeleteMessages(event.chatId, event.messageIds, event.isPermanent, event.fromCache)

    } else if (event is TdApi.UpdateUserChatAction) {

        onUserChatAction(event.chatId, event.userId, event.action)

    } else if (event is TdApi.UpdateUserStatus) {

        onUserStatus(event.userId, event.status)

    } else if (event is TdApi.UpdateUser) {

        onUser(event.user)

    } else if (event is TdApi.UpdateBasicGroup) {

        onBasicGroup(event.basicGroup)

    } else if (event is TdApi.UpdateSupergroup) {

        onSupergroup(event.supergroup)

    } else if (event is TdApi.UpdateSecretChat) {

        onSecretChat(event.secretChat)

    } else if (event is TdApi.UpdateUserFullInfo) {

        onUserFullInfo(event.userId, event.userFullInfo)

    } else if (event is TdApi.UpdateBasicGroupFullInfo) {

        onBasicGroupFullInfo(event.basicGroupId, event.basicGroupFullInfo)

    } else if (event is TdApi.UpdateSupergroupFullInfo) {

        onSupergroupFullInfo(event.supergroupId, event.supergroupFullInfo)

    } else if (event is TdApi.UpdateServiceNotification) {

        onServiceNotification(event.type, event.content)

    } else if (event is TdApi.UpdateFile) {

        onFile(event.file)

    } else if (event is TdApi.UpdateFileGenerationStart) {

        onFileGenerationStart(event.generationId, event.originalPath, event.destinationPath, event.conversion)

    } else if (event is TdApi.UpdateFileGenerationStop) {

        onFileGenerationStop(event.generationId)

    } else if (event is TdApi.UpdateCall) {

        onCall(event.call)

    } else if (event is TdApi.UpdateUserPrivacySettingRules) {

        onUserPrivacySettingRules(event.setting, event.rules)

    } else if (event is TdApi.UpdateUnreadMessageCount) {

        onUnreadMessageCount(event.unreadCount, event.unreadUnmutedCount)

    } else if (event is TdApi.UpdateUnreadChatCount) {

        onUnreadChatCount(event.unreadCount, event.unreadUnmutedCount, event.markedAsUnreadCount, event.markedAsUnreadUnmutedCount)

    } else if (event is TdApi.UpdateOption) {

        onOption(event.name, event.value)

    } else if (event is TdApi.UpdateInstalledStickerSets) {

        onInstalledStickerSets(event.isMasks, event.stickerSetIds)

    } else if (event is TdApi.UpdateTrendingStickerSets) {

        onTrendingStickerSets(event.stickerSets)

    } else if (event is TdApi.UpdateRecentStickers) {

        onRecentStickers(event.isAttached, event.stickerIds)

    } else if (event is TdApi.UpdateFavoriteStickers) {

        onFavoriteStickers(event.stickerIds)

    } else if (event is TdApi.UpdateSavedAnimations) {

        onSavedAnimations(event.animationIds)

    } else if (event is TdApi.UpdateSelectedBackground) {

        onSelectedBackground(event.forDarkTheme, event.background)

    } else if (event is TdApi.UpdateLanguagePackStrings) {

        onLanguagePackStrings(event.localizationTarget, event.languagePackId, event.strings)

    } else if (event is TdApi.UpdateConnectionState) {

        onConnectionState(event.state)

    } else if (event is TdApi.UpdateTermsOfService) {

        onTermsOfService(event.termsOfServiceId, event.termsOfService)

    } else if (event is TdApi.UpdateNewInlineQuery) {

        onNewInlineQuery(event.id, event.senderUserId, event.userLocation, event.query, event.offset)

    } else if (event is TdApi.UpdateNewChosenInlineResult) {

        onNewChosenInlineResult(event.senderUserId, event.userLocation, event.query, event.resultId, event.inlineMessageId)

    } else if (event is TdApi.UpdateNewCallbackQuery) {

        handleNewCallbackQuery(event.id, event.senderUserId, event.chatId, event.messageId, event.chatInstance, event.payload)

    } else if (event is TdApi.UpdateNewInlineCallbackQuery) {

        onNewInlineCallbackQuery(event.id, event.senderUserId, event.inlineMessageId, event.chatInstance, event.payload)

    } else if (event is TdApi.UpdateNewShippingQuery) {

        onNewShippingQuery(event.id, event.senderUserId, event.invoicePayload, event.shippingAddress)

    } else if (event is TdApi.UpdateNewPreCheckoutQuery) {

        onNewPreCheckoutQuery(event.id, event.senderUserId, event.currency, event.totalAmount, event.invoicePayload, event.shippingOptionId, event.orderInfo)

    } else if (event is TdApi.UpdateNewCustomEvent) {

        onNewCustomEvent(event.event)

    } else if (event is TdApi.UpdateNewCustomQuery) {

        onNewCustomQuery(event.id, event.data, event.timeout)

    } else if (event is TdApi.UpdatePoll) {

        onPoll(event.poll)
    }

}