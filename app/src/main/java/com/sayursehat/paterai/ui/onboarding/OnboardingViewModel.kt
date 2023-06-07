package com.sayursehat.paterai.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayursehat.paterai.service.local.OnboardingPreferences
import kotlinx.coroutines.launch

class OnboardingViewModel(private val pref: OnboardingPreferences) : ViewModel() {

    fun setOnboardingState(state: Boolean) {
        viewModelScope.launch {
            pref.setOnboardingState(state)
        }
    }
}