package com.jwplayer.opensourcedemo;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.material.appbar.MaterialToolbar;
import com.jwplayer.pub.api.JWPlayer;
import com.jwplayer.pub.api.JWPlayerSupportFragment;
import com.jwplayer.pub.api.configuration.PlayerConfig;


public class JWPlayerFragmentExample extends AppCompatActivity {


	private JWPlayerSupportFragment mPlayerFragment;

	private JWPlayer mPlayer;

	private CallbackScreen mCallbackScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_jwplayerfragment);

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

		// Construct a new JWPlayerSupportFragment (since we're using AppCompatActivity)
		mPlayerFragment = JWPlayerSupportFragment.newInstance(new PlayerConfig.Builder()
																	  .file("https://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8")
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
		mPlayer = mPlayerFragment.getPlayer();

		// Keep the screen on during playback
		new KeepScreenOnHandler(mPlayer, getWindow());

		// Event Logging
		mCallbackScreen.registerListeners(mPlayer);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_jwplayerfragment, menu);

		// Register the MediaRouterButton
		CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), menu,
												R.id.media_route_menu_item);

		MenuHelper.INSTANCE.fillInMenu(menu, this, this);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
