package tookox.scripts

import cn.hutool.core.io.FileUtil
import com.optimaize.langdetect.ngram.NgramExtractors
import com.optimaize.langdetect.profiles.LanguageProfileBuilder
import com.optimaize.langdetect.profiles.LanguageProfileWriter
import tooko.main.Fn
import tookox.core.containsChinese
import tookox.core.containsJapaneseKana
import tookox.core.maxPaging
import tookox.core.plainText

@Suppress("UNCHECKED_CAST", "DEPRECATION")
object LangProfile {

    val apiKey = ""
    val apiSecretKey = ""
    val accessToken = ""
    val accessTokenSecret = ""

    val dissident = arrayOf(
            "Ozawa_Nagise",
            "Nerazim233",
            "MingZe_suki",
            "pEunpLYrGyouHgz",
            "japanaqi",
            "charles984681",
            "MfuZDoEQrOucp0Q",
            "fuck__china",
            "GlobalHimalaya",
            "Sneakywinnie8",
            "pokfulamhku",
            "ILiveInBeijing2",
            "kuaijishoujidao",
            "Socialistfist",
            "zhanglucy88",
            "CPCYouthLeague",
            "Xhnsoc__Redflag",
            "YouthChinaParty",
            "emperorsager")

    @JvmStatic
    fun main(args: Array<String>) {

        val api = Fn.mkApi(apiKey, apiSecretKey, accessToken, accessTokenSecret)

        val output = "D://output.json"

        val languageProfile = LanguageProfileBuilder("zh")
                .ngramExtractor(NgramExtractors.standard())
                .minimalFrequency(5);

        dissident.forEach {

            println(it)

            api.getUserTimeline(it, maxPaging).forEach {

                val statusText = it.plainText

                if (statusText.isBlank() || !statusText.containsChinese() || statusText.containsJapaneseKana()) {

                    return@forEach

                }

                print(statusText)

                languageProfile.addText(statusText)

            }


        }

        LanguageProfileWriter().write(languageProfile.build(), FileUtil.getOutputStream(output));

    }


}