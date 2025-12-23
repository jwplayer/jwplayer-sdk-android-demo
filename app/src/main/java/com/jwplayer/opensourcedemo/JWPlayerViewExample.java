package com.jwplayer.opensourcedemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.material.appbar.MaterialToolbar;
import com.jwplayer.pub.api.JWPlayer;
import com.jwplayer.pub.api.configuration.PlayerConfig;
import com.jwplayer.pub.api.events.EventType;
import com.jwplayer.pub.api.events.FullscreenEvent;
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents;
import com.jwplayer.pub.api.license.LicenseUtil;
import com.jwplayer.pub.view.JWPlayerView;


public class JWPlayerViewExample extends AppCompatActivity
		implements VideoPlayerEvents.OnFullscreenListener {

	private JWPlayerView mPlayerView;

	private CastContext mCastContext;

	private CallbackScreen mCallbackScreen;
	private JWPlayer mPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jwplayerview);

		// Set up the toolbar as the ActionBar
		MaterialToolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mCallbackScreen = findViewById(R.id.callback_screen);

		// Apply insets to AppBarLayout - it will pad for status bar
		View appBarLayout = findViewById(R.id.app_bar_layout);
		ViewCompat.setOnApplyWindowInsetsListener(appBarLayout, (v, windowInsets) -> {
			Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
			return windowInsets;
		});

		// Apply insets to callback screen for navigation bar at bottom
		ViewCompat.setOnApplyWindowInsetsListener(mCallbackScreen, (v, windowInsets) -> {
			Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(0, 0, 0, systemBars.bottom);
			return windowInsets;
		});

		// TODO: Add your license key
		new LicenseUtil().setLicenseKey(this, YOUR_LICENSE_KEY );
		mPlayerView = findViewById(R.id.jwplayer);
		mPlayer = mPlayerView.getPlayer(this);


		// Handle hiding/showing of ActionBar
		mPlayer.addListener(EventType.FULLSCREEN, this);

		// Keep the screen on during playback
		new KeepScreenOnHandler(mPlayer, getWindow());

		// Event Logging
		mCallbackScreen.registerListeners(mPlayer);

		// Load a media source
		PlayerConfig config = new PlayerConfig.Builder()
				.playlistUrl("https://cdn.jwplayer.com/v2/media/1sc0kL2N?format=json")
				.build();

		mPlayer.setup(config);

		// Get a reference to the CastContext
		mCastContext = CastContext.getSharedInstance(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuHelper.INSTANCE.fillInMenu(menu, this, this);
		return true;
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (!mPlayer.isInPictureInPictureMode()) {
			final boolean isFullscreen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
			mPlayer.setFullscreen(isFullscreen, true);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Exit fullscreen when the user pressed the Back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mPlayer.getFullscreen()) {
				mPlayer.setFullscreen(false, true);
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onFullscreen(FullscreenEvent fullscreenEvent) {
		// Hide/show the entire app bar when entering/exiting fullscreen
		View appBarLayout = findViewById(R.id.app_bar_layout);
		if (appBarLayout != null) {
			appBarLayout.setVisibility(fullscreenEvent.getFullscreen() ? View.GONE : View.VISIBLE);
		}
	}
}
