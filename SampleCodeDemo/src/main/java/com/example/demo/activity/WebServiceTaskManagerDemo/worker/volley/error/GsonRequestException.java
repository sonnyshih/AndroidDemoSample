package com.example.demo.activity.WebServiceTaskManagerDemo.worker.volley.error;

import com.android.volley.VolleyError;

public class GsonRequestException extends VolleyError {

    private Exception exception;

    public GsonRequestException(Exception exception) {
        super((Throwable) null);
        this.exception = exception;
    }

    @Override
    public Throwable getCause() {
        return exception;
    }

}