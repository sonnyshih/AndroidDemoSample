
package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UIValidatorInfo implements Serializable {

    private static final long serialVersionUID = -1468825702286560265L;

    @SerializedName("IsShowCaptcha")
    private boolean isShowCaptcha;
    @SerializedName("CaptchaImageUrl")
    private String captchaImageUrl;
    @SerializedName("ValidateTransNo")
    private int validateTransNo;
    @SerializedName("ValidateToken")
    private String validateToken;

    public boolean isShowCaptcha() {
        return isShowCaptcha;
    }

    public String getCaptchaImageUrl() {
        return captchaImageUrl;
    }

    public int getValidateTransNo() {
        return validateTransNo;
    }

    public String getValidateToken() {
        return validateToken;
    }

}
