package com.sayursehat.paterai.ui.market.camera

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.sayursehat.paterai.R
import com.sayursehat.paterai.databinding.ActivityCameraMarketBinding
import com.sayursehat.paterai.databinding.ActivityCameraResultMarketBinding
import java.util.concurrent.ExecutorService

class CameraResultMarketActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCameraResultMarketBinding
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCameraResultMarketBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val imageUri = getIntent().getStringExtra("imageUri")

        val image = Uri.parse(imageUri)
        viewBinding.imgCaptureResult.setImageURI(image)

        viewBinding.btnBackCamera.setOnClickListener {
            finish()
        }

        viewBinding.btnRetry.setOnClickListener{
            finish()
        }

        viewBinding.btnOk.setOnClickListener{
            viewBinding.btnBackCamera.isEnabled = true
            viewBinding.btnBackCamera.visibility = View.VISIBLE
            viewBinding.tvCaptureResult.visibility = View.VISIBLE

            viewBinding.btnRetry.isEnabled = false
            viewBinding.btnRetry.visibility = View.INVISIBLE
            viewBinding.btnOk.isEnabled = false
            viewBinding.btnOk.visibility = View.INVISIBLE
        }
    }
}