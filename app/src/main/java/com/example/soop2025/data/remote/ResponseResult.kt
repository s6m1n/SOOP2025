package com.example.soop2025.data.remote

sealed interface ResponseResult<T : Any> {
    class Success<T : Any>(val data: T) : ResponseResult<T>

    class ServerError<T : Any>(val status: Status, val message: String) : ResponseResult<T>

    class Exception<T : Any>(val e: Throwable, val message: String) : ResponseResult<T>
}

sealed class Status {
    data class Code(val code: Int) : Status()

    data class Message(val message: String) : Status()
}
