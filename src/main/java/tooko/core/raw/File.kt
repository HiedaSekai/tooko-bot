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

package tooko.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tooko.core.client.*

/**
 * Returns information about a file
 * This is an offline request
 *
 * @fileId - Identifier of the file to get
 */
suspend fun TdAbsHandler.getFile(
    fileId: Int
) = sync<File>(
    GetFile(
        fileId
    )
)

suspend fun TdAbsHandler.getFileOrNull(
    fileId: Int
) = syncOrNull<File>(
    GetFile(
        fileId
    )
)

fun TdAbsHandler.getFile(
    fileId: Int,
    block: (suspend CoroutineScope.(File) -> Unit)
) = send(
    GetFile(
        fileId
    ),block = block
)

/**
 * Returns information about a file by its remote ID
 * This is an offline request
 * Can be used to register a URL as a file for further uploading, or sending as a message
 * Even the request succeeds, the file can be used only if it is still accessible to the user
 * For example, if the file is from a message, then the message must be not deleted and accessible to the user
 * If the file database is disabled, then the corresponding object with the file must be preloaded by the client
 *
 * @remoteFileId - Remote identifier of the file to get
 * @fileType - File type, if known
 */
suspend fun TdAbsHandler.getRemoteFile(
    remoteFileId: String? = null,
    fileType: FileType? = null
) = sync<File>(
    GetRemoteFile(
        remoteFileId,
        fileType
    )
)

suspend fun TdAbsHandler.getRemoteFileOrNull(
    remoteFileId: String? = null,
    fileType: FileType? = null
) = syncOrNull<File>(
    GetRemoteFile(
        remoteFileId,
        fileType
    )
)

fun TdAbsHandler.getRemoteFile(
    remoteFileId: String? = null,
    fileType: FileType? = null,
    block: (suspend CoroutineScope.(File) -> Unit)
) = send(
    GetRemoteFile(
        remoteFileId,
        fileType
    ),block = block
)

/**
 * Downloads a file from the cloud
 * Download progress and completion of the download will be notified through updateFile updates
 *
 * @fileId - Identifier of the file to download
 * @priority - Priority of the download (1-32)
 *             The higher the priority, the earlier the file will be downloaded
 *             If the priorities of two files are equal, then the last one for which downloadFile was called will be downloaded first
 * @offset - The starting position from which the file should be downloaded
 * @limit - Number of bytes which should be downloaded starting from the "offset" position before the download will be automatically cancelled
 *          Use 0 to download without a limit
 * @synchronous - If false, this request returns file state just after the download has been started
 *                If true, this request returns file state only after the download has succeeded, has failed, has been cancelled or a new downloadFile request with different offset/limit parameters was sent
 */
suspend fun TdAbsHandler.downloadFile(
    fileId: Int,
    priority: Int,
    offset: Int,
    limit: Int,
    synchronous: Boolean
) = sync<File>(
    DownloadFile(
        fileId,
        priority,
        offset,
        limit,
        synchronous
    )
)

suspend fun TdAbsHandler.downloadFileOrNull(
    fileId: Int,
    priority: Int,
    offset: Int,
    limit: Int,
    synchronous: Boolean
) = syncOrNull<File>(
    DownloadFile(
        fileId,
        priority,
        offset,
        limit,
        synchronous
    )
)

fun TdAbsHandler.downloadFile(
    fileId: Int,
    priority: Int,
    offset: Int,
    limit: Int,
    synchronous: Boolean,
    block: (suspend CoroutineScope.(File) -> Unit)
) = send(
    DownloadFile(
        fileId,
        priority,
        offset,
        limit,
        synchronous
    ),block = block
)

/**
 * Returns file downloaded prefix size from a given offset
 *
 * @fileId - Identifier of the file
 * @offset - Offset from which downloaded prefix size should be calculated
 */
suspend fun TdAbsHandler.getFileDownloadedPrefixSize(
    fileId: Int,
    offset: Int
) = sync<Count>(
    GetFileDownloadedPrefixSize(
        fileId,
        offset
    )
)

suspend fun TdAbsHandler.getFileDownloadedPrefixSizeOrNull(
    fileId: Int,
    offset: Int
) = syncOrNull<Count>(
    GetFileDownloadedPrefixSize(
        fileId,
        offset
    )
)

fun TdAbsHandler.getFileDownloadedPrefixSize(
    fileId: Int,
    offset: Int,
    block: (suspend CoroutineScope.(Count) -> Unit)
) = send(
    GetFileDownloadedPrefixSize(
        fileId,
        offset
    ),block = block
)

/**
 * Asynchronously uploads a file to the cloud without sending it in a message
 * UpdateFile will be used to notify about upload progress and successful completion of the upload
 * The file will not have a persistent remote identifier until it will be sent in a message
 *
 * @file - File to upload
 * @fileType - File type
 * @priority - Priority of the upload (1-32)
 *             The higher the priority, the earlier the file will be uploaded
 *             If the priorities of two files are equal, then the first one for which uploadFile was called will be uploaded first
 */
suspend fun TdAbsHandler.uploadFile(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int
) = sync<File>(
    UploadFile(
        file,
        fileType,
        priority
    )
)

suspend fun TdAbsHandler.uploadFileOrNull(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int
) = syncOrNull<File>(
    UploadFile(
        file,
        fileType,
        priority
    )
)

fun TdAbsHandler.uploadFile(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int,
    block: (suspend CoroutineScope.(File) -> Unit)
) = send(
    UploadFile(
        file,
        fileType,
        priority
    ),block = block
)

/**
 * Writes a part of a generated file
 * This method is intended to be used only if the client has no direct access to TDLib's file system, because it is usually slower than a direct write to the destination file
 *
 * @generationId - The identifier of the generation process
 * @offset - The offset from which to write the data to the file
 * @data - The data to write
 */
suspend fun TdAbsHandler.writeGeneratedFilePart(
    generationId: Long,
    offset: Int,
    data: ByteArray
) = sync<Ok>(
    WriteGeneratedFilePart(
        generationId,
        offset,
        data
    )
)

suspend fun TdAbsHandler.writeGeneratedFilePartOrNull(
    generationId: Long,
    offset: Int,
    data: ByteArray
) = syncOrNull<Ok>(
    WriteGeneratedFilePart(
        generationId,
        offset,
        data
    )
)

fun TdAbsHandler.writeGeneratedFilePart(
    generationId: Long,
    offset: Int,
    data: ByteArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    WriteGeneratedFilePart(
        generationId,
        offset,
        data
    ),block = block
)

/**
 * Informs TDLib on a file generation progress
 *
 * @generationId - The identifier of the generation process
 * @expectedSize - Expected size of the generated file, in bytes
 *                 0 if unknown
 * @localPrefixSize - The number of bytes already generated
 */
suspend fun TdAbsHandler.setFileGenerationProgress(
    generationId: Long,
    expectedSize: Int,
    localPrefixSize: Int
) = sync<Ok>(
    SetFileGenerationProgress(
        generationId,
        expectedSize,
        localPrefixSize
    )
)

suspend fun TdAbsHandler.setFileGenerationProgressOrNull(
    generationId: Long,
    expectedSize: Int,
    localPrefixSize: Int
) = syncOrNull<Ok>(
    SetFileGenerationProgress(
        generationId,
        expectedSize,
        localPrefixSize
    )
)

fun TdAbsHandler.setFileGenerationProgress(
    generationId: Long,
    expectedSize: Int,
    localPrefixSize: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetFileGenerationProgress(
        generationId,
        expectedSize,
        localPrefixSize
    ),block = block
)

/**
 * Finishes the file generation
 *
 * @generationId - The identifier of the generation process
 * @error - If set, means that file generation has failed and should be terminated
 */
suspend fun TdAbsHandler.finishFileGeneration(
    generationId: Long,
    error: Error? = null
) = sync<Ok>(
    FinishFileGeneration(
        generationId,
        error
    )
)

suspend fun TdAbsHandler.finishFileGenerationOrNull(
    generationId: Long,
    error: Error? = null
) = syncOrNull<Ok>(
    FinishFileGeneration(
        generationId,
        error
    )
)

fun TdAbsHandler.finishFileGeneration(
    generationId: Long,
    error: Error? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    FinishFileGeneration(
        generationId,
        error
    ),block = block
)

/**
 * Reads a part of a file from the TDLib file cache and returns read bytes
 * This method is intended to be used only if the client has no direct access to TDLib's file system, because it is usually slower than a direct read from the file
 *
 * @fileId - Identifier of the file
 *           The file must be located in the TDLib file cache
 * @offset - The offset from which to read the file
 * @count - Number of bytes to read
 *          An error will be returned if there are not enough bytes available in the file from the specified position
 *          Pass 0 to read all available data from the specified position
 */
suspend fun TdAbsHandler.readFilePart(
    fileId: Int,
    offset: Int,
    count: Int
) = sync<FilePart>(
    ReadFilePart(
        fileId,
        offset,
        count
    )
)

suspend fun TdAbsHandler.readFilePartOrNull(
    fileId: Int,
    offset: Int,
    count: Int
) = syncOrNull<FilePart>(
    ReadFilePart(
        fileId,
        offset,
        count
    )
)

fun TdAbsHandler.readFilePart(
    fileId: Int,
    offset: Int,
    count: Int,
    block: (suspend CoroutineScope.(FilePart) -> Unit)
) = send(
    ReadFilePart(
        fileId,
        offset,
        count
    ),block = block
)

/**
 * Deletes a file from the TDLib file cache
 *
 * @fileId - Identifier of the file to delete
 */
suspend fun TdAbsHandler.deleteFile(
    fileId: Int
) = sync<Ok>(
    DeleteFile(
        fileId
    )
)

suspend fun TdAbsHandler.deleteFileOrNull(
    fileId: Int
) = syncOrNull<Ok>(
    DeleteFile(
        fileId
    )
)

fun TdAbsHandler.deleteFile(
    fileId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteFile(
        fileId
    ),block = block
)

/**
 * Uploads a PNG image with a sticker
 * For bots only
 * Returns the uploaded file
 *
 * @userId - Sticker file owner
 * @pngSticker - PNG image with the sticker
 *               Must be up to 512 kB in size and fit in 512x512 square
 */
suspend fun TdAbsHandler.uploadStickerFile(
    userId: Int,
    pngSticker: InputFile? = null
) = sync<File>(
    UploadStickerFile(
        userId,
        pngSticker
    )
)

suspend fun TdAbsHandler.uploadStickerFileOrNull(
    userId: Int,
    pngSticker: InputFile? = null
) = syncOrNull<File>(
    UploadStickerFile(
        userId,
        pngSticker
    )
)

fun TdAbsHandler.uploadStickerFile(
    userId: Int,
    pngSticker: InputFile? = null,
    block: (suspend CoroutineScope.(File) -> Unit)
) = send(
    UploadStickerFile(
        userId,
        pngSticker
    ),block = block
)

/**
 * Returns information about a file with a map thumbnail in PNG format
 * Only map thumbnail files with size less than 1MB can be downloaded
 *
 * @location - Location of the map center
 * @zoom - Map zoom level
 * @width - Map width in pixels before applying scale
 * @height - Map height in pixels before applying scale
 * @scale - Map scale
 * @chatId - Identifier of a chat, in which the thumbnail will be shown
 *           Use 0 if unknown
 */
suspend fun TdAbsHandler.getMapThumbnailFile(
    location: Location? = null,
    zoom: Int,
    width: Int,
    height: Int,
    scale: Int,
    chatId: Long
) = sync<File>(
    GetMapThumbnailFile(
        location,
        zoom,
        width,
        height,
        scale,
        chatId
    )
)

suspend fun TdAbsHandler.getMapThumbnailFileOrNull(
    location: Location? = null,
    zoom: Int,
    width: Int,
    height: Int,
    scale: Int,
    chatId: Long
) = syncOrNull<File>(
    GetMapThumbnailFile(
        location,
        zoom,
        width,
        height,
        scale,
        chatId
    )
)

fun TdAbsHandler.getMapThumbnailFile(
    location: Location? = null,
    zoom: Int,
    width: Int,
    height: Int,
    scale: Int,
    chatId: Long,
    block: (suspend CoroutineScope.(File) -> Unit)
) = send(
    GetMapThumbnailFile(
        location,
        zoom,
        width,
        height,
        scale,
        chatId
    ),block = block
)
