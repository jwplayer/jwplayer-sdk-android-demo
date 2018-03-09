package com.jwplayer.opensourcedemo;

import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.core.PlayerState;
import com.longtailvideo.jwplayer.events.AdClickEvent;
import com.longtailvideo.jwplayer.events.AdCompleteEvent;
import com.longtailvideo.jwplayer.events.AdImpressionEvent;
import com.longtailvideo.jwplayer.events.AdPauseEvent;
import com.longtailvideo.jwplayer.events.AdPlayEvent;
import com.longtailvideo.jwplayer.events.AdSkippedEvent;
import com.longtailvideo.jwplayer.events.AdTimeEvent;
import com.longtailvideo.jwplayer.events.ControlsEvent;
import com.longtailvideo.jwplayer.events.ErrorEvent;
import com.longtailvideo.jwplayer.events.RelatedCloseEvent;
import com.longtailvideo.jwplayer.events.RelatedOpenEvent;
import com.longtailvideo.jwplayer.events.RelatedPlayEvent;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.adaptive.QualityLevel;
import com.longtailvideo.jwplayer.media.adaptive.VisualQuality;
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
        VideoPlayerEvents.OnErrorListenerV2,
        VideoPlayerEvents.OnSeekListener,
        VideoPlayerEvents.OnTimeListener,
        VideoPlayerEvents.OnFullscreenListener,
        VideoPlayerEvents.OnAudioTracksListener,
        VideoPlayerEvents.OnAudioTrackChangedListener,
        VideoPlayerEvents.OnCaptionsListListener,
        VideoPlayerEvents.OnMetaListener,
        VideoPlayerEvents.OnPlaylistCompleteListener,
        VideoPlayerEvents.OnCompleteListener,
        VideoPlayerEvents.OnLevelsChangedListener,
        VideoPlayerEvents.OnLevelsListener,
        VideoPlayerEvents.OnCaptionsChangedListener,
        VideoPlayerEvents.OnRelatedCloseListener,
        VideoPlayerEvents.OnControlsListener,
        VideoPlayerEvents.OnDisplayClickListener,
        VideoPlayerEvents.OnMuteListener,
        VideoPlayerEvents.OnRelatedOpenListener,
        VideoPlayerEvents.OnRelatedPlayListener,
        VideoPlayerEvents.OnSeekedListener,
        VideoPlayerEvents.OnVisualQualityListener,
        AdvertisingEvents.OnAdClickListenerV2,
        AdvertisingEvents.OnAdCompleteListenerV2,
        AdvertisingEvents.OnAdSkippedListenerV2,
        AdvertisingEvents.OnAdErrorListener,
        AdvertisingEvents.OnAdImpressionListenerV2,
        AdvertisingEvents.OnAdTimeListenerV2,
        AdvertisingEvents.OnAdPauseListenerV2,
        AdvertisingEvents.OnAdPlayListenerV2,
        AdvertisingEvents.OnBeforePlayListener,
        AdvertisingEvents.OnBeforeCompleteListener,
        ActivityListener {

    private final TextView mOutput;
    private final StringBuilder outputStringBuilder = new StringBuilder();

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
        jwPlayerView.addOnLevelsChangedListener(this);
        jwPlayerView.addOnLevelsListener(this);
        jwPlayerView.addOnCaptionsListListener(this);
        jwPlayerView.addOnCaptionsChangedListener(this);
        jwPlayerView.addOnRelatedCloseListener(this);
        jwPlayerView.addOnRelatedOpenListener(this);
        jwPlayerView.addOnRelatedPlayListener(this);
        jwPlayerView.addOnControlsListener(this);
        jwPlayerView.addOnDisplayClickListener(this);
        jwPlayerView.addOnMuteListener(this);
        jwPlayerView.addOnVisualQualityListener(this);
        jwPlayerView.addOnSeekedListener(this);
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
        outputStringBuilder.append(output).append("\r\n");
        mOutput.setText(outputStringBuilder.toString());
    }

    /**
     * Regular playback events below here
     */

    @Override
    public void onAudioTracks(List<AudioTrack> audioTracks) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAudioTracks(List<AudioTrack>)");
    }

    @Override
    public void onBeforeComplete() {
        updateOutput("" + System.currentTimeMillis() + " " + "onBeforeComplete()");
    }

    @Override
    public void onBeforePlay() {
        updateOutput("" + System.currentTimeMillis() + " " + "onBeforePlay()");
    }

    @Override
    public void onBuffer(PlayerState oldState) {
        updateOutput("" + System.currentTimeMillis() + " " + "onBuffer(" + oldState + ")");
    }

    @Override
    public void onCaptionsList(List<Caption> tracks) {
        updateOutput("" + System.currentTimeMillis() + " " + "onCaptionsList(List<Caption>)");
    }

    @Override
    public void onComplete() {
        updateOutput("" + System.currentTimeMillis() + " " + "onComplete()");
    }

    @Override
    public void onFullscreen(boolean fullscreen) {
        updateOutput("" + System.currentTimeMillis() + " " + "onFullscreen(" + fullscreen + ")");
    }

    @Override
    public void onIdle(PlayerState oldState) {
        updateOutput("" + System.currentTimeMillis() + " " + "onIdle(" + oldState + ")");
    }

    @Override
    public void onMeta(Metadata metadata) {
        updateOutput("" + System.currentTimeMillis() + " " + "onMeta(Metadata)");
    }

    @Override
    public void onPause(PlayerState oldState) {
        updateOutput("" + System.currentTimeMillis() + " " + "onPause(" + oldState + ")");
    }

    @Override
    public void onPlay(PlayerState oldState) {
        updateOutput("" + System.currentTimeMillis() + " " + "onPlay(" + oldState + ")");
    }

    @Override
    public void onPlaylistComplete() {
        updateOutput("" + System.currentTimeMillis() + " " + "onPlaylistComplete()");
    }

    @Override
    public void onPlaylistItem(int index, PlaylistItem playlistItem) {
        updateOutput("" + System.currentTimeMillis() + " " + "onPlaylistItem(" + index + ", PlaylistItem)");
    }

    @Override
    public void onPlaylist(List<PlaylistItem> playlist) {
        updateOutput("" + System.currentTimeMillis() + " " + "onPlaylist(List<PlaylistItem>)");
    }

    @Override
    public void onSeek(int position, int offset) {
        updateOutput("" + System.currentTimeMillis() + " " + "onSeek(" + position + ", " + offset + ")");
    }

    @Override
    public void onSetupError(String message) {
        updateOutput("" + System.currentTimeMillis() + " " + "onSetupError(\"" + message + "\")");
    }

    @Override
    public void onTime(long position, long duration) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdError(String tag, String message) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAdError(\"" + tag + "\", \"" + message + "\")");
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onError(\"" + errorEvent.getMessage() + "\")");
    }

    @Override
    public void onLevelsChanged(int i) {
        updateOutput("" + System.currentTimeMillis() + " " + "onLevelsChange(" + i + ")");
    }

    @Override
    public void onLevels(List<QualityLevel> list) {
        updateOutput("" + System.currentTimeMillis() + " " + "onLevels(List<QualityLevel>)");
    }

    @Override
    public void onAudioTrackChanged(int i) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAudioTrackChanged(" + i + ")");
    }

    @Override
    public void onCaptionsChanged(int i, List<Caption> list) {
        updateOutput("" + System.currentTimeMillis() + " " + "onCaptionsChanged(" + i + ", List<Caption>)");
    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAdClick(\"" + adClickEvent.getTag() + "\")");
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAdComplete(\"" + adCompleteEvent.getTag() + "\")");
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAdSkipped(\"" + adSkippedEvent.getTag() + "\")");
    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAdImpression(\"" + adImpressionEvent.getTag() + "\", \"" + adImpressionEvent.getCreativeType() + "\", \"" + adImpressionEvent.getAdPosition().name() + "\")");

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAdPause(\"" + adPauseEvent.getTag() + "\", \"" + adPauseEvent.getOldState() + "\")");
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onAdPlay(\"" + adPlayEvent.getTag() + "\", \"" + adPlayEvent.getOldState() + "\")");
    }

    @Override
    public void onRelatedClose(RelatedCloseEvent relatedCloseEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onRelatedClose(\"" + relatedCloseEvent.getMethod() + "\")");
    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onControls(\"" + controlsEvent.getControls() + "\")");
    }

    @Override
    public void onDisplayClick() {
        updateOutput("" + System.currentTimeMillis() + " " + "onDisplayClick(\"" + "displayClick" + "\")");
    }

    @Override
    public void onMute(boolean b) {
        updateOutput("" + System.currentTimeMillis() + " " + "onMute(\"" + b + "\")");
    }

    @Override
    public void onRelatedOpen(RelatedOpenEvent relatedOpenEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onRelatedOpen(\"" + relatedOpenEvent.getMethod() + "\")");
    }

    @Override
    public void onRelatedPlay(RelatedPlayEvent relatedPlayEvent) {
        updateOutput("" + System.currentTimeMillis() + " " + "onRelatedPlay(\"" + relatedPlayEvent.getAuto() + "\")");
    }

    @Override
    public void onSeeked() {
        updateOutput("" + System.currentTimeMillis() + " " + "onSeeked(\"" + "seeked" + "\")");
    }

    @Override
    public void onVisualQuality(VisualQuality visualQuality) {
        updateOutput("" + System.currentTimeMillis() + " " + "onVisualQuality(\"" + visualQuality.getQualityLevel().getLabel() + "\")");
    }

    @Override
    public void onCreate() {
        updateOutput("" + System.currentTimeMillis() + " onCreate");
    }

    @Override
    public void onCreateFinish() {
        updateOutput("" + System.currentTimeMillis() + " onCreateFinish");
    }

    @Override
    public void onStart() {
        updateOutput("" + System.currentTimeMillis() + " onStart");
    }

    @Override
    public void onResume() {
        updateOutput("" + System.currentTimeMillis() + " onResume");
    }

    @Override
    public void onPause() {
        updateOutput("" + System.currentTimeMillis() + " onPause");
    }

    @Override
    public void onStop() {
        updateOutput("" + System.currentTimeMillis() + " onStop");
    }

    @Override
    public void onDestroy() {
        updateOutput("" + System.currentTimeMillis() + " onDestroy");
    }
}
