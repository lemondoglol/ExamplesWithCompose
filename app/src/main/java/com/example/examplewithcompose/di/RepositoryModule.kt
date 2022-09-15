package com.example.examplewithcompose.di

import com.example.examplewithcompose.data.repository.PreferenceDataStoreRepository
import com.example.examplewithcompose.data.repository.PreferenceDataStoreRepositoryImpl
import com.example.examplewithcompose.room_example.data_sources.labresults.LabResultRepository
import com.example.examplewithcompose.room_example.data_sources.labresults.LabResultRepositoryImpl
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

    @Binds
    @Singleton
    abstract fun bindLabResultRepository(
        labResultRepository: LabResultRepositoryImpl
    ): LabResultRepository

}
