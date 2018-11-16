package com.jwplayer.opensourcedemo;

import com.longtailvideo.jwplayer.cast.CastManager;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

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
