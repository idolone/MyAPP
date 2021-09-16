package com.example.mynote.utils;

import android.util.Log;

public class LogUtil {

    private static final String TAG = "KIN";

    public static void debug(String msg, Object... args){
        Log.d(TAG, "debug: "+ msg);
    }

    public static void error(String msg,Object... args){
        Log.e(TAG, "error: " + msg);
    }
}
