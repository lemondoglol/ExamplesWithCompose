package com.example.examplewithcompose.di

import com.example.examplewithcompose.coroutine_setup.DefaultDispatcherProvider
import com.example.examplewithcompose.coroutine_setup.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    /**
     * Coroutines
     * */
    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(
        defaultDispatcherProvider: DefaultDispatcherProvider,
    ): DispatcherProvider
}