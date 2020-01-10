@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns information about a message
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Identifier of the message to get
 */
suspend fun TdAbsHandler.getMessage(
    chatId: Long = 0L,
    messageId: Long = 0L
) = sync<Message>(
    GetMessage(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.getMessageOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L
) = syncOrNull<Message>(
    GetMessage(
        chatId,
        messageId
    )
)

fun TdAbsHandler.getMessage(
    chatId: Long = 0L,
    messageId: Long = 0L,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    GetMessage(
        chatId,
        messageId
    ),block = block
)

/**
 * Returns information about a message, if it is available locally without sending network request
 * This is an offline request
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Identifier of the message to get
 */
suspend fun TdAbsHandler.getMessageLocally(
    chatId: Long = 0L,
    messageId: Long = 0L
) = sync<Message>(
    GetMessageLocally(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.getMessageLocallyOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L
) = syncOrNull<Message>(
    GetMessageLocally(
        chatId,
        messageId
    )
)

fun TdAbsHandler.getMessageLocally(
    chatId: Long = 0L,
    messageId: Long = 0L,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    GetMessageLocally(
        chatId,
        messageId
    ),block = block
)

/**
 * Returns information about a message that is replied by given message
 *
 * @chatId - Identifier of the chat the message belongs to
 * @messageId - Identifier of the message reply to which get
 */
suspend fun TdAbsHandler.getRepliedMessage(
    chatId: Long = 0L,
    messageId: Long = 0L
) = sync<Message>(
    GetRepliedMessage(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.getRepliedMessageOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L
) = syncOrNull<Message>(
    GetRepliedMessage(
        chatId,
        messageId
    )
)

fun TdAbsHandler.getRepliedMessage(
    chatId: Long = 0L,
    messageId: Long = 0L,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    GetRepliedMessage(
        chatId,
        messageId
    ),block = block
)

/**
 * Returns information about a pinned chat message
 *
 * @chatId - Identifier of the chat the message belongs to
 */
suspend fun TdAbsHandler.getChatPinnedMessage(
    chatId: Long = 0L
) = sync<Message>(
    GetChatPinnedMessage(
        chatId
    )
)

suspend fun TdAbsHandler.getChatPinnedMessageOrNull(
    chatId: Long = 0L
) = syncOrNull<Message>(
    GetChatPinnedMessage(
        chatId
    )
)

fun TdAbsHandler.getChatPinnedMessage(
    chatId: Long = 0L,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    GetChatPinnedMessage(
        chatId
    ),block = block
)

/**
 * Returns information about messages
 * If a message is not found, returns null on the corresponding position of the result
 *
 * @chatId - Identifier of the chat the messages belong to
 * @messageIds - Identifiers of the messages to get
 */
suspend fun TdAbsHandler.getMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf()
) = sync<Messages>(
    GetMessages(
        chatId,
        messageIds
    )
)

suspend fun TdAbsHandler.getMessagesOrNull(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf()
) = syncOrNull<Messages>(
    GetMessages(
        chatId,
        messageIds
    )
)

fun TdAbsHandler.getMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    GetMessages(
        chatId,
        messageIds
    ),block = block
)

/**
 * Returns messages in a chat
 * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
 * For optimal performance the number of returned messages is chosen by the library
 * This is an offline request if only_local is true
 *
 * @chatId - Chat identifier
 * @fromMessageId - Identifier of the message starting from which history must be fetched
 *                  Use 0 to get results from the last message
 * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset up to 99 to get additionally some newer messages
 * @limit - The maximum number of messages to be returned
 *          Must be positive and can't be greater than 100
 *          If the offset is negative, the limit must be greater or equal to -offset
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @onlyLocal - If true, returns only messages that are available locally without sending network requests
 */
suspend fun TdAbsHandler.getChatHistory(
    chatId: Long = 0L,
    fromMessageId: Long = 0L,
    offset: Int = 0,
    limit: Int = 0,
    onlyLocal: Boolean = false
) = sync<Messages>(
    GetChatHistory(
        chatId,
        fromMessageId,
        offset,
        limit,
        onlyLocal
    )
)

suspend fun TdAbsHandler.getChatHistoryOrNull(
    chatId: Long = 0L,
    fromMessageId: Long = 0L,
    offset: Int = 0,
    limit: Int = 0,
    onlyLocal: Boolean = false
) = syncOrNull<Messages>(
    GetChatHistory(
        chatId,
        fromMessageId,
        offset,
        limit,
        onlyLocal
    )
)

fun TdAbsHandler.getChatHistory(
    chatId: Long = 0L,
    fromMessageId: Long = 0L,
    offset: Int = 0,
    limit: Int = 0,
    onlyLocal: Boolean = false,
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    GetChatHistory(
        chatId,
        fromMessageId,
        offset,
        limit,
        onlyLocal
    ),block = block
)

/**
 * Searches for messages with given words in the chat
 * Returns the results in reverse chronological order, i.e
 * In order of decreasing message_id
 * Cannot be used in secret chats with a non-empty query (searchSecretMessages should be used instead), or without an enabled message database
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Identifier of the chat in which to search messages
 * @query - Query to search for
 * @senderUserId - If not 0, only messages sent by the specified user will be returned
 *                 Not supported in secret chats
 * @fromMessageId - Identifier of the message starting from which history must be fetched
 *                  Use 0 to get results from the last message
 * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset to get the specified message and some newer messages
 * @limit - The maximum number of messages to be returned
 *          Must be positive and can't be greater than 100
 *          If the offset is negative, the limit must be greater than -offset
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @filter - Filter for message content in the search results
 */
suspend fun TdAbsHandler.searchChatMessages(
    chatId: Long = 0L,
    query: String? = null,
    senderUserId: Int = 0,
    fromMessageId: Long = 0L,
    offset: Int = 0,
    limit: Int = 0,
    filter: SearchMessagesFilter? = null
) = sync<Messages>(
    SearchChatMessages(
        chatId,
        query,
        senderUserId,
        fromMessageId,
        offset,
        limit,
        filter
    )
)

suspend fun TdAbsHandler.searchChatMessagesOrNull(
    chatId: Long = 0L,
    query: String? = null,
    senderUserId: Int = 0,
    fromMessageId: Long = 0L,
    offset: Int = 0,
    limit: Int = 0,
    filter: SearchMessagesFilter? = null
) = syncOrNull<Messages>(
    SearchChatMessages(
        chatId,
        query,
        senderUserId,
        fromMessageId,
        offset,
        limit,
        filter
    )
)

fun TdAbsHandler.searchChatMessages(
    chatId: Long = 0L,
    query: String? = null,
    senderUserId: Int = 0,
    fromMessageId: Long = 0L,
    offset: Int = 0,
    limit: Int = 0,
    filter: SearchMessagesFilter? = null,
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    SearchChatMessages(
        chatId,
        query,
        senderUserId,
        fromMessageId,
        offset,
        limit,
        filter
    ),block = block
)

/**
 * Searches for messages in all chats except secret chats
 * Returns the results in reverse chronological order (i.e., in order of decreasing (date, chat_id, message_id))
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatList - Chat list in which to search messages
 *             Pass null to search in all chats regardless of their chat list
 * @query - Query to search for
 * @offsetDate - The date of the message starting from which the results should be fetched
 *               Use 0 or any date in the future to get results from the last message
 * @offsetChatId - The chat identifier of the last found message, or 0 for the first request
 * @offsetMessageId - The message identifier of the last found message, or 0 for the first request
 * @limit - The maximum number of messages to be returned, up to 100
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 */
suspend fun TdAbsHandler.searchMessages(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int = 0,
    offsetChatId: Long = 0L,
    offsetMessageId: Long = 0L,
    limit: Int = 0
) = sync<Messages>(
    SearchMessages(
        chatList,
        query,
        offsetDate,
        offsetChatId,
        offsetMessageId,
        limit
    )
)

suspend fun TdAbsHandler.searchMessagesOrNull(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int = 0,
    offsetChatId: Long = 0L,
    offsetMessageId: Long = 0L,
    limit: Int = 0
) = syncOrNull<Messages>(
    SearchMessages(
        chatList,
        query,
        offsetDate,
        offsetChatId,
        offsetMessageId,
        limit
    )
)

fun TdAbsHandler.searchMessages(
    chatList: ChatList? = null,
    query: String? = null,
    offsetDate: Int = 0,
    offsetChatId: Long = 0L,
    offsetMessageId: Long = 0L,
    limit: Int = 0,
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    SearchMessages(
        chatList,
        query,
        offsetDate,
        offsetChatId,
        offsetMessageId,
        limit
    ),block = block
)

/**
 * Searches for messages in secret chats
 * Returns the results in reverse chronological order
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @chatId - Identifier of the chat in which to search
 *           Specify 0 to search in all secret chats
 * @query - Query to search for
 *          If empty, searchChatMessages should be used instead
 * @fromSearchId - The identifier from the result of a previous request, use 0 to get results from the last message
 * @limit - The maximum number of messages to be returned
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @filter - A filter for the content of messages in the search results
 */
suspend fun TdAbsHandler.searchSecretMessages(
    chatId: Long = 0L,
    query: String? = null,
    fromSearchId: Long = 0L,
    limit: Int = 0,
    filter: SearchMessagesFilter? = null
) = sync<FoundMessages>(
    SearchSecretMessages(
        chatId,
        query,
        fromSearchId,
        limit,
        filter
    )
)

suspend fun TdAbsHandler.searchSecretMessagesOrNull(
    chatId: Long = 0L,
    query: String? = null,
    fromSearchId: Long = 0L,
    limit: Int = 0,
    filter: SearchMessagesFilter? = null
) = syncOrNull<FoundMessages>(
    SearchSecretMessages(
        chatId,
        query,
        fromSearchId,
        limit,
        filter
    )
)

fun TdAbsHandler.searchSecretMessages(
    chatId: Long = 0L,
    query: String? = null,
    fromSearchId: Long = 0L,
    limit: Int = 0,
    filter: SearchMessagesFilter? = null,
    block: (suspend CoroutineScope.(FoundMessages) -> Unit)
) = send(
    SearchSecretMessages(
        chatId,
        query,
        fromSearchId,
        limit,
        filter
    ),block = block
)

/**
 * Searches for call messages
 * Returns the results in reverse chronological order (i
 * E., in order of decreasing message_id)
 * For optimal performance the number of returned messages is chosen by the library
 *
 * @fromMessageId - Identifier of the message from which to search
 *                  Use 0 to get results from the last message
 * @limit - The maximum number of messages to be returned
 *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
 * @onlyMissed - If true, returns only messages with missed calls
 */
suspend fun TdAbsHandler.searchCallMessages(
    fromMessageId: Long = 0L,
    limit: Int = 0,
    onlyMissed: Boolean = false
) = sync<Messages>(
    SearchCallMessages(
        fromMessageId,
        limit,
        onlyMissed
    )
)

suspend fun TdAbsHandler.searchCallMessagesOrNull(
    fromMessageId: Long = 0L,
    limit: Int = 0,
    onlyMissed: Boolean = false
) = syncOrNull<Messages>(
    SearchCallMessages(
        fromMessageId,
        limit,
        onlyMissed
    )
)

fun TdAbsHandler.searchCallMessages(
    fromMessageId: Long = 0L,
    limit: Int = 0,
    onlyMissed: Boolean = false,
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    SearchCallMessages(
        fromMessageId,
        limit,
        onlyMissed
    ),block = block
)

/**
 * Returns information about the recent locations of chat members that were sent to the chat
 * Returns up to 1 location message per user
 *
 * @chatId - Chat identifier
 * @limit - The maximum number of messages to be returned
 */
suspend fun TdAbsHandler.searchChatRecentLocationMessages(
    chatId: Long = 0L,
    limit: Int = 0
) = sync<Messages>(
    SearchChatRecentLocationMessages(
        chatId,
        limit
    )
)

suspend fun TdAbsHandler.searchChatRecentLocationMessagesOrNull(
    chatId: Long = 0L,
    limit: Int = 0
) = syncOrNull<Messages>(
    SearchChatRecentLocationMessages(
        chatId,
        limit
    )
)

fun TdAbsHandler.searchChatRecentLocationMessages(
    chatId: Long = 0L,
    limit: Int = 0,
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    SearchChatRecentLocationMessages(
        chatId,
        limit
    ),block = block
)

/**
 * Returns all active live locations that should be updated by the client
 * The list is persistent across application restarts only if the message database is used
 */
suspend fun TdAbsHandler.getActiveLiveLocationMessages() = sync<Messages>(
    GetActiveLiveLocationMessages()
)

suspend fun TdAbsHandler.getActiveLiveLocationMessagesOrNull() = syncOrNull<Messages>(
    GetActiveLiveLocationMessages()
)

fun TdAbsHandler.getActiveLiveLocationMessages(
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    GetActiveLiveLocationMessages(),block = block
)

/**
 * Returns the last message sent in a chat no later than the specified date
 *
 * @chatId - Chat identifier
 * @date - Point in time (Unix timestamp) relative to which to search for messages
 */
suspend fun TdAbsHandler.getChatMessageByDate(
    chatId: Long = 0L,
    date: Int = 0
) = sync<Message>(
    GetChatMessageByDate(
        chatId,
        date
    )
)

suspend fun TdAbsHandler.getChatMessageByDateOrNull(
    chatId: Long = 0L,
    date: Int = 0
) = syncOrNull<Message>(
    GetChatMessageByDate(
        chatId,
        date
    )
)

fun TdAbsHandler.getChatMessageByDate(
    chatId: Long = 0L,
    date: Int = 0,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    GetChatMessageByDate(
        chatId,
        date
    ),block = block
)

/**
 * Returns all scheduled messages in a chat
 * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.getChatScheduledMessages(
    chatId: Long = 0L
) = sync<Messages>(
    GetChatScheduledMessages(
        chatId
    )
)

suspend fun TdAbsHandler.getChatScheduledMessagesOrNull(
    chatId: Long = 0L
) = syncOrNull<Messages>(
    GetChatScheduledMessages(
        chatId
    )
)

fun TdAbsHandler.getChatScheduledMessages(
    chatId: Long = 0L,
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    GetChatScheduledMessages(
        chatId
    ),block = block
)

/**
 * Returns a public HTTPS link to a message
 * Available only for messages in supergroups and channels with a username
 *
 * @chatId - Identifier of the chat to which the message belongs
 * @messageId - Identifier of the message
 * @forAlbum - Pass true if a link for a whole media album should be returned
 */
suspend fun TdAbsHandler.getPublicMessageLink(
    chatId: Long = 0L,
    messageId: Long = 0L,
    forAlbum: Boolean = false
) = sync<PublicMessageLink>(
    GetPublicMessageLink(
        chatId,
        messageId,
        forAlbum
    )
)

suspend fun TdAbsHandler.getPublicMessageLinkOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    forAlbum: Boolean = false
) = syncOrNull<PublicMessageLink>(
    GetPublicMessageLink(
        chatId,
        messageId,
        forAlbum
    )
)

fun TdAbsHandler.getPublicMessageLink(
    chatId: Long = 0L,
    messageId: Long = 0L,
    forAlbum: Boolean = false,
    block: (suspend CoroutineScope.(PublicMessageLink) -> Unit)
) = send(
    GetPublicMessageLink(
        chatId,
        messageId,
        forAlbum
    ),block = block
)

/**
 * Returns a private HTTPS link to a message in a chat
 * Available only for already sent messages in supergroups and channels
 * The link will work only for members of the chat
 *
 * @chatId - Identifier of the chat to which the message belongs
 * @messageId - Identifier of the message
 */
suspend fun TdAbsHandler.getMessageLink(
    chatId: Long = 0L,
    messageId: Long = 0L
) = sync<HttpUrl>(
    GetMessageLink(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.getMessageLinkOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L
) = syncOrNull<HttpUrl>(
    GetMessageLink(
        chatId,
        messageId
    )
)

fun TdAbsHandler.getMessageLink(
    chatId: Long = 0L,
    messageId: Long = 0L,
    block: (suspend CoroutineScope.(HttpUrl) -> Unit)
) = send(
    GetMessageLink(
        chatId,
        messageId
    ),block = block
)

/**
 * Returns information about a public or private message link
 *
 * @url - The message link in the format "https://t.me/c/...", or "tg://privatepost?...", or "https://t.me/username/...", or "tg://resolve?..."
 */
suspend fun TdAbsHandler.getMessageLinkInfo(
    url: String? = null
) = sync<MessageLinkInfo>(
    GetMessageLinkInfo(
        url
    )
)

suspend fun TdAbsHandler.getMessageLinkInfoOrNull(
    url: String? = null
) = syncOrNull<MessageLinkInfo>(
    GetMessageLinkInfo(
        url
    )
)

fun TdAbsHandler.getMessageLinkInfo(
    url: String? = null,
    block: (suspend CoroutineScope.(MessageLinkInfo) -> Unit)
) = send(
    GetMessageLinkInfo(
        url
    ),block = block
)

/**
 * Sends a message
 * Returns the sent message
 *
 * @chatId - Target chat
 * @replyToMessageId - Identifier of the message to reply to or 0
 * @options - Options to be used to send the message
 * @replyMarkup - Markup for replying to the message
 *                For bots only
 * @inputMessageContent - The content of the message to be sent
 */
suspend fun TdAbsHandler.sendMessage(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Message>(
    SendMessage(
        chatId,
        replyToMessageId,
        options,
        replyMarkup,
        inputMessageContent
    )
)

suspend fun TdAbsHandler.sendMessageOrNull(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(
    SendMessage(
        chatId,
        replyToMessageId,
        options,
        replyMarkup,
        inputMessageContent
    )
)

fun TdAbsHandler.sendMessage(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    SendMessage(
        chatId,
        replyToMessageId,
        options,
        replyMarkup,
        inputMessageContent
    ),block = block
)

/**
 * Sends messages grouped together into an album
 * Currently only photo and video messages can be grouped into an album
 * Returns sent messages
 *
 * @chatId - Target chat
 * @replyToMessageId - Identifier of a message to reply to or 0
 * @options - Options to be used to send the messages
 * @inputMessageContents - Contents of messages to be sent
 */
suspend fun TdAbsHandler.sendMessageAlbum(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    inputMessageContents: Array<InputMessageContent> = emptyArray()
) = sync<Messages>(
    SendMessageAlbum(
        chatId,
        replyToMessageId,
        options,
        inputMessageContents
    )
)

suspend fun TdAbsHandler.sendMessageAlbumOrNull(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    inputMessageContents: Array<InputMessageContent> = emptyArray()
) = syncOrNull<Messages>(
    SendMessageAlbum(
        chatId,
        replyToMessageId,
        options,
        inputMessageContents
    )
)

fun TdAbsHandler.sendMessageAlbum(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    inputMessageContents: Array<InputMessageContent> = emptyArray(),
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    SendMessageAlbum(
        chatId,
        replyToMessageId,
        options,
        inputMessageContents
    ),block = block
)

/**
 * Invites a bot to a chat (if it is not yet a member) and sends it the /start command
 * Bots can't be invited to a private chat other than the chat with the bot
 * Bots can't be invited to channels (although they can be added as admins) and secret chats
 * Returns the sent message
 *
 * @botUserId - Identifier of the bot
 * @chatId - Identifier of the target chat
 * @parameter - A hidden parameter sent to the bot for deep linking purposes (https://core.telegram.org/bots#deep-linking)
 */
suspend fun TdAbsHandler.sendBotStartMessage(
    botUserId: Int = 0,
    chatId: Long = 0L,
    parameter: String? = null
) = sync<Message>(
    SendBotStartMessage(
        botUserId,
        chatId,
        parameter
    )
)

suspend fun TdAbsHandler.sendBotStartMessageOrNull(
    botUserId: Int = 0,
    chatId: Long = 0L,
    parameter: String? = null
) = syncOrNull<Message>(
    SendBotStartMessage(
        botUserId,
        chatId,
        parameter
    )
)

fun TdAbsHandler.sendBotStartMessage(
    botUserId: Int = 0,
    chatId: Long = 0L,
    parameter: String? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    SendBotStartMessage(
        botUserId,
        chatId,
        parameter
    ),block = block
)

/**
 * Sends the result of an inline query as a message
 * Returns the sent message
 * Always clears a chat draft message
 *
 * @chatId - Target chat
 * @replyToMessageId - Identifier of a message to reply to or 0
 * @options - Options to be used to send the message
 * @queryId - Identifier of the inline query
 * @resultId - Identifier of the inline result
 * @hideViaBot - If true, there will be no mention of a bot, via which the message is sent
 *               Can be used only for bots GetOption("animation_search_bot_username"), GetOption("photo_search_bot_username") and GetOption("venue_search_bot_username")
 */
suspend fun TdAbsHandler.sendInlineQueryResultMessage(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    queryId: Long = 0L,
    resultId: String? = null,
    hideViaBot: Boolean = false
) = sync<Message>(
    SendInlineQueryResultMessage(
        chatId,
        replyToMessageId,
        options,
        queryId,
        resultId,
        hideViaBot
    )
)

suspend fun TdAbsHandler.sendInlineQueryResultMessageOrNull(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    queryId: Long = 0L,
    resultId: String? = null,
    hideViaBot: Boolean = false
) = syncOrNull<Message>(
    SendInlineQueryResultMessage(
        chatId,
        replyToMessageId,
        options,
        queryId,
        resultId,
        hideViaBot
    )
)

fun TdAbsHandler.sendInlineQueryResultMessage(
    chatId: Long = 0L,
    replyToMessageId: Long = 0L,
    options: SendMessageOptions? = null,
    queryId: Long = 0L,
    resultId: String? = null,
    hideViaBot: Boolean = false,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    SendInlineQueryResultMessage(
        chatId,
        replyToMessageId,
        options,
        queryId,
        resultId,
        hideViaBot
    ),block = block
)

/**
 * Forwards previously sent messages
 * Returns the forwarded messages in the same order as the message identifiers passed in message_ids
 * If a message can't be forwarded, null will be returned instead of the message
 *
 * @chatId - Identifier of the chat to which to forward messages
 * @fromChatId - Identifier of the chat from which to forward messages
 * @messageIds - Identifiers of the messages to forward
 * @options - Options to be used to send the messages
 * @asAlbum - True, if the messages should be grouped into an album after forwarding
 *            For this to work, no more than 10 messages may be forwarded, and all of them must be photo or video messages
 * @sendCopy - True, if content of the messages needs to be copied without links to the original messages
 *             Always true if the messages are forwarded to a secret chat
 * @removeCaption - True, if media captions of message copies needs to be removed
 *                  Ignored if send_copy is false
 */
suspend fun TdAbsHandler.forwardMessages(
    chatId: Long = 0L,
    fromChatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    options: SendMessageOptions? = null,
    asAlbum: Boolean = false,
    sendCopy: Boolean = false,
    removeCaption: Boolean = false
) = sync<Messages>(
    ForwardMessages(
        chatId,
        fromChatId,
        messageIds,
        options,
        asAlbum,
        sendCopy,
        removeCaption
    )
)

suspend fun TdAbsHandler.forwardMessagesOrNull(
    chatId: Long = 0L,
    fromChatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    options: SendMessageOptions? = null,
    asAlbum: Boolean = false,
    sendCopy: Boolean = false,
    removeCaption: Boolean = false
) = syncOrNull<Messages>(
    ForwardMessages(
        chatId,
        fromChatId,
        messageIds,
        options,
        asAlbum,
        sendCopy,
        removeCaption
    )
)

fun TdAbsHandler.forwardMessages(
    chatId: Long = 0L,
    fromChatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    options: SendMessageOptions? = null,
    asAlbum: Boolean = false,
    sendCopy: Boolean = false,
    removeCaption: Boolean = false,
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    ForwardMessages(
        chatId,
        fromChatId,
        messageIds,
        options,
        asAlbum,
        sendCopy,
        removeCaption
    ),block = block
)

/**
 * Resends messages which failed to send
 * Can be called only for messages for which messageSendingStateFailed.can_retry is true and after specified in messageSendingStateFailed.retry_after time passed
 * If a message is re-sent, the corresponding failed to send message is deleted
 * Returns the sent messages in the same order as the message identifiers passed in message_ids
 * If a message can't be re-sent, null will be returned instead of the message
 *
 * @chatId - Identifier of the chat to send messages
 * @messageIds - Identifiers of the messages to resend
 *               Message identifiers must be in a strictly increasing order
 */
suspend fun TdAbsHandler.resendMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf()
) = sync<Messages>(
    ResendMessages(
        chatId,
        messageIds
    )
)

suspend fun TdAbsHandler.resendMessagesOrNull(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf()
) = syncOrNull<Messages>(
    ResendMessages(
        chatId,
        messageIds
    )
)

fun TdAbsHandler.resendMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    block: (suspend CoroutineScope.(Messages) -> Unit)
) = send(
    ResendMessages(
        chatId,
        messageIds
    ),block = block
)

/**
 * Changes the current TTL setting (sets a new self-destruct timer) in a secret chat and sends the corresponding message
 *
 * @chatId - Chat identifier
 * @ttl - New TTL value, in seconds
 */
suspend fun TdAbsHandler.sendChatSetTtlMessage(
    chatId: Long = 0L,
    ttl: Int = 0
) = sync<Message>(
    SendChatSetTtlMessage(
        chatId,
        ttl
    )
)

suspend fun TdAbsHandler.sendChatSetTtlMessageOrNull(
    chatId: Long = 0L,
    ttl: Int = 0
) = syncOrNull<Message>(
    SendChatSetTtlMessage(
        chatId,
        ttl
    )
)

fun TdAbsHandler.sendChatSetTtlMessage(
    chatId: Long = 0L,
    ttl: Int = 0,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    SendChatSetTtlMessage(
        chatId,
        ttl
    ),block = block
)

/**
 * Adds a local message to a chat
 * The message is persistent across application restarts only if the message database is used
 * Returns the added message
 *
 * @chatId - Target chat
 * @senderUserId - Identifier of the user who will be shown as the sender of the message
 *                 May be 0 for channel posts
 * @replyToMessageId - Identifier of the message to reply to or 0
 * @disableNotification - Pass true to disable notification for the message
 * @inputMessageContent - The content of the message to be added
 */
suspend fun TdAbsHandler.addLocalMessage(
    chatId: Long = 0L,
    senderUserId: Int = 0,
    replyToMessageId: Long = 0L,
    disableNotification: Boolean = false,
    inputMessageContent: InputMessageContent? = null
) = sync<Message>(
    AddLocalMessage(
        chatId,
        senderUserId,
        replyToMessageId,
        disableNotification,
        inputMessageContent
    )
)

suspend fun TdAbsHandler.addLocalMessageOrNull(
    chatId: Long = 0L,
    senderUserId: Int = 0,
    replyToMessageId: Long = 0L,
    disableNotification: Boolean = false,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(
    AddLocalMessage(
        chatId,
        senderUserId,
        replyToMessageId,
        disableNotification,
        inputMessageContent
    )
)

fun TdAbsHandler.addLocalMessage(
    chatId: Long = 0L,
    senderUserId: Int = 0,
    replyToMessageId: Long = 0L,
    disableNotification: Boolean = false,
    inputMessageContent: InputMessageContent? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    AddLocalMessage(
        chatId,
        senderUserId,
        replyToMessageId,
        disableNotification,
        inputMessageContent
    ),block = block
)

/**
 * Deletes messages
 *
 * @chatId - Chat identifier
 * @messageIds - Identifiers of the messages to be deleted
 * @revoke - Pass true to try to delete messages for all chat members
 *           Always true for supergroups, channels and secret chats
 */
suspend fun TdAbsHandler.deleteMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    revoke: Boolean = false
) = sync<Ok>(
    DeleteMessages(
        chatId,
        messageIds,
        revoke
    )
)

suspend fun TdAbsHandler.deleteMessagesOrNull(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    revoke: Boolean = false
) = syncOrNull<Ok>(
    DeleteMessages(
        chatId,
        messageIds,
        revoke
    )
)

fun TdAbsHandler.deleteMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    revoke: Boolean = false,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteMessages(
        chatId,
        messageIds,
        revoke
    ),block = block
)

/**
 * Edits the text of a message (or a text of a game message)
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New text content of the message
 *                        Should be of type InputMessageText
 */
suspend fun TdAbsHandler.editMessageText(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Message>(
    EditMessageText(
        chatId,
        messageId,
        replyMarkup,
        inputMessageContent
    )
)

suspend fun TdAbsHandler.editMessageTextOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(
    EditMessageText(
        chatId,
        messageId,
        replyMarkup,
        inputMessageContent
    )
)

fun TdAbsHandler.editMessageText(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    EditMessageText(
        chatId,
        messageId,
        replyMarkup,
        inputMessageContent
    ),block = block
)

/**
 * Edits the message content of a live location
 * Messages can be edited for a limited period of time specified in the live location
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @location - New location content of the message
 *             Pass null to stop sharing the live location
 */
suspend fun TdAbsHandler.editMessageLiveLocation(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null
) = sync<Message>(
    EditMessageLiveLocation(
        chatId,
        messageId,
        replyMarkup,
        location
    )
)

suspend fun TdAbsHandler.editMessageLiveLocationOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null
) = syncOrNull<Message>(
    EditMessageLiveLocation(
        chatId,
        messageId,
        replyMarkup,
        location
    )
)

fun TdAbsHandler.editMessageLiveLocation(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    EditMessageLiveLocation(
        chatId,
        messageId,
        replyMarkup,
        location
    ),block = block
)

/**
 * Edits the content of a message with an animation, an audio, a document, a photo or a video
 * The media in the message can't be replaced if the message was set to self-destruct
 * Media can't be replaced by self-destructing media
 * Media in an album can be edited only to contain a photo or a video
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New content of the message
 *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
 */
suspend fun TdAbsHandler.editMessageMedia(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Message>(
    EditMessageMedia(
        chatId,
        messageId,
        replyMarkup,
        inputMessageContent
    )
)

suspend fun TdAbsHandler.editMessageMediaOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Message>(
    EditMessageMedia(
        chatId,
        messageId,
        replyMarkup,
        inputMessageContent
    )
)

fun TdAbsHandler.editMessageMedia(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    EditMessageMedia(
        chatId,
        messageId,
        replyMarkup,
        inputMessageContent
    ),block = block
)

/**
 * Edits the message content caption
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @caption - New message content caption
 *            0-GetOption("message_caption_length_max") characters
 */
suspend fun TdAbsHandler.editMessageCaption(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = sync<Message>(
    EditMessageCaption(
        chatId,
        messageId,
        replyMarkup,
        caption
    )
)

suspend fun TdAbsHandler.editMessageCaptionOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = syncOrNull<Message>(
    EditMessageCaption(
        chatId,
        messageId,
        replyMarkup,
        caption
    )
)

fun TdAbsHandler.editMessageCaption(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    EditMessageCaption(
        chatId,
        messageId,
        replyMarkup,
        caption
    ),block = block
)

/**
 * Edits the message reply markup
 * For bots only
 * Returns the edited message after the edit is completed on the server side
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @replyMarkup - The new message reply markup
 */
suspend fun TdAbsHandler.editMessageReplyMarkup(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null
) = sync<Message>(
    EditMessageReplyMarkup(
        chatId,
        messageId,
        replyMarkup
    )
)

suspend fun TdAbsHandler.editMessageReplyMarkupOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull<Message>(
    EditMessageReplyMarkup(
        chatId,
        messageId,
        replyMarkup
    )
)

fun TdAbsHandler.editMessageReplyMarkup(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    EditMessageReplyMarkup(
        chatId,
        messageId,
        replyMarkup
    ),block = block
)

/**
 * Edits the time when a scheduled message will be sent
 * Scheduling state of all messages in the same album or forwarded together with the message will be also changed
 *
 * @chatId - The chat the message belongs to
 * @messageId - Identifier of the message
 * @schedulingState - The new message scheduling state
 *                    Pass null to send the message immediately
 */
suspend fun TdAbsHandler.editMessageSchedulingState(
    chatId: Long = 0L,
    messageId: Long = 0L,
    schedulingState: MessageSchedulingState? = null
) = sync<Ok>(
    EditMessageSchedulingState(
        chatId,
        messageId,
        schedulingState
    )
)

suspend fun TdAbsHandler.editMessageSchedulingStateOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    schedulingState: MessageSchedulingState? = null
) = syncOrNull<Ok>(
    EditMessageSchedulingState(
        chatId,
        messageId,
        schedulingState
    )
)

fun TdAbsHandler.editMessageSchedulingState(
    chatId: Long = 0L,
    messageId: Long = 0L,
    schedulingState: MessageSchedulingState? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EditMessageSchedulingState(
        chatId,
        messageId,
        schedulingState
    ),block = block
)

/**
 * Updates the game score of the specified user in the game
 * For bots only
 *
 * @chatId - The chat to which the message with the game belongs
 * @messageId - Identifier of the message
 * @editMessage - True, if the message should be edited
 * @userId - User identifier
 * @score - The new score
 * @force - Pass true to update the score even if it decreases
 *          If the score is 0, the user will be deleted from the high score table
 */
suspend fun TdAbsHandler.setGameScore(
    chatId: Long = 0L,
    messageId: Long = 0L,
    editMessage: Boolean = false,
    userId: Int = 0,
    score: Int = 0,
    force: Boolean = false
) = sync<Message>(
    SetGameScore(
        chatId,
        messageId,
        editMessage,
        userId,
        score,
        force
    )
)

suspend fun TdAbsHandler.setGameScoreOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    editMessage: Boolean = false,
    userId: Int = 0,
    score: Int = 0,
    force: Boolean = false
) = syncOrNull<Message>(
    SetGameScore(
        chatId,
        messageId,
        editMessage,
        userId,
        score,
        force
    )
)

fun TdAbsHandler.setGameScore(
    chatId: Long = 0L,
    messageId: Long = 0L,
    editMessage: Boolean = false,
    userId: Int = 0,
    score: Int = 0,
    force: Boolean = false,
    block: (suspend CoroutineScope.(Message) -> Unit)
) = send(
    SetGameScore(
        chatId,
        messageId,
        editMessage,
        userId,
        score,
        force
    ),block = block
)

/**
 * Informs TDLib that messages are being viewed by the user
 * Many useful activities depend on whether the messages are currently being viewed or not (e.g., marking messages as read, incrementing a view counter, updating a view counter, removing deleted messages in supergroups and channels)
 *
 * @chatId - Chat identifier
 * @messageIds - The identifiers of the messages being viewed
 * @forceRead - True, if messages in closed chats should be marked as read
 */
suspend fun TdAbsHandler.viewMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    forceRead: Boolean = false
) = sync<Ok>(
    ViewMessages(
        chatId,
        messageIds,
        forceRead
    )
)

suspend fun TdAbsHandler.viewMessagesOrNull(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    forceRead: Boolean = false
) = syncOrNull<Ok>(
    ViewMessages(
        chatId,
        messageIds,
        forceRead
    )
)

fun TdAbsHandler.viewMessages(
    chatId: Long = 0L,
    messageIds: LongArray = longArrayOf(),
    forceRead: Boolean = false,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ViewMessages(
        chatId,
        messageIds,
        forceRead
    ),block = block
)

/**
 * Informs TDLib that the message content has been opened (e.g., the user has opened a photo, video, document, location or venue, or has listened to an audio file or voice note message)
 * An updateMessageContentOpened update will be generated if something has changed
 *
 * @chatId - Chat identifier of the message
 * @messageId - Identifier of the message with the opened content
 */
suspend fun TdAbsHandler.openMessageContent(
    chatId: Long = 0L,
    messageId: Long = 0L
) = sync<Ok>(
    OpenMessageContent(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.openMessageContentOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L
) = syncOrNull<Ok>(
    OpenMessageContent(
        chatId,
        messageId
    )
)

fun TdAbsHandler.openMessageContent(
    chatId: Long = 0L,
    messageId: Long = 0L,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    OpenMessageContent(
        chatId,
        messageId
    ),block = block
)

/**
 * Clears draft messages in all chats
 *
 * @excludeSecretChats - If true, local draft messages in secret chats will not be cleared
 */
suspend fun TdAbsHandler.clearAllDraftMessages(
    excludeSecretChats: Boolean = false
) = sync<Ok>(
    ClearAllDraftMessages(
        excludeSecretChats
    )
)

suspend fun TdAbsHandler.clearAllDraftMessagesOrNull(
    excludeSecretChats: Boolean = false
) = syncOrNull<Ok>(
    ClearAllDraftMessages(
        excludeSecretChats
    )
)

fun TdAbsHandler.clearAllDraftMessages(
    excludeSecretChats: Boolean = false,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ClearAllDraftMessages(
        excludeSecretChats
    ),block = block
)
