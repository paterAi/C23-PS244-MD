package com.sayursehat.paterai.ui.market.product.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sayursehat.paterai.R
import com.sayursehat.paterai.databinding.ActivityDetailProductMarketBinding
import com.sayursehat.paterai.model.Vegetable
import com.sayursehat.paterai.utils.Utils

class DetailProductMarketActivity : AppCompatActivity() {

    var count = 1

    private lateinit var binding: ActivityDetailProductMarketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vegetable = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_VEGETABLE, Vegetable::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_VEGETABLE)
        }
        val price = "${Utils.convertToIDRFormat(vegetable?.price ?: 0)}/ ${vegetable?.weight}"
        binding.apply {
            Glide.with(this@DetailProductMarketActivity)
                .load(vegetable?.photoUrl)
                .into(imgVegetableDetail)

            tvNameVegetableDetail.text = vegetable?.name
            tvPriceVegetableDetail.text = price
            tvDesVegetableDetail.text = vegetable?.description

            btnDecVegetableDetail.setOnClickListener {
                if (count > 1){
                    count--
                    tvCountVegetableDetail.text = count.toString()
                }
            }

            btnIncVegetableDetail.setOnClickListener {
                count++
                tvCountVegetableDetail.text = count.toString()
            }
        }
    }

    companion object {
        const val EXTRA_VEGETABLE = "extra_vegetable"
    }
}