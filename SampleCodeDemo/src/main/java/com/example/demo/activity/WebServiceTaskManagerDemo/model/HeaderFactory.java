package com.example.demo.activity.WebServiceTaskManagerDemo.model;

import com.example.demo.activity.WebServiceTaskManagerDemo.type.ContentType;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.DeviceType;
import com.example.demo.activity.WebServiceTaskManagerDemo.webservice.ClientInfo;

import java.util.HashMap;
import java.util.Map;

public class HeaderFactory {

    public static Map<String, String> create(ClientInfo clientInfo) {
        return create(clientInfo, ContentType.Json);
    }

    public static Map<String, String> create(ClientInfo clientInfo, ContentType contentType) {
        Map<String, String> headerValues = new HashMap<String, String>();
        headerValues.put("Accept", "application/json");
        headerValues.put("Accept-Charset", "utf-8");
        headerValues.put("Accept-Encoding", "gzip,deflate");
        headerValues.put("X-HighResolution", "true");

        // The Content-Type is real set in getBodyContentType() in the GsonRequest.java
        headerValues.put("Content-Type", getContentType(contentType));
        headerValues.put("User-Agent", getUserAgent(clientInfo.getDeviceType(), clientInfo.getAppVersion()));
        headerValues.put("X-AuthToken", clientInfo.getAuthToken());

        return headerValues;
    }

    private static String getContentType(ContentType contentType) {
        switch (contentType) {
            case Json:
                return "application/json";
            case UrlEncoded:
                return "application/x-www-form-urlencoded";
        }
        return "";
    }

    private static String getUserAgent(DeviceType deviceType, String appVersion){

        switch (deviceType) {
            case Phone:
                return "AndroidPhone App / " + appVersion;
            case Tablet:
                return "AndroidTablet App / " + appVersion;
            default:
                break;
        }
        return "";

    }
}
