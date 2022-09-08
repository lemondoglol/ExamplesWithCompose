package com.example.examplewithcompose.data.repository

interface PreferenceDataStoreRepository {
    suspend fun writeToPreferenceStore()
}