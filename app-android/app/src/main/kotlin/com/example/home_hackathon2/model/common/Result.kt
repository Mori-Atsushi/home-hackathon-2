package com.example.home_hackathon2.model.common

interface Result<T> {
    val valueOrNull: T?
    val exceptionOrNull: Throwable?
    val isFailure: Boolean
    val isSuccess: Boolean
}

interface SuccessResult<T> : Result<T> {
    val value: T
    override val valueOrNull: T?
        get() = value
    override val exceptionOrNull: Throwable?
        get() = null
    override val isFailure: Boolean
        get() = false
    override val isSuccess: Boolean
        get() = true
}

interface UnitResult : SuccessResult<Unit> {
    override val value: Unit get() = Unit
}

interface ErrorResult<T> : Result<T> {
    override val valueOrNull: T?
        get() = null
    override val exceptionOrNull: Throwable?
        get() = null
    override val isFailure: Boolean
        get() = true
    override val isSuccess: Boolean
        get() = false
}
