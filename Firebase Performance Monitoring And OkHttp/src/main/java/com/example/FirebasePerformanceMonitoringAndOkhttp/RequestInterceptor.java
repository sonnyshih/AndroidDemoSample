package com.example.FirebasePerformanceMonitoringAndOkhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private String TAG = this.getClass().getName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e(TAG, "request URL : " + request.url());

        Response response = chain.proceed(request).newBuilder().build();
        Log.e(TAG, "response URL : " + response.request().url());
        Headers headers = response.networkResponse().headers();
//        for (int i=0; i<headers.size();){
//            Log.e(TAG, "Key="+headers.name(i)+"## value="+ headers.value(i));
//        }

        return response;
    }}
