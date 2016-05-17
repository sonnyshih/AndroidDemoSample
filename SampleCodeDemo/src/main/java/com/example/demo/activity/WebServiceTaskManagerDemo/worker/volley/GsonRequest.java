package com.example.demo.activity.WebServiceTaskManagerDemo.worker.volley;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.demo.activity.WebServiceTaskManagerDemo.worker.volley.error.GsonRequestException;
import com.example.demo.activity.base.SampleCodeDemoApp;
import com.example.demo.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class GsonRequest<T> extends Request<T> {
    private static final String PROTOCOL_CHARSET = "utf-8";

    private Gson gson;
    private Type resultType;
    private Listener<T> listener;
    private Map<String, String> headers;
    private String postBody;

    // Get method
    public GsonRequest(int method, String url, Type resultType,
                       Listener<T> listener, ErrorListener errorListener,
                       Map<String, String> headers){

        super(method, url, errorListener);

        if (SampleCodeDemoApp.isDebugMode()) {
            for (Map.Entry<String,String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                Log.i("Volley Service API Info", key + "= " + value);
            }

            Log.i("Volley Service API Info", "URL: " + url);
        }

        setShouldCache(false);
        this.resultType = resultType;
        this.headers = headers;
        this.listener = listener;
        this.gson = new Gson();
    }

    // Post method
    public GsonRequest(int method, String url, String postBody, Type type,
                       Listener<T> listener, ErrorListener errorListener,
                       Map<String, String> headers) {

        this(method, url, type, listener, errorListener, headers);
        this.postBody = postBody;

        if (SampleCodeDemoApp.isDebugMode()) {
            if (!StringUtil.isEmpty(postBody)) {
                Log.i("Volley Service API Info", "Input: " + postBody);
            }
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (headers != null && headers.size() > 0) {
            return headers;
        } else {
            return super.getHeaders();
        }
    }


    // Setting Content-Type (Set the content-type in header is wrong. Must setting here.)
    @Override
    public String getBodyContentType() {
        return headers.get("Content-Type");
    }

    // Post Body
    @Override
    public byte[] getBody() throws AuthFailureError {

        try {

            if (postBody != null) {
                return postBody.getBytes(PROTOCOL_CHARSET);
            } else {
                return null;
            }

        } catch (UnsupportedEncodingException e) {
            VolleyLog
                    .wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            postBody, PROTOCOL_CHARSET);
            return null;

        }

    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);

    }

    @SuppressWarnings("unchecked")
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = convertResponseToString(response);
            if (SampleCodeDemoApp.isDebugMode()) {
                Log.i("Volley Service API Info",
                        "Output: " + (StringUtil.isEmpty(json) ? "null" : json));
            }

            return Response.success((T) gson.fromJson(json, resultType),
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (JsonSyntaxException e) {
            return Response.error(new GsonRequestException(e));
        } catch (IOException e) {
            return Response.error(new GsonRequestException(e));
        }
    }


    private String convertResponseToString(NetworkResponse response)
            throws IOException {
        String encoding = response.headers.get("Content-Encoding");
        if (encoding == null) {
            return new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
        }
        BufferedReader reader = null;
        try {
            reader = generateBufferedReader(new ByteArrayInputStream(
                    response.data), encoding);
            return readBufferedString(reader);
        } finally {
            reader.close();
        }
    }

    private BufferedReader generateBufferedReader(InputStream inputStream,
                                                  String encodingType) throws IOException {
        if (encodingType.equals("gzip")) {
            return new BufferedReader(new InputStreamReader(
                    new GZIPInputStream(inputStream)));
        }
        if (encodingType.equals("deflate")) {
            return new BufferedReader(new InputStreamReader(
                    new InflaterInputStream(inputStream)));
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private String readBufferedString(BufferedReader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String stringHolder;
        while ((stringHolder = reader.readLine()) != null) {
            stringBuilder.append(stringHolder);
        }
        return stringBuilder.toString();
    }
}
