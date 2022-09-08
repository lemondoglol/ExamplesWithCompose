package com.example.examplewithcompose.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

private const val MY_DATA_STORE = "MY_DATA_STORE"

// define keys
private object PreferencesKeys {
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val IS_ELIGIBLE = booleanPreferencesKey("is_eligible")
}

class PreferenceDataStoreRepositoryImpl @Inject constructor(
    context: Context,
    private val dataStore: DataStore<Preferences>,
) : PreferenceDataStoreRepository {
    // read data
    val usersFlow: Flow<User> = context.myDataStore.data
        .catch { exception ->
            // handle exception
            when (exception) {
                is IOException -> {}
                else -> {}
            }
        }.map { preference ->
        val firstName = preference[PreferencesKeys.FIRST_NAME] ?: "N/A FN"
        val lastName = preference[PreferencesKeys.LAST_NAME] ?: "N/A LN"
        val isEligible = preference[PreferencesKeys.IS_ELIGIBLE] ?: false

        User(
            firstName = firstName,
            lastName = lastName,
            isEligible = isEligible,
        )
    }

    // updating/writing data
    override suspend fun writeToPreferenceStore(): Unit = withContext(Dispatchers.IO) {
        dataStore.edit {
            it[PreferencesKeys.IS_ELIGIBLE] = true
        }
        Log.d("Tuna", "It works")
    }

}

private val Context.myDataStore by preferencesDataStore(
    name = MY_DATA_STORE
)

// reading data

data class User(
    val firstName: String,
    val lastName: String,
    val isEligible: Boolean,
)