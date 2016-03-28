package com.example.demo.activity.VolleyDemo.ShowSimpleRequestDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demo.R;

public class ShowSimpleRequestActivity extends AppCompatActivity{

    private RequestQueue requestQueue;
    private TextView textView;
    private ProgressBar progressBar;
    public static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_simple_request_activity);

        textView = (TextView) findViewById(R.id.showSimpleRequest_textView);
        textView.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.showSimpleRequest_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // Instantiate the RequestQueue.
        requestQueue = Volley.newRequestQueue(this);
        String url = "http://www.pchome.com.tw";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                textView.setText("Response is: " + response.substring(0, 500));
                textView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });


        stringRequest.setTag(TAG);

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }

    }
}
