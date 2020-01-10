@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Uploads a new profile photo for the current user
 * If something changes, updateUser will be sent
 *
 * @photo - Profile photo to set
 *          InputFileId and inputFileRemote may still be unsupported
 */
suspend fun TdAbsHandler.setProfilePhoto(
    photo: InputFile? = null
) = sync<Ok>(
    SetProfilePhoto(
        photo
    )
)

/**
 * Uploads a new profile photo for the current user
 * If something changes, updateUser will be sent
 *
 * @photo - Profile photo to set
 *          InputFileId and inputFileRemote may still be unsupported
 */
suspend fun TdAbsHandler.setProfilePhotoOrNull(
    photo: InputFile? = null
) = syncOrNull<Ok>(
    SetProfilePhoto(
        photo
    )
)

/**
 * Uploads a new profile photo for the current user
 * If something changes, updateUser will be sent
 *
 * @photo - Profile photo to set
 *          InputFileId and inputFileRemote may still be unsupported
 */
fun TdAbsHandler.setProfilePhoto(
    photo: InputFile? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetProfilePhoto(
        photo
    ),block = block
)

/**
 * Deletes a profile photo
 * If something changes, updateUser will be sent
 *
 * @profilePhotoId - Identifier of the profile photo to delete
 */
suspend fun TdAbsHandler.deleteProfilePhoto(
    profilePhotoId: Long = 0L
) = sync<Ok>(
    DeleteProfilePhoto(
        profilePhotoId
    )
)

/**
 * Deletes a profile photo
 * If something changes, updateUser will be sent
 *
 * @profilePhotoId - Identifier of the profile photo to delete
 */
suspend fun TdAbsHandler.deleteProfilePhotoOrNull(
    profilePhotoId: Long = 0L
) = syncOrNull<Ok>(
    DeleteProfilePhoto(
        profilePhotoId
    )
)

/**
 * Deletes a profile photo
 * If something changes, updateUser will be sent
 *
 * @profilePhotoId - Identifier of the profile photo to delete
 */
fun TdAbsHandler.deleteProfilePhoto(
    profilePhotoId: Long = 0L,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteProfilePhoto(
        profilePhotoId
    ),block = block
)
