package com.example.examplewithcompose.di

import com.example.examplewithcompose.data.repository.PreferenceDataStoreRepository
import com.example.examplewithcompose.data.repository.PreferenceDataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPreferenceDataStoreRepository(
        preferenceDataStoreRepository: PreferenceDataStoreRepositoryImpl
    ): PreferenceDataStoreRepository

}
