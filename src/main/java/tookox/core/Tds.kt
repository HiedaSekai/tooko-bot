@file:Suppress("unused")

package tookox.core

import cn.hutool.core.util.ArrayUtil
import tooko.td.TdApi.*
import tookox.core.client.TdAbsHandler

val User.displayName get() = "$firstName $lastName".trim()

val Message.fromPrivate get() = chatId > 0L
val Message.fromBasicGroup get() = chatId > -1000000000000L
val Message.fromSuperGroup get() = chatId < -1000000000000L && !isChannelPost
val Message.fromChannel get() = isChannelPost

val Message.text
    get() = if (content is MessageText) {

        (content as MessageText).text.text

    } else null

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

                is TextEntityTypeBold -> entityText.asBlod
                is TextEntityTypeItalic -> entityText.asItalic
                is TextEntityTypeCode -> entityText.asCode
                is TextEntityTypePre -> entityText.asCode
                is TextEntityTypePreCode -> entityText.asCode
                is TextEntityTypeUnderline -> entityText.asUnderline
                is TextEntityTypeStrikethrough -> entityText.asDelete

                is TextEntityTypeTextUrl -> entityText.toLink((it.type as TextEntityTypeTextUrl).url)
                is TextEntityTypeMentionName -> entityText.toInlineMention((it.type as TextEntityTypeMentionName).userId)

                else -> entityText

            }

        }

        if (text.length > index) {

            htmlText += text.substring(index, text.length)

        }

        return htmlText

    }