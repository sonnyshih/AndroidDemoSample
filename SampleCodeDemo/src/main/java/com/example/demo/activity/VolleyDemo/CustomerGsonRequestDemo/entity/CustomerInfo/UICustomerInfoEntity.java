
package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UICustomerInfoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3551185856033962508L;

    @SerializedName("CustomerNumber")
    private int customerNumber;
    @SerializedName("Name")
    private String name;
    @SerializedName("LoginName")
    private String loginName;
    @SerializedName("ZipCode")
    private String zipCode;
    @SerializedName("AuthToken")
    private String authToken;
    @SerializedName("IsNewOrRepeat")
    private boolean isNewOrRepeat;
    @SerializedName("CustomerPointInfo")
    private UICustomerPointInfoEntity customerPointInfo;

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getName() {
        return name;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAuthToken() {
        return authToken;
    }

    public boolean isNewOrRepeat() {
        return isNewOrRepeat;
    }

    public UICustomerPointInfoEntity getCustomerPointInfo() {
        return customerPointInfo;
    }

}
