package com.sayursehat.paterai.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayursehat.paterai.service.local.OnboardingPreferences
import com.sayursehat.paterai.ui.main.MainViewModel
import com.sayursehat.paterai.ui.onboarding.OnboardingViewModel

class ViewModelFactory(private val pref: OnboardingPreferences) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        } else if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            return OnboardingViewModel(pref) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}