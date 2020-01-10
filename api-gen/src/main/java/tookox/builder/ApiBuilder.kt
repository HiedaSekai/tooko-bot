package tookox.builder

import tookox.tl.TlAddition
import tookox.tl.TlScheme
import tookox.tl.get

fun StringBuilder.buildApi(scheme: TlScheme) {
    suppress("unused")
    // useExperimentalAnnotationsForFile()
    append("\n")
    buildPackage()
    append("\n")
    buildTypealias("TelegramObject", "TdApi.Object")
    buildTypealias("TelegramFunction", "TdApi.Function")
    buildTypealias("TelegramUpdate", "TdApi.Update")
    buildTypealias("TelegramError", "TdApi.Error")
    append("\n")
    append("class TdApi ")
    withCurlyBrackets {
        append("\n")
        append("abstract class Object ")
        withCurlyBrackets {
            append("external override fun toString(): String\n")
            append("abstract val constructor: Int")
        }
        append("\n")
        append("abstract class Function : Object() ")
        withCurlyBrackets {
            append("external override fun toString(): String")
        }
        scheme.data.forEach {
            append("\n")
            buildClass(it, scheme.metadata[it],
                    it.type.toLowerCase().startsWith("input") ||
            it.type.toLowerCase().endsWith("options"))
        }
    }
}

fun StringBuilder.useExperimentalAnnotationsForFile() {
    append("@file:UseExperimental")
    withRoundBrackets {
        TlAddition.annotations().joinTo(this, ",\n") {
            "${it.annotation}::class"
        }
    }
    append("\n")
}

fun StringBuilder.useExperimentalAnnotationForFile(annotation: TlAddition.Annotation) {
    append("@file:UseExperimental(").append(annotation.annotation).append("::class)")
}

