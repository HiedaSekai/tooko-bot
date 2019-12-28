package tookox.core

import cn.hutool.http.HtmlUtil

val String.blod get() = "<b>${HtmlUtil.escape(this)}</b>"
val String.italic get() = "<i>${HtmlUtil.escape(this)}</i>"
val Any.code get() = "<code>${HtmlUtil.escape(toString())}<code>"

fun String.link(url: String) = "<a href=\"$url\">${HtmlUtil.escape(this)}</a>"
fun String.inlineMention(userId: Int) = link("tg://user?id=$userId")