
package com.example.demo.activity.WebServiceTaskManagerDemo.worker.volley;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.WebServiceTask;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.ErrorType;
import com.example.demo.activity.WebServiceTaskManagerDemo.worker.WebServiceWorker;
import com.example.demo.activity.WebServiceTaskManagerDemo.worker.volley.error.GsonRequestException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class VolleyWorker<R> extends WebServiceWorker<R> implements
        Listener<R>, ErrorListener  {


    private RequestQueue requestQueue;

    public VolleyWorker(WebServiceTask<R> task) {
        super(task);
        requestQueue = VolleyRequestQueueManager.getInstance().getRequestQueue();
    }

    @Override
    protected void startHttpGetRequest(String serviceUrl, Map<String, String> headerValues, Type type) {

        GsonRequest<R> request = new GsonRequest<R>(Request.Method.GET, serviceUrl,
                type, this, this, headerValues);

        DefaultRetryPolicy defaultRetryPolicy = getDefaultRetryPolicy();
        request.setRetryPolicy(defaultRetryPolicy);
        request.setTag(this);

        requestQueue.add(request);

    }

    @Override
    protected void startHttpPostRequest(String serviceUrl, String postBody, Map<String, String> headerValue, Type type) {

        GsonRequest<R> request = new GsonRequest<R>(Request.Method.POST, serviceUrl,
                postBody, type, this, this, headerValue);

        DefaultRetryPolicy defaultRetryPolicy = getDefaultRetryPolicy();
        request.setRetryPolicy(defaultRetryPolicy);
        request.setTag(this);

        requestQueue.add(request);

    }

    @Override
    protected void cancelRequest() {
        requestQueue.cancelAll(this);

    }

    private DefaultRetryPolicy getDefaultRetryPolicy() {
        DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(60000,
                0, 0);
        return defaultRetryPolicy;
    }

    @Override
    public void onResponse(R response) {

        notifyWorkSucceed(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        if (error.networkResponse != null) {

            if (isClientError(error.networkResponse.statusCode)) {
                notifyWorkFailed(ErrorType.WEB_CLIENT_ERROR);
                return;
            }

            if (isServerError(error.networkResponse.statusCode)) {
                notifyWorkFailed(ErrorType.WEB_SERVER_ERROR);
                return;
            }
        }


        if (error instanceof GsonRequestException) {

            GsonRequestException e = (GsonRequestException) error;

            if (e.getCause().getClass() == JsonSyntaxException.class) {
                notifyWorkFailed(ErrorType.JSON_ERROR);
                return;
            }

            if (e.getCause().getClass() == IOException.class) {
                notifyWorkFailed(ErrorType.WEB_IO_ERROR);
                return;
            }

        }

        if (error.getClass() == NoConnectionError.class) {
            notifyWorkFailed(ErrorType.WEB_CLIENT_ERROR);
            return;
        }

        notifyWorkFailed(ErrorType.UNKNOWN_ERROR);
        return;
    }

    private boolean isServerError(int responseCode) {
        return responseCode >= 500 && responseCode < 600;
    }

    private boolean isClientError(int responseCode) {
        return responseCode >= 400 && responseCode < 500;
    }
}
