package com.example.demo.activity.IntentFilterDemo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessageEntity implements Serializable {

    @SerializedName("To")
    private String to;

    @SerializedName("From")
    private String from;

    @SerializedName("Message")
    private String message;


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
