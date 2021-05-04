package com.example.home_hackathon2.repository.helper

import com.example.home_hackathon2.source.Preferences
import io.grpc.Metadata
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthHelper @Inject constructor(
    private val preferences: Preferences
) {
    companion object {
        private val USER_ID_KEY =
            Metadata.Key.of("userId", Metadata.ASCII_STRING_MARSHALLER)
        private val ACCESS_TOKEN_KEY =
            Metadata.Key.of("accessToken", Metadata.ASCII_STRING_MARSHALLER)
    }

    fun getMeta(): Metadata {
        val metadata = Metadata()
        metadata.put(USER_ID_KEY, preferences.userId)
        metadata.put(ACCESS_TOKEN_KEY, preferences.accessToken)
        return metadata
    }
}
