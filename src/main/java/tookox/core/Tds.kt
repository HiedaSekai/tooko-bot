@file:Suppress("unused")

package tookox.core

import cn.hutool.core.util.ArrayUtil
import tooko.td.TdApi
import tooko.td.TdApi.FormattedText
import tookox.core.client.TdAbsHandler

val TdApi.User.displayName get() = "$firstName $lastName".trim()

val TdApi.Message.fromPrivate get() = chatId > 0L
val TdApi.Message.fromBasicGroup get() = chatId > -1000000000000L
val TdApi.Message.fromSuperGroup get() = chatId < -1000000000000L && !isChannelPost
val TdApi.Message.fromChannel get() = isChannelPost

class Finish : RuntimeException("Finish Event")

fun TdAbsHandler.finishEvent() {

    throw Finish()

}

operator fun FormattedText.plus(text: FormattedText): FormattedText {

    val result = FormattedText()

    result.text = this.text + text.text

    result.entities = entities?.takeIf { it.isNotEmpty() } ?: arrayOfNulls(0)

    if (text.entities != null && text.entities.isEmpty()) {

        text.entities.forEach {

            it.offset += this.text.length

        }

        ArrayUtil.append(result.entities, text, entities)

    }

    return result

}

val FormattedText.asHtml: String
    get() {

        var htmlText = ""

        var index = 0

        entities.forEach {

            if (it.offset > index) {

                htmlText += text.substring(index, it.offset)

            }

            index = it.offset + it.length

            val entityText = text.substring(it.offset, index)

            htmlText += when (it.type) {

                is TdApi.TextEntityTypeBold -> entityText.asBlod
                is TdApi.TextEntityTypeItalic -> entityText.asItalic
                is TdApi.TextEntityTypeCode -> entityText.asCode
                is TdApi.TextEntityTypePre -> entityText.asCode
                is TdApi.TextEntityTypePreCode -> entityText.asCode
                is TdApi.TextEntityTypeUnderline -> entityText.asUnderline
                is TdApi.TextEntityTypeStrikethrough -> entityText.asDelete

                is TdApi.TextEntityTypeTextUrl -> entityText.toLink((it.type as TdApi.TextEntityTypeTextUrl).url)
                is TdApi.TextEntityTypeMentionName -> entityText.toInlineMention((it.type as TdApi.TextEntityTypeMentionName).userId)

                else -> entityText

            }

        }

        if (text.length > index) {

            htmlText += text.substring(index, text.length)

        }

        return htmlText

    }

fun TdAbsHandler.onEvent(event: TdApi.Object) {

    when (event) {

        is TdApi.UpdateAuthorizationState -> onAuthorizationState(event.authorizationState)
        is TdApi.UpdateNewMessage -> onNewMessage(event.message.senderUserId, event.message.chatId, event.message)
        is TdApi.UpdateMessageSendAcknowledged -> onMessageSendAcknowledged(event.chatId, event.messageId)
        is TdApi.UpdateMessageSendSucceeded -> onMessageSendSucceeded(event.message, event.oldMessageId)
        is TdApi.UpdateMessageSendFailed -> onMessageSendFailed(event.message, event.oldMessageId, event.errorCode, event.errorMessage)
        is TdApi.UpdateMessageContent -> onMessageContent(event.chatId, event.messageId, event.newContent)
        is TdApi.UpdateMessageEdited -> onMessageEdited(event.chatId, event.messageId, event.editDate, event.replyMarkup)
        is TdApi.UpdateMessageViews -> onMessageViews(event.chatId, event.messageId, event.views)
        is TdApi.UpdateMessageContentOpened -> onMessageContentOpened(event.chatId, event.messageId)
        is TdApi.UpdateMessageMentionRead -> onMessageMentionRead(event.chatId, event.messageId, event.unreadMentionCount)
        is TdApi.UpdateNewChat -> onNewChat(event.chat)
        is TdApi.UpdateChatTitle -> onChatTitle(event.chatId, event.title)
        is TdApi.UpdateChatPhoto -> onChatPhoto(event.chatId, event.photo)
        is TdApi.UpdateChatPermissions -> onChatPermissions(event.chatId, event.permissions)
        is TdApi.UpdateChatLastMessage -> onChatLastMessage(event.chatId, event.lastMessage, event.order)
        is TdApi.UpdateChatOrder -> onChatOrder(event.chatId, event.order)
        is TdApi.UpdateChatIsPinned -> onChatIsPinned(event.chatId, event.isPinned, event.order)
        is TdApi.UpdateChatIsMarkedAsUnread -> onChatIsMarkedAsUnread(event.chatId, event.isMarkedAsUnread)
        is TdApi.UpdateChatIsSponsored -> onChatIsSponsored(event.chatId, event.isSponsored, event.order)
        is TdApi.UpdateChatDefaultDisableNotification -> onChatDefaultDisableNotification(event.chatId, event.defaultDisableNotification)
        is TdApi.UpdateChatReadInbox -> onChatReadInbox(event.chatId, event.lastReadInboxMessageId, event.unreadCount)
        is TdApi.UpdateChatReadOutbox -> onChatReadOutbox(event.chatId, event.lastReadOutboxMessageId)
        is TdApi.UpdateChatUnreadMentionCount -> onChatUnreadMentionCount(event.chatId, event.unreadMentionCount)
        is TdApi.UpdateChatNotificationSettings -> onChatNotificationSettings(event.chatId, event.notificationSettings)
        is TdApi.UpdateScopeNotificationSettings -> onScopeNotificationSettings(event.scope, event.notificationSettings)
        is TdApi.UpdateChatPinnedMessage -> onChatPinnedMessage(event.chatId, event.pinnedMessageId)
        is TdApi.UpdateChatReplyMarkup -> onChatReplyMarkup(event.chatId, event.replyMarkupMessageId)
        is TdApi.UpdateChatDraftMessage -> onChatDraftMessage(event.chatId, event.draftMessage, event.order)
        is TdApi.UpdateChatOnlineMemberCount -> onChatOnlineMemberCount(event.chatId, event.onlineMemberCount)
        is TdApi.UpdateNotification -> onNotification(event.notificationGroupId, event.notification)
        is TdApi.UpdateNotificationGroup -> onNotificationGroup(event.notificationGroupId, event.type, event.chatId, event.notificationSettingsChatId, event.isSilent, event.totalCount, event.addedNotifications, event.removedNotificationIds)
        is TdApi.UpdateActiveNotifications -> onActiveNotifications(event.groups)
        is TdApi.UpdateHavePendingNotifications -> onHavePendingNotifications(event.haveDelayedNotifications, event.haveUnreceivedNotifications)
        is TdApi.UpdateDeleteMessages -> onDeleteMessages(event.chatId, event.messageIds, event.isPermanent, event.fromCache)
        is TdApi.UpdateUserChatAction -> onUserChatAction(event.chatId, event.userId, event.action)
        is TdApi.UpdateUserStatus -> onUserStatus(event.userId, event.status)
        is TdApi.UpdateUser -> onUser(event.user)
        is TdApi.UpdateBasicGroup -> onBasicGroup(event.basicGroup)
        is TdApi.UpdateSupergroup -> onSupergroup(event.supergroup)
        is TdApi.UpdateSecretChat -> onSecretChat(event.secretChat)
        is TdApi.UpdateUserFullInfo -> onUserFullInfo(event.userId, event.userFullInfo)
        is TdApi.UpdateBasicGroupFullInfo -> onBasicGroupFullInfo(event.basicGroupId, event.basicGroupFullInfo)
        is TdApi.UpdateSupergroupFullInfo -> onSupergroupFullInfo(event.supergroupId, event.supergroupFullInfo)
        is TdApi.UpdateServiceNotification -> onServiceNotification(event.type, event.content)
        is TdApi.UpdateFile -> onFile(event.file)
        is TdApi.UpdateFileGenerationStart -> onFileGenerationStart(event.generationId, event.originalPath, event.destinationPath, event.conversion)
        is TdApi.UpdateFileGenerationStop -> onFileGenerationStop(event.generationId)
        is TdApi.UpdateCall -> onCall(event.call)
        is TdApi.UpdateUserPrivacySettingRules -> onUserPrivacySettingRules(event.setting, event.rules)
        is TdApi.UpdateUnreadMessageCount -> onUnreadMessageCount(event.unreadCount, event.unreadUnmutedCount)
        is TdApi.UpdateUnreadChatCount -> onUnreadChatCount(event.unreadCount, event.unreadUnmutedCount, event.markedAsUnreadCount, event.markedAsUnreadUnmutedCount)
        is TdApi.UpdateOption -> onOption(event.name, event.value)
        is TdApi.UpdateInstalledStickerSets -> onInstalledStickerSets(event.isMasks, event.stickerSetIds)
        is TdApi.UpdateTrendingStickerSets -> onTrendingStickerSets(event.stickerSets)
        is TdApi.UpdateRecentStickers -> onRecentStickers(event.isAttached, event.stickerIds)
        is TdApi.UpdateFavoriteStickers -> onFavoriteStickers(event.stickerIds)
        is TdApi.UpdateSavedAnimations -> onSavedAnimations(event.animationIds)
        is TdApi.UpdateSelectedBackground -> onSelectedBackground(event.forDarkTheme, event.background)
        is TdApi.UpdateLanguagePackStrings -> onLanguagePackStrings(event.localizationTarget, event.languagePackId, event.strings)
        is TdApi.UpdateConnectionState -> onConnectionState(event.state)
        is TdApi.UpdateTermsOfService -> onTermsOfService(event.termsOfServiceId, event.termsOfService)
        is TdApi.UpdateNewInlineQuery -> onNewInlineQuery(event.id, event.senderUserId, event.userLocation, event.query, event.offset)
        is TdApi.UpdateNewChosenInlineResult -> onNewChosenInlineResult(event.senderUserId, event.userLocation, event.query, event.resultId, event.inlineMessageId)
        is TdApi.UpdateNewCallbackQuery -> handleNewCallbackQuery(event.id, event.senderUserId, event.chatId, event.messageId, event.chatInstance, event.payload)
        is TdApi.UpdateNewInlineCallbackQuery -> handleNewInlineCallbackQuery(event.id, event.senderUserId, event.inlineMessageId, event.chatInstance, event.payload)
        is TdApi.UpdateNewShippingQuery -> onNewShippingQuery(event.id, event.senderUserId, event.invoicePayload, event.shippingAddress)
        is TdApi.UpdateNewPreCheckoutQuery -> onNewPreCheckoutQuery(event.id, event.senderUserId, event.currency, event.totalAmount, event.invoicePayload, event.shippingOptionId, event.orderInfo)
        is TdApi.UpdateNewCustomEvent -> onNewCustomEvent(event.event)
        is TdApi.UpdateNewCustomQuery -> onNewCustomQuery(event.id, event.data, event.timeout)
        is TdApi.UpdatePoll -> onPoll(event.poll)

    }

}