@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Closes the TDLib instance after a proper logout
 * Requires an available network connection
 * All local data will be destroyed
 * After the logout completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.logOut() = sync<Ok>(
    LogOut()
)

/**
 * Closes the TDLib instance after a proper logout
 * Requires an available network connection
 * All local data will be destroyed
 * After the logout completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.logOutOrNull() = syncOrNull<Ok>(
    LogOut()
)

/**
 * Closes the TDLib instance after a proper logout
 * Requires an available network connection
 * All local data will be destroyed
 * After the logout completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
fun TdAbsHandler.logOut(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    LogOut(),block = block
)

/**
 * Saves application log event on the server
 * Can be called before authorization
 *
 * @type - Event type
 * @chatId - Optional chat identifier, associated with the event
 * @data - The log event data
 */
suspend fun TdAbsHandler.saveApplicationLogEvent(
    type: String? = null,
    chatId: Long = 0L,
    data: JsonValue? = null
) = sync<Ok>(
    SaveApplicationLogEvent(
        type,
        chatId,
        data
    )
)

/**
 * Saves application log event on the server
 * Can be called before authorization
 *
 * @type - Event type
 * @chatId - Optional chat identifier, associated with the event
 * @data - The log event data
 */
suspend fun TdAbsHandler.saveApplicationLogEventOrNull(
    type: String? = null,
    chatId: Long = 0L,
    data: JsonValue? = null
) = syncOrNull<Ok>(
    SaveApplicationLogEvent(
        type,
        chatId,
        data
    )
)

/**
 * Saves application log event on the server
 * Can be called before authorization
 *
 * @type - Event type
 * @chatId - Optional chat identifier, associated with the event
 * @data - The log event data
 */
fun TdAbsHandler.saveApplicationLogEvent(
    type: String? = null,
    chatId: Long = 0L,
    data: JsonValue? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SaveApplicationLogEvent(
        type,
        chatId,
        data
    ),block = block
)

/**
 * Sets new log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @logStream - New log stream
 */
suspend fun TdAbsHandler.setLogStream(
    logStream: LogStream? = null
) = sync<Ok>(
    SetLogStream(
        logStream
    )
)

/**
 * Sets new log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @logStream - New log stream
 */
suspend fun TdAbsHandler.setLogStreamOrNull(
    logStream: LogStream? = null
) = syncOrNull<Ok>(
    SetLogStream(
        logStream
    )
)

/**
 * Sets new log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @logStream - New log stream
 */
fun TdAbsHandler.setLogStream(
    logStream: LogStream? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetLogStream(
        logStream
    ),block = block
)

/**
 * Returns information about currently used log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
suspend fun TdAbsHandler.getLogStream() = sync<LogStream>(
    GetLogStream()
)

/**
 * Returns information about currently used log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
suspend fun TdAbsHandler.getLogStreamOrNull() = syncOrNull<LogStream>(
    GetLogStream()
)

/**
 * Returns information about currently used log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun TdAbsHandler.getLogStream(
    block: (suspend CoroutineScope.(LogStream) -> Unit)
) = send(
    GetLogStream(),block = block
)

/**
 * Sets the verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @newVerbosityLevel - New value of the verbosity level for logging
 *                      Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging
 */
suspend fun TdAbsHandler.setLogVerbosityLevel(
    newVerbosityLevel: Int = 0
) = sync<Ok>(
    SetLogVerbosityLevel(
        newVerbosityLevel
    )
)

/**
 * Sets the verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @newVerbosityLevel - New value of the verbosity level for logging
 *                      Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging
 */
suspend fun TdAbsHandler.setLogVerbosityLevelOrNull(
    newVerbosityLevel: Int = 0
) = syncOrNull<Ok>(
    SetLogVerbosityLevel(
        newVerbosityLevel
    )
)

/**
 * Sets the verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @newVerbosityLevel - New value of the verbosity level for logging
 *                      Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging
 */
fun TdAbsHandler.setLogVerbosityLevel(
    newVerbosityLevel: Int = 0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetLogVerbosityLevel(
        newVerbosityLevel
    ),block = block
)

/**
 * Returns current verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
suspend fun TdAbsHandler.getLogVerbosityLevel() = sync<LogVerbosityLevel>(
    GetLogVerbosityLevel()
)

/**
 * Returns current verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
suspend fun TdAbsHandler.getLogVerbosityLevelOrNull() = syncOrNull<LogVerbosityLevel>(
    GetLogVerbosityLevel()
)

/**
 * Returns current verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun TdAbsHandler.getLogVerbosityLevel(
    block: (suspend CoroutineScope.(LogVerbosityLevel) -> Unit)
) = send(
    GetLogVerbosityLevel(),block = block
)

/**
 * Returns list of available TDLib internal log tags, for example, ["actor", "binlog", "connections", "notifications", "proxy"]
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
suspend fun TdAbsHandler.getLogTags() = sync<LogTags>(
    GetLogTags()
)

/**
 * Returns list of available TDLib internal log tags, for example, ["actor", "binlog", "connections", "notifications", "proxy"]
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
suspend fun TdAbsHandler.getLogTagsOrNull() = syncOrNull<LogTags>(
    GetLogTags()
)

/**
 * Returns list of available TDLib internal log tags, for example, ["actor", "binlog", "connections", "notifications", "proxy"]
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun TdAbsHandler.getLogTags(
    block: (suspend CoroutineScope.(LogTags) -> Unit)
) = send(
    GetLogTags(),block = block
)

/**
 * Sets the verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 * @newVerbosityLevel - New verbosity level
 */
suspend fun TdAbsHandler.setLogTagVerbosityLevel(
    tag: String? = null,
    newVerbosityLevel: Int = 0
) = sync<Ok>(
    SetLogTagVerbosityLevel(
        tag,
        newVerbosityLevel
    )
)

/**
 * Sets the verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 * @newVerbosityLevel - New verbosity level
 */
suspend fun TdAbsHandler.setLogTagVerbosityLevelOrNull(
    tag: String? = null,
    newVerbosityLevel: Int = 0
) = syncOrNull<Ok>(
    SetLogTagVerbosityLevel(
        tag,
        newVerbosityLevel
    )
)

/**
 * Sets the verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 * @newVerbosityLevel - New verbosity level
 */
fun TdAbsHandler.setLogTagVerbosityLevel(
    tag: String? = null,
    newVerbosityLevel: Int = 0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetLogTagVerbosityLevel(
        tag,
        newVerbosityLevel
    ),block = block
)

/**
 * Returns current verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 */
suspend fun TdAbsHandler.getLogTagVerbosityLevel(
    tag: String? = null
) = sync<LogVerbosityLevel>(
    GetLogTagVerbosityLevel(
        tag
    )
)

/**
 * Returns current verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 */
suspend fun TdAbsHandler.getLogTagVerbosityLevelOrNull(
    tag: String? = null
) = syncOrNull<LogVerbosityLevel>(
    GetLogTagVerbosityLevel(
        tag
    )
)

/**
 * Returns current verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 */
fun TdAbsHandler.getLogTagVerbosityLevel(
    tag: String? = null,
    block: (suspend CoroutineScope.(LogVerbosityLevel) -> Unit)
) = send(
    GetLogTagVerbosityLevel(
        tag
    ),block = block
)

/**
 * Adds a message to TDLib internal log
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @verbosityLevel - The minimum verbosity level needed for the message to be logged, 0-1023
 * @text - Text of a message to log
 */
suspend fun TdAbsHandler.addLogMessage(
    verbosityLevel: Int = 0,
    text: String? = null
) = sync<Ok>(
    AddLogMessage(
        verbosityLevel,
        text
    )
)

/**
 * Adds a message to TDLib internal log
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @verbosityLevel - The minimum verbosity level needed for the message to be logged, 0-1023
 * @text - Text of a message to log
 */
suspend fun TdAbsHandler.addLogMessageOrNull(
    verbosityLevel: Int = 0,
    text: String? = null
) = syncOrNull<Ok>(
    AddLogMessage(
        verbosityLevel,
        text
    )
)

/**
 * Adds a message to TDLib internal log
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @verbosityLevel - The minimum verbosity level needed for the message to be logged, 0-1023
 * @text - Text of a message to log
 */
fun TdAbsHandler.addLogMessage(
    verbosityLevel: Int = 0,
    text: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddLogMessage(
        verbosityLevel,
        text
    ),block = block
)
