package com.example.demo.activity.WebServiceTaskManagerDemo.webservice;


import com.example.demo.activity.WebServiceTaskManagerDemo.type.DeviceType;

public class ClientInfo {

    private DeviceType deviceType;
    private String appVersion;
    private String authToken;

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
