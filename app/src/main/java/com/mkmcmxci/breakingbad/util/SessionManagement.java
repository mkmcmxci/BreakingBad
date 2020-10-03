package com.mkmcmxci.breakingbad.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    private static Context context;
    private static final String SESSION = "session";
    private static SessionManagement instance;

    public SessionManagement(Context context) {
        this.context = context;

    }

    public static SessionManagement getInstance(Context context) {

        if (instance == null) {

            instance = new SessionManagement(context);

        }

        return instance;

    }

    public static void saveSession(Long time) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("time", time);
        editor.commit();

    }

    public static long loadSession() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        Long time = sharedPreferences.getLong("time", 0);

        return time;

    }

}
