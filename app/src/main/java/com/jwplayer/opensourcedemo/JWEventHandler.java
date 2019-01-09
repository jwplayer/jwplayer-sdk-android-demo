package com.jwplayer.opensourcedemo;

import android.os.Build;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.AudioTrackChangedEvent;
import com.longtailvideo.jwplayer.events.AudioTracksEvent;
import com.longtailvideo.jwplayer.events.BeforeCompleteEvent;
import com.longtailvideo.jwplayer.events.BeforePlayEvent;
import com.longtailvideo.jwplayer.events.BufferChangeEvent;
import com.longtailvideo.jwplayer.events.BufferEvent;
import com.longtailvideo.jwplayer.events.CaptionsChangedEvent;
import com.longtailvideo.jwplayer.events.CaptionsListEvent;
import com.longtailvideo.jwplayer.events.CompleteEvent;
import com.longtailvideo.jwplayer.events.ControlBarVisibilityEvent;
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
import com.longtailvideo.jwplayer.events.ReadyEvent;
import com.longtailvideo.jwplayer.events.SeekEvent;
import com.longtailvideo.jwplayer.events.SeekedEvent;
import com.longtailvideo.jwplayer.events.SetupErrorEvent;
import com.longtailvideo.jwplayer.events.TimeEvent;
import com.longtailvideo.jwplayer.events.VisualQualityEvent;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Outputs all JW Player Events to logging, with the exception of time events.
 */
public class JWEventHandler implements
        VideoPlayerEvents.OnSetupErrorListener,
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
        VideoPlayerEvents.OnControlBarVisibilityListener,
        VideoPlayerEvents.OnDisplayClickListener,
        VideoPlayerEvents.OnMuteListener,
        VideoPlayerEvents.OnSeekedListener,
        VideoPlayerEvents.OnVisualQualityListener,
        VideoPlayerEvents.OnFirstFrameListener,
        VideoPlayerEvents.OnBufferChangeListener,
        VideoPlayerEvents.OnReadyListener,

        AdvertisingEvents.OnBeforeCompleteListener,
        AdvertisingEvents.OnBeforePlayListener {

    private JWPlayerView mPlayer;
    private TextView mOutput;
    private ScrollView mScroll;
    private final StringBuilder outputStringBuilder = new StringBuilder();


    JWEventHandler(JWPlayerView jwPlayerView, TextView output, ScrollView scrollview) {
        mPlayer = jwPlayerView;
        mScroll = scrollview;
        mOutput = output;
        mOutput.setText(outputStringBuilder.append("Build version: ").append(jwPlayerView.getVersionCode()).append("\r\n"));

        // Subscribe to allEventHandler: Player events
        jwPlayerView.addOnBeforeCompleteListener(this);
        jwPlayerView.addOnBeforePlayListener(this);
        jwPlayerView.addOnBufferListener(this);

        jwPlayerView.addOnCaptionsListListener(this);
        jwPlayerView.addOnCaptionsChangedListener(this);
        jwPlayerView.addOnCompleteListener(this);
        jwPlayerView.addOnControlBarVisibilityListener(this);
        jwPlayerView.addOnControlsListener(this);

        jwPlayerView.addOnDisplayClickListener(this);

        jwPlayerView.addOnErrorListener(this);

        jwPlayerView.addOnFirstFrameListener(this);
        jwPlayerView.addOnFullscreenListener(this);

        jwPlayerView.addOnIdleListener(this);

        jwPlayerView.addOnLevelsChangedListener(this);
        jwPlayerView.addOnLevelsListener(this);

        jwPlayerView.addOnMetaListener(this);
        jwPlayerView.addOnMuteListener(this);

        jwPlayerView.addOnPauseListener(this);
        jwPlayerView.addOnPlayListener(this);
        jwPlayerView.addOnPlaylistCompleteListener(this);
        jwPlayerView.addOnPlaylistItemListener(this);
        jwPlayerView.addOnPlaylistListener(this);

        jwPlayerView.addOnReadyListener(this);

        jwPlayerView.addOnSeekListener(this);
        jwPlayerView.addOnSeekedListener(this);
        jwPlayerView.addOnSetupErrorListener(this);

        jwPlayerView.addOnTimeListener(this);

        jwPlayerView.addOnVisualQualityListener(this);

    }

    private void updateOutput(String output) {
        DateFormat dateFormat = new SimpleDateFormat("KK:mm:ss.SSS", Locale.US);
        outputStringBuilder.append("").append(dateFormat.format(new Date())).append(" ").append(output).append("\r\n");
        mOutput.setText(outputStringBuilder.toString());
        mScroll.scrollTo(0, mOutput.getBottom());
    }

    private void print(String s){
        Log.i("JWEVENTHANDLER",s);
    }


    /**
     * Regular playback events below here
     */

    @Override
    public void onAudioTracks(AudioTracksEvent audioTracksEvent) {
        updateOutput(" " + "onAudioTracks " + audioTracksEvent.getLevels());
        print(" " + "onAudioTracks " + audioTracksEvent);
    }

    public void onBufferChange(BufferChangeEvent bufferChangeEvent) {
        updateOutput(" " + "onBufferChangeEvent\r\n" +
                " position=" + bufferChangeEvent.getPosition() + "\r\n" +
                " duration=" + bufferChangeEvent.getDuration());
        print(" " + "onBufferChangeEvent\r\n" +
                " position=" + bufferChangeEvent.getPosition() + "\r\n" +
                " duration=" + bufferChangeEvent.getDuration());
    }

    @Override
    public void onBeforeComplete(BeforeCompleteEvent beforeCompleteEvent) {
        updateOutput(" " + "onBeforeComplete()");
        print(" " + "onBeforeComplete(): " +beforeCompleteEvent);
    }


    @Override
    public void onError(ErrorEvent errorEvent) {
        updateOutput("onError: " + errorEvent.getMessage());
        Exception exception = errorEvent.getException();
        Log.i("JWPLAYER-LOG", "onError: " + errorEvent.getMessage(), exception);
    }

    @Override
    public void onBeforePlay(BeforePlayEvent beforePlayEvent) {
        updateOutput(" " + "onBeforePlay()");
        print(" " + "onBeforePlay()");
    }

    @Override
    public void onAudioTrackChanged(AudioTrackChangedEvent audioTrackChangedEvent) {
        updateOutput(" " + "onAudioTrackChanged: " + audioTrackChangedEvent.getCurrentTrack());
        print(" " + "onAudioTrackChanged: " + audioTrackChangedEvent.getCurrentTrack());
    }

    @Override
    public void onBuffer(BufferEvent bufferEvent) {
        updateOutput(" " + "onBuffer() " + bufferEvent.getOldState());
        print(" " + "onBuffer() " + bufferEvent.getOldState());
    }

    @Override
    public void onCaptionsChanged(CaptionsChangedEvent captionsChangedEvent) {
        updateOutput(" " + "onCaptionsChanged(): " + captionsChangedEvent.getCurrentTrack());
        print(" " + "onCaptionsChanged(): " + captionsChangedEvent.getCurrentTrack());
    }

    @Override
    public void onCaptionsList(CaptionsListEvent captionsListEvent) {
        updateOutput(" " + "onCaptionsList()");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            captionsListEvent.getTracks().forEach(e->print("onCaptionsList-"+e.getLabel() +": "+ e.toJson().toString()));
        }
    }

    @Override
    public void onComplete(CompleteEvent completeEvent) {
        updateOutput(" " + "onComplete()");
        print(" " + "onComplete()" + completeEvent);
    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        updateOutput(" " + "onControls(): " + controlsEvent.getControls());
        print(" " + "onControls(): " + controlsEvent.getControls());
    }

    @Override
    public void onDisplayClick(DisplayClickEvent displayClickEvent) {
        updateOutput(" " + "onDisplayClick()");
        print(" " + "onDisplayClick()");
    }

    @Override
    public void onFirstFrame(FirstFrameEvent firstFrameEvent) {
        updateOutput(" " + "onFirstFrame: " + firstFrameEvent.getLoadTime());
        print(" " + "onFirstFrame: " + firstFrameEvent.getLoadTime());
    }

    @Override
    public void onFullscreen(FullscreenEvent fullscreenEvent) {
        updateOutput(" " + "onFullscreen: " + fullscreenEvent.getFullscreen());
        print(" " + "onFullscreen: " + fullscreenEvent.getFullscreen());
    }

    @Override
    public void onIdle(IdleEvent idleEvent) {
        updateOutput(" " + "onIdle: " + idleEvent.getOldState());
        print(" " + "onIdle: " + idleEvent.getOldState());
    }

    @Override
    public void onLevelsChanged(LevelsChangedEvent levelsChangedEvent) {
        updateOutput(" " + "onLevelsChanged: " + levelsChangedEvent.getCurrentQuality());
        print(" " + "onLevelsChanged:" + levelsChangedEvent.getCurrentQuality());
    }

    @Override
    public void onLevels(LevelsEvent levelsEvent) {
        updateOutput(" " + "onlevelsEvent size: " + levelsEvent.getLevels().size());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            levelsEvent.getLevels().forEach(e-> print("onlevelsEvent-"+e.getLabel()+":" + e.toJson().toString()));
        }
    }

    @Override
    public void onMeta(MetaEvent metaEvent) {
//        updateOutput(" " + "metaEvent " + metaEvent.getMetadata().toJson());
        print(" " + "onMeta " + metaEvent.getMetadata().toJson());
    }

    @Override
    public void onMute(MuteEvent muteEvent) {
        updateOutput(" " + "onMute " + muteEvent.getMute());
        print(" " + "onMute " + muteEvent.getMute());
    }

    @Override
    public void onPause(PauseEvent pauseEvent) {
        updateOutput(" " + "onPause " + pauseEvent.getOldState());
        print(" " + "onPause " + pauseEvent.getOldState());
    }

    @Override
    public void onPlay(PlayEvent playEvent) {
        updateOutput(" " + "onPlay " + playEvent.getOldState());
        print(" " + "onPlay " + playEvent.getOldState());
    }


    @Override
    public void onPlaylistComplete(PlaylistCompleteEvent playlistCompleteEvent) {
        updateOutput(" " + "onPlaylistComplete() ");
        print(" " + "onPlaylistComplete() ");
    }

    @Override
    public void onPlaylistItem(PlaylistItemEvent playlistItemEvent) {
        updateOutput(" " + "onPlaylistItem index: " + playlistItemEvent.getIndex());
        print(" " + "onPlaylistItem index: " + playlistItemEvent.getIndex());
        print(" " + "onPlaylistItem file: " + playlistItemEvent.getPlaylistItem().getFile());
    }
    @Override
    public void onPlaylist(PlaylistEvent playlistEvent) {
        updateOutput(" " + "onPlaylist() " + playlistEvent.getPlaylist().get(mPlayer.getPlaylistIndex()).getFile());
        print(" " + "onPlaylist() " + playlistEvent.getPlaylist().get(mPlayer.getPlaylistIndex()).getFile());
    }

    @Override
    public void onSeek(SeekEvent seekEvent) {
        updateOutput(" " + "onSeek()"+seekEvent.getPosition());
        print(" " + "onSeek position: " + seekEvent.getPosition());
        print(" " + "onSeek offset: " + seekEvent.getOffset());
    }

    @Override
    public void onSeeked(SeekedEvent seekedEvent) {
        updateOutput(" " + "onSeeked() ");
        print(" " + "onSeeked() "+ seekedEvent.toString());
    }

    @Override
    public void onSetupError(SetupErrorEvent setupErrorEvent) {
        updateOutput(" " + "onSetupError " + setupErrorEvent.getMessage());
        print(" " + "onSetupError "+setupErrorEvent.getMessage());
    }

    @Override
    public void onTime(TimeEvent timeEvent) {
//        updateOutput(" " + "onTime " + timeEvent);
//        print(" " + "onTime);
    }

    @Override
    public void onVisualQuality(VisualQualityEvent visualQualityEvent) {
        if(visualQualityEvent.getQualityLevel() != null){
            updateOutput(" " + "onVisualQuality: " + visualQualityEvent.getQualityLevel().toJson());
            print(" " + "onVisualQuality: " + visualQualityEvent.getQualityLevel().toJson());
        }
    }

    @Override
    public void onReady(ReadyEvent readyEvent) {
        updateOutput(" " + "onReady " + readyEvent.getSetupTime());
        print(" " + "onReady " + readyEvent.getSetupTime());
    }


    @Override
    public void onControlBarVisibilityChanged(ControlBarVisibilityEvent controlBarVisibilityEvent) {
        boolean isVisible = controlBarVisibilityEvent.isVisible();
        updateOutput("onControlBarVisibilityChanged(): " + isVisible);
        print("onControlBarVisibilityChanged(): " + isVisible);
    }
}
