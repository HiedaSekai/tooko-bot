@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns information about a file
 * This is an offline request
 *
 * @fileId - Identifier of the file to get
 */
suspend fun TdAbsHandler.getFile(
    fileId: Int = 0
) = sync<File>(
    GetFile(
        fileId
    )
)

/**
 * Returns information about a file
 * This is an offline request
 *
 * @fileId - Identifier of the file to get
 */
suspend fun TdAbsHandler.getFileOrNull(
    fileId: Int = 0
) = syncOrNull<File>(
    GetFile(
        fileId
    )
)

/**
 * Returns information about a file
 * This is an offline request
 *
 * @fileId - Identifier of the file to get
 */
fun TdAbsHandler.getFile(
    fileId: Int = 0,
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
suspend fun TdAbsHandler.getRemoteFileOrNull(
    remoteFileId: String? = null,
    fileType: FileType? = null
) = syncOrNull<File>(
    GetRemoteFile(
        remoteFileId,
        fileType
    )
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
    fileId: Int = 0,
    priority: Int = 0,
    offset: Int = 0,
    limit: Int = 0,
    synchronous: Boolean = false
) = sync<File>(
    DownloadFile(
        fileId,
        priority,
        offset,
        limit,
        synchronous
    )
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
suspend fun TdAbsHandler.downloadFileOrNull(
    fileId: Int = 0,
    priority: Int = 0,
    offset: Int = 0,
    limit: Int = 0,
    synchronous: Boolean = false
) = syncOrNull<File>(
    DownloadFile(
        fileId,
        priority,
        offset,
        limit,
        synchronous
    )
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
fun TdAbsHandler.downloadFile(
    fileId: Int = 0,
    priority: Int = 0,
    offset: Int = 0,
    limit: Int = 0,
    synchronous: Boolean = false,
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
    fileId: Int = 0,
    offset: Int = 0
) = sync<Count>(
    GetFileDownloadedPrefixSize(
        fileId,
        offset
    )
)

/**
 * Returns file downloaded prefix size from a given offset
 *
 * @fileId - Identifier of the file
 * @offset - Offset from which downloaded prefix size should be calculated
 */
suspend fun TdAbsHandler.getFileDownloadedPrefixSizeOrNull(
    fileId: Int = 0,
    offset: Int = 0
) = syncOrNull<Count>(
    GetFileDownloadedPrefixSize(
        fileId,
        offset
    )
)

/**
 * Returns file downloaded prefix size from a given offset
 *
 * @fileId - Identifier of the file
 * @offset - Offset from which downloaded prefix size should be calculated
 */
fun TdAbsHandler.getFileDownloadedPrefixSize(
    fileId: Int = 0,
    offset: Int = 0,
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
    priority: Int = 0
) = sync<File>(
    UploadFile(
        file,
        fileType,
        priority
    )
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
suspend fun TdAbsHandler.uploadFileOrNull(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int = 0
) = syncOrNull<File>(
    UploadFile(
        file,
        fileType,
        priority
    )
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
fun TdAbsHandler.uploadFile(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int = 0,
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
    generationId: Long = 0L,
    offset: Int = 0,
    data: ByteArray = byteArrayOf()
) = sync<Ok>(
    WriteGeneratedFilePart(
        generationId,
        offset,
        data
    )
)

/**
 * Writes a part of a generated file
 * This method is intended to be used only if the client has no direct access to TDLib's file system, because it is usually slower than a direct write to the destination file
 *
 * @generationId - The identifier of the generation process
 * @offset - The offset from which to write the data to the file
 * @data - The data to write
 */
suspend fun TdAbsHandler.writeGeneratedFilePartOrNull(
    generationId: Long = 0L,
    offset: Int = 0,
    data: ByteArray = byteArrayOf()
) = syncOrNull<Ok>(
    WriteGeneratedFilePart(
        generationId,
        offset,
        data
    )
)

/**
 * Writes a part of a generated file
 * This method is intended to be used only if the client has no direct access to TDLib's file system, because it is usually slower than a direct write to the destination file
 *
 * @generationId - The identifier of the generation process
 * @offset - The offset from which to write the data to the file
 * @data - The data to write
 */
fun TdAbsHandler.writeGeneratedFilePart(
    generationId: Long = 0L,
    offset: Int = 0,
    data: ByteArray = byteArrayOf(),
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    WriteGeneratedFilePart(
        generationId,
        offset,
        data
    ),block = block
)

/**
 * Informs TDLib on a file generation prograss
 *
 * @generationId - The identifier of the generation process
 * @expectedSize - Expected size of the generated file, in bytes
 *                 0 if unknown
 * @localPrefixSize - The number of bytes already generated
 */
suspend fun TdAbsHandler.setFileGenerationProgress(
    generationId: Long = 0L,
    expectedSize: Int = 0,
    localPrefixSize: Int = 0
) = sync<Ok>(
    SetFileGenerationProgress(
        generationId,
        expectedSize,
        localPrefixSize
    )
)

/**
 * Informs TDLib on a file generation prograss
 *
 * @generationId - The identifier of the generation process
 * @expectedSize - Expected size of the generated file, in bytes
 *                 0 if unknown
 * @localPrefixSize - The number of bytes already generated
 */
suspend fun TdAbsHandler.setFileGenerationProgressOrNull(
    generationId: Long = 0L,
    expectedSize: Int = 0,
    localPrefixSize: Int = 0
) = syncOrNull<Ok>(
    SetFileGenerationProgress(
        generationId,
        expectedSize,
        localPrefixSize
    )
)

/**
 * Informs TDLib on a file generation prograss
 *
 * @generationId - The identifier of the generation process
 * @expectedSize - Expected size of the generated file, in bytes
 *                 0 if unknown
 * @localPrefixSize - The number of bytes already generated
 */
fun TdAbsHandler.setFileGenerationProgress(
    generationId: Long = 0L,
    expectedSize: Int = 0,
    localPrefixSize: Int = 0,
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
    generationId: Long = 0L,
    error: Error? = null
) = sync<Ok>(
    FinishFileGeneration(
        generationId,
        error
    )
)

/**
 * Finishes the file generation
 *
 * @generationId - The identifier of the generation process
 * @error - If set, means that file generation has failed and should be terminated
 */
suspend fun TdAbsHandler.finishFileGenerationOrNull(
    generationId: Long = 0L,
    error: Error? = null
) = syncOrNull<Ok>(
    FinishFileGeneration(
        generationId,
        error
    )
)

/**
 * Finishes the file generation
 *
 * @generationId - The identifier of the generation process
 * @error - If set, means that file generation has failed and should be terminated
 */
fun TdAbsHandler.finishFileGeneration(
    generationId: Long = 0L,
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
    fileId: Int = 0,
    offset: Int = 0,
    count: Int = 0
) = sync<FilePart>(
    ReadFilePart(
        fileId,
        offset,
        count
    )
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
suspend fun TdAbsHandler.readFilePartOrNull(
    fileId: Int = 0,
    offset: Int = 0,
    count: Int = 0
) = syncOrNull<FilePart>(
    ReadFilePart(
        fileId,
        offset,
        count
    )
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
fun TdAbsHandler.readFilePart(
    fileId: Int = 0,
    offset: Int = 0,
    count: Int = 0,
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
    fileId: Int = 0
) = sync<Ok>(
    DeleteFile(
        fileId
    )
)

/**
 * Deletes a file from the TDLib file cache
 *
 * @fileId - Identifier of the file to delete
 */
suspend fun TdAbsHandler.deleteFileOrNull(
    fileId: Int = 0
) = syncOrNull<Ok>(
    DeleteFile(
        fileId
    )
)

/**
 * Deletes a file from the TDLib file cache
 *
 * @fileId - Identifier of the file to delete
 */
fun TdAbsHandler.deleteFile(
    fileId: Int = 0,
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
    userId: Int = 0,
    pngSticker: InputFile? = null
) = sync<File>(
    UploadStickerFile(
        userId,
        pngSticker
    )
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
suspend fun TdAbsHandler.uploadStickerFileOrNull(
    userId: Int = 0,
    pngSticker: InputFile? = null
) = syncOrNull<File>(
    UploadStickerFile(
        userId,
        pngSticker
    )
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
fun TdAbsHandler.uploadStickerFile(
    userId: Int = 0,
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
    zoom: Int = 0,
    width: Int = 0,
    height: Int = 0,
    scale: Int = 0,
    chatId: Long = 0L
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
suspend fun TdAbsHandler.getMapThumbnailFileOrNull(
    location: Location? = null,
    zoom: Int = 0,
    width: Int = 0,
    height: Int = 0,
    scale: Int = 0,
    chatId: Long = 0L
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
fun TdAbsHandler.getMapThumbnailFile(
    location: Location? = null,
    zoom: Int = 0,
    width: Int = 0,
    height: Int = 0,
    scale: Int = 0,
    chatId: Long = 0L,
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
