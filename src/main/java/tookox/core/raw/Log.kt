/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

suspend fun TdAbsHandler.logOutOrNull() = syncOrNull<Ok>(
    LogOut()
)

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
    chatId: Long,
    data: JsonValue? = null
) = sync<Ok>(
    SaveApplicationLogEvent(
        type,
        chatId,
        data
    )
)

suspend fun TdAbsHandler.saveApplicationLogEventOrNull(
    type: String? = null,
    chatId: Long,
    data: JsonValue? = null
) = syncOrNull<Ok>(
    SaveApplicationLogEvent(
        type,
        chatId,
        data
    )
)

fun TdAbsHandler.saveApplicationLogEvent(
    type: String? = null,
    chatId: Long,
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

suspend fun TdAbsHandler.setLogStreamOrNull(
    logStream: LogStream? = null
) = syncOrNull<Ok>(
    SetLogStream(
        logStream
    )
)

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

suspend fun TdAbsHandler.getLogStreamOrNull() = syncOrNull<LogStream>(
    GetLogStream()
)

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
    newVerbosityLevel: Int
) = sync<Ok>(
    SetLogVerbosityLevel(
        newVerbosityLevel
    )
)

suspend fun TdAbsHandler.setLogVerbosityLevelOrNull(
    newVerbosityLevel: Int
) = syncOrNull<Ok>(
    SetLogVerbosityLevel(
        newVerbosityLevel
    )
)

fun TdAbsHandler.setLogVerbosityLevel(
    newVerbosityLevel: Int,
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

suspend fun TdAbsHandler.getLogVerbosityLevelOrNull() = syncOrNull<LogVerbosityLevel>(
    GetLogVerbosityLevel()
)

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

suspend fun TdAbsHandler.getLogTagsOrNull() = syncOrNull<LogTags>(
    GetLogTags()
)

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
    newVerbosityLevel: Int
) = sync<Ok>(
    SetLogTagVerbosityLevel(
        tag,
        newVerbosityLevel
    )
)

suspend fun TdAbsHandler.setLogTagVerbosityLevelOrNull(
    tag: String? = null,
    newVerbosityLevel: Int
) = syncOrNull<Ok>(
    SetLogTagVerbosityLevel(
        tag,
        newVerbosityLevel
    )
)

fun TdAbsHandler.setLogTagVerbosityLevel(
    tag: String? = null,
    newVerbosityLevel: Int,
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

suspend fun TdAbsHandler.getLogTagVerbosityLevelOrNull(
    tag: String? = null
) = syncOrNull<LogVerbosityLevel>(
    GetLogTagVerbosityLevel(
        tag
    )
)

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
    verbosityLevel: Int,
    text: String? = null
) = sync<Ok>(
    AddLogMessage(
        verbosityLevel,
        text
    )
)

suspend fun TdAbsHandler.addLogMessageOrNull(
    verbosityLevel: Int,
    text: String? = null
) = syncOrNull<Ok>(
    AddLogMessage(
        verbosityLevel,
        text
    )
)

fun TdAbsHandler.addLogMessage(
    verbosityLevel: Int,
    text: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddLogMessage(
        verbosityLevel,
        text
    ),block = block
)
