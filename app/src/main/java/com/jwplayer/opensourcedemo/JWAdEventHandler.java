package com.jwplayer.opensourcedemo;

import android.util.Log;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Outputs all JW Player Events to logging, with the exception of time events.
 */

public class JWAdEventHandler implements
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

    private TextView mOutput;
    private ScrollView mScroll;
    private final StringBuilder outputStringBuilder = new StringBuilder();


    public JWAdEventHandler(JWPlayerView jwPlayerView, TextView output, ScrollView scrollview) {
        mScroll = scrollview;
        mOutput = output;
        mOutput.setText(outputStringBuilder.append("Build version: ").append(jwPlayerView.getVersionCode()).append("\r\n"));

        // Subscribe to all JW Player events
        jwPlayerView.addOnVisualQualityListener(this);
        jwPlayerView.addOnAdClickListener(this);
        jwPlayerView.addOnAdCompleteListener(this);
        jwPlayerView.addOnAdSkippedListener(this);
        jwPlayerView.addOnAdErrorListener(this);
        jwPlayerView.addOnAdImpressionListener(this);
        jwPlayerView.addOnAdTimeListener(this);
        jwPlayerView.addOnAdPauseListener(this);
        jwPlayerView.addOnAdPlayListener(this);
        jwPlayerView.addOnBeforePlayListener(this);
        jwPlayerView.addOnBeforeCompleteListener(this);
    }

    private void updateOutput(String output) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
        outputStringBuilder.append("").append(dateFormat.format(new Date())).append(" ").append(output).append("\r\n");
        mOutput.setText(outputStringBuilder.toString());
        mScroll.scrollTo(0, mScroll.getBottom());

    }
    private void print(String s) {
        Log.i("JWEVENTHANDLER", "(ADEVENT) "+ s);
    }

    /**
     * Regular playback events below here
     */

    @Override
    public void onBeforeComplete() {
        print("onBeforeComplete()");
        updateOutput("onBeforeComplete()");
    }

    @Override
    public void onBeforePlay() {
        print("onBeforePlay()");
        updateOutput("onBeforePlay()");
    }


    @Override
    public void onAdError(String tag, String message) {
        print("onAdError: (" + tag + "\", \"\n" + message + ")\r\n");
        updateOutput("onAdError: (" + tag + "\", \"\n" + message + ")\r\n");
    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        print("onAdClick: (" + adClickEvent.getTag() + ")\r\n");
        updateOutput("onAdClick: (" + adClickEvent.getTag() + ")\r\n");
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        print("onAdComplete: (" + adCompleteEvent.getTag() + ")\r\n");
        updateOutput("onAdComplete: (" + adCompleteEvent.getTag() + ")\r\n");
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        print("onAdSkipped: (" + adSkippedEvent.getTag() + ")\r\n");
        updateOutput("onAdSkipped: (" + adSkippedEvent.getTag() + ")\r\n");
    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        print("onAdImpression("+
                "\r\ntag: " + adImpressionEvent.getTag() +
                "\r\nMediaFile: " + adImpressionEvent.getMediaFile() +
                "\r\nCreativeType: " + adImpressionEvent.getCreativeType() +
                "\r\nClient: " + adImpressionEvent.getClient() +
                "\r\nVastVersion: " + adImpressionEvent.getVastVersion() +
                "\r\nAdPosition: " + adImpressionEvent.getAdPosition().name());
        updateOutput("onAdImpression: (" + adImpressionEvent.getTag() + "\", \"" + adImpressionEvent.getCreativeType() + "\", \"" + adImpressionEvent.getAdPosition().name() + ")\r\n");

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        // Do nothing - this fires several times per second
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        print("onAdPause: (" + adPauseEvent.getTag() + "\", \"" + adPauseEvent.getOldState() + ")\r\n");
        updateOutput("onAdPause: (" + adPauseEvent.getTag() + "\", \"" + adPauseEvent.getOldState() + ")\r\n");
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        print("onAdPlay: (" + adPlayEvent.getTag() + "\", \"" + adPlayEvent.getOldState() + ")\r\n");
        updateOutput("onAdPlay: (" + adPlayEvent.getTag() + "\", \"" + adPlayEvent.getOldState() + ")\r\n");
    }

    @Override
    public void onVisualQuality(VisualQuality visualQuality) {
        print("onVisualQuality: (" + visualQuality.getQualityLevel().getLabel() + ")\r\n");
        updateOutput("onVisualQuality: (" + visualQuality.getQualityLevel().getLabel() + ")\r\n");
    }
}
