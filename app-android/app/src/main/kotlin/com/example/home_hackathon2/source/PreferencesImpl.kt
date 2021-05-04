package com.example.home_hackathon2.source

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    @ApplicationContext context: Context
) : Preferences {
    companion object {
        private const val USER_ID = "user_id"
        private const val USER_NAME = "user_name"
        private const val ACCESS_TOKEN = "access_token"
    }

    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context);
    override var userId: String?
        get() = sharedPreferences.getString(USER_ID, null)
        set(value) {
            sharedPreferences.edit { putString(USER_ID, value) }
        }

    override var userName: String?
        get() = sharedPreferences.getString(USER_NAME, null)
        set(value) {
            sharedPreferences.edit { putString(USER_NAME, value) }
        }

    override var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN, null)
        set(value) {
            sharedPreferences.edit { putString(ACCESS_TOKEN, value) }
        }
}
