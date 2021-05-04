package com.example.home_hackathon2.repository

import com.example.home_hackathon2.model.UserWithAuth

interface UserRepository {
    suspend fun createUser(name: String): UserWithAuth
}
