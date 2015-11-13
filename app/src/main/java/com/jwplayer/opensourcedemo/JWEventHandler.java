package com.jwplayer.opensourcedemo;

import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.core.PlayerState;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.adaptive.QualityLevel;
import com.longtailvideo.jwplayer.media.audio.AudioTrack;
import com.longtailvideo.jwplayer.media.captions.Caption;
import com.longtailvideo.jwplayer.media.meta.Metadata;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.List;

/**
 * Outputs all JW Player Events to logging, with the exception of time events.
 */
public class JWEventHandler implements VideoPlayerEvents.OnSetupErrorListener,
		VideoPlayerEvents.OnPlaylistListener,
		VideoPlayerEvents.OnPlaylistItemListener,
		VideoPlayerEvents.OnPlayListener,
		VideoPlayerEvents.OnPauseListener,
		VideoPlayerEvents.OnBufferListener,
		VideoPlayerEvents.OnIdleListener,
		VideoPlayerEvents.OnErrorListener,
		VideoPlayerEvents.OnSeekListener,
		VideoPlayerEvents.OnTimeListener,
		VideoPlayerEvents.OnFullscreenListener,
		VideoPlayerEvents.OnQualityLevelsListener,
		VideoPlayerEvents.OnQualityChangeListener,
		VideoPlayerEvents.OnAudioTracksListener,
		VideoPlayerEvents.OnAudioTrackChangeListener,
		VideoPlayerEvents.OnCaptionsListListener,
		VideoPlayerEvents.OnCaptionsChangeListener,
		VideoPlayerEvents.OnMetaListener,
		VideoPlayerEvents.OnPlaylistCompleteListener,
		VideoPlayerEvents.OnCompleteListener,
		AdvertisingEvents.OnAdClickListener,
		AdvertisingEvents.OnAdCompleteListener,
		AdvertisingEvents.OnAdSkippedListener,
		AdvertisingEvents.OnAdErrorListener,
		AdvertisingEvents.OnAdImpressionListener,
		AdvertisingEvents.OnAdTimeListener,
		AdvertisingEvents.OnAdPauseListener,
		AdvertisingEvents.OnAdPlayListener,
		AdvertisingEvents.OnBeforePlayListener,
		AdvertisingEvents.OnBeforeCompleteListener
{

	TextView mOutput;

	public JWEventHandler(JWPlayerView jwPlayerView, TextView output) {
		mOutput = output;
		// Subscribe to all JW Player events
		jwPlayerView.addOnSetupErrorListener(this);
		jwPlayerView.addOnPlaylistListener(this);
		jwPlayerView.addOnPlaylistItemListener(this);
		jwPlayerView.addOnPlayListener(this);
		jwPlayerView.addOnPauseListener(this);
		jwPlayerView.addOnBufferListener(this);
		jwPlayerView.addOnIdleListener(this);
		jwPlayerView.addOnErrorListener(this);
		jwPlayerView.addOnSeekListener(this);
		jwPlayerView.addOnTimeListener(this);
		jwPlayerView.addOnFullscreenListener(this);
		jwPlayerView.addOnQualityLevelsListener(this);
		jwPlayerView.addOnQualityChangeListener(this);
		jwPlayerView.addOnCaptionsListListener(this);
		jwPlayerView.addOnCaptionsChangeListener(this);
		jwPlayerView.addOnAdClickListener(this);
		jwPlayerView.addOnAdCompleteListener(this);
		jwPlayerView.addOnAdSkippedListener(this);
		jwPlayerView.addOnAdErrorListener(this);
		jwPlayerView.addOnAdImpressionListener(this);
		jwPlayerView.addOnAdTimeListener(this);
		jwPlayerView.addOnAdPauseListener(this);
		jwPlayerView.addOnAdPlayListener(this);
		jwPlayerView.addOnMetaListener(this);
		jwPlayerView.addOnPlaylistCompleteListener(this);
		jwPlayerView.addOnCompleteListener(this);
		jwPlayerView.addOnBeforePlayListener(this);
		jwPlayerView.addOnBeforeCompleteListener(this);
	}

	private void updateOutput(String output) {
		mOutput.setText(output);
	}

	/**
	 * Regular playback events below here
	 */

	@Override
	public void onAudioTrackChange(int currentTrack) {
		updateOutput("onAudioTrackChange(" + currentTrack + ")");
	}

	@Override
	public void onAudioTracks(List<AudioTrack> audioTracks) {
		updateOutput("onAudioTracks(List<AudioTrack>)");
	}

	@Override
	public void onBeforeComplete() {
		updateOutput("onBeforeComplete()");
	}

	@Override
	public void onBeforePlay() {
		updateOutput("onBeforePlay()");
	}

	@Override
	public void onBuffer(PlayerState oldState) {
		updateOutput("onBuffer(" + oldState + ")");
	}

	@Override
	public void onCaptionsChange(int track, List<Caption> captions) {
		updateOutput("onCaptionsChange(" + track + ", List<Caption>)");
	}

	@Override
	public void onCaptionsList(List<Caption> tracks) {
		updateOutput("onCaptionsList(List<Caption>)");
	}

	@Override
	public void onComplete() {
		updateOutput("onComplete()");
	}

	@Override
	public void onError(String message) {
		updateOutput("onError(\"" + message + "\")");
	}

	@Override
	public void onFullscreen(boolean fullscreen) {
		updateOutput("onFullscreen(" + fullscreen + ")");
	}

	@Override
	public void onIdle(PlayerState oldState) {
		updateOutput("onIdle(" + oldState + ")");
	}

	@Override
	public void onMeta(Metadata metadata) {
		updateOutput("onMeta(Metadata)");
	}

	@Override
	public void onPause(PlayerState oldState) {
		updateOutput("onPause(" + oldState + ")");
	}

	@Override
	public void onPlay(PlayerState oldState) {
		updateOutput("onPlay(" + oldState + ")");
	}

	@Override
	public void onPlaylistComplete() {
		updateOutput("onPlaylistComplete()");
	}

	@Override
	public void onPlaylistItem(int index, PlaylistItem playlistItem) {
		updateOutput("onPlaylistItem(" + index + ", PlaylistItem)");
	}

	@Override
	public void onPlaylist(List<PlaylistItem> playlist) {
		updateOutput("onPlaylist(List<PlaylistItem>)");
	}

	@Override
	public void onQualityChange(int currentQuality) {
		updateOutput("onQualityChange(" + currentQuality + ")");
	}

	@Override
	public void onQualityLevels(List<QualityLevel> levels) {
		updateOutput("onQualityLevels(List<QualityLevel>)");
	}

	@Override
	public void onSeek(int position, int offset) {
		updateOutput("onSeek(" + position + ", " + offset + ")");
	}

	@Override
	public void onSetupError(String message) {
		updateOutput("onSetupError(\"" + message + "\")");
	}

	@Override
	public void onTime(long position, long duration) {
		// Do nothing - this fires several times per second
	}

	/**
	 * Advertising events below here
	 */

	@Override
	public void onAdClick(String tag) {
		updateOutput("onAdClick(\"" + tag + "\")");
	}

	@Override
	public void onAdComplete(String tag) {
		updateOutput("onAdComplete(\"" + tag + "\")");
	}

	@Override
	public void onAdError(String tag, String message) {
		updateOutput("onAdError(\"" + tag + "\", \"" + message + "\")");
	}

	@Override
	public void onAdImpression(String tag, String creativeType, String adPosition) {
		updateOutput("onAdImpression(\"" + tag + "\", \"" + creativeType + "\", \"" + adPosition + "\")");
	}

	@Override
	public void onAdPause(String tag, PlayerState oldState) {
		updateOutput("onAdPause(\"" + tag + "\", \"" + oldState + "\")");
	}

	@Override
	public void onAdPlay(String tag, PlayerState oldState) {
		updateOutput("onAdPlay(\"" + tag + "\", \"" + oldState + "\")");
	}

	@Override
	public void onAdSkipped(String tag) {
		updateOutput("onAdSkipped(\"" + tag + "\")");
	}

	@Override
	public void onAdTime(String tag, long position, long duration, int sequence) {
		// Do nothing - this fires several times per second
	}

}
