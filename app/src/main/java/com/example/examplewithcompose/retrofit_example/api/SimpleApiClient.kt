package com.example.examplewithcompose.retrofit_example.api

import com.example.examplewithcompose.retrofit_example.model.User
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApiClient {
    // https://jsonplaceholder.typicode.com/posts/1
    @GET("posts/1")
    suspend fun getPost1(): Response<User>
}
