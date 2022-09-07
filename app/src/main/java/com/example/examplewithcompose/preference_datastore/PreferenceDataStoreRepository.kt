package com.example.examplewithcompose.preference_datastore

import android.content.Context
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

private const val MY_DATA_STORE = "MY_DATA_STORE"

// define keys
private object PreferencesKeys {
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val IS_ELIGIBLE = booleanPreferencesKey("is_eligible")
}

class PreferenceDataStoreRepository(
    context: Context,
    private val dataStore: DataStore<Preferences>,
) {
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
    suspend fun writeToPreferenceStore() = withContext(Dispatchers.IO) {
        dataStore.edit {
            it[PreferencesKeys.IS_ELIGIBLE] = true
        }
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