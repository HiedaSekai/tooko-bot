package tooko.td.client;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.log.Log;
import cn.hutool.log.StaticLog;
import tooko.Launcher;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi;
import tooko.td.TdApi.*;
import tooko.td.core.LongLongArrayMap;
import tooko.td.http.HttpApi;
import tooko.td.http.request.BaseRequest;
import tooko.td.http.response.BaseResponse;

import java.util.LinkedList;
import java.util.TimerTask;

public class TdHandler {

    public Log log = Launcher.log;

    public TdClient client;

    public java.io.File getFile(File file) throws TdException {

        execute(new DownloadFile(file.id, 0, 0, 0, true));

        return new java.io.File(file.local.path);

    }

    public User user(int chatId) {

        return I(new GetUser(chatId));

    }

    public Chat chat(long chatId) {

        return I(new GetChat(chatId));

    }

    public Supergroup superGroup(long chatId) {

        return superGroup(chat(chatId));

    }

    public Supergroup superGroup(Chat chat) {

        if (chat == null || !(chat.type instanceof ChatTypeSupergroup)) return null;

        return superGroup(((ChatTypeSupergroup) chat.type).supergroupId);

    }

    public Supergroup superGroup(int superGroupId) {

        return I(new GetSupergroup(superGroupId));

    }

    public Message message(long chatId, long messageId) {

        return I(new GetMessage(chatId, messageId));

    }

    public void delay(SendMessage function) {

        delay(function, -1);

    }

    public void delay(SendMessage function, final long delay) {

        send(function, new TdCallback<Message>() {

            @Override
            public void onCallback(boolean isOk, TdApi.Message result, TdApi.Error error) {

                if (isOk) delay(result, delay);

            }

        });

    }

    public void delay(final Message message) {

        delay(message, -1);

    }

    public void delay(final Message message, long delay) {

        TdClient.mainTimer.schedule(new TimerTask() {

            @Override
            public void run() {

                send(Fn.deleteMessage(message));

            }

        }, delay <= 0 ? 1000 : delay);

    }

    public long send(Function function) {

        return client.send(function);

    }

    public <T extends TdApi.Object> T E(TdApi.Function function) {

        try {

            return client.execute(function);

        } catch (TdException e) {

            StaticLog.warn(Fn.parseError(e));

            return null;

        }

    }

    public static <T extends BaseRequest, R extends BaseResponse> R E(String botToken, final BaseRequest<T, R> request) {

        try {

            return execute(botToken, request);

        } catch (TdException e) {

            StaticLog.warn(Fn.parseError(e));

            return null;

        }

    }

    public <T extends BaseRequest, R extends BaseResponse> R E(final BaseRequest<T, R> request) {

        try {

            return execute(request);

        } catch (TdException e) {

            StaticLog.warn(Fn.parseError(e));

            return null;

        }

    }

    public Message syncE(SendMessage function) {

        try {

            return sync(function);

        } catch (TdException e) {

            StaticLog.warn(Fn.parseError(e));

            return null;

        }

    }

    public Message syncI(SendMessage function) {

        try {

            return sync(function);

        } catch (TdException e) {

            return null;

        }

    }

    public <T extends TdApi.Object> T I(TdApi.Function function) {

        try {

            return client.execute(function);

        } catch (TdException e) {

            return null;

        }

    }

    public <T extends BaseRequest, R extends BaseResponse> R I(final BaseRequest<T, R> request) {

        try {

            return execute(request);

        } catch (TdException e) {

            return null;

        }

    }

    public <T extends BaseRequest, R extends BaseResponse> R I(String botToken, final BaseRequest<T, R> request) {

        try {

            return execute(botToken, request);

        } catch (TdException e) {

            return null;

        }

    }

    public <T extends BaseRequest, R extends BaseResponse> void send(BaseRequest<T, R> request) {

        execute(request);

    }

    public <T extends BaseRequest, R extends BaseResponse> void send(String botToken, BaseRequest<T, R> request) {

        execute(botToken, request);

    }

    public <T extends BaseRequest, R extends BaseResponse> R execute(final BaseRequest<T, R> request) throws TdException {

        return execute(((TdBot) client).botToken, request);

    }

    public static <T extends BaseRequest, R extends BaseResponse> R execute(String botToken, final BaseRequest<T, R> request) throws TdException {

        return HttpApi.execute(botToken, request);

    }

    public <T extends TdApi.Object> T execute(TdApi.Function function) throws TdException {

        return client.execute(function);

    }

    public void send(Function function, TdCallback<?> callback) {

        client.send(function, callback);

    }

    public Message sync(SendMessage function) throws TdException {

        return client.sync(function);

    }

    public void send(SendMessage function, TdCallback<Message> callback) {

        client.send(function, callback);

    }

    public void sync(SendMessage function, TdCallback<Message> callback) {

        client.sync(function, callback);

    }

    public void processEvent(TdApi.Object event) {

        if (event instanceof UpdateAuthorizationState) {

            UpdateAuthorizationState update = (UpdateAuthorizationState) event;

            onAuthorizationState(update.authorizationState);

        } else if (event instanceof UpdateNewMessage) {

            UpdateNewMessage update = (UpdateNewMessage) event;

            onNewMessage(update.message);

        } else if (event instanceof UpdateMessageSendAcknowledged) {

            UpdateMessageSendAcknowledged update = (UpdateMessageSendAcknowledged) event;

            onMessageSendAcknowledged(update.chatId, update.messageId);

        } else if (event instanceof UpdateMessageSendSucceeded) {

            UpdateMessageSendSucceeded update = (UpdateMessageSendSucceeded) event;

            onMessageSendSucceeded(update.message, update.oldMessageId);

        } else if (event instanceof UpdateMessageSendFailed) {

            UpdateMessageSendFailed update = (UpdateMessageSendFailed) event;

            onMessageSendFailed(update.message, update.oldMessageId, update.errorCode, update.errorMessage);

        } else if (event instanceof UpdateMessageContent) {

            UpdateMessageContent update = (UpdateMessageContent) event;

            onMessageContent(update.chatId, update.messageId, update.newContent);

        } else if (event instanceof UpdateMessageEdited) {

            UpdateMessageEdited update = (UpdateMessageEdited) event;

            onMessageEdited(update.chatId, update.messageId, update.editDate, update.replyMarkup);

        } else if (event instanceof UpdateMessageViews) {

            UpdateMessageViews update = (UpdateMessageViews) event;

            onMessageViews(update.chatId, update.messageId, update.views);

        } else if (event instanceof UpdateMessageContentOpened) {

            UpdateMessageContentOpened update = (UpdateMessageContentOpened) event;

            onMessageContentOpened(update.chatId, update.messageId);

        } else if (event instanceof UpdateMessageMentionRead) {

            UpdateMessageMentionRead update = (UpdateMessageMentionRead) event;

            onMessageMentionRead(update.chatId, update.messageId, update.unreadMentionCount);

        } else if (event instanceof UpdateNewChat) {

            UpdateNewChat update = (UpdateNewChat) event;

            onNewChat(update.chat);

        } else if (event instanceof UpdateChatTitle) {

            UpdateChatTitle update = (UpdateChatTitle) event;

            onChatTitle(update.chatId, update.title);

        } else if (event instanceof UpdateChatPhoto) {

            UpdateChatPhoto update = (UpdateChatPhoto) event;

            onChatPhoto(update.chatId, update.photo);

        } else if (event instanceof UpdateChatPermissions) {

            UpdateChatPermissions update = (UpdateChatPermissions) event;

            onChatPermissions(update.chatId, update.permissions);

        } else if (event instanceof UpdateChatLastMessage) {

            UpdateChatLastMessage update = (UpdateChatLastMessage) event;

            onChatLastMessage(update.chatId, update.lastMessage, update.order);

        } else if (event instanceof UpdateChatOrder) {

            UpdateChatOrder update = (UpdateChatOrder) event;

            onChatOrder(update.chatId, update.order);

        } else if (event instanceof UpdateChatIsPinned) {

            UpdateChatIsPinned update = (UpdateChatIsPinned) event;

            onChatIsPinned(update.chatId, update.isPinned, update.order);

        } else if (event instanceof UpdateChatIsMarkedAsUnread) {

            UpdateChatIsMarkedAsUnread update = (UpdateChatIsMarkedAsUnread) event;

            onChatIsMarkedAsUnread(update.chatId, update.isMarkedAsUnread);

        } else if (event instanceof UpdateChatIsSponsored) {

            UpdateChatIsSponsored update = (UpdateChatIsSponsored) event;

            onChatIsSponsored(update.chatId, update.isSponsored, update.order);

        } else if (event instanceof UpdateChatDefaultDisableNotification) {

            UpdateChatDefaultDisableNotification update = (UpdateChatDefaultDisableNotification) event;

            onChatDefaultDisableNotification(update.chatId, update.defaultDisableNotification);

        } else if (event instanceof UpdateChatReadInbox) {

            UpdateChatReadInbox update = (UpdateChatReadInbox) event;

            onChatReadInbox(update.chatId, update.lastReadInboxMessageId, update.unreadCount);

        } else if (event instanceof UpdateChatReadOutbox) {

            UpdateChatReadOutbox update = (UpdateChatReadOutbox) event;

            onChatReadOutbox(update.chatId, update.lastReadOutboxMessageId);

        } else if (event instanceof UpdateChatUnreadMentionCount) {

            UpdateChatUnreadMentionCount update = (UpdateChatUnreadMentionCount) event;

            onChatUnreadMentionCount(update.chatId, update.unreadMentionCount);

        } else if (event instanceof UpdateChatNotificationSettings) {

            UpdateChatNotificationSettings update = (UpdateChatNotificationSettings) event;

            onChatNotificationSettings(update.chatId, update.notificationSettings);

        } else if (event instanceof UpdateScopeNotificationSettings) {

            UpdateScopeNotificationSettings update = (UpdateScopeNotificationSettings) event;

            onScopeNotificationSettings(update.scope, update.notificationSettings);

        } else if (event instanceof UpdateChatPinnedMessage) {

            UpdateChatPinnedMessage update = (UpdateChatPinnedMessage) event;

            onChatPinnedMessage(update.chatId, update.pinnedMessageId);

        } else if (event instanceof UpdateChatReplyMarkup) {

            UpdateChatReplyMarkup update = (UpdateChatReplyMarkup) event;

            onChatReplyMarkup(update.chatId, update.replyMarkupMessageId);

        } else if (event instanceof UpdateChatDraftMessage) {

            UpdateChatDraftMessage update = (UpdateChatDraftMessage) event;

            onChatDraftMessage(update.chatId, update.draftMessage, update.order);

        } else if (event instanceof UpdateChatOnlineMemberCount) {

            UpdateChatOnlineMemberCount update = (UpdateChatOnlineMemberCount) event;

            onChatOnlineMemberCount(update.chatId, update.onlineMemberCount);

        } else if (event instanceof UpdateNotification) {

            UpdateNotification update = (UpdateNotification) event;

            onNotification(update.notificationGroupId, update.notification);

        } else if (event instanceof UpdateNotificationGroup) {

            UpdateNotificationGroup update = (UpdateNotificationGroup) event;

            onNotificationGroup(update.notificationGroupId, update.type, update.chatId, update.notificationSettingsChatId, update.isSilent, update.totalCount, update.addedNotifications, update.removedNotificationIds);

        } else if (event instanceof UpdateActiveNotifications) {

            UpdateActiveNotifications update = (UpdateActiveNotifications) event;

            onActiveNotifications(update.groups);

        } else if (event instanceof UpdateHavePendingNotifications) {

            UpdateHavePendingNotifications update = (UpdateHavePendingNotifications) event;

            onHavePendingNotifications(update.haveDelayedNotifications, update.haveUnreceivedNotifications);

        } else if (event instanceof UpdateDeleteMessages) {

            UpdateDeleteMessages update = (UpdateDeleteMessages) event;

            onDeleteMessages(update.chatId, update.messageIds, update.isPermanent, update.fromCache);

        } else if (event instanceof UpdateUserChatAction) {

            UpdateUserChatAction update = (UpdateUserChatAction) event;

            onUserChatAction(update.chatId, update.userId, update.action);

        } else if (event instanceof UpdateUserStatus) {

            UpdateUserStatus update = (UpdateUserStatus) event;

            onUserStatus(update.userId, update.status);

        } else if (event instanceof UpdateUser) {

            UpdateUser update = (UpdateUser) event;

            onUser(update.user);

        } else if (event instanceof UpdateBasicGroup) {

            UpdateBasicGroup update = (UpdateBasicGroup) event;

            onBasicGroup(update.basicGroup);

        } else if (event instanceof UpdateSupergroup) {

            UpdateSupergroup update = (UpdateSupergroup) event;

            onSupergroup(update.supergroup);

        } else if (event instanceof UpdateSecretChat) {

            UpdateSecretChat update = (UpdateSecretChat) event;

            onSecretChat(update.secretChat);

        } else if (event instanceof UpdateUserFullInfo) {

            UpdateUserFullInfo update = (UpdateUserFullInfo) event;

            onUserFullInfo(update.userId, update.userFullInfo);

        } else if (event instanceof UpdateBasicGroupFullInfo) {

            UpdateBasicGroupFullInfo update = (UpdateBasicGroupFullInfo) event;

            onBasicGroupFullInfo(update.basicGroupId, update.basicGroupFullInfo);

        } else if (event instanceof UpdateSupergroupFullInfo) {

            UpdateSupergroupFullInfo update = (UpdateSupergroupFullInfo) event;

            onSupergroupFullInfo(update.supergroupId, update.supergroupFullInfo);

        } else if (event instanceof UpdateServiceNotification) {

            UpdateServiceNotification update = (UpdateServiceNotification) event;

            onServiceNotification(update.type, update.content);

        } else if (event instanceof UpdateFile) {

            UpdateFile update = (UpdateFile) event;

            onFile(update.file);

        } else if (event instanceof UpdateFileGenerationStart) {

            UpdateFileGenerationStart update = (UpdateFileGenerationStart) event;

            onFileGenerationStart(update.generationId, update.originalPath, update.destinationPath, update.conversion);

        } else if (event instanceof UpdateFileGenerationStop) {

            UpdateFileGenerationStop update = (UpdateFileGenerationStop) event;

            onFileGenerationStop(update.generationId);

        } else if (event instanceof UpdateCall) {

            UpdateCall update = (UpdateCall) event;

            onCall(update.call);

        } else if (event instanceof UpdateUserPrivacySettingRules) {

            UpdateUserPrivacySettingRules update = (UpdateUserPrivacySettingRules) event;

            onUserPrivacySettingRules(update.setting, update.rules);

        } else if (event instanceof UpdateUnreadMessageCount) {

            UpdateUnreadMessageCount update = (UpdateUnreadMessageCount) event;

            onUnreadMessageCount(update.unreadCount, update.unreadUnmutedCount);

        } else if (event instanceof UpdateUnreadChatCount) {

            UpdateUnreadChatCount update = (UpdateUnreadChatCount) event;

            onUnreadChatCount(update.unreadCount, update.unreadUnmutedCount, update.markedAsUnreadCount, update.markedAsUnreadUnmutedCount);

        } else if (event instanceof UpdateOption) {

            UpdateOption update = (UpdateOption) event;

            onOption(update.name, update.value);

        } else if (event instanceof UpdateInstalledStickerSets) {

            UpdateInstalledStickerSets update = (UpdateInstalledStickerSets) event;

            onInstalledStickerSets(update.isMasks, update.stickerSetIds);

        } else if (event instanceof UpdateTrendingStickerSets) {

            UpdateTrendingStickerSets update = (UpdateTrendingStickerSets) event;

            onTrendingStickerSets(update.stickerSets);

        } else if (event instanceof UpdateRecentStickers) {

            UpdateRecentStickers update = (UpdateRecentStickers) event;

            onRecentStickers(update.isAttached, update.stickerIds);

        } else if (event instanceof UpdateFavoriteStickers) {

            UpdateFavoriteStickers update = (UpdateFavoriteStickers) event;

            onFavoriteStickers(update.stickerIds);

        } else if (event instanceof UpdateSavedAnimations) {

            UpdateSavedAnimations update = (UpdateSavedAnimations) event;

            onSavedAnimations(update.animationIds);

        } else if (event instanceof UpdateSelectedBackground) {

            UpdateSelectedBackground update = (UpdateSelectedBackground) event;

            onSelectedBackground(update.forDarkTheme, update.background);

        } else if (event instanceof UpdateLanguagePackStrings) {

            UpdateLanguagePackStrings update = (UpdateLanguagePackStrings) event;

            onLanguagePackStrings(update.localizationTarget, update.languagePackId, update.strings);

        } else if (event instanceof UpdateConnectionState) {

            UpdateConnectionState update = (UpdateConnectionState) event;

            onConnectionState(update.state);

        } else if (event instanceof UpdateTermsOfService) {

            UpdateTermsOfService update = (UpdateTermsOfService) event;

            onTermsOfService(update.termsOfServiceId, update.termsOfService);

        } else if (event instanceof UpdateNewInlineQuery) {

            UpdateNewInlineQuery update = (UpdateNewInlineQuery) event;

            onNewInlineQuery(update.id, update.senderUserId, update.userLocation, update.query, update.offset);

        } else if (event instanceof UpdateNewChosenInlineResult) {

            UpdateNewChosenInlineResult update = (UpdateNewChosenInlineResult) event;

            onNewChosenInlineResult(update.senderUserId, update.userLocation, update.query, update.resultId, update.inlineMessageId);

        } else if (event instanceof UpdateNewCallbackQuery) {

            UpdateNewCallbackQuery update = (UpdateNewCallbackQuery) event;

            handleNewCallbackQuery(update.id, update.senderUserId, update.chatId, update.messageId, update.chatInstance, update.payload);

        } else if (event instanceof UpdateNewInlineCallbackQuery) {

            UpdateNewInlineCallbackQuery update = (UpdateNewInlineCallbackQuery) event;

            onNewInlineCallbackQuery(update.id, update.senderUserId, update.inlineMessageId, update.chatInstance, update.payload);

        } else if (event instanceof UpdateNewShippingQuery) {

            UpdateNewShippingQuery update = (UpdateNewShippingQuery) event;

            onNewShippingQuery(update.id, update.senderUserId, update.invoicePayload, update.shippingAddress);

        } else if (event instanceof UpdateNewPreCheckoutQuery) {

            UpdateNewPreCheckoutQuery update = (UpdateNewPreCheckoutQuery) event;

            onNewPreCheckoutQuery(update.id, update.senderUserId, update.currency, update.totalAmount, update.invoicePayload, update.shippingOptionId, update.orderInfo);

        } else if (event instanceof UpdateNewCustomEvent) {

            UpdateNewCustomEvent update = (UpdateNewCustomEvent) event;

            onNewCustomEvent(update.event);

        } else if (event instanceof UpdateNewCustomQuery) {

            UpdateNewCustomQuery update = (UpdateNewCustomQuery) event;

            onNewCustomQuery(update.id, update.data, update.timeout);

        } else if (event instanceof UpdatePoll) {

            UpdatePoll update = (UpdatePoll) event;

            onPoll(update.poll);

        }

    }

    public void onAuthorizationState(AuthorizationState authorizationState) {
    }

    private void onNewMessage(Message message) {

        while (!client.hasAuthed()) {

            ThreadUtil.safeSleep(10);

        }

        if (!containsThis() && Fn.fromPrivate(message) && message.senderUserId == client.me.id) {

            return;

        }

        onNewMessage(message.senderUserId, message.chatId, message);

    }

    public boolean containsThis() {

        return false;

    }

    public void onNewMessage(int userId, long chatId, Message message) {
    }

    public void onMessageSendAcknowledged(long chatId, long messageId) {
    }

    public void onMessageSendSucceeded(Message message, long oldMessageId) {
    }

    public void onMessageSendFailed(Message message, long oldMessageId, int errorCode, String errorMessage) {
    }

    public void onMessageContent(long chatId, long messageId, MessageContent newContent) {
    }

    public void onMessageEdited(long chatId, long messageId, int editDate, ReplyMarkup replyMarkup) {
    }

    public void onMessageViews(long chatId, long messageId, int views) {
    }

    public void onMessageContentOpened(long chatId, long messageId) {
    }

    public void onMessageMentionRead(long chatId, long messageId, int unreadMentionCount) {
    }

    public void onNewChat(Chat chat) {
    }

    public void onChatTitle(long chatId, String title) {
    }

    public void onChatPhoto(long chatId, ChatPhoto photo) {
    }

    public void onChatPermissions(long chatId, ChatPermissions permissions) {
    }

    public void onChatLastMessage(long chatId, Message lastMessage, long order) {
    }

    public void onChatOrder(long chatId, long order) {
    }

    public void onChatIsPinned(long chatId, boolean isPinned, long order) {
    }

    public void onChatIsMarkedAsUnread(long chatId, boolean isMarkedAsUnread) {
    }

    public void onChatIsSponsored(long chatId, boolean isSponsored, long order) {
    }

    public void onChatDefaultDisableNotification(long chatId, boolean defaultDisableNotification) {
    }

    public void onChatReadInbox(long chatId, long lastReadInboxMessageId, int unreadCount) {
    }

    public void onChatReadOutbox(long chatId, long lastReadOutboxMessageId) {
    }

    public void onChatUnreadMentionCount(long chatId, int unreadMentionCount) {
    }

    public void onChatNotificationSettings(long chatId, ChatNotificationSettings notificationSettings) {
    }

    public void onScopeNotificationSettings(NotificationSettingsScope scope, ScopeNotificationSettings notificationSettings) {
    }

    public void onChatPinnedMessage(long chatId, long pinnedMessageId) {
    }

    public void onChatReplyMarkup(long chatId, long replyMarkupMessageId) {
    }

    public void onChatDraftMessage(long chatId, DraftMessage draftMessage, long order) {
    }

    public void onChatOnlineMemberCount(long chatId, int onlineMemberCount) {
    }

    public void onNotification(int notificationGroupId, Notification notification) {
    }

    public void onNotificationGroup(int notificationGroupId, NotificationGroupType type, long chatId, long notificationSettingsChatId, boolean isSilent, int totalCount, Notification[] addedNotifications, int[] removedNotificationIds) {
    }

    public void onActiveNotifications(NotificationGroup[] groups) {
    }

    public void onHavePendingNotifications(boolean haveDelayedNotifications, boolean haveUnreceivedNotifications) {
    }

    public void onDeleteMessages(long chatId, long[] messageIds, boolean isPermanent, boolean fromCache) {
    }

    public void onUserChatAction(long chatId, int userId, ChatAction action) {
    }

    public void onUserStatus(int userId, UserStatus status) {
    }

    public void onUser(User user) {
    }

    public void onBasicGroup(BasicGroup basicGroup) {
    }

    public void onSupergroup(Supergroup supergroup) {
    }

    public void onSecretChat(SecretChat secretChat) {
    }

    public void onUserFullInfo(int userId, UserFullInfo userFullInfo) {
    }

    public void onBasicGroupFullInfo(int basicGroupId, BasicGroupFullInfo basicGroupFullInfo) {
    }

    public void onSupergroupFullInfo(int supergroupId, SupergroupFullInfo supergroupFullInfo) {
    }

    public void onServiceNotification(String type, MessageContent content) {
    }

    public void onFile(File file) {
    }

    public void onFileGenerationStart(long generationId, String originalPath, String destinationPath, String conversion) {
    }

    public void onFileGenerationStop(long generationId) {
    }

    public void onCall(Call call) {
    }

    public void onUserPrivacySettingRules(UserPrivacySetting setting, UserPrivacySettingRules rules) {
    }

    public void onUnreadMessageCount(int unreadCount, int unreadUnmutedCount) {
    }

    public void onUnreadChatCount(int unreadCount, int unreadUnmutedCount, int markedAsUnreadCount, int markedAsUnreadUnmutedCount) {
    }

    public void onOption(String name, OptionValue value) {
    }

    public void onInstalledStickerSets(boolean isMasks, long[] stickerSetIds) {
    }

    public void onTrendingStickerSets(StickerSets stickerSets) {
    }

    public void onRecentStickers(boolean isAttached, int[] stickerIds) {
    }

    public void onFavoriteStickers(int[] stickerIds) {
    }

    public void onSavedAnimations(int[] animationIds) {
    }

    public void onSelectedBackground(boolean forDarkTheme, Background background) {
    }

    public void onLanguagePackStrings(String localizationTarget, String languagePackId, LanguagePackString[] strings) {
    }

    public void onConnectionState(ConnectionState state) {
    }

    public void onTermsOfService(String termsOfServiceId, TermsOfService termsOfService) {
    }

    public void onNewInlineQuery(long id, int senderUserId, Location userLocation, String query, String offset) {
    }

    public void onNewChosenInlineResult(int senderUserId, Location userLocation, String query, String resultId, String inlineMessageId) {
    }

    public void handleNewCallbackQuery(long id, int senderUserId, long chatId, long messageId, long chatInstance, CallbackQueryPayload payload) {
    }

    public void onNewInlineCallbackQuery(long id, int senderUserId, String inlineMessageId, long chatInstance, CallbackQueryPayload payload) {
    }

    public void onNewShippingQuery(long id, int senderUserId, String invoicePayload, Address shippingAddress) {
    }

    public void onNewPreCheckoutQuery(long id, int senderUserId, String currency, long totalAmount, byte[] invoicePayload, String shippingOptionId, OrderInfo orderInfo) {
    }

    public void onNewCustomEvent(String event) {
    }

    public void onNewCustomQuery(long id, String data, int timeout) {
    }

    public void onPoll(Poll poll) {
    }

    public void onLoad() {
    }

    public void onLogin() {
    }

    public void onDestroy() {
    }

    public void onPersistCancel(User user, long chatId, Message message, int subId) {

        ((TdBot) client).persists.remove(user.id);

        onPersistRemoved(user.id, subId, false);

        onPersistFinished(user.id, subId);

    }

    public void onPersistTimeouted(int userId, int subId) {

        onPersistRemoved(userId, subId, true);

        onPersistFinished(userId, subId);

    }

    public void onPersistRemoved(int userId, int subId, boolean timeout) {

        Lang L = Lang.get(userId);

        send(Fn.sendText(userId, Fn.plainText(timeout ? L.TIMEOUTED : L.CANCELED)));

    }

    public void onPersistFinished(int userId, int subId) {
    }

    public boolean rejectPersistStore(int userId, int subId) {

        return false;

    }
    
    public void onPersistStore(int userId, int subId, LinkedList<String> data) {
    }

    public void onPersistRestore(int userId, int subId, String[] data) {
    }

    public void handlePersistMessage(User user, long chatId, Message message, int subId, boolean acceptFunction) {

        if ((message.content instanceof MessageText)) {

            FormattedText content = ((MessageText) message.content).text;

            if (content.entities.length > 0 && content.entities[0].type instanceof TextEntityTypeBotCommand) {

                TextEntity command = content.entities[0];

                String function = content.text.substring(1, command.length);

                if (function.endsWith("@" + client.me.username)) {

                    function = function.substring(0, function.length() - client.me.username.length() - 1);

                }

                if ("cancel".equals(function)) {

                    onPersistCancel(user, chatId, message, subId);

                    return;

                } else if (!acceptFunction && subId != -1) {

                    String param = content.text.length() > command.length ? content.text.substring(command.length + 1) : null;

                    String[] params;
                    String[] originParams;

                    if (param == null) {

                        param = "";
                        params = originParams = new String[0];

                    } else {

                        originParams = param.split(" ");
                        params = param.replace("  ", " ").split(" ");

                    }

                    onPersistFunction(user, chatId, message, subId, function, param, params, originParams);

                    return;

                }

            }

        }

        if (subId == -1) {

            delay(message);

            delay(Fn.sendText(chatId, Fn.plainText(Lang.get(user).WAITING_LAST_FUNCTION)));

        } else {

            onPersistMessage(user, chatId, message, subId);

        }

    }

    public void onPersistMessage(User user, long chatId, Message message, int subId) {
    }

    public void onPersistFunction(User user, long chatId, Message message, int subId, String function, String param, String[] params, String[] originParams) {

        onPersistCancel(user, chatId, message, subId);

        client.processEvent(new UpdateNewMessage(message));

    }

    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {
    }

    public void onNewCallbackQuery(int userId, long chatId, long messageId, long queryId, int subId, byte[][] data) {
    }

    public void onPayload(User user, long chatId, Message message, String payload, String[] params) {
    }

    public void initFunction(String... names) {

        for (String name : names) {

            ((TdBot) client).functions.put(name, this);

        }

    }

    public void initData(int callbackDataId) {

        ((TdBot) client).callbacks.put(callbackDataId, this);

    }

    public void initPersist(int persistId) {

        ((TdBot) client).persistHandlers.put(persistId, this);

    }

    public String mkPayload(String... payload) {

        return "https://t.me/" + client.me.username + "?start=" + ArrayUtil.join(payload, "_");

    }

    public void initPayload(String prefix) {

        ((TdBot) client).payloads.put(prefix, this);

    }

    public void writePersist(User user, int dataId, int subId) {

        writePersist(user.id, dataId, subId);

    }

    public void writePersist(User user, int dataId, int subId, boolean acceptFunction) {

        writePersist(user.id, dataId, subId, acceptFunction);

    }

    public void writePersist(User user, int dataId) {

        writePersist(user.id, dataId);

    }

    public void writePersist(User user, int dataId, boolean acceptFunction) {

        writePersist(user.id, dataId, acceptFunction);
    }


    public void writePersist(int userId, int dataId) {

        writePersist(userId, dataId, 0, false);

    }

    public void writePersist(int userId, int dataId, boolean acceptFunction) {

        writePersist(userId, dataId, 0, acceptFunction);
    }


    public void writePersist(int userId, int dataId, int subId) {

        writePersist(userId, dataId, subId, false);

    }

    public void writePersist(int userId, int dataId, int subId, boolean acceptFunction) {

        ((TdBot) client).persists.put(userId, new TdPersistent(userId, dataId, subId, System.currentTimeMillis(), acceptFunction));

    }

    public TdPersistent removePersist(User user) {

        return removePersist(user.id);

    }

    public TdPersistent removePersist(int userId) {

        TdPersistent persist = ((TdBot) client).persists.remove(userId);

        if (persist != null) {
            
            onPersistFinished(userId,persist.subId);
            
            
        }
        
        return persist;
        
    }

    public TdCallback<Message> asStartMessage(Message message) {

        return asStartMessage(message.id);

    }

    public void insertStartMessages(long chatId, Message... messages) {

        for (Message message : messages) insertStartMessages(chatId, message.id);

    }

    public void insertStartMessages(long chatId, long... ids) {

        LinkedList<Long> array = ((TdBot) client).startMessages.get(chatId);

        for (long messageId : ids) array.add(messageId);

    }

    public TdCallback<Message> asStartMessage(final long... ids) {

        return new TdCallback<Message>() {

            @Override
            public void onCallback(boolean isOk, Message result, TdApi.Error error) {

                if (!isOk) return;

                insertStartMessages(result.chatId, ids);

                insertStartMessages(result.chatId, result.id);

            }

        };

    }

    public void insertStartPayloads(long chatId, String payload, Message... messages) {

        for (Message message : messages) insertStartPayloads(chatId, payload, message.id);

    }

    public void insertStartPayloads(long chatId, String payload, long... ids) {

        LongLongArrayMap map = ((TdBot) client).startPayloads.get(payload);

        if (map == null) {

            map = new LongLongArrayMap();

            ((TdBot) client).startPayloads.put(payload, map);

        }

        LinkedList<Long> array = map.get(chatId);

        for (long messageId : ids) array.add(messageId);


    }

    public TdCallback<Message> asStartPayload(String payload, Message message) {

        return asStartPayload(payload, message.id);

    }

    public TdCallback<Message> asStartPayload(final String payload, final long... ids) {

        return new TdCallback<Message>() {

            @Override
            public void onCallback(boolean isOk, Message result, TdApi.Error error) {

                if (!isOk) return;

                insertStartPayloads(result.chatId, payload, ids);

                insertStartPayloads(result.chatId, payload, result.id);


            }

        };

    }

    public void deleteStartMessages(long chatId) {

        LinkedList<Long> messages = ((TdBot) client).startMessages.remove(chatId);

        if (messages == null || messages.isEmpty()) return;

        for (long messageId : messages) I(Fn.deleteMessages(chatId, messageId));

    }

    public void deleteStartPayloads(long chatId, String payload) {

        LongLongArrayMap messages = ((TdBot) client).startPayloads.get(payload);

        if (messages == null || messages.isEmpty() || !messages.containsKey(chatId)) return;

        for (long messageId : messages.remove(chatId)) I(Fn.deleteMessages(chatId, messageId));

    }

    public <T extends TdHandler> T findHandler(Class<T> clazz) {

        return client.findHandler(clazz);

    }

    public static class Break extends RuntimeException {
    }

    public static class Reject extends RuntimeException {
    }

}
