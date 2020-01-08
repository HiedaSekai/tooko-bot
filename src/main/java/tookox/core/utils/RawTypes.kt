@file:Suppress("unused")

package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import tooko.td.TdApi.*
import tookox.core.client.*


suspend fun TdAbsHandler.writeGeneratedFilePart(generationId: Long, offset: Int, data: ByteArray) = sync<Ok>(WriteGeneratedFilePart(generationId, offset, data))
suspend fun TdAbsHandler.writeGeneratedFilePartOrNull(generationId: Long, offset: Int, data: ByteArray) = syncOrNull<Ok>(WriteGeneratedFilePart(generationId, offset, data))
fun TdAbsHandler.writeGeneratedFilePart(generationId: Long, offset: Int, data: ByteArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(WriteGeneratedFilePart(generationId, offset, data), 1, block)


suspend infix fun TdAbsHandler.viewTrendingStickerSets(stickerSetIds: LongArray) = sync<Ok>(ViewTrendingStickerSets(stickerSetIds))
suspend infix fun TdAbsHandler.viewTrendingStickerSetsOrNull(stickerSetIds: LongArray) = syncOrNull<Ok>(ViewTrendingStickerSets(stickerSetIds))
fun TdAbsHandler.viewTrendingStickerSets(stickerSetIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ViewTrendingStickerSets(stickerSetIds), 1, block)


suspend fun TdAbsHandler.viewMessages(chatId: Number, messageIds: LongArray, forceRead: Boolean) = sync<Ok>(ViewMessages(chatId.toLong(), messageIds, forceRead))
suspend fun TdAbsHandler.viewMessagesOrNull(chatId: Number, messageIds: LongArray, forceRead: Boolean) = syncOrNull<Ok>(ViewMessages(chatId.toLong(), messageIds, forceRead))
fun TdAbsHandler.viewMessages(chatId: Number, messageIds: LongArray, forceRead: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ViewMessages(chatId.toLong(), messageIds, forceRead), 1, block)


suspend fun TdAbsHandler.validateOrderInfo(chatId: Number, messageId: Long, orderInfo: OrderInfo, allowSave: Boolean) = sync<Ok>(ValidateOrderInfo(chatId.toLong(), messageId, orderInfo, allowSave))
suspend fun TdAbsHandler.validateOrderInfoOrNull(chatId: Number, messageId: Long, orderInfo: OrderInfo, allowSave: Boolean) = syncOrNull<Ok>(ValidateOrderInfo(chatId.toLong(), messageId, orderInfo, allowSave))
fun TdAbsHandler.validateOrderInfo(chatId: Number, messageId: Long, orderInfo: OrderInfo, allowSave: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ValidateOrderInfo(chatId.toLong(), messageId, orderInfo, allowSave), 1, block)


suspend fun TdAbsHandler.uploadStickerFile(userId: Int, pngSticker: InputFile) = sync<Ok>(UploadStickerFile(userId, pngSticker))
suspend fun TdAbsHandler.uploadStickerFileOrNull(userId: Int, pngSticker: InputFile) = syncOrNull<Ok>(UploadStickerFile(userId, pngSticker))
fun TdAbsHandler.uploadStickerFile(userId: Int, pngSticker: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(UploadStickerFile(userId, pngSticker), 1, block)


suspend fun TdAbsHandler.uploadFile(file: InputFile, fileType: FileType, priority: Int) = sync<Ok>(UploadFile(file, fileType, priority))
suspend fun TdAbsHandler.uploadFileOrNull(file: InputFile, fileType: FileType, priority: Int) = syncOrNull<Ok>(UploadFile(file, fileType, priority))
fun TdAbsHandler.uploadFile(file: InputFile, fileType: FileType, priority: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(UploadFile(file, fileType, priority), 1, block)


suspend infix fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChat(chatId: Number) = sync<Ok>(UpgradeBasicGroupChatToSupergroupChat(chatId.toLong()))
suspend infix fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChatOrNull(chatId: Number) = syncOrNull<Ok>(UpgradeBasicGroupChatToSupergroupChat(chatId.toLong()))
fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(UpgradeBasicGroupChatToSupergroupChat(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.unpinChatMessage(chatId: Number) = sync<Ok>(UnpinChatMessage(chatId.toLong()))
suspend infix fun TdAbsHandler.unpinChatMessageOrNull(chatId: Number) = syncOrNull<Ok>(UnpinChatMessage(chatId.toLong()))
fun TdAbsHandler.unpinChatMessage(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(UnpinChatMessage(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.unblockUser(userId: Int) = sync<Ok>(UnblockUser(userId))
suspend infix fun TdAbsHandler.unblockUserOrNull(userId: Int) = syncOrNull<Ok>(UnblockUser(userId))
fun TdAbsHandler.unblockUser(userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(UnblockUser(userId), 1, block)


suspend fun TdAbsHandler.transferChatOwnership(chatId: Number, userId: Int, password: String) = sync<Ok>(TransferChatOwnership(chatId.toLong(), userId, password))
suspend fun TdAbsHandler.transferChatOwnershipOrNull(chatId: Number, userId: Int, password: String) = syncOrNull<Ok>(TransferChatOwnership(chatId.toLong(), userId, password))
fun TdAbsHandler.transferChatOwnership(chatId: Number, userId: Int, password: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TransferChatOwnership(chatId.toLong(), userId, password), 1, block)


suspend fun TdAbsHandler.toggleSupergroupSignMessages(supergroupId: Int, signMessages: Boolean) = sync<Ok>(ToggleSupergroupSignMessages(supergroupId, signMessages))
suspend fun TdAbsHandler.toggleSupergroupSignMessagesOrNull(supergroupId: Int, signMessages: Boolean) = syncOrNull<Ok>(ToggleSupergroupSignMessages(supergroupId, signMessages))
fun TdAbsHandler.toggleSupergroupSignMessages(supergroupId: Int, signMessages: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ToggleSupergroupSignMessages(supergroupId, signMessages), 1, block)


suspend fun TdAbsHandler.toggleSupergroupIsAllHistoryAvailable(supergroupId: Int, isAllHistoryAvailable: Boolean) = sync<Ok>(ToggleSupergroupIsAllHistoryAvailable(supergroupId, isAllHistoryAvailable))
suspend fun TdAbsHandler.toggleSupergroupIsAllHistoryAvailableOrNull(supergroupId: Int, isAllHistoryAvailable: Boolean) = syncOrNull<Ok>(ToggleSupergroupIsAllHistoryAvailable(supergroupId, isAllHistoryAvailable))
fun TdAbsHandler.toggleSupergroupIsAllHistoryAvailable(supergroupId: Int, isAllHistoryAvailable: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ToggleSupergroupIsAllHistoryAvailable(supergroupId, isAllHistoryAvailable), 1, block)


suspend fun TdAbsHandler.toggleChatIsPinned(chatId: Number, isPinned: Boolean) = sync<Ok>(ToggleChatIsPinned(chatId.toLong(), isPinned))
suspend fun TdAbsHandler.toggleChatIsPinnedOrNull(chatId: Number, isPinned: Boolean) = syncOrNull<Ok>(ToggleChatIsPinned(chatId.toLong(), isPinned))
fun TdAbsHandler.toggleChatIsPinned(chatId: Number, isPinned: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ToggleChatIsPinned(chatId.toLong(), isPinned), 1, block)


suspend fun TdAbsHandler.toggleChatIsMarkedAsUnread(chatId: Number, isMarkedAsUnread: Boolean) = sync<Ok>(ToggleChatIsMarkedAsUnread(chatId.toLong(), isMarkedAsUnread))
suspend fun TdAbsHandler.toggleChatIsMarkedAsUnreadOrNull(chatId: Number, isMarkedAsUnread: Boolean) = syncOrNull<Ok>(ToggleChatIsMarkedAsUnread(chatId.toLong(), isMarkedAsUnread))
fun TdAbsHandler.toggleChatIsMarkedAsUnread(chatId: Number, isMarkedAsUnread: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ToggleChatIsMarkedAsUnread(chatId.toLong(), isMarkedAsUnread), 1, block)


suspend fun TdAbsHandler.toggleChatDefaultDisableNotification(chatId: Number, defaultDisableNotification: Boolean) = sync<Ok>(ToggleChatDefaultDisableNotification(chatId.toLong(), defaultDisableNotification))
suspend fun TdAbsHandler.toggleChatDefaultDisableNotificationOrNull(chatId: Number, defaultDisableNotification: Boolean) = syncOrNull<Ok>(ToggleChatDefaultDisableNotification(chatId.toLong(), defaultDisableNotification))
fun TdAbsHandler.toggleChatDefaultDisableNotification(chatId: Number, defaultDisableNotification: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ToggleChatDefaultDisableNotification(chatId.toLong(), defaultDisableNotification), 1, block)


suspend fun TdAbsHandler.testUseUpdate() = sync<Ok>(TestUseUpdate())
suspend fun TdAbsHandler.testUseUpdateOrNull() = syncOrNull<Ok>(TestUseUpdate())
fun TdAbsHandler.testUseUpdate(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestUseUpdate(), 1, block)


suspend infix fun TdAbsHandler.testSquareInt(x: Int) = sync<Ok>(TestSquareInt(x))
suspend infix fun TdAbsHandler.testSquareIntOrNull(x: Int) = syncOrNull<Ok>(TestSquareInt(x))
fun TdAbsHandler.testSquareInt(x: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestSquareInt(x), 1, block)


suspend infix fun TdAbsHandler.testReturnError(error: Error) = sync<Ok>(TestReturnError(error))
suspend infix fun TdAbsHandler.testReturnErrorOrNull(error: Error) = syncOrNull<Ok>(TestReturnError(error))
fun TdAbsHandler.testReturnError(error: Error, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestReturnError(error), 1, block)


suspend fun TdAbsHandler.testProxy(server: String, port: Int, type: ProxyType, dcId: Int, timeout: Double) = sync<Ok>(TestProxy(server, port, type, dcId, timeout))
suspend fun TdAbsHandler.testProxyOrNull(server: String, port: Int, type: ProxyType, dcId: Int, timeout: Double) = syncOrNull<Ok>(TestProxy(server, port, type, dcId, timeout))
fun TdAbsHandler.testProxy(server: String, port: Int, type: ProxyType, dcId: Int, timeout: Double, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestProxy(server, port, type, dcId, timeout), 1, block)


suspend fun TdAbsHandler.testNetwork() = sync<Ok>(TestNetwork())
suspend fun TdAbsHandler.testNetworkOrNull() = syncOrNull<Ok>(TestNetwork())
fun TdAbsHandler.testNetwork(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestNetwork(), 1, block)


suspend fun TdAbsHandler.testGetDifference() = sync<Ok>(TestGetDifference())
suspend fun TdAbsHandler.testGetDifferenceOrNull() = syncOrNull<Ok>(TestGetDifference())
fun TdAbsHandler.testGetDifference(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestGetDifference(), 1, block)


suspend infix fun TdAbsHandler.testCallVectorStringObject(x: Array<TestString>) = sync<Ok>(TestCallVectorStringObject(x))
suspend infix fun TdAbsHandler.testCallVectorStringObjectOrNull(x: Array<TestString>) = syncOrNull<Ok>(TestCallVectorStringObject(x))
fun TdAbsHandler.testCallVectorStringObject(x: Array<TestString>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallVectorStringObject(x), 1, block)


suspend infix fun TdAbsHandler.testCallVectorString(x: Array<String>) = sync<Ok>(TestCallVectorString(x))
suspend infix fun TdAbsHandler.testCallVectorStringOrNull(x: Array<String>) = syncOrNull<Ok>(TestCallVectorString(x))
fun TdAbsHandler.testCallVectorString(x: Array<String>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallVectorString(x), 1, block)


suspend infix fun TdAbsHandler.testCallVectorIntObject(x: Array<TestInt>) = sync<Ok>(TestCallVectorIntObject(x))
suspend infix fun TdAbsHandler.testCallVectorIntObjectOrNull(x: Array<TestInt>) = syncOrNull<Ok>(TestCallVectorIntObject(x))
fun TdAbsHandler.testCallVectorIntObject(x: Array<TestInt>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallVectorIntObject(x), 1, block)


suspend infix fun TdAbsHandler.testCallVectorInt(x: IntArray) = sync<Ok>(TestCallVectorInt(x))
suspend infix fun TdAbsHandler.testCallVectorIntOrNull(x: IntArray) = syncOrNull<Ok>(TestCallVectorInt(x))
fun TdAbsHandler.testCallVectorInt(x: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallVectorInt(x), 1, block)


suspend infix fun TdAbsHandler.testCallString(x: String) = sync<Ok>(TestCallString(x))
suspend infix fun TdAbsHandler.testCallStringOrNull(x: String) = syncOrNull<Ok>(TestCallString(x))
fun TdAbsHandler.testCallString(x: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallString(x), 1, block)


suspend fun TdAbsHandler.testCallEmpty() = sync<Ok>(TestCallEmpty())
suspend fun TdAbsHandler.testCallEmptyOrNull() = syncOrNull<Ok>(TestCallEmpty())
fun TdAbsHandler.testCallEmpty(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallEmpty(), 1, block)


suspend infix fun TdAbsHandler.testCallBytes(x: ByteArray) = sync<Ok>(TestCallBytes(x))
suspend infix fun TdAbsHandler.testCallBytesOrNull(x: ByteArray) = syncOrNull<Ok>(TestCallBytes(x))
fun TdAbsHandler.testCallBytes(x: ByteArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallBytes(x), 1, block)


suspend infix fun TdAbsHandler.terminateSession(sessionId: Long) = sync<Ok>(TerminateSession(sessionId))
suspend infix fun TdAbsHandler.terminateSessionOrNull(sessionId: Long) = syncOrNull<Ok>(TerminateSession(sessionId))
fun TdAbsHandler.terminateSession(sessionId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TerminateSession(sessionId), 1, block)


suspend fun TdAbsHandler.terminateAllOtherSessions() = sync<Ok>(TerminateAllOtherSessions())
suspend fun TdAbsHandler.terminateAllOtherSessionsOrNull() = syncOrNull<Ok>(TerminateAllOtherSessions())
fun TdAbsHandler.terminateAllOtherSessions(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TerminateAllOtherSessions(), 1, block)


suspend infix fun TdAbsHandler.synchronizeLanguagePack(languagePackId: String) = sync<Ok>(SynchronizeLanguagePack(languagePackId))
suspend infix fun TdAbsHandler.synchronizeLanguagePackOrNull(languagePackId: String) = syncOrNull<Ok>(SynchronizeLanguagePack(languagePackId))
fun TdAbsHandler.synchronizeLanguagePack(languagePackId: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SynchronizeLanguagePack(languagePackId), 1, block)


suspend fun TdAbsHandler.stopPoll(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup) = sync<Ok>(StopPoll(chatId.toLong(), messageId, replyMarkup))
suspend fun TdAbsHandler.stopPollOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup) = syncOrNull<Ok>(StopPoll(chatId.toLong(), messageId, replyMarkup))
fun TdAbsHandler.stopPoll(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(StopPoll(chatId.toLong(), messageId, replyMarkup), 1, block)


suspend infix fun TdAbsHandler.sharePhoneNumber(userId: Int) = sync<Ok>(SharePhoneNumber(userId))
suspend infix fun TdAbsHandler.sharePhoneNumberOrNull(userId: Int) = syncOrNull<Ok>(SharePhoneNumber(userId))
fun TdAbsHandler.sharePhoneNumber(userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SharePhoneNumber(userId), 1, block)


suspend infix fun TdAbsHandler.setUsername(username: String) = sync<Ok>(SetUsername(username))
suspend infix fun TdAbsHandler.setUsernameOrNull(username: String) = syncOrNull<Ok>(SetUsername(username))
fun TdAbsHandler.setUsername(username: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetUsername(username), 1, block)


suspend fun TdAbsHandler.setUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules) = sync<Ok>(SetUserPrivacySettingRules(setting, rules))
suspend fun TdAbsHandler.setUserPrivacySettingRulesOrNull(setting: UserPrivacySetting, rules: UserPrivacySettingRules) = syncOrNull<Ok>(SetUserPrivacySettingRules(setting, rules))
fun TdAbsHandler.setUserPrivacySettingRules(setting: UserPrivacySetting, rules: UserPrivacySettingRules, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetUserPrivacySettingRules(setting, rules), 1, block)


suspend infix fun TdAbsHandler.setTdlibParameters(parameters: TdlibParameters) = sync<Ok>(SetTdlibParameters(parameters))
suspend infix fun TdAbsHandler.setTdlibParametersOrNull(parameters: TdlibParameters) = syncOrNull<Ok>(SetTdlibParameters(parameters))
fun TdAbsHandler.setTdlibParameters(parameters: TdlibParameters, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetTdlibParameters(parameters), 1, block)


suspend fun TdAbsHandler.setSupergroupUsername(supergroupId: Int, username: String) = sync<Ok>(SetSupergroupUsername(supergroupId, username))
suspend fun TdAbsHandler.setSupergroupUsernameOrNull(supergroupId: Int, username: String) = syncOrNull<Ok>(SetSupergroupUsername(supergroupId, username))
fun TdAbsHandler.setSupergroupUsername(supergroupId: Int, username: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetSupergroupUsername(supergroupId, username), 1, block)


suspend fun TdAbsHandler.setSupergroupStickerSet(supergroupId: Int, stickerSetId: Long) = sync<Ok>(SetSupergroupStickerSet(supergroupId, stickerSetId))
suspend fun TdAbsHandler.setSupergroupStickerSetOrNull(supergroupId: Int, stickerSetId: Long) = syncOrNull<Ok>(SetSupergroupStickerSet(supergroupId, stickerSetId))
fun TdAbsHandler.setSupergroupStickerSet(supergroupId: Int, stickerSetId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetSupergroupStickerSet(supergroupId, stickerSetId), 1, block)


suspend fun TdAbsHandler.setStickerPositionInSet(sticker: InputFile, position: Int) = sync<Ok>(SetStickerPositionInSet(sticker, position))
suspend fun TdAbsHandler.setStickerPositionInSetOrNull(sticker: InputFile, position: Int) = syncOrNull<Ok>(SetStickerPositionInSet(sticker, position))
fun TdAbsHandler.setStickerPositionInSet(sticker: InputFile, position: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetStickerPositionInSet(sticker, position), 1, block)


suspend fun TdAbsHandler.setScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings) = sync<Ok>(SetScopeNotificationSettings(scope, notificationSettings))
suspend fun TdAbsHandler.setScopeNotificationSettingsOrNull(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings) = syncOrNull<Ok>(SetScopeNotificationSettings(scope, notificationSettings))
fun TdAbsHandler.setScopeNotificationSettings(scope: NotificationSettingsScope, notificationSettings: ScopeNotificationSettings, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetScopeNotificationSettings(scope, notificationSettings), 1, block)


suspend fun TdAbsHandler.setRecoveryEmailAddress(password: String, newRecoveryEmailAddress: String) = sync<Ok>(SetRecoveryEmailAddress(password, newRecoveryEmailAddress))
suspend fun TdAbsHandler.setRecoveryEmailAddressOrNull(password: String, newRecoveryEmailAddress: String) = syncOrNull<Ok>(SetRecoveryEmailAddress(password, newRecoveryEmailAddress))
fun TdAbsHandler.setRecoveryEmailAddress(password: String, newRecoveryEmailAddress: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetRecoveryEmailAddress(password, newRecoveryEmailAddress), 1, block)


suspend infix fun TdAbsHandler.setProfilePhoto(photo: InputFile) = sync<Ok>(SetProfilePhoto(photo))
suspend infix fun TdAbsHandler.setProfilePhotoOrNull(photo: InputFile) = syncOrNull<Ok>(SetProfilePhoto(photo))
fun TdAbsHandler.setProfilePhoto(photo: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetProfilePhoto(photo), 1, block)


suspend fun TdAbsHandler.setPollAnswer(chatId: Number, messageId: Long, optionIds: IntArray) = sync<Ok>(SetPollAnswer(chatId.toLong(), messageId, optionIds))
suspend fun TdAbsHandler.setPollAnswerOrNull(chatId: Number, messageId: Long, optionIds: IntArray) = syncOrNull<Ok>(SetPollAnswer(chatId.toLong(), messageId, optionIds))
fun TdAbsHandler.setPollAnswer(chatId: Number, messageId: Long, optionIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPollAnswer(chatId.toLong(), messageId, optionIds), 1, block)


suspend fun TdAbsHandler.setPinnedChats(chatList: ChatList, chatIds: LongArray) = sync<Ok>(SetPinnedChats(chatList, chatIds))
suspend fun TdAbsHandler.setPinnedChatsOrNull(chatList: ChatList, chatIds: LongArray) = syncOrNull<Ok>(SetPinnedChats(chatList, chatIds))
fun TdAbsHandler.setPinnedChats(chatList: ChatList, chatIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPinnedChats(chatList, chatIds), 1, block)


suspend fun TdAbsHandler.setPassword(oldPassword: String, newPassword: String, newHint: String, setRecoveryEmailAddress: Boolean, newRecoveryEmailAddress: String) = sync<Ok>(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress))
suspend fun TdAbsHandler.setPasswordOrNull(oldPassword: String, newPassword: String, newHint: String, setRecoveryEmailAddress: Boolean, newRecoveryEmailAddress: String) = syncOrNull<Ok>(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress))
fun TdAbsHandler.setPassword(oldPassword: String, newPassword: String, newHint: String, setRecoveryEmailAddress: Boolean, newRecoveryEmailAddress: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress), 1, block)


suspend fun TdAbsHandler.setPassportElementErrors(userId: Int, errors: Array<InputPassportElementError>) = sync<Ok>(SetPassportElementErrors(userId, errors))
suspend fun TdAbsHandler.setPassportElementErrorsOrNull(userId: Int, errors: Array<InputPassportElementError>) = syncOrNull<Ok>(SetPassportElementErrors(userId, errors))
fun TdAbsHandler.setPassportElementErrors(userId: Int, errors: Array<InputPassportElementError>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPassportElementErrors(userId, errors), 1, block)


suspend fun TdAbsHandler.setPassportElement(element: InputPassportElement, password: String) = sync<Ok>(SetPassportElement(element, password))
suspend fun TdAbsHandler.setPassportElementOrNull(element: InputPassportElement, password: String) = syncOrNull<Ok>(SetPassportElement(element, password))
fun TdAbsHandler.setPassportElement(element: InputPassportElement, password: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPassportElement(element, password), 1, block)


suspend fun TdAbsHandler.setOption(name: String, value: OptionValue) = sync<Ok>(SetOption(name, value))
suspend fun TdAbsHandler.setOptionOrNull(name: String, value: OptionValue) = syncOrNull<Ok>(SetOption(name, value))
fun TdAbsHandler.setOption(name: String, value: OptionValue, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetOption(name, value), 1, block)


suspend infix fun TdAbsHandler.setNetworkType(type: NetworkType) = sync<Ok>(SetNetworkType(type))
suspend infix fun TdAbsHandler.setNetworkTypeOrNull(type: NetworkType) = syncOrNull<Ok>(SetNetworkType(type))
fun TdAbsHandler.setNetworkType(type: NetworkType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetNetworkType(type), 1, block)


suspend fun TdAbsHandler.setName(firstName: String, lastName: String) = sync<Ok>(SetName(firstName, lastName))
suspend fun TdAbsHandler.setNameOrNull(firstName: String, lastName: String) = syncOrNull<Ok>(SetName(firstName, lastName))
fun TdAbsHandler.setName(firstName: String, lastName: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetName(firstName, lastName), 1, block)


suspend infix fun TdAbsHandler.setLogVerbosityLevel(newVerbosityLevel: Int) = sync<Ok>(SetLogVerbosityLevel(newVerbosityLevel))
suspend infix fun TdAbsHandler.setLogVerbosityLevelOrNull(newVerbosityLevel: Int) = syncOrNull<Ok>(SetLogVerbosityLevel(newVerbosityLevel))
fun TdAbsHandler.setLogVerbosityLevel(newVerbosityLevel: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetLogVerbosityLevel(newVerbosityLevel), 1, block)


suspend fun TdAbsHandler.setLogTagVerbosityLevel(tag: String, newVerbosityLevel: Int) = sync<Ok>(SetLogTagVerbosityLevel(tag, newVerbosityLevel))
suspend fun TdAbsHandler.setLogTagVerbosityLevelOrNull(tag: String, newVerbosityLevel: Int) = syncOrNull<Ok>(SetLogTagVerbosityLevel(tag, newVerbosityLevel))
fun TdAbsHandler.setLogTagVerbosityLevel(tag: String, newVerbosityLevel: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetLogTagVerbosityLevel(tag, newVerbosityLevel), 1, block)


suspend infix fun TdAbsHandler.setLogStream(logStream: LogStream) = sync<Ok>(SetLogStream(logStream))
suspend infix fun TdAbsHandler.setLogStreamOrNull(logStream: LogStream) = syncOrNull<Ok>(SetLogStream(logStream))
fun TdAbsHandler.setLogStream(logStream: LogStream, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetLogStream(logStream), 1, block)


suspend fun TdAbsHandler.setInlineGameScore(inlineMessageId: String, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = sync<Ok>(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force))
suspend fun TdAbsHandler.setInlineGameScoreOrNull(inlineMessageId: String, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = syncOrNull<Ok>(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force))
fun TdAbsHandler.setInlineGameScore(inlineMessageId: String, editMessage: Boolean, userId: Int, score: Int, force: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force), 1, block)


suspend fun TdAbsHandler.setGameScore(chatId: Number, messageId: Long, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = sync<Ok>(SetGameScore(chatId.toLong(), messageId, editMessage, userId, score, force))
suspend fun TdAbsHandler.setGameScoreOrNull(chatId: Number, messageId: Long, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = syncOrNull<Ok>(SetGameScore(chatId.toLong(), messageId, editMessage, userId, score, force))
fun TdAbsHandler.setGameScore(chatId: Number, messageId: Long, editMessage: Boolean, userId: Int, score: Int, force: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetGameScore(chatId.toLong(), messageId, editMessage, userId, score, force), 1, block)


suspend fun TdAbsHandler.setFileGenerationProgress(generationId: Long, expectedSize: Int, localPrefixSize: Int) = sync<Ok>(SetFileGenerationProgress(generationId, expectedSize, localPrefixSize))
suspend fun TdAbsHandler.setFileGenerationProgressOrNull(generationId: Long, expectedSize: Int, localPrefixSize: Int) = syncOrNull<Ok>(SetFileGenerationProgress(generationId, expectedSize, localPrefixSize))
fun TdAbsHandler.setFileGenerationProgress(generationId: Long, expectedSize: Int, localPrefixSize: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetFileGenerationProgress(generationId, expectedSize, localPrefixSize), 1, block)


suspend infix fun TdAbsHandler.setDatabaseEncryptionKey(newEncryptionKey: ByteArray) = sync<Ok>(SetDatabaseEncryptionKey(newEncryptionKey))
suspend infix fun TdAbsHandler.setDatabaseEncryptionKeyOrNull(newEncryptionKey: ByteArray) = syncOrNull<Ok>(SetDatabaseEncryptionKey(newEncryptionKey))
fun TdAbsHandler.setDatabaseEncryptionKey(newEncryptionKey: ByteArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetDatabaseEncryptionKey(newEncryptionKey), 1, block)


suspend fun TdAbsHandler.setCustomLanguagePackString(languagePackId: String, newString: LanguagePackString) = sync<Ok>(SetCustomLanguagePackString(languagePackId, newString))
suspend fun TdAbsHandler.setCustomLanguagePackStringOrNull(languagePackId: String, newString: LanguagePackString) = syncOrNull<Ok>(SetCustomLanguagePackString(languagePackId, newString))
fun TdAbsHandler.setCustomLanguagePackString(languagePackId: String, newString: LanguagePackString, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetCustomLanguagePackString(languagePackId, newString), 1, block)


suspend fun TdAbsHandler.setCustomLanguagePack(info: LanguagePackInfo, strings: Array<LanguagePackString>) = sync<Ok>(SetCustomLanguagePack(info, strings))
suspend fun TdAbsHandler.setCustomLanguagePackOrNull(info: LanguagePackInfo, strings: Array<LanguagePackString>) = syncOrNull<Ok>(SetCustomLanguagePack(info, strings))
fun TdAbsHandler.setCustomLanguagePack(info: LanguagePackInfo, strings: Array<LanguagePackString>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetCustomLanguagePack(info, strings), 1, block)


suspend fun TdAbsHandler.setChatTitle(chatId: Number, title: String) = sync<Ok>(SetChatTitle(chatId.toLong(), title))
suspend fun TdAbsHandler.setChatTitleOrNull(chatId: Number, title: String) = syncOrNull<Ok>(SetChatTitle(chatId.toLong(), title))
fun TdAbsHandler.setChatTitle(chatId: Number, title: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatTitle(chatId.toLong(), title), 1, block)


suspend fun TdAbsHandler.setChatSlowModeDelay(chatId: Number, slowModeDelay: Int) = sync<Ok>(SetChatSlowModeDelay(chatId.toLong(), slowModeDelay))
suspend fun TdAbsHandler.setChatSlowModeDelayOrNull(chatId: Number, slowModeDelay: Int) = syncOrNull<Ok>(SetChatSlowModeDelay(chatId.toLong(), slowModeDelay))
fun TdAbsHandler.setChatSlowModeDelay(chatId: Number, slowModeDelay: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatSlowModeDelay(chatId.toLong(), slowModeDelay), 1, block)


suspend fun TdAbsHandler.setChatPhoto(chatId: Number, photo: InputFile) = sync<Ok>(SetChatPhoto(chatId.toLong(), photo))
suspend fun TdAbsHandler.setChatPhotoOrNull(chatId: Number, photo: InputFile) = syncOrNull<Ok>(SetChatPhoto(chatId.toLong(), photo))
fun TdAbsHandler.setChatPhoto(chatId: Number, photo: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatPhoto(chatId.toLong(), photo), 1, block)


suspend fun TdAbsHandler.setChatPermissions(chatId: Number, permissions: ChatPermissions) = sync<Ok>(SetChatPermissions(chatId.toLong(), permissions))
suspend fun TdAbsHandler.setChatPermissionsOrNull(chatId: Number, permissions: ChatPermissions) = syncOrNull<Ok>(SetChatPermissions(chatId.toLong(), permissions))
fun TdAbsHandler.setChatPermissions(chatId: Number, permissions: ChatPermissions, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatPermissions(chatId.toLong(), permissions), 1, block)


suspend fun TdAbsHandler.setChatNotificationSettings(chatId: Number, notificationSettings: ChatNotificationSettings) = sync<Ok>(SetChatNotificationSettings(chatId.toLong(), notificationSettings))
suspend fun TdAbsHandler.setChatNotificationSettingsOrNull(chatId: Number, notificationSettings: ChatNotificationSettings) = syncOrNull<Ok>(SetChatNotificationSettings(chatId.toLong(), notificationSettings))
fun TdAbsHandler.setChatNotificationSettings(chatId: Number, notificationSettings: ChatNotificationSettings, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatNotificationSettings(chatId.toLong(), notificationSettings), 1, block)


suspend fun TdAbsHandler.setChatMemberStatus(chatId: Number, userId: Int, status: ChatMemberStatus) = sync<Ok>(SetChatMemberStatus(chatId.toLong(), userId, status))
suspend fun TdAbsHandler.setChatMemberStatusOrNull(chatId: Number, userId: Int, status: ChatMemberStatus) = syncOrNull<Ok>(SetChatMemberStatus(chatId.toLong(), userId, status))
fun TdAbsHandler.setChatMemberStatus(chatId: Number, userId: Int, status: ChatMemberStatus, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatMemberStatus(chatId.toLong(), userId, status), 1, block)


suspend fun TdAbsHandler.setChatLocation(chatId: Number, location: ChatLocation) = sync<Ok>(SetChatLocation(chatId.toLong(), location))
suspend fun TdAbsHandler.setChatLocationOrNull(chatId: Number, location: ChatLocation) = syncOrNull<Ok>(SetChatLocation(chatId.toLong(), location))
fun TdAbsHandler.setChatLocation(chatId: Number, location: ChatLocation, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatLocation(chatId.toLong(), location), 1, block)


suspend fun TdAbsHandler.setChatDraftMessage(chatId: Number, draftMessage: DraftMessage) = sync<Ok>(SetChatDraftMessage(chatId.toLong(), draftMessage))
suspend fun TdAbsHandler.setChatDraftMessageOrNull(chatId: Number, draftMessage: DraftMessage) = syncOrNull<Ok>(SetChatDraftMessage(chatId.toLong(), draftMessage))
fun TdAbsHandler.setChatDraftMessage(chatId: Number, draftMessage: DraftMessage, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatDraftMessage(chatId.toLong(), draftMessage), 1, block)


suspend fun TdAbsHandler.setChatDiscussionGroup(chatId: Number, discussionChatId: Long) = sync<Ok>(SetChatDiscussionGroup(chatId.toLong(), discussionChatId))
suspend fun TdAbsHandler.setChatDiscussionGroupOrNull(chatId: Number, discussionChatId: Long) = syncOrNull<Ok>(SetChatDiscussionGroup(chatId.toLong(), discussionChatId))
fun TdAbsHandler.setChatDiscussionGroup(chatId: Number, discussionChatId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatDiscussionGroup(chatId.toLong(), discussionChatId), 1, block)


suspend fun TdAbsHandler.setChatDescription(chatId: Number, description: String) = sync<Ok>(SetChatDescription(chatId.toLong(), description))
suspend fun TdAbsHandler.setChatDescriptionOrNull(chatId: Number, description: String) = syncOrNull<Ok>(SetChatDescription(chatId.toLong(), description))
fun TdAbsHandler.setChatDescription(chatId: Number, description: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatDescription(chatId.toLong(), description), 1, block)


suspend fun TdAbsHandler.setChatClientData(chatId: Number, clientData: String) = sync<Ok>(SetChatClientData(chatId.toLong(), clientData))
suspend fun TdAbsHandler.setChatClientDataOrNull(chatId: Number, clientData: String) = syncOrNull<Ok>(SetChatClientData(chatId.toLong(), clientData))
fun TdAbsHandler.setChatClientData(chatId: Number, clientData: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatClientData(chatId.toLong(), clientData), 1, block)


suspend fun TdAbsHandler.setChatChatList(chatId: Number, chatList: ChatList) = sync<Ok>(SetChatChatList(chatId.toLong(), chatList))
suspend fun TdAbsHandler.setChatChatListOrNull(chatId: Number, chatList: ChatList) = syncOrNull<Ok>(SetChatChatList(chatId.toLong(), chatList))
fun TdAbsHandler.setChatChatList(chatId: Number, chatList: ChatList, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetChatChatList(chatId.toLong(), chatList), 1, block)


suspend fun TdAbsHandler.setBotUpdatesStatus(pendingUpdateCount: Int, errorMessage: String) = sync<Ok>(SetBotUpdatesStatus(pendingUpdateCount, errorMessage))
suspend fun TdAbsHandler.setBotUpdatesStatusOrNull(pendingUpdateCount: Int, errorMessage: String) = syncOrNull<Ok>(SetBotUpdatesStatus(pendingUpdateCount, errorMessage))
fun TdAbsHandler.setBotUpdatesStatus(pendingUpdateCount: Int, errorMessage: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetBotUpdatesStatus(pendingUpdateCount, errorMessage), 1, block)


suspend infix fun TdAbsHandler.setBio(bio: String) = sync<Ok>(SetBio(bio))
suspend infix fun TdAbsHandler.setBioOrNull(bio: String) = syncOrNull<Ok>(SetBio(bio))
fun TdAbsHandler.setBio(bio: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetBio(bio), 1, block)


suspend fun TdAbsHandler.setBackground(background: InputBackground, type: BackgroundType, forDarkTheme: Boolean) = sync<Ok>(SetBackground(background, type, forDarkTheme))
suspend fun TdAbsHandler.setBackgroundOrNull(background: InputBackground, type: BackgroundType, forDarkTheme: Boolean) = syncOrNull<Ok>(SetBackground(background, type, forDarkTheme))
fun TdAbsHandler.setBackground(background: InputBackground, type: BackgroundType, forDarkTheme: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetBackground(background, type, forDarkTheme), 1, block)


suspend fun TdAbsHandler.setAutoDownloadSettings(settings: AutoDownloadSettings, type: NetworkType) = sync<Ok>(SetAutoDownloadSettings(settings, type))
suspend fun TdAbsHandler.setAutoDownloadSettingsOrNull(settings: AutoDownloadSettings, type: NetworkType) = syncOrNull<Ok>(SetAutoDownloadSettings(settings, type))
fun TdAbsHandler.setAutoDownloadSettings(settings: AutoDownloadSettings, type: NetworkType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetAutoDownloadSettings(settings, type), 1, block)


suspend fun TdAbsHandler.setAuthenticationPhoneNumber(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = sync<Ok>(SetAuthenticationPhoneNumber(phoneNumber, settings))
suspend fun TdAbsHandler.setAuthenticationPhoneNumberOrNull(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = syncOrNull<Ok>(SetAuthenticationPhoneNumber(phoneNumber, settings))
fun TdAbsHandler.setAuthenticationPhoneNumber(phoneNumber: String, settings: PhoneNumberAuthenticationSettings, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetAuthenticationPhoneNumber(phoneNumber, settings), 1, block)


suspend infix fun TdAbsHandler.setAlarm(seconds: Double) = sync<Ok>(SetAlarm(seconds))
suspend infix fun TdAbsHandler.setAlarmOrNull(seconds: Double) = syncOrNull<Ok>(SetAlarm(seconds))
fun TdAbsHandler.setAlarm(seconds: Double, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetAlarm(seconds), 1, block)


suspend infix fun TdAbsHandler.setAccountTtl(ttl: AccountTtl) = sync<Ok>(SetAccountTtl(ttl))
suspend infix fun TdAbsHandler.setAccountTtlOrNull(ttl: AccountTtl) = syncOrNull<Ok>(SetAccountTtl(ttl))
fun TdAbsHandler.setAccountTtl(ttl: AccountTtl, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetAccountTtl(ttl), 1, block)


suspend infix fun TdAbsHandler.sendTonLiteServerRequest(request: ByteArray) = sync<Ok>(SendTonLiteServerRequest(request))
suspend infix fun TdAbsHandler.sendTonLiteServerRequestOrNull(request: ByteArray) = syncOrNull<Ok>(SendTonLiteServerRequest(request))
fun TdAbsHandler.sendTonLiteServerRequest(request: ByteArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendTonLiteServerRequest(request), 1, block)


suspend fun TdAbsHandler.sendPhoneNumberVerificationCode(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = sync<Ok>(SendPhoneNumberVerificationCode(phoneNumber, settings))
suspend fun TdAbsHandler.sendPhoneNumberVerificationCodeOrNull(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = syncOrNull<Ok>(SendPhoneNumberVerificationCode(phoneNumber, settings))
fun TdAbsHandler.sendPhoneNumberVerificationCode(phoneNumber: String, settings: PhoneNumberAuthenticationSettings, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendPhoneNumberVerificationCode(phoneNumber, settings), 1, block)


suspend fun TdAbsHandler.sendPhoneNumberConfirmationCode(hash: String, phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = sync<Ok>(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings))
suspend fun TdAbsHandler.sendPhoneNumberConfirmationCodeOrNull(hash: String, phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = syncOrNull<Ok>(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings))
fun TdAbsHandler.sendPhoneNumberConfirmationCode(hash: String, phoneNumber: String, settings: PhoneNumberAuthenticationSettings, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings), 1, block)


suspend fun TdAbsHandler.sendPaymentForm(chatId: Number, messageId: Long, orderInfoId: String, shippingOptionId: String, credentials: InputCredentials) = sync<Ok>(SendPaymentForm(chatId.toLong(), messageId, orderInfoId, shippingOptionId, credentials))
suspend fun TdAbsHandler.sendPaymentFormOrNull(chatId: Number, messageId: Long, orderInfoId: String, shippingOptionId: String, credentials: InputCredentials) = syncOrNull<Ok>(SendPaymentForm(chatId.toLong(), messageId, orderInfoId, shippingOptionId, credentials))
fun TdAbsHandler.sendPaymentForm(chatId: Number, messageId: Long, orderInfoId: String, shippingOptionId: String, credentials: InputCredentials, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendPaymentForm(chatId.toLong(), messageId, orderInfoId, shippingOptionId, credentials), 1, block)


suspend fun TdAbsHandler.sendPassportAuthorizationForm(autorizationFormId: Int, types: Array<PassportElementType>) = sync<Ok>(SendPassportAuthorizationForm(autorizationFormId, types))
suspend fun TdAbsHandler.sendPassportAuthorizationFormOrNull(autorizationFormId: Int, types: Array<PassportElementType>) = syncOrNull<Ok>(SendPassportAuthorizationForm(autorizationFormId, types))
fun TdAbsHandler.sendPassportAuthorizationForm(autorizationFormId: Int, types: Array<PassportElementType>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendPassportAuthorizationForm(autorizationFormId, types), 1, block)


suspend fun TdAbsHandler.sendMessageAlbum(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, inputMessageContents: Array<InputMessageContent>) = sync<Ok>(SendMessageAlbum(chatId.toLong(), replyToMessageId, options, inputMessageContents))
suspend fun TdAbsHandler.sendMessageAlbumOrNull(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, inputMessageContents: Array<InputMessageContent>) = syncOrNull<Ok>(SendMessageAlbum(chatId.toLong(), replyToMessageId, options, inputMessageContents))
fun TdAbsHandler.sendMessageAlbum(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, inputMessageContents: Array<InputMessageContent>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendMessageAlbum(chatId.toLong(), replyToMessageId, options, inputMessageContents), 1, block)


suspend fun TdAbsHandler.sendMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<Ok>(SendMessage(chatId.toLong(), replyToMessageId, options, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.sendMessageOrNull(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<Ok>(SendMessage(chatId.toLong(), replyToMessageId, options, replyMarkup, inputMessageContent))
fun TdAbsHandler.sendMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendMessage(chatId.toLong(), replyToMessageId, options, replyMarkup, inputMessageContent), 1, block)


suspend fun TdAbsHandler.sendInlineQueryResultMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, queryId: Long, resultId: String, hideViaBot: Boolean) = sync<Ok>(SendInlineQueryResultMessage(chatId.toLong(), replyToMessageId, options, queryId, resultId, hideViaBot))
suspend fun TdAbsHandler.sendInlineQueryResultMessageOrNull(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, queryId: Long, resultId: String, hideViaBot: Boolean) = syncOrNull<Ok>(SendInlineQueryResultMessage(chatId.toLong(), replyToMessageId, options, queryId, resultId, hideViaBot))
fun TdAbsHandler.sendInlineQueryResultMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, queryId: Long, resultId: String, hideViaBot: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendInlineQueryResultMessage(chatId.toLong(), replyToMessageId, options, queryId, resultId, hideViaBot), 1, block)


suspend infix fun TdAbsHandler.sendEmailAddressVerificationCode(emailAddress: String) = sync<Ok>(SendEmailAddressVerificationCode(emailAddress))
suspend infix fun TdAbsHandler.sendEmailAddressVerificationCodeOrNull(emailAddress: String) = syncOrNull<Ok>(SendEmailAddressVerificationCode(emailAddress))
fun TdAbsHandler.sendEmailAddressVerificationCode(emailAddress: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendEmailAddressVerificationCode(emailAddress), 1, block)


suspend fun TdAbsHandler.sendCustomRequest(method: String, parameters: String) = sync<Ok>(SendCustomRequest(method, parameters))
suspend fun TdAbsHandler.sendCustomRequestOrNull(method: String, parameters: String) = syncOrNull<Ok>(SendCustomRequest(method, parameters))
fun TdAbsHandler.sendCustomRequest(method: String, parameters: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendCustomRequest(method, parameters), 1, block)


suspend fun TdAbsHandler.sendChatSetTtlMessage(chatId: Number, ttl: Int) = sync<Ok>(SendChatSetTtlMessage(chatId.toLong(), ttl))
suspend fun TdAbsHandler.sendChatSetTtlMessageOrNull(chatId: Number, ttl: Int) = syncOrNull<Ok>(SendChatSetTtlMessage(chatId.toLong(), ttl))
fun TdAbsHandler.sendChatSetTtlMessage(chatId: Number, ttl: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendChatSetTtlMessage(chatId.toLong(), ttl), 1, block)


suspend infix fun TdAbsHandler.sendChatScreenshotTakenNotification(chatId: Number) = sync<Ok>(SendChatScreenshotTakenNotification(chatId.toLong()))
suspend infix fun TdAbsHandler.sendChatScreenshotTakenNotificationOrNull(chatId: Number) = syncOrNull<Ok>(SendChatScreenshotTakenNotification(chatId.toLong()))
fun TdAbsHandler.sendChatScreenshotTakenNotification(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendChatScreenshotTakenNotification(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.sendChatAction(chatId: Number, action: ChatAction) = sync<Ok>(SendChatAction(chatId.toLong(), action))
suspend fun TdAbsHandler.sendChatActionOrNull(chatId: Number, action: ChatAction) = syncOrNull<Ok>(SendChatAction(chatId.toLong(), action))
fun TdAbsHandler.sendChatAction(chatId: Number, action: ChatAction, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendChatAction(chatId.toLong(), action), 1, block)


suspend fun TdAbsHandler.sendCallRating(callId: Int, rating: Int, comment: String, problems: Array<CallProblem>) = sync<Ok>(SendCallRating(callId, rating, comment, problems))
suspend fun TdAbsHandler.sendCallRatingOrNull(callId: Int, rating: Int, comment: String, problems: Array<CallProblem>) = syncOrNull<Ok>(SendCallRating(callId, rating, comment, problems))
fun TdAbsHandler.sendCallRating(callId: Int, rating: Int, comment: String, problems: Array<CallProblem>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendCallRating(callId, rating, comment, problems), 1, block)


suspend fun TdAbsHandler.sendCallDebugInformation(callId: Int, debugInformation: String) = sync<Ok>(SendCallDebugInformation(callId, debugInformation))
suspend fun TdAbsHandler.sendCallDebugInformationOrNull(callId: Int, debugInformation: String) = syncOrNull<Ok>(SendCallDebugInformation(callId, debugInformation))
fun TdAbsHandler.sendCallDebugInformation(callId: Int, debugInformation: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendCallDebugInformation(callId, debugInformation), 1, block)


suspend fun TdAbsHandler.sendBotStartMessage(botUserId: Int, chatId: Number, parameter: String) = sync<Ok>(SendBotStartMessage(botUserId, chatId.toLong(), parameter))
suspend fun TdAbsHandler.sendBotStartMessageOrNull(botUserId: Int, chatId: Number, parameter: String) = syncOrNull<Ok>(SendBotStartMessage(botUserId, chatId.toLong(), parameter))
fun TdAbsHandler.sendBotStartMessage(botUserId: Int, chatId: Number, parameter: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendBotStartMessage(botUserId, chatId.toLong(), parameter), 1, block)


suspend fun TdAbsHandler.searchStickers(emoji: String, limit: Int) = sync<Ok>(SearchStickers(emoji, limit))
suspend fun TdAbsHandler.searchStickersOrNull(emoji: String, limit: Int) = syncOrNull<Ok>(SearchStickers(emoji, limit))
fun TdAbsHandler.searchStickers(emoji: String, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchStickers(emoji, limit), 1, block)


suspend infix fun TdAbsHandler.searchStickerSets(query: String) = sync<Ok>(SearchStickerSets(query))
suspend infix fun TdAbsHandler.searchStickerSetsOrNull(query: String) = syncOrNull<Ok>(SearchStickerSets(query))
fun TdAbsHandler.searchStickerSets(query: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchStickerSets(query), 1, block)


suspend infix fun TdAbsHandler.searchStickerSet(name: String) = sync<Ok>(SearchStickerSet(name))
suspend infix fun TdAbsHandler.searchStickerSetOrNull(name: String) = syncOrNull<Ok>(SearchStickerSet(name))
fun TdAbsHandler.searchStickerSet(name: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchStickerSet(name), 1, block)


suspend fun TdAbsHandler.searchSecretMessages(chatId: Number, query: String, fromSearchId: Long, limit: Int, filter: SearchMessagesFilter) = sync<Ok>(SearchSecretMessages(chatId.toLong(), query, fromSearchId, limit, filter))
suspend fun TdAbsHandler.searchSecretMessagesOrNull(chatId: Number, query: String, fromSearchId: Long, limit: Int, filter: SearchMessagesFilter) = syncOrNull<Ok>(SearchSecretMessages(chatId.toLong(), query, fromSearchId, limit, filter))
fun TdAbsHandler.searchSecretMessages(chatId: Number, query: String, fromSearchId: Long, limit: Int, filter: SearchMessagesFilter, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchSecretMessages(chatId.toLong(), query, fromSearchId, limit, filter), 1, block)


suspend infix fun TdAbsHandler.searchPublicChats(query: String) = sync<Ok>(SearchPublicChats(query))
suspend infix fun TdAbsHandler.searchPublicChatsOrNull(query: String) = syncOrNull<Ok>(SearchPublicChats(query))
fun TdAbsHandler.searchPublicChats(query: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchPublicChats(query), 1, block)


suspend infix fun TdAbsHandler.searchPublicChat(username: String) = sync<Ok>(SearchPublicChat(username))
suspend infix fun TdAbsHandler.searchPublicChatOrNull(username: String) = syncOrNull<Ok>(SearchPublicChat(username))
fun TdAbsHandler.searchPublicChat(username: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchPublicChat(username), 1, block)


suspend fun TdAbsHandler.searchMessages(chatList: ChatList, query: String, offsetDate: Int, offsetChatId: Long, offsetMessageId: Long, limit: Int) = sync<Ok>(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit))
suspend fun TdAbsHandler.searchMessagesOrNull(chatList: ChatList, query: String, offsetDate: Int, offsetChatId: Long, offsetMessageId: Long, limit: Int) = syncOrNull<Ok>(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit))
fun TdAbsHandler.searchMessages(chatList: ChatList, query: String, offsetDate: Int, offsetChatId: Long, offsetMessageId: Long, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit), 1, block)


suspend fun TdAbsHandler.searchInstalledStickerSets(isMasks: Boolean, query: String, limit: Int) = sync<Ok>(SearchInstalledStickerSets(isMasks, query, limit))
suspend fun TdAbsHandler.searchInstalledStickerSetsOrNull(isMasks: Boolean, query: String, limit: Int) = syncOrNull<Ok>(SearchInstalledStickerSets(isMasks, query, limit))
fun TdAbsHandler.searchInstalledStickerSets(isMasks: Boolean, query: String, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchInstalledStickerSets(isMasks, query, limit), 1, block)


suspend fun TdAbsHandler.searchHashtags(prefix: String, limit: Int) = sync<Ok>(SearchHashtags(prefix, limit))
suspend fun TdAbsHandler.searchHashtagsOrNull(prefix: String, limit: Int) = syncOrNull<Ok>(SearchHashtags(prefix, limit))
fun TdAbsHandler.searchHashtags(prefix: String, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchHashtags(prefix, limit), 1, block)


suspend fun TdAbsHandler.searchEmojis(text: String, exactMatch: Boolean) = sync<Ok>(SearchEmojis(text, exactMatch))
suspend fun TdAbsHandler.searchEmojisOrNull(text: String, exactMatch: Boolean) = syncOrNull<Ok>(SearchEmojis(text, exactMatch))
fun TdAbsHandler.searchEmojis(text: String, exactMatch: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchEmojis(text, exactMatch), 1, block)


suspend fun TdAbsHandler.searchContacts(query: String, limit: Int) = sync<Ok>(SearchContacts(query, limit))
suspend fun TdAbsHandler.searchContactsOrNull(query: String, limit: Int) = syncOrNull<Ok>(SearchContacts(query, limit))
fun TdAbsHandler.searchContacts(query: String, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchContacts(query, limit), 1, block)


suspend fun TdAbsHandler.searchChatsOnServer(query: String, limit: Int) = sync<Ok>(SearchChatsOnServer(query, limit))
suspend fun TdAbsHandler.searchChatsOnServerOrNull(query: String, limit: Int) = syncOrNull<Ok>(SearchChatsOnServer(query, limit))
fun TdAbsHandler.searchChatsOnServer(query: String, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchChatsOnServer(query, limit), 1, block)


suspend infix fun TdAbsHandler.searchChatsNearby(location: Location) = sync<Ok>(SearchChatsNearby(location))
suspend infix fun TdAbsHandler.searchChatsNearbyOrNull(location: Location) = syncOrNull<Ok>(SearchChatsNearby(location))
fun TdAbsHandler.searchChatsNearby(location: Location, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchChatsNearby(location), 1, block)


suspend fun TdAbsHandler.searchChats(query: String, limit: Int) = sync<Ok>(SearchChats(query, limit))
suspend fun TdAbsHandler.searchChatsOrNull(query: String, limit: Int) = syncOrNull<Ok>(SearchChats(query, limit))
fun TdAbsHandler.searchChats(query: String, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchChats(query, limit), 1, block)


suspend fun TdAbsHandler.searchChatRecentLocationMessages(chatId: Number, limit: Int) = sync<Ok>(SearchChatRecentLocationMessages(chatId.toLong(), limit))
suspend fun TdAbsHandler.searchChatRecentLocationMessagesOrNull(chatId: Number, limit: Int) = syncOrNull<Ok>(SearchChatRecentLocationMessages(chatId.toLong(), limit))
fun TdAbsHandler.searchChatRecentLocationMessages(chatId: Number, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchChatRecentLocationMessages(chatId.toLong(), limit), 1, block)


suspend fun TdAbsHandler.searchChatMessages(chatId: Number, query: String, senderUserId: Int, fromMessageId: Long, offset: Int, limit: Int, filter: SearchMessagesFilter) = sync<Ok>(SearchChatMessages(chatId.toLong(), query, senderUserId, fromMessageId, offset, limit, filter))
suspend fun TdAbsHandler.searchChatMessagesOrNull(chatId: Number, query: String, senderUserId: Int, fromMessageId: Long, offset: Int, limit: Int, filter: SearchMessagesFilter) = syncOrNull<Ok>(SearchChatMessages(chatId.toLong(), query, senderUserId, fromMessageId, offset, limit, filter))
fun TdAbsHandler.searchChatMessages(chatId: Number, query: String, senderUserId: Int, fromMessageId: Long, offset: Int, limit: Int, filter: SearchMessagesFilter, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchChatMessages(chatId.toLong(), query, senderUserId, fromMessageId, offset, limit, filter), 1, block)


suspend fun TdAbsHandler.searchChatMembers(chatId: Number, query: String, limit: Int, filter: ChatMembersFilter) = sync<Ok>(SearchChatMembers(chatId.toLong(), query, limit, filter))
suspend fun TdAbsHandler.searchChatMembersOrNull(chatId: Number, query: String, limit: Int, filter: ChatMembersFilter) = syncOrNull<Ok>(SearchChatMembers(chatId.toLong(), query, limit, filter))
fun TdAbsHandler.searchChatMembers(chatId: Number, query: String, limit: Int, filter: ChatMembersFilter, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchChatMembers(chatId.toLong(), query, limit, filter), 1, block)


suspend fun TdAbsHandler.searchCallMessages(fromMessageId: Long, limit: Int, onlyMissed: Boolean) = sync<Ok>(SearchCallMessages(fromMessageId, limit, onlyMissed))
suspend fun TdAbsHandler.searchCallMessagesOrNull(fromMessageId: Long, limit: Int, onlyMissed: Boolean) = syncOrNull<Ok>(SearchCallMessages(fromMessageId, limit, onlyMissed))
fun TdAbsHandler.searchCallMessages(fromMessageId: Long, limit: Int, onlyMissed: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchCallMessages(fromMessageId, limit, onlyMissed), 1, block)


suspend infix fun TdAbsHandler.searchBackground(name: String) = sync<Ok>(SearchBackground(name))
suspend infix fun TdAbsHandler.searchBackgroundOrNull(name: String) = syncOrNull<Ok>(SearchBackground(name))
fun TdAbsHandler.searchBackground(name: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SearchBackground(name), 1, block)


suspend fun TdAbsHandler.saveApplicationLogEvent(type: String, chatId: Number, data: JsonValue) = sync<Ok>(SaveApplicationLogEvent(type, chatId.toLong(), data))
suspend fun TdAbsHandler.saveApplicationLogEventOrNull(type: String, chatId: Number, data: JsonValue) = syncOrNull<Ok>(SaveApplicationLogEvent(type, chatId.toLong(), data))
fun TdAbsHandler.saveApplicationLogEvent(type: String, chatId: Number, data: JsonValue, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SaveApplicationLogEvent(type, chatId.toLong(), data), 1, block)


suspend fun TdAbsHandler.resetNetworkStatistics() = sync<Ok>(ResetNetworkStatistics())
suspend fun TdAbsHandler.resetNetworkStatisticsOrNull() = syncOrNull<Ok>(ResetNetworkStatistics())
fun TdAbsHandler.resetNetworkStatistics(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResetNetworkStatistics(), 1, block)


suspend fun TdAbsHandler.resetBackgrounds() = sync<Ok>(ResetBackgrounds())
suspend fun TdAbsHandler.resetBackgroundsOrNull() = syncOrNull<Ok>(ResetBackgrounds())
fun TdAbsHandler.resetBackgrounds(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResetBackgrounds(), 1, block)


suspend fun TdAbsHandler.resetAllNotificationSettings() = sync<Ok>(ResetAllNotificationSettings())
suspend fun TdAbsHandler.resetAllNotificationSettingsOrNull() = syncOrNull<Ok>(ResetAllNotificationSettings())
fun TdAbsHandler.resetAllNotificationSettings(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResetAllNotificationSettings(), 1, block)


suspend fun TdAbsHandler.resendRecoveryEmailAddressCode() = sync<Ok>(ResendRecoveryEmailAddressCode())
suspend fun TdAbsHandler.resendRecoveryEmailAddressCodeOrNull() = syncOrNull<Ok>(ResendRecoveryEmailAddressCode())
fun TdAbsHandler.resendRecoveryEmailAddressCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendRecoveryEmailAddressCode(), 1, block)


suspend fun TdAbsHandler.resendPhoneNumberVerificationCode() = sync<Ok>(ResendPhoneNumberVerificationCode())
suspend fun TdAbsHandler.resendPhoneNumberVerificationCodeOrNull() = syncOrNull<Ok>(ResendPhoneNumberVerificationCode())
fun TdAbsHandler.resendPhoneNumberVerificationCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendPhoneNumberVerificationCode(), 1, block)


suspend fun TdAbsHandler.resendPhoneNumberConfirmationCode() = sync<Ok>(ResendPhoneNumberConfirmationCode())
suspend fun TdAbsHandler.resendPhoneNumberConfirmationCodeOrNull() = syncOrNull<Ok>(ResendPhoneNumberConfirmationCode())
fun TdAbsHandler.resendPhoneNumberConfirmationCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendPhoneNumberConfirmationCode(), 1, block)


suspend fun TdAbsHandler.resendMessages(chatId: Number, messageIds: LongArray) = sync<Ok>(ResendMessages(chatId.toLong(), messageIds))
suspend fun TdAbsHandler.resendMessagesOrNull(chatId: Number, messageIds: LongArray) = syncOrNull<Ok>(ResendMessages(chatId.toLong(), messageIds))
fun TdAbsHandler.resendMessages(chatId: Number, messageIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendMessages(chatId.toLong(), messageIds), 1, block)


suspend fun TdAbsHandler.resendEmailAddressVerificationCode() = sync<Ok>(ResendEmailAddressVerificationCode())
suspend fun TdAbsHandler.resendEmailAddressVerificationCodeOrNull() = syncOrNull<Ok>(ResendEmailAddressVerificationCode())
fun TdAbsHandler.resendEmailAddressVerificationCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendEmailAddressVerificationCode(), 1, block)


suspend fun TdAbsHandler.resendChangePhoneNumberCode() = sync<Ok>(ResendChangePhoneNumberCode())
suspend fun TdAbsHandler.resendChangePhoneNumberCodeOrNull() = syncOrNull<Ok>(ResendChangePhoneNumberCode())
fun TdAbsHandler.resendChangePhoneNumberCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendChangePhoneNumberCode(), 1, block)


suspend fun TdAbsHandler.resendAuthenticationCode() = sync<Ok>(ResendAuthenticationCode())
suspend fun TdAbsHandler.resendAuthenticationCodeOrNull() = syncOrNull<Ok>(ResendAuthenticationCode())
fun TdAbsHandler.resendAuthenticationCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendAuthenticationCode(), 1, block)


suspend infix fun TdAbsHandler.requestQrCodeAuthentication(otherUserIds: IntArray) = sync<Ok>(RequestQrCodeAuthentication(otherUserIds))
suspend infix fun TdAbsHandler.requestQrCodeAuthenticationOrNull(otherUserIds: IntArray) = syncOrNull<Ok>(RequestQrCodeAuthentication(otherUserIds))
fun TdAbsHandler.requestQrCodeAuthentication(otherUserIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RequestQrCodeAuthentication(otherUserIds), 1, block)


suspend fun TdAbsHandler.requestPasswordRecovery() = sync<Ok>(RequestPasswordRecovery())
suspend fun TdAbsHandler.requestPasswordRecoveryOrNull() = syncOrNull<Ok>(RequestPasswordRecovery())
fun TdAbsHandler.requestPasswordRecovery(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RequestPasswordRecovery(), 1, block)


suspend fun TdAbsHandler.requestAuthenticationPasswordRecovery() = sync<Ok>(RequestAuthenticationPasswordRecovery())
suspend fun TdAbsHandler.requestAuthenticationPasswordRecoveryOrNull() = syncOrNull<Ok>(RequestAuthenticationPasswordRecovery())
fun TdAbsHandler.requestAuthenticationPasswordRecovery(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RequestAuthenticationPasswordRecovery(), 1, block)


suspend fun TdAbsHandler.reportSupergroupSpam(supergroupId: Int, userId: Int, messageIds: LongArray) = sync<Ok>(ReportSupergroupSpam(supergroupId, userId, messageIds))
suspend fun TdAbsHandler.reportSupergroupSpamOrNull(supergroupId: Int, userId: Int, messageIds: LongArray) = syncOrNull<Ok>(ReportSupergroupSpam(supergroupId, userId, messageIds))
fun TdAbsHandler.reportSupergroupSpam(supergroupId: Int, userId: Int, messageIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ReportSupergroupSpam(supergroupId, userId, messageIds), 1, block)


suspend fun TdAbsHandler.reportChat(chatId: Number, reason: ChatReportReason, messageIds: LongArray) = sync<Ok>(ReportChat(chatId.toLong(), reason, messageIds))
suspend fun TdAbsHandler.reportChatOrNull(chatId: Number, reason: ChatReportReason, messageIds: LongArray) = syncOrNull<Ok>(ReportChat(chatId.toLong(), reason, messageIds))
fun TdAbsHandler.reportChat(chatId: Number, reason: ChatReportReason, messageIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ReportChat(chatId.toLong(), reason, messageIds), 1, block)


suspend fun TdAbsHandler.reorderInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray) = sync<Ok>(ReorderInstalledStickerSets(isMasks, stickerSetIds))
suspend fun TdAbsHandler.reorderInstalledStickerSetsOrNull(isMasks: Boolean, stickerSetIds: LongArray) = syncOrNull<Ok>(ReorderInstalledStickerSets(isMasks, stickerSetIds))
fun TdAbsHandler.reorderInstalledStickerSets(isMasks: Boolean, stickerSetIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ReorderInstalledStickerSets(isMasks, stickerSetIds), 1, block)


suspend fun TdAbsHandler.removeTopChat(category: TopChatCategory, chatId: Number) = sync<Ok>(RemoveTopChat(category, chatId.toLong()))
suspend fun TdAbsHandler.removeTopChatOrNull(category: TopChatCategory, chatId: Number) = syncOrNull<Ok>(RemoveTopChat(category, chatId.toLong()))
fun TdAbsHandler.removeTopChat(category: TopChatCategory, chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveTopChat(category, chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.removeStickerFromSet(sticker: InputFile) = sync<Ok>(RemoveStickerFromSet(sticker))
suspend infix fun TdAbsHandler.removeStickerFromSetOrNull(sticker: InputFile) = syncOrNull<Ok>(RemoveStickerFromSet(sticker))
fun TdAbsHandler.removeStickerFromSet(sticker: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveStickerFromSet(sticker), 1, block)


suspend infix fun TdAbsHandler.removeSavedAnimation(animation: InputFile) = sync<Ok>(RemoveSavedAnimation(animation))
suspend infix fun TdAbsHandler.removeSavedAnimationOrNull(animation: InputFile) = syncOrNull<Ok>(RemoveSavedAnimation(animation))
fun TdAbsHandler.removeSavedAnimation(animation: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveSavedAnimation(animation), 1, block)


suspend infix fun TdAbsHandler.removeRecentlyFoundChat(chatId: Number) = sync<Ok>(RemoveRecentlyFoundChat(chatId.toLong()))
suspend infix fun TdAbsHandler.removeRecentlyFoundChatOrNull(chatId: Number) = syncOrNull<Ok>(RemoveRecentlyFoundChat(chatId.toLong()))
fun TdAbsHandler.removeRecentlyFoundChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveRecentlyFoundChat(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.removeRecentSticker(isAttached: Boolean, sticker: InputFile) = sync<Ok>(RemoveRecentSticker(isAttached, sticker))
suspend fun TdAbsHandler.removeRecentStickerOrNull(isAttached: Boolean, sticker: InputFile) = syncOrNull<Ok>(RemoveRecentSticker(isAttached, sticker))
fun TdAbsHandler.removeRecentSticker(isAttached: Boolean, sticker: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveRecentSticker(isAttached, sticker), 1, block)


suspend infix fun TdAbsHandler.removeRecentHashtag(hashtag: String) = sync<Ok>(RemoveRecentHashtag(hashtag))
suspend infix fun TdAbsHandler.removeRecentHashtagOrNull(hashtag: String) = syncOrNull<Ok>(RemoveRecentHashtag(hashtag))
fun TdAbsHandler.removeRecentHashtag(hashtag: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveRecentHashtag(hashtag), 1, block)


suspend infix fun TdAbsHandler.removeProxy(proxyId: Int) = sync<Ok>(RemoveProxy(proxyId))
suspend infix fun TdAbsHandler.removeProxyOrNull(proxyId: Int) = syncOrNull<Ok>(RemoveProxy(proxyId))
fun TdAbsHandler.removeProxy(proxyId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveProxy(proxyId), 1, block)


suspend fun TdAbsHandler.removeNotificationGroup(notificationGroupId: Int, maxNotificationId: Int) = sync<Ok>(RemoveNotificationGroup(notificationGroupId, maxNotificationId))
suspend fun TdAbsHandler.removeNotificationGroupOrNull(notificationGroupId: Int, maxNotificationId: Int) = syncOrNull<Ok>(RemoveNotificationGroup(notificationGroupId, maxNotificationId))
fun TdAbsHandler.removeNotificationGroup(notificationGroupId: Int, maxNotificationId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveNotificationGroup(notificationGroupId, maxNotificationId), 1, block)


suspend fun TdAbsHandler.removeNotification(notificationGroupId: Int, notificationId: Int) = sync<Ok>(RemoveNotification(notificationGroupId, notificationId))
suspend fun TdAbsHandler.removeNotificationOrNull(notificationGroupId: Int, notificationId: Int) = syncOrNull<Ok>(RemoveNotification(notificationGroupId, notificationId))
fun TdAbsHandler.removeNotification(notificationGroupId: Int, notificationId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveNotification(notificationGroupId, notificationId), 1, block)


suspend infix fun TdAbsHandler.removeFavoriteSticker(sticker: InputFile) = sync<Ok>(RemoveFavoriteSticker(sticker))
suspend infix fun TdAbsHandler.removeFavoriteStickerOrNull(sticker: InputFile) = syncOrNull<Ok>(RemoveFavoriteSticker(sticker))
fun TdAbsHandler.removeFavoriteSticker(sticker: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveFavoriteSticker(sticker), 1, block)


suspend infix fun TdAbsHandler.removeContacts(userIds: IntArray) = sync<Ok>(RemoveContacts(userIds))
suspend infix fun TdAbsHandler.removeContactsOrNull(userIds: IntArray) = syncOrNull<Ok>(RemoveContacts(userIds))
fun TdAbsHandler.removeContacts(userIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveContacts(userIds), 1, block)


suspend infix fun TdAbsHandler.removeChatActionBar(chatId: Number) = sync<Ok>(RemoveChatActionBar(chatId.toLong()))
suspend infix fun TdAbsHandler.removeChatActionBarOrNull(chatId: Number) = syncOrNull<Ok>(RemoveChatActionBar(chatId.toLong()))
fun TdAbsHandler.removeChatActionBar(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveChatActionBar(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.removeBackground(backgroundId: Long) = sync<Ok>(RemoveBackground(backgroundId))
suspend infix fun TdAbsHandler.removeBackgroundOrNull(backgroundId: Long) = syncOrNull<Ok>(RemoveBackground(backgroundId))
fun TdAbsHandler.removeBackground(backgroundId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RemoveBackground(backgroundId), 1, block)


suspend fun TdAbsHandler.registerUser(firstName: String, lastName: String) = sync<Ok>(RegisterUser(firstName, lastName))
suspend fun TdAbsHandler.registerUserOrNull(firstName: String, lastName: String) = syncOrNull<Ok>(RegisterUser(firstName, lastName))
fun TdAbsHandler.registerUser(firstName: String, lastName: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RegisterUser(firstName, lastName), 1, block)


suspend fun TdAbsHandler.registerDevice(deviceToken: DeviceToken, otherUserIds: IntArray) = sync<Ok>(RegisterDevice(deviceToken, otherUserIds))
suspend fun TdAbsHandler.registerDeviceOrNull(deviceToken: DeviceToken, otherUserIds: IntArray) = syncOrNull<Ok>(RegisterDevice(deviceToken, otherUserIds))
fun TdAbsHandler.registerDevice(deviceToken: DeviceToken, otherUserIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RegisterDevice(deviceToken, otherUserIds), 1, block)


suspend infix fun TdAbsHandler.recoverPassword(recoveryCode: String) = sync<Ok>(RecoverPassword(recoveryCode))
suspend infix fun TdAbsHandler.recoverPasswordOrNull(recoveryCode: String) = syncOrNull<Ok>(RecoverPassword(recoveryCode))
fun TdAbsHandler.recoverPassword(recoveryCode: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RecoverPassword(recoveryCode), 1, block)


suspend infix fun TdAbsHandler.recoverAuthenticationPassword(recoveryCode: String) = sync<Ok>(RecoverAuthenticationPassword(recoveryCode))
suspend infix fun TdAbsHandler.recoverAuthenticationPasswordOrNull(recoveryCode: String) = syncOrNull<Ok>(RecoverAuthenticationPassword(recoveryCode))
fun TdAbsHandler.recoverAuthenticationPassword(recoveryCode: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RecoverAuthenticationPassword(recoveryCode), 1, block)


suspend fun TdAbsHandler.readFilePart(fileId: Int, offset: Int, count: Int) = sync<Ok>(ReadFilePart(fileId, offset, count))
suspend fun TdAbsHandler.readFilePartOrNull(fileId: Int, offset: Int, count: Int) = syncOrNull<Ok>(ReadFilePart(fileId, offset, count))
fun TdAbsHandler.readFilePart(fileId: Int, offset: Int, count: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ReadFilePart(fileId, offset, count), 1, block)


suspend infix fun TdAbsHandler.readAllChatMentions(chatId: Number) = sync<Ok>(ReadAllChatMentions(chatId.toLong()))
suspend infix fun TdAbsHandler.readAllChatMentionsOrNull(chatId: Number) = syncOrNull<Ok>(ReadAllChatMentions(chatId.toLong()))
fun TdAbsHandler.readAllChatMentions(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ReadAllChatMentions(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.processPushNotification(payload: String) = sync<Ok>(ProcessPushNotification(payload))
suspend infix fun TdAbsHandler.processPushNotificationOrNull(payload: String) = syncOrNull<Ok>(ProcessPushNotification(payload))
fun TdAbsHandler.processPushNotification(payload: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ProcessPushNotification(payload), 1, block)


suspend infix fun TdAbsHandler.pingProxy(proxyId: Int) = sync<Ok>(PingProxy(proxyId))
suspend infix fun TdAbsHandler.pingProxyOrNull(proxyId: Int) = syncOrNull<Ok>(PingProxy(proxyId))
fun TdAbsHandler.pingProxy(proxyId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(PingProxy(proxyId), 1, block)


suspend fun TdAbsHandler.pinChatMessage(chatId: Number, messageId: Long, disableNotification: Boolean) = sync<Ok>(PinChatMessage(chatId.toLong(), messageId, disableNotification))
suspend fun TdAbsHandler.pinChatMessageOrNull(chatId: Number, messageId: Long, disableNotification: Boolean) = syncOrNull<Ok>(PinChatMessage(chatId.toLong(), messageId, disableNotification))
fun TdAbsHandler.pinChatMessage(chatId: Number, messageId: Long, disableNotification: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(PinChatMessage(chatId.toLong(), messageId, disableNotification), 1, block)


suspend fun TdAbsHandler.parseTextEntities(text: String, parseMode: TextParseMode) = sync<Ok>(ParseTextEntities(text, parseMode))
suspend fun TdAbsHandler.parseTextEntitiesOrNull(text: String, parseMode: TextParseMode) = syncOrNull<Ok>(ParseTextEntities(text, parseMode))
fun TdAbsHandler.parseTextEntities(text: String, parseMode: TextParseMode, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ParseTextEntities(text, parseMode), 1, block)


suspend fun TdAbsHandler.optimizeStorage(size: Long, ttl: Int, count: Int, immunityDelay: Int, fileTypes: Array<FileType>, chatIds: LongArray, excludeChatIds: LongArray, chatLimit: Int) = sync<Ok>(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, chatLimit))
suspend fun TdAbsHandler.optimizeStorageOrNull(size: Long, ttl: Int, count: Int, immunityDelay: Int, fileTypes: Array<FileType>, chatIds: LongArray, excludeChatIds: LongArray, chatLimit: Int) = syncOrNull<Ok>(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, chatLimit))
fun TdAbsHandler.optimizeStorage(size: Long, ttl: Int, count: Int, immunityDelay: Int, fileTypes: Array<FileType>, chatIds: LongArray, excludeChatIds: LongArray, chatLimit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, chatLimit), 1, block)


suspend fun TdAbsHandler.openMessageContent(chatId: Number, messageId: Long) = sync<Ok>(OpenMessageContent(chatId.toLong(), messageId))
suspend fun TdAbsHandler.openMessageContentOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(OpenMessageContent(chatId.toLong(), messageId))
fun TdAbsHandler.openMessageContent(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(OpenMessageContent(chatId.toLong(), messageId), 1, block)


suspend infix fun TdAbsHandler.openChat(chatId: Number) = sync<Ok>(OpenChat(chatId.toLong()))
suspend infix fun TdAbsHandler.openChatOrNull(chatId: Number) = syncOrNull<Ok>(OpenChat(chatId.toLong()))
fun TdAbsHandler.openChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(OpenChat(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.logOut() = sync<Ok>(LogOut())
suspend fun TdAbsHandler.logOutOrNull() = syncOrNull<Ok>(LogOut())
fun TdAbsHandler.logOut(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(LogOut(), 1, block)


suspend infix fun TdAbsHandler.leaveChat(chatId: Number) = sync<Ok>(LeaveChat(chatId.toLong()))
suspend infix fun TdAbsHandler.leaveChatOrNull(chatId: Number) = syncOrNull<Ok>(LeaveChat(chatId.toLong()))
fun TdAbsHandler.leaveChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(LeaveChat(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.joinChatByInviteLink(inviteLink: String) = sync<Ok>(JoinChatByInviteLink(inviteLink))
suspend infix fun TdAbsHandler.joinChatByInviteLinkOrNull(inviteLink: String) = syncOrNull<Ok>(JoinChatByInviteLink(inviteLink))
fun TdAbsHandler.joinChatByInviteLink(inviteLink: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(JoinChatByInviteLink(inviteLink), 1, block)


suspend infix fun TdAbsHandler.joinChat(chatId: Number) = sync<Ok>(JoinChat(chatId.toLong()))
suspend infix fun TdAbsHandler.joinChatOrNull(chatId: Number) = syncOrNull<Ok>(JoinChat(chatId.toLong()))
fun TdAbsHandler.joinChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(JoinChat(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.importContacts(contacts: Array<Contact>) = sync<Ok>(ImportContacts(contacts))
suspend infix fun TdAbsHandler.importContactsOrNull(contacts: Array<Contact>) = syncOrNull<Ok>(ImportContacts(contacts))
fun TdAbsHandler.importContacts(contacts: Array<Contact>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ImportContacts(contacts), 1, block)


suspend infix fun TdAbsHandler.getWebPagePreview(text: FormattedText) = sync<Ok>(GetWebPagePreview(text))
suspend infix fun TdAbsHandler.getWebPagePreviewOrNull(text: FormattedText) = syncOrNull<Ok>(GetWebPagePreview(text))
fun TdAbsHandler.getWebPagePreview(text: FormattedText, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetWebPagePreview(text), 1, block)


suspend fun TdAbsHandler.getWebPageInstantView(url: String, forceFull: Boolean) = sync<Ok>(GetWebPageInstantView(url, forceFull))
suspend fun TdAbsHandler.getWebPageInstantViewOrNull(url: String, forceFull: Boolean) = syncOrNull<Ok>(GetWebPageInstantView(url, forceFull))
fun TdAbsHandler.getWebPageInstantView(url: String, forceFull: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetWebPageInstantView(url, forceFull), 1, block)


suspend fun TdAbsHandler.getUserProfilePhotos(userId: Int, offset: Int, limit: Int) = sync<Ok>(GetUserProfilePhotos(userId, offset, limit))
suspend fun TdAbsHandler.getUserProfilePhotosOrNull(userId: Int, offset: Int, limit: Int) = syncOrNull<Ok>(GetUserProfilePhotos(userId, offset, limit))
fun TdAbsHandler.getUserProfilePhotos(userId: Int, offset: Int, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetUserProfilePhotos(userId, offset, limit), 1, block)


suspend infix fun TdAbsHandler.getUserPrivacySettingRules(setting: UserPrivacySetting) = sync<Ok>(GetUserPrivacySettingRules(setting))
suspend infix fun TdAbsHandler.getUserPrivacySettingRulesOrNull(setting: UserPrivacySetting) = syncOrNull<Ok>(GetUserPrivacySettingRules(setting))
fun TdAbsHandler.getUserPrivacySettingRules(setting: UserPrivacySetting, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetUserPrivacySettingRules(setting), 1, block)


suspend infix fun TdAbsHandler.getUserFullInfo(userId: Int) = sync<Ok>(GetUserFullInfo(userId))
suspend infix fun TdAbsHandler.getUserFullInfoOrNull(userId: Int) = syncOrNull<Ok>(GetUserFullInfo(userId))
fun TdAbsHandler.getUserFullInfo(userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetUserFullInfo(userId), 1, block)


suspend infix fun TdAbsHandler.getUser(userId: Int) = sync<Ok>(GetUser(userId))
suspend infix fun TdAbsHandler.getUserOrNull(userId: Int) = syncOrNull<Ok>(GetUser(userId))
fun TdAbsHandler.getUser(userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetUser(userId), 1, block)


suspend fun TdAbsHandler.getTrendingStickerSets() = sync<Ok>(GetTrendingStickerSets())
suspend fun TdAbsHandler.getTrendingStickerSetsOrNull() = syncOrNull<Ok>(GetTrendingStickerSets())
fun TdAbsHandler.getTrendingStickerSets(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetTrendingStickerSets(), 1, block)


suspend fun TdAbsHandler.getTopChats(category: TopChatCategory, limit: Int) = sync<Ok>(GetTopChats(category, limit))
suspend fun TdAbsHandler.getTopChatsOrNull(category: TopChatCategory, limit: Int) = syncOrNull<Ok>(GetTopChats(category, limit))
fun TdAbsHandler.getTopChats(category: TopChatCategory, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetTopChats(category, limit), 1, block)


suspend fun TdAbsHandler.getTonWalletPasswordSalt() = sync<Ok>(GetTonWalletPasswordSalt())
suspend fun TdAbsHandler.getTonWalletPasswordSaltOrNull() = syncOrNull<Ok>(GetTonWalletPasswordSalt())
fun TdAbsHandler.getTonWalletPasswordSalt(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetTonWalletPasswordSalt(), 1, block)


suspend infix fun TdAbsHandler.getTextEntities(text: String) = sync<Ok>(GetTextEntities(text))
suspend infix fun TdAbsHandler.getTextEntitiesOrNull(text: String) = syncOrNull<Ok>(GetTextEntities(text))
fun TdAbsHandler.getTextEntities(text: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetTextEntities(text), 1, block)


suspend fun TdAbsHandler.getTemporaryPasswordState() = sync<Ok>(GetTemporaryPasswordState())
suspend fun TdAbsHandler.getTemporaryPasswordStateOrNull() = syncOrNull<Ok>(GetTemporaryPasswordState())
fun TdAbsHandler.getTemporaryPasswordState(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetTemporaryPasswordState(), 1, block)


suspend fun TdAbsHandler.getSupportUser() = sync<Ok>(GetSupportUser())
suspend fun TdAbsHandler.getSupportUserOrNull() = syncOrNull<Ok>(GetSupportUser())
fun TdAbsHandler.getSupportUser(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSupportUser(), 1, block)


suspend fun TdAbsHandler.getSupergroupMembers(supergroupId: Int, filter: SupergroupMembersFilter, offset: Int, limit: Int) = sync<Ok>(GetSupergroupMembers(supergroupId, filter, offset, limit))
suspend fun TdAbsHandler.getSupergroupMembersOrNull(supergroupId: Int, filter: SupergroupMembersFilter, offset: Int, limit: Int) = syncOrNull<Ok>(GetSupergroupMembers(supergroupId, filter, offset, limit))
fun TdAbsHandler.getSupergroupMembers(supergroupId: Int, filter: SupergroupMembersFilter, offset: Int, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSupergroupMembers(supergroupId, filter, offset, limit), 1, block)


suspend infix fun TdAbsHandler.getSupergroupFullInfo(supergroupId: Int) = sync<Ok>(GetSupergroupFullInfo(supergroupId))
suspend infix fun TdAbsHandler.getSupergroupFullInfoOrNull(supergroupId: Int) = syncOrNull<Ok>(GetSupergroupFullInfo(supergroupId))
fun TdAbsHandler.getSupergroupFullInfo(supergroupId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSupergroupFullInfo(supergroupId), 1, block)


suspend infix fun TdAbsHandler.getSupergroup(supergroupId: Int) = sync<Ok>(GetSupergroup(supergroupId))
suspend infix fun TdAbsHandler.getSupergroupOrNull(supergroupId: Int) = syncOrNull<Ok>(GetSupergroup(supergroupId))
fun TdAbsHandler.getSupergroup(supergroupId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSupergroup(supergroupId), 1, block)


suspend fun TdAbsHandler.getSuitableDiscussionChats() = sync<Ok>(GetSuitableDiscussionChats())
suspend fun TdAbsHandler.getSuitableDiscussionChatsOrNull() = syncOrNull<Ok>(GetSuitableDiscussionChats())
fun TdAbsHandler.getSuitableDiscussionChats(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSuitableDiscussionChats(), 1, block)


suspend fun TdAbsHandler.getStorageStatisticsFast() = sync<Ok>(GetStorageStatisticsFast())
suspend fun TdAbsHandler.getStorageStatisticsFastOrNull() = syncOrNull<Ok>(GetStorageStatisticsFast())
fun TdAbsHandler.getStorageStatisticsFast(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetStorageStatisticsFast(), 1, block)


suspend infix fun TdAbsHandler.getStorageStatistics(chatLimit: Int) = sync<Ok>(GetStorageStatistics(chatLimit))
suspend infix fun TdAbsHandler.getStorageStatisticsOrNull(chatLimit: Int) = syncOrNull<Ok>(GetStorageStatistics(chatLimit))
fun TdAbsHandler.getStorageStatistics(chatLimit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetStorageStatistics(chatLimit), 1, block)


suspend fun TdAbsHandler.getStickers(emoji: String, limit: Int) = sync<Ok>(GetStickers(emoji, limit))
suspend fun TdAbsHandler.getStickersOrNull(emoji: String, limit: Int) = syncOrNull<Ok>(GetStickers(emoji, limit))
fun TdAbsHandler.getStickers(emoji: String, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetStickers(emoji, limit), 1, block)


suspend infix fun TdAbsHandler.getStickerSet(setId: Long) = sync<Ok>(GetStickerSet(setId))
suspend infix fun TdAbsHandler.getStickerSetOrNull(setId: Long) = syncOrNull<Ok>(GetStickerSet(setId))
fun TdAbsHandler.getStickerSet(setId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetStickerSet(setId), 1, block)


suspend infix fun TdAbsHandler.getStickerEmojis(sticker: InputFile) = sync<Ok>(GetStickerEmojis(sticker))
suspend infix fun TdAbsHandler.getStickerEmojisOrNull(sticker: InputFile) = syncOrNull<Ok>(GetStickerEmojis(sticker))
fun TdAbsHandler.getStickerEmojis(sticker: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetStickerEmojis(sticker), 1, block)


suspend infix fun TdAbsHandler.getSecretChat(secretChatId: Int) = sync<Ok>(GetSecretChat(secretChatId))
suspend infix fun TdAbsHandler.getSecretChatOrNull(secretChatId: Int) = syncOrNull<Ok>(GetSecretChat(secretChatId))
fun TdAbsHandler.getSecretChat(secretChatId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSecretChat(secretChatId), 1, block)


suspend infix fun TdAbsHandler.getScopeNotificationSettings(scope: NotificationSettingsScope) = sync<Ok>(GetScopeNotificationSettings(scope))
suspend infix fun TdAbsHandler.getScopeNotificationSettingsOrNull(scope: NotificationSettingsScope) = syncOrNull<Ok>(GetScopeNotificationSettings(scope))
fun TdAbsHandler.getScopeNotificationSettings(scope: NotificationSettingsScope, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetScopeNotificationSettings(scope), 1, block)


suspend fun TdAbsHandler.getSavedOrderInfo() = sync<Ok>(GetSavedOrderInfo())
suspend fun TdAbsHandler.getSavedOrderInfoOrNull() = syncOrNull<Ok>(GetSavedOrderInfo())
fun TdAbsHandler.getSavedOrderInfo(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSavedOrderInfo(), 1, block)


suspend fun TdAbsHandler.getSavedAnimations() = sync<Ok>(GetSavedAnimations())
suspend fun TdAbsHandler.getSavedAnimationsOrNull() = syncOrNull<Ok>(GetSavedAnimations())
fun TdAbsHandler.getSavedAnimations(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetSavedAnimations(), 1, block)


suspend fun TdAbsHandler.getRepliedMessage(chatId: Number, messageId: Long) = sync<Ok>(GetRepliedMessage(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getRepliedMessageOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(GetRepliedMessage(chatId.toLong(), messageId))
fun TdAbsHandler.getRepliedMessage(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetRepliedMessage(chatId.toLong(), messageId), 1, block)


suspend fun TdAbsHandler.getRemoteFile(remoteFileId: String, fileType: FileType) = sync<Ok>(GetRemoteFile(remoteFileId, fileType))
suspend fun TdAbsHandler.getRemoteFileOrNull(remoteFileId: String, fileType: FileType) = syncOrNull<Ok>(GetRemoteFile(remoteFileId, fileType))
fun TdAbsHandler.getRemoteFile(remoteFileId: String, fileType: FileType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetRemoteFile(remoteFileId, fileType), 1, block)


suspend infix fun TdAbsHandler.getRecoveryEmailAddress(password: String) = sync<Ok>(GetRecoveryEmailAddress(password))
suspend infix fun TdAbsHandler.getRecoveryEmailAddressOrNull(password: String) = syncOrNull<Ok>(GetRecoveryEmailAddress(password))
fun TdAbsHandler.getRecoveryEmailAddress(password: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetRecoveryEmailAddress(password), 1, block)


suspend infix fun TdAbsHandler.getRecentlyVisitedTMeUrls(referrer: String) = sync<Ok>(GetRecentlyVisitedTMeUrls(referrer))
suspend infix fun TdAbsHandler.getRecentlyVisitedTMeUrlsOrNull(referrer: String) = syncOrNull<Ok>(GetRecentlyVisitedTMeUrls(referrer))
fun TdAbsHandler.getRecentlyVisitedTMeUrls(referrer: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetRecentlyVisitedTMeUrls(referrer), 1, block)


suspend infix fun TdAbsHandler.getRecentStickers(isAttached: Boolean) = sync<Ok>(GetRecentStickers(isAttached))
suspend infix fun TdAbsHandler.getRecentStickersOrNull(isAttached: Boolean) = syncOrNull<Ok>(GetRecentStickers(isAttached))
fun TdAbsHandler.getRecentStickers(isAttached: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetRecentStickers(isAttached), 1, block)


suspend fun TdAbsHandler.getRecentInlineBots() = sync<Ok>(GetRecentInlineBots())
suspend fun TdAbsHandler.getRecentInlineBotsOrNull() = syncOrNull<Ok>(GetRecentInlineBots())
fun TdAbsHandler.getRecentInlineBots(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetRecentInlineBots(), 1, block)


suspend infix fun TdAbsHandler.getPushReceiverId(payload: String) = sync<Ok>(GetPushReceiverId(payload))
suspend infix fun TdAbsHandler.getPushReceiverIdOrNull(payload: String) = syncOrNull<Ok>(GetPushReceiverId(payload))
fun TdAbsHandler.getPushReceiverId(payload: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPushReceiverId(payload), 1, block)


suspend fun TdAbsHandler.getPublicMessageLink(chatId: Number, messageId: Long, forAlbum: Boolean) = sync<Ok>(GetPublicMessageLink(chatId.toLong(), messageId, forAlbum))
suspend fun TdAbsHandler.getPublicMessageLinkOrNull(chatId: Number, messageId: Long, forAlbum: Boolean) = syncOrNull<Ok>(GetPublicMessageLink(chatId.toLong(), messageId, forAlbum))
fun TdAbsHandler.getPublicMessageLink(chatId: Number, messageId: Long, forAlbum: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPublicMessageLink(chatId.toLong(), messageId, forAlbum), 1, block)


suspend infix fun TdAbsHandler.getProxyLink(proxyId: Int) = sync<Ok>(GetProxyLink(proxyId))
suspend infix fun TdAbsHandler.getProxyLinkOrNull(proxyId: Int) = syncOrNull<Ok>(GetProxyLink(proxyId))
fun TdAbsHandler.getProxyLink(proxyId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetProxyLink(proxyId), 1, block)


suspend fun TdAbsHandler.getProxies() = sync<Ok>(GetProxies())
suspend fun TdAbsHandler.getProxiesOrNull() = syncOrNull<Ok>(GetProxies())
fun TdAbsHandler.getProxies(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetProxies(), 1, block)


suspend infix fun TdAbsHandler.getPreferredCountryLanguage(countryCode: String) = sync<Ok>(GetPreferredCountryLanguage(countryCode))
suspend infix fun TdAbsHandler.getPreferredCountryLanguageOrNull(countryCode: String) = syncOrNull<Ok>(GetPreferredCountryLanguage(countryCode))
fun TdAbsHandler.getPreferredCountryLanguage(countryCode: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPreferredCountryLanguage(countryCode), 1, block)


suspend fun TdAbsHandler.getPaymentReceipt(chatId: Number, messageId: Long) = sync<Ok>(GetPaymentReceipt(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getPaymentReceiptOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(GetPaymentReceipt(chatId.toLong(), messageId))
fun TdAbsHandler.getPaymentReceipt(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPaymentReceipt(chatId.toLong(), messageId), 1, block)


suspend fun TdAbsHandler.getPaymentForm(chatId: Number, messageId: Long) = sync<Ok>(GetPaymentForm(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getPaymentFormOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(GetPaymentForm(chatId.toLong(), messageId))
fun TdAbsHandler.getPaymentForm(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPaymentForm(chatId.toLong(), messageId), 1, block)


suspend fun TdAbsHandler.getPasswordState() = sync<Ok>(GetPasswordState())
suspend fun TdAbsHandler.getPasswordStateOrNull() = syncOrNull<Ok>(GetPasswordState())
fun TdAbsHandler.getPasswordState(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPasswordState(), 1, block)


suspend fun TdAbsHandler.getPassportElement(type: PassportElementType, password: String) = sync<Ok>(GetPassportElement(type, password))
suspend fun TdAbsHandler.getPassportElementOrNull(type: PassportElementType, password: String) = syncOrNull<Ok>(GetPassportElement(type, password))
fun TdAbsHandler.getPassportElement(type: PassportElementType, password: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPassportElement(type, password), 1, block)


suspend fun TdAbsHandler.getPassportAuthorizationFormAvailableElements(autorizationFormId: Int, password: String) = sync<Ok>(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password))
suspend fun TdAbsHandler.getPassportAuthorizationFormAvailableElementsOrNull(autorizationFormId: Int, password: String) = syncOrNull<Ok>(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password))
fun TdAbsHandler.getPassportAuthorizationFormAvailableElements(autorizationFormId: Int, password: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password), 1, block)


suspend fun TdAbsHandler.getPassportAuthorizationForm(botUserId: Int, scope: String, publicKey: String, nonce: String) = sync<Ok>(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce))
suspend fun TdAbsHandler.getPassportAuthorizationFormOrNull(botUserId: Int, scope: String, publicKey: String, nonce: String) = syncOrNull<Ok>(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce))
fun TdAbsHandler.getPassportAuthorizationForm(botUserId: Int, scope: String, publicKey: String, nonce: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce), 1, block)


suspend infix fun TdAbsHandler.getOption(name: String) = sync<Ok>(GetOption(name))
suspend infix fun TdAbsHandler.getOptionOrNull(name: String) = syncOrNull<Ok>(GetOption(name))
fun TdAbsHandler.getOption(name: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetOption(name), 1, block)


suspend infix fun TdAbsHandler.getNetworkStatistics(onlyCurrent: Boolean) = sync<Ok>(GetNetworkStatistics(onlyCurrent))
suspend infix fun TdAbsHandler.getNetworkStatisticsOrNull(onlyCurrent: Boolean) = syncOrNull<Ok>(GetNetworkStatistics(onlyCurrent))
fun TdAbsHandler.getNetworkStatistics(onlyCurrent: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetNetworkStatistics(onlyCurrent), 1, block)


suspend fun TdAbsHandler.getMessages(chatId: Number, messageIds: LongArray) = sync<Ok>(GetMessages(chatId.toLong(), messageIds))
suspend fun TdAbsHandler.getMessagesOrNull(chatId: Number, messageIds: LongArray) = syncOrNull<Ok>(GetMessages(chatId.toLong(), messageIds))
fun TdAbsHandler.getMessages(chatId: Number, messageIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetMessages(chatId.toLong(), messageIds), 1, block)


suspend fun TdAbsHandler.getMessageLocally(chatId: Number, messageId: Long) = sync<Ok>(GetMessageLocally(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getMessageLocallyOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(GetMessageLocally(chatId.toLong(), messageId))
fun TdAbsHandler.getMessageLocally(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetMessageLocally(chatId.toLong(), messageId), 1, block)


suspend infix fun TdAbsHandler.getMessageLinkInfo(url: String) = sync<Ok>(GetMessageLinkInfo(url))
suspend infix fun TdAbsHandler.getMessageLinkInfoOrNull(url: String) = syncOrNull<Ok>(GetMessageLinkInfo(url))
fun TdAbsHandler.getMessageLinkInfo(url: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetMessageLinkInfo(url), 1, block)


suspend fun TdAbsHandler.getMessageLink(chatId: Number, messageId: Long) = sync<Ok>(GetMessageLink(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getMessageLinkOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(GetMessageLink(chatId.toLong(), messageId))
fun TdAbsHandler.getMessageLink(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetMessageLink(chatId.toLong(), messageId), 1, block)


suspend fun TdAbsHandler.getMessage(chatId: Number, messageId: Long) = sync<Ok>(GetMessage(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getMessageOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(GetMessage(chatId.toLong(), messageId))
fun TdAbsHandler.getMessage(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetMessage(chatId.toLong(), messageId), 1, block)


suspend fun TdAbsHandler.getMe() = sync<Ok>(GetMe())
suspend fun TdAbsHandler.getMeOrNull() = syncOrNull<Ok>(GetMe())
fun TdAbsHandler.getMe(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetMe(), 1, block)


suspend fun TdAbsHandler.getMapThumbnailFile(location: Location, zoom: Int, width: Int, height: Int, scale: Int, chatId: Number) = sync<Ok>(GetMapThumbnailFile(location, zoom, width, height, scale, chatId.toLong()))
suspend fun TdAbsHandler.getMapThumbnailFileOrNull(location: Location, zoom: Int, width: Int, height: Int, scale: Int, chatId: Number) = syncOrNull<Ok>(GetMapThumbnailFile(location, zoom, width, height, scale, chatId.toLong()))
fun TdAbsHandler.getMapThumbnailFile(location: Location, zoom: Int, width: Int, height: Int, scale: Int, chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetMapThumbnailFile(location, zoom, width, height, scale, chatId.toLong()), 1, block)


suspend fun TdAbsHandler.getLoginUrlInfo(chatId: Number, messageId: Long, buttonId: Int) = sync<Ok>(GetLoginUrlInfo(chatId.toLong(), messageId, buttonId))
suspend fun TdAbsHandler.getLoginUrlInfoOrNull(chatId: Number, messageId: Long, buttonId: Int) = syncOrNull<Ok>(GetLoginUrlInfo(chatId.toLong(), messageId, buttonId))
fun TdAbsHandler.getLoginUrlInfo(chatId: Number, messageId: Long, buttonId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLoginUrlInfo(chatId.toLong(), messageId, buttonId), 1, block)


suspend fun TdAbsHandler.getLoginUrl(chatId: Number, messageId: Long, buttonId: Int, allowWriteAccess: Boolean) = sync<Ok>(GetLoginUrl(chatId.toLong(), messageId, buttonId, allowWriteAccess))
suspend fun TdAbsHandler.getLoginUrlOrNull(chatId: Number, messageId: Long, buttonId: Int, allowWriteAccess: Boolean) = syncOrNull<Ok>(GetLoginUrl(chatId.toLong(), messageId, buttonId, allowWriteAccess))
fun TdAbsHandler.getLoginUrl(chatId: Number, messageId: Long, buttonId: Int, allowWriteAccess: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLoginUrl(chatId.toLong(), messageId, buttonId, allowWriteAccess), 1, block)


suspend fun TdAbsHandler.getLogVerbosityLevel() = sync<Ok>(GetLogVerbosityLevel())
suspend fun TdAbsHandler.getLogVerbosityLevelOrNull() = syncOrNull<Ok>(GetLogVerbosityLevel())
fun TdAbsHandler.getLogVerbosityLevel(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLogVerbosityLevel(), 1, block)


suspend fun TdAbsHandler.getLogTags() = sync<Ok>(GetLogTags())
suspend fun TdAbsHandler.getLogTagsOrNull() = syncOrNull<Ok>(GetLogTags())
fun TdAbsHandler.getLogTags(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLogTags(), 1, block)


suspend infix fun TdAbsHandler.getLogTagVerbosityLevel(tag: String) = sync<Ok>(GetLogTagVerbosityLevel(tag))
suspend infix fun TdAbsHandler.getLogTagVerbosityLevelOrNull(tag: String) = syncOrNull<Ok>(GetLogTagVerbosityLevel(tag))
fun TdAbsHandler.getLogTagVerbosityLevel(tag: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLogTagVerbosityLevel(tag), 1, block)


suspend fun TdAbsHandler.getLogStream() = sync<Ok>(GetLogStream())
suspend fun TdAbsHandler.getLogStreamOrNull() = syncOrNull<Ok>(GetLogStream())
fun TdAbsHandler.getLogStream(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLogStream(), 1, block)


suspend infix fun TdAbsHandler.getLocalizationTargetInfo(onlyLocal: Boolean) = sync<Ok>(GetLocalizationTargetInfo(onlyLocal))
suspend infix fun TdAbsHandler.getLocalizationTargetInfoOrNull(onlyLocal: Boolean) = syncOrNull<Ok>(GetLocalizationTargetInfo(onlyLocal))
fun TdAbsHandler.getLocalizationTargetInfo(onlyLocal: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLocalizationTargetInfo(onlyLocal), 1, block)


suspend fun TdAbsHandler.getLanguagePackStrings(languagePackId: String, keys: Array<String>) = sync<Ok>(GetLanguagePackStrings(languagePackId, keys))
suspend fun TdAbsHandler.getLanguagePackStringsOrNull(languagePackId: String, keys: Array<String>) = syncOrNull<Ok>(GetLanguagePackStrings(languagePackId, keys))
fun TdAbsHandler.getLanguagePackStrings(languagePackId: String, keys: Array<String>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLanguagePackStrings(languagePackId, keys), 1, block)


suspend fun TdAbsHandler.getLanguagePackString(languagePackDatabasePath: String, localizationTarget: String, languagePackId: String, key: String) = sync<Ok>(GetLanguagePackString(languagePackDatabasePath, localizationTarget, languagePackId, key))
suspend fun TdAbsHandler.getLanguagePackStringOrNull(languagePackDatabasePath: String, localizationTarget: String, languagePackId: String, key: String) = syncOrNull<Ok>(GetLanguagePackString(languagePackDatabasePath, localizationTarget, languagePackId, key))
fun TdAbsHandler.getLanguagePackString(languagePackDatabasePath: String, localizationTarget: String, languagePackId: String, key: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLanguagePackString(languagePackDatabasePath, localizationTarget, languagePackId, key), 1, block)


suspend infix fun TdAbsHandler.getLanguagePackInfo(languagePackId: String) = sync<Ok>(GetLanguagePackInfo(languagePackId))
suspend infix fun TdAbsHandler.getLanguagePackInfoOrNull(languagePackId: String) = syncOrNull<Ok>(GetLanguagePackInfo(languagePackId))
fun TdAbsHandler.getLanguagePackInfo(languagePackId: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetLanguagePackInfo(languagePackId), 1, block)


suspend infix fun TdAbsHandler.getJsonValue(json: String) = sync<Ok>(GetJsonValue(json))
suspend infix fun TdAbsHandler.getJsonValueOrNull(json: String) = syncOrNull<Ok>(GetJsonValue(json))
fun TdAbsHandler.getJsonValue(json: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetJsonValue(json), 1, block)


suspend infix fun TdAbsHandler.getJsonString(jsonValue: JsonValue) = sync<Ok>(GetJsonString(jsonValue))
suspend infix fun TdAbsHandler.getJsonStringOrNull(jsonValue: JsonValue) = syncOrNull<Ok>(GetJsonString(jsonValue))
fun TdAbsHandler.getJsonString(jsonValue: JsonValue, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetJsonString(jsonValue), 1, block)


suspend fun TdAbsHandler.getInviteText() = sync<Ok>(GetInviteText())
suspend fun TdAbsHandler.getInviteTextOrNull() = syncOrNull<Ok>(GetInviteText())
fun TdAbsHandler.getInviteText(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetInviteText(), 1, block)


suspend infix fun TdAbsHandler.getInstalledStickerSets(isMasks: Boolean) = sync<Ok>(GetInstalledStickerSets(isMasks))
suspend infix fun TdAbsHandler.getInstalledStickerSetsOrNull(isMasks: Boolean) = syncOrNull<Ok>(GetInstalledStickerSets(isMasks))
fun TdAbsHandler.getInstalledStickerSets(isMasks: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetInstalledStickerSets(isMasks), 1, block)


suspend fun TdAbsHandler.getInlineQueryResults(botUserId: Int, chatId: Number, userLocation: Location, query: String, offset: String) = sync<Ok>(GetInlineQueryResults(botUserId, chatId.toLong(), userLocation, query, offset))
suspend fun TdAbsHandler.getInlineQueryResultsOrNull(botUserId: Int, chatId: Number, userLocation: Location, query: String, offset: String) = syncOrNull<Ok>(GetInlineQueryResults(botUserId, chatId.toLong(), userLocation, query, offset))
fun TdAbsHandler.getInlineQueryResults(botUserId: Int, chatId: Number, userLocation: Location, query: String, offset: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetInlineQueryResults(botUserId, chatId.toLong(), userLocation, query, offset), 1, block)


suspend fun TdAbsHandler.getInlineGameHighScores(inlineMessageId: String, userId: Int) = sync<Ok>(GetInlineGameHighScores(inlineMessageId, userId))
suspend fun TdAbsHandler.getInlineGameHighScoresOrNull(inlineMessageId: String, userId: Int) = syncOrNull<Ok>(GetInlineGameHighScores(inlineMessageId, userId))
fun TdAbsHandler.getInlineGameHighScores(inlineMessageId: String, userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetInlineGameHighScores(inlineMessageId, userId), 1, block)


suspend fun TdAbsHandler.getInactiveSupergroupChats() = sync<Ok>(GetInactiveSupergroupChats())
suspend fun TdAbsHandler.getInactiveSupergroupChatsOrNull() = syncOrNull<Ok>(GetInactiveSupergroupChats())
fun TdAbsHandler.getInactiveSupergroupChats(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetInactiveSupergroupChats(), 1, block)


suspend fun TdAbsHandler.getImportedContactCount() = sync<Ok>(GetImportedContactCount())
suspend fun TdAbsHandler.getImportedContactCountOrNull() = syncOrNull<Ok>(GetImportedContactCount())
fun TdAbsHandler.getImportedContactCount(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetImportedContactCount(), 1, block)


suspend fun TdAbsHandler.getGroupsInCommon(userId: Int, offsetChatId: Long, limit: Int) = sync<Ok>(GetGroupsInCommon(userId, offsetChatId, limit))
suspend fun TdAbsHandler.getGroupsInCommonOrNull(userId: Int, offsetChatId: Long, limit: Int) = syncOrNull<Ok>(GetGroupsInCommon(userId, offsetChatId, limit))
fun TdAbsHandler.getGroupsInCommon(userId: Int, offsetChatId: Long, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetGroupsInCommon(userId, offsetChatId, limit), 1, block)


suspend fun TdAbsHandler.getGameHighScores(chatId: Number, messageId: Long, userId: Int) = sync<Ok>(GetGameHighScores(chatId.toLong(), messageId, userId))
suspend fun TdAbsHandler.getGameHighScoresOrNull(chatId: Number, messageId: Long, userId: Int) = syncOrNull<Ok>(GetGameHighScores(chatId.toLong(), messageId, userId))
fun TdAbsHandler.getGameHighScores(chatId: Number, messageId: Long, userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetGameHighScores(chatId.toLong(), messageId, userId), 1, block)


suspend infix fun TdAbsHandler.getFileMimeType(fileName: String) = sync<Ok>(GetFileMimeType(fileName))
suspend infix fun TdAbsHandler.getFileMimeTypeOrNull(fileName: String) = syncOrNull<Ok>(GetFileMimeType(fileName))
fun TdAbsHandler.getFileMimeType(fileName: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetFileMimeType(fileName), 1, block)


suspend infix fun TdAbsHandler.getFileExtension(mimeType: String) = sync<Ok>(GetFileExtension(mimeType))
suspend infix fun TdAbsHandler.getFileExtensionOrNull(mimeType: String) = syncOrNull<Ok>(GetFileExtension(mimeType))
fun TdAbsHandler.getFileExtension(mimeType: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetFileExtension(mimeType), 1, block)


suspend fun TdAbsHandler.getFileDownloadedPrefixSize(fileId: Int, offset: Int) = sync<Ok>(GetFileDownloadedPrefixSize(fileId, offset))
suspend fun TdAbsHandler.getFileDownloadedPrefixSizeOrNull(fileId: Int, offset: Int) = syncOrNull<Ok>(GetFileDownloadedPrefixSize(fileId, offset))
fun TdAbsHandler.getFileDownloadedPrefixSize(fileId: Int, offset: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetFileDownloadedPrefixSize(fileId, offset), 1, block)


suspend infix fun TdAbsHandler.getFile(fileId: Int) = sync<Ok>(GetFile(fileId))
suspend infix fun TdAbsHandler.getFileOrNull(fileId: Int) = syncOrNull<Ok>(GetFile(fileId))
fun TdAbsHandler.getFile(fileId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetFile(fileId), 1, block)


suspend fun TdAbsHandler.getFavoriteStickers() = sync<Ok>(GetFavoriteStickers())
suspend fun TdAbsHandler.getFavoriteStickersOrNull() = syncOrNull<Ok>(GetFavoriteStickers())
fun TdAbsHandler.getFavoriteStickers(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetFavoriteStickers(), 1, block)


suspend infix fun TdAbsHandler.getEmojiSuggestionsUrl(languageCode: String) = sync<Ok>(GetEmojiSuggestionsUrl(languageCode))
suspend infix fun TdAbsHandler.getEmojiSuggestionsUrlOrNull(languageCode: String) = syncOrNull<Ok>(GetEmojiSuggestionsUrl(languageCode))
fun TdAbsHandler.getEmojiSuggestionsUrl(languageCode: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetEmojiSuggestionsUrl(languageCode), 1, block)


suspend infix fun TdAbsHandler.getDeepLinkInfo(link: String) = sync<Ok>(GetDeepLinkInfo(link))
suspend infix fun TdAbsHandler.getDeepLinkInfoOrNull(link: String) = syncOrNull<Ok>(GetDeepLinkInfo(link))
fun TdAbsHandler.getDeepLinkInfo(link: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetDeepLinkInfo(link), 1, block)


suspend fun TdAbsHandler.getDatabaseStatistics() = sync<Ok>(GetDatabaseStatistics())
suspend fun TdAbsHandler.getDatabaseStatisticsOrNull() = syncOrNull<Ok>(GetDatabaseStatistics())
fun TdAbsHandler.getDatabaseStatistics(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetDatabaseStatistics(), 1, block)


suspend fun TdAbsHandler.getCurrentState() = sync<Ok>(GetCurrentState())
suspend fun TdAbsHandler.getCurrentStateOrNull() = syncOrNull<Ok>(GetCurrentState())
fun TdAbsHandler.getCurrentState(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetCurrentState(), 1, block)


suspend infix fun TdAbsHandler.getCreatedPublicChats(type: PublicChatType) = sync<Ok>(GetCreatedPublicChats(type))
suspend infix fun TdAbsHandler.getCreatedPublicChatsOrNull(type: PublicChatType) = syncOrNull<Ok>(GetCreatedPublicChats(type))
fun TdAbsHandler.getCreatedPublicChats(type: PublicChatType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetCreatedPublicChats(type), 1, block)


suspend fun TdAbsHandler.getCountryCode() = sync<Ok>(GetCountryCode())
suspend fun TdAbsHandler.getCountryCodeOrNull() = syncOrNull<Ok>(GetCountryCode())
fun TdAbsHandler.getCountryCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetCountryCode(), 1, block)


suspend fun TdAbsHandler.getContacts() = sync<Ok>(GetContacts())
suspend fun TdAbsHandler.getContactsOrNull() = syncOrNull<Ok>(GetContacts())
fun TdAbsHandler.getContacts(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetContacts(), 1, block)


suspend fun TdAbsHandler.getConnectedWebsites() = sync<Ok>(GetConnectedWebsites())
suspend fun TdAbsHandler.getConnectedWebsitesOrNull() = syncOrNull<Ok>(GetConnectedWebsites())
fun TdAbsHandler.getConnectedWebsites(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetConnectedWebsites(), 1, block)


suspend fun TdAbsHandler.getChats(chatList: ChatList, offsetOrder: Long, offsetChatId: Long, limit: Int) = sync<Ok>(GetChats(chatList, offsetOrder, offsetChatId, limit))
suspend fun TdAbsHandler.getChatsOrNull(chatList: ChatList, offsetOrder: Long, offsetChatId: Long, limit: Int) = syncOrNull<Ok>(GetChats(chatList, offsetOrder, offsetChatId, limit))
fun TdAbsHandler.getChats(chatList: ChatList, offsetOrder: Long, offsetChatId: Long, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChats(chatList, offsetOrder, offsetChatId, limit), 1, block)


suspend fun TdAbsHandler.getChatStatisticsUrl(chatId: Number, parameters: String, isDark: Boolean) = sync<Ok>(GetChatStatisticsUrl(chatId.toLong(), parameters, isDark))
suspend fun TdAbsHandler.getChatStatisticsUrlOrNull(chatId: Number, parameters: String, isDark: Boolean) = syncOrNull<Ok>(GetChatStatisticsUrl(chatId.toLong(), parameters, isDark))
fun TdAbsHandler.getChatStatisticsUrl(chatId: Number, parameters: String, isDark: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatStatisticsUrl(chatId.toLong(), parameters, isDark), 1, block)


suspend infix fun TdAbsHandler.getChatScheduledMessages(chatId: Number) = sync<Ok>(GetChatScheduledMessages(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatScheduledMessagesOrNull(chatId: Number) = syncOrNull<Ok>(GetChatScheduledMessages(chatId.toLong()))
fun TdAbsHandler.getChatScheduledMessages(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatScheduledMessages(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.getChatPinnedMessage(chatId: Number) = sync<Ok>(GetChatPinnedMessage(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatPinnedMessageOrNull(chatId: Number) = syncOrNull<Ok>(GetChatPinnedMessage(chatId.toLong()))
fun TdAbsHandler.getChatPinnedMessage(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatPinnedMessage(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.getChatNotificationSettingsExceptions(scope: NotificationSettingsScope, compareSound: Boolean) = sync<Ok>(GetChatNotificationSettingsExceptions(scope, compareSound))
suspend fun TdAbsHandler.getChatNotificationSettingsExceptionsOrNull(scope: NotificationSettingsScope, compareSound: Boolean) = syncOrNull<Ok>(GetChatNotificationSettingsExceptions(scope, compareSound))
fun TdAbsHandler.getChatNotificationSettingsExceptions(scope: NotificationSettingsScope, compareSound: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatNotificationSettingsExceptions(scope, compareSound), 1, block)


suspend fun TdAbsHandler.getChatMessageCount(chatId: Number, filter: SearchMessagesFilter, returnLocal: Boolean) = sync<Ok>(GetChatMessageCount(chatId.toLong(), filter, returnLocal))
suspend fun TdAbsHandler.getChatMessageCountOrNull(chatId: Number, filter: SearchMessagesFilter, returnLocal: Boolean) = syncOrNull<Ok>(GetChatMessageCount(chatId.toLong(), filter, returnLocal))
fun TdAbsHandler.getChatMessageCount(chatId: Number, filter: SearchMessagesFilter, returnLocal: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatMessageCount(chatId.toLong(), filter, returnLocal), 1, block)


suspend fun TdAbsHandler.getChatMessageByDate(chatId: Number, date: Int) = sync<Ok>(GetChatMessageByDate(chatId.toLong(), date))
suspend fun TdAbsHandler.getChatMessageByDateOrNull(chatId: Number, date: Int) = syncOrNull<Ok>(GetChatMessageByDate(chatId.toLong(), date))
fun TdAbsHandler.getChatMessageByDate(chatId: Number, date: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatMessageByDate(chatId.toLong(), date), 1, block)


suspend fun TdAbsHandler.getChatMember(chatId: Number, userId: Int) = sync<Ok>(GetChatMember(chatId.toLong(), userId))
suspend fun TdAbsHandler.getChatMemberOrNull(chatId: Number, userId: Int) = syncOrNull<Ok>(GetChatMember(chatId.toLong(), userId))
fun TdAbsHandler.getChatMember(chatId: Number, userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatMember(chatId.toLong(), userId), 1, block)


suspend fun TdAbsHandler.getChatHistory(chatId: Number, fromMessageId: Long, offset: Int, limit: Int, onlyLocal: Boolean) = sync<Ok>(GetChatHistory(chatId.toLong(), fromMessageId, offset, limit, onlyLocal))
suspend fun TdAbsHandler.getChatHistoryOrNull(chatId: Number, fromMessageId: Long, offset: Int, limit: Int, onlyLocal: Boolean) = syncOrNull<Ok>(GetChatHistory(chatId.toLong(), fromMessageId, offset, limit, onlyLocal))
fun TdAbsHandler.getChatHistory(chatId: Number, fromMessageId: Long, offset: Int, limit: Int, onlyLocal: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatHistory(chatId.toLong(), fromMessageId, offset, limit, onlyLocal), 1, block)


suspend fun TdAbsHandler.getChatEventLog(chatId: Number, query: String, fromEventId: Long, limit: Int, filters: ChatEventLogFilters, userIds: IntArray) = sync<Ok>(GetChatEventLog(chatId.toLong(), query, fromEventId, limit, filters, userIds))
suspend fun TdAbsHandler.getChatEventLogOrNull(chatId: Number, query: String, fromEventId: Long, limit: Int, filters: ChatEventLogFilters, userIds: IntArray) = syncOrNull<Ok>(GetChatEventLog(chatId.toLong(), query, fromEventId, limit, filters, userIds))
fun TdAbsHandler.getChatEventLog(chatId: Number, query: String, fromEventId: Long, limit: Int, filters: ChatEventLogFilters, userIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatEventLog(chatId.toLong(), query, fromEventId, limit, filters, userIds), 1, block)


suspend infix fun TdAbsHandler.getChatAdministrators(chatId: Number) = sync<Ok>(GetChatAdministrators(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatAdministratorsOrNull(chatId: Number) = syncOrNull<Ok>(GetChatAdministrators(chatId.toLong()))
fun TdAbsHandler.getChatAdministrators(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChatAdministrators(chatId.toLong()), 1, block)


suspend infix fun TdAbsHandler.getChat(chatId: Number) = sync<Ok>(GetChat(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatOrNull(chatId: Number) = syncOrNull<Ok>(GetChat(chatId.toLong()))
fun TdAbsHandler.getChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetChat(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.getCallbackQueryAnswer(chatId: Number, messageId: Long, payload: CallbackQueryPayload) = sync<Ok>(GetCallbackQueryAnswer(chatId.toLong(), messageId, payload))
suspend fun TdAbsHandler.getCallbackQueryAnswerOrNull(chatId: Number, messageId: Long, payload: CallbackQueryPayload) = syncOrNull<Ok>(GetCallbackQueryAnswer(chatId.toLong(), messageId, payload))
fun TdAbsHandler.getCallbackQueryAnswer(chatId: Number, messageId: Long, payload: CallbackQueryPayload, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetCallbackQueryAnswer(chatId.toLong(), messageId, payload), 1, block)


suspend fun TdAbsHandler.getBlockedUsers(offset: Int, limit: Int) = sync<Ok>(GetBlockedUsers(offset, limit))
suspend fun TdAbsHandler.getBlockedUsersOrNull(offset: Int, limit: Int) = syncOrNull<Ok>(GetBlockedUsers(offset, limit))
fun TdAbsHandler.getBlockedUsers(offset: Int, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetBlockedUsers(offset, limit), 1, block)


suspend infix fun TdAbsHandler.getBasicGroupFullInfo(basicGroupId: Int) = sync<Ok>(GetBasicGroupFullInfo(basicGroupId))
suspend infix fun TdAbsHandler.getBasicGroupFullInfoOrNull(basicGroupId: Int) = syncOrNull<Ok>(GetBasicGroupFullInfo(basicGroupId))
fun TdAbsHandler.getBasicGroupFullInfo(basicGroupId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetBasicGroupFullInfo(basicGroupId), 1, block)


suspend infix fun TdAbsHandler.getBasicGroup(basicGroupId: Int) = sync<Ok>(GetBasicGroup(basicGroupId))
suspend infix fun TdAbsHandler.getBasicGroupOrNull(basicGroupId: Int) = syncOrNull<Ok>(GetBasicGroup(basicGroupId))
fun TdAbsHandler.getBasicGroup(basicGroupId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetBasicGroup(basicGroupId), 1, block)


suspend infix fun TdAbsHandler.getBackgrounds(forDarkTheme: Boolean) = sync<Ok>(GetBackgrounds(forDarkTheme))
suspend infix fun TdAbsHandler.getBackgroundsOrNull(forDarkTheme: Boolean) = syncOrNull<Ok>(GetBackgrounds(forDarkTheme))
fun TdAbsHandler.getBackgrounds(forDarkTheme: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetBackgrounds(forDarkTheme), 1, block)


suspend fun TdAbsHandler.getBackgroundUrl(name: String, type: BackgroundType) = sync<Ok>(GetBackgroundUrl(name, type))
suspend fun TdAbsHandler.getBackgroundUrlOrNull(name: String, type: BackgroundType) = syncOrNull<Ok>(GetBackgroundUrl(name, type))
fun TdAbsHandler.getBackgroundUrl(name: String, type: BackgroundType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetBackgroundUrl(name, type), 1, block)


suspend fun TdAbsHandler.getAutoDownloadSettingsPresets() = sync<Ok>(GetAutoDownloadSettingsPresets())
suspend fun TdAbsHandler.getAutoDownloadSettingsPresetsOrNull() = syncOrNull<Ok>(GetAutoDownloadSettingsPresets())
fun TdAbsHandler.getAutoDownloadSettingsPresets(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetAutoDownloadSettingsPresets(), 1, block)


suspend fun TdAbsHandler.getAuthorizationState() = sync<Ok>(GetAuthorizationState())
suspend fun TdAbsHandler.getAuthorizationStateOrNull() = syncOrNull<Ok>(GetAuthorizationState())
fun TdAbsHandler.getAuthorizationState(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetAuthorizationState(), 1, block)


suspend infix fun TdAbsHandler.getAttachedStickerSets(fileId: Int) = sync<Ok>(GetAttachedStickerSets(fileId))
suspend infix fun TdAbsHandler.getAttachedStickerSetsOrNull(fileId: Int) = syncOrNull<Ok>(GetAttachedStickerSets(fileId))
fun TdAbsHandler.getAttachedStickerSets(fileId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetAttachedStickerSets(fileId), 1, block)


suspend fun TdAbsHandler.getArchivedStickerSets(isMasks: Boolean, offsetStickerSetId: Long, limit: Int) = sync<Ok>(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit))
suspend fun TdAbsHandler.getArchivedStickerSetsOrNull(isMasks: Boolean, offsetStickerSetId: Long, limit: Int) = syncOrNull<Ok>(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit))
fun TdAbsHandler.getArchivedStickerSets(isMasks: Boolean, offsetStickerSetId: Long, limit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit), 1, block)


suspend fun TdAbsHandler.getApplicationConfig() = sync<Ok>(GetApplicationConfig())
suspend fun TdAbsHandler.getApplicationConfigOrNull() = syncOrNull<Ok>(GetApplicationConfig())
fun TdAbsHandler.getApplicationConfig(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetApplicationConfig(), 1, block)


suspend infix fun TdAbsHandler.getAllPassportElements(password: String) = sync<Ok>(GetAllPassportElements(password))
suspend infix fun TdAbsHandler.getAllPassportElementsOrNull(password: String) = syncOrNull<Ok>(GetAllPassportElements(password))
fun TdAbsHandler.getAllPassportElements(password: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetAllPassportElements(password), 1, block)


suspend fun TdAbsHandler.getActiveSessions() = sync<Ok>(GetActiveSessions())
suspend fun TdAbsHandler.getActiveSessionsOrNull() = syncOrNull<Ok>(GetActiveSessions())
fun TdAbsHandler.getActiveSessions(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetActiveSessions(), 1, block)


suspend fun TdAbsHandler.getActiveLiveLocationMessages() = sync<Ok>(GetActiveLiveLocationMessages())
suspend fun TdAbsHandler.getActiveLiveLocationMessagesOrNull() = syncOrNull<Ok>(GetActiveLiveLocationMessages())
fun TdAbsHandler.getActiveLiveLocationMessages(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetActiveLiveLocationMessages(), 1, block)


suspend fun TdAbsHandler.getAccountTtl() = sync<Ok>(GetAccountTtl())
suspend fun TdAbsHandler.getAccountTtlOrNull() = syncOrNull<Ok>(GetAccountTtl())
fun TdAbsHandler.getAccountTtl(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GetAccountTtl(), 1, block)


suspend infix fun TdAbsHandler.generateChatInviteLink(chatId: Number) = sync<Ok>(GenerateChatInviteLink(chatId.toLong()))
suspend infix fun TdAbsHandler.generateChatInviteLinkOrNull(chatId: Number) = syncOrNull<Ok>(GenerateChatInviteLink(chatId.toLong()))
fun TdAbsHandler.generateChatInviteLink(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(GenerateChatInviteLink(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.forwardMessages(chatId: Number, fromChatId: Long, messageIds: LongArray, options: SendMessageOptions, asAlbum: Boolean, sendCopy: Boolean, removeCaption: Boolean) = sync<Ok>(ForwardMessages(chatId.toLong(), fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption))
suspend fun TdAbsHandler.forwardMessagesOrNull(chatId: Number, fromChatId: Long, messageIds: LongArray, options: SendMessageOptions, asAlbum: Boolean, sendCopy: Boolean, removeCaption: Boolean) = syncOrNull<Ok>(ForwardMessages(chatId.toLong(), fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption))
fun TdAbsHandler.forwardMessages(chatId: Number, fromChatId: Long, messageIds: LongArray, options: SendMessageOptions, asAlbum: Boolean, sendCopy: Boolean, removeCaption: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ForwardMessages(chatId.toLong(), fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption), 1, block)


suspend fun TdAbsHandler.finishFileGeneration(generationId: Long, error: Error) = sync<Ok>(FinishFileGeneration(generationId, error))
suspend fun TdAbsHandler.finishFileGenerationOrNull(generationId: Long, error: Error) = syncOrNull<Ok>(FinishFileGeneration(generationId, error))
fun TdAbsHandler.finishFileGeneration(generationId: Long, error: Error, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(FinishFileGeneration(generationId, error), 1, block)


suspend infix fun TdAbsHandler.enableProxy(proxyId: Int) = sync<Ok>(EnableProxy(proxyId))
suspend infix fun TdAbsHandler.enableProxyOrNull(proxyId: Int) = syncOrNull<Ok>(EnableProxy(proxyId))
fun TdAbsHandler.enableProxy(proxyId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EnableProxy(proxyId), 1, block)


suspend fun TdAbsHandler.editProxy(proxyId: Int, server: String, port: Int, enable: Boolean, type: ProxyType) = sync<Ok>(EditProxy(proxyId, server, port, enable, type))
suspend fun TdAbsHandler.editProxyOrNull(proxyId: Int, server: String, port: Int, enable: Boolean, type: ProxyType) = syncOrNull<Ok>(EditProxy(proxyId, server, port, enable, type))
fun TdAbsHandler.editProxy(proxyId: Int, server: String, port: Int, enable: Boolean, type: ProxyType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditProxy(proxyId, server, port, enable, type), 1, block)


suspend fun TdAbsHandler.editMessageText(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<Ok>(EditMessageText(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.editMessageTextOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<Ok>(EditMessageText(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
fun TdAbsHandler.editMessageText(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditMessageText(chatId.toLong(), messageId, replyMarkup, inputMessageContent), 1, block)


suspend fun TdAbsHandler.editMessageSchedulingState(chatId: Number, messageId: Long, schedulingState: MessageSchedulingState) = sync<Ok>(EditMessageSchedulingState(chatId.toLong(), messageId, schedulingState))
suspend fun TdAbsHandler.editMessageSchedulingStateOrNull(chatId: Number, messageId: Long, schedulingState: MessageSchedulingState) = syncOrNull<Ok>(EditMessageSchedulingState(chatId.toLong(), messageId, schedulingState))
fun TdAbsHandler.editMessageSchedulingState(chatId: Number, messageId: Long, schedulingState: MessageSchedulingState, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditMessageSchedulingState(chatId.toLong(), messageId, schedulingState), 1, block)


suspend fun TdAbsHandler.editMessageReplyMarkup(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup) = sync<Ok>(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup))
suspend fun TdAbsHandler.editMessageReplyMarkupOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup) = syncOrNull<Ok>(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup))
fun TdAbsHandler.editMessageReplyMarkup(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup), 1, block)


suspend fun TdAbsHandler.editMessageMedia(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<Ok>(EditMessageMedia(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.editMessageMediaOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<Ok>(EditMessageMedia(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
fun TdAbsHandler.editMessageMedia(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditMessageMedia(chatId.toLong(), messageId, replyMarkup, inputMessageContent), 1, block)


suspend fun TdAbsHandler.editMessageLiveLocation(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, location: Location) = sync<Ok>(EditMessageLiveLocation(chatId.toLong(), messageId, replyMarkup, location))
suspend fun TdAbsHandler.editMessageLiveLocationOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, location: Location) = syncOrNull<Ok>(EditMessageLiveLocation(chatId.toLong(), messageId, replyMarkup, location))
fun TdAbsHandler.editMessageLiveLocation(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, location: Location, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditMessageLiveLocation(chatId.toLong(), messageId, replyMarkup, location), 1, block)


suspend fun TdAbsHandler.editMessageCaption(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, caption: FormattedText) = sync<Ok>(EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption))
suspend fun TdAbsHandler.editMessageCaptionOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, caption: FormattedText) = syncOrNull<Ok>(EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption))
fun TdAbsHandler.editMessageCaption(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, caption: FormattedText, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption), 1, block)


suspend fun TdAbsHandler.editInlineMessageText(inlineMessageId: String, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<Ok>(EditInlineMessageText(inlineMessageId, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.editInlineMessageTextOrNull(inlineMessageId: String, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<Ok>(EditInlineMessageText(inlineMessageId, replyMarkup, inputMessageContent))
fun TdAbsHandler.editInlineMessageText(inlineMessageId: String, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditInlineMessageText(inlineMessageId, replyMarkup, inputMessageContent), 1, block)


suspend fun TdAbsHandler.editInlineMessageReplyMarkup(inlineMessageId: String, replyMarkup: ReplyMarkup) = sync<Ok>(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))
suspend fun TdAbsHandler.editInlineMessageReplyMarkupOrNull(inlineMessageId: String, replyMarkup: ReplyMarkup) = syncOrNull<Ok>(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))
fun TdAbsHandler.editInlineMessageReplyMarkup(inlineMessageId: String, replyMarkup: ReplyMarkup, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup), 1, block)


suspend fun TdAbsHandler.editInlineMessageMedia(inlineMessageId: String, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<Ok>(EditInlineMessageMedia(inlineMessageId, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.editInlineMessageMediaOrNull(inlineMessageId: String, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<Ok>(EditInlineMessageMedia(inlineMessageId, replyMarkup, inputMessageContent))
fun TdAbsHandler.editInlineMessageMedia(inlineMessageId: String, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditInlineMessageMedia(inlineMessageId, replyMarkup, inputMessageContent), 1, block)


suspend fun TdAbsHandler.editInlineMessageLiveLocation(inlineMessageId: String, replyMarkup: ReplyMarkup, location: Location) = sync<Ok>(EditInlineMessageLiveLocation(inlineMessageId, replyMarkup, location))
suspend fun TdAbsHandler.editInlineMessageLiveLocationOrNull(inlineMessageId: String, replyMarkup: ReplyMarkup, location: Location) = syncOrNull<Ok>(EditInlineMessageLiveLocation(inlineMessageId, replyMarkup, location))
fun TdAbsHandler.editInlineMessageLiveLocation(inlineMessageId: String, replyMarkup: ReplyMarkup, location: Location, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditInlineMessageLiveLocation(inlineMessageId, replyMarkup, location), 1, block)


suspend fun TdAbsHandler.editInlineMessageCaption(inlineMessageId: String, replyMarkup: ReplyMarkup, caption: FormattedText) = sync<Ok>(EditInlineMessageCaption(inlineMessageId, replyMarkup, caption))
suspend fun TdAbsHandler.editInlineMessageCaptionOrNull(inlineMessageId: String, replyMarkup: ReplyMarkup, caption: FormattedText) = syncOrNull<Ok>(EditInlineMessageCaption(inlineMessageId, replyMarkup, caption))
fun TdAbsHandler.editInlineMessageCaption(inlineMessageId: String, replyMarkup: ReplyMarkup, caption: FormattedText, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditInlineMessageCaption(inlineMessageId, replyMarkup, caption), 1, block)


suspend infix fun TdAbsHandler.editCustomLanguagePackInfo(info: LanguagePackInfo) = sync<Ok>(EditCustomLanguagePackInfo(info))
suspend infix fun TdAbsHandler.editCustomLanguagePackInfoOrNull(info: LanguagePackInfo) = syncOrNull<Ok>(EditCustomLanguagePackInfo(info))
fun TdAbsHandler.editCustomLanguagePackInfo(info: LanguagePackInfo, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditCustomLanguagePackInfo(info), 1, block)


suspend fun TdAbsHandler.downloadFile(fileId: Int, priority: Int, offset: Int, limit: Int, synchronous: Boolean) = sync<Ok>(DownloadFile(fileId, priority, offset, limit, synchronous))
suspend fun TdAbsHandler.downloadFileOrNull(fileId: Int, priority: Int, offset: Int, limit: Int, synchronous: Boolean) = syncOrNull<Ok>(DownloadFile(fileId, priority, offset, limit, synchronous))
fun TdAbsHandler.downloadFile(fileId: Int, priority: Int, offset: Int, limit: Int, synchronous: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DownloadFile(fileId, priority, offset, limit, synchronous), 1, block)


suspend infix fun TdAbsHandler.disconnectWebsite(websiteId: Long) = sync<Ok>(DisconnectWebsite(websiteId))
suspend infix fun TdAbsHandler.disconnectWebsiteOrNull(websiteId: Long) = syncOrNull<Ok>(DisconnectWebsite(websiteId))
fun TdAbsHandler.disconnectWebsite(websiteId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DisconnectWebsite(websiteId), 1, block)


suspend fun TdAbsHandler.disconnectAllWebsites() = sync<Ok>(DisconnectAllWebsites())
suspend fun TdAbsHandler.disconnectAllWebsitesOrNull() = syncOrNull<Ok>(DisconnectAllWebsites())
fun TdAbsHandler.disconnectAllWebsites(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DisconnectAllWebsites(), 1, block)


suspend fun TdAbsHandler.discardCall(callId: Int, isDisconnected: Boolean, duration: Int, connectionId: Long) = sync<Ok>(DiscardCall(callId, isDisconnected, duration, connectionId))
suspend fun TdAbsHandler.discardCallOrNull(callId: Int, isDisconnected: Boolean, duration: Int, connectionId: Long) = syncOrNull<Ok>(DiscardCall(callId, isDisconnected, duration, connectionId))
fun TdAbsHandler.discardCall(callId: Int, isDisconnected: Boolean, duration: Int, connectionId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DiscardCall(callId, isDisconnected, duration, connectionId), 1, block)


suspend fun TdAbsHandler.disableProxy() = sync<Ok>(DisableProxy())
suspend fun TdAbsHandler.disableProxyOrNull() = syncOrNull<Ok>(DisableProxy())
fun TdAbsHandler.disableProxy(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DisableProxy(), 1, block)


suspend fun TdAbsHandler.destroy() = sync<Ok>(Destroy())
suspend fun TdAbsHandler.destroyOrNull() = syncOrNull<Ok>(Destroy())
fun TdAbsHandler.destroy(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(Destroy(), 1, block)


suspend infix fun TdAbsHandler.deleteSupergroup(supergroupId: Int) = sync<Ok>(DeleteSupergroup(supergroupId))
suspend infix fun TdAbsHandler.deleteSupergroupOrNull(supergroupId: Int) = syncOrNull<Ok>(DeleteSupergroup(supergroupId))
fun TdAbsHandler.deleteSupergroup(supergroupId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteSupergroup(supergroupId), 1, block)


suspend fun TdAbsHandler.deleteSavedOrderInfo() = sync<Ok>(DeleteSavedOrderInfo())
suspend fun TdAbsHandler.deleteSavedOrderInfoOrNull() = syncOrNull<Ok>(DeleteSavedOrderInfo())
fun TdAbsHandler.deleteSavedOrderInfo(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteSavedOrderInfo(), 1, block)


suspend fun TdAbsHandler.deleteSavedCredentials() = sync<Ok>(DeleteSavedCredentials())
suspend fun TdAbsHandler.deleteSavedCredentialsOrNull() = syncOrNull<Ok>(DeleteSavedCredentials())
fun TdAbsHandler.deleteSavedCredentials(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteSavedCredentials(), 1, block)


suspend infix fun TdAbsHandler.deleteProfilePhoto(profilePhotoId: Long) = sync<Ok>(DeleteProfilePhoto(profilePhotoId))
suspend infix fun TdAbsHandler.deleteProfilePhotoOrNull(profilePhotoId: Long) = syncOrNull<Ok>(DeleteProfilePhoto(profilePhotoId))
fun TdAbsHandler.deleteProfilePhoto(profilePhotoId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteProfilePhoto(profilePhotoId), 1, block)


suspend infix fun TdAbsHandler.deletePassportElement(type: PassportElementType) = sync<Ok>(DeletePassportElement(type))
suspend infix fun TdAbsHandler.deletePassportElementOrNull(type: PassportElementType) = syncOrNull<Ok>(DeletePassportElement(type))
fun TdAbsHandler.deletePassportElement(type: PassportElementType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeletePassportElement(type), 1, block)


suspend fun TdAbsHandler.deleteMessages(chatId: Number, messageIds: LongArray, revoke: Boolean) = sync<Ok>(DeleteMessages(chatId.toLong(), messageIds, revoke))
suspend fun TdAbsHandler.deleteMessagesOrNull(chatId: Number, messageIds: LongArray, revoke: Boolean) = syncOrNull<Ok>(DeleteMessages(chatId.toLong(), messageIds, revoke))
fun TdAbsHandler.deleteMessages(chatId: Number, messageIds: LongArray, revoke: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteMessages(chatId.toLong(), messageIds, revoke), 1, block)


suspend infix fun TdAbsHandler.deleteLanguagePack(languagePackId: String) = sync<Ok>(DeleteLanguagePack(languagePackId))
suspend infix fun TdAbsHandler.deleteLanguagePackOrNull(languagePackId: String) = syncOrNull<Ok>(DeleteLanguagePack(languagePackId))
fun TdAbsHandler.deleteLanguagePack(languagePackId: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteLanguagePack(languagePackId), 1, block)


suspend infix fun TdAbsHandler.deleteFile(fileId: Int) = sync<Ok>(DeleteFile(fileId))
suspend infix fun TdAbsHandler.deleteFileOrNull(fileId: Int) = syncOrNull<Ok>(DeleteFile(fileId))
fun TdAbsHandler.deleteFile(fileId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteFile(fileId), 1, block)


suspend fun TdAbsHandler.deleteChatReplyMarkup(chatId: Number, messageId: Long) = sync<Ok>(DeleteChatReplyMarkup(chatId.toLong(), messageId))
suspend fun TdAbsHandler.deleteChatReplyMarkupOrNull(chatId: Number, messageId: Long) = syncOrNull<Ok>(DeleteChatReplyMarkup(chatId.toLong(), messageId))
fun TdAbsHandler.deleteChatReplyMarkup(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteChatReplyMarkup(chatId.toLong(), messageId), 1, block)


suspend fun TdAbsHandler.deleteChatMessagesFromUser(chatId: Number, userId: Int) = sync<Ok>(DeleteChatMessagesFromUser(chatId.toLong(), userId))
suspend fun TdAbsHandler.deleteChatMessagesFromUserOrNull(chatId: Number, userId: Int) = syncOrNull<Ok>(DeleteChatMessagesFromUser(chatId.toLong(), userId))
fun TdAbsHandler.deleteChatMessagesFromUser(chatId: Number, userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteChatMessagesFromUser(chatId.toLong(), userId), 1, block)


suspend fun TdAbsHandler.deleteChatHistory(chatId: Number, removeFromChatList: Boolean, revoke: Boolean) = sync<Ok>(DeleteChatHistory(chatId.toLong(), removeFromChatList, revoke))
suspend fun TdAbsHandler.deleteChatHistoryOrNull(chatId: Number, removeFromChatList: Boolean, revoke: Boolean) = syncOrNull<Ok>(DeleteChatHistory(chatId.toLong(), removeFromChatList, revoke))
fun TdAbsHandler.deleteChatHistory(chatId: Number, removeFromChatList: Boolean, revoke: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteChatHistory(chatId.toLong(), removeFromChatList, revoke), 1, block)


suspend infix fun TdAbsHandler.deleteAccount(reason: String) = sync<Ok>(DeleteAccount(reason))
suspend infix fun TdAbsHandler.deleteAccountOrNull(reason: String) = syncOrNull<Ok>(DeleteAccount(reason))
fun TdAbsHandler.deleteAccount(reason: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(DeleteAccount(reason), 1, block)


suspend fun TdAbsHandler.createTemporaryPassword(password: String, validFor: Int) = sync<Ok>(CreateTemporaryPassword(password, validFor))
suspend fun TdAbsHandler.createTemporaryPasswordOrNull(password: String, validFor: Int) = syncOrNull<Ok>(CreateTemporaryPassword(password, validFor))
fun TdAbsHandler.createTemporaryPassword(password: String, validFor: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateTemporaryPassword(password, validFor), 1, block)


suspend fun TdAbsHandler.createSupergroupChat(supergroupId: Int, force: Boolean) = sync<Ok>(CreateSupergroupChat(supergroupId, force))
suspend fun TdAbsHandler.createSupergroupChatOrNull(supergroupId: Int, force: Boolean) = syncOrNull<Ok>(CreateSupergroupChat(supergroupId, force))
fun TdAbsHandler.createSupergroupChat(supergroupId: Int, force: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateSupergroupChat(supergroupId, force), 1, block)


suspend infix fun TdAbsHandler.createSecretChat(secretChatId: Int) = sync<Ok>(CreateSecretChat(secretChatId))
suspend infix fun TdAbsHandler.createSecretChatOrNull(secretChatId: Int) = syncOrNull<Ok>(CreateSecretChat(secretChatId))
fun TdAbsHandler.createSecretChat(secretChatId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateSecretChat(secretChatId), 1, block)


suspend fun TdAbsHandler.createPrivateChat(userId: Int, force: Boolean) = sync<Ok>(CreatePrivateChat(userId, force))
suspend fun TdAbsHandler.createPrivateChatOrNull(userId: Int, force: Boolean) = syncOrNull<Ok>(CreatePrivateChat(userId, force))
fun TdAbsHandler.createPrivateChat(userId: Int, force: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreatePrivateChat(userId, force), 1, block)


suspend fun TdAbsHandler.createNewSupergroupChat(title: String, isChannel: Boolean, description: String, location: ChatLocation) = sync<Ok>(CreateNewSupergroupChat(title, isChannel, description, location))
suspend fun TdAbsHandler.createNewSupergroupChatOrNull(title: String, isChannel: Boolean, description: String, location: ChatLocation) = syncOrNull<Ok>(CreateNewSupergroupChat(title, isChannel, description, location))
fun TdAbsHandler.createNewSupergroupChat(title: String, isChannel: Boolean, description: String, location: ChatLocation, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateNewSupergroupChat(title, isChannel, description, location), 1, block)


suspend fun TdAbsHandler.createNewStickerSet(userId: Int, title: String, name: String, isMasks: Boolean, stickers: Array<InputSticker>) = sync<Ok>(CreateNewStickerSet(userId, title, name, isMasks, stickers))
suspend fun TdAbsHandler.createNewStickerSetOrNull(userId: Int, title: String, name: String, isMasks: Boolean, stickers: Array<InputSticker>) = syncOrNull<Ok>(CreateNewStickerSet(userId, title, name, isMasks, stickers))
fun TdAbsHandler.createNewStickerSet(userId: Int, title: String, name: String, isMasks: Boolean, stickers: Array<InputSticker>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateNewStickerSet(userId, title, name, isMasks, stickers), 1, block)


suspend infix fun TdAbsHandler.createNewSecretChat(userId: Int) = sync<Ok>(CreateNewSecretChat(userId))
suspend infix fun TdAbsHandler.createNewSecretChatOrNull(userId: Int) = syncOrNull<Ok>(CreateNewSecretChat(userId))
fun TdAbsHandler.createNewSecretChat(userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateNewSecretChat(userId), 1, block)


suspend fun TdAbsHandler.createNewBasicGroupChat(userIds: IntArray, title: String) = sync<Ok>(CreateNewBasicGroupChat(userIds, title))
suspend fun TdAbsHandler.createNewBasicGroupChatOrNull(userIds: IntArray, title: String) = syncOrNull<Ok>(CreateNewBasicGroupChat(userIds, title))
fun TdAbsHandler.createNewBasicGroupChat(userIds: IntArray, title: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateNewBasicGroupChat(userIds, title), 1, block)


suspend fun TdAbsHandler.createCall(userId: Int, protocol: CallProtocol) = sync<Ok>(CreateCall(userId, protocol))
suspend fun TdAbsHandler.createCallOrNull(userId: Int, protocol: CallProtocol) = syncOrNull<Ok>(CreateCall(userId, protocol))
fun TdAbsHandler.createCall(userId: Int, protocol: CallProtocol, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateCall(userId, protocol), 1, block)


suspend fun TdAbsHandler.createBasicGroupChat(basicGroupId: Int, force: Boolean) = sync<Ok>(CreateBasicGroupChat(basicGroupId, force))
suspend fun TdAbsHandler.createBasicGroupChatOrNull(basicGroupId: Int, force: Boolean) = syncOrNull<Ok>(CreateBasicGroupChat(basicGroupId, force))
fun TdAbsHandler.createBasicGroupChat(basicGroupId: Int, force: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CreateBasicGroupChat(basicGroupId, force), 1, block)


suspend infix fun TdAbsHandler.confirmQrCodeAuthentication(link: String) = sync<Ok>(ConfirmQrCodeAuthentication(link))
suspend infix fun TdAbsHandler.confirmQrCodeAuthenticationOrNull(link: String) = syncOrNull<Ok>(ConfirmQrCodeAuthentication(link))
fun TdAbsHandler.confirmQrCodeAuthentication(link: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ConfirmQrCodeAuthentication(link), 1, block)


suspend infix fun TdAbsHandler.closeSecretChat(secretChatId: Int) = sync<Ok>(CloseSecretChat(secretChatId))
suspend infix fun TdAbsHandler.closeSecretChatOrNull(secretChatId: Int) = syncOrNull<Ok>(CloseSecretChat(secretChatId))
fun TdAbsHandler.closeSecretChat(secretChatId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CloseSecretChat(secretChatId), 1, block)


suspend infix fun TdAbsHandler.closeChat(chatId: Number) = sync<Ok>(CloseChat(chatId.toLong()))
suspend infix fun TdAbsHandler.closeChatOrNull(chatId: Number) = syncOrNull<Ok>(CloseChat(chatId.toLong()))
fun TdAbsHandler.closeChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CloseChat(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.close() = sync<Ok>(Close())
suspend fun TdAbsHandler.closeOrNull() = syncOrNull<Ok>(Close())
fun TdAbsHandler.close(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(Close(), 1, block)


suspend fun TdAbsHandler.clearRecentlyFoundChats() = sync<Ok>(ClearRecentlyFoundChats())
suspend fun TdAbsHandler.clearRecentlyFoundChatsOrNull() = syncOrNull<Ok>(ClearRecentlyFoundChats())
fun TdAbsHandler.clearRecentlyFoundChats(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ClearRecentlyFoundChats(), 1, block)


suspend infix fun TdAbsHandler.clearRecentStickers(isAttached: Boolean) = sync<Ok>(ClearRecentStickers(isAttached))
suspend infix fun TdAbsHandler.clearRecentStickersOrNull(isAttached: Boolean) = syncOrNull<Ok>(ClearRecentStickers(isAttached))
fun TdAbsHandler.clearRecentStickers(isAttached: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ClearRecentStickers(isAttached), 1, block)


suspend fun TdAbsHandler.clearImportedContacts() = sync<Ok>(ClearImportedContacts())
suspend fun TdAbsHandler.clearImportedContactsOrNull() = syncOrNull<Ok>(ClearImportedContacts())
fun TdAbsHandler.clearImportedContacts(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ClearImportedContacts(), 1, block)


suspend infix fun TdAbsHandler.clearAllDraftMessages(excludeSecretChats: Boolean) = sync<Ok>(ClearAllDraftMessages(excludeSecretChats))
suspend infix fun TdAbsHandler.clearAllDraftMessagesOrNull(excludeSecretChats: Boolean) = syncOrNull<Ok>(ClearAllDraftMessages(excludeSecretChats))
fun TdAbsHandler.clearAllDraftMessages(excludeSecretChats: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ClearAllDraftMessages(excludeSecretChats), 1, block)


suspend infix fun TdAbsHandler.cleanFileName(fileName: String) = sync<Ok>(CleanFileName(fileName))
suspend infix fun TdAbsHandler.cleanFileNameOrNull(fileName: String) = syncOrNull<Ok>(CleanFileName(fileName))
fun TdAbsHandler.cleanFileName(fileName: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CleanFileName(fileName), 1, block)


suspend infix fun TdAbsHandler.checkRecoveryEmailAddressCode(code: String) = sync<Ok>(CheckRecoveryEmailAddressCode(code))
suspend infix fun TdAbsHandler.checkRecoveryEmailAddressCodeOrNull(code: String) = syncOrNull<Ok>(CheckRecoveryEmailAddressCode(code))
fun TdAbsHandler.checkRecoveryEmailAddressCode(code: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckRecoveryEmailAddressCode(code), 1, block)


suspend infix fun TdAbsHandler.checkPhoneNumberVerificationCode(code: String) = sync<Ok>(CheckPhoneNumberVerificationCode(code))
suspend infix fun TdAbsHandler.checkPhoneNumberVerificationCodeOrNull(code: String) = syncOrNull<Ok>(CheckPhoneNumberVerificationCode(code))
fun TdAbsHandler.checkPhoneNumberVerificationCode(code: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckPhoneNumberVerificationCode(code), 1, block)


suspend infix fun TdAbsHandler.checkPhoneNumberConfirmationCode(code: String) = sync<Ok>(CheckPhoneNumberConfirmationCode(code))
suspend infix fun TdAbsHandler.checkPhoneNumberConfirmationCodeOrNull(code: String) = syncOrNull<Ok>(CheckPhoneNumberConfirmationCode(code))
fun TdAbsHandler.checkPhoneNumberConfirmationCode(code: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckPhoneNumberConfirmationCode(code), 1, block)


suspend infix fun TdAbsHandler.checkEmailAddressVerificationCode(code: String) = sync<Ok>(CheckEmailAddressVerificationCode(code))
suspend infix fun TdAbsHandler.checkEmailAddressVerificationCodeOrNull(code: String) = syncOrNull<Ok>(CheckEmailAddressVerificationCode(code))
fun TdAbsHandler.checkEmailAddressVerificationCode(code: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckEmailAddressVerificationCode(code), 1, block)


suspend infix fun TdAbsHandler.checkDatabaseEncryptionKey(encryptionKey: ByteArray) = sync<Ok>(CheckDatabaseEncryptionKey(encryptionKey))
suspend infix fun TdAbsHandler.checkDatabaseEncryptionKeyOrNull(encryptionKey: ByteArray) = syncOrNull<Ok>(CheckDatabaseEncryptionKey(encryptionKey))
fun TdAbsHandler.checkDatabaseEncryptionKey(encryptionKey: ByteArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckDatabaseEncryptionKey(encryptionKey), 1, block)


suspend infix fun TdAbsHandler.checkCreatedPublicChatsLimit(type: PublicChatType) = sync<Ok>(CheckCreatedPublicChatsLimit(type))
suspend infix fun TdAbsHandler.checkCreatedPublicChatsLimitOrNull(type: PublicChatType) = syncOrNull<Ok>(CheckCreatedPublicChatsLimit(type))
fun TdAbsHandler.checkCreatedPublicChatsLimit(type: PublicChatType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckCreatedPublicChatsLimit(type), 1, block)


suspend fun TdAbsHandler.checkChatUsername(chatId: Number, username: String) = sync<Ok>(CheckChatUsername(chatId.toLong(), username))
suspend fun TdAbsHandler.checkChatUsernameOrNull(chatId: Number, username: String) = syncOrNull<Ok>(CheckChatUsername(chatId.toLong(), username))
fun TdAbsHandler.checkChatUsername(chatId: Number, username: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckChatUsername(chatId.toLong(), username), 1, block)


suspend infix fun TdAbsHandler.checkChatInviteLink(inviteLink: String) = sync<Ok>(CheckChatInviteLink(inviteLink))
suspend infix fun TdAbsHandler.checkChatInviteLinkOrNull(inviteLink: String) = syncOrNull<Ok>(CheckChatInviteLink(inviteLink))
fun TdAbsHandler.checkChatInviteLink(inviteLink: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckChatInviteLink(inviteLink), 1, block)


suspend infix fun TdAbsHandler.checkChangePhoneNumberCode(code: String) = sync<Ok>(CheckChangePhoneNumberCode(code))
suspend infix fun TdAbsHandler.checkChangePhoneNumberCodeOrNull(code: String) = syncOrNull<Ok>(CheckChangePhoneNumberCode(code))
fun TdAbsHandler.checkChangePhoneNumberCode(code: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckChangePhoneNumberCode(code), 1, block)


suspend infix fun TdAbsHandler.checkAuthenticationPassword(password: String) = sync<Ok>(CheckAuthenticationPassword(password))
suspend infix fun TdAbsHandler.checkAuthenticationPasswordOrNull(password: String) = syncOrNull<Ok>(CheckAuthenticationPassword(password))
fun TdAbsHandler.checkAuthenticationPassword(password: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckAuthenticationPassword(password), 1, block)


suspend infix fun TdAbsHandler.checkAuthenticationCode(code: String) = sync<Ok>(CheckAuthenticationCode(code))
suspend infix fun TdAbsHandler.checkAuthenticationCodeOrNull(code: String) = syncOrNull<Ok>(CheckAuthenticationCode(code))
fun TdAbsHandler.checkAuthenticationCode(code: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckAuthenticationCode(code), 1, block)


suspend infix fun TdAbsHandler.checkAuthenticationBotToken(token: String) = sync<Ok>(CheckAuthenticationBotToken(token))
suspend infix fun TdAbsHandler.checkAuthenticationBotTokenOrNull(token: String) = syncOrNull<Ok>(CheckAuthenticationBotToken(token))
fun TdAbsHandler.checkAuthenticationBotToken(token: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CheckAuthenticationBotToken(token), 1, block)


suspend fun TdAbsHandler.changeStickerSet(setId: Long, isInstalled: Boolean, isArchived: Boolean) = sync<Ok>(ChangeStickerSet(setId, isInstalled, isArchived))
suspend fun TdAbsHandler.changeStickerSetOrNull(setId: Long, isInstalled: Boolean, isArchived: Boolean) = syncOrNull<Ok>(ChangeStickerSet(setId, isInstalled, isArchived))
fun TdAbsHandler.changeStickerSet(setId: Long, isInstalled: Boolean, isArchived: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ChangeStickerSet(setId, isInstalled, isArchived), 1, block)


suspend fun TdAbsHandler.changePhoneNumber(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = sync<Ok>(ChangePhoneNumber(phoneNumber, settings))
suspend fun TdAbsHandler.changePhoneNumberOrNull(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = syncOrNull<Ok>(ChangePhoneNumber(phoneNumber, settings))
fun TdAbsHandler.changePhoneNumber(phoneNumber: String, settings: PhoneNumberAuthenticationSettings, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ChangePhoneNumber(phoneNumber, settings), 1, block)


suspend infix fun TdAbsHandler.changeImportedContacts(contacts: Array<Contact>) = sync<Ok>(ChangeImportedContacts(contacts))
suspend infix fun TdAbsHandler.changeImportedContactsOrNull(contacts: Array<Contact>) = syncOrNull<Ok>(ChangeImportedContacts(contacts))
fun TdAbsHandler.changeImportedContacts(contacts: Array<Contact>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ChangeImportedContacts(contacts), 1, block)


suspend infix fun TdAbsHandler.cancelUploadFile(fileId: Int) = sync<Ok>(CancelUploadFile(fileId))
suspend infix fun TdAbsHandler.cancelUploadFileOrNull(fileId: Int) = syncOrNull<Ok>(CancelUploadFile(fileId))
fun TdAbsHandler.cancelUploadFile(fileId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CancelUploadFile(fileId), 1, block)


suspend fun TdAbsHandler.cancelDownloadFile(fileId: Int, onlyIfPending: Boolean) = sync<Ok>(CancelDownloadFile(fileId, onlyIfPending))
suspend fun TdAbsHandler.cancelDownloadFileOrNull(fileId: Int, onlyIfPending: Boolean) = syncOrNull<Ok>(CancelDownloadFile(fileId, onlyIfPending))
fun TdAbsHandler.cancelDownloadFile(fileId: Int, onlyIfPending: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CancelDownloadFile(fileId, onlyIfPending), 1, block)


suspend fun TdAbsHandler.canTransferOwnership() = sync<Ok>(CanTransferOwnership())
suspend fun TdAbsHandler.canTransferOwnershipOrNull() = syncOrNull<Ok>(CanTransferOwnership())
fun TdAbsHandler.canTransferOwnership(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CanTransferOwnership(), 1, block)


suspend infix fun TdAbsHandler.blockUser(userId: Int) = sync<Ok>(BlockUser(userId))
suspend infix fun TdAbsHandler.blockUserOrNull(userId: Int) = syncOrNull<Ok>(BlockUser(userId))
fun TdAbsHandler.blockUser(userId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(BlockUser(userId), 1, block)


suspend fun TdAbsHandler.answerShippingQuery(shippingQueryId: Long, shippingOptions: Array<ShippingOption>, errorMessage: String) = sync<Ok>(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage))
suspend fun TdAbsHandler.answerShippingQueryOrNull(shippingQueryId: Long, shippingOptions: Array<ShippingOption>, errorMessage: String) = syncOrNull<Ok>(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage))
fun TdAbsHandler.answerShippingQuery(shippingQueryId: Long, shippingOptions: Array<ShippingOption>, errorMessage: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage), 1, block)


suspend fun TdAbsHandler.answerPreCheckoutQuery(preCheckoutQueryId: Long, errorMessage: String) = sync<Ok>(AnswerPreCheckoutQuery(preCheckoutQueryId, errorMessage))
suspend fun TdAbsHandler.answerPreCheckoutQueryOrNull(preCheckoutQueryId: Long, errorMessage: String) = syncOrNull<Ok>(AnswerPreCheckoutQuery(preCheckoutQueryId, errorMessage))
fun TdAbsHandler.answerPreCheckoutQuery(preCheckoutQueryId: Long, errorMessage: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AnswerPreCheckoutQuery(preCheckoutQueryId, errorMessage), 1, block)


suspend fun TdAbsHandler.answerInlineQuery(inlineQueryId: Long, isPersonal: Boolean, results: Array<InputInlineQueryResult>, cacheTime: Int, nextOffset: String, switchPmText: String, switchPmParameter: String) = sync<Ok>(AnswerInlineQuery(inlineQueryId, isPersonal, results, cacheTime, nextOffset, switchPmText, switchPmParameter))
suspend fun TdAbsHandler.answerInlineQueryOrNull(inlineQueryId: Long, isPersonal: Boolean, results: Array<InputInlineQueryResult>, cacheTime: Int, nextOffset: String, switchPmText: String, switchPmParameter: String) = syncOrNull<Ok>(AnswerInlineQuery(inlineQueryId, isPersonal, results, cacheTime, nextOffset, switchPmText, switchPmParameter))
fun TdAbsHandler.answerInlineQuery(inlineQueryId: Long, isPersonal: Boolean, results: Array<InputInlineQueryResult>, cacheTime: Int, nextOffset: String, switchPmText: String, switchPmParameter: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AnswerInlineQuery(inlineQueryId, isPersonal, results, cacheTime, nextOffset, switchPmText, switchPmParameter), 1, block)


suspend fun TdAbsHandler.answerCustomQuery(customQueryId: Long, data: String) = sync<Ok>(AnswerCustomQuery(customQueryId, data))
suspend fun TdAbsHandler.answerCustomQueryOrNull(customQueryId: Long, data: String) = syncOrNull<Ok>(AnswerCustomQuery(customQueryId, data))
fun TdAbsHandler.answerCustomQuery(customQueryId: Long, data: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AnswerCustomQuery(customQueryId, data), 1, block)


suspend fun TdAbsHandler.answerCallbackQuery(callbackQueryId: Long, text: String, showAlert: Boolean, url: String, cacheTime: Int) = sync<Ok>(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime))
suspend fun TdAbsHandler.answerCallbackQueryOrNull(callbackQueryId: Long, text: String, showAlert: Boolean, url: String, cacheTime: Int) = syncOrNull<Ok>(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime))
fun TdAbsHandler.answerCallbackQuery(callbackQueryId: Long, text: String, showAlert: Boolean, url: String, cacheTime: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime), 1, block)


suspend fun TdAbsHandler.addStickerToSet(userId: Int, name: String, sticker: InputSticker) = sync<Ok>(AddStickerToSet(userId, name, sticker))
suspend fun TdAbsHandler.addStickerToSetOrNull(userId: Int, name: String, sticker: InputSticker) = syncOrNull<Ok>(AddStickerToSet(userId, name, sticker))
fun TdAbsHandler.addStickerToSet(userId: Int, name: String, sticker: InputSticker, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddStickerToSet(userId, name, sticker), 1, block)


suspend infix fun TdAbsHandler.addSavedAnimation(animation: InputFile) = sync<Ok>(AddSavedAnimation(animation))
suspend infix fun TdAbsHandler.addSavedAnimationOrNull(animation: InputFile) = syncOrNull<Ok>(AddSavedAnimation(animation))
fun TdAbsHandler.addSavedAnimation(animation: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddSavedAnimation(animation), 1, block)


suspend infix fun TdAbsHandler.addRecentlyFoundChat(chatId: Number) = sync<Ok>(AddRecentlyFoundChat(chatId.toLong()))
suspend infix fun TdAbsHandler.addRecentlyFoundChatOrNull(chatId: Number) = syncOrNull<Ok>(AddRecentlyFoundChat(chatId.toLong()))
fun TdAbsHandler.addRecentlyFoundChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddRecentlyFoundChat(chatId.toLong()), 1, block)


suspend fun TdAbsHandler.addRecentSticker(isAttached: Boolean, sticker: InputFile) = sync<Ok>(AddRecentSticker(isAttached, sticker))
suspend fun TdAbsHandler.addRecentStickerOrNull(isAttached: Boolean, sticker: InputFile) = syncOrNull<Ok>(AddRecentSticker(isAttached, sticker))
fun TdAbsHandler.addRecentSticker(isAttached: Boolean, sticker: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddRecentSticker(isAttached, sticker), 1, block)


suspend fun TdAbsHandler.addProxy(server: String, port: Int, enable: Boolean, type: ProxyType) = sync<Ok>(AddProxy(server, port, enable, type))
suspend fun TdAbsHandler.addProxyOrNull(server: String, port: Int, enable: Boolean, type: ProxyType) = syncOrNull<Ok>(AddProxy(server, port, enable, type))
fun TdAbsHandler.addProxy(server: String, port: Int, enable: Boolean, type: ProxyType, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddProxy(server, port, enable, type), 1, block)


suspend infix fun TdAbsHandler.addNetworkStatistics(entry: NetworkStatisticsEntry) = sync<Ok>(AddNetworkStatistics(entry))
suspend infix fun TdAbsHandler.addNetworkStatisticsOrNull(entry: NetworkStatisticsEntry) = syncOrNull<Ok>(AddNetworkStatistics(entry))
fun TdAbsHandler.addNetworkStatistics(entry: NetworkStatisticsEntry, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddNetworkStatistics(entry), 1, block)


suspend fun TdAbsHandler.addLogMessage(verbosityLevel: Int, text: String) = sync<Ok>(AddLogMessage(verbosityLevel, text))
suspend fun TdAbsHandler.addLogMessageOrNull(verbosityLevel: Int, text: String) = syncOrNull<Ok>(AddLogMessage(verbosityLevel, text))
fun TdAbsHandler.addLogMessage(verbosityLevel: Int, text: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddLogMessage(verbosityLevel, text), 1, block)


suspend fun TdAbsHandler.addLocalMessage(chatId: Number, senderUserId: Int, replyToMessageId: Long, disableNotification: Boolean, inputMessageContent: InputMessageContent) = sync<Ok>(AddLocalMessage(chatId.toLong(), senderUserId, replyToMessageId, disableNotification, inputMessageContent))
suspend fun TdAbsHandler.addLocalMessageOrNull(chatId: Number, senderUserId: Int, replyToMessageId: Long, disableNotification: Boolean, inputMessageContent: InputMessageContent) = syncOrNull<Ok>(AddLocalMessage(chatId.toLong(), senderUserId, replyToMessageId, disableNotification, inputMessageContent))
fun TdAbsHandler.addLocalMessage(chatId: Number, senderUserId: Int, replyToMessageId: Long, disableNotification: Boolean, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddLocalMessage(chatId.toLong(), senderUserId, replyToMessageId, disableNotification, inputMessageContent), 1, block)


suspend infix fun TdAbsHandler.addFavoriteSticker(sticker: InputFile) = sync<Ok>(AddFavoriteSticker(sticker))
suspend infix fun TdAbsHandler.addFavoriteStickerOrNull(sticker: InputFile) = syncOrNull<Ok>(AddFavoriteSticker(sticker))
fun TdAbsHandler.addFavoriteSticker(sticker: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddFavoriteSticker(sticker), 1, block)


suspend infix fun TdAbsHandler.addCustomServerLanguagePack(languagePackId: String) = sync<Ok>(AddCustomServerLanguagePack(languagePackId))
suspend infix fun TdAbsHandler.addCustomServerLanguagePackOrNull(languagePackId: String) = syncOrNull<Ok>(AddCustomServerLanguagePack(languagePackId))
fun TdAbsHandler.addCustomServerLanguagePack(languagePackId: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddCustomServerLanguagePack(languagePackId), 1, block)


suspend fun TdAbsHandler.addContact(contact: Contact, sharePhoneNumber: Boolean) = sync<Ok>(AddContact(contact, sharePhoneNumber))
suspend fun TdAbsHandler.addContactOrNull(contact: Contact, sharePhoneNumber: Boolean) = syncOrNull<Ok>(AddContact(contact, sharePhoneNumber))
fun TdAbsHandler.addContact(contact: Contact, sharePhoneNumber: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddContact(contact, sharePhoneNumber), 1, block)


suspend fun TdAbsHandler.addChatMembers(chatId: Number, userIds: IntArray) = sync<Ok>(AddChatMembers(chatId.toLong(), userIds))
suspend fun TdAbsHandler.addChatMembersOrNull(chatId: Number, userIds: IntArray) = syncOrNull<Ok>(AddChatMembers(chatId.toLong(), userIds))
fun TdAbsHandler.addChatMembers(chatId: Number, userIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddChatMembers(chatId.toLong(), userIds), 1, block)


suspend fun TdAbsHandler.addChatMember(chatId: Number, userId: Int, forwardLimit: Int) = sync<Ok>(AddChatMember(chatId.toLong(), userId, forwardLimit))
suspend fun TdAbsHandler.addChatMemberOrNull(chatId: Number, userId: Int, forwardLimit: Int) = syncOrNull<Ok>(AddChatMember(chatId.toLong(), userId, forwardLimit))
fun TdAbsHandler.addChatMember(chatId: Number, userId: Int, forwardLimit: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddChatMember(chatId.toLong(), userId, forwardLimit), 1, block)


suspend infix fun TdAbsHandler.acceptTermsOfService(termsOfServiceId: String) = sync<Ok>(AcceptTermsOfService(termsOfServiceId))
suspend infix fun TdAbsHandler.acceptTermsOfServiceOrNull(termsOfServiceId: String) = syncOrNull<Ok>(AcceptTermsOfService(termsOfServiceId))
fun TdAbsHandler.acceptTermsOfService(termsOfServiceId: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AcceptTermsOfService(termsOfServiceId), 1, block)


suspend fun TdAbsHandler.acceptCall(callId: Int, protocol: CallProtocol) = sync<Ok>(AcceptCall(callId, protocol))
suspend fun TdAbsHandler.acceptCallOrNull(callId: Int, protocol: CallProtocol) = syncOrNull<Ok>(AcceptCall(callId, protocol))
fun TdAbsHandler.acceptCall(callId: Int, protocol: CallProtocol, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AcceptCall(callId, protocol), 1, block)
