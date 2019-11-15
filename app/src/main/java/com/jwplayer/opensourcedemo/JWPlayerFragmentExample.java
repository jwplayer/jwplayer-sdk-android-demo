package com.jwplayer.opensourcedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.cast.framework.CastButtonFactory;
import com.longtailvideo.jwplayer.JWPlayerSupportFragment;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class JWPlayerFragmentExample extends AppCompatActivity {


	private JWPlayerSupportFragment mPlayerFragment;

	private JWPlayerView mPlayerView;

	private CallbackScreen mCallbackScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jwplayerfragment);

		// Construct a new JWPlayerSupportFragment (since we're using AppCompatActivity)
		mPlayerFragment = JWPlayerSupportFragment.newInstance(new PlayerConfig.Builder()
																	  .file("http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8")
																	  .build());

		// Attach the Fragment to our layout
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.fragment_container, mPlayerFragment);
		ft.commit();

		// Make sure all the pending fragment transactions have been completed, otherwise
		// mPlayerFragment.getPlayer() may return null
		fm.executePendingTransactions();

		// Get a reference to the JWPlayerView from the fragment
		mPlayerView = mPlayerFragment.getPlayer();

		// Keep the screen on during playback
		new KeepScreenOnHandler(mPlayerView, getWindow());

		// Event Logging
		mCallbackScreen = findViewById(R.id.callback_screen);
		mCallbackScreen.registerListeners(mPlayerView);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_jwplayerfragment, menu);

		// Register the MediaRouterButton
		CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), menu,
												R.id.media_route_menu_item);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.switch_to_view:
				Intent i = new Intent(this, JWPlayerViewExample.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
