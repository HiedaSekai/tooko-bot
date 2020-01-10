@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Checks whether the current session can be used to transfer a chat ownership to another user
 */
suspend fun TdAbsHandler.canTransferOwnership() = sync<CanTransferOwnershipResult>(
    CanTransferOwnership()
)

suspend fun TdAbsHandler.canTransferOwnershipOrNull() = syncOrNull<CanTransferOwnershipResult>(
    CanTransferOwnership()
)

fun TdAbsHandler.canTransferOwnership(
    block: (suspend CoroutineScope.(CanTransferOwnershipResult) -> Unit)
) = send(
    CanTransferOwnership(),block = block
)

/**
 * Stops the downloading of a file
 * If a file has already been downloaded, does nothing
 *
 * @fileId - Identifier of a file to stop downloading
 * @onlyIfPending - Pass true to stop downloading only if it hasn't been started, i.e
 *                  Request hasn't been sent to server
 */
suspend fun TdAbsHandler.cancelDownloadFile(
    fileId: Int,
    onlyIfPending: Boolean
) = sync<Ok>(
    CancelDownloadFile(
        fileId,
        onlyIfPending
    )
)

suspend fun TdAbsHandler.cancelDownloadFileOrNull(
    fileId: Int,
    onlyIfPending: Boolean
) = syncOrNull<Ok>(
    CancelDownloadFile(
        fileId,
        onlyIfPending
    )
)

fun TdAbsHandler.cancelDownloadFile(
    fileId: Int,
    onlyIfPending: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CancelDownloadFile(
        fileId,
        onlyIfPending
    ),block = block
)

/**
 * Stops the uploading of a file
 * Supported only for files uploaded by using uploadFile
 * For other files the behavior is undefined
 *
 * @fileId - Identifier of the file to stop uploading
 */
suspend fun TdAbsHandler.cancelUploadFile(
    fileId: Int
) = sync<Ok>(
    CancelUploadFile(
        fileId
    )
)

suspend fun TdAbsHandler.cancelUploadFileOrNull(
    fileId: Int
) = syncOrNull<Ok>(
    CancelUploadFile(
        fileId
    )
)

fun TdAbsHandler.cancelUploadFile(
    fileId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CancelUploadFile(
        fileId
    ),block = block
)
