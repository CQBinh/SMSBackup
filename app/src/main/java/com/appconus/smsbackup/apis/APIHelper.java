package com.appconus.smsbackup.apis;

import retrofit.RestAdapter;
import retrofit.android.AndroidApacheClient;

public class APIHelper<T> {
    private RestAdapter instance;

    public T createService(Class<T> service) {
        return getRestAdapter().create(service);
    }

    public RestAdapter getRestAdapter() {
        if (instance == null) synchronized (RestAdapter.class) {
            if (instance == null) {
                createInstance();
            }
        }
        return instance;
    }

    private void createInstance() {
        String SERVICE_URL = "https://sms-backup.firebaseio.com/sms/";
        instance = new RestAdapter.Builder()
                .setEndpoint(SERVICE_URL)
                .setClient(new AndroidApacheClient())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}