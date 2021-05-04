package com.example.home_hackathon2.repository

import com.example.home_hackathon2.model.UserWithAuth
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    val isCreatedUser: StateFlow<Boolean>
    suspend fun createUser(name: String): UserWithAuth
}
