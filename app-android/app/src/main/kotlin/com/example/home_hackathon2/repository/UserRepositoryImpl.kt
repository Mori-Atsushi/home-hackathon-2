package com.example.home_hackathon2.repository

import com.example.home_hackathon2.model.UserWithAuth
import com.example.home_hackathon2.pb.App
import com.example.home_hackathon2.pb.AppServiceGrpcKt.AppServiceCoroutineStub
import com.example.home_hackathon2.repository.mapper.toModel
import com.example.home_hackathon2.source.Preferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: AppServiceCoroutineStub,
    private val preferences: Preferences
) : UserRepository {
    private val isCreatedUserValue
        get() = preferences.userId != null && preferences.accessToken != null
    private val _isCreatedUser = MutableStateFlow(isCreatedUserValue)
    override val isCreatedUser: StateFlow<Boolean>
        get() = _isCreatedUser

    override suspend fun createUser(name: String): UserWithAuth {
        val request = App.CreateUserRequest.newBuilder()
            .setName(name)
            .build()
        val response = service.createUser(request)
        val model = response.userWithAuth.toModel()
        preferences.userId = model.user.id
        preferences.userName = model.user.name
        preferences.accessToken = model.accessToken
        _isCreatedUser.value = true
        return model
    }
}
