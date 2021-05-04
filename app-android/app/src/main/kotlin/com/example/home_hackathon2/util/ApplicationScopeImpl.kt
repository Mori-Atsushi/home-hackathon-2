package com.example.home_hackathon2.util

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ApplicationScopeImpl @Inject constructor() : ApplicationScope {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Main.immediate + exceptionHandler
}
