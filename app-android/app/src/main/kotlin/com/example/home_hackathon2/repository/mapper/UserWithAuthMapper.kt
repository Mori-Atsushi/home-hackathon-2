package com.example.home_hackathon2.repository.mapper

import com.example.home_hackathon2.model.UserWithAuth
import com.example.home_hackathon2.pb.App

fun App.UserWithAuth.toModel(): UserWithAuth {
    return UserWithAuth(
        user = user.toModel(),
        accessToken = accessToken
    )
}
