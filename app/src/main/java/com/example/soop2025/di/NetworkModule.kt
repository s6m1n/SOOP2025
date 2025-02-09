package com.example.soop2025.di

import com.example.soop2025.BuildConfig.BASE_URL
import com.example.soop2025.data.model.shared.ErrorResponse
import com.example.soop2025.data.remote.ErrorResponseConverter
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = CONTENT_TYPE.toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @JvmSuppressWildcards
    fun provideErrorResponse(retrofit: Retrofit): ErrorResponseConverter {
        return ErrorResponseConverter { errorBody ->
            retrofit.responseBodyConverter<ErrorResponse>(
                ErrorResponse::class.java,
                ErrorResponse::class.java.annotations
            ).convert(errorBody) ?: throw IllegalArgumentException("errorBody를 변환할 수 없습니다.")
        }
    }

    private const val CONTENT_TYPE = "application/json"
}
