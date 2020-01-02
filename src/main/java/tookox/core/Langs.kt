package tookox.core

import cn.hutool.core.getter.OptNullBasicTypeFromObjectGetter
import tooko.main.Env
import java.util.*
import kotlin.collections.HashMap

@Suppress("UNCHECKED_CAST")
class TypedMap(map: Any) : HashMap<String,Any>(map as Map<String,Any>), OptNullBasicTypeFromObjectGetter<String> {

    override fun getObj(key: String, defaultValue: Any?): Any? = get(key) ?: defaultValue

}

fun Any.toTypedMap() = TypedMap(this)

fun loadLanguages() {

    val langs = Env.getFile("i18n").listFiles()

    // if (langs == null || langs.isEmpty()) error("missing language files.")

}

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
