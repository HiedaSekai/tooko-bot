@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Changes the period of inactivity after which the account of the current user will automatically be deleted
 *
 * @ttl - New account TTL
 */
suspend fun TdAbsHandler.setAccountTtl(
    ttl: AccountTtl? = null
) = sync<Ok>(
    SetAccountTtl(
        ttl
    )
)

suspend fun TdAbsHandler.setAccountTtlOrNull(
    ttl: AccountTtl? = null
) = syncOrNull<Ok>(
    SetAccountTtl(
        ttl
    )
)

fun TdAbsHandler.setAccountTtl(
    ttl: AccountTtl? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetAccountTtl(
        ttl
    ),block = block
)

/**
 * Returns the period of inactivity after which the account of the current user will automatically be deleted
 */
suspend fun TdAbsHandler.getAccountTtl() = sync<AccountTtl>(
    GetAccountTtl()
)

suspend fun TdAbsHandler.getAccountTtlOrNull() = syncOrNull<AccountTtl>(
    GetAccountTtl()
)

fun TdAbsHandler.getAccountTtl(
    block: (suspend CoroutineScope.(AccountTtl) -> Unit)
) = send(
    GetAccountTtl(),block = block
)

/**
 * Deletes the account of the current user, deleting all information associated with the user from the server
 * The phone number of the account can be used to create a new account
 * Can be called before authorization when the current authorization state is authorizationStateWaitPassword
 *
 * @reason - The reason why the account was deleted
 */
suspend fun TdAbsHandler.deleteAccount(
    reason: String? = null
) = sync<Ok>(
    DeleteAccount(
        reason
    )
)

suspend fun TdAbsHandler.deleteAccountOrNull(
    reason: String? = null
) = syncOrNull<Ok>(
    DeleteAccount(
        reason
    )
)

fun TdAbsHandler.deleteAccount(
    reason: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteAccount(
        reason
    ),block = block
)
