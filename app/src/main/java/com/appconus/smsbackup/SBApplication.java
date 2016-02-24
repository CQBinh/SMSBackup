package com.appconus.smsbackup;

import android.app.Application;

import com.appconus.smsbackup.utils.FirebaseHelper;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 24/02/2016.
 */
public class SBApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseHelper.INSTANCE.init(getApplicationContext());
    }
}
