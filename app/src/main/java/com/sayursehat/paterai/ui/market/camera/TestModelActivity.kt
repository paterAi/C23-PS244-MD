package com.sayursehat.paterai.ui.market.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.modeldownloader.CustomModel
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import com.sayursehat.paterai.R
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TestModelActivity : AppCompatActivity() {
    private var interpreter: Interpreter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_model)

        val drawableId = R.drawable.img_papaya
        val imageInput = BitmapFactory.decodeResource(resources, drawableId)

        val conditions = CustomModelDownloadConditions.Builder()
            .requireWifi()  // Also possible: .requireCharging() and .requireDeviceIdle()
            .build()
        FirebaseModelDownloader.getInstance()
            .getModel(
                "paterai_v1", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
                conditions
            )
            .addOnSuccessListener { model: CustomModel? ->
                // Download complete. Depending on your app, you could enable the ML
                // feature, or switch from the local model to the remote model, etc.

                // The CustomModel object contains the local path of the model file,
                // which you can use to instantiate a TensorFlow Lite interpreter.
                val modelFile = model?.file
                if (modelFile != null) {
                    interpreter = Interpreter(modelFile)
                }

                try {
                    // model input
                    val bitmap = Bitmap.createScaledBitmap(imageInput, 224, 224, true)
                    val input = ByteBuffer.allocateDirect(224 * 224 * 3 * 4).order(ByteOrder.nativeOrder())
                    for (y in 0 until 224) {
                        for (x in 0 until 224) {
                            val px = bitmap.getPixel(x, y)

                            // Get channel values from the pixel value.
                            val r = Color.red(px)
                            val g = Color.green(px)
                            val b = Color.blue(px)

                            // Normalize channel values to [-1.0, 1.0]. This requirement depends on the model.
                            // For example, some models might require values to be normalized to the range
                            // [0.0, 1.0] instead.
                            val rf = (r - 127) / 255f
                            val gf = (g - 127) / 255f
                            val bf = (b - 127) / 255f

                            input.putFloat(rf)
                            input.putFloat(gf)
                            input.putFloat(bf)
                        }
                    }

                    val bufferSize = 1000 * java.lang.Float.SIZE / java.lang.Byte.SIZE
                    val modelOutput = ByteBuffer.allocateDirect(bufferSize).order(ByteOrder.nativeOrder())
                    interpreter?.run(input, modelOutput)

                    // model output
                    modelOutput.rewind()
                    val probabilities = modelOutput.asFloatBuffer()
                    try {
                        val reader = BufferedReader(
                            InputStreamReader(assets.open("vegetable_labels.txt"))
                        )
                        for (i in 0 until probabilities.capacity()) {
                            val label: String? = reader.readLine()
                            if (label != null) {
                                val probability = probabilities.get(i)
                                println("$label: $probability")
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

}