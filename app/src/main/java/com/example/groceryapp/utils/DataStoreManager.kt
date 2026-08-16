package com.example.groceryapp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.groceryapp.domain.model.Completed
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object{
        private val COMPLETED_KEY = booleanPreferencesKey("onboarding_completed")
    }

    suspend fun saveOnboardingCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[COMPLETED_KEY] = completed
        }
    }

    fun getParamDataStore() =
        dataStore.data.map { preferences ->
            Completed(completed = preferences[COMPLETED_KEY] ?: false)
        }


}