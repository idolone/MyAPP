package com.example.mytest12.Util;

import android.util.Log;

public class LogUtil {

    private static final String TAG = "KIN";

    public static void debug(String msg){
        Log.d(TAG, "debug: "+msg);
    }

    public static void info(String msg){
        Log.d(TAG, "info: "+msg);
    }

    public static void error(String msg){
        Log.d(TAG, "error: "+msg);
    }

}
