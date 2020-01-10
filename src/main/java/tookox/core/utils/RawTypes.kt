@file:Suppress("unused")
package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import td.TdApi.*
import tookox.core.*
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

suspend fun TdAbsHandler.validateOrderInfo(chatId: Number, messageId: Long, orderInfo: OrderInfo, allowSave: Boolean) = sync<ValidatedOrderInfo>(ValidateOrderInfo(chatId.toLong(), messageId, orderInfo, allowSave))
suspend fun TdAbsHandler.validateOrderInfoOrNull(chatId: Number, messageId: Long, orderInfo: OrderInfo, allowSave: Boolean) = syncOrNull<ValidatedOrderInfo>(ValidateOrderInfo(chatId.toLong(), messageId, orderInfo, allowSave))
fun TdAbsHandler.validateOrderInfo(chatId: Number, messageId: Long, orderInfo: OrderInfo, allowSave: Boolean, block: (suspend CoroutineScope.(ValidatedOrderInfo) -> Unit)) = send(ValidateOrderInfo(chatId.toLong(), messageId, orderInfo, allowSave), 1, block)

suspend fun TdAbsHandler.uploadStickerFile(userId: Int, pngSticker: InputFile) = sync<File>(UploadStickerFile(userId, pngSticker))
suspend fun TdAbsHandler.uploadStickerFileOrNull(userId: Int, pngSticker: InputFile) = syncOrNull<File>(UploadStickerFile(userId, pngSticker))
fun TdAbsHandler.uploadStickerFile(userId: Int, pngSticker: InputFile, block: (suspend CoroutineScope.(File) -> Unit)) = send(UploadStickerFile(userId, pngSticker), 1, block)

suspend fun TdAbsHandler.uploadFile(file: InputFile, fileType: FileType, priority: Int) = sync<File>(UploadFile(file, fileType, priority))
suspend fun TdAbsHandler.uploadFileOrNull(file: InputFile, fileType: FileType, priority: Int) = syncOrNull<File>(UploadFile(file, fileType, priority))
fun TdAbsHandler.uploadFile(file: InputFile, fileType: FileType, priority: Int, block: (suspend CoroutineScope.(File) -> Unit)) = send(UploadFile(file, fileType, priority), 1, block)

suspend infix fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChat(chatId: Number) = sync<Chat>(UpgradeBasicGroupChatToSupergroupChat(chatId.toLong()))
suspend infix fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChatOrNull(chatId: Number) = syncOrNull<Chat>(UpgradeBasicGroupChatToSupergroupChat(chatId.toLong()))
fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChat(chatId: Number, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(UpgradeBasicGroupChatToSupergroupChat(chatId.toLong()), 1, block)

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

suspend fun TdAbsHandler.testUseUpdate() = sync<Update>(TestUseUpdate())
suspend fun TdAbsHandler.testUseUpdateOrNull() = syncOrNull<Update>(TestUseUpdate())
fun TdAbsHandler.testUseUpdate(block: (suspend CoroutineScope.(Update) -> Unit)) = send(TestUseUpdate(), 1, block)

suspend infix fun TdAbsHandler.testSquareInt(x: Int) = sync<TestInt>(TestSquareInt(x))
suspend infix fun TdAbsHandler.testSquareIntOrNull(x: Int) = syncOrNull<TestInt>(TestSquareInt(x))
fun TdAbsHandler.testSquareInt(x: Int, block: (suspend CoroutineScope.(TestInt) -> Unit)) = send(TestSquareInt(x), 1, block)

infix fun TdAbsHandler.testReturnError(error: Error) = syncRaw<Error>(TestReturnError(error))

suspend fun TdAbsHandler.testProxy(server: String, port: Int, type: ProxyType, dcId: Int, timeout: Double) = sync<Ok>(TestProxy(server, port, type, dcId, timeout))
suspend fun TdAbsHandler.testProxyOrNull(server: String, port: Int, type: ProxyType, dcId: Int, timeout: Double) = syncOrNull<Ok>(TestProxy(server, port, type, dcId, timeout))
fun TdAbsHandler.testProxy(server: String, port: Int, type: ProxyType, dcId: Int, timeout: Double, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestProxy(server, port, type, dcId, timeout), 1, block)

suspend fun TdAbsHandler.testNetwork() = sync<Ok>(TestNetwork())
suspend fun TdAbsHandler.testNetworkOrNull() = syncOrNull<Ok>(TestNetwork())
fun TdAbsHandler.testNetwork(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestNetwork(), 1, block)

suspend fun TdAbsHandler.testGetDifference() = sync<Ok>(TestGetDifference())
suspend fun TdAbsHandler.testGetDifferenceOrNull() = syncOrNull<Ok>(TestGetDifference())
fun TdAbsHandler.testGetDifference(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestGetDifference(), 1, block)

suspend infix fun TdAbsHandler.testCallVectorStringObject(x: Array<TestString>) = sync<TestVectorStringObject>(TestCallVectorStringObject(x))
suspend infix fun TdAbsHandler.testCallVectorStringObjectOrNull(x: Array<TestString>) = syncOrNull<TestVectorStringObject>(TestCallVectorStringObject(x))
fun TdAbsHandler.testCallVectorStringObject(x: Array<TestString>, block: (suspend CoroutineScope.(TestVectorStringObject) -> Unit)) = send(TestCallVectorStringObject(x), 1, block)

suspend infix fun TdAbsHandler.testCallVectorString(x: Array<String>) = sync<TestVectorString>(TestCallVectorString(x))
suspend infix fun TdAbsHandler.testCallVectorStringOrNull(x: Array<String>) = syncOrNull<TestVectorString>(TestCallVectorString(x))
fun TdAbsHandler.testCallVectorString(x: Array<String>, block: (suspend CoroutineScope.(TestVectorString) -> Unit)) = send(TestCallVectorString(x), 1, block)

suspend infix fun TdAbsHandler.testCallVectorIntObject(x: Array<TestInt>) = sync<TestVectorIntObject>(TestCallVectorIntObject(x))
suspend infix fun TdAbsHandler.testCallVectorIntObjectOrNull(x: Array<TestInt>) = syncOrNull<TestVectorIntObject>(TestCallVectorIntObject(x))
fun TdAbsHandler.testCallVectorIntObject(x: Array<TestInt>, block: (suspend CoroutineScope.(TestVectorIntObject) -> Unit)) = send(TestCallVectorIntObject(x), 1, block)

suspend infix fun TdAbsHandler.testCallVectorInt(x: IntArray) = sync<TestVectorInt>(TestCallVectorInt(x))
suspend infix fun TdAbsHandler.testCallVectorIntOrNull(x: IntArray) = syncOrNull<TestVectorInt>(TestCallVectorInt(x))
fun TdAbsHandler.testCallVectorInt(x: IntArray, block: (suspend CoroutineScope.(TestVectorInt) -> Unit)) = send(TestCallVectorInt(x), 1, block)

suspend infix fun TdAbsHandler.testCallString(x: String) = sync<TestString>(TestCallString(x))
suspend infix fun TdAbsHandler.testCallStringOrNull(x: String) = syncOrNull<TestString>(TestCallString(x))
fun TdAbsHandler.testCallString(x: String, block: (suspend CoroutineScope.(TestString) -> Unit)) = send(TestCallString(x), 1, block)

suspend fun TdAbsHandler.testCallEmpty() = sync<Ok>(TestCallEmpty())
suspend fun TdAbsHandler.testCallEmptyOrNull() = syncOrNull<Ok>(TestCallEmpty())
fun TdAbsHandler.testCallEmpty(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(TestCallEmpty(), 1, block)

suspend infix fun TdAbsHandler.testCallBytes(x: ByteArray) = sync<TestBytes>(TestCallBytes(x))
suspend infix fun TdAbsHandler.testCallBytesOrNull(x: ByteArray) = syncOrNull<TestBytes>(TestCallBytes(x))
fun TdAbsHandler.testCallBytes(x: ByteArray, block: (suspend CoroutineScope.(TestBytes) -> Unit)) = send(TestCallBytes(x), 1, block)

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

suspend fun TdAbsHandler.setRecoveryEmailAddress(password: String, newRecoveryEmailAddress: String) = sync<PasswordState>(SetRecoveryEmailAddress(password, newRecoveryEmailAddress))
suspend fun TdAbsHandler.setRecoveryEmailAddressOrNull(password: String, newRecoveryEmailAddress: String) = syncOrNull<PasswordState>(SetRecoveryEmailAddress(password, newRecoveryEmailAddress))
fun TdAbsHandler.setRecoveryEmailAddress(password: String, newRecoveryEmailAddress: String, block: (suspend CoroutineScope.(PasswordState) -> Unit)) = send(SetRecoveryEmailAddress(password, newRecoveryEmailAddress), 1, block)

suspend infix fun TdAbsHandler.setProfilePhoto(photo: InputFile) = sync<Ok>(SetProfilePhoto(photo))
suspend infix fun TdAbsHandler.setProfilePhotoOrNull(photo: InputFile) = syncOrNull<Ok>(SetProfilePhoto(photo))
fun TdAbsHandler.setProfilePhoto(photo: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetProfilePhoto(photo), 1, block)

suspend fun TdAbsHandler.setPollAnswer(chatId: Number, messageId: Long, optionIds: IntArray) = sync<Ok>(SetPollAnswer(chatId.toLong(), messageId, optionIds))
suspend fun TdAbsHandler.setPollAnswerOrNull(chatId: Number, messageId: Long, optionIds: IntArray) = syncOrNull<Ok>(SetPollAnswer(chatId.toLong(), messageId, optionIds))
fun TdAbsHandler.setPollAnswer(chatId: Number, messageId: Long, optionIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPollAnswer(chatId.toLong(), messageId, optionIds), 1, block)

suspend fun TdAbsHandler.setPinnedChats(chatList: ChatList, chatIds: LongArray) = sync<Ok>(SetPinnedChats(chatList, chatIds))
suspend fun TdAbsHandler.setPinnedChatsOrNull(chatList: ChatList, chatIds: LongArray) = syncOrNull<Ok>(SetPinnedChats(chatList, chatIds))
fun TdAbsHandler.setPinnedChats(chatList: ChatList, chatIds: LongArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPinnedChats(chatList, chatIds), 1, block)

suspend fun TdAbsHandler.setPassword(oldPassword: String, newPassword: String, newHint: String, setRecoveryEmailAddress: Boolean, newRecoveryEmailAddress: String) = sync<PasswordState>(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress))
suspend fun TdAbsHandler.setPasswordOrNull(oldPassword: String, newPassword: String, newHint: String, setRecoveryEmailAddress: Boolean, newRecoveryEmailAddress: String) = syncOrNull<PasswordState>(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress))
fun TdAbsHandler.setPassword(oldPassword: String, newPassword: String, newHint: String, setRecoveryEmailAddress: Boolean, newRecoveryEmailAddress: String, block: (suspend CoroutineScope.(PasswordState) -> Unit)) = send(SetPassword(oldPassword, newPassword, newHint, setRecoveryEmailAddress, newRecoveryEmailAddress), 1, block)

suspend fun TdAbsHandler.setPassportElementErrors(userId: Int, errors: Array<InputPassportElementError>) = sync<Ok>(SetPassportElementErrors(userId, errors))
suspend fun TdAbsHandler.setPassportElementErrorsOrNull(userId: Int, errors: Array<InputPassportElementError>) = syncOrNull<Ok>(SetPassportElementErrors(userId, errors))
fun TdAbsHandler.setPassportElementErrors(userId: Int, errors: Array<InputPassportElementError>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetPassportElementErrors(userId, errors), 1, block)

suspend fun TdAbsHandler.setPassportElement(element: InputPassportElement, password: String) = sync<PassportElement>(SetPassportElement(element, password))
suspend fun TdAbsHandler.setPassportElementOrNull(element: InputPassportElement, password: String) = syncOrNull<PassportElement>(SetPassportElement(element, password))
fun TdAbsHandler.setPassportElement(element: InputPassportElement, password: String, block: (suspend CoroutineScope.(PassportElement) -> Unit)) = send(SetPassportElement(element, password), 1, block)

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

infix fun TdAbsHandler.setLogStream(logStream: LogStream) = syncRaw<Ok>(SetLogStream(logStream))

suspend fun TdAbsHandler.setInlineGameScore(inlineMessageId: String, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = sync<Ok>(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force))
suspend fun TdAbsHandler.setInlineGameScoreOrNull(inlineMessageId: String, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = syncOrNull<Ok>(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force))
fun TdAbsHandler.setInlineGameScore(inlineMessageId: String, editMessage: Boolean, userId: Int, score: Int, force: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SetInlineGameScore(inlineMessageId, editMessage, userId, score, force), 1, block)

suspend fun TdAbsHandler.setGameScore(chatId: Number, messageId: Long, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = sync<Message>(SetGameScore(chatId.toLong(), messageId, editMessage, userId, score, force))
suspend fun TdAbsHandler.setGameScoreOrNull(chatId: Number, messageId: Long, editMessage: Boolean, userId: Int, score: Int, force: Boolean) = syncOrNull<Message>(SetGameScore(chatId.toLong(), messageId, editMessage, userId, score, force))
fun TdAbsHandler.setGameScore(chatId: Number, messageId: Long, editMessage: Boolean, userId: Int, score: Int, force: Boolean, block: (suspend CoroutineScope.(Message) -> Unit)) = send(SetGameScore(chatId.toLong(), messageId, editMessage, userId, score, force), 1, block)

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

suspend fun TdAbsHandler.setBackground(background: InputBackground, type: BackgroundType, forDarkTheme: Boolean) = sync<Background>(SetBackground(background, type, forDarkTheme))
suspend fun TdAbsHandler.setBackgroundOrNull(background: InputBackground, type: BackgroundType, forDarkTheme: Boolean) = syncOrNull<Background>(SetBackground(background, type, forDarkTheme))
fun TdAbsHandler.setBackground(background: InputBackground, type: BackgroundType, forDarkTheme: Boolean, block: (suspend CoroutineScope.(Background) -> Unit)) = send(SetBackground(background, type, forDarkTheme), 1, block)

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

suspend infix fun TdAbsHandler.sendTonLiteServerRequest(request: ByteArray) = sync<TonLiteServerResponse>(SendTonLiteServerRequest(request))
suspend infix fun TdAbsHandler.sendTonLiteServerRequestOrNull(request: ByteArray) = syncOrNull<TonLiteServerResponse>(SendTonLiteServerRequest(request))
fun TdAbsHandler.sendTonLiteServerRequest(request: ByteArray, block: (suspend CoroutineScope.(TonLiteServerResponse) -> Unit)) = send(SendTonLiteServerRequest(request), 1, block)

suspend fun TdAbsHandler.sendPhoneNumberVerificationCode(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = sync<AuthenticationCodeInfo>(SendPhoneNumberVerificationCode(phoneNumber, settings))
suspend fun TdAbsHandler.sendPhoneNumberVerificationCodeOrNull(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = syncOrNull<AuthenticationCodeInfo>(SendPhoneNumberVerificationCode(phoneNumber, settings))
fun TdAbsHandler.sendPhoneNumberVerificationCode(phoneNumber: String, settings: PhoneNumberAuthenticationSettings, block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)) = send(SendPhoneNumberVerificationCode(phoneNumber, settings), 1, block)

suspend fun TdAbsHandler.sendPhoneNumberConfirmationCode(hash: String, phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = sync<AuthenticationCodeInfo>(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings))
suspend fun TdAbsHandler.sendPhoneNumberConfirmationCodeOrNull(hash: String, phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = syncOrNull<AuthenticationCodeInfo>(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings))
fun TdAbsHandler.sendPhoneNumberConfirmationCode(hash: String, phoneNumber: String, settings: PhoneNumberAuthenticationSettings, block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)) = send(SendPhoneNumberConfirmationCode(hash, phoneNumber, settings), 1, block)

suspend fun TdAbsHandler.sendPaymentForm(chatId: Number, messageId: Long, orderInfoId: String, shippingOptionId: String, credentials: InputCredentials) = sync<PaymentResult>(SendPaymentForm(chatId.toLong(), messageId, orderInfoId, shippingOptionId, credentials))
suspend fun TdAbsHandler.sendPaymentFormOrNull(chatId: Number, messageId: Long, orderInfoId: String, shippingOptionId: String, credentials: InputCredentials) = syncOrNull<PaymentResult>(SendPaymentForm(chatId.toLong(), messageId, orderInfoId, shippingOptionId, credentials))
fun TdAbsHandler.sendPaymentForm(chatId: Number, messageId: Long, orderInfoId: String, shippingOptionId: String, credentials: InputCredentials, block: (suspend CoroutineScope.(PaymentResult) -> Unit)) = send(SendPaymentForm(chatId.toLong(), messageId, orderInfoId, shippingOptionId, credentials), 1, block)

suspend fun TdAbsHandler.sendPassportAuthorizationForm(autorizationFormId: Int, types: Array<PassportElementType>) = sync<Ok>(SendPassportAuthorizationForm(autorizationFormId, types))
suspend fun TdAbsHandler.sendPassportAuthorizationFormOrNull(autorizationFormId: Int, types: Array<PassportElementType>) = syncOrNull<Ok>(SendPassportAuthorizationForm(autorizationFormId, types))
fun TdAbsHandler.sendPassportAuthorizationForm(autorizationFormId: Int, types: Array<PassportElementType>, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(SendPassportAuthorizationForm(autorizationFormId, types), 1, block)

suspend fun TdAbsHandler.sendMessageAlbum(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, inputMessageContents: Array<InputMessageContent>) = sync<Messages>(SendMessageAlbum(chatId.toLong(), replyToMessageId, options, inputMessageContents))
suspend fun TdAbsHandler.sendMessageAlbumOrNull(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, inputMessageContents: Array<InputMessageContent>) = syncOrNull<Messages>(SendMessageAlbum(chatId.toLong(), replyToMessageId, options, inputMessageContents))
fun TdAbsHandler.sendMessageAlbum(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, inputMessageContents: Array<InputMessageContent>, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(SendMessageAlbum(chatId.toLong(), replyToMessageId, options, inputMessageContents), 1, block)

suspend fun TdAbsHandler.sendMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<SendMessageOptions>(SendMessage(chatId.toLong(), replyToMessageId, options, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.sendMessageOrNull(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<SendMessageOptions>(SendMessage(chatId.toLong(), replyToMessageId, options, replyMarkup, inputMessageContent))
fun TdAbsHandler.sendMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(SendMessageOptions) -> Unit)) = send(SendMessage(chatId.toLong(), replyToMessageId, options, replyMarkup, inputMessageContent), 1, block)

suspend fun TdAbsHandler.sendInlineQueryResultMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, queryId: Long, resultId: String, hideViaBot: Boolean) = sync<Message>(SendInlineQueryResultMessage(chatId.toLong(), replyToMessageId, options, queryId, resultId, hideViaBot))
suspend fun TdAbsHandler.sendInlineQueryResultMessageOrNull(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, queryId: Long, resultId: String, hideViaBot: Boolean) = syncOrNull<Message>(SendInlineQueryResultMessage(chatId.toLong(), replyToMessageId, options, queryId, resultId, hideViaBot))
fun TdAbsHandler.sendInlineQueryResultMessage(chatId: Number, replyToMessageId: Long, options: SendMessageOptions, queryId: Long, resultId: String, hideViaBot: Boolean, block: (suspend CoroutineScope.(Message) -> Unit)) = send(SendInlineQueryResultMessage(chatId.toLong(), replyToMessageId, options, queryId, resultId, hideViaBot), 1, block)

suspend infix fun TdAbsHandler.sendEmailAddressVerificationCode(emailAddress: String) = sync<EmailAddressAuthenticationCodeInfo>(SendEmailAddressVerificationCode(emailAddress))
suspend infix fun TdAbsHandler.sendEmailAddressVerificationCodeOrNull(emailAddress: String) = syncOrNull<EmailAddressAuthenticationCodeInfo>(SendEmailAddressVerificationCode(emailAddress))
fun TdAbsHandler.sendEmailAddressVerificationCode(emailAddress: String, block: (suspend CoroutineScope.(EmailAddressAuthenticationCodeInfo) -> Unit)) = send(SendEmailAddressVerificationCode(emailAddress), 1, block)

suspend fun TdAbsHandler.sendCustomRequest(method: String, parameters: String) = sync<CustomRequestResult>(SendCustomRequest(method, parameters))
suspend fun TdAbsHandler.sendCustomRequestOrNull(method: String, parameters: String) = syncOrNull<CustomRequestResult>(SendCustomRequest(method, parameters))
fun TdAbsHandler.sendCustomRequest(method: String, parameters: String, block: (suspend CoroutineScope.(CustomRequestResult) -> Unit)) = send(SendCustomRequest(method, parameters), 1, block)

suspend fun TdAbsHandler.sendChatSetTtlMessage(chatId: Number, ttl: Int) = sync<Message>(SendChatSetTtlMessage(chatId.toLong(), ttl))
suspend fun TdAbsHandler.sendChatSetTtlMessageOrNull(chatId: Number, ttl: Int) = syncOrNull<Message>(SendChatSetTtlMessage(chatId.toLong(), ttl))
fun TdAbsHandler.sendChatSetTtlMessage(chatId: Number, ttl: Int, block: (suspend CoroutineScope.(Message) -> Unit)) = send(SendChatSetTtlMessage(chatId.toLong(), ttl), 1, block)

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

suspend fun TdAbsHandler.sendBotStartMessage(botUserId: Int, chatId: Number, parameter: String) = sync<Message>(SendBotStartMessage(botUserId, chatId.toLong(), parameter))
suspend fun TdAbsHandler.sendBotStartMessageOrNull(botUserId: Int, chatId: Number, parameter: String) = syncOrNull<Message>(SendBotStartMessage(botUserId, chatId.toLong(), parameter))
fun TdAbsHandler.sendBotStartMessage(botUserId: Int, chatId: Number, parameter: String, block: (suspend CoroutineScope.(Message) -> Unit)) = send(SendBotStartMessage(botUserId, chatId.toLong(), parameter), 1, block)

suspend fun TdAbsHandler.searchStickers(emoji: String, limit: Int) = sync<Stickers>(SearchStickers(emoji, limit))
suspend fun TdAbsHandler.searchStickersOrNull(emoji: String, limit: Int) = syncOrNull<Stickers>(SearchStickers(emoji, limit))
fun TdAbsHandler.searchStickers(emoji: String, limit: Int, block: (suspend CoroutineScope.(Stickers) -> Unit)) = send(SearchStickers(emoji, limit), 1, block)

suspend infix fun TdAbsHandler.searchStickerSets(query: String) = sync<StickerSets>(SearchStickerSets(query))
suspend infix fun TdAbsHandler.searchStickerSetsOrNull(query: String) = syncOrNull<StickerSets>(SearchStickerSets(query))
fun TdAbsHandler.searchStickerSets(query: String, block: (suspend CoroutineScope.(StickerSets) -> Unit)) = send(SearchStickerSets(query), 1, block)

suspend infix fun TdAbsHandler.searchStickerSet(name: String) = sync<StickerSet>(SearchStickerSet(name))
suspend infix fun TdAbsHandler.searchStickerSetOrNull(name: String) = syncOrNull<StickerSet>(SearchStickerSet(name))
fun TdAbsHandler.searchStickerSet(name: String, block: (suspend CoroutineScope.(StickerSet) -> Unit)) = send(SearchStickerSet(name), 1, block)

suspend fun TdAbsHandler.searchSecretMessages(chatId: Number, query: String, fromSearchId: Long, limit: Int, filter: SearchMessagesFilter) = sync<FoundMessages>(SearchSecretMessages(chatId.toLong(), query, fromSearchId, limit, filter))
suspend fun TdAbsHandler.searchSecretMessagesOrNull(chatId: Number, query: String, fromSearchId: Long, limit: Int, filter: SearchMessagesFilter) = syncOrNull<FoundMessages>(SearchSecretMessages(chatId.toLong(), query, fromSearchId, limit, filter))
fun TdAbsHandler.searchSecretMessages(chatId: Number, query: String, fromSearchId: Long, limit: Int, filter: SearchMessagesFilter, block: (suspend CoroutineScope.(FoundMessages) -> Unit)) = send(SearchSecretMessages(chatId.toLong(), query, fromSearchId, limit, filter), 1, block)

suspend infix fun TdAbsHandler.searchPublicChats(query: String) = sync<Chats>(SearchPublicChats(query))
suspend infix fun TdAbsHandler.searchPublicChatsOrNull(query: String) = syncOrNull<Chats>(SearchPublicChats(query))
fun TdAbsHandler.searchPublicChats(query: String, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(SearchPublicChats(query), 1, block)

suspend infix fun TdAbsHandler.searchPublicChat(username: String) = sync<Chat>(SearchPublicChat(username))
suspend infix fun TdAbsHandler.searchPublicChatOrNull(username: String) = syncOrNull<Chat>(SearchPublicChat(username))
fun TdAbsHandler.searchPublicChat(username: String, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(SearchPublicChat(username), 1, block)

suspend fun TdAbsHandler.searchMessages(chatList: ChatList, query: String, offsetDate: Int, offsetChatId: Long, offsetMessageId: Long, limit: Int) = sync<SearchMessagesFilter>(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit))
suspend fun TdAbsHandler.searchMessagesOrNull(chatList: ChatList, query: String, offsetDate: Int, offsetChatId: Long, offsetMessageId: Long, limit: Int) = syncOrNull<SearchMessagesFilter>(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit))
fun TdAbsHandler.searchMessages(chatList: ChatList, query: String, offsetDate: Int, offsetChatId: Long, offsetMessageId: Long, limit: Int, block: (suspend CoroutineScope.(SearchMessagesFilter) -> Unit)) = send(SearchMessages(chatList, query, offsetDate, offsetChatId, offsetMessageId, limit), 1, block)

suspend fun TdAbsHandler.searchInstalledStickerSets(isMasks: Boolean, query: String, limit: Int) = sync<StickerSets>(SearchInstalledStickerSets(isMasks, query, limit))
suspend fun TdAbsHandler.searchInstalledStickerSetsOrNull(isMasks: Boolean, query: String, limit: Int) = syncOrNull<StickerSets>(SearchInstalledStickerSets(isMasks, query, limit))
fun TdAbsHandler.searchInstalledStickerSets(isMasks: Boolean, query: String, limit: Int, block: (suspend CoroutineScope.(StickerSets) -> Unit)) = send(SearchInstalledStickerSets(isMasks, query, limit), 1, block)

suspend fun TdAbsHandler.searchHashtags(prefix: String, limit: Int) = sync<Hashtags>(SearchHashtags(prefix, limit))
suspend fun TdAbsHandler.searchHashtagsOrNull(prefix: String, limit: Int) = syncOrNull<Hashtags>(SearchHashtags(prefix, limit))
fun TdAbsHandler.searchHashtags(prefix: String, limit: Int, block: (suspend CoroutineScope.(Hashtags) -> Unit)) = send(SearchHashtags(prefix, limit), 1, block)

suspend fun TdAbsHandler.searchEmojis(text: String, exactMatch: Boolean) = sync<Emojis>(SearchEmojis(text, exactMatch))
suspend fun TdAbsHandler.searchEmojisOrNull(text: String, exactMatch: Boolean) = syncOrNull<Emojis>(SearchEmojis(text, exactMatch))
fun TdAbsHandler.searchEmojis(text: String, exactMatch: Boolean, block: (suspend CoroutineScope.(Emojis) -> Unit)) = send(SearchEmojis(text, exactMatch), 1, block)

suspend fun TdAbsHandler.searchContacts(query: String, limit: Int) = sync<Users>(SearchContacts(query, limit))
suspend fun TdAbsHandler.searchContactsOrNull(query: String, limit: Int) = syncOrNull<Users>(SearchContacts(query, limit))
fun TdAbsHandler.searchContacts(query: String, limit: Int, block: (suspend CoroutineScope.(Users) -> Unit)) = send(SearchContacts(query, limit), 1, block)

suspend fun TdAbsHandler.searchChatsOnServer(query: String, limit: Int) = sync<Chats>(SearchChatsOnServer(query, limit))
suspend fun TdAbsHandler.searchChatsOnServerOrNull(query: String, limit: Int) = syncOrNull<Chats>(SearchChatsOnServer(query, limit))
fun TdAbsHandler.searchChatsOnServer(query: String, limit: Int, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(SearchChatsOnServer(query, limit), 1, block)

suspend infix fun TdAbsHandler.searchChatsNearby(location: Location) = sync<ChatsNearby>(SearchChatsNearby(location))
suspend infix fun TdAbsHandler.searchChatsNearbyOrNull(location: Location) = syncOrNull<ChatsNearby>(SearchChatsNearby(location))
fun TdAbsHandler.searchChatsNearby(location: Location, block: (suspend CoroutineScope.(ChatsNearby) -> Unit)) = send(SearchChatsNearby(location), 1, block)

suspend fun TdAbsHandler.searchChats(query: String, limit: Int) = sync<Chats>(SearchChats(query, limit))
suspend fun TdAbsHandler.searchChatsOrNull(query: String, limit: Int) = syncOrNull<Chats>(SearchChats(query, limit))
fun TdAbsHandler.searchChats(query: String, limit: Int, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(SearchChats(query, limit), 1, block)

suspend fun TdAbsHandler.searchChatRecentLocationMessages(chatId: Number, limit: Int) = sync<Messages>(SearchChatRecentLocationMessages(chatId.toLong(), limit))
suspend fun TdAbsHandler.searchChatRecentLocationMessagesOrNull(chatId: Number, limit: Int) = syncOrNull<Messages>(SearchChatRecentLocationMessages(chatId.toLong(), limit))
fun TdAbsHandler.searchChatRecentLocationMessages(chatId: Number, limit: Int, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(SearchChatRecentLocationMessages(chatId.toLong(), limit), 1, block)

suspend fun TdAbsHandler.searchChatMessages(chatId: Number, query: String, senderUserId: Int, fromMessageId: Long, offset: Int, limit: Int, filter: SearchMessagesFilter) = sync<Messages>(SearchChatMessages(chatId.toLong(), query, senderUserId, fromMessageId, offset, limit, filter))
suspend fun TdAbsHandler.searchChatMessagesOrNull(chatId: Number, query: String, senderUserId: Int, fromMessageId: Long, offset: Int, limit: Int, filter: SearchMessagesFilter) = syncOrNull<Messages>(SearchChatMessages(chatId.toLong(), query, senderUserId, fromMessageId, offset, limit, filter))
fun TdAbsHandler.searchChatMessages(chatId: Number, query: String, senderUserId: Int, fromMessageId: Long, offset: Int, limit: Int, filter: SearchMessagesFilter, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(SearchChatMessages(chatId.toLong(), query, senderUserId, fromMessageId, offset, limit, filter), 1, block)

suspend fun TdAbsHandler.searchChatMembers(chatId: Number, query: String, limit: Int, filter: ChatMembersFilter) = sync<ChatMembers>(SearchChatMembers(chatId.toLong(), query, limit, filter))
suspend fun TdAbsHandler.searchChatMembersOrNull(chatId: Number, query: String, limit: Int, filter: ChatMembersFilter) = syncOrNull<ChatMembers>(SearchChatMembers(chatId.toLong(), query, limit, filter))
fun TdAbsHandler.searchChatMembers(chatId: Number, query: String, limit: Int, filter: ChatMembersFilter, block: (suspend CoroutineScope.(ChatMembers) -> Unit)) = send(SearchChatMembers(chatId.toLong(), query, limit, filter), 1, block)

suspend fun TdAbsHandler.searchCallMessages(fromMessageId: Long, limit: Int, onlyMissed: Boolean) = sync<Messages>(SearchCallMessages(fromMessageId, limit, onlyMissed))
suspend fun TdAbsHandler.searchCallMessagesOrNull(fromMessageId: Long, limit: Int, onlyMissed: Boolean) = syncOrNull<Messages>(SearchCallMessages(fromMessageId, limit, onlyMissed))
fun TdAbsHandler.searchCallMessages(fromMessageId: Long, limit: Int, onlyMissed: Boolean, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(SearchCallMessages(fromMessageId, limit, onlyMissed), 1, block)

suspend infix fun TdAbsHandler.searchBackground(name: String) = sync<Background>(SearchBackground(name))
suspend infix fun TdAbsHandler.searchBackgroundOrNull(name: String) = syncOrNull<Background>(SearchBackground(name))
fun TdAbsHandler.searchBackground(name: String, block: (suspend CoroutineScope.(Background) -> Unit)) = send(SearchBackground(name), 1, block)

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

suspend fun TdAbsHandler.resendRecoveryEmailAddressCode() = sync<PasswordState>(ResendRecoveryEmailAddressCode())
suspend fun TdAbsHandler.resendRecoveryEmailAddressCodeOrNull() = syncOrNull<PasswordState>(ResendRecoveryEmailAddressCode())
fun TdAbsHandler.resendRecoveryEmailAddressCode(block: (suspend CoroutineScope.(PasswordState) -> Unit)) = send(ResendRecoveryEmailAddressCode(), 1, block)

suspend fun TdAbsHandler.resendPhoneNumberVerificationCode() = sync<AuthenticationCodeInfo>(ResendPhoneNumberVerificationCode())
suspend fun TdAbsHandler.resendPhoneNumberVerificationCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(ResendPhoneNumberVerificationCode())
fun TdAbsHandler.resendPhoneNumberVerificationCode(block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)) = send(ResendPhoneNumberVerificationCode(), 1, block)

suspend fun TdAbsHandler.resendPhoneNumberConfirmationCode() = sync<AuthenticationCodeInfo>(ResendPhoneNumberConfirmationCode())
suspend fun TdAbsHandler.resendPhoneNumberConfirmationCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(ResendPhoneNumberConfirmationCode())
fun TdAbsHandler.resendPhoneNumberConfirmationCode(block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)) = send(ResendPhoneNumberConfirmationCode(), 1, block)

suspend fun TdAbsHandler.resendMessages(chatId: Number, messageIds: LongArray) = sync<Messages>(ResendMessages(chatId.toLong(), messageIds))
suspend fun TdAbsHandler.resendMessagesOrNull(chatId: Number, messageIds: LongArray) = syncOrNull<Messages>(ResendMessages(chatId.toLong(), messageIds))
fun TdAbsHandler.resendMessages(chatId: Number, messageIds: LongArray, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(ResendMessages(chatId.toLong(), messageIds), 1, block)

suspend fun TdAbsHandler.resendEmailAddressVerificationCode() = sync<EmailAddressAuthenticationCodeInfo>(ResendEmailAddressVerificationCode())
suspend fun TdAbsHandler.resendEmailAddressVerificationCodeOrNull() = syncOrNull<EmailAddressAuthenticationCodeInfo>(ResendEmailAddressVerificationCode())
fun TdAbsHandler.resendEmailAddressVerificationCode(block: (suspend CoroutineScope.(EmailAddressAuthenticationCodeInfo) -> Unit)) = send(ResendEmailAddressVerificationCode(), 1, block)

suspend fun TdAbsHandler.resendChangePhoneNumberCode() = sync<AuthenticationCodeInfo>(ResendChangePhoneNumberCode())
suspend fun TdAbsHandler.resendChangePhoneNumberCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(ResendChangePhoneNumberCode())
fun TdAbsHandler.resendChangePhoneNumberCode(block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)) = send(ResendChangePhoneNumberCode(), 1, block)

suspend fun TdAbsHandler.resendAuthenticationCode() = sync<Ok>(ResendAuthenticationCode())
suspend fun TdAbsHandler.resendAuthenticationCodeOrNull() = syncOrNull<Ok>(ResendAuthenticationCode())
fun TdAbsHandler.resendAuthenticationCode(block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ResendAuthenticationCode(), 1, block)

suspend infix fun TdAbsHandler.requestQrCodeAuthentication(otherUserIds: IntArray) = sync<Ok>(RequestQrCodeAuthentication(otherUserIds))
suspend infix fun TdAbsHandler.requestQrCodeAuthenticationOrNull(otherUserIds: IntArray) = syncOrNull<Ok>(RequestQrCodeAuthentication(otherUserIds))
fun TdAbsHandler.requestQrCodeAuthentication(otherUserIds: IntArray, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RequestQrCodeAuthentication(otherUserIds), 1, block)

suspend fun TdAbsHandler.requestPasswordRecovery() = sync<EmailAddressAuthenticationCodeInfo>(RequestPasswordRecovery())
suspend fun TdAbsHandler.requestPasswordRecoveryOrNull() = syncOrNull<EmailAddressAuthenticationCodeInfo>(RequestPasswordRecovery())
fun TdAbsHandler.requestPasswordRecovery(block: (suspend CoroutineScope.(EmailAddressAuthenticationCodeInfo) -> Unit)) = send(RequestPasswordRecovery(), 1, block)

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

suspend fun TdAbsHandler.registerDevice(deviceToken: DeviceToken, otherUserIds: IntArray) = sync<PushReceiverId>(RegisterDevice(deviceToken, otherUserIds))
suspend fun TdAbsHandler.registerDeviceOrNull(deviceToken: DeviceToken, otherUserIds: IntArray) = syncOrNull<PushReceiverId>(RegisterDevice(deviceToken, otherUserIds))
fun TdAbsHandler.registerDevice(deviceToken: DeviceToken, otherUserIds: IntArray, block: (suspend CoroutineScope.(PushReceiverId) -> Unit)) = send(RegisterDevice(deviceToken, otherUserIds), 1, block)

suspend infix fun TdAbsHandler.recoverPassword(recoveryCode: String) = sync<PasswordState>(RecoverPassword(recoveryCode))
suspend infix fun TdAbsHandler.recoverPasswordOrNull(recoveryCode: String) = syncOrNull<PasswordState>(RecoverPassword(recoveryCode))
fun TdAbsHandler.recoverPassword(recoveryCode: String, block: (suspend CoroutineScope.(PasswordState) -> Unit)) = send(RecoverPassword(recoveryCode), 1, block)

suspend infix fun TdAbsHandler.recoverAuthenticationPassword(recoveryCode: String) = sync<Ok>(RecoverAuthenticationPassword(recoveryCode))
suspend infix fun TdAbsHandler.recoverAuthenticationPasswordOrNull(recoveryCode: String) = syncOrNull<Ok>(RecoverAuthenticationPassword(recoveryCode))
fun TdAbsHandler.recoverAuthenticationPassword(recoveryCode: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(RecoverAuthenticationPassword(recoveryCode), 1, block)

suspend fun TdAbsHandler.readFilePart(fileId: Int, offset: Int, count: Int) = sync<FilePart>(ReadFilePart(fileId, offset, count))
suspend fun TdAbsHandler.readFilePartOrNull(fileId: Int, offset: Int, count: Int) = syncOrNull<FilePart>(ReadFilePart(fileId, offset, count))
fun TdAbsHandler.readFilePart(fileId: Int, offset: Int, count: Int, block: (suspend CoroutineScope.(FilePart) -> Unit)) = send(ReadFilePart(fileId, offset, count), 1, block)

suspend infix fun TdAbsHandler.readAllChatMentions(chatId: Number) = sync<Ok>(ReadAllChatMentions(chatId.toLong()))
suspend infix fun TdAbsHandler.readAllChatMentionsOrNull(chatId: Number) = syncOrNull<Ok>(ReadAllChatMentions(chatId.toLong()))
fun TdAbsHandler.readAllChatMentions(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ReadAllChatMentions(chatId.toLong()), 1, block)

suspend infix fun TdAbsHandler.processPushNotification(payload: String) = sync<Ok>(ProcessPushNotification(payload))
suspend infix fun TdAbsHandler.processPushNotificationOrNull(payload: String) = syncOrNull<Ok>(ProcessPushNotification(payload))
fun TdAbsHandler.processPushNotification(payload: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(ProcessPushNotification(payload), 1, block)

suspend infix fun TdAbsHandler.pingProxy(proxyId: Int) = sync<Seconds>(PingProxy(proxyId))
suspend infix fun TdAbsHandler.pingProxyOrNull(proxyId: Int) = syncOrNull<Seconds>(PingProxy(proxyId))
fun TdAbsHandler.pingProxy(proxyId: Int, block: (suspend CoroutineScope.(Seconds) -> Unit)) = send(PingProxy(proxyId), 1, block)

suspend fun TdAbsHandler.pinChatMessage(chatId: Number, messageId: Long, disableNotification: Boolean) = sync<Ok>(PinChatMessage(chatId.toLong(), messageId, disableNotification))
suspend fun TdAbsHandler.pinChatMessageOrNull(chatId: Number, messageId: Long, disableNotification: Boolean) = syncOrNull<Ok>(PinChatMessage(chatId.toLong(), messageId, disableNotification))
fun TdAbsHandler.pinChatMessage(chatId: Number, messageId: Long, disableNotification: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(PinChatMessage(chatId.toLong(), messageId, disableNotification), 1, block)

fun TdAbsHandler.parseTextEntities(text: String, parseMode: TextParseMode) = syncRaw<FormattedText>(ParseTextEntities(text, parseMode))

suspend fun TdAbsHandler.optimizeStorage(size: Long, ttl: Int, count: Int, immunityDelay: Int, fileTypes: Array<FileType>, chatIds: LongArray, excludeChatIds: LongArray, chatLimit: Int) = sync<StorageStatistics>(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, chatLimit))
suspend fun TdAbsHandler.optimizeStorageOrNull(size: Long, ttl: Int, count: Int, immunityDelay: Int, fileTypes: Array<FileType>, chatIds: LongArray, excludeChatIds: LongArray, chatLimit: Int) = syncOrNull<StorageStatistics>(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, chatLimit))
fun TdAbsHandler.optimizeStorage(size: Long, ttl: Int, count: Int, immunityDelay: Int, fileTypes: Array<FileType>, chatIds: LongArray, excludeChatIds: LongArray, chatLimit: Int, block: (suspend CoroutineScope.(StorageStatistics) -> Unit)) = send(OptimizeStorage(size, ttl, count, immunityDelay, fileTypes, chatIds, excludeChatIds, chatLimit), 1, block)

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

suspend infix fun TdAbsHandler.joinChatByInviteLink(inviteLink: String) = sync<Chat>(JoinChatByInviteLink(inviteLink))
suspend infix fun TdAbsHandler.joinChatByInviteLinkOrNull(inviteLink: String) = syncOrNull<Chat>(JoinChatByInviteLink(inviteLink))
fun TdAbsHandler.joinChatByInviteLink(inviteLink: String, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(JoinChatByInviteLink(inviteLink), 1, block)

suspend infix fun TdAbsHandler.joinChat(chatId: Number) = sync<Ok>(JoinChat(chatId.toLong()))
suspend infix fun TdAbsHandler.joinChatOrNull(chatId: Number) = syncOrNull<Ok>(JoinChat(chatId.toLong()))
fun TdAbsHandler.joinChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(JoinChat(chatId.toLong()), 1, block)

suspend infix fun TdAbsHandler.importContacts(contacts: Array<Contact>) = sync<ImportedContacts>(ImportContacts(contacts))
suspend infix fun TdAbsHandler.importContactsOrNull(contacts: Array<Contact>) = syncOrNull<ImportedContacts>(ImportContacts(contacts))
fun TdAbsHandler.importContacts(contacts: Array<Contact>, block: (suspend CoroutineScope.(ImportedContacts) -> Unit)) = send(ImportContacts(contacts), 1, block)

suspend infix fun TdAbsHandler.getWebPagePreview(text: FormattedText) = sync<WebPage>(GetWebPagePreview(text))
suspend infix fun TdAbsHandler.getWebPagePreviewOrNull(text: FormattedText) = syncOrNull<WebPage>(GetWebPagePreview(text))
fun TdAbsHandler.getWebPagePreview(text: FormattedText, block: (suspend CoroutineScope.(WebPage) -> Unit)) = send(GetWebPagePreview(text), 1, block)

suspend fun TdAbsHandler.getWebPageInstantView(url: String, forceFull: Boolean) = sync<WebPageInstantView>(GetWebPageInstantView(url, forceFull))
suspend fun TdAbsHandler.getWebPageInstantViewOrNull(url: String, forceFull: Boolean) = syncOrNull<WebPageInstantView>(GetWebPageInstantView(url, forceFull))
fun TdAbsHandler.getWebPageInstantView(url: String, forceFull: Boolean, block: (suspend CoroutineScope.(WebPageInstantView) -> Unit)) = send(GetWebPageInstantView(url, forceFull), 1, block)

suspend fun TdAbsHandler.getUserProfilePhotos(userId: Int, offset: Int, limit: Int) = sync<UserProfilePhotos>(GetUserProfilePhotos(userId, offset, limit))
suspend fun TdAbsHandler.getUserProfilePhotosOrNull(userId: Int, offset: Int, limit: Int) = syncOrNull<UserProfilePhotos>(GetUserProfilePhotos(userId, offset, limit))
fun TdAbsHandler.getUserProfilePhotos(userId: Int, offset: Int, limit: Int, block: (suspend CoroutineScope.(UserProfilePhotos) -> Unit)) = send(GetUserProfilePhotos(userId, offset, limit), 1, block)

suspend infix fun TdAbsHandler.getUserPrivacySettingRules(setting: UserPrivacySetting) = sync<UserPrivacySettingRules>(GetUserPrivacySettingRules(setting))
suspend infix fun TdAbsHandler.getUserPrivacySettingRulesOrNull(setting: UserPrivacySetting) = syncOrNull<UserPrivacySettingRules>(GetUserPrivacySettingRules(setting))
fun TdAbsHandler.getUserPrivacySettingRules(setting: UserPrivacySetting, block: (suspend CoroutineScope.(UserPrivacySettingRules) -> Unit)) = send(GetUserPrivacySettingRules(setting), 1, block)

suspend infix fun TdAbsHandler.getUserFullInfo(userId: Int) = sync<UserFullInfo>(GetUserFullInfo(userId))
suspend infix fun TdAbsHandler.getUserFullInfoOrNull(userId: Int) = syncOrNull<UserFullInfo>(GetUserFullInfo(userId))
fun TdAbsHandler.getUserFullInfo(userId: Int, block: (suspend CoroutineScope.(UserFullInfo) -> Unit)) = send(GetUserFullInfo(userId), 1, block)

suspend infix fun TdAbsHandler.getUser(userId: Int) = sync<User>(GetUser(userId))
suspend infix fun TdAbsHandler.getUserOrNull(userId: Int) = syncOrNull<User>(GetUser(userId))
fun TdAbsHandler.getUser(userId: Int, block: (suspend CoroutineScope.(User) -> Unit)) = send(GetUser(userId), 1, block)

suspend fun TdAbsHandler.getTrendingStickerSets() = sync<StickerSets>(GetTrendingStickerSets())
suspend fun TdAbsHandler.getTrendingStickerSetsOrNull() = syncOrNull<StickerSets>(GetTrendingStickerSets())
fun TdAbsHandler.getTrendingStickerSets(block: (suspend CoroutineScope.(StickerSets) -> Unit)) = send(GetTrendingStickerSets(), 1, block)

suspend fun TdAbsHandler.getTopChats(category: TopChatCategory, limit: Int) = sync<Chats>(GetTopChats(category, limit))
suspend fun TdAbsHandler.getTopChatsOrNull(category: TopChatCategory, limit: Int) = syncOrNull<Chats>(GetTopChats(category, limit))
fun TdAbsHandler.getTopChats(category: TopChatCategory, limit: Int, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(GetTopChats(category, limit), 1, block)

suspend fun TdAbsHandler.getTonWalletPasswordSalt() = sync<TonWalletPasswordSalt>(GetTonWalletPasswordSalt())
suspend fun TdAbsHandler.getTonWalletPasswordSaltOrNull() = syncOrNull<TonWalletPasswordSalt>(GetTonWalletPasswordSalt())
fun TdAbsHandler.getTonWalletPasswordSalt(block: (suspend CoroutineScope.(TonWalletPasswordSalt) -> Unit)) = send(GetTonWalletPasswordSalt(), 1, block)

infix fun TdAbsHandler.getTextEntities(text: String) = syncRaw<TextEntities>(GetTextEntities(text))

suspend fun TdAbsHandler.getTemporaryPasswordState() = sync<TemporaryPasswordState>(GetTemporaryPasswordState())
suspend fun TdAbsHandler.getTemporaryPasswordStateOrNull() = syncOrNull<TemporaryPasswordState>(GetTemporaryPasswordState())
fun TdAbsHandler.getTemporaryPasswordState(block: (suspend CoroutineScope.(TemporaryPasswordState) -> Unit)) = send(GetTemporaryPasswordState(), 1, block)

suspend fun TdAbsHandler.getSupportUser() = sync<User>(GetSupportUser())
suspend fun TdAbsHandler.getSupportUserOrNull() = syncOrNull<User>(GetSupportUser())
fun TdAbsHandler.getSupportUser(block: (suspend CoroutineScope.(User) -> Unit)) = send(GetSupportUser(), 1, block)

suspend fun TdAbsHandler.getSupergroupMembers(supergroupId: Int, filter: SupergroupMembersFilter, offset: Int, limit: Int) = sync<ChatMembers>(GetSupergroupMembers(supergroupId, filter, offset, limit))
suspend fun TdAbsHandler.getSupergroupMembersOrNull(supergroupId: Int, filter: SupergroupMembersFilter, offset: Int, limit: Int) = syncOrNull<ChatMembers>(GetSupergroupMembers(supergroupId, filter, offset, limit))
fun TdAbsHandler.getSupergroupMembers(supergroupId: Int, filter: SupergroupMembersFilter, offset: Int, limit: Int, block: (suspend CoroutineScope.(ChatMembers) -> Unit)) = send(GetSupergroupMembers(supergroupId, filter, offset, limit), 1, block)

suspend infix fun TdAbsHandler.getSupergroupFullInfo(supergroupId: Int) = sync<SupergroupFullInfo>(GetSupergroupFullInfo(supergroupId))
suspend infix fun TdAbsHandler.getSupergroupFullInfoOrNull(supergroupId: Int) = syncOrNull<SupergroupFullInfo>(GetSupergroupFullInfo(supergroupId))
fun TdAbsHandler.getSupergroupFullInfo(supergroupId: Int, block: (suspend CoroutineScope.(SupergroupFullInfo) -> Unit)) = send(GetSupergroupFullInfo(supergroupId), 1, block)

suspend infix fun TdAbsHandler.getSupergroup(supergroupId: Int) = sync<Supergroup>(GetSupergroup(supergroupId))
suspend infix fun TdAbsHandler.getSupergroupOrNull(supergroupId: Int) = syncOrNull<Supergroup>(GetSupergroup(supergroupId))
fun TdAbsHandler.getSupergroup(supergroupId: Int, block: (suspend CoroutineScope.(Supergroup) -> Unit)) = send(GetSupergroup(supergroupId), 1, block)

suspend fun TdAbsHandler.getSuitableDiscussionChats() = sync<Chats>(GetSuitableDiscussionChats())
suspend fun TdAbsHandler.getSuitableDiscussionChatsOrNull() = syncOrNull<Chats>(GetSuitableDiscussionChats())
fun TdAbsHandler.getSuitableDiscussionChats(block: (suspend CoroutineScope.(Chats) -> Unit)) = send(GetSuitableDiscussionChats(), 1, block)

suspend fun TdAbsHandler.getStorageStatisticsFast() = sync<StorageStatisticsFast>(GetStorageStatisticsFast())
suspend fun TdAbsHandler.getStorageStatisticsFastOrNull() = syncOrNull<StorageStatisticsFast>(GetStorageStatisticsFast())
fun TdAbsHandler.getStorageStatisticsFast(block: (suspend CoroutineScope.(StorageStatisticsFast) -> Unit)) = send(GetStorageStatisticsFast(), 1, block)

suspend infix fun TdAbsHandler.getStorageStatistics(chatLimit: Int) = sync<StorageStatistics>(GetStorageStatistics(chatLimit))
suspend infix fun TdAbsHandler.getStorageStatisticsOrNull(chatLimit: Int) = syncOrNull<StorageStatistics>(GetStorageStatistics(chatLimit))
fun TdAbsHandler.getStorageStatistics(chatLimit: Int, block: (suspend CoroutineScope.(StorageStatistics) -> Unit)) = send(GetStorageStatistics(chatLimit), 1, block)

suspend fun TdAbsHandler.getStickers(emoji: String, limit: Int) = sync<Stickers>(GetStickers(emoji, limit))
suspend fun TdAbsHandler.getStickersOrNull(emoji: String, limit: Int) = syncOrNull<Stickers>(GetStickers(emoji, limit))
fun TdAbsHandler.getStickers(emoji: String, limit: Int, block: (suspend CoroutineScope.(Stickers) -> Unit)) = send(GetStickers(emoji, limit), 1, block)

suspend infix fun TdAbsHandler.getStickerSet(setId: Long) = sync<StickerSet>(GetStickerSet(setId))
suspend infix fun TdAbsHandler.getStickerSetOrNull(setId: Long) = syncOrNull<StickerSet>(GetStickerSet(setId))
fun TdAbsHandler.getStickerSet(setId: Long, block: (suspend CoroutineScope.(StickerSet) -> Unit)) = send(GetStickerSet(setId), 1, block)

suspend infix fun TdAbsHandler.getStickerEmojis(sticker: InputFile) = sync<Emojis>(GetStickerEmojis(sticker))
suspend infix fun TdAbsHandler.getStickerEmojisOrNull(sticker: InputFile) = syncOrNull<Emojis>(GetStickerEmojis(sticker))
fun TdAbsHandler.getStickerEmojis(sticker: InputFile, block: (suspend CoroutineScope.(Emojis) -> Unit)) = send(GetStickerEmojis(sticker), 1, block)

suspend infix fun TdAbsHandler.getSecretChat(secretChatId: Int) = sync<SecretChat>(GetSecretChat(secretChatId))
suspend infix fun TdAbsHandler.getSecretChatOrNull(secretChatId: Int) = syncOrNull<SecretChat>(GetSecretChat(secretChatId))
fun TdAbsHandler.getSecretChat(secretChatId: Int, block: (suspend CoroutineScope.(SecretChat) -> Unit)) = send(GetSecretChat(secretChatId), 1, block)

suspend infix fun TdAbsHandler.getScopeNotificationSettings(scope: NotificationSettingsScope) = sync<ScopeNotificationSettings>(GetScopeNotificationSettings(scope))
suspend infix fun TdAbsHandler.getScopeNotificationSettingsOrNull(scope: NotificationSettingsScope) = syncOrNull<ScopeNotificationSettings>(GetScopeNotificationSettings(scope))
fun TdAbsHandler.getScopeNotificationSettings(scope: NotificationSettingsScope, block: (suspend CoroutineScope.(ScopeNotificationSettings) -> Unit)) = send(GetScopeNotificationSettings(scope), 1, block)

suspend fun TdAbsHandler.getSavedOrderInfo() = sync<OrderInfo>(GetSavedOrderInfo())
suspend fun TdAbsHandler.getSavedOrderInfoOrNull() = syncOrNull<OrderInfo>(GetSavedOrderInfo())
fun TdAbsHandler.getSavedOrderInfo(block: (suspend CoroutineScope.(OrderInfo) -> Unit)) = send(GetSavedOrderInfo(), 1, block)

suspend fun TdAbsHandler.getSavedAnimations() = sync<Animations>(GetSavedAnimations())
suspend fun TdAbsHandler.getSavedAnimationsOrNull() = syncOrNull<Animations>(GetSavedAnimations())
fun TdAbsHandler.getSavedAnimations(block: (suspend CoroutineScope.(Animations) -> Unit)) = send(GetSavedAnimations(), 1, block)

suspend fun TdAbsHandler.getRepliedMessage(chatId: Number, messageId: Long) = sync<Message>(GetRepliedMessage(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getRepliedMessageOrNull(chatId: Number, messageId: Long) = syncOrNull<Message>(GetRepliedMessage(chatId.toLong(), messageId))
fun TdAbsHandler.getRepliedMessage(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Message) -> Unit)) = send(GetRepliedMessage(chatId.toLong(), messageId), 1, block)

suspend fun TdAbsHandler.getRemoteFile(remoteFileId: String, fileType: FileType) = sync<File>(GetRemoteFile(remoteFileId, fileType))
suspend fun TdAbsHandler.getRemoteFileOrNull(remoteFileId: String, fileType: FileType) = syncOrNull<File>(GetRemoteFile(remoteFileId, fileType))
fun TdAbsHandler.getRemoteFile(remoteFileId: String, fileType: FileType, block: (suspend CoroutineScope.(File) -> Unit)) = send(GetRemoteFile(remoteFileId, fileType), 1, block)

suspend infix fun TdAbsHandler.getRecoveryEmailAddress(password: String) = sync<RecoveryEmailAddress>(GetRecoveryEmailAddress(password))
suspend infix fun TdAbsHandler.getRecoveryEmailAddressOrNull(password: String) = syncOrNull<RecoveryEmailAddress>(GetRecoveryEmailAddress(password))
fun TdAbsHandler.getRecoveryEmailAddress(password: String, block: (suspend CoroutineScope.(RecoveryEmailAddress) -> Unit)) = send(GetRecoveryEmailAddress(password), 1, block)

suspend infix fun TdAbsHandler.getRecentlyVisitedTMeUrls(referrer: String) = sync<TMeUrls>(GetRecentlyVisitedTMeUrls(referrer))
suspend infix fun TdAbsHandler.getRecentlyVisitedTMeUrlsOrNull(referrer: String) = syncOrNull<TMeUrls>(GetRecentlyVisitedTMeUrls(referrer))
fun TdAbsHandler.getRecentlyVisitedTMeUrls(referrer: String, block: (suspend CoroutineScope.(TMeUrls) -> Unit)) = send(GetRecentlyVisitedTMeUrls(referrer), 1, block)

suspend infix fun TdAbsHandler.getRecentStickers(isAttached: Boolean) = sync<Stickers>(GetRecentStickers(isAttached))
suspend infix fun TdAbsHandler.getRecentStickersOrNull(isAttached: Boolean) = syncOrNull<Stickers>(GetRecentStickers(isAttached))
fun TdAbsHandler.getRecentStickers(isAttached: Boolean, block: (suspend CoroutineScope.(Stickers) -> Unit)) = send(GetRecentStickers(isAttached), 1, block)

suspend fun TdAbsHandler.getRecentInlineBots() = sync<Users>(GetRecentInlineBots())
suspend fun TdAbsHandler.getRecentInlineBotsOrNull() = syncOrNull<Users>(GetRecentInlineBots())
fun TdAbsHandler.getRecentInlineBots(block: (suspend CoroutineScope.(Users) -> Unit)) = send(GetRecentInlineBots(), 1, block)

infix fun TdAbsHandler.getPushReceiverId(payload: String) = syncRaw<PushReceiverId>(GetPushReceiverId(payload))

suspend fun TdAbsHandler.getPublicMessageLink(chatId: Number, messageId: Long, forAlbum: Boolean) = sync<PublicMessageLink>(GetPublicMessageLink(chatId.toLong(), messageId, forAlbum))
suspend fun TdAbsHandler.getPublicMessageLinkOrNull(chatId: Number, messageId: Long, forAlbum: Boolean) = syncOrNull<PublicMessageLink>(GetPublicMessageLink(chatId.toLong(), messageId, forAlbum))
fun TdAbsHandler.getPublicMessageLink(chatId: Number, messageId: Long, forAlbum: Boolean, block: (suspend CoroutineScope.(PublicMessageLink) -> Unit)) = send(GetPublicMessageLink(chatId.toLong(), messageId, forAlbum), 1, block)

suspend infix fun TdAbsHandler.getProxyLink(proxyId: Int) = sync<Text>(GetProxyLink(proxyId))
suspend infix fun TdAbsHandler.getProxyLinkOrNull(proxyId: Int) = syncOrNull<Text>(GetProxyLink(proxyId))
fun TdAbsHandler.getProxyLink(proxyId: Int, block: (suspend CoroutineScope.(Text) -> Unit)) = send(GetProxyLink(proxyId), 1, block)

suspend fun TdAbsHandler.getProxies() = sync<Proxies>(GetProxies())
suspend fun TdAbsHandler.getProxiesOrNull() = syncOrNull<Proxies>(GetProxies())
fun TdAbsHandler.getProxies(block: (suspend CoroutineScope.(Proxies) -> Unit)) = send(GetProxies(), 1, block)

suspend infix fun TdAbsHandler.getPreferredCountryLanguage(countryCode: String) = sync<Text>(GetPreferredCountryLanguage(countryCode))
suspend infix fun TdAbsHandler.getPreferredCountryLanguageOrNull(countryCode: String) = syncOrNull<Text>(GetPreferredCountryLanguage(countryCode))
fun TdAbsHandler.getPreferredCountryLanguage(countryCode: String, block: (suspend CoroutineScope.(Text) -> Unit)) = send(GetPreferredCountryLanguage(countryCode), 1, block)

suspend fun TdAbsHandler.getPaymentReceipt(chatId: Number, messageId: Long) = sync<PaymentReceipt>(GetPaymentReceipt(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getPaymentReceiptOrNull(chatId: Number, messageId: Long) = syncOrNull<PaymentReceipt>(GetPaymentReceipt(chatId.toLong(), messageId))
fun TdAbsHandler.getPaymentReceipt(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(PaymentReceipt) -> Unit)) = send(GetPaymentReceipt(chatId.toLong(), messageId), 1, block)

suspend fun TdAbsHandler.getPaymentForm(chatId: Number, messageId: Long) = sync<PaymentForm>(GetPaymentForm(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getPaymentFormOrNull(chatId: Number, messageId: Long) = syncOrNull<PaymentForm>(GetPaymentForm(chatId.toLong(), messageId))
fun TdAbsHandler.getPaymentForm(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(PaymentForm) -> Unit)) = send(GetPaymentForm(chatId.toLong(), messageId), 1, block)

suspend fun TdAbsHandler.getPasswordState() = sync<PasswordState>(GetPasswordState())
suspend fun TdAbsHandler.getPasswordStateOrNull() = syncOrNull<PasswordState>(GetPasswordState())
fun TdAbsHandler.getPasswordState(block: (suspend CoroutineScope.(PasswordState) -> Unit)) = send(GetPasswordState(), 1, block)

suspend fun TdAbsHandler.getPassportElement(type: PassportElementType, password: String) = sync<PassportElement>(GetPassportElement(type, password))
suspend fun TdAbsHandler.getPassportElementOrNull(type: PassportElementType, password: String) = syncOrNull<PassportElement>(GetPassportElement(type, password))
fun TdAbsHandler.getPassportElement(type: PassportElementType, password: String, block: (suspend CoroutineScope.(PassportElement) -> Unit)) = send(GetPassportElement(type, password), 1, block)

suspend fun TdAbsHandler.getPassportAuthorizationFormAvailableElements(autorizationFormId: Int, password: String) = sync<PassportElementsWithErrors>(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password))
suspend fun TdAbsHandler.getPassportAuthorizationFormAvailableElementsOrNull(autorizationFormId: Int, password: String) = syncOrNull<PassportElementsWithErrors>(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password))
fun TdAbsHandler.getPassportAuthorizationFormAvailableElements(autorizationFormId: Int, password: String, block: (suspend CoroutineScope.(PassportElementsWithErrors) -> Unit)) = send(GetPassportAuthorizationFormAvailableElements(autorizationFormId, password), 1, block)

suspend fun TdAbsHandler.getPassportAuthorizationForm(botUserId: Int, scope: String, publicKey: String, nonce: String) = sync<PassportAuthorizationForm>(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce))
suspend fun TdAbsHandler.getPassportAuthorizationFormOrNull(botUserId: Int, scope: String, publicKey: String, nonce: String) = syncOrNull<PassportAuthorizationForm>(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce))
fun TdAbsHandler.getPassportAuthorizationForm(botUserId: Int, scope: String, publicKey: String, nonce: String, block: (suspend CoroutineScope.(PassportAuthorizationForm) -> Unit)) = send(GetPassportAuthorizationForm(botUserId, scope, publicKey, nonce), 1, block)

suspend infix fun TdAbsHandler.getOption(name: String) = sync<OptionValue>(GetOption(name))
suspend infix fun TdAbsHandler.getOptionOrNull(name: String) = syncOrNull<OptionValue>(GetOption(name))
fun TdAbsHandler.getOption(name: String, block: (suspend CoroutineScope.(OptionValue) -> Unit)) = send(GetOption(name), 1, block)

suspend infix fun TdAbsHandler.getNetworkStatistics(onlyCurrent: Boolean) = sync<NetworkStatistics>(GetNetworkStatistics(onlyCurrent))
suspend infix fun TdAbsHandler.getNetworkStatisticsOrNull(onlyCurrent: Boolean) = syncOrNull<NetworkStatistics>(GetNetworkStatistics(onlyCurrent))
fun TdAbsHandler.getNetworkStatistics(onlyCurrent: Boolean, block: (suspend CoroutineScope.(NetworkStatistics) -> Unit)) = send(GetNetworkStatistics(onlyCurrent), 1, block)

suspend fun TdAbsHandler.getMessages(chatId: Number, messageIds: LongArray) = sync<Messages>(GetMessages(chatId.toLong(), messageIds))
suspend fun TdAbsHandler.getMessagesOrNull(chatId: Number, messageIds: LongArray) = syncOrNull<Messages>(GetMessages(chatId.toLong(), messageIds))
fun TdAbsHandler.getMessages(chatId: Number, messageIds: LongArray, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(GetMessages(chatId.toLong(), messageIds), 1, block)

suspend fun TdAbsHandler.getMessageLocally(chatId: Number, messageId: Long) = sync<Message>(GetMessageLocally(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getMessageLocallyOrNull(chatId: Number, messageId: Long) = syncOrNull<Message>(GetMessageLocally(chatId.toLong(), messageId))
fun TdAbsHandler.getMessageLocally(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Message) -> Unit)) = send(GetMessageLocally(chatId.toLong(), messageId), 1, block)

suspend infix fun TdAbsHandler.getMessageLinkInfo(url: String) = sync<MessageLinkInfo>(GetMessageLinkInfo(url))
suspend infix fun TdAbsHandler.getMessageLinkInfoOrNull(url: String) = syncOrNull<MessageLinkInfo>(GetMessageLinkInfo(url))
fun TdAbsHandler.getMessageLinkInfo(url: String, block: (suspend CoroutineScope.(MessageLinkInfo) -> Unit)) = send(GetMessageLinkInfo(url), 1, block)

suspend fun TdAbsHandler.getMessageLink(chatId: Number, messageId: Long) = sync<HttpUrl>(GetMessageLink(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getMessageLinkOrNull(chatId: Number, messageId: Long) = syncOrNull<HttpUrl>(GetMessageLink(chatId.toLong(), messageId))
fun TdAbsHandler.getMessageLink(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(HttpUrl) -> Unit)) = send(GetMessageLink(chatId.toLong(), messageId), 1, block)

suspend fun TdAbsHandler.getMessage(chatId: Number, messageId: Long) = sync<Message>(GetMessage(chatId.toLong(), messageId))
suspend fun TdAbsHandler.getMessageOrNull(chatId: Number, messageId: Long) = syncOrNull<Message>(GetMessage(chatId.toLong(), messageId))
fun TdAbsHandler.getMessage(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Message) -> Unit)) = send(GetMessage(chatId.toLong(), messageId), 1, block)

suspend fun TdAbsHandler.getMe() = sync<User>(GetMe())
suspend fun TdAbsHandler.getMeOrNull() = syncOrNull<User>(GetMe())
fun TdAbsHandler.getMe(block: (suspend CoroutineScope.(User) -> Unit)) = send(GetMe(), 1, block)

suspend fun TdAbsHandler.getMapThumbnailFile(location: Location, zoom: Int, width: Int, height: Int, scale: Int, chatId: Number) = sync<File>(GetMapThumbnailFile(location, zoom, width, height, scale, chatId.toLong()))
suspend fun TdAbsHandler.getMapThumbnailFileOrNull(location: Location, zoom: Int, width: Int, height: Int, scale: Int, chatId: Number) = syncOrNull<File>(GetMapThumbnailFile(location, zoom, width, height, scale, chatId.toLong()))
fun TdAbsHandler.getMapThumbnailFile(location: Location, zoom: Int, width: Int, height: Int, scale: Int, chatId: Number, block: (suspend CoroutineScope.(File) -> Unit)) = send(GetMapThumbnailFile(location, zoom, width, height, scale, chatId.toLong()), 1, block)

suspend fun TdAbsHandler.getLoginUrlInfo(chatId: Number, messageId: Long, buttonId: Int) = sync<LoginUrlInfo>(GetLoginUrlInfo(chatId.toLong(), messageId, buttonId))
suspend fun TdAbsHandler.getLoginUrlInfoOrNull(chatId: Number, messageId: Long, buttonId: Int) = syncOrNull<LoginUrlInfo>(GetLoginUrlInfo(chatId.toLong(), messageId, buttonId))
fun TdAbsHandler.getLoginUrlInfo(chatId: Number, messageId: Long, buttonId: Int, block: (suspend CoroutineScope.(LoginUrlInfo) -> Unit)) = send(GetLoginUrlInfo(chatId.toLong(), messageId, buttonId), 1, block)

suspend fun TdAbsHandler.getLoginUrl(chatId: Number, messageId: Long, buttonId: Int, allowWriteAccess: Boolean) = sync<LoginUrlInfo>(GetLoginUrl(chatId.toLong(), messageId, buttonId, allowWriteAccess))
suspend fun TdAbsHandler.getLoginUrlOrNull(chatId: Number, messageId: Long, buttonId: Int, allowWriteAccess: Boolean) = syncOrNull<LoginUrlInfo>(GetLoginUrl(chatId.toLong(), messageId, buttonId, allowWriteAccess))
fun TdAbsHandler.getLoginUrl(chatId: Number, messageId: Long, buttonId: Int, allowWriteAccess: Boolean, block: (suspend CoroutineScope.(LoginUrlInfo) -> Unit)) = send(GetLoginUrl(chatId.toLong(), messageId, buttonId, allowWriteAccess), 1, block)

fun TdAbsHandler.getLogVerbosityLevel() = syncRaw<LogVerbosityLevel>(GetLogVerbosityLevel())

fun TdAbsHandler.getLogTags() = syncRaw<LogTags>(GetLogTags())

infix fun TdAbsHandler.getLogTagVerbosityLevel(tag: String) = syncRaw<LogVerbosityLevel>(GetLogTagVerbosityLevel(tag))

fun TdAbsHandler.getLogStream() = syncRaw<LogStream>(GetLogStream())

suspend infix fun TdAbsHandler.getLocalizationTargetInfo(onlyLocal: Boolean) = sync<LocalizationTargetInfo>(GetLocalizationTargetInfo(onlyLocal))
suspend infix fun TdAbsHandler.getLocalizationTargetInfoOrNull(onlyLocal: Boolean) = syncOrNull<LocalizationTargetInfo>(GetLocalizationTargetInfo(onlyLocal))
fun TdAbsHandler.getLocalizationTargetInfo(onlyLocal: Boolean, block: (suspend CoroutineScope.(LocalizationTargetInfo) -> Unit)) = send(GetLocalizationTargetInfo(onlyLocal), 1, block)

suspend fun TdAbsHandler.getLanguagePackStrings(languagePackId: String, keys: Array<String>) = sync<LanguagePackStrings>(GetLanguagePackStrings(languagePackId, keys))
suspend fun TdAbsHandler.getLanguagePackStringsOrNull(languagePackId: String, keys: Array<String>) = syncOrNull<LanguagePackStrings>(GetLanguagePackStrings(languagePackId, keys))
fun TdAbsHandler.getLanguagePackStrings(languagePackId: String, keys: Array<String>, block: (suspend CoroutineScope.(LanguagePackStrings) -> Unit)) = send(GetLanguagePackStrings(languagePackId, keys), 1, block)

suspend fun TdAbsHandler.getLanguagePackString(languagePackDatabasePath: String, localizationTarget: String, languagePackId: String, key: String) = sync<LanguagePackStringValue>(GetLanguagePackString(languagePackDatabasePath, localizationTarget, languagePackId, key))
suspend fun TdAbsHandler.getLanguagePackStringOrNull(languagePackDatabasePath: String, localizationTarget: String, languagePackId: String, key: String) = syncOrNull<LanguagePackStringValue>(GetLanguagePackString(languagePackDatabasePath, localizationTarget, languagePackId, key))
fun TdAbsHandler.getLanguagePackString(languagePackDatabasePath: String, localizationTarget: String, languagePackId: String, key: String, block: (suspend CoroutineScope.(LanguagePackStringValue) -> Unit)) = send(GetLanguagePackString(languagePackDatabasePath, localizationTarget, languagePackId, key), 1, block)

suspend infix fun TdAbsHandler.getLanguagePackInfo(languagePackId: String) = sync<LanguagePackInfo>(GetLanguagePackInfo(languagePackId))
suspend infix fun TdAbsHandler.getLanguagePackInfoOrNull(languagePackId: String) = syncOrNull<LanguagePackInfo>(GetLanguagePackInfo(languagePackId))
fun TdAbsHandler.getLanguagePackInfo(languagePackId: String, block: (suspend CoroutineScope.(LanguagePackInfo) -> Unit)) = send(GetLanguagePackInfo(languagePackId), 1, block)

infix fun TdAbsHandler.getJsonValue(json: String) = syncRaw<JsonValue>(GetJsonValue(json))

infix fun TdAbsHandler.getJsonString(jsonValue: JsonValue) = syncRaw<Text>(GetJsonString(jsonValue))

suspend fun TdAbsHandler.getInviteText() = sync<Text>(GetInviteText())
suspend fun TdAbsHandler.getInviteTextOrNull() = syncOrNull<Text>(GetInviteText())
fun TdAbsHandler.getInviteText(block: (suspend CoroutineScope.(Text) -> Unit)) = send(GetInviteText(), 1, block)

suspend infix fun TdAbsHandler.getInstalledStickerSets(isMasks: Boolean) = sync<StickerSets>(GetInstalledStickerSets(isMasks))
suspend infix fun TdAbsHandler.getInstalledStickerSetsOrNull(isMasks: Boolean) = syncOrNull<StickerSets>(GetInstalledStickerSets(isMasks))
fun TdAbsHandler.getInstalledStickerSets(isMasks: Boolean, block: (suspend CoroutineScope.(StickerSets) -> Unit)) = send(GetInstalledStickerSets(isMasks), 1, block)

suspend fun TdAbsHandler.getInlineQueryResults(botUserId: Int, chatId: Number, userLocation: Location, query: String, offset: String) = sync<InlineQueryResults>(GetInlineQueryResults(botUserId, chatId.toLong(), userLocation, query, offset))
suspend fun TdAbsHandler.getInlineQueryResultsOrNull(botUserId: Int, chatId: Number, userLocation: Location, query: String, offset: String) = syncOrNull<InlineQueryResults>(GetInlineQueryResults(botUserId, chatId.toLong(), userLocation, query, offset))
fun TdAbsHandler.getInlineQueryResults(botUserId: Int, chatId: Number, userLocation: Location, query: String, offset: String, block: (suspend CoroutineScope.(InlineQueryResults) -> Unit)) = send(GetInlineQueryResults(botUserId, chatId.toLong(), userLocation, query, offset), 1, block)

suspend fun TdAbsHandler.getInlineGameHighScores(inlineMessageId: String, userId: Int) = sync<GameHighScores>(GetInlineGameHighScores(inlineMessageId, userId))
suspend fun TdAbsHandler.getInlineGameHighScoresOrNull(inlineMessageId: String, userId: Int) = syncOrNull<GameHighScores>(GetInlineGameHighScores(inlineMessageId, userId))
fun TdAbsHandler.getInlineGameHighScores(inlineMessageId: String, userId: Int, block: (suspend CoroutineScope.(GameHighScores) -> Unit)) = send(GetInlineGameHighScores(inlineMessageId, userId), 1, block)

suspend fun TdAbsHandler.getInactiveSupergroupChats() = sync<Chats>(GetInactiveSupergroupChats())
suspend fun TdAbsHandler.getInactiveSupergroupChatsOrNull() = syncOrNull<Chats>(GetInactiveSupergroupChats())
fun TdAbsHandler.getInactiveSupergroupChats(block: (suspend CoroutineScope.(Chats) -> Unit)) = send(GetInactiveSupergroupChats(), 1, block)

suspend fun TdAbsHandler.getImportedContactCount() = sync<Count>(GetImportedContactCount())
suspend fun TdAbsHandler.getImportedContactCountOrNull() = syncOrNull<Count>(GetImportedContactCount())
fun TdAbsHandler.getImportedContactCount(block: (suspend CoroutineScope.(Count) -> Unit)) = send(GetImportedContactCount(), 1, block)

suspend fun TdAbsHandler.getGroupsInCommon(userId: Int, offsetChatId: Long, limit: Int) = sync<Chats>(GetGroupsInCommon(userId, offsetChatId, limit))
suspend fun TdAbsHandler.getGroupsInCommonOrNull(userId: Int, offsetChatId: Long, limit: Int) = syncOrNull<Chats>(GetGroupsInCommon(userId, offsetChatId, limit))
fun TdAbsHandler.getGroupsInCommon(userId: Int, offsetChatId: Long, limit: Int, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(GetGroupsInCommon(userId, offsetChatId, limit), 1, block)

suspend fun TdAbsHandler.getGameHighScores(chatId: Number, messageId: Long, userId: Int) = sync<GameHighScores>(GetGameHighScores(chatId.toLong(), messageId, userId))
suspend fun TdAbsHandler.getGameHighScoresOrNull(chatId: Number, messageId: Long, userId: Int) = syncOrNull<GameHighScores>(GetGameHighScores(chatId.toLong(), messageId, userId))
fun TdAbsHandler.getGameHighScores(chatId: Number, messageId: Long, userId: Int, block: (suspend CoroutineScope.(GameHighScores) -> Unit)) = send(GetGameHighScores(chatId.toLong(), messageId, userId), 1, block)

infix fun TdAbsHandler.getFileMimeType(fileName: String) = syncRaw<Text>(GetFileMimeType(fileName))

infix fun TdAbsHandler.getFileExtension(mimeType: String) = syncRaw<Text>(GetFileExtension(mimeType))

suspend fun TdAbsHandler.getFileDownloadedPrefixSize(fileId: Int, offset: Int) = sync<Count>(GetFileDownloadedPrefixSize(fileId, offset))
suspend fun TdAbsHandler.getFileDownloadedPrefixSizeOrNull(fileId: Int, offset: Int) = syncOrNull<Count>(GetFileDownloadedPrefixSize(fileId, offset))
fun TdAbsHandler.getFileDownloadedPrefixSize(fileId: Int, offset: Int, block: (suspend CoroutineScope.(Count) -> Unit)) = send(GetFileDownloadedPrefixSize(fileId, offset), 1, block)

suspend infix fun TdAbsHandler.getFile(fileId: Int) = sync<File>(GetFile(fileId))
suspend infix fun TdAbsHandler.getFileOrNull(fileId: Int) = syncOrNull<File>(GetFile(fileId))
fun TdAbsHandler.getFile(fileId: Int, block: (suspend CoroutineScope.(File) -> Unit)) = send(GetFile(fileId), 1, block)

suspend fun TdAbsHandler.getFavoriteStickers() = sync<Stickers>(GetFavoriteStickers())
suspend fun TdAbsHandler.getFavoriteStickersOrNull() = syncOrNull<Stickers>(GetFavoriteStickers())
fun TdAbsHandler.getFavoriteStickers(block: (suspend CoroutineScope.(Stickers) -> Unit)) = send(GetFavoriteStickers(), 1, block)

suspend infix fun TdAbsHandler.getEmojiSuggestionsUrl(languageCode: String) = sync<HttpUrl>(GetEmojiSuggestionsUrl(languageCode))
suspend infix fun TdAbsHandler.getEmojiSuggestionsUrlOrNull(languageCode: String) = syncOrNull<HttpUrl>(GetEmojiSuggestionsUrl(languageCode))
fun TdAbsHandler.getEmojiSuggestionsUrl(languageCode: String, block: (suspend CoroutineScope.(HttpUrl) -> Unit)) = send(GetEmojiSuggestionsUrl(languageCode), 1, block)

suspend infix fun TdAbsHandler.getDeepLinkInfo(link: String) = sync<DeepLinkInfo>(GetDeepLinkInfo(link))
suspend infix fun TdAbsHandler.getDeepLinkInfoOrNull(link: String) = syncOrNull<DeepLinkInfo>(GetDeepLinkInfo(link))
fun TdAbsHandler.getDeepLinkInfo(link: String, block: (suspend CoroutineScope.(DeepLinkInfo) -> Unit)) = send(GetDeepLinkInfo(link), 1, block)

suspend fun TdAbsHandler.getDatabaseStatistics() = sync<DatabaseStatistics>(GetDatabaseStatistics())
suspend fun TdAbsHandler.getDatabaseStatisticsOrNull() = syncOrNull<DatabaseStatistics>(GetDatabaseStatistics())
fun TdAbsHandler.getDatabaseStatistics(block: (suspend CoroutineScope.(DatabaseStatistics) -> Unit)) = send(GetDatabaseStatistics(), 1, block)

suspend fun TdAbsHandler.getCurrentState() = sync<Updates>(GetCurrentState())
suspend fun TdAbsHandler.getCurrentStateOrNull() = syncOrNull<Updates>(GetCurrentState())
fun TdAbsHandler.getCurrentState(block: (suspend CoroutineScope.(Updates) -> Unit)) = send(GetCurrentState(), 1, block)

suspend infix fun TdAbsHandler.getCreatedPublicChats(type: PublicChatType) = sync<Chats>(GetCreatedPublicChats(type))
suspend infix fun TdAbsHandler.getCreatedPublicChatsOrNull(type: PublicChatType) = syncOrNull<Chats>(GetCreatedPublicChats(type))
fun TdAbsHandler.getCreatedPublicChats(type: PublicChatType, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(GetCreatedPublicChats(type), 1, block)

suspend fun TdAbsHandler.getCountryCode() = sync<Text>(GetCountryCode())
suspend fun TdAbsHandler.getCountryCodeOrNull() = syncOrNull<Text>(GetCountryCode())
fun TdAbsHandler.getCountryCode(block: (suspend CoroutineScope.(Text) -> Unit)) = send(GetCountryCode(), 1, block)

suspend fun TdAbsHandler.getContacts() = sync<Users>(GetContacts())
suspend fun TdAbsHandler.getContactsOrNull() = syncOrNull<Users>(GetContacts())
fun TdAbsHandler.getContacts(block: (suspend CoroutineScope.(Users) -> Unit)) = send(GetContacts(), 1, block)

suspend fun TdAbsHandler.getConnectedWebsites() = sync<ConnectedWebsites>(GetConnectedWebsites())
suspend fun TdAbsHandler.getConnectedWebsitesOrNull() = syncOrNull<ConnectedWebsites>(GetConnectedWebsites())
fun TdAbsHandler.getConnectedWebsites(block: (suspend CoroutineScope.(ConnectedWebsites) -> Unit)) = send(GetConnectedWebsites(), 1, block)

suspend fun TdAbsHandler.getChats(chatList: ChatList, offsetOrder: Long, offsetChatId: Long, limit: Int) = sync<Chats>(GetChats(chatList, offsetOrder, offsetChatId, limit))
suspend fun TdAbsHandler.getChatsOrNull(chatList: ChatList, offsetOrder: Long, offsetChatId: Long, limit: Int) = syncOrNull<Chats>(GetChats(chatList, offsetOrder, offsetChatId, limit))
fun TdAbsHandler.getChats(chatList: ChatList, offsetOrder: Long, offsetChatId: Long, limit: Int, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(GetChats(chatList, offsetOrder, offsetChatId, limit), 1, block)

suspend fun TdAbsHandler.getChatStatisticsUrl(chatId: Number, parameters: String, isDark: Boolean) = sync<HttpUrl>(GetChatStatisticsUrl(chatId.toLong(), parameters, isDark))
suspend fun TdAbsHandler.getChatStatisticsUrlOrNull(chatId: Number, parameters: String, isDark: Boolean) = syncOrNull<HttpUrl>(GetChatStatisticsUrl(chatId.toLong(), parameters, isDark))
fun TdAbsHandler.getChatStatisticsUrl(chatId: Number, parameters: String, isDark: Boolean, block: (suspend CoroutineScope.(HttpUrl) -> Unit)) = send(GetChatStatisticsUrl(chatId.toLong(), parameters, isDark), 1, block)

suspend infix fun TdAbsHandler.getChatScheduledMessages(chatId: Number) = sync<Messages>(GetChatScheduledMessages(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatScheduledMessagesOrNull(chatId: Number) = syncOrNull<Messages>(GetChatScheduledMessages(chatId.toLong()))
fun TdAbsHandler.getChatScheduledMessages(chatId: Number, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(GetChatScheduledMessages(chatId.toLong()), 1, block)

suspend infix fun TdAbsHandler.getChatPinnedMessage(chatId: Number) = sync<Message>(GetChatPinnedMessage(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatPinnedMessageOrNull(chatId: Number) = syncOrNull<Message>(GetChatPinnedMessage(chatId.toLong()))
fun TdAbsHandler.getChatPinnedMessage(chatId: Number, block: (suspend CoroutineScope.(Message) -> Unit)) = send(GetChatPinnedMessage(chatId.toLong()), 1, block)

suspend fun TdAbsHandler.getChatNotificationSettingsExceptions(scope: NotificationSettingsScope, compareSound: Boolean) = sync<Chats>(GetChatNotificationSettingsExceptions(scope, compareSound))
suspend fun TdAbsHandler.getChatNotificationSettingsExceptionsOrNull(scope: NotificationSettingsScope, compareSound: Boolean) = syncOrNull<Chats>(GetChatNotificationSettingsExceptions(scope, compareSound))
fun TdAbsHandler.getChatNotificationSettingsExceptions(scope: NotificationSettingsScope, compareSound: Boolean, block: (suspend CoroutineScope.(Chats) -> Unit)) = send(GetChatNotificationSettingsExceptions(scope, compareSound), 1, block)

suspend fun TdAbsHandler.getChatMessageCount(chatId: Number, filter: SearchMessagesFilter, returnLocal: Boolean) = sync<Count>(GetChatMessageCount(chatId.toLong(), filter, returnLocal))
suspend fun TdAbsHandler.getChatMessageCountOrNull(chatId: Number, filter: SearchMessagesFilter, returnLocal: Boolean) = syncOrNull<Count>(GetChatMessageCount(chatId.toLong(), filter, returnLocal))
fun TdAbsHandler.getChatMessageCount(chatId: Number, filter: SearchMessagesFilter, returnLocal: Boolean, block: (suspend CoroutineScope.(Count) -> Unit)) = send(GetChatMessageCount(chatId.toLong(), filter, returnLocal), 1, block)

suspend fun TdAbsHandler.getChatMessageByDate(chatId: Number, date: Int) = sync<Message>(GetChatMessageByDate(chatId.toLong(), date))
suspend fun TdAbsHandler.getChatMessageByDateOrNull(chatId: Number, date: Int) = syncOrNull<Message>(GetChatMessageByDate(chatId.toLong(), date))
fun TdAbsHandler.getChatMessageByDate(chatId: Number, date: Int, block: (suspend CoroutineScope.(Message) -> Unit)) = send(GetChatMessageByDate(chatId.toLong(), date), 1, block)

suspend fun TdAbsHandler.getChatMember(chatId: Number, userId: Int) = sync<ChatMember>(GetChatMember(chatId.toLong(), userId))
suspend fun TdAbsHandler.getChatMemberOrNull(chatId: Number, userId: Int) = syncOrNull<ChatMember>(GetChatMember(chatId.toLong(), userId))
fun TdAbsHandler.getChatMember(chatId: Number, userId: Int, block: (suspend CoroutineScope.(ChatMember) -> Unit)) = send(GetChatMember(chatId.toLong(), userId), 1, block)

suspend fun TdAbsHandler.getChatHistory(chatId: Number, fromMessageId: Long, offset: Int, limit: Int, onlyLocal: Boolean) = sync<Messages>(GetChatHistory(chatId.toLong(), fromMessageId, offset, limit, onlyLocal))
suspend fun TdAbsHandler.getChatHistoryOrNull(chatId: Number, fromMessageId: Long, offset: Int, limit: Int, onlyLocal: Boolean) = syncOrNull<Messages>(GetChatHistory(chatId.toLong(), fromMessageId, offset, limit, onlyLocal))
fun TdAbsHandler.getChatHistory(chatId: Number, fromMessageId: Long, offset: Int, limit: Int, onlyLocal: Boolean, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(GetChatHistory(chatId.toLong(), fromMessageId, offset, limit, onlyLocal), 1, block)

suspend fun TdAbsHandler.getChatEventLog(chatId: Number, query: String, fromEventId: Long, limit: Int, filters: ChatEventLogFilters, userIds: IntArray) = sync<ChatEvents>(GetChatEventLog(chatId.toLong(), query, fromEventId, limit, filters, userIds))
suspend fun TdAbsHandler.getChatEventLogOrNull(chatId: Number, query: String, fromEventId: Long, limit: Int, filters: ChatEventLogFilters, userIds: IntArray) = syncOrNull<ChatEvents>(GetChatEventLog(chatId.toLong(), query, fromEventId, limit, filters, userIds))
fun TdAbsHandler.getChatEventLog(chatId: Number, query: String, fromEventId: Long, limit: Int, filters: ChatEventLogFilters, userIds: IntArray, block: (suspend CoroutineScope.(ChatEvents) -> Unit)) = send(GetChatEventLog(chatId.toLong(), query, fromEventId, limit, filters, userIds), 1, block)

suspend infix fun TdAbsHandler.getChatAdministrators(chatId: Number) = sync<ChatAdministrators>(GetChatAdministrators(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatAdministratorsOrNull(chatId: Number) = syncOrNull<ChatAdministrators>(GetChatAdministrators(chatId.toLong()))
fun TdAbsHandler.getChatAdministrators(chatId: Number, block: (suspend CoroutineScope.(ChatAdministrators) -> Unit)) = send(GetChatAdministrators(chatId.toLong()), 1, block)

suspend infix fun TdAbsHandler.getChat(chatId: Number) = sync<Chat>(GetChat(chatId.toLong()))
suspend infix fun TdAbsHandler.getChatOrNull(chatId: Number) = syncOrNull<Chat>(GetChat(chatId.toLong()))
fun TdAbsHandler.getChat(chatId: Number, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(GetChat(chatId.toLong()), 1, block)

suspend fun TdAbsHandler.getCallbackQueryAnswer(chatId: Number, messageId: Long, payload: CallbackQueryPayload) = sync<CallbackQueryAnswer>(GetCallbackQueryAnswer(chatId.toLong(), messageId, payload))
suspend fun TdAbsHandler.getCallbackQueryAnswerOrNull(chatId: Number, messageId: Long, payload: CallbackQueryPayload) = syncOrNull<CallbackQueryAnswer>(GetCallbackQueryAnswer(chatId.toLong(), messageId, payload))
fun TdAbsHandler.getCallbackQueryAnswer(chatId: Number, messageId: Long, payload: CallbackQueryPayload, block: (suspend CoroutineScope.(CallbackQueryAnswer) -> Unit)) = send(GetCallbackQueryAnswer(chatId.toLong(), messageId, payload), 1, block)

suspend fun TdAbsHandler.getBlockedUsers(offset: Int, limit: Int) = sync<Users>(GetBlockedUsers(offset, limit))
suspend fun TdAbsHandler.getBlockedUsersOrNull(offset: Int, limit: Int) = syncOrNull<Users>(GetBlockedUsers(offset, limit))
fun TdAbsHandler.getBlockedUsers(offset: Int, limit: Int, block: (suspend CoroutineScope.(Users) -> Unit)) = send(GetBlockedUsers(offset, limit), 1, block)

suspend infix fun TdAbsHandler.getBasicGroupFullInfo(basicGroupId: Int) = sync<BasicGroupFullInfo>(GetBasicGroupFullInfo(basicGroupId))
suspend infix fun TdAbsHandler.getBasicGroupFullInfoOrNull(basicGroupId: Int) = syncOrNull<BasicGroupFullInfo>(GetBasicGroupFullInfo(basicGroupId))
fun TdAbsHandler.getBasicGroupFullInfo(basicGroupId: Int, block: (suspend CoroutineScope.(BasicGroupFullInfo) -> Unit)) = send(GetBasicGroupFullInfo(basicGroupId), 1, block)

suspend infix fun TdAbsHandler.getBasicGroup(basicGroupId: Int) = sync<BasicGroup>(GetBasicGroup(basicGroupId))
suspend infix fun TdAbsHandler.getBasicGroupOrNull(basicGroupId: Int) = syncOrNull<BasicGroup>(GetBasicGroup(basicGroupId))
fun TdAbsHandler.getBasicGroup(basicGroupId: Int, block: (suspend CoroutineScope.(BasicGroup) -> Unit)) = send(GetBasicGroup(basicGroupId), 1, block)

suspend infix fun TdAbsHandler.getBackgrounds(forDarkTheme: Boolean) = sync<Backgrounds>(GetBackgrounds(forDarkTheme))
suspend infix fun TdAbsHandler.getBackgroundsOrNull(forDarkTheme: Boolean) = syncOrNull<Backgrounds>(GetBackgrounds(forDarkTheme))
fun TdAbsHandler.getBackgrounds(forDarkTheme: Boolean, block: (suspend CoroutineScope.(Backgrounds) -> Unit)) = send(GetBackgrounds(forDarkTheme), 1, block)

suspend fun TdAbsHandler.getBackgroundUrl(name: String, type: BackgroundType) = sync<HttpUrl>(GetBackgroundUrl(name, type))
suspend fun TdAbsHandler.getBackgroundUrlOrNull(name: String, type: BackgroundType) = syncOrNull<HttpUrl>(GetBackgroundUrl(name, type))
fun TdAbsHandler.getBackgroundUrl(name: String, type: BackgroundType, block: (suspend CoroutineScope.(HttpUrl) -> Unit)) = send(GetBackgroundUrl(name, type), 1, block)

suspend fun TdAbsHandler.getAutoDownloadSettingsPresets() = sync<AutoDownloadSettingsPresets>(GetAutoDownloadSettingsPresets())
suspend fun TdAbsHandler.getAutoDownloadSettingsPresetsOrNull() = syncOrNull<AutoDownloadSettingsPresets>(GetAutoDownloadSettingsPresets())
fun TdAbsHandler.getAutoDownloadSettingsPresets(block: (suspend CoroutineScope.(AutoDownloadSettingsPresets) -> Unit)) = send(GetAutoDownloadSettingsPresets(), 1, block)

suspend fun TdAbsHandler.getAuthorizationState() = sync<AuthorizationState>(GetAuthorizationState())
suspend fun TdAbsHandler.getAuthorizationStateOrNull() = syncOrNull<AuthorizationState>(GetAuthorizationState())
fun TdAbsHandler.getAuthorizationState(block: (suspend CoroutineScope.(AuthorizationState) -> Unit)) = send(GetAuthorizationState(), 1, block)

suspend infix fun TdAbsHandler.getAttachedStickerSets(fileId: Int) = sync<StickerSets>(GetAttachedStickerSets(fileId))
suspend infix fun TdAbsHandler.getAttachedStickerSetsOrNull(fileId: Int) = syncOrNull<StickerSets>(GetAttachedStickerSets(fileId))
fun TdAbsHandler.getAttachedStickerSets(fileId: Int, block: (suspend CoroutineScope.(StickerSets) -> Unit)) = send(GetAttachedStickerSets(fileId), 1, block)

suspend fun TdAbsHandler.getArchivedStickerSets(isMasks: Boolean, offsetStickerSetId: Long, limit: Int) = sync<StickerSets>(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit))
suspend fun TdAbsHandler.getArchivedStickerSetsOrNull(isMasks: Boolean, offsetStickerSetId: Long, limit: Int) = syncOrNull<StickerSets>(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit))
fun TdAbsHandler.getArchivedStickerSets(isMasks: Boolean, offsetStickerSetId: Long, limit: Int, block: (suspend CoroutineScope.(StickerSets) -> Unit)) = send(GetArchivedStickerSets(isMasks, offsetStickerSetId, limit), 1, block)

suspend fun TdAbsHandler.getApplicationConfig() = sync<JsonValue>(GetApplicationConfig())
suspend fun TdAbsHandler.getApplicationConfigOrNull() = syncOrNull<JsonValue>(GetApplicationConfig())
fun TdAbsHandler.getApplicationConfig(block: (suspend CoroutineScope.(JsonValue) -> Unit)) = send(GetApplicationConfig(), 1, block)

suspend infix fun TdAbsHandler.getAllPassportElements(password: String) = sync<PassportElements>(GetAllPassportElements(password))
suspend infix fun TdAbsHandler.getAllPassportElementsOrNull(password: String) = syncOrNull<PassportElements>(GetAllPassportElements(password))
fun TdAbsHandler.getAllPassportElements(password: String, block: (suspend CoroutineScope.(PassportElements) -> Unit)) = send(GetAllPassportElements(password), 1, block)

suspend fun TdAbsHandler.getActiveSessions() = sync<Sessions>(GetActiveSessions())
suspend fun TdAbsHandler.getActiveSessionsOrNull() = syncOrNull<Sessions>(GetActiveSessions())
fun TdAbsHandler.getActiveSessions(block: (suspend CoroutineScope.(Sessions) -> Unit)) = send(GetActiveSessions(), 1, block)

suspend fun TdAbsHandler.getActiveLiveLocationMessages() = sync<Messages>(GetActiveLiveLocationMessages())
suspend fun TdAbsHandler.getActiveLiveLocationMessagesOrNull() = syncOrNull<Messages>(GetActiveLiveLocationMessages())
fun TdAbsHandler.getActiveLiveLocationMessages(block: (suspend CoroutineScope.(Messages) -> Unit)) = send(GetActiveLiveLocationMessages(), 1, block)

suspend fun TdAbsHandler.getAccountTtl() = sync<AccountTtl>(GetAccountTtl())
suspend fun TdAbsHandler.getAccountTtlOrNull() = syncOrNull<AccountTtl>(GetAccountTtl())
fun TdAbsHandler.getAccountTtl(block: (suspend CoroutineScope.(AccountTtl) -> Unit)) = send(GetAccountTtl(), 1, block)

suspend infix fun TdAbsHandler.generateChatInviteLink(chatId: Number) = sync<ChatInviteLink>(GenerateChatInviteLink(chatId.toLong()))
suspend infix fun TdAbsHandler.generateChatInviteLinkOrNull(chatId: Number) = syncOrNull<ChatInviteLink>(GenerateChatInviteLink(chatId.toLong()))
fun TdAbsHandler.generateChatInviteLink(chatId: Number, block: (suspend CoroutineScope.(ChatInviteLink) -> Unit)) = send(GenerateChatInviteLink(chatId.toLong()), 1, block)

suspend fun TdAbsHandler.forwardMessages(chatId: Number, fromChatId: Long, messageIds: LongArray, options: SendMessageOptions, asAlbum: Boolean, sendCopy: Boolean, removeCaption: Boolean) = sync<Messages>(ForwardMessages(chatId.toLong(), fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption))
suspend fun TdAbsHandler.forwardMessagesOrNull(chatId: Number, fromChatId: Long, messageIds: LongArray, options: SendMessageOptions, asAlbum: Boolean, sendCopy: Boolean, removeCaption: Boolean) = syncOrNull<Messages>(ForwardMessages(chatId.toLong(), fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption))
fun TdAbsHandler.forwardMessages(chatId: Number, fromChatId: Long, messageIds: LongArray, options: SendMessageOptions, asAlbum: Boolean, sendCopy: Boolean, removeCaption: Boolean, block: (suspend CoroutineScope.(Messages) -> Unit)) = send(ForwardMessages(chatId.toLong(), fromChatId, messageIds, options, asAlbum, sendCopy, removeCaption), 1, block)

suspend fun TdAbsHandler.finishFileGeneration(generationId: Long, error: Error) = sync<Ok>(FinishFileGeneration(generationId, error))
suspend fun TdAbsHandler.finishFileGenerationOrNull(generationId: Long, error: Error) = syncOrNull<Ok>(FinishFileGeneration(generationId, error))
fun TdAbsHandler.finishFileGeneration(generationId: Long, error: Error, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(FinishFileGeneration(generationId, error), 1, block)

suspend infix fun TdAbsHandler.enableProxy(proxyId: Int) = sync<Ok>(EnableProxy(proxyId))
suspend infix fun TdAbsHandler.enableProxyOrNull(proxyId: Int) = syncOrNull<Ok>(EnableProxy(proxyId))
fun TdAbsHandler.enableProxy(proxyId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EnableProxy(proxyId), 1, block)

suspend fun TdAbsHandler.editProxy(proxyId: Int, server: String, port: Int, enable: Boolean, type: ProxyType) = sync<Proxy>(EditProxy(proxyId, server, port, enable, type))
suspend fun TdAbsHandler.editProxyOrNull(proxyId: Int, server: String, port: Int, enable: Boolean, type: ProxyType) = syncOrNull<Proxy>(EditProxy(proxyId, server, port, enable, type))
fun TdAbsHandler.editProxy(proxyId: Int, server: String, port: Int, enable: Boolean, type: ProxyType, block: (suspend CoroutineScope.(Proxy) -> Unit)) = send(EditProxy(proxyId, server, port, enable, type), 1, block)

suspend fun TdAbsHandler.editMessageText(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<Message>(EditMessageText(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.editMessageTextOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<Message>(EditMessageText(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
fun TdAbsHandler.editMessageText(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Message) -> Unit)) = send(EditMessageText(chatId.toLong(), messageId, replyMarkup, inputMessageContent), 1, block)

suspend fun TdAbsHandler.editMessageSchedulingState(chatId: Number, messageId: Long, schedulingState: MessageSchedulingState) = sync<Ok>(EditMessageSchedulingState(chatId.toLong(), messageId, schedulingState))
suspend fun TdAbsHandler.editMessageSchedulingStateOrNull(chatId: Number, messageId: Long, schedulingState: MessageSchedulingState) = syncOrNull<Ok>(EditMessageSchedulingState(chatId.toLong(), messageId, schedulingState))
fun TdAbsHandler.editMessageSchedulingState(chatId: Number, messageId: Long, schedulingState: MessageSchedulingState, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(EditMessageSchedulingState(chatId.toLong(), messageId, schedulingState), 1, block)

suspend fun TdAbsHandler.editMessageReplyMarkup(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup) = sync<Message>(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup))
suspend fun TdAbsHandler.editMessageReplyMarkupOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup) = syncOrNull<Message>(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup))
fun TdAbsHandler.editMessageReplyMarkup(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, block: (suspend CoroutineScope.(Message) -> Unit)) = send(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkup), 1, block)

suspend fun TdAbsHandler.editMessageMedia(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = sync<Message>(EditMessageMedia(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
suspend fun TdAbsHandler.editMessageMediaOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent) = syncOrNull<Message>(EditMessageMedia(chatId.toLong(), messageId, replyMarkup, inputMessageContent))
fun TdAbsHandler.editMessageMedia(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Message) -> Unit)) = send(EditMessageMedia(chatId.toLong(), messageId, replyMarkup, inputMessageContent), 1, block)

suspend fun TdAbsHandler.editMessageLiveLocation(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, location: Location) = sync<Message>(EditMessageLiveLocation(chatId.toLong(), messageId, replyMarkup, location))
suspend fun TdAbsHandler.editMessageLiveLocationOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, location: Location) = syncOrNull<Message>(EditMessageLiveLocation(chatId.toLong(), messageId, replyMarkup, location))
fun TdAbsHandler.editMessageLiveLocation(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, location: Location, block: (suspend CoroutineScope.(Message) -> Unit)) = send(EditMessageLiveLocation(chatId.toLong(), messageId, replyMarkup, location), 1, block)

suspend fun TdAbsHandler.editMessageCaption(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, caption: FormattedText) = sync<Message>(EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption))
suspend fun TdAbsHandler.editMessageCaptionOrNull(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, caption: FormattedText) = syncOrNull<Message>(EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption))
fun TdAbsHandler.editMessageCaption(chatId: Number, messageId: Long, replyMarkup: ReplyMarkup, caption: FormattedText, block: (suspend CoroutineScope.(Message) -> Unit)) = send(EditMessageCaption(chatId.toLong(), messageId, replyMarkup, caption), 1, block)

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

suspend fun TdAbsHandler.downloadFile(fileId: Int, priority: Int, offset: Int, limit: Int, synchronous: Boolean) = sync<File>(DownloadFile(fileId, priority, offset, limit, synchronous))
suspend fun TdAbsHandler.downloadFileOrNull(fileId: Int, priority: Int, offset: Int, limit: Int, synchronous: Boolean) = syncOrNull<File>(DownloadFile(fileId, priority, offset, limit, synchronous))
fun TdAbsHandler.downloadFile(fileId: Int, priority: Int, offset: Int, limit: Int, synchronous: Boolean, block: (suspend CoroutineScope.(File) -> Unit)) = send(DownloadFile(fileId, priority, offset, limit, synchronous), 1, block)

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

suspend fun TdAbsHandler.createTemporaryPassword(password: String, validFor: Int) = sync<TemporaryPasswordState>(CreateTemporaryPassword(password, validFor))
suspend fun TdAbsHandler.createTemporaryPasswordOrNull(password: String, validFor: Int) = syncOrNull<TemporaryPasswordState>(CreateTemporaryPassword(password, validFor))
fun TdAbsHandler.createTemporaryPassword(password: String, validFor: Int, block: (suspend CoroutineScope.(TemporaryPasswordState) -> Unit)) = send(CreateTemporaryPassword(password, validFor), 1, block)

suspend fun TdAbsHandler.createSupergroupChat(supergroupId: Int, force: Boolean) = sync<Chat>(CreateSupergroupChat(supergroupId, force))
suspend fun TdAbsHandler.createSupergroupChatOrNull(supergroupId: Int, force: Boolean) = syncOrNull<Chat>(CreateSupergroupChat(supergroupId, force))
fun TdAbsHandler.createSupergroupChat(supergroupId: Int, force: Boolean, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(CreateSupergroupChat(supergroupId, force), 1, block)

suspend infix fun TdAbsHandler.createSecretChat(secretChatId: Int) = sync<Chat>(CreateSecretChat(secretChatId))
suspend infix fun TdAbsHandler.createSecretChatOrNull(secretChatId: Int) = syncOrNull<Chat>(CreateSecretChat(secretChatId))
fun TdAbsHandler.createSecretChat(secretChatId: Int, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(CreateSecretChat(secretChatId), 1, block)

suspend fun TdAbsHandler.createPrivateChat(userId: Int, force: Boolean) = sync<Chat>(CreatePrivateChat(userId, force))
suspend fun TdAbsHandler.createPrivateChatOrNull(userId: Int, force: Boolean) = syncOrNull<Chat>(CreatePrivateChat(userId, force))
fun TdAbsHandler.createPrivateChat(userId: Int, force: Boolean, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(CreatePrivateChat(userId, force), 1, block)

suspend fun TdAbsHandler.createNewSupergroupChat(title: String, isChannel: Boolean, description: String, location: ChatLocation) = sync<Chat>(CreateNewSupergroupChat(title, isChannel, description, location))
suspend fun TdAbsHandler.createNewSupergroupChatOrNull(title: String, isChannel: Boolean, description: String, location: ChatLocation) = syncOrNull<Chat>(CreateNewSupergroupChat(title, isChannel, description, location))
fun TdAbsHandler.createNewSupergroupChat(title: String, isChannel: Boolean, description: String, location: ChatLocation, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(CreateNewSupergroupChat(title, isChannel, description, location), 1, block)

suspend fun TdAbsHandler.createNewStickerSet(userId: Int, title: String, name: String, isMasks: Boolean, stickers: Array<InputSticker>) = sync<StickerSet>(CreateNewStickerSet(userId, title, name, isMasks, stickers))
suspend fun TdAbsHandler.createNewStickerSetOrNull(userId: Int, title: String, name: String, isMasks: Boolean, stickers: Array<InputSticker>) = syncOrNull<StickerSet>(CreateNewStickerSet(userId, title, name, isMasks, stickers))
fun TdAbsHandler.createNewStickerSet(userId: Int, title: String, name: String, isMasks: Boolean, stickers: Array<InputSticker>, block: (suspend CoroutineScope.(StickerSet) -> Unit)) = send(CreateNewStickerSet(userId, title, name, isMasks, stickers), 1, block)

suspend infix fun TdAbsHandler.createNewSecretChat(userId: Int) = sync<Chat>(CreateNewSecretChat(userId))
suspend infix fun TdAbsHandler.createNewSecretChatOrNull(userId: Int) = syncOrNull<Chat>(CreateNewSecretChat(userId))
fun TdAbsHandler.createNewSecretChat(userId: Int, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(CreateNewSecretChat(userId), 1, block)

suspend fun TdAbsHandler.createNewBasicGroupChat(userIds: IntArray, title: String) = sync<Chat>(CreateNewBasicGroupChat(userIds, title))
suspend fun TdAbsHandler.createNewBasicGroupChatOrNull(userIds: IntArray, title: String) = syncOrNull<Chat>(CreateNewBasicGroupChat(userIds, title))
fun TdAbsHandler.createNewBasicGroupChat(userIds: IntArray, title: String, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(CreateNewBasicGroupChat(userIds, title), 1, block)

suspend fun TdAbsHandler.createCall(userId: Int, protocol: CallProtocol) = sync<CallId>(CreateCall(userId, protocol))
suspend fun TdAbsHandler.createCallOrNull(userId: Int, protocol: CallProtocol) = syncOrNull<CallId>(CreateCall(userId, protocol))
fun TdAbsHandler.createCall(userId: Int, protocol: CallProtocol, block: (suspend CoroutineScope.(CallId) -> Unit)) = send(CreateCall(userId, protocol), 1, block)

suspend fun TdAbsHandler.createBasicGroupChat(basicGroupId: Int, force: Boolean) = sync<Chat>(CreateBasicGroupChat(basicGroupId, force))
suspend fun TdAbsHandler.createBasicGroupChatOrNull(basicGroupId: Int, force: Boolean) = syncOrNull<Chat>(CreateBasicGroupChat(basicGroupId, force))
fun TdAbsHandler.createBasicGroupChat(basicGroupId: Int, force: Boolean, block: (suspend CoroutineScope.(Chat) -> Unit)) = send(CreateBasicGroupChat(basicGroupId, force), 1, block)

suspend infix fun TdAbsHandler.confirmQrCodeAuthentication(link: String) = sync<Session>(ConfirmQrCodeAuthentication(link))
suspend infix fun TdAbsHandler.confirmQrCodeAuthenticationOrNull(link: String) = syncOrNull<Session>(ConfirmQrCodeAuthentication(link))
fun TdAbsHandler.confirmQrCodeAuthentication(link: String, block: (suspend CoroutineScope.(Session) -> Unit)) = send(ConfirmQrCodeAuthentication(link), 1, block)

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

infix fun TdAbsHandler.cleanFileName(fileName: String) = syncRaw<Text>(CleanFileName(fileName))

suspend infix fun TdAbsHandler.checkRecoveryEmailAddressCode(code: String) = sync<PasswordState>(CheckRecoveryEmailAddressCode(code))
suspend infix fun TdAbsHandler.checkRecoveryEmailAddressCodeOrNull(code: String) = syncOrNull<PasswordState>(CheckRecoveryEmailAddressCode(code))
fun TdAbsHandler.checkRecoveryEmailAddressCode(code: String, block: (suspend CoroutineScope.(PasswordState) -> Unit)) = send(CheckRecoveryEmailAddressCode(code), 1, block)

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

suspend fun TdAbsHandler.checkChatUsername(chatId: Number, username: String) = sync<CheckChatUsernameResult>(CheckChatUsername(chatId.toLong(), username))
suspend fun TdAbsHandler.checkChatUsernameOrNull(chatId: Number, username: String) = syncOrNull<CheckChatUsernameResult>(CheckChatUsername(chatId.toLong(), username))
fun TdAbsHandler.checkChatUsername(chatId: Number, username: String, block: (suspend CoroutineScope.(CheckChatUsernameResult) -> Unit)) = send(CheckChatUsername(chatId.toLong(), username), 1, block)

suspend infix fun TdAbsHandler.checkChatInviteLink(inviteLink: String) = sync<ChatInviteLinkInfo>(CheckChatInviteLink(inviteLink))
suspend infix fun TdAbsHandler.checkChatInviteLinkOrNull(inviteLink: String) = syncOrNull<ChatInviteLinkInfo>(CheckChatInviteLink(inviteLink))
fun TdAbsHandler.checkChatInviteLink(inviteLink: String, block: (suspend CoroutineScope.(ChatInviteLinkInfo) -> Unit)) = send(CheckChatInviteLink(inviteLink), 1, block)

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

suspend fun TdAbsHandler.changePhoneNumber(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = sync<AuthenticationCodeInfo>(ChangePhoneNumber(phoneNumber, settings))
suspend fun TdAbsHandler.changePhoneNumberOrNull(phoneNumber: String, settings: PhoneNumberAuthenticationSettings) = syncOrNull<AuthenticationCodeInfo>(ChangePhoneNumber(phoneNumber, settings))
fun TdAbsHandler.changePhoneNumber(phoneNumber: String, settings: PhoneNumberAuthenticationSettings, block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)) = send(ChangePhoneNumber(phoneNumber, settings), 1, block)

suspend infix fun TdAbsHandler.changeImportedContacts(contacts: Array<Contact>) = sync<ImportedContacts>(ChangeImportedContacts(contacts))
suspend infix fun TdAbsHandler.changeImportedContactsOrNull(contacts: Array<Contact>) = syncOrNull<ImportedContacts>(ChangeImportedContacts(contacts))
fun TdAbsHandler.changeImportedContacts(contacts: Array<Contact>, block: (suspend CoroutineScope.(ImportedContacts) -> Unit)) = send(ChangeImportedContacts(contacts), 1, block)

suspend infix fun TdAbsHandler.cancelUploadFile(fileId: Int) = sync<Ok>(CancelUploadFile(fileId))
suspend infix fun TdAbsHandler.cancelUploadFileOrNull(fileId: Int) = syncOrNull<Ok>(CancelUploadFile(fileId))
fun TdAbsHandler.cancelUploadFile(fileId: Int, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CancelUploadFile(fileId), 1, block)

suspend fun TdAbsHandler.cancelDownloadFile(fileId: Int, onlyIfPending: Boolean) = sync<Ok>(CancelDownloadFile(fileId, onlyIfPending))
suspend fun TdAbsHandler.cancelDownloadFileOrNull(fileId: Int, onlyIfPending: Boolean) = syncOrNull<Ok>(CancelDownloadFile(fileId, onlyIfPending))
fun TdAbsHandler.cancelDownloadFile(fileId: Int, onlyIfPending: Boolean, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(CancelDownloadFile(fileId, onlyIfPending), 1, block)

suspend fun TdAbsHandler.canTransferOwnership() = sync<CanTransferOwnershipResult>(CanTransferOwnership())
suspend fun TdAbsHandler.canTransferOwnershipOrNull() = syncOrNull<CanTransferOwnershipResult>(CanTransferOwnership())
fun TdAbsHandler.canTransferOwnership(block: (suspend CoroutineScope.(CanTransferOwnershipResult) -> Unit)) = send(CanTransferOwnership(), 1, block)

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

suspend fun TdAbsHandler.addStickerToSet(userId: Int, name: String, sticker: InputSticker) = sync<StickerSet>(AddStickerToSet(userId, name, sticker))
suspend fun TdAbsHandler.addStickerToSetOrNull(userId: Int, name: String, sticker: InputSticker) = syncOrNull<StickerSet>(AddStickerToSet(userId, name, sticker))
fun TdAbsHandler.addStickerToSet(userId: Int, name: String, sticker: InputSticker, block: (suspend CoroutineScope.(StickerSet) -> Unit)) = send(AddStickerToSet(userId, name, sticker), 1, block)

suspend infix fun TdAbsHandler.addSavedAnimation(animation: InputFile) = sync<Ok>(AddSavedAnimation(animation))
suspend infix fun TdAbsHandler.addSavedAnimationOrNull(animation: InputFile) = syncOrNull<Ok>(AddSavedAnimation(animation))
fun TdAbsHandler.addSavedAnimation(animation: InputFile, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddSavedAnimation(animation), 1, block)

suspend infix fun TdAbsHandler.addRecentlyFoundChat(chatId: Number) = sync<Ok>(AddRecentlyFoundChat(chatId.toLong()))
suspend infix fun TdAbsHandler.addRecentlyFoundChatOrNull(chatId: Number) = syncOrNull<Ok>(AddRecentlyFoundChat(chatId.toLong()))
fun TdAbsHandler.addRecentlyFoundChat(chatId: Number, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddRecentlyFoundChat(chatId.toLong()), 1, block)

suspend fun TdAbsHandler.addRecentSticker(isAttached: Boolean, sticker: InputFile) = sync<Stickers>(AddRecentSticker(isAttached, sticker))
suspend fun TdAbsHandler.addRecentStickerOrNull(isAttached: Boolean, sticker: InputFile) = syncOrNull<Stickers>(AddRecentSticker(isAttached, sticker))
fun TdAbsHandler.addRecentSticker(isAttached: Boolean, sticker: InputFile, block: (suspend CoroutineScope.(Stickers) -> Unit)) = send(AddRecentSticker(isAttached, sticker), 1, block)

suspend fun TdAbsHandler.addProxy(server: String, port: Int, enable: Boolean, type: ProxyType) = sync<Proxy>(AddProxy(server, port, enable, type))
suspend fun TdAbsHandler.addProxyOrNull(server: String, port: Int, enable: Boolean, type: ProxyType) = syncOrNull<Proxy>(AddProxy(server, port, enable, type))
fun TdAbsHandler.addProxy(server: String, port: Int, enable: Boolean, type: ProxyType, block: (suspend CoroutineScope.(Proxy) -> Unit)) = send(AddProxy(server, port, enable, type), 1, block)

suspend infix fun TdAbsHandler.addNetworkStatistics(entry: NetworkStatisticsEntry) = sync<Ok>(AddNetworkStatistics(entry))
suspend infix fun TdAbsHandler.addNetworkStatisticsOrNull(entry: NetworkStatisticsEntry) = syncOrNull<Ok>(AddNetworkStatistics(entry))
fun TdAbsHandler.addNetworkStatistics(entry: NetworkStatisticsEntry, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddNetworkStatistics(entry), 1, block)

suspend fun TdAbsHandler.addLogMessage(verbosityLevel: Int, text: String) = sync<Ok>(AddLogMessage(verbosityLevel, text))
suspend fun TdAbsHandler.addLogMessageOrNull(verbosityLevel: Int, text: String) = syncOrNull<Ok>(AddLogMessage(verbosityLevel, text))
fun TdAbsHandler.addLogMessage(verbosityLevel: Int, text: String, block: (suspend CoroutineScope.(Ok) -> Unit)) = send(AddLogMessage(verbosityLevel, text), 1, block)

suspend fun TdAbsHandler.addLocalMessage(chatId: Number, senderUserId: Int, replyToMessageId: Long, disableNotification: Boolean, inputMessageContent: InputMessageContent) = sync<Message>(AddLocalMessage(chatId.toLong(), senderUserId, replyToMessageId, disableNotification, inputMessageContent))
suspend fun TdAbsHandler.addLocalMessageOrNull(chatId: Number, senderUserId: Int, replyToMessageId: Long, disableNotification: Boolean, inputMessageContent: InputMessageContent) = syncOrNull<Message>(AddLocalMessage(chatId.toLong(), senderUserId, replyToMessageId, disableNotification, inputMessageContent))
fun TdAbsHandler.addLocalMessage(chatId: Number, senderUserId: Int, replyToMessageId: Long, disableNotification: Boolean, inputMessageContent: InputMessageContent, block: (suspend CoroutineScope.(Message) -> Unit)) = send(AddLocalMessage(chatId.toLong(), senderUserId, replyToMessageId, disableNotification, inputMessageContent), 1, block)

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
