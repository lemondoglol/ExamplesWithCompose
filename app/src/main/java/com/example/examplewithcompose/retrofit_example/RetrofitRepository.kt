package com.example.examplewithcompose.retrofit_example

import com.example.examplewithcompose.retrofit_example.api.SimpleApiClient
import com.example.examplewithcompose.retrofit_example.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val simpleApiClient: SimpleApiClient,
) {

    suspend fun getUser1(): User? = withContext(Dispatchers.IO) {
        val response = simpleApiClient.getPost1()
        when (response.isSuccessful) {
            true -> response.body()
            false -> null
        }
    }

}