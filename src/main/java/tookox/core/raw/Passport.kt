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
 * Returns one of the available Telegram Passport elements
 *
 * @type - Telegram Passport element type
 * @password - Password of the current user
 */
suspend fun TdAbsHandler.getPassportElement(
    type: PassportElementType? = null,
    password: String? = null
) = sync<PassportElement>(
    GetPassportElement(
        type,
        password
    )
)

suspend fun TdAbsHandler.getPassportElementOrNull(
    type: PassportElementType? = null,
    password: String? = null
) = syncOrNull<PassportElement>(
    GetPassportElement(
        type,
        password
    )
)

fun TdAbsHandler.getPassportElement(
    type: PassportElementType? = null,
    password: String? = null,
    block: (suspend CoroutineScope.(PassportElement) -> Unit)
) = send(
    GetPassportElement(
        type,
        password
    ),block = block
)

/**
 * Returns all available Telegram Passport elements
 *
 * @password - Password of the current user
 */
suspend fun TdAbsHandler.getAllPassportElements(
    password: String? = null
) = sync<PassportElements>(
    GetAllPassportElements(
        password
    )
)

suspend fun TdAbsHandler.getAllPassportElementsOrNull(
    password: String? = null
) = syncOrNull<PassportElements>(
    GetAllPassportElements(
        password
    )
)

fun TdAbsHandler.getAllPassportElements(
    password: String? = null,
    block: (suspend CoroutineScope.(PassportElements) -> Unit)
) = send(
    GetAllPassportElements(
        password
    ),block = block
)

/**
 * Adds an element to the user's Telegram Passport
 * May return an error with a message "PHONE_VERIFICATION_NEEDED" or "EMAIL_VERIFICATION_NEEDED" if the chosen phone number or the chosen email address must be verified first
 *
 * @element - Input Telegram Passport element
 * @password - Password of the current user
 */
suspend fun TdAbsHandler.setPassportElement(
    element: InputPassportElement? = null,
    password: String? = null
) = sync<PassportElement>(
    SetPassportElement(
        element,
        password
    )
)

suspend fun TdAbsHandler.setPassportElementOrNull(
    element: InputPassportElement? = null,
    password: String? = null
) = syncOrNull<PassportElement>(
    SetPassportElement(
        element,
        password
    )
)

fun TdAbsHandler.setPassportElement(
    element: InputPassportElement? = null,
    password: String? = null,
    block: (suspend CoroutineScope.(PassportElement) -> Unit)
) = send(
    SetPassportElement(
        element,
        password
    ),block = block
)

/**
 * Deletes a Telegram Passport element
 *
 * @type - Element type
 */
suspend fun TdAbsHandler.deletePassportElement(
    type: PassportElementType? = null
) = sync<Ok>(
    DeletePassportElement(
        type
    )
)

suspend fun TdAbsHandler.deletePassportElementOrNull(
    type: PassportElementType? = null
) = syncOrNull<Ok>(
    DeletePassportElement(
        type
    )
)

fun TdAbsHandler.deletePassportElement(
    type: PassportElementType? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeletePassportElement(
        type
    ),block = block
)

/**
 * Informs the user that some of the elements in their Telegram Passport contain errors
 * For bots only
 * The user will not be able to resend the elements, until the errors are fixed
 *
 * @userId - User identifier
 * @errors - The errors
 */
suspend fun TdAbsHandler.setPassportElementErrors(
    userId: Int,
    errors: Array<InputPassportElementError>
) = sync<Ok>(
    SetPassportElementErrors(
        userId,
        errors
    )
)

suspend fun TdAbsHandler.setPassportElementErrorsOrNull(
    userId: Int,
    errors: Array<InputPassportElementError>
) = syncOrNull<Ok>(
    SetPassportElementErrors(
        userId,
        errors
    )
)

fun TdAbsHandler.setPassportElementErrors(
    userId: Int,
    errors: Array<InputPassportElementError>,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetPassportElementErrors(
        userId,
        errors
    ),block = block
)

/**
 * Returns a Telegram Passport authorization form for sharing data with a service
 *
 * @botUserId - User identifier of the service's bot
 * @scope - Telegram Passport element types requested by the service
 * @publicKey - Service's public_key
 * @nonce - Authorization form nonce provided by the service
 */
suspend fun TdAbsHandler.getPassportAuthorizationForm(
    botUserId: Int,
    scope: String? = null,
    publicKey: String? = null,
    nonce: String? = null
) = sync<PassportAuthorizationForm>(
    GetPassportAuthorizationForm(
        botUserId,
        scope,
        publicKey,
        nonce
    )
)

suspend fun TdAbsHandler.getPassportAuthorizationFormOrNull(
    botUserId: Int,
    scope: String? = null,
    publicKey: String? = null,
    nonce: String? = null
) = syncOrNull<PassportAuthorizationForm>(
    GetPassportAuthorizationForm(
        botUserId,
        scope,
        publicKey,
        nonce
    )
)

fun TdAbsHandler.getPassportAuthorizationForm(
    botUserId: Int,
    scope: String? = null,
    publicKey: String? = null,
    nonce: String? = null,
    block: (suspend CoroutineScope.(PassportAuthorizationForm) -> Unit)
) = send(
    GetPassportAuthorizationForm(
        botUserId,
        scope,
        publicKey,
        nonce
    ),block = block
)

/**
 * Returns already available Telegram Passport elements suitable for completing a Telegram Passport authorization form
 * Result can be received only once for each authorization form
 *
 * @autorizationFormId - Authorization form identifier
 * @password - Password of the current user
 */
suspend fun TdAbsHandler.getPassportAuthorizationFormAvailableElements(
    autorizationFormId: Int,
    password: String? = null
) = sync<PassportElementsWithErrors>(
    GetPassportAuthorizationFormAvailableElements(
        autorizationFormId,
        password
    )
)

suspend fun TdAbsHandler.getPassportAuthorizationFormAvailableElementsOrNull(
    autorizationFormId: Int,
    password: String? = null
) = syncOrNull<PassportElementsWithErrors>(
    GetPassportAuthorizationFormAvailableElements(
        autorizationFormId,
        password
    )
)

fun TdAbsHandler.getPassportAuthorizationFormAvailableElements(
    autorizationFormId: Int,
    password: String? = null,
    block: (suspend CoroutineScope.(PassportElementsWithErrors) -> Unit)
) = send(
    GetPassportAuthorizationFormAvailableElements(
        autorizationFormId,
        password
    ),block = block
)
