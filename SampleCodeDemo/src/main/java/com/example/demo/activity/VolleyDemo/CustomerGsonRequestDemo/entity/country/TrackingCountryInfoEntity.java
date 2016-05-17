
package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.country;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrackingCountryInfoEntity implements Serializable {

    @SerializedName("CountryID")
    private int countryId = TrackingCountryIDEntity.COUNTRY_ID_NONE;

    @SerializedName("CountryName")
    private String countryName;

    public int getCountryID() {
        return countryId;
    }

    public void setCountryID(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
