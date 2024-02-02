package com.jwplayer.opensourcedemo

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
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
        binding = ActivityJwkotlinPlayerViewExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val actionBar = supportActionBar
        if (actionBar != null) {
            if (fullscreenEvent.fullscreen) {
                actionBar.hide()
            } else {
                actionBar.show()
            }
        }
    }
}