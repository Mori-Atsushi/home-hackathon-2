package com.example.home_hackathon2.di

import com.example.home_hackathon2.pb.AppServiceGrpcKt.AppServiceCoroutineStub
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannelBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GrpcModule {
    private const val HOST = "35.243.113.15"
    private const val PORT = 5300

    @Provides
    @Singleton
    fun provideAppServiceCoroutineStub(): AppServiceCoroutineStub {
        val channel = ManagedChannelBuilder
            .forAddress(HOST, PORT)
            .usePlaintext()
            .build()
        return AppServiceCoroutineStub(channel)
    }
}
