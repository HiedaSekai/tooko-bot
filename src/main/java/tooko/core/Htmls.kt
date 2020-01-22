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