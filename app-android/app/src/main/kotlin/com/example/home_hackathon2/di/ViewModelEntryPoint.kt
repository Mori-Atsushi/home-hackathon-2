package com.example.home_hackathon2.di

import com.example.home_hackathon2.ui.AppViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelEntryPoint {
    fun getAppViewModel(): AppViewModel
}
