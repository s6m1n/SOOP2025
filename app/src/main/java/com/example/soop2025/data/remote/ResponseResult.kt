package com.example.soop2025.data.remote

sealed interface ResponseResult<T : Any> {
    class Success<T : Any>(val data: T) : ResponseResult<T>

    class Exception<T : Any>(val e: Throwable, val message: String) : ResponseResult<T>
}
