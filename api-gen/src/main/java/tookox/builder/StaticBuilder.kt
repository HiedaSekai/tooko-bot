package tookox.builder

import tookox.tl.TlDataMetadata
import tookox.tl.TlFunction

fun StringBuilder.buildRawSyncFunction(function: TlFunction) {
    buildDescription(function.descriptions())
    buildAnnotations(function.metadata.additions)
    append("fun syncRaw")
    withRoundBrackets {
        append("f: ").append(function.type.capitalize())
    }
    val returnType = function.returnType.capitalize()
    append(" = tookox.core.syncRaw<$returnType>(f)")
    append("\n")
}

fun StringBuilder.buildSyncFunction(function: TlFunction, metadata: TlDataMetadata) {
    buildDescription(function.descriptionsWithProperties())
    buildAnnotations(function.metadata.additions)
    append("fun ").append(function.type.decapitalize())
    buildParameters(function.metadata.properties.map { it.toParameter(metadata) }, addEmptyBrackets = true)
    /*append(": ").append(function.returnType.capitalize()).*/append(" = syncRaw")
    withRoundBrackets {
        append(function.type.capitalize())
        if (function.metadata.properties.isNotEmpty()) withRoundBrackets {
            function.metadata.properties.joinTo(this, ",\n") { it.name.snakeToCamel() }
        } else append("()")
    }
    append("\n")
}