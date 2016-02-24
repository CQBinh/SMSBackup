package com.appconus.smsbackup.utils.Objects;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 24/02/2016.
 */
public class Message {
    private String from;
    private String message;
    private long time;

    public Message() {
    }

    public Message(String from, String message, long time) {
        this.from = from;
        this.message = message;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }
}
