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

suspend fun TdAbsHandler.setProfilePhotoOrNull(
    photo: InputFile? = null
) = syncOrNull<Ok>(
    SetProfilePhoto(
        photo
    )
)

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
    profilePhotoId: Long
) = sync<Ok>(
    DeleteProfilePhoto(
        profilePhotoId
    )
)

suspend fun TdAbsHandler.deleteProfilePhotoOrNull(
    profilePhotoId: Long
) = syncOrNull<Ok>(
    DeleteProfilePhoto(
        profilePhotoId
    )
)

fun TdAbsHandler.deleteProfilePhoto(
    profilePhotoId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteProfilePhoto(
        profilePhotoId
    ),block = block
)
