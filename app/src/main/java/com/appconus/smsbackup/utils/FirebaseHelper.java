package com.appconus.smsbackup.utils;

import android.content.Context;

import com.appconus.smsbackup.utils.Objects.Message;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Firebase.ValueResultHandler;

import java.util.Map;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 24/02/2016.
 */
public enum FirebaseHelper {
    INSTANCE;
    private Firebase firebase;
    private Context context;

    public void init(Context context) {
        this.context = context;
        Firebase.setAndroidContext(context);
        firebase = new Firebase("https://sms-backup.firebaseio.com");
    }

    public void logSms(String from, String message, long time) {
        Message messageObj = new Message(from, message, time);
        Firebase newMessage = firebase.child("sms").child(getUserUid()).push();
        newMessage.setValue(messageObj);
    }

    public void createUser(String email, String password, ValueResultHandler<Map<String, Object>> callback) {
        firebase.createUser(email, AccountHelper.md5(password), callback);
    }

    public void login(String email, String password, Firebase.AuthResultHandler callback) {
        firebase.authWithPassword(email, AccountHelper.md5(password), callback);
    }

    public String getUserUid() {
        AuthData authData = firebase.getAuth();
        return authData.getUid();
    }

}
