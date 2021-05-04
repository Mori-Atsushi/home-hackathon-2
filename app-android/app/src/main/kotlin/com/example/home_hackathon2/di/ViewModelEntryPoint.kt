package com.example.home_hackathon2.di

import com.example.home_hackathon2.ui.AppViewModel
import com.example.home_hackathon2.ui.chat.ChatRoomViewModel
import com.example.home_hackathon2.ui.chat.MicInViewModel
import com.example.home_hackathon2.ui.initial.InitialViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelEntryPoint {
    fun getAppViewModel(): AppViewModel
    fun getInitialViewModel(): InitialViewModel
    fun getChatRoomViewModel(): ChatRoomViewModel
    fun getMicInViewModel(): MicInViewModel
}
