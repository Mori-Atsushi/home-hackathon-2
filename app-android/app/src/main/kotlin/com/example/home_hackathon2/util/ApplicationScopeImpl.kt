package com.example.home_hackathon2.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ApplicationScopeImpl: ApplicationScope {
    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Main.immediate
}
