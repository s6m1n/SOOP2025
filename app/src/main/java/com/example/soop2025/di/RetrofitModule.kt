package com.example.soop2025.di

import com.example.soop2025.data.remote.api.ReposApiService
import com.example.soop2025.data.remote.api.ReposSearchApiService
import com.example.soop2025.data.remote.api.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideLoginApiService(retrofit: Retrofit): ReposSearchApiService =
        retrofit.create(ReposSearchApiService::class.java)

    @Singleton
    @Provides
    fun provideReposApiService(retrofit: Retrofit): ReposApiService =
        retrofit.create(ReposApiService::class.java)

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)
}
