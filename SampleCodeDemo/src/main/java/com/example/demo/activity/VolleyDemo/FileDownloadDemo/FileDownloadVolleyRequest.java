/*******************************************************************
 * Copyright  (C) Newegg Corporation. All rights reserved.
 * <p/>
 * Author: Sonny Shih (Sonny.h.Shih@newegg.com)
 * Create Date: 2016/05/27
 * Usage:
 * <p/>
 * RevisionHistory
 * Date    Author    Description
 ********************************************************************/

package com.example.demo.activity.VolleyDemo.FileDownloadDemo;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

public class FileDownloadVolleyRequest extends Request<byte[]> {

    private final Listener<byte[]> listener;
    private Map<String, String> params;

    //create a static map for directly accessing headers
    private Map<String, String> responseHeader;

    public int responseCode;

    public FileDownloadVolleyRequest(int method, String url,
                                     Listener<byte[]> listener,
                                     ErrorListener errorListener,
                                     HashMap<String, String> params) {

        super(method, url, errorListener);
        setShouldCache(false);
        this.listener = listener;
        this.params = params;

    }

    @Override
    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    };

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {

        //Initialise local responseHeaders map with response headers received
        responseHeader = response.headers;

        // http responseCode
        responseCode = response.statusCode;

        //Pass the response data here
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) {
        listener.onResponse(response);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }
}
