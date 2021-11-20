package com.junction.toxicometr.toneAnalyzer

import com.google.gson.Gson
import com.ibm.cloud.sdk.core.security.Authenticator
import com.ibm.cloud.sdk.core.security.IamAuthenticator
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer
import com.ibm.watson.tone_analyzer.v3.model.ToneChatOptions
import com.ibm.watson.tone_analyzer.v3.model.ToneChatScore
import com.ibm.watson.tone_analyzer.v3.model.Utterance
import com.ibm.watson.tone_analyzer.v3.model.Utterance.Builder
import com.ibm.watson.tone_analyzer.v3.model.UtteranceAnalyses

object ToneAnalyzerChat {
    private data class Credential(val apikey: String, val url: String)

    private const val toneAnalyzerVersion = "2017-09-21"
    private const val credentialPath = "/config/apikey.json"

    fun analyze(text: String): TextTone {
        val service = constructAnalyzer()

        val utterance: Utterance = Builder(text).build()
        val toneChatOptions = buildToneChatOptions(utterance)

        val analysisResult: UtteranceAnalyses = service.toneChat(toneChatOptions).execute().result
        val textTone: ToneChatScore? = analysisResult.utterancesTone[0].tones.maxByOrNull { it.score }

        return TextTone.values().find { it.tone == textTone?.toneId } ?: TextTone.UNKNOWN
    }

    private fun constructAnalyzer(): ToneAnalyzer {
        val credential = parseIBMCredential()
        val authenticator: Authenticator = IamAuthenticator.Builder().apikey(credential.apikey).build()

        return ToneAnalyzer(toneAnalyzerVersion, authenticator).apply {
            serviceUrl = credential.url
        }
    }

    private fun parseIBMCredential(): Credential {
        val credentialJson = ToneAnalyzerChat::class.java.getResource(credentialPath).readText()
        return Gson().fromJson(credentialJson, Credential::class.java)
    }

    private fun buildToneChatOptions(utterances: Utterance): ToneChatOptions =
        ToneChatOptions.Builder().contentLanguage(ToneChatOptions.ContentLanguage.EN).addUtterances(utterances).build()
}
