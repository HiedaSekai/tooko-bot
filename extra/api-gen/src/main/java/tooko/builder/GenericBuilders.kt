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

package tooko.builder

import tooko.tl.TlAddition
import tooko.tl.emptyToken
import tooko.tl.spaceToken

const val tabIndent = "    "

fun StringBuilder.suppress(vararg suppressed: String) {
    append("@file:Suppress")
    withRoundBrackets {
        suppressed.joinTo(this, ",\n") { "\"$it\"" }
    }
    append("\n")
}

fun StringBuilder.buildImport(vararg packages: String) {
    append("import ")
    if (packages.isNotEmpty()) packages.joinTo(this, ".", "", ".*\n", transform = String::trim)
    else append(".*\n")
}

fun StringBuilder.buildJavaImport(vararg packages: String) {
    append("import ")
    if (packages.isNotEmpty()) packages.joinTo(this, ".", "", ".*;\n", transform = String::trim)
    else append(".*;\n")
}


fun StringBuilder.buildPackage(vararg packages: String) {
    append("package ")
    if (packages.isNotEmpty()) packages.joinTo(this, ".", "", "\n", transform = String::trim)
    else append("\n")
}

fun StringBuilder.buildJavaPackage(vararg packages: String) {
    append("package ")
    if (packages.isNotEmpty()) packages.joinTo(this, ".", "", ";\n", transform = String::trim)
    else append(";\n")
}


fun StringBuilder.buildTypealias(from: String, to: String) {
    append("typealias ").append(from.trim()).append(" = ").append(to.trim()).append("\n")
}


fun StringBuilder.withIndent(
        indent: String = tabIndent,
        block: StringBuilder.() -> Unit
) {
    buildString(block).split("\n").joinTo(this, "\n", postfix = "\n") {
        if (it.isBlank() && indent.isBlank()) emptyToken
        else indent + it
    }
}

fun StringBuilder.withBrackets(
        start: String,
        end: String,
        indent: String = tabIndent,
        newLine: Boolean = true,
        block: StringBuilder.() -> Unit
) {
    append(start).append("\n")
    withIndent(indent, block)
    append(end)
    if (newLine) append("\n")
}

fun StringBuilder.withCurlyBrackets(block: StringBuilder.() -> Unit) {
    withBrackets("{", "}", block = block)
}

fun StringBuilder.withRoundBrackets(block: StringBuilder.() -> Unit) {
    withBrackets("(", ")", newLine = false, block = block)
}

fun StringBuilder.buildDescription(lines: List<String>) {
    withBrackets("/**", " */", " *") {
        lines.joinTo(this, "\n") {
            if (it.isBlank()) emptyToken
            else spaceToken + it
        }
    }
}

fun List<TlAddition>.strings(): List<String> = filterIsInstance<TlAddition.WithMessage>().mapNotNull(TlAddition.WithMessage::message)
