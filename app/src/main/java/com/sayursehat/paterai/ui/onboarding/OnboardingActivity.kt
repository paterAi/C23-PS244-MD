package com.sayursehat.paterai.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sayursehat.paterai.R
import com.sayursehat.paterai.databinding.ActivityOnboardingBinding
import com.sayursehat.paterai.service.local.OnboardingPreferences
import com.sayursehat.paterai.ui.ViewModelFactory
import com.sayursehat.paterai.ui.auth.AuthenticationActivity
import com.sayursehat.paterai.ui.main.dataStore

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = OnboardingPreferences.getInstance(dataStore)
        val factory = ViewModelFactory(pref)
        val viewModel: OnboardingViewModel by viewModels {
            factory
        }

        binding.btnOnboardingNext.setOnClickListener {
            viewModel.setOnboardingState(false)
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}