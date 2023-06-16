package com.sayursehat.paterai.ui.market

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.sayursehat.paterai.R
import com.sayursehat.paterai.databinding.ActivityMarketBinding
import com.sayursehat.paterai.ui.market.camera.CameraActivity
import com.sayursehat.paterai.ui.market.product.detail.DetailProductMarketActivity

class MarketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavMarket

        val navController = findNavController(R.id.nav_host_fragment_activity_market)

        val radius = resources.getDimension(R.dimen.corner_radius_bottom_bar)
        val bottomBarBackground = binding.bottomNavAppbar.background as MaterialShapeDrawable

        bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
            .toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .build()

        navView.setupWithNavController(navController)
        binding.fabScan.setOnClickListener {startCameraX()}
    }

    private fun startCameraX(){
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }
}