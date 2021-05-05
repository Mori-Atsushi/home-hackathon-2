package com.example.home_hackathon2.ui.listener

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log

class SimpleRecognizerListener(
    private val listener: SimpleRecognizerResponseListener
) : RecognitionListener {

    interface SimpleRecognizerResponseListener {
        fun onBeginningOfSpeech()
        fun onRecognizedResult(speechText: String)
        fun onPartialResults(speechText: String)
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        Log.d("speech", "onReadyForSpeech")
    }

    override fun onRmsChanged(p0: Float) {
    }

    override fun onBufferReceived(p0: ByteArray?) {
        Log.d("speech", "onBufferReceived$p0")
    }

    override fun onPartialResults(p0: Bundle?) {
        val result = parseResult(p0)
        Log.d("speech", "onPartialResults$result")
        listener.onPartialResults(result)
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
        Log.d("speech", "onBeginningOfSpeech")
        listener.onBeginningOfSpeech()
    }

    override fun onEndOfSpeech() {
        Log.d("speech", "onEndOfSpeech")
    }

    override fun onError(p0: Int) {
        Log.d("speech", "onError$p0")
        if (p0 == SpeechRecognizer.ERROR_NO_MATCH) {
            listener.onRecognizedResult("")
        }
    }

    override fun onResults(bundle: Bundle?) {
        val result = parseResult(bundle)
        Log.d("speech", "onResults$result")
        listener.onRecognizedResult(result)
    }

    private fun parseResult(bundle: Bundle?): String {
        val key = SpeechRecognizer.RESULTS_RECOGNITION
        val result = bundle?.getStringArrayList(key)
        val speechText = result?.get(0)?.replace("\\s".toRegex(), "")
        return speechText.orEmpty()
    }
}

