package tookox

import tooko.main.Env

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

}