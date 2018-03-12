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
        updateOutput("" + Time.currentTime() + " " + "onAudioTracks(List<AudioTrack>)");
    }

    @Override
    public void onBeforeComplete() {
        updateOutput("" + Time.currentTime() + " " + "onBeforeComplete()");
    }

    @Override
    public void onBeforePlay() {
        updateOutput("" + Time.currentTime() + " " + "onBeforePlay()");
    }

    @Override
    public void onBuffer(PlayerState oldState) {
        updateOutput("" + Time.currentTime() + " " + "onBuffer(" + oldState + ")");
    }

    @Override
    public void onCaptionsList(List<Caption> tracks) {
        updateOutput("" + Time.currentTime() + " " + "onCaptionsList(List<Caption>)");
    }

    @Override
    public void onComplete() {
        updateOutput("" + Time.currentTime() + " " + "onComplete()");
    }

    @Override
    public void onFullscreen(boolean fullscreen) {
        updateOutput("" + Time.currentTime() + " " + "onFullscreen(" + fullscreen + ")");
    }

    @Override
    public void onIdle(PlayerState oldState) {
        updateOutput("" + Time.currentTime() + " " + "onIdle(" + oldState + ")");
    }

    @Override
    public void onMeta(Metadata metadata) {
        updateOutput("" + Time.currentTime() + " " + "onMeta(Metadata)");
    }

    @Override
    public void onPause(PlayerState oldState) {
        updateOutput("" + Time.currentTime() + " " + "onPause(" + oldState + ")");
    }

    @Override
    public void onPlay(PlayerState oldState) {
        updateOutput("" + Time.currentTime() + " " + "onPlay(" + oldState + ")");
    }

    @Override
    public void onPlaylistComplete() {
        updateOutput("" + Time.currentTime() + " " + "onPlaylistComplete()");
    }

    @Override
    public void onPlaylistItem(int index, PlaylistItem playlistItem) {
        updateOutput("" + Time.currentTime() + " " + "onPlaylistItem(" + index + ", PlaylistItem)");
    }

    @Override
    public void onPlaylist(List<PlaylistItem> playlist) {
        updateOutput("" + Time.currentTime() + " " + "onPlaylist(List<PlaylistItem>)");
    }

    @Override
    public void onSeek(int position, int offset) {
        updateOutput("" + Time.currentTime() + " " + "onSeek(" + position + ", " + offset + ")");
    }

    @Override
    public void onSetupError(String message) {
        updateOutput("" + Time.currentTime() + " " + "onSetupError(\"" + message + "\")");
    }

    @Override
    public void onTime(long position, long duration) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdError(String tag, String message) {
        updateOutput("" + Time.currentTime() + " " + "onAdError(\"" + tag + "\", \"" + message + "\")");
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        updateOutput("" + Time.currentTime() + " " + "onError(\"" + errorEvent.getMessage() + "\")");
    }

    @Override
    public void onLevelsChanged(int i) {
        updateOutput("" + Time.currentTime() + " " + "onLevelsChange(" + i + ")");
    }

    @Override
    public void onLevels(List<QualityLevel> list) {
        updateOutput("" + Time.currentTime() + " " + "onLevels(List<QualityLevel>)");
    }

    @Override
    public void onAudioTrackChanged(int i) {
        updateOutput("" + Time.currentTime() + " " + "onAudioTrackChanged(" + i + ")");
    }

    @Override
    public void onCaptionsChanged(int i, List<Caption> list) {
        updateOutput("" + Time.currentTime() + " " + "onCaptionsChanged(" + i + ", List<Caption>)");
    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        updateOutput("" + Time.currentTime() + " " + "onAdClick(\"" + adClickEvent.getTag() + "\")");
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        updateOutput("" + Time.currentTime() + " " + "onAdComplete(\"" + adCompleteEvent.getTag() + "\")");
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        updateOutput("" + Time.currentTime() + " " + "onAdSkipped(\"" + adSkippedEvent.getTag() + "\")");
    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        updateOutput("" + Time.currentTime() + " " + "onAdImpression(\"" + adImpressionEvent.getTag() + "\", \"" + adImpressionEvent.getCreativeType() + "\", \"" + adImpressionEvent.getAdPosition().name() + "\")");

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        updateOutput("" + Time.currentTime() + " " + "onAdPause(\"" + adPauseEvent.getTag() + "\", \"" + adPauseEvent.getOldState() + "\")");
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        updateOutput("" + Time.currentTime() + " " + "onAdPlay(\"" + adPlayEvent.getTag() + "\", \"" + adPlayEvent.getOldState() + "\")");
    }

    @Override
    public void onRelatedClose(RelatedCloseEvent relatedCloseEvent) {
        updateOutput("" + Time.currentTime() + " " + "onRelatedClose(\"" + relatedCloseEvent.getMethod() + "\")");
    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        updateOutput("" + Time.currentTime() + " " + "onControls(\"" + controlsEvent.getControls() + "\")");
    }

    @Override
    public void onDisplayClick() {
        updateOutput("" + Time.currentTime() + " " + "onDisplayClick(\"" + "displayClick" + "\")");
    }

    @Override
    public void onMute(boolean b) {
        updateOutput("" + Time.currentTime() + " " + "onMute(\"" + b + "\")");
    }

    @Override
    public void onRelatedOpen(RelatedOpenEvent relatedOpenEvent) {
        updateOutput("" + Time.currentTime() + " " + "onRelatedOpen(\"" + relatedOpenEvent.getMethod() + "\")");
    }

    @Override
    public void onRelatedPlay(RelatedPlayEvent relatedPlayEvent) {
        updateOutput("" + Time.currentTime() + " " + "onRelatedPlay(\"" + relatedPlayEvent.getAuto() + "\")");
    }

    @Override
    public void onSeeked() {
        updateOutput("" + Time.currentTime() + " " + "onSeeked(\"" + "seeked" + "\")");
    }

    @Override
    public void onVisualQuality(VisualQuality visualQuality) {
        updateOutput("" + Time.currentTime() + " " + "onVisualQuality(\"" + visualQuality.getQualityLevel().getLabel() + "\")");
    }

    @Override
    public void onCreate() {
        updateOutput("" + Time.currentTime() + " onCreate");
    }

    @Override
    public void onCreateFinish() {
        updateOutput("" + Time.currentTime() + " onCreateFinish");
    }

    @Override
    public void onStart() {
        updateOutput("" + Time.currentTime() + " onStart");
    }

    @Override
    public void onResume() {
        updateOutput("" + Time.currentTime() + " onResume");
    }

    @Override
    public void onPause() {
        updateOutput("" + Time.currentTime() + " onPause");
    }

    @Override
    public void onStop() {
        updateOutput("" + Time.currentTime() + " onStop");
    }

    @Override
    public void onDestroy() {
        updateOutput("" + Time.currentTime() + " onDestroy");
    }
}
