package com.jwplayer.opensourcedemo;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.exoplayer2.metadata.id3.BinaryFrame;
import com.google.android.exoplayer2.metadata.id3.GeobFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.core.PlayerState;
import com.longtailvideo.jwplayer.events.AdClickEvent;
import com.longtailvideo.jwplayer.events.AdCompleteEvent;
import com.longtailvideo.jwplayer.events.AdImpressionEvent;
import com.longtailvideo.jwplayer.events.AdPauseEvent;
import com.longtailvideo.jwplayer.events.AdPlayEvent;
import com.longtailvideo.jwplayer.events.AdSkippedEvent;
import com.longtailvideo.jwplayer.events.AdTimeEvent;
import com.longtailvideo.jwplayer.events.BufferChangeEvent;
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
 * Outputs allEventHandler: Player Events to logging, with the exception of time events.
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
        VideoPlayerEvents.OnFirstFrameListener,
        VideoPlayerEvents.OnBufferChangeListener{

    private static final String MEASURE_TAG = "JWMeasure";
    private JWPlayerView jwPlayerView;
    private final TextView mOutput;
    private final StringBuilder outputStringBuilder = new StringBuilder();
    private final ScrollView mScroll;

    public JWEventHandler(JWPlayerView jwPlayerView, TextView output, ScrollView scrollview) {
        this.jwPlayerView = jwPlayerView;
        mScroll = scrollview;
        mOutput = output;

        // Subscribe to allEventHandler: Player events
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
        jwPlayerView.addOnFirstFrameListener(this);
    }

    private void updateOutput(String output) {
        outputStringBuilder.append(""  + Time.currentTime() + " " + output).append("\r\n");
        mOutput.setText(outputStringBuilder.toString());
        mScroll.scrollTo(0,mOutput.getBottom());
    }

    public void print(String log){
        Log.i(MEASURE_TAG,"" + Time.currentTime() + log + "(Event)");
    }

    /**
     * Regular playback events below here
     */

    @Override
    public void onAudioTracks(List<AudioTrack> audioTracks) {
        updateOutput(" " + "onAudioTracks(List<AudioTrack>)");
    }

    @Override
    public void onBeforeComplete() {
        updateOutput(" " + "onBeforeComplete()");
    }

    @Override
    public void onBeforePlay() {
        updateOutput(" " + "onBeforePlay()");
    }

    @Override
    public void onCaptionsList(List<Caption> tracks) {
        updateOutput(" " + "onCaptionsList(List<Caption>)");
    }

    @Override
    public void onFullscreen(boolean fullscreen) {
        updateOutput(" " + "onFullscreen(" + fullscreen + ")");
    }
    @Override
    public void onMeta(Metadata meta) {
        updateOutput("onMeta(Metadata)");

        if(meta.getId3Metadata().size() > 0) {
            List<Id3Frame> id3 = meta.getId3Metadata();
            for(Id3Frame id3Obj : id3) {
                if(id3Obj instanceof TextInformationFrame) {
                    TextInformationFrame txxxFrame = (TextInformationFrame)id3Obj;
                } else if(id3Obj instanceof PrivFrame) {
                    PrivFrame privFrame = (PrivFrame)id3Obj;
                } else if(id3Obj instanceof GeobFrame) {
                    GeobFrame geobFrame = (GeobFrame)id3Obj;
                } else if(id3Obj instanceof BinaryFrame) {
                    BinaryFrame binaryFrame = (BinaryFrame)id3Obj;
                }
            }
        }
    }

    @Override
    public void onPlaylistComplete() {
        updateOutput(" " + "onPlaylistComplete()");
    }

    @Override
    public void onPlaylistItem(int index, PlaylistItem playlistItem) {
        updateOutput(" " + "onPlaylistItem(" + index + ", PlaylistItem)");
    }

    @Override
    public void onPlaylist(List<PlaylistItem> playlist) {
        updateOutput(" " + "onPlaylist(List<PlaylistItem>)");
    }
    @Override
    public void onTime(long position, long duration) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdError(String tag, String message) {
        updateOutput(" " + "onAdError(\"" + tag + "\", \"" + message + "\")");
    }

    @Override
    public void onLevelsChanged(int i) {
        updateOutput(" " + "onLevelsChange(" + i + ")");
    }

    @Override
    public void onLevels(List<QualityLevel> list) {
        updateOutput(" " + "onLevels(List<QualityLevel>)");
    }

    @Override
    public void onAudioTrackChanged(int i) {
        updateOutput(" " + "onAudioTrackChanged(" + i + ")");
    }

    @Override
    public void onCaptionsChanged(int i, List<Caption> list) {
        updateOutput(" " + "onCaptionsChanged(" + i + ", List<Caption>)");
    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        updateOutput(" " + "onAdClick(\"" + adClickEvent.getTag() + "\")");
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        updateOutput(" " + "onAdComplete(\"" + adCompleteEvent.getTag() + "\")");
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        updateOutput(" " + "onAdSkipped(\"" + adSkippedEvent.getTag() + "\")");
    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        updateOutput(" " + "onAdImpression(\"" + adImpressionEvent.getTag() + "\", \"" + adImpressionEvent.getCreativeType() + "\", \"" + adImpressionEvent.getAdPosition().name() + "\")");

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        updateOutput(" " + "onAdPause(\"" + adPauseEvent.getTag() + "\", \"" + adPauseEvent.getOldState() + "\")");
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        updateOutput(" " + "onAdPlay(\"" + adPlayEvent.getTag() + "\", \"" + adPlayEvent.getOldState() + "\")");
    }

    @Override
    public void onRelatedClose(RelatedCloseEvent relatedCloseEvent) {
        updateOutput(" " + "onRelatedClose(\"" + relatedCloseEvent.getMethod() + "\")");
    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        updateOutput(" " + "onControls(\"" + controlsEvent.getControls() + "\")");
    }

    @Override
    public void onDisplayClick() {
        updateOutput(" " + "onDisplayClick(\"" + "displayClick" + "\")");
    }

    @Override
    public void onMute(boolean b) {
        updateOutput(" " + "onMute(\"" + b + "\")");
    }

    @Override
    public void onRelatedOpen(RelatedOpenEvent relatedOpenEvent) {
        updateOutput(" " + "onRelatedOpen(\"" + relatedOpenEvent.getMethod() + "\")");
    }

    @Override
    public void onRelatedPlay(RelatedPlayEvent relatedPlayEvent) {
        updateOutput(" " + "onRelatedPlay(\"" + relatedPlayEvent.getAuto() + "\")");
    }

    @Override
    public void onVisualQuality(VisualQuality visualQuality) {
        updateOutput(" " + "onVisualQuality(\"" + visualQuality.getQualityLevel().getLabel() + "\")");
    }

    @Override
    public void onFirstFrame(int i) {
        updateOutput(" onFirstFrame= "+i);
        print(" onFirstFrame= "+i);
    }

    @Override
    public void onBuffer(PlayerState oldState) {
        updateOutput(" " + "onBuffer");
        print(" onBuffer; oldState=" + String.valueOf(oldState));
    }

    @Override
    public void onComplete() {
        updateOutput(" " + "onComplete");
        print(" onComplete");
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        Exception exception = errorEvent.getException();
        Log.i(MEASURE_TAG, "onError: " + errorEvent.getMessage(), exception);
    }

    @Override
    public void onIdle(PlayerState oldState) {
        updateOutput(" " + "onIdle");
        print(" onIdle; oldState=" + String.valueOf(oldState));
    }

    @Override
    public void onPause(PlayerState oldState) {
        updateOutput(" " + "onPause");
        print(" onPause; oldState=" + String.valueOf(oldState));
    }

    @Override
    public void onPlay(PlayerState oldState) {
        updateOutput(" " + "onPlay");
        print(" onPlay; oldState=" + String.valueOf(oldState));
    }

    @Override
    public void onSeek(int position, int offset) {
        updateOutput(" " + "onSeek");
        print(" onSeek position=" + position + " offset" + offset);
    }

    @Override
    public void onSeeked() {
        updateOutput(" " + "onSeeked");
        print(" onSeeked");
    }


    @Override
    public void onBufferChange(BufferChangeEvent bufferChangeEvent) {
        updateOutput(" " + "onBufferChangeEvent");
        print(" onBufferChange; percent=" + bufferChangeEvent.getBufferPercent() +
                " position=" + bufferChangeEvent.getPosition() +
                " duration=" + bufferChangeEvent.getDuration()
        );
    }

    @Override
    public void onSetupError(String s) {
        print(" onSetupError:" + String.valueOf(s));
        updateOutput(" " + "onSetupError" + s);
    }
}
