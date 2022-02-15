package com.pexels.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pexels.sample.databinding.ActivityMainBinding
import com.pexels.sample.photo.PhotosActivity
import com.pexels.sample.video.VideosActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.photosBtn.setOnClickListener {
            Intent(this, PhotosActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.videosBtn.setOnClickListener {
            Intent(this, VideosActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}