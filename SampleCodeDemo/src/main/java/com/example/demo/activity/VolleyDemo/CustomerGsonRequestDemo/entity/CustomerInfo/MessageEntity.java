package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessageEntity<T> implements Serializable {

    private static final long serialVersionUID = -4852683731952230992L;

    @SerializedName("Body")
    private T body;
    @SerializedName("Code")
    private String code;
    @SerializedName("Description")
    private String description;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
