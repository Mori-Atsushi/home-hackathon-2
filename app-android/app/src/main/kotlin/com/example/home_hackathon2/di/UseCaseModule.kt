package com.example.home_hackathon2.di

import com.example.home_hackathon2.usecase.*
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

    @Binds
    abstract fun bindJoinRoomUseCase(
        impl: JoinRoomUseCaseImpl
    ): JoinRoomUseCase

    @Binds
    abstract fun bindLeaveRoomUseCase(
        impl: LeaveRoomUseCaseImpl
    ): LeaveRoomUseCase
}
