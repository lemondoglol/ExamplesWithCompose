package com.example.examplewithcompose.di

import com.example.examplewithcompose.retrofit_example.api.SimpleApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // check Code
    @Provides
    @Singleton
    fun provideSimpleApiClient(retrofit: Retrofit): SimpleApiClient
            = retrofit.create(SimpleApiClient::class.java)

    private const val BASE_URL = "https://jsonplaceholder.typicode.com"
}