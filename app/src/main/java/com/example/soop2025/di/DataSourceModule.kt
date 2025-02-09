package com.example.soop2025.di

import com.example.soop2025.data.local.language.LanguageDataSource
import com.example.soop2025.data.local.language.LocalLanguageDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindLanguageDataSource(dataSourceImpl: LocalLanguageDataSource): LanguageDataSource
}
