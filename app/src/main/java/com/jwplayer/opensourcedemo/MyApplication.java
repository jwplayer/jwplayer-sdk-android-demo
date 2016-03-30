package com.jwplayer.opensourcedemo;

import android.app.Application;

import com.longtailvideo.jwplayer.cast.CastManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the CastManager.
        // The CastManager must be initialized in the Application's context to prevent
        // issues with garbage collection.
        CastManager.initialize(this);
    }
}
