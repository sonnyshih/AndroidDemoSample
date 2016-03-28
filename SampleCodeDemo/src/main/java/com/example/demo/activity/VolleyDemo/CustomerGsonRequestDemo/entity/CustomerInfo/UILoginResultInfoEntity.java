package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UILoginResultInfoEntity implements Serializable {

    @SerializedName("Customer")
    private UICustomerInfoEntity customer;
    @SerializedName("LoginStatus")
    private int loginStatus;
    @SerializedName("ErrorMessage")
    private String errorMessage;
    @SerializedName("IsNPAAvailable")
    protected boolean isNpaAvailable;
    @SerializedName("Validator")
    private UIValidatorInfo validator;

    public UICustomerInfoEntity getCustomer() {
        return customer;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isNpaAvailable() {
        return isNpaAvailable;
    }

    public UIValidatorInfo getValidator() {
        return validator;
    }
}
