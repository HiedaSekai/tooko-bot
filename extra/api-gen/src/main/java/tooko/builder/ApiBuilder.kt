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
import tooko.tl.TlScheme
import tooko.tl.get

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

