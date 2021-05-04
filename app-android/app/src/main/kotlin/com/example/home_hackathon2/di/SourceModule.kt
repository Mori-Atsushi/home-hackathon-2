package com.example.home_hackathon2.di

import com.example.home_hackathon2.source.Preferences
import com.example.home_hackathon2.source.PreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {
    @Binds
    @Singleton
    abstract fun bindsPreferences(
        impl: PreferencesImpl
    ): Preferences
}
