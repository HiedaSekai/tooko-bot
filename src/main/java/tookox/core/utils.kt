package tookox.core

import com.google.common.base.Optional
import com.optimaize.langdetect.DetectedLanguage
import com.optimaize.langdetect.LanguageDetectorBuilder
import com.optimaize.langdetect.i18n.LdLocale
import com.optimaize.langdetect.ngram.NgramExtractors
import com.optimaize.langdetect.profiles.LanguageProfileReader


fun <T : Any> T.applyIf(boolean: Boolean, block: T.() -> Unit): T {

    if (boolean) block.invoke(this)

    return this

}

fun <T : Any> T.applyIfNot(boolean: Boolean, block: T.() -> Unit): T {

    if (!boolean) block.invoke(this)

    return this

}

private val languageProfiles = LanguageProfileReader().readAllBuiltIn()

private val languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
        .withProfiles(languageProfiles)
        .build()

fun String.detectLanguage(): Optional<LdLocale> {

    return languageDetector.detect(this)

}

fun String.detectLanguages(): List<DetectedLanguage>? {

    return languageDetector.getProbabilities(this)

}