@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Changes the database encryption key
 * Usually the encryption key is never changed and is stored in some OS keychain
 *
 * @newEncryptionKey - New encryption key
 */
suspend fun TdAbsHandler.setDatabaseEncryptionKey(
    newEncryptionKey: ByteArray
) = sync<Ok>(
    SetDatabaseEncryptionKey(
        newEncryptionKey
    )
)

suspend fun TdAbsHandler.setDatabaseEncryptionKeyOrNull(
    newEncryptionKey: ByteArray
) = syncOrNull<Ok>(
    SetDatabaseEncryptionKey(
        newEncryptionKey
    )
)

fun TdAbsHandler.setDatabaseEncryptionKey(
    newEncryptionKey: ByteArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetDatabaseEncryptionKey(
        newEncryptionKey
    ),block = block
)

/**
 * Returns database statistics
 */
suspend fun TdAbsHandler.getDatabaseStatistics() = sync<DatabaseStatistics>(
    GetDatabaseStatistics()
)

suspend fun TdAbsHandler.getDatabaseStatisticsOrNull() = syncOrNull<DatabaseStatistics>(
    GetDatabaseStatistics()
)

fun TdAbsHandler.getDatabaseStatistics(
    block: (suspend CoroutineScope.(DatabaseStatistics) -> Unit)
) = send(
    GetDatabaseStatistics(),block = block
)
