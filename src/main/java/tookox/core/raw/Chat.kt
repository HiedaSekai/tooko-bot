@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns information about a chat by its identifier, this is an offline request if the current user is not a bot
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.getChat(
    chatId: Long
) = sync<Chat>(
    GetChat(
        chatId
    )
)

suspend fun TdAbsHandler.getChatOrNull(
    chatId: Long
) = syncOrNull<Chat>(
    GetChat(
        chatId
    )
)

fun TdAbsHandler.getChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    GetChat(
        chatId
    ),block = block
)

/**
 * Returns an ordered list of chats in a chat list
 * Chats are sorted by the pair (order, chat_id) in decreasing order
 * (For example, to get a list of chats from the beginning, the offset_order should be equal to a biggest signed 64-bit number 9223372036854775807 == 2^63 - 1)
 * For optimal performance the number of returned chats is chosen by the library
 *
 * @chatList - The chat list in which to return chats
 * @offsetOrder - Chat order to return chats from
 * @offsetChatId - Chat identifier to return chats from
 * @limit - The maximum number of chats to be returned
 *          It is possible that fewer chats than the limit are returned even if the end of the list is not reached
 */
suspend fun TdAbsHandler.getChats(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int
) = sync<Chats>(
    GetChats(
        chatList,
        offsetOrder,
        offsetChatId,
        limit
    )
)

suspend fun TdAbsHandler.getChatsOrNull(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int
) = syncOrNull<Chats>(
    GetChats(
        chatList,
        offsetOrder,
        offsetChatId,
        limit
    )
)

fun TdAbsHandler.getChats(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    GetChats(
        chatList,
        offsetOrder,
        offsetChatId,
        limit
    ),block = block
)

/**
 * Searches a public chat by its username
 * Currently only private chats, supergroups and channels can be public
 * Returns the chat if found
 * Otherwise an error is returned
 *
 * @username - Username to be resolved
 */
suspend fun TdAbsHandler.searchPublicChat(
    username: String? = null
) = sync<Chat>(
    SearchPublicChat(
        username
    )
)

suspend fun TdAbsHandler.searchPublicChatOrNull(
    username: String? = null
) = syncOrNull<Chat>(
    SearchPublicChat(
        username
    )
)

fun TdAbsHandler.searchPublicChat(
    username: String? = null,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    SearchPublicChat(
        username
    ),block = block
)

/**
 * Searches public chats by looking for specified query in their username and title
 * Currently only private chats, supergroups and channels can be public
 * Returns a meaningful number of results
 * Returns nothing if the length of the searched username prefix is less than 5
 * Excludes private chats with contacts and chats from the chat list from the results
 *
 * @query - Query to search for
 */
suspend fun TdAbsHandler.searchPublicChats(
    query: String? = null
) = sync<Chats>(
    SearchPublicChats(
        query
    )
)

suspend fun TdAbsHandler.searchPublicChatsOrNull(
    query: String? = null
) = syncOrNull<Chats>(
    SearchPublicChats(
        query
    )
)

fun TdAbsHandler.searchPublicChats(
    query: String? = null,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    SearchPublicChats(
        query
    ),block = block
)

/**
 * Searches for the specified query in the title and username of already known chats, this is an offline request
 * Returns chats in the order seen in the chat list
 *
 * @query - Query to search for
 *          If the query is empty, returns up to 20 recently found chats
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdAbsHandler.searchChats(
    query: String? = null,
    limit: Int
) = sync<Chats>(
    SearchChats(
        query,
        limit
    )
)

suspend fun TdAbsHandler.searchChatsOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull<Chats>(
    SearchChats(
        query,
        limit
    )
)

fun TdAbsHandler.searchChats(
    query: String? = null,
    limit: Int,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    SearchChats(
        query,
        limit
    ),block = block
)

/**
 * Searches for the specified query in the title and username of already known chats via request to the server
 * Returns chats in the order seen in the chat list
 *
 * @query - Query to search for
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdAbsHandler.searchChatsOnServer(
    query: String? = null,
    limit: Int
) = sync<Chats>(
    SearchChatsOnServer(
        query,
        limit
    )
)

suspend fun TdAbsHandler.searchChatsOnServerOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull<Chats>(
    SearchChatsOnServer(
        query,
        limit
    )
)

fun TdAbsHandler.searchChatsOnServer(
    query: String? = null,
    limit: Int,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    SearchChatsOnServer(
        query,
        limit
    ),block = block
)

/**
 * Returns a list of users and location-based supergroups nearby
 * The list of users nearby will be updated for 60 seconds after the request by the updates updateUsersNearby
 * The request should be sent again every 25 seconds with adjusted location to not miss new chats
 *
 * @location - Current user location
 */
suspend fun TdAbsHandler.searchChatsNearby(
    location: Location? = null
) = sync<ChatsNearby>(
    SearchChatsNearby(
        location
    )
)

suspend fun TdAbsHandler.searchChatsNearbyOrNull(
    location: Location? = null
) = syncOrNull<ChatsNearby>(
    SearchChatsNearby(
        location
    )
)

fun TdAbsHandler.searchChatsNearby(
    location: Location? = null,
    block: (suspend CoroutineScope.(ChatsNearby) -> Unit)
) = send(
    SearchChatsNearby(
        location
    ),block = block
)

/**
 * Returns a list of frequently used chats
 * Supported only if the chat info database is enabled
 *
 * @category - Category of chats to be returned
 * @limit - The maximum number of chats to be returned
 *          Up to 30
 */
suspend fun TdAbsHandler.getTopChats(
    category: TopChatCategory? = null,
    limit: Int
) = sync<Chats>(
    GetTopChats(
        category,
        limit
    )
)

suspend fun TdAbsHandler.getTopChatsOrNull(
    category: TopChatCategory? = null,
    limit: Int
) = syncOrNull<Chats>(
    GetTopChats(
        category,
        limit
    )
)

fun TdAbsHandler.getTopChats(
    category: TopChatCategory? = null,
    limit: Int,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    GetTopChats(
        category,
        limit
    ),block = block
)

/**
 * Removes a chat from the list of frequently used chats
 * Supported only if the chat info database is enabled
 *
 * @category - Category of frequently used chats
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.removeTopChat(
    category: TopChatCategory? = null,
    chatId: Long
) = sync<Ok>(
    RemoveTopChat(
        category,
        chatId
    )
)

suspend fun TdAbsHandler.removeTopChatOrNull(
    category: TopChatCategory? = null,
    chatId: Long
) = syncOrNull<Ok>(
    RemoveTopChat(
        category,
        chatId
    )
)

fun TdAbsHandler.removeTopChat(
    category: TopChatCategory? = null,
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveTopChat(
        category,
        chatId
    ),block = block
)

/**
 * Adds a chat to the list of recently found chats
 * The chat is added to the beginning of the list
 * If the chat is already in the list, it will be removed from the list first
 *
 * @chatId - Identifier of the chat to add
 */
suspend fun TdAbsHandler.addRecentlyFoundChat(
    chatId: Long
) = sync<Ok>(
    AddRecentlyFoundChat(
        chatId
    )
)

suspend fun TdAbsHandler.addRecentlyFoundChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    AddRecentlyFoundChat(
        chatId
    )
)

fun TdAbsHandler.addRecentlyFoundChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddRecentlyFoundChat(
        chatId
    ),block = block
)

/**
 * Removes a chat from the list of recently found chats
 *
 * @chatId - Identifier of the chat to be removed
 */
suspend fun TdAbsHandler.removeRecentlyFoundChat(
    chatId: Long
) = sync<Ok>(
    RemoveRecentlyFoundChat(
        chatId
    )
)

suspend fun TdAbsHandler.removeRecentlyFoundChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    RemoveRecentlyFoundChat(
        chatId
    )
)

fun TdAbsHandler.removeRecentlyFoundChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveRecentlyFoundChat(
        chatId
    ),block = block
)

/**
 * Clears the list of recently found chats
 */
suspend fun TdAbsHandler.clearRecentlyFoundChats() = sync<Ok>(
    ClearRecentlyFoundChats()
)

suspend fun TdAbsHandler.clearRecentlyFoundChatsOrNull() = syncOrNull<Ok>(
    ClearRecentlyFoundChats()
)

fun TdAbsHandler.clearRecentlyFoundChats(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ClearRecentlyFoundChats(),block = block
)

/**
 * Returns a list of public chats of the specified type, owned by the user
 *
 * @type - Type of the public chats to return
 */
suspend fun TdAbsHandler.getCreatedPublicChats(
    type: PublicChatType? = null
) = sync<Chats>(
    GetCreatedPublicChats(
        type
    )
)

suspend fun TdAbsHandler.getCreatedPublicChatsOrNull(
    type: PublicChatType? = null
) = syncOrNull<Chats>(
    GetCreatedPublicChats(
        type
    )
)

fun TdAbsHandler.getCreatedPublicChats(
    type: PublicChatType? = null,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    GetCreatedPublicChats(
        type
    ),block = block
)

/**
 * Returns a list of basic group and supergroup chats, which can be used as a discussion group for a channel
 * Basic group chats need to be first upgraded to supergroups before they can be set as a discussion group
 */
suspend fun TdAbsHandler.getSuitableDiscussionChats() = sync<Chats>(
    GetSuitableDiscussionChats()
)

suspend fun TdAbsHandler.getSuitableDiscussionChatsOrNull() = syncOrNull<Chats>(
    GetSuitableDiscussionChats()
)

fun TdAbsHandler.getSuitableDiscussionChats(
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    GetSuitableDiscussionChats(),block = block
)

/**
 * Returns a list of recently inactive supergroups and channels
 * Can be used when user reaches limit on the number of joined supergroups and channels and receives CHANNELS_TOO_MUCH error
 */
suspend fun TdAbsHandler.getInactiveSupergroupChats() = sync<Chats>(
    GetInactiveSupergroupChats()
)

suspend fun TdAbsHandler.getInactiveSupergroupChatsOrNull() = syncOrNull<Chats>(
    GetInactiveSupergroupChats()
)

fun TdAbsHandler.getInactiveSupergroupChats(
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    GetInactiveSupergroupChats(),block = block
)

/**
 * Returns a list of common group chats with a given user
 * Chats are sorted by their type and creation date
 *
 * @userId - User identifier
 * @offsetChatId - Chat identifier starting from which to return chats
 *                 Use 0 for the first request
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdAbsHandler.getGroupsInCommon(
    userId: Int,
    offsetChatId: Long,
    limit: Int
) = sync<Chats>(
    GetGroupsInCommon(
        userId,
        offsetChatId,
        limit
    )
)

suspend fun TdAbsHandler.getGroupsInCommonOrNull(
    userId: Int,
    offsetChatId: Long,
    limit: Int
) = syncOrNull<Chats>(
    GetGroupsInCommon(
        userId,
        offsetChatId,
        limit
    )
)

fun TdAbsHandler.getGroupsInCommon(
    userId: Int,
    offsetChatId: Long,
    limit: Int,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    GetGroupsInCommon(
        userId,
        offsetChatId,
        limit
    ),block = block
)

/**
 * Deletes all messages in the chat
 * Use Chat.can_be_deleted_only_for_self and Chat.can_be_deleted_for_all_users fields to find whether and how the method can be applied to the chat
 *
 * @chatId - Chat identifier
 * @removeFromChatList - Pass true if the chat should be removed from the chat list
 * @revoke - Pass true to try to delete chat history for all users
 */
suspend fun TdAbsHandler.deleteChatHistory(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean
) = sync<Ok>(
    DeleteChatHistory(
        chatId,
        removeFromChatList,
        revoke
    )
)

suspend fun TdAbsHandler.deleteChatHistoryOrNull(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean
) = syncOrNull<Ok>(
    DeleteChatHistory(
        chatId,
        removeFromChatList,
        revoke
    )
)

fun TdAbsHandler.deleteChatHistory(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteChatHistory(
        chatId,
        removeFromChatList,
        revoke
    ),block = block
)

/**
 * Returns approximate number of messages of the specified type in the chat
 *
 * @chatId - Identifier of the chat in which to count messages
 * @filter - Filter for message content
 *           SearchMessagesFilterEmpty is unsupported in this function
 * @returnLocal - If true, returns count that is available locally without sending network requests, returning -1 if the number of messages is unknown
 */
suspend fun TdAbsHandler.getChatMessageCount(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean
) = sync<Count>(
    GetChatMessageCount(
        chatId,
        filter,
        returnLocal
    )
)

suspend fun TdAbsHandler.getChatMessageCountOrNull(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean
) = syncOrNull<Count>(
    GetChatMessageCount(
        chatId,
        filter,
        returnLocal
    )
)

fun TdAbsHandler.getChatMessageCount(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean,
    block: (suspend CoroutineScope.(Count) -> Unit)
) = send(
    GetChatMessageCount(
        chatId,
        filter,
        returnLocal
    ),block = block
)

/**
 * Deletes all messages sent by the specified user to a chat
 * Supported only for supergroups
 * Requires can_delete_messages administrator privileges
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 */
suspend fun TdAbsHandler.deleteChatMessagesFromUser(
    chatId: Long,
    userId: Int
) = sync<Ok>(
    DeleteChatMessagesFromUser(
        chatId,
        userId
    )
)

suspend fun TdAbsHandler.deleteChatMessagesFromUserOrNull(
    chatId: Long,
    userId: Int
) = syncOrNull<Ok>(
    DeleteChatMessagesFromUser(
        chatId,
        userId
    )
)

fun TdAbsHandler.deleteChatMessagesFromUser(
    chatId: Long,
    userId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteChatMessagesFromUser(
        chatId,
        userId
    ),block = block
)

/**
 * Deletes the default reply markup from a chat
 * Must be called after a one-time keyboard or a ForceReply reply markup has been used
 * UpdateChatReplyMarkup will be sent if the reply markup will be changed
 *
 * @chatId - Chat identifier
 * @messageId - The message identifier of the used keyboard
 */
suspend fun TdAbsHandler.deleteChatReplyMarkup(
    chatId: Long,
    messageId: Long
) = sync<Ok>(
    DeleteChatReplyMarkup(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.deleteChatReplyMarkupOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<Ok>(
    DeleteChatReplyMarkup(
        chatId,
        messageId
    )
)

fun TdAbsHandler.deleteChatReplyMarkup(
    chatId: Long,
    messageId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteChatReplyMarkup(
        chatId,
        messageId
    ),block = block
)

/**
 * Informs TDLib that the chat is opened by the user
 * Many useful activities depend on the chat being opened or closed (e.g., in supergroups and channels all updates are received only for opened chats)
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.openChat(
    chatId: Long
) = sync<Ok>(
    OpenChat(
        chatId
    )
)

suspend fun TdAbsHandler.openChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    OpenChat(
        chatId
    )
)

fun TdAbsHandler.openChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    OpenChat(
        chatId
    ),block = block
)

/**
 * Informs TDLib that the chat is closed by the user
 * Many useful activities depend on the chat being opened or closed
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.closeChat(
    chatId: Long
) = sync<Ok>(
    CloseChat(
        chatId
    )
)

suspend fun TdAbsHandler.closeChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    CloseChat(
        chatId
    )
)

fun TdAbsHandler.closeChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CloseChat(
        chatId
    ),block = block
)

/**
 * Marks all mentions in a chat as read
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.readAllChatMentions(
    chatId: Long
) = sync<Ok>(
    ReadAllChatMentions(
        chatId
    )
)

suspend fun TdAbsHandler.readAllChatMentionsOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    ReadAllChatMentions(
        chatId
    )
)

fun TdAbsHandler.readAllChatMentions(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ReadAllChatMentions(
        chatId
    ),block = block
)

/**
 * Returns an existing chat corresponding to a given user
 *
 * @userId - User identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdAbsHandler.createPrivateChat(
    userId: Int,
    force: Boolean
) = sync<Chat>(
    CreatePrivateChat(
        userId,
        force
    )
)

suspend fun TdAbsHandler.createPrivateChatOrNull(
    userId: Int,
    force: Boolean
) = syncOrNull<Chat>(
    CreatePrivateChat(
        userId,
        force
    )
)

fun TdAbsHandler.createPrivateChat(
    userId: Int,
    force: Boolean,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    CreatePrivateChat(
        userId,
        force
    ),block = block
)

/**
 * Returns an existing chat corresponding to a known basic group
 *
 * @basicGroupId - Basic group identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdAbsHandler.createBasicGroupChat(
    basicGroupId: Int,
    force: Boolean
) = sync<Chat>(
    CreateBasicGroupChat(
        basicGroupId,
        force
    )
)

suspend fun TdAbsHandler.createBasicGroupChatOrNull(
    basicGroupId: Int,
    force: Boolean
) = syncOrNull<Chat>(
    CreateBasicGroupChat(
        basicGroupId,
        force
    )
)

fun TdAbsHandler.createBasicGroupChat(
    basicGroupId: Int,
    force: Boolean,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    CreateBasicGroupChat(
        basicGroupId,
        force
    ),block = block
)

/**
 * Returns an existing chat corresponding to a known supergroup or channel
 *
 * @supergroupId - Supergroup or channel identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdAbsHandler.createSupergroupChat(
    supergroupId: Int,
    force: Boolean
) = sync<Chat>(
    CreateSupergroupChat(
        supergroupId,
        force
    )
)

suspend fun TdAbsHandler.createSupergroupChatOrNull(
    supergroupId: Int,
    force: Boolean
) = syncOrNull<Chat>(
    CreateSupergroupChat(
        supergroupId,
        force
    )
)

fun TdAbsHandler.createSupergroupChat(
    supergroupId: Int,
    force: Boolean,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    CreateSupergroupChat(
        supergroupId,
        force
    ),block = block
)

/**
 * Returns an existing chat corresponding to a known secret chat
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdAbsHandler.createSecretChat(
    secretChatId: Int
) = sync<Chat>(
    CreateSecretChat(
        secretChatId
    )
)

suspend fun TdAbsHandler.createSecretChatOrNull(
    secretChatId: Int
) = syncOrNull<Chat>(
    CreateSecretChat(
        secretChatId
    )
)

fun TdAbsHandler.createSecretChat(
    secretChatId: Int,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    CreateSecretChat(
        secretChatId
    ),block = block
)

/**
 * Creates a new basic group and sends a corresponding messageBasicGroupChatCreate
 * Returns the newly created chat
 *
 * @userIds - Identifiers of users to be added to the basic group
 * @title - Title of the new basic group
 */
suspend fun TdAbsHandler.createNewBasicGroupChat(
    userIds: IntArray,
    title: String? = null
) = sync<Chat>(
    CreateNewBasicGroupChat(
        userIds,
        title
    )
)

suspend fun TdAbsHandler.createNewBasicGroupChatOrNull(
    userIds: IntArray,
    title: String? = null
) = syncOrNull<Chat>(
    CreateNewBasicGroupChat(
        userIds,
        title
    )
)

fun TdAbsHandler.createNewBasicGroupChat(
    userIds: IntArray,
    title: String? = null,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    CreateNewBasicGroupChat(
        userIds,
        title
    ),block = block
)

/**
 * Creates a new supergroup or channel and sends a corresponding messageSupergroupChatCreate
 * Returns the newly created chat
 *
 * @title - Title of the new chat
 * @isChannel - True, if a channel chat should be created
 * @description - Chat description
 * @location - Chat location if a location-based supergroup is being created
 */
suspend fun TdAbsHandler.createNewSupergroupChat(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null
) = sync<Chat>(
    CreateNewSupergroupChat(
        title,
        isChannel,
        description,
        location
    )
)

suspend fun TdAbsHandler.createNewSupergroupChatOrNull(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null
) = syncOrNull<Chat>(
    CreateNewSupergroupChat(
        title,
        isChannel,
        description,
        location
    )
)

fun TdAbsHandler.createNewSupergroupChat(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    CreateNewSupergroupChat(
        title,
        isChannel,
        description,
        location
    ),block = block
)

/**
 * Creates a new secret chat
 * Returns the newly created chat
 *
 * @userId - Identifier of the target user
 */
suspend fun TdAbsHandler.createNewSecretChat(
    userId: Int
) = sync<Chat>(
    CreateNewSecretChat(
        userId
    )
)

suspend fun TdAbsHandler.createNewSecretChatOrNull(
    userId: Int
) = syncOrNull<Chat>(
    CreateNewSecretChat(
        userId
    )
)

fun TdAbsHandler.createNewSecretChat(
    userId: Int,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    CreateNewSecretChat(
        userId
    ),block = block
)

/**
 * Creates a new supergroup from an existing basic group and sends a corresponding messageChatUpgradeTo and messageChatUpgradeFrom
 * Requires creator privileges
 * Deactivates the original basic group
 *
 * @chatId - Identifier of the chat to upgrade
 */
suspend fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChat(
    chatId: Long
) = sync<Chat>(
    UpgradeBasicGroupChatToSupergroupChat(
        chatId
    )
)

suspend fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChatOrNull(
    chatId: Long
) = syncOrNull<Chat>(
    UpgradeBasicGroupChatToSupergroupChat(
        chatId
    )
)

fun TdAbsHandler.upgradeBasicGroupChatToSupergroupChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    UpgradeBasicGroupChatToSupergroupChat(
        chatId
    ),block = block
)

/**
 * Moves a chat to a different chat list
 * Current chat list of the chat must ne non-null
 *
 * @chatId - Chat identifier
 * @chatList - New chat list of the chat
 */
suspend fun TdAbsHandler.setChatChatList(
    chatId: Long,
    chatList: ChatList? = null
) = sync<Ok>(
    SetChatChatList(
        chatId,
        chatList
    )
)

suspend fun TdAbsHandler.setChatChatListOrNull(
    chatId: Long,
    chatList: ChatList? = null
) = syncOrNull<Ok>(
    SetChatChatList(
        chatId,
        chatList
    )
)

fun TdAbsHandler.setChatChatList(
    chatId: Long,
    chatList: ChatList? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatChatList(
        chatId,
        chatList
    ),block = block
)

/**
 * Changes the chat title
 * Supported only for basic groups, supergroups and channels
 * Requires can_change_info rights
 * The title will not be changed until the request to the server has been completed
 *
 * @chatId - Chat identifier
 * @title - New title of the chat
 */
suspend fun TdAbsHandler.setChatTitle(
    chatId: Long,
    title: String? = null
) = sync<Ok>(
    SetChatTitle(
        chatId,
        title
    )
)

suspend fun TdAbsHandler.setChatTitleOrNull(
    chatId: Long,
    title: String? = null
) = syncOrNull<Ok>(
    SetChatTitle(
        chatId,
        title
    )
)

fun TdAbsHandler.setChatTitle(
    chatId: Long,
    title: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatTitle(
        chatId,
        title
    ),block = block
)

/**
 * Changes the photo of a chat
 * Supported only for basic groups, supergroups and channels
 * Requires can_change_info rights
 * The photo will not be changed before request to the server has been completed
 *
 * @chatId - Chat identifier
 * @photo - New chat photo
 *          You can use a zero InputFileId to delete the chat photo
 *          Files that are accessible only by HTTP URL are not acceptable
 */
suspend fun TdAbsHandler.setChatPhoto(
    chatId: Long,
    photo: InputFile? = null
) = sync<Ok>(
    SetChatPhoto(
        chatId,
        photo
    )
)

suspend fun TdAbsHandler.setChatPhotoOrNull(
    chatId: Long,
    photo: InputFile? = null
) = syncOrNull<Ok>(
    SetChatPhoto(
        chatId,
        photo
    )
)

fun TdAbsHandler.setChatPhoto(
    chatId: Long,
    photo: InputFile? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatPhoto(
        chatId,
        photo
    ),block = block
)

/**
 * Changes the chat members permissions
 * Supported only for basic groups and supergroups
 * Requires can_restrict_members administrator right
 *
 * @chatId - Chat identifier
 * @permissions - New non-administrator members permissions in the chat
 */
suspend fun TdAbsHandler.setChatPermissions(
    chatId: Long,
    permissions: ChatPermissions? = null
) = sync<Ok>(
    SetChatPermissions(
        chatId,
        permissions
    )
)

suspend fun TdAbsHandler.setChatPermissionsOrNull(
    chatId: Long,
    permissions: ChatPermissions? = null
) = syncOrNull<Ok>(
    SetChatPermissions(
        chatId,
        permissions
    )
)

fun TdAbsHandler.setChatPermissions(
    chatId: Long,
    permissions: ChatPermissions? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatPermissions(
        chatId,
        permissions
    ),block = block
)

/**
 * Changes the draft message in a chat
 *
 * @chatId - Chat identifier
 * @draftMessage - New draft message
 */
suspend fun TdAbsHandler.setChatDraftMessage(
    chatId: Long,
    draftMessage: DraftMessage? = null
) = sync<Ok>(
    SetChatDraftMessage(
        chatId,
        draftMessage
    )
)

suspend fun TdAbsHandler.setChatDraftMessageOrNull(
    chatId: Long,
    draftMessage: DraftMessage? = null
) = syncOrNull<Ok>(
    SetChatDraftMessage(
        chatId,
        draftMessage
    )
)

fun TdAbsHandler.setChatDraftMessage(
    chatId: Long,
    draftMessage: DraftMessage? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatDraftMessage(
        chatId,
        draftMessage
    ),block = block
)

/**
 * Changes the notification settings of a chat
 * Notification settings of a chat with the current user (Saved Messages) can't be changed
 *
 * @chatId - Chat identifier
 * @notificationSettings - New notification settings for the chat
 *                         If the chat is muted for more than 1 week, it is considered to be muted forever
 */
suspend fun TdAbsHandler.setChatNotificationSettings(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null
) = sync<Ok>(
    SetChatNotificationSettings(
        chatId,
        notificationSettings
    )
)

suspend fun TdAbsHandler.setChatNotificationSettingsOrNull(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null
) = syncOrNull<Ok>(
    SetChatNotificationSettings(
        chatId,
        notificationSettings
    )
)

fun TdAbsHandler.setChatNotificationSettings(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatNotificationSettings(
        chatId,
        notificationSettings
    ),block = block
)

/**
 * Changes the pinned state of a chat
 * You can pin up to GetOption("pinned_chat_count_max")/GetOption("pinned_archived_chat_count_max") non-secret chats and the same number of secret chats in the main/archive chat list
 *
 * @chatId - Chat identifier
 * @isPinned - New value of is_pinned
 */
suspend fun TdAbsHandler.toggleChatIsPinned(
    chatId: Long,
    isPinned: Boolean
) = sync<Ok>(
    ToggleChatIsPinned(
        chatId,
        isPinned
    )
)

suspend fun TdAbsHandler.toggleChatIsPinnedOrNull(
    chatId: Long,
    isPinned: Boolean
) = syncOrNull<Ok>(
    ToggleChatIsPinned(
        chatId,
        isPinned
    )
)

fun TdAbsHandler.toggleChatIsPinned(
    chatId: Long,
    isPinned: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ToggleChatIsPinned(
        chatId,
        isPinned
    ),block = block
)

/**
 * Changes the marked as unread state of a chat
 *
 * @chatId - Chat identifier
 * @isMarkedAsUnread - New value of is_marked_as_unread
 */
suspend fun TdAbsHandler.toggleChatIsMarkedAsUnread(
    chatId: Long,
    isMarkedAsUnread: Boolean
) = sync<Ok>(
    ToggleChatIsMarkedAsUnread(
        chatId,
        isMarkedAsUnread
    )
)

suspend fun TdAbsHandler.toggleChatIsMarkedAsUnreadOrNull(
    chatId: Long,
    isMarkedAsUnread: Boolean
) = syncOrNull<Ok>(
    ToggleChatIsMarkedAsUnread(
        chatId,
        isMarkedAsUnread
    )
)

fun TdAbsHandler.toggleChatIsMarkedAsUnread(
    chatId: Long,
    isMarkedAsUnread: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ToggleChatIsMarkedAsUnread(
        chatId,
        isMarkedAsUnread
    ),block = block
)

/**
 * Changes the value of the default disable_notification parameter, used when a message is sent to a chat
 *
 * @chatId - Chat identifier
 * @defaultDisableNotification - New value of default_disable_notification
 */
suspend fun TdAbsHandler.toggleChatDefaultDisableNotification(
    chatId: Long,
    defaultDisableNotification: Boolean
) = sync<Ok>(
    ToggleChatDefaultDisableNotification(
        chatId,
        defaultDisableNotification
    )
)

suspend fun TdAbsHandler.toggleChatDefaultDisableNotificationOrNull(
    chatId: Long,
    defaultDisableNotification: Boolean
) = syncOrNull<Ok>(
    ToggleChatDefaultDisableNotification(
        chatId,
        defaultDisableNotification
    )
)

fun TdAbsHandler.toggleChatDefaultDisableNotification(
    chatId: Long,
    defaultDisableNotification: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ToggleChatDefaultDisableNotification(
        chatId,
        defaultDisableNotification
    ),block = block
)

/**
 * Changes client data associated with a chat
 *
 * @chatId - Chat identifier
 * @clientData - New value of client_data
 */
suspend fun TdAbsHandler.setChatClientData(
    chatId: Long,
    clientData: String? = null
) = sync<Ok>(
    SetChatClientData(
        chatId,
        clientData
    )
)

suspend fun TdAbsHandler.setChatClientDataOrNull(
    chatId: Long,
    clientData: String? = null
) = syncOrNull<Ok>(
    SetChatClientData(
        chatId,
        clientData
    )
)

fun TdAbsHandler.setChatClientData(
    chatId: Long,
    clientData: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatClientData(
        chatId,
        clientData
    ),block = block
)

/**
 * Changes information about a chat
 * Available for basic groups, supergroups, and channels
 * Requires can_change_info rights
 *
 * @chatId - Identifier of the chat
 * @description - New chat description
 */
suspend fun TdAbsHandler.setChatDescription(
    chatId: Long,
    description: String? = null
) = sync<Ok>(
    SetChatDescription(
        chatId,
        description
    )
)

suspend fun TdAbsHandler.setChatDescriptionOrNull(
    chatId: Long,
    description: String? = null
) = syncOrNull<Ok>(
    SetChatDescription(
        chatId,
        description
    )
)

fun TdAbsHandler.setChatDescription(
    chatId: Long,
    description: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatDescription(
        chatId,
        description
    ),block = block
)

/**
 * Changes the discussion group of a channel chat
 * Requires can_change_info rights in the channel if it is specified
 *
 * @chatId - Identifier of the channel chat
 *           Pass 0 to remove a link from the supergroup passed in the second argument to a linked channel chat (requires can_pin_messages rights in the supergroup)
 * @discussionChatId - Identifier of a new channel's discussion group
 *                     Use 0 to remove the discussion group
 *                     Use the method getSuitableDiscussionChats to find all suitable groups
 *                     Basic group chats needs to be first upgraded to supergroup chats
 *                     If new chat members don't have access to old messages in the supergroup, then toggleSupergroupIsAllHistoryAvailable needs to be used first to change that
 */
suspend fun TdAbsHandler.setChatDiscussionGroup(
    chatId: Long,
    discussionChatId: Long
) = sync<Ok>(
    SetChatDiscussionGroup(
        chatId,
        discussionChatId
    )
)

suspend fun TdAbsHandler.setChatDiscussionGroupOrNull(
    chatId: Long,
    discussionChatId: Long
) = syncOrNull<Ok>(
    SetChatDiscussionGroup(
        chatId,
        discussionChatId
    )
)

fun TdAbsHandler.setChatDiscussionGroup(
    chatId: Long,
    discussionChatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatDiscussionGroup(
        chatId,
        discussionChatId
    ),block = block
)

/**
 * Changes the location of a chat
 * Available only for some location-based supergroups, use supergroupFullInfo.can_set_location to check whether the method is allowed to use
 *
 * @chatId - Chat identifier
 * @location - New location for the chat
 *             Must be valid and not null
 */
suspend fun TdAbsHandler.setChatLocation(
    chatId: Long,
    location: ChatLocation? = null
) = sync<Ok>(
    SetChatLocation(
        chatId,
        location
    )
)

suspend fun TdAbsHandler.setChatLocationOrNull(
    chatId: Long,
    location: ChatLocation? = null
) = syncOrNull<Ok>(
    SetChatLocation(
        chatId,
        location
    )
)

fun TdAbsHandler.setChatLocation(
    chatId: Long,
    location: ChatLocation? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatLocation(
        chatId,
        location
    ),block = block
)

/**
 * Changes the slow mode delay of a chat
 * Available only for supergroups
 * Requires can_restrict_members rights
 *
 * @chatId - Chat identifier
 * @slowModeDelay - New slow mode delay for the chat
 *                  Must be one of 0, 10, 30, 60, 300, 900, 3600
 */
suspend fun TdAbsHandler.setChatSlowModeDelay(
    chatId: Long,
    slowModeDelay: Int
) = sync<Ok>(
    SetChatSlowModeDelay(
        chatId,
        slowModeDelay
    )
)

suspend fun TdAbsHandler.setChatSlowModeDelayOrNull(
    chatId: Long,
    slowModeDelay: Int
) = syncOrNull<Ok>(
    SetChatSlowModeDelay(
        chatId,
        slowModeDelay
    )
)

fun TdAbsHandler.setChatSlowModeDelay(
    chatId: Long,
    slowModeDelay: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatSlowModeDelay(
        chatId,
        slowModeDelay
    ),block = block
)

/**
 * Pins a message in a chat
 * Requires can_pin_messages rights
 *
 * @chatId - Identifier of the chat
 * @messageId - Identifier of the new pinned message
 * @disableNotification - True, if there should be no notification about the pinned message
 */
suspend fun TdAbsHandler.pinChatMessage(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean
) = sync<Ok>(
    PinChatMessage(
        chatId,
        messageId,
        disableNotification
    )
)

suspend fun TdAbsHandler.pinChatMessageOrNull(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean
) = syncOrNull<Ok>(
    PinChatMessage(
        chatId,
        messageId,
        disableNotification
    )
)

fun TdAbsHandler.pinChatMessage(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    PinChatMessage(
        chatId,
        messageId,
        disableNotification
    ),block = block
)

/**
 * Removes the pinned message from a chat
 * Requires can_pin_messages rights in the group or channel
 *
 * @chatId - Identifier of the chat
 */
suspend fun TdAbsHandler.unpinChatMessage(
    chatId: Long
) = sync<Ok>(
    UnpinChatMessage(
        chatId
    )
)

suspend fun TdAbsHandler.unpinChatMessageOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    UnpinChatMessage(
        chatId
    )
)

fun TdAbsHandler.unpinChatMessage(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    UnpinChatMessage(
        chatId
    ),block = block
)

/**
 * Adds current user as a new member to a chat
 * Private and secret chats can't be joined using this method
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.joinChat(
    chatId: Long
) = sync<Ok>(
    JoinChat(
        chatId
    )
)

suspend fun TdAbsHandler.joinChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    JoinChat(
        chatId
    )
)

fun TdAbsHandler.joinChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    JoinChat(
        chatId
    ),block = block
)

/**
 * Removes current user from chat members
 * Private and secret chats can't be left using this method
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.leaveChat(
    chatId: Long
) = sync<Ok>(
    LeaveChat(
        chatId
    )
)

suspend fun TdAbsHandler.leaveChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    LeaveChat(
        chatId
    )
)

fun TdAbsHandler.leaveChat(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    LeaveChat(
        chatId
    ),block = block
)

/**
 * Adds a new member to a chat
 * Members can't be added to private or secret chats
 * Members will not be added until the chat state has been synchronized with the server
 *
 * @chatId - Chat identifier
 * @userId - Identifier of the user
 * @forwardLimit - The number of earlier messages from the chat to be forwarded to the new member
 *                 Ignored for supergroups and channels
 */
suspend fun TdAbsHandler.addChatMember(
    chatId: Long,
    userId: Int,
    forwardLimit: Int
) = sync<Ok>(
    AddChatMember(
        chatId,
        userId,
        forwardLimit
    )
)

suspend fun TdAbsHandler.addChatMemberOrNull(
    chatId: Long,
    userId: Int,
    forwardLimit: Int
) = syncOrNull<Ok>(
    AddChatMember(
        chatId,
        userId,
        forwardLimit
    )
)

fun TdAbsHandler.addChatMember(
    chatId: Long,
    userId: Int,
    forwardLimit: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddChatMember(
        chatId,
        userId,
        forwardLimit
    ),block = block
)

/**
 * Adds multiple new members to a chat
 * Currently this option is only available for supergroups and channels
 * This option can't be used to join a chat
 * Members can't be added to a channel if it has more than 200 members
 * Members will not be added until the chat state has been synchronized with the server
 *
 * @chatId - Chat identifier
 * @userIds - Identifiers of the users to be added to the chat
 */
suspend fun TdAbsHandler.addChatMembers(
    chatId: Long,
    userIds: IntArray
) = sync<Ok>(
    AddChatMembers(
        chatId,
        userIds
    )
)

suspend fun TdAbsHandler.addChatMembersOrNull(
    chatId: Long,
    userIds: IntArray
) = syncOrNull<Ok>(
    AddChatMembers(
        chatId,
        userIds
    )
)

fun TdAbsHandler.addChatMembers(
    chatId: Long,
    userIds: IntArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddChatMembers(
        chatId,
        userIds
    ),block = block
)

/**
 * Changes the status of a chat member, needs appropriate privileges
 * This function is currently not suitable for adding new members to the chat and transferring chat ownership
 * Instead, use addChatMember or transferChatOwnership
 * The chat member status will not be changed until it has been synchronized with the server
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 * @status - The new status of the member in the chat
 */
suspend fun TdAbsHandler.setChatMemberStatus(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null
) = sync<Ok>(
    SetChatMemberStatus(
        chatId,
        userId,
        status
    )
)

suspend fun TdAbsHandler.setChatMemberStatusOrNull(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null
) = syncOrNull<Ok>(
    SetChatMemberStatus(
        chatId,
        userId,
        status
    )
)

fun TdAbsHandler.setChatMemberStatus(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetChatMemberStatus(
        chatId,
        userId,
        status
    ),block = block
)

/**
 * Changes the owner of a chat
 * The current user must be a current owner of the chat
 * Use the method canTransferOwnership to check whether the ownership can be transferred from the current session
 * Available only for supergroups and channel chats
 *
 * @chatId - Chat identifier
 * @userId - Identifier of the user to which transfer the ownership
 *           The ownership can't be transferred to a bot or to a deleted user
 * @password - The password of the current user
 */
suspend fun TdAbsHandler.transferChatOwnership(
    chatId: Long,
    userId: Int,
    password: String? = null
) = sync<Ok>(
    TransferChatOwnership(
        chatId,
        userId,
        password
    )
)

suspend fun TdAbsHandler.transferChatOwnershipOrNull(
    chatId: Long,
    userId: Int,
    password: String? = null
) = syncOrNull<Ok>(
    TransferChatOwnership(
        chatId,
        userId,
        password
    )
)

fun TdAbsHandler.transferChatOwnership(
    chatId: Long,
    userId: Int,
    password: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    TransferChatOwnership(
        chatId,
        userId,
        password
    ),block = block
)

/**
 * Returns information about a single member of a chat
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 */
suspend fun TdAbsHandler.getChatMember(
    chatId: Long,
    userId: Int
) = sync<ChatMember>(
    GetChatMember(
        chatId,
        userId
    )
)

suspend fun TdAbsHandler.getChatMemberOrNull(
    chatId: Long,
    userId: Int
) = syncOrNull<ChatMember>(
    GetChatMember(
        chatId,
        userId
    )
)

fun TdAbsHandler.getChatMember(
    chatId: Long,
    userId: Int,
    block: (suspend CoroutineScope.(ChatMember) -> Unit)
) = send(
    GetChatMember(
        chatId,
        userId
    ),block = block
)

/**
 * Searches for a specified query in the first name, last name and username of the members of a specified chat
 * Requires administrator rights in channels
 *
 * @chatId - Chat identifier
 * @query - Query to search for
 * @limit - The maximum number of users to be returned
 * @filter - The type of users to return
 *           By default, chatMembersFilterMembers
 */
suspend fun TdAbsHandler.searchChatMembers(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null
) = sync<ChatMembers>(
    SearchChatMembers(
        chatId,
        query,
        limit,
        filter
    )
)

suspend fun TdAbsHandler.searchChatMembersOrNull(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null
) = syncOrNull<ChatMembers>(
    SearchChatMembers(
        chatId,
        query,
        limit,
        filter
    )
)

fun TdAbsHandler.searchChatMembers(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null,
    block: (suspend CoroutineScope.(ChatMembers) -> Unit)
) = send(
    SearchChatMembers(
        chatId,
        query,
        limit,
        filter
    ),block = block
)

/**
 * Returns a list of administrators of the chat with their custom titles
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.getChatAdministrators(
    chatId: Long
) = sync<ChatAdministrators>(
    GetChatAdministrators(
        chatId
    )
)

suspend fun TdAbsHandler.getChatAdministratorsOrNull(
    chatId: Long
) = syncOrNull<ChatAdministrators>(
    GetChatAdministrators(
        chatId
    )
)

fun TdAbsHandler.getChatAdministrators(
    chatId: Long,
    block: (suspend CoroutineScope.(ChatAdministrators) -> Unit)
) = send(
    GetChatAdministrators(
        chatId
    ),block = block
)

/**
 * Returns list of chats with non-default notification settings
 *
 * @scope - If specified, only chats from the specified scope will be returned
 * @compareSound - If true, also chats with non-default sound will be returned
 */
suspend fun TdAbsHandler.getChatNotificationSettingsExceptions(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean
) = sync<Chats>(
    GetChatNotificationSettingsExceptions(
        scope,
        compareSound
    )
)

suspend fun TdAbsHandler.getChatNotificationSettingsExceptionsOrNull(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean
) = syncOrNull<Chats>(
    GetChatNotificationSettingsExceptions(
        scope,
        compareSound
    )
)

fun TdAbsHandler.getChatNotificationSettingsExceptions(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean,
    block: (suspend CoroutineScope.(Chats) -> Unit)
) = send(
    GetChatNotificationSettingsExceptions(
        scope,
        compareSound
    ),block = block
)

/**
 * Changes the order of pinned chats
 *
 * @chatList - Chat list in which to change the order of pinned chats
 * @chatIds - The new list of pinned chats
 */
suspend fun TdAbsHandler.setPinnedChats(
    chatList: ChatList? = null,
    chatIds: LongArray
) = sync<Ok>(
    SetPinnedChats(
        chatList,
        chatIds
    )
)

suspend fun TdAbsHandler.setPinnedChatsOrNull(
    chatList: ChatList? = null,
    chatIds: LongArray
) = syncOrNull<Ok>(
    SetPinnedChats(
        chatList,
        chatIds
    )
)

fun TdAbsHandler.setPinnedChats(
    chatList: ChatList? = null,
    chatIds: LongArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetPinnedChats(
        chatList,
        chatIds
    ),block = block
)

/**
 * Generates a new invite link for a chat
 * The previously generated link is revoked
 * Available for basic groups, supergroups, and channels
 * Requires administrator privileges and can_invite_users right
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.generateChatInviteLink(
    chatId: Long
) = sync<ChatInviteLink>(
    GenerateChatInviteLink(
        chatId
    )
)

suspend fun TdAbsHandler.generateChatInviteLinkOrNull(
    chatId: Long
) = syncOrNull<ChatInviteLink>(
    GenerateChatInviteLink(
        chatId
    )
)

fun TdAbsHandler.generateChatInviteLink(
    chatId: Long,
    block: (suspend CoroutineScope.(ChatInviteLink) -> Unit)
) = send(
    GenerateChatInviteLink(
        chatId
    ),block = block
)

/**
 * Checks the validity of an invite link for a chat and returns information about the corresponding chat
 *
 * @inviteLink - Invite link to be checked
 */
suspend fun TdAbsHandler.checkChatInviteLink(
    inviteLink: String? = null
) = sync<ChatInviteLinkInfo>(
    CheckChatInviteLink(
        inviteLink
    )
)

suspend fun TdAbsHandler.checkChatInviteLinkOrNull(
    inviteLink: String? = null
) = syncOrNull<ChatInviteLinkInfo>(
    CheckChatInviteLink(
        inviteLink
    )
)

fun TdAbsHandler.checkChatInviteLink(
    inviteLink: String? = null,
    block: (suspend CoroutineScope.(ChatInviteLinkInfo) -> Unit)
) = send(
    CheckChatInviteLink(
        inviteLink
    ),block = block
)

/**
 * Uses an invite link to add the current user to the chat if possible
 * The new member will not be added until the chat state has been synchronized with the server
 *
 * @inviteLink - Invite link to import
 */
suspend fun TdAbsHandler.joinChatByInviteLink(
    inviteLink: String? = null
) = sync<Chat>(
    JoinChatByInviteLink(
        inviteLink
    )
)

suspend fun TdAbsHandler.joinChatByInviteLinkOrNull(
    inviteLink: String? = null
) = syncOrNull<Chat>(
    JoinChatByInviteLink(
        inviteLink
    )
)

fun TdAbsHandler.joinChatByInviteLink(
    inviteLink: String? = null,
    block: (suspend CoroutineScope.(Chat) -> Unit)
) = send(
    JoinChatByInviteLink(
        inviteLink
    ),block = block
)

/**
 * Returns information about members or banned users in a supergroup or channel
 * Can be used only if SupergroupFullInfo.can_get_members == true
 * Additionally, administrator privileges may be required for some filters
 *
 * @supergroupId - Identifier of the supergroup or channel
 * @filter - The type of users to return
 *           By default, supergroupMembersRecent
 * @offset - Number of users to skip
 * @limit - The maximum number of users be returned
 *          Up to 200
 */
suspend fun TdAbsHandler.getSupergroupMembers(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int
) = sync<ChatMembers>(
    GetSupergroupMembers(
        supergroupId,
        filter,
        offset,
        limit
    )
)

suspend fun TdAbsHandler.getSupergroupMembersOrNull(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int
) = syncOrNull<ChatMembers>(
    GetSupergroupMembers(
        supergroupId,
        filter,
        offset,
        limit
    )
)

fun TdAbsHandler.getSupergroupMembers(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int,
    block: (suspend CoroutineScope.(ChatMembers) -> Unit)
) = send(
    GetSupergroupMembers(
        supergroupId,
        filter,
        offset,
        limit
    ),block = block
)

/**
 * Returns a list of service actions taken by chat members and administrators in the last 48 hours
 * Available only for supergroups and channels
 * Requires administrator rights
 * Returns results in reverse chronological order (i
 * E., in order of decreasing event_id)
 *
 * @chatId - Chat identifier
 * @query - Search query by which to filter events
 * @fromEventId - Identifier of an event from which to return results
 *                Use 0 to get results from the latest events
 * @limit - The maximum number of events to return
 * @filters - The types of events to return
 *            By default, all types will be returned
 * @userIds - User identifiers by which to filter events
 *            By default, events relating to all users will be returned
 */
suspend fun TdAbsHandler.getChatEventLog(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray
) = sync<ChatEvents>(
    GetChatEventLog(
        chatId,
        query,
        fromEventId,
        limit,
        filters,
        userIds
    )
)

suspend fun TdAbsHandler.getChatEventLogOrNull(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray
) = syncOrNull<ChatEvents>(
    GetChatEventLog(
        chatId,
        query,
        fromEventId,
        limit,
        filters,
        userIds
    )
)

fun TdAbsHandler.getChatEventLog(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray,
    block: (suspend CoroutineScope.(ChatEvents) -> Unit)
) = send(
    GetChatEventLog(
        chatId,
        query,
        fromEventId,
        limit,
        filters,
        userIds
    ),block = block
)

/**
 * Removes a chat action bar without any other action
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.removeChatActionBar(
    chatId: Long
) = sync<Ok>(
    RemoveChatActionBar(
        chatId
    )
)

suspend fun TdAbsHandler.removeChatActionBarOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    RemoveChatActionBar(
        chatId
    )
)

fun TdAbsHandler.removeChatActionBar(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveChatActionBar(
        chatId
    ),block = block
)

/**
 * Reports a chat to the Telegram moderators
 * Supported only for supergroups, channels, or private chats with bots, since other chats can't be checked by moderators, or when the report is done from the chat action bar
 *
 * @chatId - Chat identifier
 * @reason - The reason for reporting the chat
 * @messageIds - Identifiers of reported messages, if any
 */
suspend fun TdAbsHandler.reportChat(
    chatId: Long,
    reason: ChatReportReason? = null,
    messageIds: LongArray
) = sync<Ok>(
    ReportChat(
        chatId,
        reason,
        messageIds
    )
)

suspend fun TdAbsHandler.reportChatOrNull(
    chatId: Long,
    reason: ChatReportReason? = null,
    messageIds: LongArray
) = syncOrNull<Ok>(
    ReportChat(
        chatId,
        reason,
        messageIds
    )
)

fun TdAbsHandler.reportChat(
    chatId: Long,
    reason: ChatReportReason? = null,
    messageIds: LongArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ReportChat(
        chatId,
        reason,
        messageIds
    ),block = block
)

/**
 * Returns an HTTP URL with the chat statistics
 * Currently this method can be used only for channels
 * Can be used only if SupergroupFullInfo.can_view_statistics == true
 *
 * @chatId - Chat identifier
 * @parameters - Parameters from "tg://statsrefresh?params=******" link
 * @isDark - Pass true if a URL with the dark theme must be returned
 */
suspend fun TdAbsHandler.getChatStatisticsUrl(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean
) = sync<HttpUrl>(
    GetChatStatisticsUrl(
        chatId,
        parameters,
        isDark
    )
)

suspend fun TdAbsHandler.getChatStatisticsUrlOrNull(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean
) = syncOrNull<HttpUrl>(
    GetChatStatisticsUrl(
        chatId,
        parameters,
        isDark
    )
)

fun TdAbsHandler.getChatStatisticsUrl(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean,
    block: (suspend CoroutineScope.(HttpUrl) -> Unit)
) = send(
    GetChatStatisticsUrl(
        chatId,
        parameters,
        isDark
    ),block = block
)
