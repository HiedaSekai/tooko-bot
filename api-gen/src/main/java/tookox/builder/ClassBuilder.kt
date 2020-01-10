package tookox.builder

import tookox.tl.*

fun StringBuilder.buildClass(data: TlData, metadata: TlDataMetadata, paramNullable: Boolean) {

    val declaration = when (data) {
        is TlAbstract -> "abstract class "
        is TlClass -> "class "
    }

    buildDescription(data.descriptionsWithProperties())
    // buildAnnotations(listOf(TlAddition.JvmOverloads))
    append(declaration).append(data.type.capitalize())
    append(" : ").append(data.parentType.capitalize())

    if (data.metadata.properties.isEmpty()) append("()")

    if (data is TlClass || data.metadata.properties.isNotEmpty()) append(spaceToken).withCurlyBrackets {

        append("\n")

        if (data.metadata.properties.isNotEmpty()) {

            data.metadata.properties.forEach {

                append(it.toField(metadata,paramNullable)).append("\n")

            }

            append("\nconstructor()\n\nconstructor")

            append("(")

            data.metadata.properties.joinTo(this) {

                it.toParameter(metadata, nullable = paramNullable)

            }

            append(")")

            append(spaceToken)

            withCurlyBrackets {

                data.metadata.properties.joinTo(this, "\n", "\n", "\n") {

                    val propName = it.name.snakeToCamel()

                    "this.$propName = $propName"

                }

            }

            append("\n")

        }

        if (data is TlClass) {

            buildConstructorField(data.crc)

            append("\n")

        }

    }

    append("\n")

}

fun StringBuilder.buildConstructorField(crc: Int) {
    append("override val constructor: Int @BsonIgnore get() = ").append(crc)
}

fun TlData.descriptions(): List<String> = metadata.descriptions + metadata.additions.strings()

fun TlData.descriptionsWithProperties(): List<String> =
        descriptions() + (when (metadata.properties.isEmpty()) {
            true -> emptyList()
            false -> listOf("") + metadata.properties.flatMap(TlProperty::descriptionLines)
        })
