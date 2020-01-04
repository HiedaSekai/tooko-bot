package tookox

import org.yaml.snakeyaml.Yaml
import tooko.main.Env
import tooko.main.Lang
import tookox.core.asHtml
import tookox.core.defaultLog
import tookox.core.td.asMarkdown

object LibsLoader {

    fun load(vararg names: String) {

        for (name in names) {

            runCatching {

                val target = NativeTarget.current()

                System.load(Env.getPath("libs/${target.prefix}$name.${target.ext}"))

            }.recover {

                runCatching { System.loadLibrary("tdjni") }.onFailure {

                    _ ->

                    throw it

                }

            }.onFailure {

                error("Unable load $name")

            }

        }

    }

    enum class NativeTarget(val prefix: String, val ext: String) {

        Linux("lib", "so"),

        MacOS("lib", "dylib"),

        Win32("", "dll"),

        Win64("", "dll");

        companion object {

            fun current(): NativeTarget = target(System.getProperty("os.name"), System.getProperty("os.arch"))

            internal fun target(os: String, arch: String): NativeTarget {

                val osLowerCase = os.toLowerCase()

                return when {

                    "linux" in osLowerCase -> Linux

                    "mac" in osLowerCase -> MacOS

                    "windows" in osLowerCase -> if ("64" in arch.toLowerCase()) Win64 else Win32

                    else -> error("Target is not supported")

                }

            }

        }

    }

    fun loadLanguages() {

        val dir = Env.getFile("i18n")

        val languages = dir.listFiles { _, name -> name.endsWith(".yml") }

        if (languages == null || languages.isEmpty()) error("找不到语言文件.")

        val yaml = Yaml()

        languages.forEach {

            runCatching {

                val language = yaml.loadAs(it.inputStream(), Lang::class.java)

                language::class.java.fields.forEach { field ->

                    if (field.type == String::class.java) {

                        field.set(it, (field.get(language) as String).asMarkdown.asHtml)

                    }

                }

                Lang.ALL[language.LANG_ID] = language

                Lang.BY_NAME[language.LANG_NAME] = language

            }.onFailure { ex ->

                defaultLog.warn(ex, "语言文件 $it.name 解析错误, 已跳过.")

            }

        }

        if (Lang.ALL.isEmpty()) {

            error("无可用的语言文件.")

        }

        Lang.DEFAULT = if (Lang.BY_NAME.containsKey(Env.DEF_LANG)) {

            defaultLog.info("将 ${Env.DEF_LANG} 设置为基本语言")

            Lang.BY_NAME.get(Env.DEF_LANG)!!

        } else {

            if (Lang.ALL.containsKey(0)) {

                Lang.ALL.get(0)!!

            } else {

                Lang.ALL.values.iterator().next()!!

            }.apply {

                defaultLog.warn("找不到设定的默认语言 : ${Env.DEF_LANG} , 已重置为 $LANG_NAME")

            }
        }

    }

}