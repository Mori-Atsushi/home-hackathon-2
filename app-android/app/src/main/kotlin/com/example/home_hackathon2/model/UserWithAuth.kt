package com.example.home_hackathon2.model

data class UserWithAuth(
    val user: User,
    val accessToken: String
)
