package com.example.home_hackathon2.repository

import com.example.home_hackathon2.model.UserWithAuth
import com.example.home_hackathon2.pb.App
import com.example.home_hackathon2.pb.AppServiceGrpcKt.AppServiceCoroutineStub
import com.example.home_hackathon2.repository.mapper.toModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: AppServiceCoroutineStub
) : UserRepository {
    override suspend fun createUser(name: String): UserWithAuth {
        val request = App.CreateUserRequest.newBuilder()
            .setName(name)
            .build()
        val response = service.createUser(request)
        return response.userWithAuth.toModel()
    }
}
