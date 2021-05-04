package com.example.home_hackathon2.di

import com.example.home_hackathon2.usecase.CreateUserUseCase
import com.example.home_hackathon2.usecase.CreateUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindCreateUserUseCase(
        impl: CreateUserUseCaseImpl
    ): CreateUserUseCase
}
