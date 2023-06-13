package com.example.cosmosservicemaven.serializer;

import java.time.Instant;

public class Message {
    private String text;
    private Instant time;
    private String userName;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
