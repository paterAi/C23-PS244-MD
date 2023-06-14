package com.sayursehat.paterai.ui.market.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sayursehat.paterai.R
import com.sayursehat.paterai.adapter.ListCartAdapter
import com.sayursehat.paterai.adapter.ListProductStoreAdapter
import com.sayursehat.paterai.databinding.ActivityCartMarketBinding
import com.sayursehat.paterai.model.Cart
import com.sayursehat.paterai.model.InitialDataDummy

class CartMarketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_market)
    }

}