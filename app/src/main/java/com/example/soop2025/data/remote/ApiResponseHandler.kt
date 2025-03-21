package com.example.soop2025.data.remote

import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.Success
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiResponseHandler @Inject constructor(
    private val errorResponseConverter: ErrorResponseConverter
) {

    suspend fun <T : Any> handle(execute: suspend () -> Response<T>): ResponseResult<T> {
        return try {
            val response: Response<T> = execute()
            val body: T? = response.body()
            if (response.isSuccessful) {
                handleSuccessResponse(response, body)
            } else {
                handleErrorResponse(response)
            }
        } catch (e: Throwable) {
            Exception(
                e = e,
                message = e.message.toString()
            )
        }
    }

    private fun <T : Any> handleSuccessResponse(
        response: Response<T>,
        body: T?
    ): ResponseResult<T> = when {
        response.code() == HttpStatusCode.CREATED -> Success(body as T)
        response.code() == HttpStatusCode.NO_CONTENT -> Success(Unit as T)
        body != null -> Success(body)
        else -> handleErrorResponse(response)
    }

    private fun <T : Any> handleErrorResponse(
        response: Response<T>
    ): ResponseResult<T> {
        val errorBody = response.errorBody() ?: throw IllegalArgumentException(ERROR_BODY_NOT_FOUND)
        val errorResponse = errorResponseConverter.convert(errorBody)
        return Exception(
            e = IllegalStateException("Server Error: ${response.code()}"),
            message = errorResponse.message
        )
    }

    companion object {
        private object HttpStatusCode {
            const val CREATED = 201
            const val NO_CONTENT = 204
        }

        private const val ERROR_BODY_NOT_FOUND = "errorBody를 찾을 수 없습니다."

        suspend fun <T : Any> ResponseResult<T>.onSuccess(executable: suspend (T) -> Unit): ResponseResult<T> =
            apply {
                if (this is Success<T>) {
                    executable(data)
                }
            }

        suspend fun <T : Any> ResponseResult<T>.onException(executable: suspend (e: Throwable, message: String) -> Unit): ResponseResult<T> =
            apply {
                if (this is Exception<T>) {
                    executable(e, message)
                }
            }
    }
}
