package com.jwplayer.opensourcedemo;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.longtailvideo.jwplayer.cast.CastManager;

import java.io.IOException;
public class MyApplication extends MultiDexApplication {

    private static final String MEASURE_TAG = "JWMeasure";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " MyApplication onCreate: start");

        // Initialize the CastManager.
        // The CastManager must be initialized in the Application's context to prevent
        // issues with garbage collection.
        CastManager.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}



