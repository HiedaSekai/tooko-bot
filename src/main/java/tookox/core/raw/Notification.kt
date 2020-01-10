@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Removes an active notification from notification list
 * Needs to be called only if the notification is removed by the current user
 *
 * @notificationGroupId - Identifier of notification group to which the notification belongs
 * @notificationId - Identifier of removed notification
 */
suspend fun TdAbsHandler.removeNotification(
    notificationGroupId: Int = 0,
    notificationId: Int = 0
) = sync<Ok>(
    RemoveNotification(
        notificationGroupId,
        notificationId
    )
)

/**
 * Removes an active notification from notification list
 * Needs to be called only if the notification is removed by the current user
 *
 * @notificationGroupId - Identifier of notification group to which the notification belongs
 * @notificationId - Identifier of removed notification
 */
suspend fun TdAbsHandler.removeNotificationOrNull(
    notificationGroupId: Int = 0,
    notificationId: Int = 0
) = syncOrNull<Ok>(
    RemoveNotification(
        notificationGroupId,
        notificationId
    )
)

/**
 * Removes an active notification from notification list
 * Needs to be called only if the notification is removed by the current user
 *
 * @notificationGroupId - Identifier of notification group to which the notification belongs
 * @notificationId - Identifier of removed notification
 */
fun TdAbsHandler.removeNotification(
    notificationGroupId: Int = 0,
    notificationId: Int = 0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveNotification(
        notificationGroupId,
        notificationId
    ),block = block
)

/**
 * Removes a group of active notifications
 * Needs to be called only if the notification group is removed by the current user
 *
 * @notificationGroupId - Notification group identifier
 * @maxNotificationId - The maximum identifier of removed notifications
 */
suspend fun TdAbsHandler.removeNotificationGroup(
    notificationGroupId: Int = 0,
    maxNotificationId: Int = 0
) = sync<Ok>(
    RemoveNotificationGroup(
        notificationGroupId,
        maxNotificationId
    )
)

/**
 * Removes a group of active notifications
 * Needs to be called only if the notification group is removed by the current user
 *
 * @notificationGroupId - Notification group identifier
 * @maxNotificationId - The maximum identifier of removed notifications
 */
suspend fun TdAbsHandler.removeNotificationGroupOrNull(
    notificationGroupId: Int = 0,
    maxNotificationId: Int = 0
) = syncOrNull<Ok>(
    RemoveNotificationGroup(
        notificationGroupId,
        maxNotificationId
    )
)

/**
 * Removes a group of active notifications
 * Needs to be called only if the notification group is removed by the current user
 *
 * @notificationGroupId - Notification group identifier
 * @maxNotificationId - The maximum identifier of removed notifications
 */
fun TdAbsHandler.removeNotificationGroup(
    notificationGroupId: Int = 0,
    maxNotificationId: Int = 0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveNotificationGroup(
        notificationGroupId,
        maxNotificationId
    ),block = block
)

/**
 * Returns the notification settings for chats of a given type
 *
 * @scope - Types of chats for which to return the notification settings information
 */
suspend fun TdAbsHandler.getScopeNotificationSettings(
    scope: NotificationSettingsScope? = null
) = sync<ScopeNotificationSettings>(
    GetScopeNotificationSettings(
        scope
    )
)

/**
 * Returns the notification settings for chats of a given type
 *
 * @scope - Types of chats for which to return the notification settings information
 */
suspend fun TdAbsHandler.getScopeNotificationSettingsOrNull(
    scope: NotificationSettingsScope? = null
) = syncOrNull<ScopeNotificationSettings>(
    GetScopeNotificationSettings(
        scope
    )
)

/**
 * Returns the notification settings for chats of a given type
 *
 * @scope - Types of chats for which to return the notification settings information
 */
fun TdAbsHandler.getScopeNotificationSettings(
    scope: NotificationSettingsScope? = null,
    block: (suspend CoroutineScope.(ScopeNotificationSettings) -> Unit)
) = send(
    GetScopeNotificationSettings(
        scope
    ),block = block
)

/**
 * Changes notification settings for chats of a given type
 *
 * @scope - Types of chats for which to change the notification settings
 * @notificationSettings - The new notification settings for the given scope
 */
suspend fun TdAbsHandler.setScopeNotificationSettings(
    scope: NotificationSettingsScope? = null,
    notificationSettings: ScopeNotificationSettings? = null
) = sync<Ok>(
    SetScopeNotificationSettings(
        scope,
        notificationSettings
    )
)

/**
 * Changes notification settings for chats of a given type
 *
 * @scope - Types of chats for which to change the notification settings
 * @notificationSettings - The new notification settings for the given scope
 */
suspend fun TdAbsHandler.setScopeNotificationSettingsOrNull(
    scope: NotificationSettingsScope? = null,
    notificationSettings: ScopeNotificationSettings? = null
) = syncOrNull<Ok>(
    SetScopeNotificationSettings(
        scope,
        notificationSettings
    )
)

/**
 * Changes notification settings for chats of a given type
 *
 * @scope - Types of chats for which to change the notification settings
 * @notificationSettings - The new notification settings for the given scope
 */
fun TdAbsHandler.setScopeNotificationSettings(
    scope: NotificationSettingsScope? = null,
    notificationSettings: ScopeNotificationSettings? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetScopeNotificationSettings(
        scope,
        notificationSettings
    ),block = block
)

/**
 * Resets all notification settings to their default values
 * By default, all chats are unmuted, the sound is set to "default" and message previews are shown
 */
suspend fun TdAbsHandler.resetAllNotificationSettings() = sync<Ok>(
    ResetAllNotificationSettings()
)

/**
 * Resets all notification settings to their default values
 * By default, all chats are unmuted, the sound is set to "default" and message previews are shown
 */
suspend fun TdAbsHandler.resetAllNotificationSettingsOrNull() = syncOrNull<Ok>(
    ResetAllNotificationSettings()
)

/**
 * Resets all notification settings to their default values
 * By default, all chats are unmuted, the sound is set to "default" and message previews are shown
 */
fun TdAbsHandler.resetAllNotificationSettings(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ResetAllNotificationSettings(),block = block
)

/**
 * Handles a push notification
 * Returns error with code 406 if the push notification is not supported and connection to the server is required to fetch new data
 * Can be called before authorization
 *
 * @payload - JSON-encoded push notification payload with all fields sent by the server, and "google.sent_time" and "google.notification.sound" fields added
 */
suspend fun TdAbsHandler.processPushNotification(
    payload: String? = null
) = sync<Ok>(
    ProcessPushNotification(
        payload
    )
)

/**
 * Handles a push notification
 * Returns error with code 406 if the push notification is not supported and connection to the server is required to fetch new data
 * Can be called before authorization
 *
 * @payload - JSON-encoded push notification payload with all fields sent by the server, and "google.sent_time" and "google.notification.sound" fields added
 */
suspend fun TdAbsHandler.processPushNotificationOrNull(
    payload: String? = null
) = syncOrNull<Ok>(
    ProcessPushNotification(
        payload
    )
)

/**
 * Handles a push notification
 * Returns error with code 406 if the push notification is not supported and connection to the server is required to fetch new data
 * Can be called before authorization
 *
 * @payload - JSON-encoded push notification payload with all fields sent by the server, and "google.sent_time" and "google.notification.sound" fields added
 */
fun TdAbsHandler.processPushNotification(
    payload: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ProcessPushNotification(
        payload
    ),block = block
)

/**
 * Returns a globally unique push notification subscription identifier for identification of an account, which has received a push notification
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @payload - JSON-encoded push notification payload
 */
suspend fun TdAbsHandler.getPushReceiverId(
    payload: String? = null
) = sync<PushReceiverId>(
    GetPushReceiverId(
        payload
    )
)

/**
 * Returns a globally unique push notification subscription identifier for identification of an account, which has received a push notification
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @payload - JSON-encoded push notification payload
 */
suspend fun TdAbsHandler.getPushReceiverIdOrNull(
    payload: String? = null
) = syncOrNull<PushReceiverId>(
    GetPushReceiverId(
        payload
    )
)

/**
 * Returns a globally unique push notification subscription identifier for identification of an account, which has received a push notification
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @payload - JSON-encoded push notification payload
 */
fun TdAbsHandler.getPushReceiverId(
    payload: String? = null,
    block: (suspend CoroutineScope.(PushReceiverId) -> Unit)
) = send(
    GetPushReceiverId(
        payload
    ),block = block
)
