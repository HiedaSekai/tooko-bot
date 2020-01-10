package tookox.builder

import tookox.tl.*

fun StringBuilder.buildParameters(parameters: List<String>, addEmptyBrackets: Boolean = false) {
    if (parameters.isNotEmpty()) withRoundBrackets {
        parameters.joinTo(this, ",\n")
    } else if (addEmptyBrackets) append("()")
}

fun TlProperty.toParameter(metadata: TlDataMetadata, prefix: String = "", nullable: Boolean = false): String {
    val (withDefault, withNullables) = metadata
    val propName = name.snakeToCamel()

    val default = if (nullable)

        questionToken + nullToken

    else if (type is TlRefType) {

        if (additions.any { it is TlAddition.Nullable } || withNullables) questionToken + nullToken
        else emptyToken

    } else emptyToken

    return "${inlineAnnotations()}$prefix$propName: ${type.kotlinType}$default".replace("??", "?")
}

fun TlProperty.toField(metadata: TlDataMetadata,nullable: Boolean): String {

    val (withDefault, withNullables) = metadata

    val propName = name.snakeToCamel()

    val prefix: String

    val default = if (nullable) {

        prefix = "var"

        questionToken + nullToken

    } else if (type !is TlPrimitiveType) {

        if (type is TlRefType && (additions.any { it is TlAddition.Nullable } || withNullables)) {

            prefix = "var"

            questionToken + nullToken

        } else {

            prefix = "var"

            weakToken

        }

    } else {

        prefix = "var"

        weakToken

    }

    return "${inlineAnnotations()}$prefix $propName: ${type.kotlinType}$default"

}

fun TlProperty.descriptionLines(): List<String> {
    val link = "$addressToken${name.snakeToCamel()}$spaceToken$dashToken$spaceToken"
    val spaces = (1..link.length).joinToString("") { " " }
    return listOf(link + descriptions.first()) + (descriptions.drop(1) + additions.strings()).map { "$spaces$it" }
}


fun TlProperty.inlineAnnotations(): String =
        additions
                .filterIsInstance<TlAddition.Annotation>()
                .takeIf(List<*>::isNotEmpty)
                ?.map(TlAddition.Annotation::annotation)
                ?.distinct()
                ?.sorted()
                ?.joinToString(spaceToken + addressToken, addressToken, spaceToken)
                ?: ""
