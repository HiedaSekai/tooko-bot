package tookox.builder

import tookox.tl.TlAddition
import tookox.tl.emptyToken
import tookox.tl.spaceToken

const val group = "td"
const val tabIndent = "    "

fun StringBuilder.suppress(vararg suppressed: String) {
    append("@file:Suppress")
    withRoundBrackets {
        suppressed.joinTo(this, ",\n") { "\"$it\"" }
    }
    append("\n")
}

fun StringBuilder.buildImport(vararg packages: String) {
    append("import ").append(group)
    if (packages.isNotEmpty()) packages.joinTo(this, ".", ".", ".*\n", transform = String::trim)
    else append(".*\n")
}

fun StringBuilder.buildPackage(vararg packages: String) {
    append("package ").append(group)
    if (packages.isNotEmpty()) packages.joinTo(this, ".", ".", "\n", transform = String::trim).append(";")
    else append("\n")
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