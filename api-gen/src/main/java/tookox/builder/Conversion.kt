package tookox.builder

fun String.snakeToCamel(): String = split("_").joinToString("") { it.capitalize() }.decapitalize()
