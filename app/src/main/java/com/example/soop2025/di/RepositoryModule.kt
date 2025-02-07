package com.example.soop2025.di

import com.example.soop2025.data.remote.repository.ReposDefaultRepository
import com.example.soop2025.data.remote.repository.ReposSearchDefaultRepository
import com.example.soop2025.data.remote.repository.UserDefaultRepository
import com.example.soop2025.domain.ReposRepository
import com.example.soop2025.domain.ReposSearchRepository
import com.example.soop2025.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindReposSearchRepository(repositoryImpl: ReposSearchDefaultRepository): ReposSearchRepository

    @Binds
    abstract fun bindReposRepository(repositoryImpl: ReposDefaultRepository): ReposRepository

    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserDefaultRepository): UserRepository
}
