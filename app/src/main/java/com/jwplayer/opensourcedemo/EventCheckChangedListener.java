package com.jwplayer.opensourcedemo;

import android.widget.CompoundButton;

import com.jwplayer.pub.api.JWPlayer;
import com.jwplayer.pub.api.events.EventListener;
import com.jwplayer.pub.api.events.EventType;

public class EventCheckChangedListener implements CompoundButton.OnCheckedChangeListener {

    private final JWPlayer mPlayer;
    private final EventType mEventType;
    private final EventListener mEventListener;

    public EventCheckChangedListener(JWPlayer player,
                                     EventType eventType,
                                     EventListener eventListener) {
        mPlayer = player;
        mEventType = eventType;
        mEventListener = eventListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mPlayer.addListener(mEventType, mEventListener);
        } else {
            mPlayer.removeListener(mEventType, mEventListener);
        }
    }
}
