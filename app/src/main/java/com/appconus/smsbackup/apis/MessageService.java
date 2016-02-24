package com.appconus.smsbackup.apis;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 24/02/2016.
 */
public interface MessageService {
    @GET("/{userId}")
    void getAllMessages(@Path("userId") String userId, Callback<JSONObject> callback);
}
