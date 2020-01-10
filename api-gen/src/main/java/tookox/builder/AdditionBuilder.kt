package tookox.builder

import tookox.tl.TlAddition

fun StringBuilder.buildAnnotations(additions: List<TlAddition>) {
    additions
        .filterIsInstance<TlAddition.Annotation>()
        .takeIf(List<*>::isNotEmpty)
        ?.map(TlAddition.Annotation::annotation)
        ?.distinct()
        ?.sorted()
        ?.joinTo(this, "\n@", "@", "\n")
}
