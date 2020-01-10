@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns information about a supergroup or a channel by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @supergroupId - Supergroup or channel identifier
 */
suspend fun TdAbsHandler.getSupergroup(
    supergroupId: Int = 0
) = sync<Supergroup>(
    GetSupergroup(
        supergroupId
    )
)

/**
 * Returns information about a supergroup or a channel by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @supergroupId - Supergroup or channel identifier
 */
suspend fun TdAbsHandler.getSupergroupOrNull(
    supergroupId: Int = 0
) = syncOrNull<Supergroup>(
    GetSupergroup(
        supergroupId
    )
)

/**
 * Returns information about a supergroup or a channel by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @supergroupId - Supergroup or channel identifier
 */
fun TdAbsHandler.getSupergroup(
    supergroupId: Int = 0,
    block: (suspend CoroutineScope.(Supergroup) -> Unit)
) = send(
    GetSupergroup(
        supergroupId
    ),block = block
)

/**
 * Returns full information about a supergroup or a channel by its identifier, cached for up to 1 minute
 *
 * @supergroupId - Supergroup or channel identifier
 */
suspend fun TdAbsHandler.getSupergroupFullInfo(
    supergroupId: Int = 0
) = sync<SupergroupFullInfo>(
    GetSupergroupFullInfo(
        supergroupId
    )
)

/**
 * Returns full information about a supergroup or a channel by its identifier, cached for up to 1 minute
 *
 * @supergroupId - Supergroup or channel identifier
 */
suspend fun TdAbsHandler.getSupergroupFullInfoOrNull(
    supergroupId: Int = 0
) = syncOrNull<SupergroupFullInfo>(
    GetSupergroupFullInfo(
        supergroupId
    )
)

/**
 * Returns full information about a supergroup or a channel by its identifier, cached for up to 1 minute
 *
 * @supergroupId - Supergroup or channel identifier
 */
fun TdAbsHandler.getSupergroupFullInfo(
    supergroupId: Int = 0,
    block: (suspend CoroutineScope.(SupergroupFullInfo) -> Unit)
) = send(
    GetSupergroupFullInfo(
        supergroupId
    ),block = block
)

/**
 * Changes the username of a supergroup or channel, requires owner privileges in the supergroup or channel
 *
 * @supergroupId - Identifier of the supergroup or channel
 * @username - New value of the username
 *             Use an empty string to remove the username
 */
suspend fun TdAbsHandler.setSupergroupUsername(
    supergroupId: Int = 0,
    username: String? = null
) = sync<Ok>(
    SetSupergroupUsername(
        supergroupId,
        username
    )
)

/**
 * Changes the username of a supergroup or channel, requires owner privileges in the supergroup or channel
 *
 * @supergroupId - Identifier of the supergroup or channel
 * @username - New value of the username
 *             Use an empty string to remove the username
 */
suspend fun TdAbsHandler.setSupergroupUsernameOrNull(
    supergroupId: Int = 0,
    username: String? = null
) = syncOrNull<Ok>(
    SetSupergroupUsername(
        supergroupId,
        username
    )
)

/**
 * Changes the username of a supergroup or channel, requires owner privileges in the supergroup or channel
 *
 * @supergroupId - Identifier of the supergroup or channel
 * @username - New value of the username
 *             Use an empty string to remove the username
 */
fun TdAbsHandler.setSupergroupUsername(
    supergroupId: Int = 0,
    username: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetSupergroupUsername(
        supergroupId,
        username
    ),block = block
)

/**
 * Changes the sticker set of a supergroup
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the supergroup
 * @stickerSetId - New value of the supergroup sticker set identifier
 *                 Use 0 to remove the supergroup sticker set
 */
suspend fun TdAbsHandler.setSupergroupStickerSet(
    supergroupId: Int = 0,
    stickerSetId: Long = 0L
) = sync<Ok>(
    SetSupergroupStickerSet(
        supergroupId,
        stickerSetId
    )
)

/**
 * Changes the sticker set of a supergroup
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the supergroup
 * @stickerSetId - New value of the supergroup sticker set identifier
 *                 Use 0 to remove the supergroup sticker set
 */
suspend fun TdAbsHandler.setSupergroupStickerSetOrNull(
    supergroupId: Int = 0,
    stickerSetId: Long = 0L
) = syncOrNull<Ok>(
    SetSupergroupStickerSet(
        supergroupId,
        stickerSetId
    )
)

/**
 * Changes the sticker set of a supergroup
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the supergroup
 * @stickerSetId - New value of the supergroup sticker set identifier
 *                 Use 0 to remove the supergroup sticker set
 */
fun TdAbsHandler.setSupergroupStickerSet(
    supergroupId: Int = 0,
    stickerSetId: Long = 0L,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetSupergroupStickerSet(
        supergroupId,
        stickerSetId
    ),block = block
)

/**
 * Toggles sender signatures messages sent in a channel
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the channel
 * @signMessages - New value of sign_messages
 */
suspend fun TdAbsHandler.toggleSupergroupSignMessages(
    supergroupId: Int = 0,
    signMessages: Boolean = false
) = sync<Ok>(
    ToggleSupergroupSignMessages(
        supergroupId,
        signMessages
    )
)

/**
 * Toggles sender signatures messages sent in a channel
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the channel
 * @signMessages - New value of sign_messages
 */
suspend fun TdAbsHandler.toggleSupergroupSignMessagesOrNull(
    supergroupId: Int = 0,
    signMessages: Boolean = false
) = syncOrNull<Ok>(
    ToggleSupergroupSignMessages(
        supergroupId,
        signMessages
    )
)

/**
 * Toggles sender signatures messages sent in a channel
 * Requires can_change_info rights
 *
 * @supergroupId - Identifier of the channel
 * @signMessages - New value of sign_messages
 */
fun TdAbsHandler.toggleSupergroupSignMessages(
    supergroupId: Int = 0,
    signMessages: Boolean = false,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ToggleSupergroupSignMessages(
        supergroupId,
        signMessages
    ),block = block
)

/**
 * Toggles whether the message history of a supergroup is available to new members
 * Requires can_change_info rights
 *
 * @supergroupId - The identifier of the supergroup
 * @isAllHistoryAvailable - The new value of is_all_history_available
 */
suspend fun TdAbsHandler.toggleSupergroupIsAllHistoryAvailable(
    supergroupId: Int = 0,
    isAllHistoryAvailable: Boolean = false
) = sync<Ok>(
    ToggleSupergroupIsAllHistoryAvailable(
        supergroupId,
        isAllHistoryAvailable
    )
)

/**
 * Toggles whether the message history of a supergroup is available to new members
 * Requires can_change_info rights
 *
 * @supergroupId - The identifier of the supergroup
 * @isAllHistoryAvailable - The new value of is_all_history_available
 */
suspend fun TdAbsHandler.toggleSupergroupIsAllHistoryAvailableOrNull(
    supergroupId: Int = 0,
    isAllHistoryAvailable: Boolean = false
) = syncOrNull<Ok>(
    ToggleSupergroupIsAllHistoryAvailable(
        supergroupId,
        isAllHistoryAvailable
    )
)

/**
 * Toggles whether the message history of a supergroup is available to new members
 * Requires can_change_info rights
 *
 * @supergroupId - The identifier of the supergroup
 * @isAllHistoryAvailable - The new value of is_all_history_available
 */
fun TdAbsHandler.toggleSupergroupIsAllHistoryAvailable(
    supergroupId: Int = 0,
    isAllHistoryAvailable: Boolean = false,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ToggleSupergroupIsAllHistoryAvailable(
        supergroupId,
        isAllHistoryAvailable
    ),block = block
)

/**
 * Reports some messages from a user in a supergroup as spam
 * Requires administrator rights in the supergroup
 *
 * @supergroupId - Supergroup identifier
 * @userId - User identifier
 * @messageIds - Identifiers of messages sent in the supergroup by the user
 *               This list must be non-empty
 */
suspend fun TdAbsHandler.reportSupergroupSpam(
    supergroupId: Int = 0,
    userId: Int = 0,
    messageIds: LongArray = longArrayOf()
) = sync<Ok>(
    ReportSupergroupSpam(
        supergroupId,
        userId,
        messageIds
    )
)

/**
 * Reports some messages from a user in a supergroup as spam
 * Requires administrator rights in the supergroup
 *
 * @supergroupId - Supergroup identifier
 * @userId - User identifier
 * @messageIds - Identifiers of messages sent in the supergroup by the user
 *               This list must be non-empty
 */
suspend fun TdAbsHandler.reportSupergroupSpamOrNull(
    supergroupId: Int = 0,
    userId: Int = 0,
    messageIds: LongArray = longArrayOf()
) = syncOrNull<Ok>(
    ReportSupergroupSpam(
        supergroupId,
        userId,
        messageIds
    )
)

/**
 * Reports some messages from a user in a supergroup as spam
 * Requires administrator rights in the supergroup
 *
 * @supergroupId - Supergroup identifier
 * @userId - User identifier
 * @messageIds - Identifiers of messages sent in the supergroup by the user
 *               This list must be non-empty
 */
fun TdAbsHandler.reportSupergroupSpam(
    supergroupId: Int = 0,
    userId: Int = 0,
    messageIds: LongArray = longArrayOf(),
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ReportSupergroupSpam(
        supergroupId,
        userId,
        messageIds
    ),block = block
)

/**
 * Deletes a supergroup or channel along with all messages in the corresponding chat
 * This will release the supergroup or channel username and remove all members
 * Requires owner privileges in the supergroup or channel
 * Chats with more than 1000 members can't be deleted using this method
 *
 * @supergroupId - Identifier of the supergroup or channel
 */
suspend fun TdAbsHandler.deleteSupergroup(
    supergroupId: Int = 0
) = sync<Ok>(
    DeleteSupergroup(
        supergroupId
    )
)

/**
 * Deletes a supergroup or channel along with all messages in the corresponding chat
 * This will release the supergroup or channel username and remove all members
 * Requires owner privileges in the supergroup or channel
 * Chats with more than 1000 members can't be deleted using this method
 *
 * @supergroupId - Identifier of the supergroup or channel
 */
suspend fun TdAbsHandler.deleteSupergroupOrNull(
    supergroupId: Int = 0
) = syncOrNull<Ok>(
    DeleteSupergroup(
        supergroupId
    )
)

/**
 * Deletes a supergroup or channel along with all messages in the corresponding chat
 * This will release the supergroup or channel username and remove all members
 * Requires owner privileges in the supergroup or channel
 * Chats with more than 1000 members can't be deleted using this method
 *
 * @supergroupId - Identifier of the supergroup or channel
 */
fun TdAbsHandler.deleteSupergroup(
    supergroupId: Int = 0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteSupergroup(
        supergroupId
    ),block = block
)
