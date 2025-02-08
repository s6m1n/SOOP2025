package com.example.soop2025.data.remote

import com.example.soop2025.data.model.shared.ErrorResponse
import okhttp3.ResponseBody

fun interface ErrorResponseConverter {
    fun convert(errorBody: ResponseBody): ErrorResponse
}
