package com.example.examplewithcompose.di

import android.content.Context
import androidx.room.Room
import com.example.examplewithcompose.room_example.data_sources.labresults.local_storage.LabResultDao
import com.example.examplewithcompose.room_example.data_sources.labresults.local_storage.MemoryAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideMemoryAppDatabase(context: Context): MemoryAppDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            MemoryAppDatabase::class.java,
        ).build()

    @Provides
    @Singleton
    internal fun provideLabResultDao(
        memoryAppDatabase: MemoryAppDatabase,
    ): LabResultDao = memoryAppDatabase.labResultsDao()
}