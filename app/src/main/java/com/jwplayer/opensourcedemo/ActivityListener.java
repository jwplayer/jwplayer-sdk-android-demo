package com.jwplayer.opensourcedemo;

/**
 * Created by alexandr on 09/03/2018.
 */

public interface ActivityListener {

    void onCreate();
    void onCreateFinish();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();

}
