package com.sayursehat.paterai.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sayursehat.paterai.service.local.OnboardingPreferences

class MainViewModel(private val pref: OnboardingPreferences) : ViewModel() {

    fun getOnboardingState(): LiveData<Boolean> = pref.getOnboardingState().asLiveData()
}