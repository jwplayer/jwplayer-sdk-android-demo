package com.jwplayer.opensourcedemo.myutil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {

    private static StringBuilder outputStringBuilder;
    private static boolean printedVersion = false;

    public static void newInstance(){
        outputStringBuilder = new StringBuilder();
    }

    public static String updateOutput(String output) {
        DateFormat dateFormat = new SimpleDateFormat("KK:mm:ss.SSS", Locale.US);
        outputStringBuilder.append("").append(dateFormat.format(new Date())).append(" ").append(output).append("\r\n");
        return outputStringBuilder.toString();
    }

    public static String printBuildVersion(String jwPlayerVersion) {
        String version = "";

        if(!printedVersion) {
            version = outputStringBuilder.append("Build version: ").append(jwPlayerVersion).append("\r\n").toString();
            printedVersion = !printedVersion;
        }

        return version;
    }
}
