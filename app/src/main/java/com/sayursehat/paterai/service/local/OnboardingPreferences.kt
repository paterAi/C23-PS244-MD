package com.sayursehat.paterai.service.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnboardingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getOnboardingState(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[STATE_KEY] ?: true
        }
    }

    suspend fun setOnboardingState(state: Boolean) {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = state
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: OnboardingPreferences? = null

        private val STATE_KEY = booleanPreferencesKey("state")

        fun getInstance(dataStore: DataStore<Preferences>): OnboardingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = OnboardingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}