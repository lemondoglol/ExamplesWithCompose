package com.example.examplewithcompose.retrofit_example.model

import com.google.gson.annotations.SerializedName

/**
 * data model
 * https://jsonplaceholder.typicode.com/posts
 * */
data class User(
    val userId: Int,
    val id: Int,
    @SerializedName("title") // if using different name
    val title: String,
    val body: String,
)
