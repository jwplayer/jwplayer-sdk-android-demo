package com.jwplayer.opensourcedemo

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.cast.framework.CastContext
import com.jwplayer.opensourcedemo.MenuHelper.fillInMenu
import com.jwplayer.opensourcedemo.databinding.ActivityJwkotlinPlayerViewExampleBinding
import com.jwplayer.pub.api.JWPlayer
import com.jwplayer.pub.api.configuration.PlayerConfig
import com.jwplayer.pub.api.events.EventType
import com.jwplayer.pub.api.events.FullscreenEvent
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents
import java.util.concurrent.Executors

class JWKotlinPlayerViewExample : AppCompatActivity(), VideoPlayerEvents.OnFullscreenListener {

    private var mCastContext: CastContext? = null

    private lateinit var mPlayer: JWPlayer
    private lateinit var binding: ActivityJwkotlinPlayerViewExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        
        binding = ActivityJwkotlinPlayerViewExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar as the ActionBar
        setSupportActionBar(binding.toolbar)

        // Apply insets to AppBarLayout
        ViewCompat.setOnApplyWindowInsetsListener(binding.appBarLayout) { v, windowInsets ->
            val systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            windowInsets
        }

        // Apply insets to callback screen for navigation bar at bottom
        ViewCompat.setOnApplyWindowInsetsListener(binding.callbackScreen) { v, windowInsets ->
            val systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            windowInsets
        }

        binding.jwplayer.getPlayerAsync(this,this) {
            mPlayer = it
            // Handle hiding/showing of ActionBar
            mPlayer.addListener(EventType.FULLSCREEN, this)

            // Keep the screen on during playback
            KeepScreenOnHandler(mPlayer, window)

            // Event Logging
            binding.callbackScreen.registerListeners(mPlayer)

            // Load a media source
            val config = PlayerConfig.Builder()
                .playlistUrl("https://cdn.jwplayer.com/v2/media/1sc0kL2N?format=json")
                .build()
            mPlayer.setup(config)
        }

        // Get a reference to the CastContext from a Task
        val instanceTask =  CastContext.getSharedInstance(this, Executors.newSingleThreadExecutor());
        instanceTask.addOnCompleteListener {
            mCastContext = it.result
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        fillInMenu(menu, this, this)
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (!mPlayer.isInPictureInPictureMode) {
            val isFullscreen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
            mPlayer.setFullscreen(isFullscreen, true)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Exit fullscreen when the user pressed the Back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPlayer.fullscreen) {
                mPlayer.setFullscreen(false, true)
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onFullscreen(fullscreenEvent: FullscreenEvent) {
        // Hide/show the entire app bar when entering/exiting fullscreen
        binding.appBarLayout.visibility = if (fullscreenEvent.fullscreen) View.GONE else View.VISIBLE
    }
}