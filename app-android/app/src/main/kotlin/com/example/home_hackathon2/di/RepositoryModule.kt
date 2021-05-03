package com.example.home_hackathon2.di

import com.example.home_hackathon2.repository.ChatRoomRepository
import com.example.home_hackathon2.repository.ChatRoomRepositoryImpl
import com.example.home_hackathon2.repository.UserRepository
import com.example.home_hackathon2.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindChatRoomRepository(
        impl: ChatRoomRepositoryImpl
    ): ChatRoomRepository
}
