package com.jwplayer.opensourcedemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by alexandr on 12/03/2018.
 */

public class Time {

    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);

    public static String currentTime() {
        return dateFormat.format(new Date());
    }


}
