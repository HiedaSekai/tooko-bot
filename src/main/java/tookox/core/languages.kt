package tookox.core

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
