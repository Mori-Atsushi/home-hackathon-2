package com.example.home_hackathon2.ui.listener

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

class SimpleRecognizerListener(
    private val listener: SimpleRecognizerResponseListener
) : RecognitionListener {

    interface SimpleRecognizerResponseListener {
        fun onRecognizedResult(speechText: String)
    }

    override fun onReadyForSpeech(p0: Bundle?) {

    }

    override fun onRmsChanged(p0: Float) {
    }

    override fun onBufferReceived(p0: ByteArray?) {
    }

    override fun onPartialResults(p0: Bundle?) {
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(p0: Int) {
        if (p0 == SpeechRecognizer.ERROR_NO_MATCH) {
            listener.onRecognizedResult("")
        }

    }

    override fun onResults(bundle: Bundle?) {
        if (bundle == null) {
            listener.onRecognizedResult("")
            return
        }

        val key = SpeechRecognizer.RESULTS_RECOGNITION
        val result = bundle.getStringArrayList(key)
        val speechText = result?.get(0)?.replace("\\s".toRegex(), "")

        if (speechText.isNullOrEmpty()) {
            listener.onRecognizedResult("")
        } else {
            listener.onRecognizedResult(speechText)
        }
    }
}

