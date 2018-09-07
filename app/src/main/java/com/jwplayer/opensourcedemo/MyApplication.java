package com.jwplayer.opensourcedemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.longtailvideo.jwplayer.cast.CastManager;

public class MyApplication extends MultiDexApplication {

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
