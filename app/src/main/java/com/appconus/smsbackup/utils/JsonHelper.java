package com.appconus.smsbackup.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class JsonHelper {
    public static <T> T fromJson(String json, Class<T> cls) {
		GsonBuilder b = new GsonBuilder();
		Gson gson = b.create();
		return gson.fromJson(json, cls);
	}

	public static <T> T fromJson(JSONObject obj, Class<T> cls) {
		if (obj == null)
			return null;
		return fromJson(obj.toString(), cls);
	}

	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
}