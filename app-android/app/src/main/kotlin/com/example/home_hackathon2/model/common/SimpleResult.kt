package com.example.home_hackathon2.model.common

sealed class SimpleResult<T> : Result<T> {
    companion object {
        inline fun <T> of(f: () -> T): SimpleResult<T> {
            return try {
                Success(f())
            } catch (e: Throwable) {
                OtherError(e)
            }
        }
    }

    data class Success<T>(
        override val value: T
    ) : SimpleResult<T>(), SuccessResult<T>

    data class OtherError<T>(
        override val exceptionOrNull: Throwable
    ) : SimpleResult<T>(), ErrorResult<T>
}
