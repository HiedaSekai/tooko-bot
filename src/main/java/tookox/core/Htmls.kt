@file:Suppress("unused")

package tookox.core

import cn.hutool.http.HtmlUtil
import td.TdApi.User

val String.asBlod get() = "<b>${HtmlUtil.escape(this)}</b>"
val String.asItalic get() = "<i>${HtmlUtil.escape(this)}</i>"
val String.asUnderline get() = "<u>${HtmlUtil.escape(this)}</u>"
val String.asDelete get() = "<del>${HtmlUtil.escape(this)}</del>"
val Any.asCode get() = "<code>${HtmlUtil.escape(toString())}</code>"

fun String.toLink(url: String) = "<a href=\"$url\">${HtmlUtil.escape(this)}</a>"
fun String.toInlineMention(userId: Int) = toLink("tg://user?id=$userId")
val User.asInlineMention get() = displayName.toInlineMention(id)