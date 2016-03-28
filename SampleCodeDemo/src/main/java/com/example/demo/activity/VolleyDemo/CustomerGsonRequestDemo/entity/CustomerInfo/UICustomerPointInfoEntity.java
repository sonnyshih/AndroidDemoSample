package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UICustomerPointInfoEntity implements Serializable {

    private static final long serialVersionUID = -1333265472035149430L;
    @SerializedName("ActivePoints")
    private int activePoints;
    @SerializedName("LastExpireDate")
    private String lastExpireDate;
    @SerializedName("RedeemForCurrencyUnit")
    private int redeemForCurrencyUnit;

    public int getActivePoints() {
        return activePoints;
    }

    public String getLastExpireDate() {
        return lastExpireDate;
    }

    public int getRedeemForCurrencyUnit() {
        return redeemForCurrencyUnit;
    }

}
