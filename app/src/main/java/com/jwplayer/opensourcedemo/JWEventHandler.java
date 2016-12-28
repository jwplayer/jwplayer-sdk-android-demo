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
        AdvertisingEvents.OnBeforeCompleteListener {

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
        mOutput.setText(output);
    }

    /**
     * Regular playback events below here
     */

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
    public void onCaptionsList(List<Caption> tracks) {
        updateOutput("onCaptionsList(List<Caption>)");
    }

    @Override
    public void onComplete() {
        updateOutput("onComplete()");
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

    @Override
    public void onAdError(String tag, String message) {
        updateOutput("onAdError(\"" + tag + "\", \"" + message + "\")");
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        updateOutput("onError(\"" + errorEvent.getMessage() + "\")");
    }

    @Override
    public void onLevelsChanged(int i) {
        updateOutput("onLevelsChange(" + i + ")");
    }

    @Override
    public void onLevels(List<QualityLevel> list) {
        updateOutput("onLevels(List<QualityLevel>)");
    }

    @Override
    public void onAudioTrackChanged(int i) {
        updateOutput("onAudioTrackChanged(" + i + ")");
    }

    @Override
    public void onCaptionsChanged(int i, List<Caption> list) {
        updateOutput("onCaptionsChanged(" + i + ", List<Caption>)");
    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        updateOutput("onAdClick(\"" + adClickEvent.getTag() + "\")");
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        updateOutput("onAdComplete(\"" + adCompleteEvent.getTag() + "\")");
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        updateOutput("onAdSkipped(\"" + adSkippedEvent.getTag() + "\")");
    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        updateOutput("onAdImpression(\"" + adImpressionEvent.getTag() + "\", \"" + adImpressionEvent.getCreativeType() + "\", \"" + adImpressionEvent.getAdPosition().name() + "\")");

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        updateOutput("onAdPause(\"" + adPauseEvent.getTag() + "\", \"" + adPauseEvent.getOldState() + "\")");
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        updateOutput("onAdPlay(\"" + adPlayEvent.getTag() + "\", \"" + adPlayEvent.getOldState() + "\")");
    }

    @Override
    public void onRelatedClose(RelatedCloseEvent relatedCloseEvent) {
        updateOutput("onRelatedClose(\"" + relatedCloseEvent.getMethod() + "\")");
    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        updateOutput("onControls(\"" + controlsEvent.getControls() + "\")");
    }

    @Override
    public void onDisplayClick() {
        updateOutput("onDisplayClick(\"" + "displayClick" + "\")");
    }

    @Override
    public void onMute(boolean b) {
        updateOutput("onMute(\"" + b + "\")");
    }

    @Override
    public void onRelatedOpen(RelatedOpenEvent relatedOpenEvent) {
        updateOutput("onRelatedOpen(\"" + relatedOpenEvent.getMethod() + "\")");
    }

    @Override
    public void onRelatedPlay(RelatedPlayEvent relatedPlayEvent) {
        updateOutput("onRelatedPlay(\"" + relatedPlayEvent.getAuto() + "\")");
    }

    @Override
    public void onSeeked() {
        updateOutput("onSeeked(\"" + "seeked" + "\")");
    }

    @Override
    public void onVisualQuality(VisualQuality visualQuality) {
        updateOutput("onVisualQuality(\"" + visualQuality.getQualityLevel().getLabel() + "\")");
    }
}
