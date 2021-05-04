package com.example.home_hackathon2.repository.mapper

import com.example.home_hackathon2.model.User
import com.example.home_hackathon2.pb.App

fun App.User.toModel(): User {
    return User(
        id = id,
        name = name
    )
}
