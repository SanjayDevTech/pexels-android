package com.pexels.sample.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pexels.sample.R
import com.pexels.sample.databinding.ActivityPhotosBinding
import com.pexels.sample.databinding.ActivityVideosBinding

class VideosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideosBinding

    private lateinit var navController: NavController

    companion object {
        @JvmStatic
        private val TAG = VideosActivity::class.java.simpleName
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(
            navController, AppBarConfiguration(
                setOf(R.id.popularVideos, R.id.searchVideos),
            )
        )
        binding.bottomNavView.setupWithNavController(navController)
    }
}