package com.appconus.smsbackup.utils;

import android.content.Context;
import android.content.Intent;

public class ActivityController {
    public static void navigateToActivity(Context from, Class<?> toClass) {
        Intent intent = new Intent(from, toClass);
        from.startActivity(intent);
    }
}