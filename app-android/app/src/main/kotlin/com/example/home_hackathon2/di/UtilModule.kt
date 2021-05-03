package com.example.home_hackathon2.di

import com.example.home_hackathon2.util.ApplicationScope
import com.example.home_hackathon2.util.ApplicationScopeImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilModule {
    @Binds
    @Singleton
    abstract fun bindApplicationScope(
        impl: ApplicationScopeImpl
    ): ApplicationScope
}
