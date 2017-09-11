package com.example.demo.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtil {

    public static String getProtocol(String urlString){
        String protocol = "";

        try {
            URL url = new URL(urlString);
            protocol = url.getProtocol();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return protocol;
    }

    public static String getHost(String urlString){
        String hostString = "";
        try {
            URL url = new URL(urlString);
            hostString = url.getHost();
            if (url.getPort() != -1) {
                hostString += ":" + url.getPort();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return hostString;
    }

    public static String getPath(String urlString){

        String path = "";

        try {
            URL url = new URL(urlString);
            path =url.getPath();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return path;

    }
}
