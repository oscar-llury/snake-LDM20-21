package com.ldm.virusnake;

import android.app.Application;
import android.content.Context;

public class Virusnake extends Application {

    public static Context context;

    public void onCreate() {
        super.onCreate();
        Virusnake.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Virusnake.context;
    }
}
