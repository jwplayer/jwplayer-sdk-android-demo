package com.jwplayer.opensourcedemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.cast.CastManager;
import com.longtailvideo.jwplayer.core.PlayerState;
import com.longtailvideo.jwplayer.events.BufferChangeEvent;
import com.longtailvideo.jwplayer.events.ErrorEvent;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.audio.AudioTrack;
import com.longtailvideo.jwplayer.media.captions.Caption;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.List;

public class JWPlayerViewExample extends AppCompatActivity implements VideoPlayerEvents.OnFullscreenListener {

    private static final String MEASURE_TAG = "JWMeasure";

	/**
	 * Reference to the {@link JWPlayerView}
	 */
	private JWPlayerView mPlayerView;

	/**
	 * An instance of our event handling class
	 */
	private JWEventHandler mEventHandler;

	/**
	 * Reference to the {@link CastManager}
	 */
	private CastManager mCastManager;

	/**
	 * Stored instance of CoordinatorLayout
	 * http://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.html
	 */
	private CoordinatorLayout mCoordinatorLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onCreate: start");
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jwplayerview);
		mPlayerView = (JWPlayerView)findViewById(R.id.jwplayer);
		TextView outputTextView = (TextView)findViewById(R.id.output);

		mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_jwplayerview);

		// Handle hiding/showing of ActionBar
		mPlayerView.addOnFullscreenListener(this);

		// Keep the screen on during playback
		new KeepScreenOnHandler(mPlayerView, getWindow());

		// Instantiate the JW Player event handler class
		mEventHandler = new JWEventHandler(mPlayerView, outputTextView);

		// Load a media source
		PlaylistItem pi = new PlaylistItem.Builder()
				.file("https://origin1-ams-bitgravity.sotalcloud.com/content/private/r862350/824321f549d17c9e3a4c11d7afc1ceaf0fc5f81f/playlist.m3u8")
				.title("Test Video")
				.description("A video player testing video.")
				.build();
		mPlayerView.load(pi);
//		mPlayerView.play();

        mPlayerView.addOnAudioTrackChangedListener(playerCallbacks);
        mPlayerView.addOnAudioTracksListener(playerCallbacks);
        mPlayerView.addOnBufferListener(playerCallbacks);
        mPlayerView.addOnBufferChangeListener(playerCallbacks);
        mPlayerView.addOnErrorListener(playerCallbacks);
        mPlayerView.addOnIdleListener(playerCallbacks);
        mPlayerView.addOnPlayListener(playerCallbacks);
        mPlayerView.addOnPauseListener(playerCallbacks);
        mPlayerView.addOnSeekedListener(playerCallbacks);
        mPlayerView.addOnSeekedListener(playerCallbacks);
        mPlayerView.addOnCaptionsChangedListener(playerCallbacks);
        mPlayerView.addOnCaptionsListListener(playerCallbacks);
        mPlayerView.addOnCompleteListener(playerCallbacks);
        mPlayerView.addOnTimeListener(playerCallbacks);
        mPlayerView.addOnSetupErrorListener(playerCallbacks);

		// !!! Are there way to start buffering playlist item in this place?

        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onCreate: finish");
        mEventHandler.onCreateFinish();
		// Get a reference to the CastManager
		mCastManager = CastManager.getInstance();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// Set fullscreen when the device is rotated to landscape
		mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
		super.onConfigurationChanged(newConfig);
	}

    @Override
    protected void onStart() {
        super.onStart();
        mEventHandler.onStart();
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onStart");
    }

    @Override
	protected void onResume() {
		// Let JW Player know that the app has returned from the background
        super.onResume();
        mEventHandler.onResume();
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onResume");
        mPlayerView.onResume();

		mPlayerView.play();
	}

	@Override
	protected void onPause() {
		// Let JW Player know that the app is going to the background
        mPlayerView.onPause();
        super.onPause();
        mEventHandler.onPause();
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onPause");
    }

    @Override
    protected void onStop() {
	    mEventHandler.onStop();
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onRestart");
        super.onRestart();
    }

    @Override
	protected void onDestroy() {
		// Let JW Player know that the app is being destroyed
        mPlayerView.onDestroy();
        mEventHandler.onDestroy();
        super.onDestroy();
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onDestroy");
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
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			if (fullscreen) {
				actionBar.hide();
			} else {
				actionBar.show();
			}
		}

		// When going to Fullscreen we want to set fitsSystemWindows="false"
		mCoordinatorLayout.setFitsSystemWindows(!fullscreen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_jwplayerview, menu);
		// Register the MediaRouterButton on the JW Player SDK
		mCastManager.addMediaRouterButton(menu, R.id.media_route_menu_item);
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


	private final JWPlayerCallbacks playerCallbacks = new JWPlayerCallbacks();

    private class JWPlayerCallbacks implements VideoPlayerEvents.OnCompleteListener,
            VideoPlayerEvents.OnBufferListener,
            VideoPlayerEvents.OnBufferChangeListener,
            VideoPlayerEvents.OnErrorListenerV2,
            VideoPlayerEvents.OnIdleListener,
            VideoPlayerEvents.OnPauseListener,
            VideoPlayerEvents.OnPlayListener,
            VideoPlayerEvents.OnSeekListener,
            VideoPlayerEvents.OnSeekedListener,
            VideoPlayerEvents.OnAudioTracksListener,
            VideoPlayerEvents.OnCaptionsListListener,
            VideoPlayerEvents.OnAudioTrackChangedListener,
            VideoPlayerEvents.OnCaptionsChangedListener,
            VideoPlayerEvents.OnTimeListener,
            VideoPlayerEvents.OnDisplayClickListener, VideoPlayerEvents.OnSetupErrorListener {

        @Override
        public void onBuffer(PlayerState oldState) {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onBuffer; oldState=" + String.valueOf(oldState));
        }

        @Override
        public void onComplete() {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onComplete");
        }

        @Override
        public void onError(ErrorEvent errorEvent) {
            Exception exception = errorEvent.getException();
            Log.e(MEASURE_TAG, "" + Time.currentTime() + " JW onError: " + errorEvent.getMessage(), exception);
        }

        @Override
        public void onIdle(PlayerState oldState) {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onIdle; oldState=" + String.valueOf(oldState));
        }

        @Override
        public void onPause(PlayerState oldState) {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onPause; oldState=" + String.valueOf(oldState));
        }

        @Override
        public void onPlay(PlayerState oldState) {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onPlay; oldState=" + String.valueOf(oldState));
        }

        @Override
        public void onSeek(int position, int offset) {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onSeek position=" + position + " offset" + offset);
        }

        @Override
        public void onSeeked() {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onSeeked");
        }

        @Override
        public void onAudioTrackChanged(int i) {

        }

        @Override
        public void onAudioTracks(List<AudioTrack> list) {

        }

        @Override
        public void onCaptionsChanged(int i, List<Caption> list) {

        }

        @Override
        public void onCaptionsList(List<Caption> list) {

        }

        @Override
        public void onTime(long position, long duration) {
//            Log.i(TAG, "onTime position=" + position + " duration" + duration);

        }

        @Override
        public void onDisplayClick() {

        }

        @Override
        public void onBufferChange(BufferChangeEvent bufferChangeEvent) {
            Log.i(MEASURE_TAG, "" + Time.currentTime() + " JW onBufferChange; percent=" + bufferChangeEvent.getBufferPercent() +
                    " position=" + bufferChangeEvent.getPosition() +
                    " duration=" + bufferChangeEvent.getDuration()
            );
        }

        @Override
        public void onSetupError(String s) {
            Log.e(MEASURE_TAG, "" + Time.currentTime() + " JW onSetupError:" + String.valueOf(s));
        }
    }
}
