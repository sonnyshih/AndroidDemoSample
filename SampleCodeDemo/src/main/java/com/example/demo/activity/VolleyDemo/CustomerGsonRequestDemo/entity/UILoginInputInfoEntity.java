package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UILoginInputInfoEntity implements Serializable {

    @SerializedName("LoginName")
    private String loginName;
    @SerializedName("Password")
    private String password;
    @SerializedName("ValidateToken")
    private String validateToken;
    @SerializedName("AllowNewsLetter")
    private boolean allowNewsLetter;
    @SerializedName("ValidateTransNo")
    private int validateTransNo;
    @SerializedName("ValidateCode")
    private String validateCode;

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValidateToken(String validateToken) {
        this.validateToken = validateToken;
    }

    public void setAllowNewsLetter(boolean allowNewsLetter) {
        this.allowNewsLetter = allowNewsLetter;
    }

    public void setValidateTransNo(int validateTransNo) {
        this.validateTransNo = validateTransNo;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
