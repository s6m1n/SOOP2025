package com.example.soop2025.data.remote

import com.example.soop2025.data.remote.model.response.shared.ErrorResponse
import okhttp3.ResponseBody

fun interface ErrorResponseConverter {
    fun convert(errorBody: ResponseBody): ErrorResponse
}
