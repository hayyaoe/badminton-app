package com.hayyaoe.badmintonapp.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private const val DATA_STORE_NAME = "BadmintonApp"
class DataStoreManager (context: Context){

    private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)
    private val dataStore = context.dataStore

    companion object {
        val TOKEN_KEY = stringPreferencesKey("token_key")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            Log.d("DataStoreManager", "Token saved: $token")
        }
    }

    suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
            Log.d("DataStoreManager", "Email saved: $email")
        }
    }

    val getToken: Flow<String?> = dataStore.data.map { preferences ->
        Log.d("DataStoreManager", "Fetching Token: ${preferences[TOKEN_KEY]}")
        preferences[TOKEN_KEY]
    }

    val getEmail: Flow<String?> = dataStore.data.map { preferences ->
        Log.d("DataStoreManager", "Fetching Email: ${preferences[USER_EMAIL]}")
        preferences[USER_EMAIL]
    }
}