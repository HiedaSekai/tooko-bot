@file:Suppress("unused")

package tookox.core

import cn.hutool.core.util.ArrayUtil
import tooko.td.TdApi
import tooko.td.TdApi.FormattedText
import tookox.core.client.TdAbsHandler

val TdApi.User.displayName get() = "$firstName $lastName".trim()

val TdApi.Message.fromPrivate get() = chatId > 0L
val TdApi.Message.fromBasicGroup get() = chatId > -1000000000000L
val TdApi.Message.fromSuperGroup get() = chatId < -1000000000000L && !isChannelPost
val TdApi.Message.fromChannel get() = isChannelPost

class Finish : RuntimeException("Finish Event")

fun TdAbsHandler.finishEvent() {

    throw Finish()

}

operator fun FormattedText.plus(text: FormattedText): FormattedText {

    val result = FormattedText()

    result.text = this.text + text.text

    result.entities = entities?.takeIf { it.isNotEmpty() } ?: arrayOfNulls(0)

    if (text.entities != null && text.entities.isEmpty()) {

        text.entities.forEach {

            it.offset += this.text.length

        }

        ArrayUtil.append(result.entities, text, entities)

    }

    return result

}

val FormattedText.asHtml: String
    get() {

        var htmlText = ""

        var index = 0

        entities.forEach {

            if (it.offset > index) {

                htmlText += text.substring(index, it.offset)

            }

            index = it.offset + it.length

            val entityText = text.substring(it.offset, index)

            htmlText += when (it.type) {

                is TdApi.TextEntityTypeBold -> entityText.asBlod
                is TdApi.TextEntityTypeItalic -> entityText.asItalic
                is TdApi.TextEntityTypeCode -> entityText.asCode
                is TdApi.TextEntityTypePre -> entityText.asCode
                is TdApi.TextEntityTypePreCode -> entityText.asCode
                is TdApi.TextEntityTypeUnderline -> entityText.asUnderline
                is TdApi.TextEntityTypeStrikethrough -> entityText.asDelete

                is TdApi.TextEntityTypeTextUrl -> entityText.toLink((it.type as TdApi.TextEntityTypeTextUrl).url)
                is TdApi.TextEntityTypeMentionName -> entityText.toInlineMention((it.type as TdApi.TextEntityTypeMentionName).userId)

                else -> entityText

            }

        }

        if (text.length > index) {

            htmlText += text.substring(index, text.length)

        }

        return htmlText

    }