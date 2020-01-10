@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Finishes user registration
 * Works only when the current authorization state is authorizationStateWaitRegistration
 *
 * @firstName - The first name of the user
 * @lastName - The last name of the user
 */
suspend fun TdAbsHandler.registerUser(
    firstName: String? = null,
    lastName: String? = null
) = sync<Ok>(
    RegisterUser(
        firstName,
        lastName
    )
)

suspend fun TdAbsHandler.registerUserOrNull(
    firstName: String? = null,
    lastName: String? = null
) = syncOrNull<Ok>(
    RegisterUser(
        firstName,
        lastName
    )
)

fun TdAbsHandler.registerUser(
    firstName: String? = null,
    lastName: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RegisterUser(
        firstName,
        lastName
    ),block = block
)

/**
 * Returns the current user
 */
suspend fun TdAbsHandler.getMe() = sync<User>(
    GetMe()
)

suspend fun TdAbsHandler.getMeOrNull() = syncOrNull<User>(
    GetMe()
)

fun TdAbsHandler.getMe(
    block: (suspend CoroutineScope.(User) -> Unit)
) = send(
    GetMe(),block = block
)

/**
 * Returns information about a user by their identifier
 * This is an offline request if the current user is not a bot
 *
 * @userId - User identifier
 */
suspend fun TdAbsHandler.getUser(
    userId: Int
) = sync<User>(
    GetUser(
        userId
    )
)

suspend fun TdAbsHandler.getUserOrNull(
    userId: Int
) = syncOrNull<User>(
    GetUser(
        userId
    )
)

fun TdAbsHandler.getUser(
    userId: Int,
    block: (suspend CoroutineScope.(User) -> Unit)
) = send(
    GetUser(
        userId
    ),block = block
)

/**
 * Returns full information about a user by their identifier
 *
 * @userId - User identifier
 */
suspend fun TdAbsHandler.getUserFullInfo(
    userId: Int
) = sync<UserFullInfo>(
    GetUserFullInfo(
        userId
    )
)

suspend fun TdAbsHandler.getUserFullInfoOrNull(
    userId: Int
) = syncOrNull<UserFullInfo>(
    GetUserFullInfo(
        userId
    )
)

fun TdAbsHandler.getUserFullInfo(
    userId: Int,
    block: (suspend CoroutineScope.(UserFullInfo) -> Unit)
) = send(
    GetUserFullInfo(
        userId
    ),block = block
)

/**
 * Adds a user to the blacklist
 *
 * @userId - User identifier
 */
suspend fun TdAbsHandler.blockUser(
    userId: Int
) = sync<Ok>(
    BlockUser(
        userId
    )
)

suspend fun TdAbsHandler.blockUserOrNull(
    userId: Int
) = syncOrNull<Ok>(
    BlockUser(
        userId
    )
)

fun TdAbsHandler.blockUser(
    userId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    BlockUser(
        userId
    ),block = block
)

/**
 * Removes a user from the blacklist
 *
 * @userId - User identifier
 */
suspend fun TdAbsHandler.unblockUser(
    userId: Int
) = sync<Ok>(
    UnblockUser(
        userId
    )
)

suspend fun TdAbsHandler.unblockUserOrNull(
    userId: Int
) = syncOrNull<Ok>(
    UnblockUser(
        userId
    )
)

fun TdAbsHandler.unblockUser(
    userId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    UnblockUser(
        userId
    ),block = block
)

/**
 * Returns users that were blocked by the current user
 *
 * @offset - Number of users to skip in the result
 * @limit - The maximum number of users to return
 */
suspend fun TdAbsHandler.getBlockedUsers(
    offset: Int,
    limit: Int
) = sync<Users>(
    GetBlockedUsers(
        offset,
        limit
    )
)

suspend fun TdAbsHandler.getBlockedUsersOrNull(
    offset: Int,
    limit: Int
) = syncOrNull<Users>(
    GetBlockedUsers(
        offset,
        limit
    )
)

fun TdAbsHandler.getBlockedUsers(
    offset: Int,
    limit: Int,
    block: (suspend CoroutineScope.(Users) -> Unit)
) = send(
    GetBlockedUsers(
        offset,
        limit
    ),block = block
)

/**
 * Returns all user contacts
 */
suspend fun TdAbsHandler.getContacts() = sync<Users>(
    GetContacts()
)

suspend fun TdAbsHandler.getContactsOrNull() = syncOrNull<Users>(
    GetContacts()
)

fun TdAbsHandler.getContacts(
    block: (suspend CoroutineScope.(Users) -> Unit)
) = send(
    GetContacts(),block = block
)

/**
 * Searches for the specified query in the first names, last names and usernames of the known user contacts
 *
 * @query - Query to search for
 *          May be empty to return all contacts
 * @limit - The maximum number of users to be returned
 */
suspend fun TdAbsHandler.searchContacts(
    query: String? = null,
    limit: Int
) = sync<Users>(
    SearchContacts(
        query,
        limit
    )
)

suspend fun TdAbsHandler.searchContactsOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull<Users>(
    SearchContacts(
        query,
        limit
    )
)

fun TdAbsHandler.searchContacts(
    query: String? = null,
    limit: Int,
    block: (suspend CoroutineScope.(Users) -> Unit)
) = send(
    SearchContacts(
        query,
        limit
    ),block = block
)

/**
 * Returns the profile photos of a user
 * The result of this query may be outdated: some photos might have been deleted already
 *
 * @userId - User identifier
 * @offset - The number of photos to skip
 * @limit - The maximum number of photos to be returned
 */
suspend fun TdAbsHandler.getUserProfilePhotos(
    userId: Int,
    offset: Int,
    limit: Int
) = sync<UserProfilePhotos>(
    GetUserProfilePhotos(
        userId,
        offset,
        limit
    )
)

suspend fun TdAbsHandler.getUserProfilePhotosOrNull(
    userId: Int,
    offset: Int,
    limit: Int
) = syncOrNull<UserProfilePhotos>(
    GetUserProfilePhotos(
        userId,
        offset,
        limit
    )
)

fun TdAbsHandler.getUserProfilePhotos(
    userId: Int,
    offset: Int,
    limit: Int,
    block: (suspend CoroutineScope.(UserProfilePhotos) -> Unit)
) = send(
    GetUserProfilePhotos(
        userId,
        offset,
        limit
    ),block = block
)

/**
 * Returns up to 20 recently used inline bots in the order of their last usage
 */
suspend fun TdAbsHandler.getRecentInlineBots() = sync<Users>(
    GetRecentInlineBots()
)

suspend fun TdAbsHandler.getRecentInlineBotsOrNull() = syncOrNull<Users>(
    GetRecentInlineBots()
)

fun TdAbsHandler.getRecentInlineBots(
    block: (suspend CoroutineScope.(Users) -> Unit)
) = send(
    GetRecentInlineBots(),block = block
)

/**
 * Changes the first and last name of the current user
 * If something changes, updateUser will be sent
 *
 * @firstName - The new value of the first name for the user
 * @lastName - The new value of the optional last name for the user
 */
suspend fun TdAbsHandler.setName(
    firstName: String? = null,
    lastName: String? = null
) = sync<Ok>(
    SetName(
        firstName,
        lastName
    )
)

suspend fun TdAbsHandler.setNameOrNull(
    firstName: String? = null,
    lastName: String? = null
) = syncOrNull<Ok>(
    SetName(
        firstName,
        lastName
    )
)

fun TdAbsHandler.setName(
    firstName: String? = null,
    lastName: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetName(
        firstName,
        lastName
    ),block = block
)

/**
 * Changes the bio of the current user
 *
 * @bio - The new value of the user bio
 */
suspend fun TdAbsHandler.setBio(
    bio: String? = null
) = sync<Ok>(
    SetBio(
        bio
    )
)

suspend fun TdAbsHandler.setBioOrNull(
    bio: String? = null
) = syncOrNull<Ok>(
    SetBio(
        bio
    )
)

fun TdAbsHandler.setBio(
    bio: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetBio(
        bio
    ),block = block
)

/**
 * Changes the username of the current user
 * If something changes, updateUser will be sent
 *
 * @username - The new value of the username
 *             Use an empty string to remove the username
 */
suspend fun TdAbsHandler.setUsername(
    username: String? = null
) = sync<Ok>(
    SetUsername(
        username
    )
)

suspend fun TdAbsHandler.setUsernameOrNull(
    username: String? = null
) = syncOrNull<Ok>(
    SetUsername(
        username
    )
)

fun TdAbsHandler.setUsername(
    username: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetUsername(
        username
    ),block = block
)

/**
 * Returns a user that can be contacted to get support
 */
suspend fun TdAbsHandler.getSupportUser() = sync<User>(
    GetSupportUser()
)

suspend fun TdAbsHandler.getSupportUserOrNull() = syncOrNull<User>(
    GetSupportUser()
)

fun TdAbsHandler.getSupportUser(
    block: (suspend CoroutineScope.(User) -> Unit)
) = send(
    GetSupportUser(),block = block
)

/**
 * Changes user privacy settings
 *
 * @setting - The privacy setting
 * @rules - The new privacy rules
 */
suspend fun TdAbsHandler.setUserPrivacySettingRules(
    setting: UserPrivacySetting? = null,
    rules: UserPrivacySettingRules? = null
) = sync<Ok>(
    SetUserPrivacySettingRules(
        setting,
        rules
    )
)

suspend fun TdAbsHandler.setUserPrivacySettingRulesOrNull(
    setting: UserPrivacySetting? = null,
    rules: UserPrivacySettingRules? = null
) = syncOrNull<Ok>(
    SetUserPrivacySettingRules(
        setting,
        rules
    )
)

fun TdAbsHandler.setUserPrivacySettingRules(
    setting: UserPrivacySetting? = null,
    rules: UserPrivacySettingRules? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetUserPrivacySettingRules(
        setting,
        rules
    ),block = block
)

/**
 * Returns the current privacy settings
 *
 * @setting - The privacy setting
 */
suspend fun TdAbsHandler.getUserPrivacySettingRules(
    setting: UserPrivacySetting? = null
) = sync<UserPrivacySettingRules>(
    GetUserPrivacySettingRules(
        setting
    )
)

suspend fun TdAbsHandler.getUserPrivacySettingRulesOrNull(
    setting: UserPrivacySetting? = null
) = syncOrNull<UserPrivacySettingRules>(
    GetUserPrivacySettingRules(
        setting
    )
)

fun TdAbsHandler.getUserPrivacySettingRules(
    setting: UserPrivacySetting? = null,
    block: (suspend CoroutineScope.(UserPrivacySettingRules) -> Unit)
) = send(
    GetUserPrivacySettingRules(
        setting
    ),block = block
)
