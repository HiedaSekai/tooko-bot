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

@file:Suppress("unused")

package tooko.core

import td.TdApi
import td.TdApi.*
import td.TdNative
import tooko.core.client.*

val User.displayName get() = "$firstName $lastName".trim()

val Message.fromPrivate get() = chatId > 0L
val Message.fromBasicGroup get() = chatId > -1000000000000L
val Message.fromSuperGroup get() = chatId < -1000000000000L && !isChannelPost
val Message.fromChannel get() = isChannelPost

val Long.fromPrivate get() = this > 0L
val Long.fromBasicGroup get() = this > -1000000000000L && this < 0
val Long.fromSuperGroupOrChannel get() = this < -1000000000000L

val Message.text
    get() = if (content is MessageText) {

        (content as MessageText).text.text

    } else null

fun <T : Object> syncRaw(function: TdApi.Function): T {

    val result = TdNative.nativeClientExecute(function)

    if (result is Error) {

        throw TdException(result)

    } else {

        @Suppress("UNCHECKED_CAST")
        return result as T

    }

}
val MessageContent.asInput: InputMessageContent?
    get() = when (this) {

        is MessageText -> InputMessageText(this.text, this.webPage == null, false)

        is MessageAnimation -> {

            val file = InputFileRemote(this.animation.animation.remote.id!!)
            val thumbnail = if (this.animation.thumbnail == null) null else InputThumbnail(InputFileRemote(this.animation.thumbnail!!.photo.remote.id), this.animation.thumbnail!!.width, this.animation.thumbnail!!.height)

            InputMessageAnimation(file, thumbnail, this.animation.duration, this.animation.width, this.animation.height, this.caption)

        }

        is MessageAudio -> {

            val file = InputFileRemote(this.audio.audio.remote.id)
            val thumbnail = if (this.audio.albumCoverThumbnail == null) null else InputThumbnail(InputFileRemote(this.audio.albumCoverThumbnail!!.photo.remote.id), this.audio.albumCoverThumbnail!!.width, this.audio.albumCoverThumbnail!!.height)

            InputMessageAudio(file, thumbnail, this.audio.duration, this.audio.title, this.audio.performer, this.caption)

        }

        is MessageDocument -> {

            val file = InputFileRemote(this.document.document.remote.id)
            val thumbnail = if (this.document.thumbnail == null) null else InputThumbnail(InputFileRemote(this.document.thumbnail!!.photo.remote.id), this.document.thumbnail!!.width, this.document.thumbnail!!.height)

            InputMessageDocument(file, thumbnail, this.caption)

        }

        is MessagePhoto -> {

            val file = InputFileRemote(this.photo.sizes[0].photo.remote.id)

            InputMessagePhoto(file, null, intArrayOf(), this.photo.sizes[0].width, this.photo.sizes[0].height, this.caption, 0)

        }

        is MessageSticker -> {

            val file = InputFileRemote(this.sticker.sticker.remote.id)
            val thumbnail = if (this.sticker.thumbnail == null) null else InputThumbnail(InputFileRemote(this.sticker.thumbnail!!.photo.remote.id), this.sticker.thumbnail!!.width, this.sticker.thumbnail!!.height)

            InputMessageSticker(file, thumbnail, this.sticker.width, this.sticker.height)

        }

        is MessageVideo -> {

            val file = InputFileRemote(this.video.video.remote.id)
            val thumbnail = if (this.video.thumbnail == null) null else InputThumbnail(InputFileRemote(this.video.thumbnail!!.photo.remote.id), this.video.thumbnail!!.width, this.video.thumbnail!!.height)

            InputMessageVideo(file, thumbnail, intArrayOf(), this.video.duration, this.video.width, this.video.height, this.video.supportsStreaming, this.caption, 0)

        }

        is MessageVideoNote -> {

            val file = InputFileRemote(this.videoNote.video.remote.id)
            val thumbnail = if (this.videoNote.thumbnail == null) null else InputThumbnail(InputFileRemote(this.videoNote.thumbnail!!.photo.remote.id), this.videoNote.thumbnail!!.width, this.videoNote.thumbnail!!.height)

            InputMessageVideoNote(file, thumbnail, this.videoNote.duration, this.videoNote.length)

        }

        is MessageLocation -> InputMessageLocation(this.location, this.livePeriod)

        is MessageVoiceNote -> {

            val file = InputFileRemote(this.voiceNote.voice.remote.id)

            InputMessageVoiceNote(file, this.voiceNote.duration, this.voiceNote.waveform, this.caption)

        }

        is MessageVenue -> InputMessageVenue(this.venue)
        is MessageContact -> InputMessageContact(this.contact)

        else -> null

    }