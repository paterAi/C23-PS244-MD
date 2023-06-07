package com.sayursehat.paterai.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sayursehat.paterai.R
import com.sayursehat.paterai.service.local.OnboardingPreferences
import com.sayursehat.paterai.ui.ViewModelFactory
import com.sayursehat.paterai.ui.auth.AuthenticationActivity
import com.sayursehat.paterai.ui.market.MarketActivity
import com.sayursehat.paterai.ui.onboarding.OnboardingActivity

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "onboarding")

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val pref = OnboardingPreferences.getInstance(dataStore)
        val factory = ViewModelFactory(pref)
        viewModel = ViewModelProvider(
            this, factory
        )[MainViewModel::class.java]

        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        viewModel.getOnboardingState().observe(this) { state ->
            if (state) {
                val intent = Intent(this, OnboardingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                if (currentUser != null) {
                    val intent = Intent(this, MarketActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, AuthenticationActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}