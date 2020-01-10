package tookox.builder

import tookox.tl.TlAddition
import tookox.tl.TlScheme
import tookox.tl.get

fun StringBuilder.buildApi(scheme: TlScheme) {
   // suppress("unused")
    // useExperimentalAnnotationsForFile()
    //append("\n")
    buildJavaPackage("td")
    append("\n")
    buildJavaImport("org.jetbrains.annotations")
    buildJavaImport("org.bson.codecs.pojo.annotations")
    append("\n")
    append("@SuppressWarnings(\"NotNullFieldNotInitialized\")\n")
    append("public class TdApi ")
    withCurlyBrackets {
        append("\n")
        append("public static abstract class Object ")
        withCurlyBrackets {
            append("\npublic native String toString();\n\n")
            append("public abstract int getConstructor();\n")
        }
        append("\n")
        append("public static abstract class Function extends Object")
        withCurlyBrackets {
            append("\npublic native String toString();\n")
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

