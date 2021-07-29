package com.jwplayer.opensourcedemo;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jwplayer.pub.api.JWPlayer;
import com.jwplayer.pub.api.PlayerState;
import com.jwplayer.pub.api.events.AdClickEvent;
import com.jwplayer.pub.api.events.AdCompanionsEvent;
import com.jwplayer.pub.api.events.AdCompleteEvent;
import com.jwplayer.pub.api.events.AdErrorEvent;
import com.jwplayer.pub.api.events.AdImpressionEvent;
import com.jwplayer.pub.api.events.AdPauseEvent;
import com.jwplayer.pub.api.events.AdPlayEvent;
import com.jwplayer.pub.api.events.AdRequestEvent;
import com.jwplayer.pub.api.events.AdScheduleEvent;
import com.jwplayer.pub.api.events.AdSkippedEvent;
import com.jwplayer.pub.api.events.AdStartedEvent;
import com.jwplayer.pub.api.events.AdTimeEvent;
import com.jwplayer.pub.api.events.AudioTrackChangedEvent;
import com.jwplayer.pub.api.events.AudioTracksEvent;
import com.jwplayer.pub.api.events.BeforeCompleteEvent;
import com.jwplayer.pub.api.events.BeforePlayEvent;
import com.jwplayer.pub.api.events.BufferChangeEvent;
import com.jwplayer.pub.api.events.BufferEvent;
import com.jwplayer.pub.api.events.CaptionsChangedEvent;
import com.jwplayer.pub.api.events.CaptionsListEvent;
import com.jwplayer.pub.api.events.CompleteEvent;
import com.jwplayer.pub.api.events.ControlBarVisibilityEvent;
import com.jwplayer.pub.api.events.ControlsEvent;
import com.jwplayer.pub.api.events.DisplayClickEvent;
import com.jwplayer.pub.api.events.ErrorEvent;
import com.jwplayer.pub.api.events.EventType;
import com.jwplayer.pub.api.events.FirstFrameEvent;
import com.jwplayer.pub.api.events.FullscreenEvent;
import com.jwplayer.pub.api.events.IdleEvent;
import com.jwplayer.pub.api.events.LevelsChangedEvent;
import com.jwplayer.pub.api.events.LevelsEvent;
import com.jwplayer.pub.api.events.MetaEvent;
import com.jwplayer.pub.api.events.MuteEvent;
import com.jwplayer.pub.api.events.PauseEvent;
import com.jwplayer.pub.api.events.PlayEvent;
import com.jwplayer.pub.api.events.PlaylistCompleteEvent;
import com.jwplayer.pub.api.events.PlaylistEvent;
import com.jwplayer.pub.api.events.PlaylistItemEvent;
import com.jwplayer.pub.api.events.RelatedCloseEvent;
import com.jwplayer.pub.api.events.RelatedOpenEvent;
import com.jwplayer.pub.api.events.RelatedPlayEvent;
import com.jwplayer.pub.api.events.SeekEvent;
import com.jwplayer.pub.api.events.SeekedEvent;
import com.jwplayer.pub.api.events.SetupErrorEvent;
import com.jwplayer.pub.api.events.TimeEvent;
import com.jwplayer.pub.api.events.VisualQualityEvent;
import com.jwplayer.pub.api.events.listeners.AdvertisingEvents;
import com.jwplayer.pub.api.events.listeners.RelatedPluginEvents;
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents;
import com.jwplayer.pub.api.media.adaptive.QualityLevel;
import com.jwplayer.pub.api.media.ads.AdCompanion;
import com.jwplayer.pub.api.media.ads.AdPosition;
import com.jwplayer.pub.api.media.ads.VmapAdBreak;
import com.jwplayer.pub.api.media.audio.AudioTrack;
import com.jwplayer.pub.api.media.captions.Caption;
import com.jwplayer.pub.api.media.meta.Metadata;
import com.jwplayer.pub.api.media.playlists.PlaylistItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Outputs all JW Player Events to your console and the Log TextView
 * <p>
 * Steps to edit class:
 * 1) implement event listener
 * 2) create checkbox in view_callback_screen.xml
 * 3) create a reference to view in CallbackScreen class
 * 4) set a onCheckChangeListener for the checkbox
 * 5) add or remove player listeners depending on checkbox state
 * 6) add checkbox to mCallbacksList for easy select all
 */
public class CallbackScreen extends LinearLayout implements
												 VideoPlayerEvents.OnIdleListener,
												 VideoPlayerEvents.OnBufferListener,
												 VideoPlayerEvents.OnPlayListener,
												 VideoPlayerEvents.OnPauseListener,
												 VideoPlayerEvents.OnTimeListener,
												 VideoPlayerEvents.OnCompleteListener,
												 VideoPlayerEvents.OnErrorListener,
												 VideoPlayerEvents.OnSeekListener,
												 VideoPlayerEvents.OnFullscreenListener,
												 VideoPlayerEvents.OnAudioTracksListener,
												 VideoPlayerEvents.OnAudioTrackChangedListener,
												 VideoPlayerEvents.OnLevelsListener,
												 VideoPlayerEvents.OnLevelsChangedListener,
												 VideoPlayerEvents.OnSeekedListener,
												 VideoPlayerEvents.OnFirstFrameListener,
												 VideoPlayerEvents.OnDisplayClickListener,
												 VideoPlayerEvents.OnPlaylistCompleteListener,
												 VideoPlayerEvents.OnMetaListener,
												 VideoPlayerEvents.OnCaptionsChangedListener,
												 VideoPlayerEvents.OnCaptionsListListener,
												 VideoPlayerEvents.OnPlaylistItemListener,
												 VideoPlayerEvents.OnPlaylistListener,
												 VideoPlayerEvents.OnSetupErrorListener,
												 VideoPlayerEvents.OnMuteListener,
												 VideoPlayerEvents.OnVisualQualityListener,
												 VideoPlayerEvents.OnControlsListener,
												 VideoPlayerEvents.OnBufferChangeListener,
												 RelatedPluginEvents.OnRelatedCloseListener,
												 RelatedPluginEvents.OnRelatedOpenListener,
												 RelatedPluginEvents.OnRelatedPlayListener,
												 VideoPlayerEvents.OnControlBarVisibilityListener,
												 AdvertisingEvents.OnAdTimeListener,
												 AdvertisingEvents.OnAdScheduleListener,
												 AdvertisingEvents.OnAdPlayListener,
												 AdvertisingEvents.OnAdPauseListener,
												 AdvertisingEvents.OnAdImpressionListener,
												 AdvertisingEvents.OnAdClickListener,
												 AdvertisingEvents.OnAdErrorListener,
												 AdvertisingEvents.OnAdCompleteListener,
												 AdvertisingEvents.OnAdSkippedListener,
												 AdvertisingEvents.OnAdCompanionsListener,
												 AdvertisingEvents.OnAdRequestListener,
												 AdvertisingEvents.OnBeforeCompleteListener,
												 AdvertisingEvents.OnBeforePlayListener,
												 AdvertisingEvents.OnAdStartedListener {


	// CALLBACKS
	private TextView mCallbackLog;
	private TextView mCallbackPlayerVersion;
	private ArrayList<CheckBox> mCallbacksList;
	private CheckBox mCheckAll;
	private CheckBox mOnIdleCheckBox;
	private CheckBox mOnBufferingCheckBox;
	private CheckBox mOnPlayCheckBox;
	private CheckBox mOnPauseCheckBox;
	private CheckBox mOnCompleteCheckBox;
	private CheckBox mOnErrorCheckBox;
	private CheckBox mOnFullscreenCheckBox;
	private CheckBox mOnSeekCheckBox;
	private CheckBox mOnTimeCheckBox;
	private CheckBox mOnAdPlayCheckBox;
	private CheckBox mOnAdPauseCheckBox;
	private CheckBox mOnAdCompleteCheckBox;
	private CheckBox mOnAdErrorCheckBox;
	private CheckBox mOnAdSkipCheckBox;
	private CheckBox mOnAdTimeCheckBox;
	private CheckBox mOnAdClickCheckBox;
	private CheckBox mOnAdImpressionCheckBox;
	private CheckBox mOnAdRequestCheckBox;
	private CheckBox mOnAudioTracksCheckBox;
	private CheckBox mOnAudioTracksChangedCheckBox;
	private CheckBox mOnLevelsCheckBox;
	private CheckBox mOnLevelsChangedCheckBox;
	private CheckBox mOnSeekedCheckBox;
	private CheckBox mOnFirstFrameCheckBox;
	private CheckBox mOnDisplayClickCheckBox;
	private CheckBox mOnPlaylistCompleteCheckBox;
	private CheckBox mOnMetaCheckBox;
	private CheckBox mOnCaptionsChangedCheckBox;
	private CheckBox mOnCaptionsListCheckBox;
	private CheckBox mOnPlaylistItemCheckBox;
	private CheckBox mOnPlaylistCheckBox;
	private CheckBox mOnSetupErrorCheckBox;
	private CheckBox mOnBeforeCompleteCheckBox;
	private CheckBox mOnBeforePlayCheckBox;
	private CheckBox mOnMuteCheckBox;
	private CheckBox mOnVisualQualityCheckBox;
	private CheckBox mOnAdStartedCheckBox;
	private CheckBox mTimeSeconds;
	private CheckBox mOnControls;
	private CheckBox mOnControlbarVisibilityChanged;
	private CheckBox mOnBufferChange;
	private CheckBox mOnRelatedClose;
	private CheckBox mOnRelatedOpen;
	private CheckBox mOnRelatedPlay;
	private CheckBox mOnAdCompanion;
	private CheckBox mOnAdSchedule;

	private JWPlayer mPlayer;

	private boolean timeInSeconds = false;


	public CallbackScreen(Context context) {
		super(context);
		inflate(getContext(), R.layout.view_callback_screen, this);
	}

	public CallbackScreen(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		inflate(getContext(), R.layout.view_callback_screen, this);
	}

	public CallbackScreen(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(getContext(), R.layout.view_callback_screen, this);
	}

	private void setOutput(String output) {
		Log.d("CallbackScreen", output);
		mCallbackLog.append(output + "\n\n");
	}


	@Override
	public void onAdRequest(AdRequestEvent event) {
		String client = event.getClient().name();
		String tag = event.getTag();
		AdPosition adPosition = event.getAdPosition();
		String offset = event.getOffset();


		String output = "/// onAdRequest START ///" + "\n" +
				"Tag:" + "\n" +
				tag + "\n" +
				"Ad Client:" + "\n" +
				client + "\n" +
				"Ad Position:" + "\n" +
				adPosition + "\n" +
				"Ad Offset:" + "\n" +
				offset + "\n" +
				"/// onAdRequest END ///";
		setOutput(output);
	}


	@Override
	public void onAdSkipped(AdSkippedEvent event) {
		String client = event.getClient().name();
		String creativeType = event.getCreativeType();
		String tag = event.getTag();
		String output = "/// onAdSkipped START ///" + "\n" +
				"Tag:" + "\n" +
				tag + "\n" +
				"Creative Type:" + "\n" +
				creativeType + "\n" +
				"Ad Client:" + "\n" +
				client + "\n" +
				"/// onAdSkipped END ///";
		setOutput(output);
	}


	@Override
	public void onAdClick(AdClickEvent event) {
		String creativeType = event.getCreativeType();
		String tag = event.getTag();
		String client = event.getClient().name();

		String output = "/// onAdClick START ///" + "\n" +
				"Tag:" + "\n" +
				tag + "\n" +
				"Creative Type:" + "\n" +
				creativeType + "\n" +
				"Client:" + "\n" +
				client + "\n" +
				"/// onAdClick END ///";
		setOutput(output);
	}

	@Override
	public void onAdImpression(AdImpressionEvent event) {
		String creativeType = event.getCreativeType();
		String tag = event.getTag();
		String client = event.getClient().name();
		// AdPosition is an enum now and can return pre, mid, post
		String adPosition = event.getAdPosition().name();
		String adSystem = event.getAdSystem();
		String adTitle = event.getAdTitle();
		String linear = event.getLinear();
		String mediaFile = event.getMediaFile() != null ? event.getMediaFile().getFile() : "";
		String vastVersion = event.getVastVersion();
		String clickThroughUrl = event.getClickThroughUrl();


		String output = "/// onAdImpression START ///" + "\n" +
				"Tag:" + "\n" +
				tag + "\n" +
				"Creative Type:" + "\n" +
				creativeType + "\n" +
				"Client:" + "\n" +
				client + "\n" +
				"Ad Position:" + "\n" +
				adPosition + "\n" +
				"Ad System:" + "\n" +
				adSystem + "\n" +
				"Ad Title:" + "\n" +
				adTitle + "\n" +
				"Linear:" + "\n" +
				linear + "\n" +
				"Media File:" + "\n" +
				mediaFile + "\n" +
				"Vast Version:" + "\n" +
				vastVersion + "\n" +
				"Clickthrough URL:" + "\n" +
				clickThroughUrl + "\n" +
				"/// onAdImpression END ///";
		setOutput(output);
	}

	@Override
	public void onAdPause(AdPauseEvent event) {
		PlayerState oldState = event.getOldState();
		PlayerState currentState = event.getNewState();
		String tag = event.getTag();
		String creativeType = event.getCreativeType();

		String output = "/// onAdPause START ///" + "\n" +
				"Tag:" + "\n" +
				tag + "\n" +
				"Creative Type:" + "\n" +
				creativeType + "\n" +
				"Previous Player State:" + "\n" +
				oldState.name() + "\n" +
				"Current Player State:" + "\n" +
				currentState.name() + "\n" +
				"/// onAdPause END ///";
		setOutput(output);
	}

	@Override
	public void onAdPlay(AdPlayEvent event) {
		String tag = event.getTag();
		String creativeType = event.getCreativeType();
		PlayerState oldState = event.getOldState();
		PlayerState currentState = event.getNewState();

		String output = "/// onAdPlay START ///" + "\n" +
				"Tag:" + "\n" +
				tag + "\n" +
				"Creative Type:" + "\n" +
				creativeType + "\n" +
				"Previous Player State:" + "\n" +
				oldState.name() + "\n" +
				"Current Player State:" + "\n" +
				currentState.name() + "\n" +
				"/// onAdPlay END ///";
		setOutput(output);
	}

	private double mOldAdTimeSeconds = 0;
	private double mOldAdDurationSeconds = 0;

	@Override
	public void onAdTime(AdTimeEvent event) {
		String creativeType = event.getCreativeType();
		int sequence = event.getSequence();
		String tag = event.getTag();
		double position = event.getPosition();
		double duration = event.getDuration();

		if (mTimeSeconds.isChecked()) {
			double adTimeSeconds = Math.floor(position / 1000);
			double adDurationSeconds = Math.floor(duration / 1000);

			if (mOldAdTimeSeconds != adTimeSeconds || mOldAdDurationSeconds != adDurationSeconds) {

				mOldAdTimeSeconds = adTimeSeconds;
				mOldAdDurationSeconds = adDurationSeconds;

				String output = "/// onAdTime START ///" + "\n" +
						"Creative Type:" + "\n" +
						creativeType + "\n" +
						"Tag:" + "\n" +
						tag + "\n" +
						"Position:" + "\n" +
						adTimeSeconds + "s" + "\n" +
						"Duration:" + "\n" +
						adDurationSeconds + "s" + "\n" +
						"Sequence:" + "\n" +
						sequence + "\n" +
						"/// onAdTime END ///";
				setOutput(output);
			}
		} else {
			String output = "/// onAdTime START ///" + "\n" +
					"Creative Type:" + "\n" +
					creativeType + "\n" +
					"Tag:" + "\n" +
					tag + "\n" +
					"Position:" + "\n" +
					position + "s" + "\n" +
					"Duration:" + "\n" +
					duration + "s" + "\n" +
					"Sequence:" + "\n" +
					sequence + "\n" +
					"/// onAdTime END ///";
			setOutput(output);
		}
	}


	@Override
	public void onAdComplete(AdCompleteEvent event) {
		String client = event.getClient().name();
		String creativeType = event.getCreativeType();
		String tag = event.getTag();

		String output = "/// onAdComplete START ///" + "\n" +
				"Tag:" + "\n" +
				tag + "\n" +
				"Creative Type:" + "\n" +
				creativeType + "\n" +
				"Ad Client:" + "\n" +
				client + "\n" +
				"/// onAdComplete END ///";
		setOutput(output);
	}


	@Override
	public void onError(ErrorEvent event) {
		String message = event.getMessage();
		String output = "/// onError START ///" + "\n" +
				"Error Message:" + "\n" +
				message + "\n" +
				"/// onError END ///";
		setOutput(output);
	}


	public void registerListeners(JWPlayer player) {

		mPlayer = player;

		mCallbackPlayerVersion = findViewById(R.id.callback_player_version);
		mCallbackPlayerVersion.setText("Player Version: " + mPlayer.getVersionCode());

		mCallbackLog = findViewById(R.id.callback_status_tv);
		mCallbackLog.setMovementMethod(new ScrollingMovementMethod());


		//         This handles clearing the log
		findViewById(R.id.callback_clear_btn).setOnClickListener(v -> mCallbackLog.setText(""));


		mTimeSeconds = findViewById(R.id.callback_time_seconds);
		mTimeSeconds.setOnCheckedChangeListener((buttonView, isChecked) -> timeInSeconds = isChecked);

		mOnCompleteCheckBox = findViewById(R.id.callback_on_complete_check);
		mOnCompleteCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.COMPLETE,this)
		);
		mOnErrorCheckBox = findViewById(R.id.callback_on_error_check);
		mOnErrorCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.ERROR,this)
		);
		mOnFullscreenCheckBox = findViewById(R.id.callback_on_fullscreen_check);
		mOnFullscreenCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.FULLSCREEN,this)
		);
		mOnSeekCheckBox = findViewById(R.id.callback_on_seek_check);
		mOnSeekCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.SEEK,this)
		);
		mOnSeekedCheckBox = findViewById(R.id.callback_on_seeked_check);
		mOnSeekedCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.SEEKED,this)
		);
		mOnIdleCheckBox = findViewById(R.id.callback_on_idle_check);
		mOnIdleCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.IDLE,this)
		);
		mOnBufferingCheckBox = findViewById(R.id.callback_on_buffer_check);
		mOnBufferingCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.BUFFER,this)
		);
		mOnPlayCheckBox = findViewById(R.id.callback_on_play_check);
		mOnPlayCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.PLAY,this)
		);
		mOnPauseCheckBox = findViewById(R.id.callback_on_pause_check);
		mOnPauseCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.PAUSE,this)
		);
		mOnTimeCheckBox = findViewById(R.id.callback_on_time_check);
		mOnTimeCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.TIME,this)
		);
		mOnAdCompleteCheckBox = findViewById(R.id.callback_ad_on_complete_check);
		mOnAdCompleteCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_COMPLETE,this)
		);
		mOnAdErrorCheckBox = findViewById(R.id.callback_ad_on_error_check);
		mOnAdErrorCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_ERROR,this)
		);
		mOnAdSkipCheckBox = findViewById(R.id.callback_ad_on_skip_check);
		mOnAdSkipCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_SKIPPED,this)
		);
		mOnAdPlayCheckBox = findViewById(R.id.callback_ad_on_play_check);
		mOnAdPlayCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_PLAY,this)
		);
		mOnAdPauseCheckBox = findViewById(R.id.callback_ad_on_pause_check);
		mOnAdPauseCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_PAUSE,this)
		);
		mOnAdTimeCheckBox = findViewById(R.id.callback_ad_on_time_check);
		mOnAdTimeCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_TIME,this)
		);
		mOnAdImpressionCheckBox = findViewById(R.id.callback_ad_on_impression_check);
		mOnAdImpressionCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_IMPRESSION,this)
		);
		mOnAdClickCheckBox = findViewById(R.id.callback_ad_on_click_check);
		mOnAdClickCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_CLICK,this)
		);
		mOnAdRequestCheckBox = findViewById(R.id.callback_ad_on_request_check);
		mOnAdRequestCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_REQUEST,this)
		);
		mOnAudioTracksCheckBox = findViewById(R.id.callback_on_audio_tracks_check);
		mOnAudioTracksCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AUDIO_TRACKS,this)
		);
		mOnAudioTracksChangedCheckBox = findViewById(R.id.callback_on_audio_tracks_changed_check);
		mOnAudioTracksChangedCheckBox.setOnCheckedChangeListener(
						new EventCheckChangedListener(mPlayer,EventType.AUDIO_TRACK_CHANGED,this)
		);
		mOnLevelsCheckBox = findViewById(R.id.callback_on_levels_check);
		mOnLevelsCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.LEVELS,this)
		);
		mOnLevelsChangedCheckBox = findViewById(R.id.callback_on_levels_changed_check);
		mOnLevelsChangedCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.LEVELS_CHANGED,this)
		);
		mOnFirstFrameCheckBox = findViewById(R.id.callback_on_first_frame_check);
		mOnFirstFrameCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.FIRST_FRAME,this)
		);
		mOnDisplayClickCheckBox = findViewById(R.id.callback_on_display_click_check);
		mOnDisplayClickCheckBox.setOnCheckedChangeListener(
						new EventCheckChangedListener(mPlayer,EventType.DISPLAY_CLICK,this)
		);
		mOnPlaylistCompleteCheckBox = findViewById(R.id.callback_on_playlist_complete_check);
		mOnPlaylistCompleteCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.PLAYLIST_COMPLETE,this)
		);
		mOnMetaCheckBox = findViewById(R.id.callback_on_meta_check);
		mOnMetaCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.META,this)
		);
		mOnCaptionsChangedCheckBox = findViewById(R.id.callback_on_captions_changed_check);
		mOnCaptionsChangedCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.CAPTIONS_CHANGED,this)
		);
		mOnCaptionsListCheckBox = findViewById(R.id.callback_on_captions_list_check);
		mOnCaptionsListCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.CAPTIONS_LIST,this)
		);
		mOnPlaylistItemCheckBox = findViewById(R.id.callback_on_playlist_item_check);
		mOnPlaylistItemCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.PLAYLIST_ITEM,this)
		);
		mOnPlaylistCheckBox = findViewById(R.id.callback_on_playlist_check);
		mOnPlaylistCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.PLAYLIST,this)
		);
		mOnSetupErrorCheckBox = findViewById(R.id.callback_on_setup_error_check);
		mOnSetupErrorCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.SETUP_ERROR,this)
		);
		mOnBeforeCompleteCheckBox = findViewById(R.id.callback_ad_on_before_complete_check);
		mOnBeforeCompleteCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.BEFORE_COMPLETE,this)
		);
		mOnBeforePlayCheckBox = findViewById(R.id.callback_ad_on_before_play_check);
		mOnBeforePlayCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.BEFORE_PLAY,this)
		);
		mOnMuteCheckBox = findViewById(R.id.callback_on_mute_check);
		mOnMuteCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.MUTE,this)
		);
		mOnVisualQualityCheckBox = findViewById(R.id.callback_on_visual_quality_check);
		mOnVisualQualityCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.VISUAL_QUALITY,this)
		);
		mOnAdStartedCheckBox = findViewById(R.id.callback_ad_on_started_check);
		mOnAdStartedCheckBox.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_STARTED,this)
		);
		mOnControls = findViewById(R.id.callback_on_controls_check);
		mOnControls.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.CONTROLS,this)
		);
		mOnBufferChange = findViewById(R.id.callback_on_buffer_change_check);
		mOnBufferChange.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.BUFFER_CHANGE,this)
		);
		mOnRelatedClose = findViewById(R.id.callback_on_related_close_check);
		mOnRelatedClose.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.RELATED_CLOSE,this)
		);
		mOnRelatedOpen = findViewById(R.id.callback_on_related_open_check);
		mOnRelatedOpen.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.RELATED_OPEN,this)
		);
		mOnRelatedPlay = findViewById(R.id.callback_on_related_play_check);
		mOnRelatedPlay.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.RELATED_PLAY,this)
		);
		mOnAdCompanion = findViewById(R.id.callback_ad_on_companion_check);
		mOnAdCompanion.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_COMPANIONS,this)
		);
		mOnControlbarVisibilityChanged = findViewById(R.id.callback_on_controlbar_visibility_changed_check);
		mOnControlbarVisibilityChanged.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.CONTROLBAR_VISIBILITY,this)
		);
		mOnAdSchedule = findViewById(R.id.callback_ad_on_schedule_check);
		mOnAdSchedule.setOnCheckedChangeListener(
				new EventCheckChangedListener(mPlayer,EventType.AD_SCHEDULE,this)
		);

		// handles unchecking all boxes
		mCallbacksList = new ArrayList<>();
		mCallbacksList.add(mOnCompleteCheckBox);
		mCallbacksList.add(mOnErrorCheckBox);
		mCallbacksList.add(mOnFullscreenCheckBox);
		mCallbacksList.add(mOnSeekCheckBox);
		mCallbacksList.add(mOnSeekedCheckBox);
		mCallbacksList.add(mOnIdleCheckBox);
		mCallbacksList.add(mOnBufferingCheckBox);
		mCallbacksList.add(mOnPlayCheckBox);
		mCallbacksList.add(mOnPauseCheckBox);
		mCallbacksList.add(mOnTimeCheckBox);
		mCallbacksList.add(mOnAdCompleteCheckBox);
		mCallbacksList.add(mOnAdErrorCheckBox);
		mCallbacksList.add(mOnAdSkipCheckBox);
		mCallbacksList.add(mOnAdPlayCheckBox);
		mCallbacksList.add(mOnAdPauseCheckBox);
		mCallbacksList.add(mOnAdTimeCheckBox);
		mCallbacksList.add(mOnAdImpressionCheckBox);
		mCallbacksList.add(mOnAdClickCheckBox);
		mCallbacksList.add(mOnAdRequestCheckBox);
		mCallbacksList.add(mOnAudioTracksCheckBox);
		mCallbacksList.add(mOnAudioTracksChangedCheckBox);
		mCallbacksList.add(mOnLevelsCheckBox);
		mCallbacksList.add(mOnLevelsChangedCheckBox);
		mCallbacksList.add(mOnFirstFrameCheckBox);
		mCallbacksList.add(mOnDisplayClickCheckBox);
		mCallbacksList.add(mOnPlaylistCompleteCheckBox);
		mCallbacksList.add(mOnMetaCheckBox);
		mCallbacksList.add(mOnCaptionsChangedCheckBox);
		mCallbacksList.add(mOnCaptionsListCheckBox);
		mCallbacksList.add(mOnPlaylistItemCheckBox);
		mCallbacksList.add(mOnPlaylistCheckBox);
		mCallbacksList.add(mOnSetupErrorCheckBox);
		mCallbacksList.add(mOnBeforeCompleteCheckBox);
		mCallbacksList.add(mOnBeforePlayCheckBox);
		mCallbacksList.add(mOnMuteCheckBox);
		mCallbacksList.add(mOnVisualQualityCheckBox);
		mCallbacksList.add(mOnAdStartedCheckBox);
		mCallbacksList.add(mOnControls);
		mCallbacksList.add(mOnBufferChange);
		mCallbacksList.add(mOnRelatedClose);
		mCallbacksList.add(mOnRelatedOpen);
		mCallbacksList.add(mOnRelatedPlay);
		mCallbacksList.add(mOnAdCompanion);
		mCallbacksList.add(mOnControlbarVisibilityChanged);
		mCallbacksList.add(mOnAdSchedule);

		// This handles unchecking all checkboxes
		mCheckAll = findViewById(R.id.callback_check_all);
		mCheckAll = findViewById(R.id.callback_check_all);
		mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				for (CheckBox currectCheckBox : mCallbacksList) {
					currectCheckBox.setChecked(isChecked);
				}
			}
		});


	}

	private void updateListeners() {
		for (CheckBox currectCheckBox : mCallbacksList) {
			if (currectCheckBox.isChecked()) {
				currectCheckBox.setChecked(false);
				currectCheckBox.setChecked(true);
			}
		}
	}

	public String getCallbackLog() {
		return mCallbackLog.getText().toString();
	}


	@Override
	public void onControls(ControlsEvent controlsEvent) {
		String prefix = "/// onControls START ///" + "\n";

		String controls = "Controls = " + controlsEvent.getControls() + "\n";
		String event = "ControlsEvent = " + controlsEvent.toString() + "\n";

		String suffix = "\n" + "/// onControls END ///";
		setOutput(prefix + controls + event + suffix);
	}

	@Override
	public void onBufferChange(BufferChangeEvent bufferChangeEvent) {
		String prefix = "/// onBufferChange START ///" + "\n";

		String percentage = "Buffer Percentage = " + bufferChangeEvent.getBufferPercent() + "\n";
		String duration = "Duration = " + bufferChangeEvent.getDuration() + "\n";
		String position = "Position = " + bufferChangeEvent.getPosition() + "\n";

		String suffix = "\n" + "/// onBufferChange END ///";
		setOutput(prefix + percentage + duration + position + suffix);
	}

	@Override
	public void onRelatedClose(RelatedCloseEvent relatedCloseEvent) {
		String prefix = "/// onRelatedClose START ///" + "\n";

		String method = "Method = " + relatedCloseEvent.getMethod() + "\n";

		String suffix = "\n" + "/// onRelatedClose END ///";
		setOutput(prefix + method + suffix);
	}

	private String playlistToString(List<PlaylistItem> playlist) {
		String result = "[\n";

		for (PlaylistItem item : playlist) {
			result += item.getFile();
			result += ",";
		}

		result += "\n]";
		return result;
	}

	@Override
	public void onRelatedOpen(RelatedOpenEvent relatedOpenEvent) {
		String prefix = "/// onRelatedOpen START ///" + "\n";

		String method = "Method = " + relatedOpenEvent.getMethod() + "\n";
		String file = "File = " + relatedOpenEvent.getUrl() + "\n";

		String playlist = "Playlist = " + playlistToString(relatedOpenEvent.getItems()) + "\n";

		String suffix = "\n" + "/// onRelatedOpen END ///";
		setOutput(prefix + method + file + playlist + suffix);
	}

	@Override
	public void onRelatedPlay(RelatedPlayEvent relatedPlayEvent) {
		String prefix = "/// onRelatedPlay START ///" + "\n";

		String auto = "Auto = " + relatedPlayEvent.getAuto() + "\n";
		String item = "";
		item = "Item = " + relatedPlayEvent.getItem().getFile() + "\n";


		String suffix = "\n" + "/// onRelatedPlay END ///";
		setOutput(prefix + auto + item + suffix);
	}


	public void updateJWPlayerView(JWPlayer player) {
		this.mPlayer = player;
		updateListeners();
	}

	@Override
	public void onControlBarVisibilityChanged(ControlBarVisibilityEvent controlBarVisibilityEvent) {
		String prefix = "/// onControlBarVisibilityChanged START ///" + "\n";

		String isVisible = "isVisible = " + controlBarVisibilityEvent.isVisible();

		String suffix = "\n" + "/// onControlBarVisibilityChanged END ///";
		setOutput(prefix + isVisible + suffix);
	}

	@Override
	public void onAdSchedule(AdScheduleEvent adScheduleEvent) {
		String prefix = "/// onAdSchedule START ///" + "\n";

		String tag = "Tag = " + adScheduleEvent.getTag() + "\n";
		String client = "Client = " + adScheduleEvent.getClient().name() + "\n";

		String json = "";
		List<VmapAdBreak> adCompanionList = adScheduleEvent.getVmapAdBreaks();
		StringBuilder stringBuilder = new StringBuilder();
		for (VmapAdBreak vmapAdBreak : adCompanionList) {
			stringBuilder.append(vmapAdBreak.getVMapInfo().getId());
		}

		json = "List<VMAPAdBreak> = \n" + stringBuilder.toString() + "\n";


		String suffix = "\n" + "/// onAdSchedule END ///";
		setOutput(prefix + client + tag + json + suffix);
	}

	@Override
	public void onAdCompanions(AdCompanionsEvent adCompanionsEvent) {
		String prefix = "/// onAdCompanion START ///" + "\n";

		String tag = "Tag = " + adCompanionsEvent.getTag() + "\n";

		String item = "";
		try {
			List<AdCompanion> adCompanionList = adCompanionsEvent.getCompanions();
			JSONArray jsonArray = new JSONArray();
			for (AdCompanion adCompanion : adCompanionList) {
				JSONObject companionJson = new JSONObject();
				companionJson.put("height:", adCompanion.getHeight());
				companionJson.put("width:", adCompanion.getWidth());
				companionJson.put("type:", adCompanion.getType());
				companionJson.put("resource:", adCompanion.getResource());
				companionJson.put("click:", adCompanion.getClick());
				companionJson.put("creatives", adCompanion.getCreativeViews());
				jsonArray.put(companionJson);
			}

			item = "Ad Companions = \n" + jsonArray.toString(4) + "\n";
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String suffix = "\n" + "/// onAdCompanion END ///";
		setOutput(prefix + tag + item + suffix);
	}

	@Override
	public void onAdError(AdErrorEvent adErrorEvent) {
		String output = "/// onAdError START ///" + "\n" +
				"Message:" + "\n" +
				adErrorEvent.getMessage() + "\n" +
				"Tag:" + "\n" +
				adErrorEvent.getTag() + "\n" +
				"/// onAdError END ///";
		setOutput(output);
	}

	@Override
	public void onAdStarted(AdStartedEvent adStartedEvent) {
		String output = "/// onAdStarted START ///" + "\n" +
				"Creative Type:" + "\n" +
				adStartedEvent.getCreativeType() + "\n" +
				"Tag:" + "\n" +
				adStartedEvent.getTag() + "\n" +
				"/// onAdStarted END ///";
		setOutput(output);
	}

	@Override
	public void onBeforeComplete(BeforeCompleteEvent beforeCompleteEvent) {
		String output = "/// onBeforeComplete START ///" + "\n" +
				"/// onBeforeComplete END ///";
		setOutput(output);
	}

	@Override
	public void onBeforePlay(BeforePlayEvent beforePlayEvent) {
		String output = "/// onBeforePlay START ///" + "\n" +
				"/// onBeforePlay END ///";
		setOutput(output);
	}

	@Override
	public void onAudioTrackChanged(AudioTrackChangedEvent audioTrackChangedEvent) {
		String output = "/// onAudioTrackChanged START ///" + "\n" +
				"Current AudioTrack Index:" + "\n" +
				audioTrackChangedEvent.getCurrentTrack() + "\n" +
				"/// onAudioTrackChanged END ///";
		setOutput(output);
	}

	@Override
	public void onAudioTracks(AudioTracksEvent audioTracksEvent) {
		String prefix = "/// onAudioTracks START ///" + "\n";

		StringBuilder stringBuilder = new StringBuilder();
		for (AudioTrack currentTrack : audioTracksEvent.getAudioTracks()) {
			String separator = "-------------" + "\n";
			String name = "Name: " + currentTrack.getName() + "\n";
			String lang = "Language: " + currentTrack.getLanguage() + "\n";
			String groupId = "Group ID: " + currentTrack.getGroupId() + "\n";
			String autoSelect = "Autoselect: " + currentTrack.isAutoSelect() + "\n";
			String defaultTrack = "Default: " + currentTrack.isDefaultTrack() + "\n";
			stringBuilder.append(name)
						 .append(lang)
						 .append(groupId)
						 .append(autoSelect)
						 .append(defaultTrack)
						 .append(separator);
		}

		String suffix = "\n" + "/// onAudioTracks END ///";
		setOutput(prefix + stringBuilder.toString() + suffix);
	}

	@Override
	public void onBuffer(BufferEvent bufferEvent) {
		String output = "/// onBuffer START ///" + "\n" +
				"Previous Player State:" + "\n" +
				bufferEvent.getOldState().name() + "\n" +
				"/// onBuffer END ///";
		setOutput(output);
	}

	@Override
	public void onCaptionsChanged(CaptionsChangedEvent captionsChangedEvent) {
		String output = "/// onCaptionsChanged START ///" + "\n" +
				"Current Caption Index:" + "\n" +
				captionsChangedEvent.getCurrentTrack() + "\n" +
				"/// onCaptionsChanged END ///";
		setOutput(output);
	}

	@Override
	public void onCaptionsList(CaptionsListEvent captionsListEvent) {
		String prefix = "/// onCaptionsList START ///" + "\n";

		StringBuilder stringBuilder = new StringBuilder();
		for (Caption currentCaption : captionsListEvent.getCaptions()) {
			String separator = "-------------" + "\n";
			String isDefault = "";
			String file = "";
			String label = "";
			String kind = "";
			if (currentCaption.getFile() != null) {
				file = "File: " + currentCaption.getFile() + "\n";
			}
			if (currentCaption.getLabel() != null) {
				label = "Label: " + currentCaption.getLabel() + "\n";
			}
			if (currentCaption.getKind() != null) {
				kind = "Kind: " + currentCaption.getKind().name() + "\n";
			}
			isDefault = "isDefault: " + currentCaption.isDefault() + "\n";

			stringBuilder.append(file).append(label).append(kind).append(isDefault)
						 .append(separator);
		}

		String suffix = "\n" + "/// onCaptionsList END ///";
		setOutput(prefix + stringBuilder.toString() + suffix);
	}

	@Override
	public void onComplete(CompleteEvent completeEvent) {
		String output = "/// onComplete START ///" + "\n" +
				"/// onComplete END ///";
		setOutput(output);
	}

	@Override
	public void onDisplayClick(DisplayClickEvent displayClickEvent) {
		String output = "/// onDisplayClick START ///" + "\n" +
				"/// onDisplayClick END ///";
		setOutput(output);
	}

	@Override
	public void onFirstFrame(FirstFrameEvent firstFrameEvent) {
		String output = "/// onFirstFrame START ///" + "\n" +
				"First Frame:" + "\n" +
				firstFrameEvent.getLoadTime() + "ms" + "\n" +
				"/// onFirstFrame END ///";
		setOutput(output);
	}

	@Override
	public void onFullscreen(FullscreenEvent fullscreenEvent) {
		String output = "/// onFullscreen START ///" + "\n" +
				"Fullscreen:" + "\n" +
				fullscreenEvent.getFullscreen() + "\n" +
				"/// onFullscreen END ///";
		setOutput(output);
	}

	@Override
	public void onIdle(IdleEvent idleEvent) {
		String output = "/// onIdle START ///" + "\n" +
				"Previous Player State:" + "\n" +
				idleEvent.getOldState().name() + "\n" +
				"/// onIdle END ///";
		setOutput(output);
	}

	@Override
	public void onLevelsChanged(LevelsChangedEvent levelsChangedEvent) {
		String output = "/// onLevelsChanged START ///" + "\n" +
				"Current Level Index:" + "\n" +
				levelsChangedEvent.getCurrentQualityIndex() + "\n" +
				"/// onLevelsChanged END ///";
		setOutput(output);
	}

	@Override
	public void onLevels(LevelsEvent levelsEvent) {
		String prefix = "/// onLevels START ///" + "\n";
		StringBuilder stringBuilder = new StringBuilder();

		for (QualityLevel currentLevel : levelsEvent.getLevels()) {
			String separator = "-------------" + "\n";
			String index = "Track Index: " + currentLevel.getTrackIndex() + "\n";
			String label = "Label: " + currentLevel.getLabel() + "\n";
			String bitrate = "Bitrate: " + currentLevel.getBitrate() + "\n";
			String height = "Height: " + currentLevel.getHeight() + "\n";
			String width = "Width: " + currentLevel.getWidth() + "\n";
			String playlistPosition = "Playlist Position: " + currentLevel
					.getPlaylistPosition() + "\n";
			stringBuilder.append(index).append(label).append(bitrate).append(height).append(width)
						 .append(playlistPosition).append(separator);
		}

		String suffix = "\n" + "/// onLevels END ///";
		setOutput(prefix + stringBuilder.toString() + suffix);
	}

	@Override
	public void onMeta(MetaEvent metaEvent) {
		String prefix = "/// onMeta START ///" + "\n";
		String body = "";
		Metadata metadata = metaEvent.getMetadata();
		String videoID = "Video ID: " + metadata.getVideoId() + "\n";
		String videoMimeType = "Video MimeType: " + metadata.getVideoMimeType() + "\n";
		String videoBitrate = "Video Bitrate: " + metadata.getVideoBitrate() + "\n";
		String framerate = "Framerate: " + metadata.getFramerate() + "\n";
		String droppedFrames = "Dropped Frames: " + metadata.getDroppedFrames() + "\n";
		String width = "Width: " + metadata.getWidth() + "\n";
		String height = "Height: " + metadata.getHeight() + "\n";
		String lang = "Language: " + metadata.getLanguage() + "\n";
		String audioID = "Audio ID: " + metadata.getAudioId() + "\n";
		String audioMimeType = "Audio MimeType: " + metadata.getAudioMimeType() + "\n";
		String audioBitrate = "Audio Bitrate: " + metadata.getAudioBitrate() + "\n";
		String audioChannels = "Audio Channels: " + metadata.getAudioChannels() + "\n";
		String audioSamplingRate = "Audio Sampling Rate: " + metadata.getAudioSamplingRate() + "\n";
		String meta = "Metadata:\n" + metadata.getId3Metadata().toString() + "\n";

		body += videoID + videoMimeType + videoBitrate + framerate + droppedFrames + width + height + lang + audioID + audioMimeType + audioBitrate + audioChannels + audioSamplingRate + meta;

		String suffix = "\n" + "/// onMeta END ///";

		setOutput(prefix + body + suffix);
	}

	@Override
	public void onMute(MuteEvent muteEvent) {
		String output = "/// onMute START ///" + "\n" +
				"Mute: " + "\n" +
				muteEvent.getMute() + "\n" +
				"/// onMute END ///";
		setOutput(output);
	}

	@Override
	public void onPause(PauseEvent pauseEvent) {
		String output = "/// onPause START ///" + "\n" +
				"Previous Player State:" + "\n" +
				pauseEvent.getOldState().name() + "\n" +
				"/// onPause END ///";
		setOutput(output);
	}

	@Override
	public void onPlay(PlayEvent playEvent) {
		String output = "/// onPlay START ///" + "\n" +
				"Previous Player State:" + "\n" +
				playEvent.getOldState().name() + "\n" +
				"/// onPlay END ///";
		setOutput(output);
	}

	@Override
	public void onPlaylistComplete(PlaylistCompleteEvent playlistCompleteEvent) {
		String output = "/// onPlaylistComplete START ///" + "\n" +
				"/// onPlaylistComplete END ///";
		setOutput(output);
	}

	@Override
	public void onPlaylistItem(PlaylistItemEvent playlistItemEvent) {
		String output = "/// onPlaylistItem START ///" + "\n" +
				"Current PlaylistItem Index:" + "\n" +
				playlistItemEvent.getIndex() + "\n" +
				"PlaylistItem JSON:" + "\n" +
				playlistItemEvent.getPlaylistItem().getFile() + "\n" +
				"/// onPlaylistItem END ///";
		setOutput(output);
	}

	@Override
	public void onPlaylist(PlaylistEvent playlistEvent) {
		String prefix = "/// onPlaylist START ///" + "\n";

		StringBuilder builder = new StringBuilder();
		for (PlaylistItem currentItem : playlistEvent.getPlaylist()) {
			String separator = "-------------" + "\n";
			String item = currentItem.getFile();

			builder.append(item).append(separator);
		}

		String suffix = "\n" + "/// onPlaylist END ///";
		setOutput(prefix + builder.toString() + suffix);
	}

	@Override
	public void onSeek(SeekEvent seekEvent) {
		String output = "/// onSeek START ///" + "\n" +
				"Position:" + "\n" +
				seekEvent.getPosition() + "s" + "\n" +
				"Offset:" + "\n" +
				seekEvent.getOffset() + "s" + "\n" +
				"/// onSeek END ///";
		setOutput(output);
	}

	@Override
	public void onSeeked(SeekedEvent seekedEvent) {
		String output = "/// onSeeked START ///" + "\n" +
				"Position:" + "\n" +
				seekedEvent.getPosition() + "s" + "\n" +
				"/// onSeeked END ///";
		setOutput(output);
	}

	@Override
	public void onSetupError(SetupErrorEvent setupErrorEvent) {
		String output = "/// onSetupError START ///" + "\n" +
				"Error Message:" + "\n" +
				setupErrorEvent.getMessage() + "\n" +
				"/// onSetupError END ///";
		setOutput(output);
	}

	private double mOldTime = 0;

	@Override
	public void onTime(TimeEvent timeEvent) {

		double positionSeconds = timeEvent.getPosition();
		double durationSeconds = timeEvent.getDuration();

		if (timeInSeconds) {
			double roundedSeconds = Math.floor(positionSeconds);
			if (mOldTime != roundedSeconds) {
				mOldTime = roundedSeconds;
				String output = "/// onTime START ///" + "\n" +
						"Current Position:" + "\n" +
						roundedSeconds + "s" + "\n" +
						"Total Duration:" + "\n" +
						durationSeconds + "s" + "\n" +
						"/// onTime END ///";

				setOutput(output);
			}
		} else {
			String output = "/// onTime START ///" + "\n" +
					"Current Position:" + "\n" +
					positionSeconds + "s" + "\n" +
					"Total Duration:" + "\n" +
					durationSeconds + "s" + "\n" +
					"/// onTime END ///";

			setOutput(output);
		}
	}

	@Override
	public void onVisualQuality(VisualQualityEvent visualQualityEvent) {
		String prefix = "/// onVisualQuality START ///" + "\n";

		String mode = "Mode = " + visualQualityEvent.getMode().name() + "\n";
		String qualityLevel = "Quality Level = " + visualQualityEvent.getQualityLevel().getLabel() + "\n";
		String reason = "Reason = " + visualQualityEvent.getReason().name();

		String suffix = "\n" + "/// onVisualQuality END ///";
		setOutput(prefix + mode + qualityLevel + reason + suffix);
	}
}