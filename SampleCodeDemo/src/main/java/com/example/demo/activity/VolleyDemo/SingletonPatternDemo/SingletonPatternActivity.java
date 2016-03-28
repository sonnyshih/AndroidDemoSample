
package com.example.demo.activity.VolleyDemo.SingletonPatternDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.demo.R;

public class SingletonPatternActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleton_pattern_activity);

        textView = (TextView) findViewById(R.id.singletonPattern_textView);
        textView.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.singletonPattern_progressBar);
        progressBar.setVisibility(View.VISIBLE);

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
                textView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);

            }
        });

   		stringRequest.setTag(this);

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (MySingleton.getInstance(this).getRequestQueue() != null) {
            MySingleton.getInstance(this).cancelAll(this);
        }

    }
}
