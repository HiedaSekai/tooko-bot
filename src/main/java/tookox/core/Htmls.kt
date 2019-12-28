package tookox.core

import cn.hutool.http.HtmlUtil

val String.blod get() = "<b>${HtmlUtil.escape(this)}</b>"

val String.italic get() = "<i>${HtmlUtil.escape(this)}</i>"

val String.code get() = "<code>${HtmlUtil.escape(this)}<code>"
