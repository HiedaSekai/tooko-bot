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

package tookox.core

import td.TdApi.User
import tookox.core.env.*

val Number.langFor get() = Lang.get(this)
val User.langFor get() = Lang.get(this)

fun String.containsChinese(): Boolean {

    var han = false

    codePoints().forEach {

        if (Character.UnicodeScript.of(it) == Character.UnicodeScript.HAN) {

            han = true

        }

    }

    return han

}

fun String.containsJapaneseKana(): Boolean {

    var japaneseKana = false

    codePoints().forEach {

        if (Character.UnicodeScript.of(it) == Character.UnicodeScript.HIRAGANA ||
                Character.UnicodeScript.of(it) == Character.UnicodeScript.KATAKANA) {

            japaneseKana = true

        }

    }

    return japaneseKana

}


fun String.containsKoreanHangul(): Boolean {

    var koreanHangul = false

    codePoints().forEach {

        if (Character.UnicodeScript.of(it) == Character.UnicodeScript.HANGUL) {

            koreanHangul = true

        }

    }

    return koreanHangul

}
