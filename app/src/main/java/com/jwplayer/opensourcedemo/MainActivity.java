package com.jwplayer.opensourcedemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

public class MainActivity extends ActionBarActivity implements VideoPlayerEvents.OnFullscreenListener {

	/**
	 * Reference to the JW Player View
	 */
	JWPlayerView mPlayerView;

	/**
	 * An instance of our event handling class
	 */
	private JWEventHandler mEventHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPlayerView = (JWPlayerView)findViewById(R.id.jwplayer);
		TextView outputTextView = (TextView)findViewById(R.id.output);

		// Handle hiding/showing of ActionBar
		mPlayerView.addOnFullscreenListener(this);

		// Instantiate the JW Player event handler class
		mEventHandler = new JWEventHandler(outputTextView);

		// Subscribe to all JW Player events
		mPlayerView.addOnSetupErrorListener(mEventHandler);
		mPlayerView.addOnPlaylistListener(mEventHandler);
		mPlayerView.addOnPlaylistItemListener(mEventHandler);
		mPlayerView.addOnPlayListener(mEventHandler);
		mPlayerView.addOnPauseListener(mEventHandler);
		mPlayerView.addOnBufferListener(mEventHandler);
		mPlayerView.addOnIdleListener(mEventHandler);
		mPlayerView.addOnErrorListener(mEventHandler);
		mPlayerView.addOnSeekListener(mEventHandler);
		mPlayerView.addOnTimeListener(mEventHandler);
		mPlayerView.addOnFullscreenListener(mEventHandler);
		mPlayerView.addOnQualityLevelsListener(mEventHandler);
		mPlayerView.addOnQualityChangeListener(mEventHandler);
		mPlayerView.addOnCaptionsListListener(mEventHandler);
		mPlayerView.addOnCaptionsChangeListener(mEventHandler);
		mPlayerView.addOnAdClickListener(mEventHandler);
		mPlayerView.addOnAdCompleteListener(mEventHandler);
		mPlayerView.addOnAdSkippedListener(mEventHandler);
		mPlayerView.addOnAdErrorListener(mEventHandler);
		mPlayerView.addOnAdImpressionListener(mEventHandler);
		mPlayerView.addOnAdTimeListener(mEventHandler);
		mPlayerView.addOnAdPauseListener(mEventHandler);
		mPlayerView.addOnAdPlayListener(mEventHandler);
		mPlayerView.addOnMetaListener(mEventHandler);
		mPlayerView.addOnPlaylistCompleteListener(mEventHandler);
		mPlayerView.addOnCompleteListener(mEventHandler);
		mPlayerView.addOnBeforePlayListener(mEventHandler);
		mPlayerView.addOnBeforeCompleteListener(mEventHandler);

		// Load a media source
		PlaylistItem pi = new PlaylistItem("http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8");
		mPlayerView.load(pi);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// Set fullscreen when the device is rotated to landscape
		mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onResume() {
		// Let JW Player know that the app has returned from the background
		mPlayerView.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// Let JW Player know that the app is going to the background
		mPlayerView.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// Let JW Player know that the app is being destroyed
		mPlayerView.onDestroy();
		super.onDestroy();
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

	/**
	 * Handles JW Player going to and returning from fullscreen by hiding the ActionBar
	 *
	 * @param fullscreen true if the player is fullscreen
	 */
	@Override
	public void onFullscreen(boolean fullscreen) {
		if (fullscreen) {
			getSupportActionBar().hide();
		} else {
			getSupportActionBar().show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
