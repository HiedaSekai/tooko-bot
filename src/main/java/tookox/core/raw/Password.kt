@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns the current state of 2-step verification
 */
suspend fun TdAbsHandler.getPasswordState() = sync<PasswordState>(
    GetPasswordState()
)

/**
 * Returns the current state of 2-step verification
 */
suspend fun TdAbsHandler.getPasswordStateOrNull() = syncOrNull<PasswordState>(
    GetPasswordState()
)

/**
 * Returns the current state of 2-step verification
 */
fun TdAbsHandler.getPasswordState(
    block: (suspend CoroutineScope.(PasswordState) -> Unit)
) = send(
    GetPasswordState(),block = block
)

/**
 * Changes the password for the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 *
 * @oldPassword - Previous password of the user
 * @newPassword - New password of the user
 *                May be empty to remove the password
 * @newHint - New password hint
 * @setRecoveryEmailAddress - Pass true if the recovery email address should be changed
 * @newRecoveryEmailAddress - New recovery email address
 */
suspend fun TdAbsHandler.setPassword(
    oldPassword: String? = null,
    newPassword: String? = null,
    newHint: String? = null,
    setRecoveryEmailAddress: Boolean = false,
    newRecoveryEmailAddress: String? = null
) = sync<PasswordState>(
    SetPassword(
        oldPassword,
        newPassword,
        newHint,
        setRecoveryEmailAddress,
        newRecoveryEmailAddress
    )
)

/**
 * Changes the password for the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 *
 * @oldPassword - Previous password of the user
 * @newPassword - New password of the user
 *                May be empty to remove the password
 * @newHint - New password hint
 * @setRecoveryEmailAddress - Pass true if the recovery email address should be changed
 * @newRecoveryEmailAddress - New recovery email address
 */
suspend fun TdAbsHandler.setPasswordOrNull(
    oldPassword: String? = null,
    newPassword: String? = null,
    newHint: String? = null,
    setRecoveryEmailAddress: Boolean = false,
    newRecoveryEmailAddress: String? = null
) = syncOrNull<PasswordState>(
    SetPassword(
        oldPassword,
        newPassword,
        newHint,
        setRecoveryEmailAddress,
        newRecoveryEmailAddress
    )
)

/**
 * Changes the password for the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 *
 * @oldPassword - Previous password of the user
 * @newPassword - New password of the user
 *                May be empty to remove the password
 * @newHint - New password hint
 * @setRecoveryEmailAddress - Pass true if the recovery email address should be changed
 * @newRecoveryEmailAddress - New recovery email address
 */
fun TdAbsHandler.setPassword(
    oldPassword: String? = null,
    newPassword: String? = null,
    newHint: String? = null,
    setRecoveryEmailAddress: Boolean = false,
    newRecoveryEmailAddress: String? = null,
    block: (suspend CoroutineScope.(PasswordState) -> Unit)
) = send(
    SetPassword(
        oldPassword,
        newPassword,
        newHint,
        setRecoveryEmailAddress,
        newRecoveryEmailAddress
    ),block = block
)

/**
 * Changes the 2-step verification recovery email address of the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 * If new_recovery_email_address is the same as the email address that is currently set up, this call succeeds immediately and aborts all other requests waiting for an email confirmation
 *
 * @password - Password of the current user
 * @newRecoveryEmailAddress - New recovery email address
 */
suspend fun TdAbsHandler.setRecoveryEmailAddress(
    password: String? = null,
    newRecoveryEmailAddress: String? = null
) = sync<PasswordState>(
    SetRecoveryEmailAddress(
        password,
        newRecoveryEmailAddress
    )
)

/**
 * Changes the 2-step verification recovery email address of the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 * If new_recovery_email_address is the same as the email address that is currently set up, this call succeeds immediately and aborts all other requests waiting for an email confirmation
 *
 * @password - Password of the current user
 * @newRecoveryEmailAddress - New recovery email address
 */
suspend fun TdAbsHandler.setRecoveryEmailAddressOrNull(
    password: String? = null,
    newRecoveryEmailAddress: String? = null
) = syncOrNull<PasswordState>(
    SetRecoveryEmailAddress(
        password,
        newRecoveryEmailAddress
    )
)

/**
 * Changes the 2-step verification recovery email address of the user
 * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
 * If new_recovery_email_address is the same as the email address that is currently set up, this call succeeds immediately and aborts all other requests waiting for an email confirmation
 *
 * @password - Password of the current user
 * @newRecoveryEmailAddress - New recovery email address
 */
fun TdAbsHandler.setRecoveryEmailAddress(
    password: String? = null,
    newRecoveryEmailAddress: String? = null,
    block: (suspend CoroutineScope.(PasswordState) -> Unit)
) = send(
    SetRecoveryEmailAddress(
        password,
        newRecoveryEmailAddress
    ),block = block
)

/**
 * Checks the 2-step verification recovery email address verification code
 *
 * @code - Verification code
 */
suspend fun TdAbsHandler.checkRecoveryEmailAddressCode(
    code: String? = null
) = sync<PasswordState>(
    CheckRecoveryEmailAddressCode(
        code
    )
)

/**
 * Checks the 2-step verification recovery email address verification code
 *
 * @code - Verification code
 */
suspend fun TdAbsHandler.checkRecoveryEmailAddressCodeOrNull(
    code: String? = null
) = syncOrNull<PasswordState>(
    CheckRecoveryEmailAddressCode(
        code
    )
)

/**
 * Checks the 2-step verification recovery email address verification code
 *
 * @code - Verification code
 */
fun TdAbsHandler.checkRecoveryEmailAddressCode(
    code: String? = null,
    block: (suspend CoroutineScope.(PasswordState) -> Unit)
) = send(
    CheckRecoveryEmailAddressCode(
        code
    ),block = block
)

/**
 * Resends the 2-step verification recovery email address verification code
 */
suspend fun TdAbsHandler.resendRecoveryEmailAddressCode() = sync<PasswordState>(
    ResendRecoveryEmailAddressCode()
)

/**
 * Resends the 2-step verification recovery email address verification code
 */
suspend fun TdAbsHandler.resendRecoveryEmailAddressCodeOrNull() = syncOrNull<PasswordState>(
    ResendRecoveryEmailAddressCode()
)

/**
 * Resends the 2-step verification recovery email address verification code
 */
fun TdAbsHandler.resendRecoveryEmailAddressCode(
    block: (suspend CoroutineScope.(PasswordState) -> Unit)
) = send(
    ResendRecoveryEmailAddressCode(),block = block
)

/**
 * Recovers the password using a recovery code sent to an email address that was previously set up
 *
 * @recoveryCode - Recovery code to check
 */
suspend fun TdAbsHandler.recoverPassword(
    recoveryCode: String? = null
) = sync<PasswordState>(
    RecoverPassword(
        recoveryCode
    )
)

/**
 * Recovers the password using a recovery code sent to an email address that was previously set up
 *
 * @recoveryCode - Recovery code to check
 */
suspend fun TdAbsHandler.recoverPasswordOrNull(
    recoveryCode: String? = null
) = syncOrNull<PasswordState>(
    RecoverPassword(
        recoveryCode
    )
)

/**
 * Recovers the password using a recovery code sent to an email address that was previously set up
 *
 * @recoveryCode - Recovery code to check
 */
fun TdAbsHandler.recoverPassword(
    recoveryCode: String? = null,
    block: (suspend CoroutineScope.(PasswordState) -> Unit)
) = send(
    RecoverPassword(
        recoveryCode
    ),block = block
)

/**
 * Creates a new temporary password for processing payments
 *
 * @password - Persistent user password
 * @validFor - Time during which the temporary password will be valid, in seconds
 *             Should be between 60 and 86400
 */
suspend fun TdAbsHandler.createTemporaryPassword(
    password: String? = null,
    validFor: Int = 0
) = sync<TemporaryPasswordState>(
    CreateTemporaryPassword(
        password,
        validFor
    )
)

/**
 * Creates a new temporary password for processing payments
 *
 * @password - Persistent user password
 * @validFor - Time during which the temporary password will be valid, in seconds
 *             Should be between 60 and 86400
 */
suspend fun TdAbsHandler.createTemporaryPasswordOrNull(
    password: String? = null,
    validFor: Int = 0
) = syncOrNull<TemporaryPasswordState>(
    CreateTemporaryPassword(
        password,
        validFor
    )
)

/**
 * Creates a new temporary password for processing payments
 *
 * @password - Persistent user password
 * @validFor - Time during which the temporary password will be valid, in seconds
 *             Should be between 60 and 86400
 */
fun TdAbsHandler.createTemporaryPassword(
    password: String? = null,
    validFor: Int = 0,
    block: (suspend CoroutineScope.(TemporaryPasswordState) -> Unit)
) = send(
    CreateTemporaryPassword(
        password,
        validFor
    ),block = block
)

/**
 * Returns information about the current temporary password
 */
suspend fun TdAbsHandler.getTemporaryPasswordState() = sync<TemporaryPasswordState>(
    GetTemporaryPasswordState()
)

/**
 * Returns information about the current temporary password
 */
suspend fun TdAbsHandler.getTemporaryPasswordStateOrNull() = syncOrNull<TemporaryPasswordState>(
    GetTemporaryPasswordState()
)

/**
 * Returns information about the current temporary password
 */
fun TdAbsHandler.getTemporaryPasswordState(
    block: (suspend CoroutineScope.(TemporaryPasswordState) -> Unit)
) = send(
    GetTemporaryPasswordState(),block = block
)
