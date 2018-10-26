package com.jwplayer.opensourcedemo;

import android.util.Log;
import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.AdClickEvent;
import com.longtailvideo.jwplayer.events.AdCompleteEvent;
import com.longtailvideo.jwplayer.events.AdErrorEvent;
import com.longtailvideo.jwplayer.events.AdImpressionEvent;
import com.longtailvideo.jwplayer.events.AdPauseEvent;
import com.longtailvideo.jwplayer.events.AdPlayEvent;
import com.longtailvideo.jwplayer.events.AdScheduleEvent;
import com.longtailvideo.jwplayer.events.AdSkippedEvent;
import com.longtailvideo.jwplayer.events.AdTimeEvent;
import com.longtailvideo.jwplayer.events.AudioTrackChangedEvent;
import com.longtailvideo.jwplayer.events.AudioTracksEvent;
import com.longtailvideo.jwplayer.events.BeforeCompleteEvent;
import com.longtailvideo.jwplayer.events.BeforePlayEvent;
import com.longtailvideo.jwplayer.events.BufferEvent;
import com.longtailvideo.jwplayer.events.CaptionsChangedEvent;
import com.longtailvideo.jwplayer.events.CaptionsListEvent;
import com.longtailvideo.jwplayer.events.CompleteEvent;
import com.longtailvideo.jwplayer.events.ControlsEvent;
import com.longtailvideo.jwplayer.events.DisplayClickEvent;
import com.longtailvideo.jwplayer.events.ErrorEvent;
import com.longtailvideo.jwplayer.events.FirstFrameEvent;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.IdleEvent;
import com.longtailvideo.jwplayer.events.LevelsChangedEvent;
import com.longtailvideo.jwplayer.events.LevelsEvent;
import com.longtailvideo.jwplayer.events.MetaEvent;
import com.longtailvideo.jwplayer.events.MuteEvent;
import com.longtailvideo.jwplayer.events.PauseEvent;
import com.longtailvideo.jwplayer.events.PlayEvent;
import com.longtailvideo.jwplayer.events.PlaylistCompleteEvent;
import com.longtailvideo.jwplayer.events.PlaylistEvent;
import com.longtailvideo.jwplayer.events.PlaylistItemEvent;
import com.longtailvideo.jwplayer.events.SeekEvent;
import com.longtailvideo.jwplayer.events.SeekedEvent;
import com.longtailvideo.jwplayer.events.SetupErrorEvent;
import com.longtailvideo.jwplayer.events.TimeEvent;
import com.longtailvideo.jwplayer.events.VisualQualityEvent;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

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
        VideoPlayerEvents.OnAudioTracksListener,
        VideoPlayerEvents.OnAudioTrackChangedListener,
        VideoPlayerEvents.OnCaptionsListListener,
        VideoPlayerEvents.OnMetaListener,
        VideoPlayerEvents.OnPlaylistCompleteListener,
        VideoPlayerEvents.OnCompleteListener,
        VideoPlayerEvents.OnLevelsChangedListener,
        VideoPlayerEvents.OnLevelsListener,
        VideoPlayerEvents.OnCaptionsChangedListener,
        VideoPlayerEvents.OnControlsListener,
        VideoPlayerEvents.OnDisplayClickListener,
        VideoPlayerEvents.OnMuteListener,
        VideoPlayerEvents.OnSeekedListener,
        VideoPlayerEvents.OnVisualQualityListener,
        VideoPlayerEvents.OnFirstFrameListener,
        AdvertisingEvents.OnAdClickListener,
        AdvertisingEvents.OnAdCompleteListener,
        AdvertisingEvents.OnAdSkippedListener,
        AdvertisingEvents.OnAdErrorListener,
        AdvertisingEvents.OnAdImpressionListener,
        AdvertisingEvents.OnAdTimeListener,
        AdvertisingEvents.OnAdPauseListener,
        AdvertisingEvents.OnAdPlayListener,
        AdvertisingEvents.OnAdScheduleListener,
        AdvertisingEvents.OnBeforePlayListener,
        AdvertisingEvents.OnBeforeCompleteListener {
    private String TAG = JWEventHandler.class.getName();

    TextView mOutput;

    public JWEventHandler(JWPlayerView jwPlayerView, TextView output) {
        mOutput = output;
        // Subscribe to all JW Player events
        jwPlayerView.addOnFirstFrameListener(this);
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
        //  jwPlayerView.addOnRelatedCloseListener(this);
        //  jwPlayerView.addOnRelatedOpenListener(this);
        //  jwPlayerView.addOnRelatedPlayListener(this);
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
        jwPlayerView.addOnAdScheduleListener(this);
    }

    private void updateOutput(String output) {
        mOutput.setText(output);
    }

    /**
     * Regular playback events below here
     */

    @Override
    public void onAudioTracks(AudioTracksEvent audioTracksEvent) {
        Log.d(TAG, "onAudioTracks");
        updateOutput("onAudioTracks(List<AudioTrack>)");
    }

    @Override
    public void onBeforeComplete(BeforeCompleteEvent beforeCompleteEvent) {
        Log.d(TAG, "onBeforeComplete");
        updateOutput("onBeforeComplete()");
    }

    @Override
    public void onBeforePlay(BeforePlayEvent beforePlayEvent
    ) {
        Log.d(TAG, "onBeforePlay");
        updateOutput("onBeforePlay()");
    }

    @Override
    public void onBuffer(BufferEvent bufferEvent) {
        Log.d(TAG, "onBuffer");
        updateOutput("onBuffer()");
    }

    @Override
    public void onCaptionsList(CaptionsListEvent captionsListEvent) {
        Log.d(TAG, "onCaptionsList");
        updateOutput("onCaptionsList(List<Caption>)");
    }

    @Override
    public void onComplete(CompleteEvent completeEvent) {
        Log.d(TAG, "onComplete");
        updateOutput("onComplete()");
    }

    @Override
    public void onFullscreen(FullscreenEvent fullscreen) {
        Log.d(TAG, "onFullscreen");
        updateOutput("onFullscreen(" + fullscreen.getFullscreen() + ")");
    }

    @Override
    public void onIdle(IdleEvent idleEvent) {
        Log.d(TAG, "onIdle");
        updateOutput("onIdle()");
    }

    @Override
    public void onMeta(MetaEvent metaEvent) {
        Log.d(TAG, "onMeta");
        updateOutput("onMeta()");
    }

    @Override
    public void onPause(PauseEvent pauseEvent) {
        Log.d(TAG, "onPause");
        updateOutput("onPause()");
    }

    @Override
    public void onPlay(PlayEvent playEvent) {
        Log.d(TAG, "onPlay");
        updateOutput("onPlay()");
    }

    @Override
    public void onPlaylistComplete(PlaylistCompleteEvent playlistCompleteEvent) {
        Log.d(TAG, "onPlaylistComplete");
        updateOutput("onPlaylistComplete()");
    }

    @Override
    public void onPlaylistItem(PlaylistItemEvent playlistItemEvent) {
        Log.d(TAG, "onPlaylistItem");
        updateOutput("onPlaylistItem()");
    }

    @Override
    public void onPlaylist(PlaylistEvent playlistEvent) {
        Log.d(TAG, "onPlaylist");
        updateOutput("onPlaylist()");
    }


    @Override
    public void onSeek(SeekEvent seekEvent) {
        Log.d(TAG, "onSeek");
        updateOutput("onSeek(" + seekEvent.getPosition() + ", " + seekEvent.getOffset() + ")");
    }

    @Override
    public void onSetupError(SetupErrorEvent setupErrorEvent) {
        Log.d("onSetupError: ", setupErrorEvent.getMessage());
        updateOutput("onSetupError(\"" + setupErrorEvent.getMessage() + "\")");
    }

    @Override
    public void onTime(TimeEvent timeEvent) {
        Log.d(TAG, timeEvent.getDuration() + "  ***  ");
    }

    @Override
    public void onAdError(AdErrorEvent adErrorEvent) {
        updateOutput("onAdError(\"" + "\", \"" + adErrorEvent.getMessage() + "\")");
        Log.d(TAG, "onAdError tag : " + adErrorEvent.getTag());
        Log.d(TAG, "onAdError msg : " + adErrorEvent.getMessage());
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        Log.d(TAG, "onError : " + errorEvent);
        Log.d(TAG, "onError : " + errorEvent.getMessage());
        updateOutput("onError(\"" + errorEvent.getMessage() + "\")");
    }

    @Override
    public void onLevelsChanged(LevelsChangedEvent levelsChangedEvent) {
        Log.d(TAG, "onLevelsChanged");
        updateOutput("onLevelsChange(" + levelsChangedEvent.getCurrentQuality() + ")");
    }

    @Override
    public void onLevels(LevelsEvent levelsEvent) {
        Log.d(TAG, "onLevels");
        updateOutput("onLevels(List<QualityLevel>)");
    }

    @Override
    public void onAudioTrackChanged(AudioTrackChangedEvent audioTrackChangedEvent) {
        Log.d(TAG, "onAudioTrackChanged");
        updateOutput("onAudioTrackChanged(" + audioTrackChangedEvent.getCurrentTrack() + ")");
    }

    @Override
    public void onCaptionsChanged(CaptionsChangedEvent list) {
        Log.d(TAG, "onCaptionsChanged");
        updateOutput("onCaptionsChanged(" + list.getCurrentTrack() + ", List<Caption>)");
    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        Log.d(TAG, "onAdClick");
        updateOutput("onAdClick(\"" + adClickEvent.getTag() + "\")");
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        Log.d(TAG, "onAdComplete");
        updateOutput("onAdComplete(\"" + adCompleteEvent.getTag() + "\")");
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        Log.d(TAG, "onAdSkipped");
        updateOutput("onAdSkipped(\"" + adSkippedEvent.getTag() + "\")");
    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        Log.d(TAG, "onAdImpression");
        updateOutput("onAdImpression(\"" + adImpressionEvent.getTag() + "\", \"" + adImpressionEvent.getCreativeType() + "\", \"" + adImpressionEvent.getAdPosition().name() + "\")");

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        Log.d(TAG, "onAdTime");
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        Log.d(TAG, "onAdPause");
        updateOutput("onAdPause(\"" + adPauseEvent.getTag() + "\", \"" + adPauseEvent.getOldState() + "\")");
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        Log.d(TAG, "onAdPlay");
        updateOutput("onAdPlay(\"" + adPlayEvent.getTag() + "\", \"" + adPlayEvent.getOldState() + "\")");
    }

    public void onSeeked(SeekedEvent seekedEvent) {
        Log.d(TAG, "onSeeked");
        updateOutput("onSeeked(\"" + "seeked" + "\")");
    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        Log.d(TAG, "onControls");
        updateOutput("onControls(\"" + controlsEvent.getControls() + "\")");
    }

    @Override
    public void onDisplayClick(DisplayClickEvent displayClickEvent) {
        Log.d(TAG, "onDisplayClick");
        updateOutput("onDisplayClick()");
    }

    @Override
    public void onVisualQuality(VisualQualityEvent visualQuality) {
        Log.d(TAG, "onVisualQuality");
        updateOutput("onVisualQuality(\"" + "\")");
    }

    @Override
    public void onMute(MuteEvent muteEvent) {
        Log.d(TAG, "onMute");
        updateOutput("onMute()");

    }

    @Override
    public void onFirstFrame(FirstFrameEvent firstFrameEvent) {
        Log.d(TAG, "firstFrameEvent");
        updateOutput("onFirstFrame()");
    }


    @Override
    public void onAdSchedule(AdScheduleEvent adScheduleEvent) {
        Log.d(TAG, "onAdSchedule");
        updateOutput("onAdSchedule()");
    }
}
