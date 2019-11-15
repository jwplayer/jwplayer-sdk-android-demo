package com.jwplayer.opensourcedemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class JWPlayerViewExample extends AppCompatActivity
		implements VideoPlayerEvents.OnFullscreenListener {

	private JWPlayerView mPlayerView;

	private CastContext mCastContext;

	private CallbackScreen mCallbackScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jwplayerview);

		mPlayerView = findViewById(R.id.jwplayer);


		// Handle hiding/showing of ActionBar
		mPlayerView.addOnFullscreenListener(this);

		// Keep the screen on during playback
		new KeepScreenOnHandler(mPlayerView, getWindow());

		// Event Logging
		mCallbackScreen = findViewById(R.id.callback_screen);
		mCallbackScreen.registerListeners(mPlayerView);

		// Load a media source
		PlaylistItem pi = new PlaylistItem.Builder()
				.file("https://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8")
				.title("BipBop")
				.description("A video player testing video.")
				.build();

		mPlayerView.load(pi);

		// Get a reference to the CastContext
		mCastContext = CastContext.getSharedInstance(this);


	}


	@Override
	protected void onStart() {
		super.onStart();
		mPlayerView.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mPlayerView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mPlayerView.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mPlayerView.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPlayerView.onDestroy();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// Set fullscreen when the device is rotated to landscape
		mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE,
								  true);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Exit fullscreen when the user pressed the Back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mPlayerView.getFullscreen()) {
				mPlayerView.setFullscreen(false, true);
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onFullscreen(FullscreenEvent fullscreenEvent) {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			if (fullscreenEvent.getFullscreen()) {
				actionBar.hide();
			} else {
				actionBar.show();
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_jwplayerview, menu);

		// Register the MediaRouterButton
		CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), menu,
												R.id.media_route_menu_item);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.switch_to_fragment:
				Intent i = new Intent(this, JWPlayerFragmentExample.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
