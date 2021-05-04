package com.example.home_hackathon2.di

import com.example.home_hackathon2.usecase.CreateUserUseCase
import com.example.home_hackathon2.usecase.CreateUserUseCaseImpl
import com.example.home_hackathon2.usecase.ObserveUserCreatedUseCase
import com.example.home_hackathon2.usecase.ObserveUserCreatedUseCaseImpl
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


    @Binds
    abstract fun bindObserveUserCreatedUseCase(
        impl: ObserveUserCreatedUseCaseImpl
    ): ObserveUserCreatedUseCase
}
