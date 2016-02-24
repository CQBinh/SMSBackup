package com.appconus.smsbackup.utils;

import android.content.Context;
import android.widget.TextView;

import com.appconus.smsbackup.utils.Objects.Message;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Firebase.ValueResultHandler;
import com.firebase.client.FirebaseError;

import java.util.Date;
import java.util.Map;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 24/02/2016.
 */
public enum FirebaseHelper {
    INSTANCE;
    private Firebase firebase;
    private Context context;
    public void init(Context context){
        this.context = context;
        Firebase.setAndroidContext(context);
        firebase = new Firebase("https://sms-backup.firebaseio.com");
    }

    public void logSms(String from, String message, long time){
        Message messageObj = new Message(from, message, time);
        Firebase newMessage = firebase.child("sms").child(getUserUid()).push();
        newMessage.setValue(messageObj);
    }

    public void createUser(String email, String password, ValueResultHandler<Map<String, Object>> callback){
        firebase.createUser(email, AccountHelper.md5(password), callback);
    }

    public void login(String email, String password, Firebase.AuthResultHandler callback){
        firebase.authWithPassword(email, AccountHelper.md5(password), callback);
    }

    public void loadMessage(final TextView textView){
        Firebase messageFirebase = new Firebase("https://sms-backup.firebaseio.com/sms/");
        messageFirebase.orderByChild("time");
        messageFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String smss = "";
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    smss += String.valueOf(child.getValue());
                }
                Message newMessage = dataSnapshot.getValue(Message.class);
                // TODO save to db
                textView.setText(smss);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        messageFirebase.push().setValue(new Message("from", "mes", new Date().getTime()));
    }

    public String getUserUid(){
        AuthData authData = firebase.getAuth();
        return authData.getUid();
    }

}
