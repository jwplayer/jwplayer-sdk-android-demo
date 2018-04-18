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
import android.widget.ScrollView;
import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.cast.CastManager;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.ads.Ad;
import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.ads.AdSource;
import com.longtailvideo.jwplayer.media.ads.VMAPAdvertising;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.ArrayList;
import java.util.List;

public class JWPlayerViewExample extends AppCompatActivity implements VideoPlayerEvents.OnFullscreenListener{

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

        ScrollView sv = (ScrollView) findViewById(R.id.scroll);

        TextView outputTextView = (TextView)findViewById(R.id.output);

		mPlayerView = (JWPlayerView)findViewById(R.id.jwplayer);

		setupConfig();

        mEventHandler = new JWEventHandler(mPlayerView, outputTextView,sv);

		mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.activity_jwplayerview);

		// Keep the screen on during playback
		new KeepScreenOnHandler(mPlayerView, getWindow());

		// Get a reference to the CastManager
		mCastManager = CastManager.getInstance();

	}
    String vast2URL = "https://s3.amazonaws.com/demo.jwplayer.com/static-tag/preroll.xml";
    String videoURL = "http://cdn.jwplayer.com/v2/media/jumBvHdL";

    private void setupConfig(){
        
        // Create a playlist
        List<PlaylistItem> playlist = new ArrayList<PlaylistItem>();
        
        // Create video
        PlaylistItem playlistItem = new PlaylistItem(videoURL);
        
        // Set ad tag with macros
        Ad ad = new Ad(AdSource.VAST, vast2URL);

        // Set the ad break offset
        // TODO: Change ad tags here, below
        AdBreak adBreak = new AdBreak("pre", ad);
        List<AdBreak> adSchedule = new ArrayList<AdBreak>();
        adSchedule.add(adBreak);

        // Set the ad schedule to your video
        playlistItem.setAdSchedule(adSchedule);

        // Add video to the playlist
        playlist.add(playlistItem);
        
        // Create your player config
        PlayerConfig playerConfig = new PlayerConfig.Builder()
            .playlist(playlist)
            .preload(true)
            .autostart(true)
            .build();
        
        // Setup your player with the config
        mPlayerView.setup(playerConfig);
    }

    public void print(String activity){
		Log.i(MEASURE_TAG,  Time.currentTime() + " on" + activity +" (\"JW\")");
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
        print("Start");
    }

    @Override
	protected void onResume() {
		// Let JW Player know that the app has returned from the background
        super.onResume();
        print("Resume");
        mPlayerView.onResume();
	}

	@Override
	protected void onPause() {
		// Let JW Player know that the app is going to the background
        mPlayerView.onPause();
        print("Pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        print("Stop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        print("Restart");
        super.onRestart();
    }

    @Override
	protected void onDestroy() {
		// Let JW Player know that the app is being destroyed
        mPlayerView.onDestroy();
        print("Destroy");
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

}
