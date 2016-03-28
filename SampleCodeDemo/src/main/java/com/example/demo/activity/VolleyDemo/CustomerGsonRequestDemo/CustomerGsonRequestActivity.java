package com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.demo.R;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.MessageEntity;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.UILoginResultInfoEntity;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.VStoreNavigationItemInfoEntity;
import com.example.demo.activity.VolleyDemo.SingletonPatternDemo.MySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class CustomerGsonRequestActivity extends AppCompatActivity implements OnClickListener {

    private Button getButton;
    private Button postButton;
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_gson_request_activity);

        getButton = (Button) findViewById(R.id.customerGsonRequest_getButton);
        getButton.setOnClickListener(this);

        postButton = (Button) findViewById(R.id.customerGsonRequest_postButton);
        postButton.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.customerGsonRequest_textView);

        progressBar = (ProgressBar) findViewById(R.id.customerGsonRequest_progressBar);

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

    private void onGetJsonClick() {

        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        String url = "http://www.ows.newegg.com/Stores.egg/ShopAllNavigation";
        Type messageType = new TypeToken<List<VStoreNavigationItemInfoEntity>>() {
        }.getType();

        HashMap<String, String> headers = getHaders();

        GsonRequest<List<VStoreNavigationItemInfoEntity>> gsonRequest = new GsonRequest<List<VStoreNavigationItemInfoEntity>>(
                Request.Method.GET, url, messageType, headers,
                new Response.Listener<List<VStoreNavigationItemInfoEntity>>() {
                    @Override
                    public void onResponse(List<VStoreNavigationItemInfoEntity> response) {
                        Gson gson = new Gson();
                        String temp = gson.toJson(response);
                        textView.setText(temp);

                        textView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());

                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }
        });

        gsonRequest.setTag(this);

        MySingleton.getInstance(this).getRequestQueue().add(gsonRequest);

    }

    private void onPostJsonClick() {

        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        String url = "http://175.98.136.168:4000/ShoppingLogin.egg";
        String inputJSON = "{\"LoginName\": \"c@thy.com\", \"password\": \"111111\"}";

        Type typeToken = new TypeToken<MessageEntity<UILoginResultInfoEntity>>() {
        }.getType();

        HashMap<String, String> headers = getHaders();

        GsonRequest<MessageEntity<UILoginResultInfoEntity>> gsonRequest = new GsonRequest<MessageEntity<UILoginResultInfoEntity>>(
                Request.Method.POST, url, inputJSON, typeToken, headers,
                new Response.Listener<MessageEntity<UILoginResultInfoEntity>>() {
                    @Override
                    public void onResponse(MessageEntity<UILoginResultInfoEntity> response) {
                        Gson gson = new Gson();
                        String temp = gson.toJson(response);
                        textView.setText(temp);

                        textView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("error = " + error.getMessage());

                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

        gsonRequest.setTag(this);

        MySingleton.getInstance(this).getRequestQueue().add(gsonRequest);

    }

    private HashMap<String, String> getHaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Accept-Charset", "utf-8");
        headers.put("Accept-Encoding", "gzip,deflate");
        headers.put("X-HighResolution", "false");
//		headers.put("Content-type", "application/json");
//		headers.put("Content-type", "application/x-www-form-urlencoded");

        return headers;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.customerGsonRequest_getButton:
                onGetJsonClick();
                break;

            case R.id.customerGsonRequest_postButton:
                onPostJsonClick();
                break;

            default:
                break;
        }
    }

}
