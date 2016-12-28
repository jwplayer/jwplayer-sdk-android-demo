package com.jwplayer.opensourcedemo;

import android.view.Window;
import android.view.WindowManager;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.core.PlayerState;
import com.longtailvideo.jwplayer.events.AdCompleteEvent;
import com.longtailvideo.jwplayer.events.AdPauseEvent;
import com.longtailvideo.jwplayer.events.AdPlayEvent;
import com.longtailvideo.jwplayer.events.AdSkippedEvent;
import com.longtailvideo.jwplayer.events.ErrorEvent;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

/**
 * Sets the FLAG_KEEP_SCREEN_ON flag during playback - disables it when playback is stopped
 */
public class KeepScreenOnHandler implements VideoPlayerEvents.OnPlayListener,
        VideoPlayerEvents.OnPauseListener,
        VideoPlayerEvents.OnCompleteListener,
        VideoPlayerEvents.OnErrorListenerV2,
        AdvertisingEvents.OnAdPlayListenerV2,
        AdvertisingEvents.OnAdPauseListenerV2,
        AdvertisingEvents.OnAdCompleteListenerV2,
        AdvertisingEvents.OnAdSkippedListenerV2,
        AdvertisingEvents.OnAdErrorListener {

    /**
     * The application window
     */
    private Window mWindow;

    public KeepScreenOnHandler(JWPlayerView jwPlayerView, Window window) {
        jwPlayerView.addOnPlayListener(this);
        jwPlayerView.addOnPauseListener(this);
        jwPlayerView.addOnCompleteListener(this);
        jwPlayerView.addOnErrorListener(this);
        jwPlayerView.addOnAdPlayListener(this);
        jwPlayerView.addOnAdPauseListener(this);
        jwPlayerView.addOnAdCompleteListener(this);
        jwPlayerView.addOnAdErrorListener(this);
        mWindow = window;
    }

    private void updateWakeLock(boolean enable) {
        if (enable) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public void onPlay(PlayerState oldState) {
        updateWakeLock(true);
    }

    @Override
    public void onPause(PlayerState oldState) {
        updateWakeLock(false);
    }

    @Override
    public void onComplete() {
        updateWakeLock(false);
    }

    @Override
    public void onAdError(String tag, String message) {
        updateWakeLock(false);
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        updateWakeLock(true);
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        updateWakeLock(false);
    }
}
