package com.appconus.smsbackup.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.appconus.smsbackup.utils.FirebaseHelper;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 24/02/2016.
 */
public class SmsListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    assert pdus != null;
                    for (int i = 0; i < new SmsMessage[pdus.length].length; i++) {
                        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        String from = message.getOriginatingAddress();
                        String msgBody = message.getMessageBody();
                        long time = message.getTimestampMillis();
                        FirebaseHelper.INSTANCE.logSms(from, msgBody, time);
                    }
                } catch (Exception e) {
                    Log.d("Exception caught", e.getMessage());
                }
            }
        }
    }
}
