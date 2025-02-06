package com.example.soop2025.data

import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.ServerError
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.Status
import com.example.soop2025.di.NetworkModule.getErrorResponse
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

object ApiResponseHandler {

    suspend fun <T : Any> handleApiResponse(execute: suspend () -> Response<T>): ResponseResult<T> {
        return try {
            val response: Response<T> = execute()
            val body: T? = response.body()
            if (response.isSuccessful) {
                handleSuccessResponse(response, body)
            } else {
                handleErrorResponse(response)
            }
        } catch (e: HttpException) {
            ServerError(
                status = Status.Code(e.code()),
                message = e.message()
            )
        } catch (e: Throwable) {
            val message = when (e) {
                is UnknownHostException, is SocketException -> NETWORK_UNSTABLE
                else -> e.message.toString()
            }
            Exception(e = e, message = message)
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
        val errorResponse = getErrorResponse(errorBody)
        return ServerError(
            status = Status.Message(errorResponse.status),
            message = errorResponse.message
        )
    }

    suspend fun <T : Any> ResponseResult<T>.onSuccess(executable: suspend (T) -> Unit): ResponseResult<T> =
        apply {
            if (this is Success<T>) {
                executable(data)
            }
        }

    suspend fun <T : Any> ResponseResult<T>.onServerError(
        executable: suspend (status: Status, message: String) -> Unit
    ): ResponseResult<T> =
        apply {
            if (this is ServerError<T>) {
                executable(status, message)
            }
        }

    suspend fun <T : Any> ResponseResult<T>.onException(executable: suspend (e: Throwable, message: String) -> Unit): ResponseResult<T> =
        apply {
            if (this is Exception<T>) {
                executable(e, message)
            }
        }

    private object HttpStatusCode {
        const val CREATED = 201
        const val NO_CONTENT = 204
    }

    private const val NETWORK_UNSTABLE = "네트워크 연결이 불안정합니다.\n연결을 재설정한 후 다시 시도해 주세요."
    private const val ERROR_BODY_NOT_FOUND = "errorBody를 찾을 수 없습니다."
}
