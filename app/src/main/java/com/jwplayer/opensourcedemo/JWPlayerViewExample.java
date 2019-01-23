package com.jwplayer.opensourcedemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.cast.CastManager;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.configuration.SkinConfig;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.ads.AdSource;
import com.longtailvideo.jwplayer.media.ads.Advertising;
import com.longtailvideo.jwplayer.media.ads.ImaAdvertising;
import com.longtailvideo.jwplayer.media.playlists.MediaSource;
import com.longtailvideo.jwplayer.media.playlists.MediaType;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.ArrayList;
import java.util.List;


public class JWPlayerViewExample extends AppCompatActivity implements
		VideoPlayerEvents.OnFullscreenListener {

	/**
	 * Reference to the {@link JWPlayerView}
	 */
	private JWPlayerView mPlayerView;

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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jwplayerview);

		mPlayerView = findViewById(R.id.jwplayer);
		TextView outputTextView = findViewById(R.id.output);
		ScrollView scrollView = findViewById(R.id.scroll);
		mCoordinatorLayout = findViewById(R.id.activity_jwplayerview);

		// Setup JWPlayer
		setupJWPlayer();

		// Handle hiding/showing of ActionBar
		mPlayerView.addOnFullscreenListener(this);

		// Keep the screen on during playback
		new KeepScreenOnHandler(mPlayerView, getWindow());

		// Instantiate the JW Player event handler class
		new JWEventHandler(mPlayerView, outputTextView, scrollView);

		// Instantiate the JW Player Ad event handler class
		new JWAdEventHandler(mPlayerView, outputTextView, scrollView);

		// Get a reference to the CastManager
		mCastManager = CastManager.getInstance();
	}


	private void setupJWPlayer() {

//		List<PlaylistItem> playlistItemList = createMediaSourcePlaylist();
		List<PlaylistItem> playlistItemList = createPlaylist();

		String url = "http://podcast.20min-tv.ch/podcast/20min/640845.mp4";

		// Ima Tag Example
		ImaAdvertising imaAdvertising = getImaAd();

		// VAST Tag Example
		Advertising vastAdvertising = getVastAd();

		SkinConfig skinConfig = new SkinConfig.Builder()
				.url("https://s3.amazonaws.com/qa.jwplayer.com/~hyunjoo/css/showcontrolsalways.css")
				.name("showcontrolsalways")
				.build();

		PlayerConfig config = new PlayerConfig.Builder()
				.file(url)
//				.playlist(playlistItemList)
				.autostart(true)
				.preload(true)
				.allowCrossProtocolRedirects(true)
//				.skinConfig(skinConfig)
//				.advertising(imaAdvertising)
//				.advertising(vastAdvertising)
				.build();

		mPlayerView.setup(config);
	}

	/*
	* VAST AD Example
	* */
	private Advertising getVastAd(){
		List<AdBreak> adbreaklist = new ArrayList<>();
		String adtag = "";
		adbreaklist.add(new AdBreak("pre", AdSource.VAST, adtag));
		return new Advertising(AdSource.VAST,adbreaklist);
	}

	/*
	* IMA Ad Example*/
	private ImaAdvertising getImaAd(){
		List<AdBreak> adbreakList = new ArrayList<>();

		String imaurl = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator=";

		adbreakList.add(new AdBreak("pre", AdSource.IMA, imaurl));

		ImaSdkSettings settings = ImaSdkFactory.getInstance().createImaSdkSettings();
		settings.setEnableOmidExperimentally(true);

		return new ImaAdvertising(adbreakList, settings);
	}

	/**
	 * MediaSource Playlist Example
	 * */
	private List<PlaylistItem> createMediaSourcePlaylist() {
		List<MediaSource> mediaSourceList = new ArrayList<>();
		List<PlaylistItem> playlistItemList = new ArrayList<>();

		String hls = "https://cdn.jwplayer.com/manifests/jumBvHdL.m3u8";

		MediaSource ms = new MediaSource.Builder()
				.file(hls)
				.type(MediaType.HLS)
				.build();
		mediaSourceList.add(ms);

		PlaylistItem item = new PlaylistItem.Builder()
				.sources(mediaSourceList)
				.build();

		playlistItemList.add(item);

		return playlistItemList;
	}


	/*
	* Create a Playlist Example
	* */
	private List<PlaylistItem> createPlaylist() {
		List<PlaylistItem> playlistItemList = new ArrayList<>();

		String[] playlist = {
				"https://cdn.jwplayer.com/manifests/jumBvHdL.m3u8",
				"http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8",
				"http://content.jwplatform.com/videos/tkM1zvBq-cIp6U8lV.mp4",
				"https://cdn.jwplayer.com/manifests/jumBvHdL.m3u8",
				"http://content.jwplatform.com/videos/RDn7eg0o-cIp6U8lV.mp4",
				"http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8",
				"http://content.jwplatform.com/videos/i3q4gcBi-cIp6U8lV.mp4",
				"http://content.jwplatform.com/videos/iLwfYW2S-cIp6U8lV.mp4",
				"http://content.jwplatform.com/videos/8TbJTFy5-cIp6U8lV.mp4",
		};

		for(String each : playlist){
			playlistItemList.add(new PlaylistItem(each));
		}

		return playlistItemList;
	}

	/*
	 * In landscape mode, set to fullscreen or if the user clicks the fullscreen button
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// Set fullscreen when the device is rotated to landscape
		mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onResume() {
		// Let JW Player know that the app has returned from the background
		super.onResume();
		mPlayerView.onResume();
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
	 * @param fullscreenEvent true if the player is fullscreen
	 */
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

		// When going to Fullscreen we want to set fitsSystemWindows="false"
		mCoordinatorLayout.setFitsSystemWindows(!fullscreenEvent.getFullscreen());
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
