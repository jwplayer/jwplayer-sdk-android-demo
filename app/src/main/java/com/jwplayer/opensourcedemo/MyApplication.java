package com.jwplayer.opensourcedemo;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.longtailvideo.jwplayer.cast.CastManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        /*
         * We need to initialize singletons in the global application object to prevent issues
         * with garbage collection.
         */
        CastManager.initialize(this);
        // You can now get a reference to the singleton by calling CastManager.getInstance();
    }
}
