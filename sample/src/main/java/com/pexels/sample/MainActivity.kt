package com.pexels.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.pexels.android.Pexels
import com.pexels.android.PexelsClient
import com.pexels.android.model.param.Color
import com.pexels.android.model.param.Locale
import com.pexels.android.model.param.Orientation
import com.pexels.android.model.param.Size
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {
    private val apiKey: String = "563492ad6f917000010000013b68d3e217514ab7a4185f3a741ba48c"
    private val pexelsClient: PexelsClient =
        Pexels.createClient(apiKey = apiKey)

    companion object {
        @JvmStatic
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            try {
                val photosResponse = pexelsClient.searchForPhotos(
                    query = "Space",
                    orientation = Orientation.SQUARE,
                    size = Size.MEDIUM,
                    color = Color.GREEN, // You can also use Hexadecimal values like #121212
                    locale = Locale.EN_US,
                    page = 1, // Try changing it to below 1
                    perPage = 15, // Try changing it to below 15 or above 80
                )
                Log.d(TAG, "Start")
                photosResponse.photos.forEachIndexed { i, photo ->
                    Log.d(TAG, "PhotoId #$i: ${photo.id}")
                }
                Log.d(TAG, "Done")
            } catch (e: IllegalArgumentException) {
                Log.d(TAG, "Parameter validation exception", e)
            } catch (e: HttpException) {
                Log.d(TAG, "Server response is not success exception", e)
            } catch (e: Exception) {
                Log.d(TAG, "Network Exception", e)
            }
        }
    }
}