package com.sayursehat.paterai.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.sayursehat.paterai.R
import com.sayursehat.paterai.ui.market.MarketActivity
import com.sayursehat.paterai.ui.market.camera.CameraMarketActivity
import com.sayursehat.paterai.ui.market.camera.CameraResultMarketActivity
import com.sayursehat.paterai.ui.market.camera.TestModelActivity
import com.sayursehat.paterai.ui.market.cart.CartMarketActivity
import com.sayursehat.paterai.ui.market.product.ProductMarketFragment
import com.sayursehat.paterai.ui.market.product.detail.DetailProductMarketActivity
import com.sayursehat.paterai.ui.welcome.WelcomeActivity

class MainActivity() : AppCompatActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intent = Intent(this, CameraResultMarketActivity::class.java)
//        startActivity(intent)
//        finish()

//        val intent = Intent(this, TestModelActivity::class.java)
//        startActivity(intent)
//        finish()

//        val intent = Intent(this, MarketActivity::class.java)
//        startActivity(intent)
//        finish()

//        val intent = Intent(this, WelcomeActivity::class.java)
//        startActivity(intent)
//        finish()

//        val intent = Intent(this, CartMarketActivity::class.java)
//        startActivity(intent)
//        finish()
//
        val intent = Intent(this, CameraMarketActivity::class.java)
        startActivity(intent)
        finish()
//
//        val intent = Intent(this, DetailProductMarketActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}