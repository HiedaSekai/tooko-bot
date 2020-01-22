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
 * Returns stickers from the installed sticker sets that correspond to a given emoji
 * If the emoji is not empty, favorite and recently used stickers may also be returned
 *
 * @emoji - String representation of emoji
 *          If empty, returns all known installed stickers
 * @limit - The maximum number of stickers to be returned
 */
suspend fun TdAbsHandler.getStickers(
    emoji: String? = null,
    limit: Int
) = sync<Stickers>(
    GetStickers(
        emoji,
        limit
    )
)

suspend fun TdAbsHandler.getStickersOrNull(
    emoji: String? = null,
    limit: Int
) = syncOrNull<Stickers>(
    GetStickers(
        emoji,
        limit
    )
)

fun TdAbsHandler.getStickers(
    emoji: String? = null,
    limit: Int,
    block: (suspend CoroutineScope.(Stickers) -> Unit)
) = send(
    GetStickers(
        emoji,
        limit
    ),block = block
)

/**
 * Searches for stickers from public sticker sets that correspond to a given emoji
 *
 * @emoji - String representation of emoji
 * @limit - The maximum number of stickers to be returned
 */
suspend fun TdAbsHandler.searchStickers(
    emoji: String? = null,
    limit: Int
) = sync<Stickers>(
    SearchStickers(
        emoji,
        limit
    )
)

suspend fun TdAbsHandler.searchStickersOrNull(
    emoji: String? = null,
    limit: Int
) = syncOrNull<Stickers>(
    SearchStickers(
        emoji,
        limit
    )
)

fun TdAbsHandler.searchStickers(
    emoji: String? = null,
    limit: Int,
    block: (suspend CoroutineScope.(Stickers) -> Unit)
) = send(
    SearchStickers(
        emoji,
        limit
    ),block = block
)

/**
 * Returns a list of installed sticker sets
 *
 * @isMasks - Pass true to return mask sticker sets
 *            Pass false to return ordinary sticker sets
 */
suspend fun TdAbsHandler.getInstalledStickerSets(
    isMasks: Boolean
) = sync<StickerSets>(
    GetInstalledStickerSets(
        isMasks
    )
)

suspend fun TdAbsHandler.getInstalledStickerSetsOrNull(
    isMasks: Boolean
) = syncOrNull<StickerSets>(
    GetInstalledStickerSets(
        isMasks
    )
)

fun TdAbsHandler.getInstalledStickerSets(
    isMasks: Boolean,
    block: (suspend CoroutineScope.(StickerSets) -> Unit)
) = send(
    GetInstalledStickerSets(
        isMasks
    ),block = block
)

/**
 * Returns a list of archived sticker sets
 *
 * @isMasks - Pass true to return mask stickers sets
 *            Pass false to return ordinary sticker sets
 * @offsetStickerSetId - Identifier of the sticker set from which to return the result
 * @limit - The maximum number of sticker sets to return
 */
suspend fun TdAbsHandler.getArchivedStickerSets(
    isMasks: Boolean,
    offsetStickerSetId: Long,
    limit: Int
) = sync<StickerSets>(
    GetArchivedStickerSets(
        isMasks,
        offsetStickerSetId,
        limit
    )
)

suspend fun TdAbsHandler.getArchivedStickerSetsOrNull(
    isMasks: Boolean,
    offsetStickerSetId: Long,
    limit: Int
) = syncOrNull<StickerSets>(
    GetArchivedStickerSets(
        isMasks,
        offsetStickerSetId,
        limit
    )
)

fun TdAbsHandler.getArchivedStickerSets(
    isMasks: Boolean,
    offsetStickerSetId: Long,
    limit: Int,
    block: (suspend CoroutineScope.(StickerSets) -> Unit)
) = send(
    GetArchivedStickerSets(
        isMasks,
        offsetStickerSetId,
        limit
    ),block = block
)

/**
 * Returns a list of trending sticker sets
 */
suspend fun TdAbsHandler.getTrendingStickerSets() = sync<StickerSets>(
    GetTrendingStickerSets()
)

suspend fun TdAbsHandler.getTrendingStickerSetsOrNull() = syncOrNull<StickerSets>(
    GetTrendingStickerSets()
)

fun TdAbsHandler.getTrendingStickerSets(
    block: (suspend CoroutineScope.(StickerSets) -> Unit)
) = send(
    GetTrendingStickerSets(),block = block
)

/**
 * Returns a list of sticker sets attached to a file
 * Currently only photos and videos can have attached sticker sets
 *
 * @fileId - File identifier
 */
suspend fun TdAbsHandler.getAttachedStickerSets(
    fileId: Int
) = sync<StickerSets>(
    GetAttachedStickerSets(
        fileId
    )
)

suspend fun TdAbsHandler.getAttachedStickerSetsOrNull(
    fileId: Int
) = syncOrNull<StickerSets>(
    GetAttachedStickerSets(
        fileId
    )
)

fun TdAbsHandler.getAttachedStickerSets(
    fileId: Int,
    block: (suspend CoroutineScope.(StickerSets) -> Unit)
) = send(
    GetAttachedStickerSets(
        fileId
    ),block = block
)

/**
 * Returns information about a sticker set by its identifier
 *
 * @setId - Identifier of the sticker set
 */
suspend fun TdAbsHandler.getStickerSet(
    setId: Long
) = sync<StickerSet>(
    GetStickerSet(
        setId
    )
)

suspend fun TdAbsHandler.getStickerSetOrNull(
    setId: Long
) = syncOrNull<StickerSet>(
    GetStickerSet(
        setId
    )
)

fun TdAbsHandler.getStickerSet(
    setId: Long,
    block: (suspend CoroutineScope.(StickerSet) -> Unit)
) = send(
    GetStickerSet(
        setId
    ),block = block
)

/**
 * Searches for a sticker set by its name
 *
 * @name - Name of the sticker set
 */
suspend fun TdAbsHandler.searchStickerSet(
    name: String? = null
) = sync<StickerSet>(
    SearchStickerSet(
        name
    )
)

suspend fun TdAbsHandler.searchStickerSetOrNull(
    name: String? = null
) = syncOrNull<StickerSet>(
    SearchStickerSet(
        name
    )
)

fun TdAbsHandler.searchStickerSet(
    name: String? = null,
    block: (suspend CoroutineScope.(StickerSet) -> Unit)
) = send(
    SearchStickerSet(
        name
    ),block = block
)

/**
 * Searches for installed sticker sets by looking for specified query in their title and name
 *
 * @isMasks - Pass true to return mask sticker sets
 *            Pass false to return ordinary sticker sets
 * @query - Query to search for
 * @limit - The maximum number of sticker sets to return
 */
suspend fun TdAbsHandler.searchInstalledStickerSets(
    isMasks: Boolean,
    query: String? = null,
    limit: Int
) = sync<StickerSets>(
    SearchInstalledStickerSets(
        isMasks,
        query,
        limit
    )
)

suspend fun TdAbsHandler.searchInstalledStickerSetsOrNull(
    isMasks: Boolean,
    query: String? = null,
    limit: Int
) = syncOrNull<StickerSets>(
    SearchInstalledStickerSets(
        isMasks,
        query,
        limit
    )
)

fun TdAbsHandler.searchInstalledStickerSets(
    isMasks: Boolean,
    query: String? = null,
    limit: Int,
    block: (suspend CoroutineScope.(StickerSets) -> Unit)
) = send(
    SearchInstalledStickerSets(
        isMasks,
        query,
        limit
    ),block = block
)

/**
 * Searches for ordinary sticker sets by looking for specified query in their title and name
 * Excludes installed sticker sets from the results
 *
 * @query - Query to search for
 */
suspend fun TdAbsHandler.searchStickerSets(
    query: String? = null
) = sync<StickerSets>(
    SearchStickerSets(
        query
    )
)

suspend fun TdAbsHandler.searchStickerSetsOrNull(
    query: String? = null
) = syncOrNull<StickerSets>(
    SearchStickerSets(
        query
    )
)

fun TdAbsHandler.searchStickerSets(
    query: String? = null,
    block: (suspend CoroutineScope.(StickerSets) -> Unit)
) = send(
    SearchStickerSets(
        query
    ),block = block
)

/**
 * Installs/uninstalls or activates/archives a sticker set
 *
 * @setId - Identifier of the sticker set
 * @isInstalled - The new value of is_installed
 * @isArchived - The new value of is_archived
 *               A sticker set can't be installed and archived simultaneously
 */
suspend fun TdAbsHandler.changeStickerSet(
    setId: Long,
    isInstalled: Boolean,
    isArchived: Boolean
) = sync<Ok>(
    ChangeStickerSet(
        setId,
        isInstalled,
        isArchived
    )
)

suspend fun TdAbsHandler.changeStickerSetOrNull(
    setId: Long,
    isInstalled: Boolean,
    isArchived: Boolean
) = syncOrNull<Ok>(
    ChangeStickerSet(
        setId,
        isInstalled,
        isArchived
    )
)

fun TdAbsHandler.changeStickerSet(
    setId: Long,
    isInstalled: Boolean,
    isArchived: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ChangeStickerSet(
        setId,
        isInstalled,
        isArchived
    ),block = block
)

/**
 * Informs the server that some trending sticker sets have been viewed by the user
 *
 * @stickerSetIds - Identifiers of viewed trending sticker sets
 */
suspend fun TdAbsHandler.viewTrendingStickerSets(
    stickerSetIds: LongArray
) = sync<Ok>(
    ViewTrendingStickerSets(
        stickerSetIds
    )
)

suspend fun TdAbsHandler.viewTrendingStickerSetsOrNull(
    stickerSetIds: LongArray
) = syncOrNull<Ok>(
    ViewTrendingStickerSets(
        stickerSetIds
    )
)

fun TdAbsHandler.viewTrendingStickerSets(
    stickerSetIds: LongArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ViewTrendingStickerSets(
        stickerSetIds
    ),block = block
)

/**
 * Changes the order of installed sticker sets
 *
 * @isMasks - Pass true to change the order of mask sticker sets
 *            Pass false to change the order of ordinary sticker sets
 * @stickerSetIds - Identifiers of installed sticker sets in the new correct order
 */
suspend fun TdAbsHandler.reorderInstalledStickerSets(
    isMasks: Boolean,
    stickerSetIds: LongArray
) = sync<Ok>(
    ReorderInstalledStickerSets(
        isMasks,
        stickerSetIds
    )
)

suspend fun TdAbsHandler.reorderInstalledStickerSetsOrNull(
    isMasks: Boolean,
    stickerSetIds: LongArray
) = syncOrNull<Ok>(
    ReorderInstalledStickerSets(
        isMasks,
        stickerSetIds
    )
)

fun TdAbsHandler.reorderInstalledStickerSets(
    isMasks: Boolean,
    stickerSetIds: LongArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ReorderInstalledStickerSets(
        isMasks,
        stickerSetIds
    ),block = block
)

/**
 * Returns a list of recently used stickers
 *
 * @isAttached - Pass true to return stickers and masks that were recently attached to photos or video files
 *               Pass false to return recently sent stickers
 */
suspend fun TdAbsHandler.getRecentStickers(
    isAttached: Boolean
) = sync<Stickers>(
    GetRecentStickers(
        isAttached
    )
)

suspend fun TdAbsHandler.getRecentStickersOrNull(
    isAttached: Boolean
) = syncOrNull<Stickers>(
    GetRecentStickers(
        isAttached
    )
)

fun TdAbsHandler.getRecentStickers(
    isAttached: Boolean,
    block: (suspend CoroutineScope.(Stickers) -> Unit)
) = send(
    GetRecentStickers(
        isAttached
    ),block = block
)

/**
 * Manually adds a new sticker to the list of recently used stickers
 * The new sticker is added to the top of the list
 * If the sticker was already in the list, it is removed from the list first
 * Only stickers belonging to a sticker set can be added to this list
 *
 * @isAttached - Pass true to add the sticker to the list of stickers recently attached to photo or video files
 *               Pass false to add the sticker to the list of recently sent stickers
 * @sticker - Sticker file to add
 */
suspend fun TdAbsHandler.addRecentSticker(
    isAttached: Boolean,
    sticker: InputFile? = null
) = sync<Stickers>(
    AddRecentSticker(
        isAttached,
        sticker
    )
)

suspend fun TdAbsHandler.addRecentStickerOrNull(
    isAttached: Boolean,
    sticker: InputFile? = null
) = syncOrNull<Stickers>(
    AddRecentSticker(
        isAttached,
        sticker
    )
)

fun TdAbsHandler.addRecentSticker(
    isAttached: Boolean,
    sticker: InputFile? = null,
    block: (suspend CoroutineScope.(Stickers) -> Unit)
) = send(
    AddRecentSticker(
        isAttached,
        sticker
    ),block = block
)

/**
 * Removes a sticker from the list of recently used stickers
 *
 * @isAttached - Pass true to remove the sticker from the list of stickers recently attached to photo or video files
 *               Pass false to remove the sticker from the list of recently sent stickers
 * @sticker - Sticker file to delete
 */
suspend fun TdAbsHandler.removeRecentSticker(
    isAttached: Boolean,
    sticker: InputFile? = null
) = sync<Ok>(
    RemoveRecentSticker(
        isAttached,
        sticker
    )
)

suspend fun TdAbsHandler.removeRecentStickerOrNull(
    isAttached: Boolean,
    sticker: InputFile? = null
) = syncOrNull<Ok>(
    RemoveRecentSticker(
        isAttached,
        sticker
    )
)

fun TdAbsHandler.removeRecentSticker(
    isAttached: Boolean,
    sticker: InputFile? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveRecentSticker(
        isAttached,
        sticker
    ),block = block
)

/**
 * Clears the list of recently used stickers
 *
 * @isAttached - Pass true to clear the list of stickers recently attached to photo or video files
 *               Pass false to clear the list of recently sent stickers
 */
suspend fun TdAbsHandler.clearRecentStickers(
    isAttached: Boolean
) = sync<Ok>(
    ClearRecentStickers(
        isAttached
    )
)

suspend fun TdAbsHandler.clearRecentStickersOrNull(
    isAttached: Boolean
) = syncOrNull<Ok>(
    ClearRecentStickers(
        isAttached
    )
)

fun TdAbsHandler.clearRecentStickers(
    isAttached: Boolean,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ClearRecentStickers(
        isAttached
    ),block = block
)

/**
 * Returns favorite stickers
 */
suspend fun TdAbsHandler.getFavoriteStickers() = sync<Stickers>(
    GetFavoriteStickers()
)

suspend fun TdAbsHandler.getFavoriteStickersOrNull() = syncOrNull<Stickers>(
    GetFavoriteStickers()
)

fun TdAbsHandler.getFavoriteStickers(
    block: (suspend CoroutineScope.(Stickers) -> Unit)
) = send(
    GetFavoriteStickers(),block = block
)

/**
 * Adds a new sticker to the list of favorite stickers
 * The new sticker is added to the top of the list
 * If the sticker was already in the list, it is removed from the list first
 * Only stickers belonging to a sticker set can be added to this list
 *
 * @sticker - Sticker file to add
 */
suspend fun TdAbsHandler.addFavoriteSticker(
    sticker: InputFile? = null
) = sync<Ok>(
    AddFavoriteSticker(
        sticker
    )
)

suspend fun TdAbsHandler.addFavoriteStickerOrNull(
    sticker: InputFile? = null
) = syncOrNull<Ok>(
    AddFavoriteSticker(
        sticker
    )
)

fun TdAbsHandler.addFavoriteSticker(
    sticker: InputFile? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddFavoriteSticker(
        sticker
    ),block = block
)

/**
 * Removes a sticker from the list of favorite stickers
 *
 * @sticker - Sticker file to delete from the list
 */
suspend fun TdAbsHandler.removeFavoriteSticker(
    sticker: InputFile? = null
) = sync<Ok>(
    RemoveFavoriteSticker(
        sticker
    )
)

suspend fun TdAbsHandler.removeFavoriteStickerOrNull(
    sticker: InputFile? = null
) = syncOrNull<Ok>(
    RemoveFavoriteSticker(
        sticker
    )
)

fun TdAbsHandler.removeFavoriteSticker(
    sticker: InputFile? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveFavoriteSticker(
        sticker
    ),block = block
)

/**
 * Creates a new sticker set
 * For bots only
 * Returns the newly created sticker set
 *
 * @userId - Sticker set owner
 * @title - Sticker set title
 * @name - Sticker set name
 *         Can contain only English letters, digits and underscores
 *         Must end with *"_by_<bot username>"* (*<bot_username>* is case insensitive)
 * @isMasks - True, if stickers are masks
 * @stickers - List of stickers to be added to the set
 */
suspend fun TdAbsHandler.createNewStickerSet(
    userId: Int,
    title: String? = null,
    name: String? = null,
    isMasks: Boolean,
    stickers: Array<InputSticker>
) = sync<StickerSet>(
    CreateNewStickerSet(
        userId,
        title,
        name,
        isMasks,
        stickers
    )
)

suspend fun TdAbsHandler.createNewStickerSetOrNull(
    userId: Int,
    title: String? = null,
    name: String? = null,
    isMasks: Boolean,
    stickers: Array<InputSticker>
) = syncOrNull<StickerSet>(
    CreateNewStickerSet(
        userId,
        title,
        name,
        isMasks,
        stickers
    )
)

fun TdAbsHandler.createNewStickerSet(
    userId: Int,
    title: String? = null,
    name: String? = null,
    isMasks: Boolean,
    stickers: Array<InputSticker>,
    block: (suspend CoroutineScope.(StickerSet) -> Unit)
) = send(
    CreateNewStickerSet(
        userId,
        title,
        name,
        isMasks,
        stickers
    ),block = block
)

/**
 * Adds a new sticker to a set
 * For bots only
 * Returns the sticker set
 *
 * @userId - Sticker set owner
 * @name - Sticker set name
 * @sticker - Sticker to add to the set
 */
suspend fun TdAbsHandler.addStickerToSet(
    userId: Int,
    name: String? = null,
    sticker: InputSticker? = null
) = sync<StickerSet>(
    AddStickerToSet(
        userId,
        name,
        sticker
    )
)

suspend fun TdAbsHandler.addStickerToSetOrNull(
    userId: Int,
    name: String? = null,
    sticker: InputSticker? = null
) = syncOrNull<StickerSet>(
    AddStickerToSet(
        userId,
        name,
        sticker
    )
)

fun TdAbsHandler.addStickerToSet(
    userId: Int,
    name: String? = null,
    sticker: InputSticker? = null,
    block: (suspend CoroutineScope.(StickerSet) -> Unit)
) = send(
    AddStickerToSet(
        userId,
        name,
        sticker
    ),block = block
)

/**
 * Changes the position of a sticker in the set to which it belongs
 * For bots only
 * The sticker set must have been created by the bot
 *
 * @sticker - Sticker
 * @position - New position of the sticker in the set, zero-based
 */
suspend fun TdAbsHandler.setStickerPositionInSet(
    sticker: InputFile? = null,
    position: Int
) = sync<Ok>(
    SetStickerPositionInSet(
        sticker,
        position
    )
)

suspend fun TdAbsHandler.setStickerPositionInSetOrNull(
    sticker: InputFile? = null,
    position: Int
) = syncOrNull<Ok>(
    SetStickerPositionInSet(
        sticker,
        position
    )
)

fun TdAbsHandler.setStickerPositionInSet(
    sticker: InputFile? = null,
    position: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetStickerPositionInSet(
        sticker,
        position
    ),block = block
)

/**
 * Removes a sticker from the set to which it belongs
 * For bots only
 * The sticker set must have been created by the bot
 *
 * @sticker - Sticker
 */
suspend fun TdAbsHandler.removeStickerFromSet(
    sticker: InputFile? = null
) = sync<Ok>(
    RemoveStickerFromSet(
        sticker
    )
)

suspend fun TdAbsHandler.removeStickerFromSetOrNull(
    sticker: InputFile? = null
) = syncOrNull<Ok>(
    RemoveStickerFromSet(
        sticker
    )
)

fun TdAbsHandler.removeStickerFromSet(
    sticker: InputFile? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveStickerFromSet(
        sticker
    ),block = block
)
