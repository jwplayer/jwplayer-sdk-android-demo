package com.jwplayer.opensourcedemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.longtailvideo.jwplayer.cast.CastManager;

public class MyApplication extends MultiDexApplication {

    private static final String MEASURE_TAG = "JWMeasure";

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        // Initialize the CastManager.
        // The CastManager must be initialized in the Application's context to prevent
        // issues with garbage collection.
        CastManager.initialize(this);
    }
}
