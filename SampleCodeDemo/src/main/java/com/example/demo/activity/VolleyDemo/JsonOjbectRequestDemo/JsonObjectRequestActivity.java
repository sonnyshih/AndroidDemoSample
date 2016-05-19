
package com.example.demo.activity.VolleyDemo.JsonOjbectRequestDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.demo.R;
import com.example.demo.activity.VolleyDemo.SingletonPatternDemo.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectRequestActivity extends AppCompatActivity implements
        OnClickListener {

    private Button getButton;
    private Button postButton;
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_object_request_activity);

        getButton = (Button) findViewById(R.id.jsonObjectRequest_getButton);
        getButton.setOnClickListener(this);

        postButton = (Button) findViewById(R.id.jsonObjectRequest_postButton);
        postButton.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.jsonObjectRequest_textView);

        progressBar = (ProgressBar) findViewById(R.id.jsonObjectRequest_progressBar);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (MySingleton.getInstance(this).getRequestQueue() != null) {
            MySingleton.getInstance(this).cancelAll(this);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getMethod() {
        String url = "http://175.98.136.168:4080/configuration.egg/iphoneclient";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText("Response: " + response.toString());
                        textView.setVisibility(View.VISIBLE);

                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("error=" + error.getMessage());
                textView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);

            }
        });

        jsObjRequest.setTag(this);

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void postMethod() {
        String url = "http://175.98.136.168:4080/Search.egg/Query";

        String inputJSON ="{";
        inputJSON +="   \"BrandList\":[],";
        inputJSON +="   \"Keyword\":\"Acer\",";
        inputJSON +="   \"MaxPrice\":\"\",";
        inputJSON +="   \"MinPrice\":\"\",";
        inputJSON +="   \"ModuleParams\":\"\",";
        inputJSON +="   \"NValue\":\"\",";
        inputJSON +="   \"ProductType\":[],";
        inputJSON +="   \"SearchProperties\":[],";
        inputJSON +="   \"BrandId\":-1,";
        inputJSON +="   \"CategoryId\":-1,";
        inputJSON +="   \"NodeId\":-1,";
        inputJSON +="   \"PageNumber\":1,";
        inputJSON +="   \"StoreId\":-1,";
        inputJSON +="   \"StoreType\":-1,";
        inputJSON +="   \"SubCategoryId\":-1";
        inputJSON +="}";


        JSONObject jsonObj = null;

        try {
            jsonObj = new JSONObject(inputJSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonObj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText("Response: " + response.toString());
                        textView.setVisibility(View.VISIBLE);

                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("error=" + error.getMessage());
                textView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHaders();
            }

        };

        jsObjRequest.setTag(this);

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

    }

    private HashMap<String, String> getHaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Accept-Charset", "utf-8");
        headers.put("Accept-Encoding", "gzip,deflate");
        headers.put("X-HighResolution", "false");
//		headers.put("Content-type", "application/json"); //
//		headers.put("Content-type", "application/x-www-form-urlencoded"); //

        return headers;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jsonObjectRequest_getButton:
                getMethod();
                break;

            case R.id.jsonObjectRequest_postButton:
                postMethod();
                break;

            default:
                break;
        }
    }
}
