package com.example.volleydemo.CustomerGsonRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.volleydemo.CustomerGsonRequest.entity.UserInfoEntity;
import com.example.volleydemo.CustomerGsonRequest.entity.VStoreNavigationItemInfoEntity;
import com.example.volleydemo.R;
import com.example.volleydemo.SingletonPattern.MySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class CustomerGsonRequestActivity extends Activity implements OnClickListener{
	private RequestQueue requestQueue;
	private Button getButton;
	private Button postButton;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_gson_request_activity);
		
		requestQueue = MySingleton.getInstance(this).getRequestQueue();

		getButton = (Button) findViewById(R.id.customerGsonRequest_getButton);
		getButton.setOnClickListener(this);
		
		postButton = (Button) findViewById(R.id.customerGsonRequest_postButton);
		postButton.setOnClickListener(this);
		
		textView = (TextView) findViewById(R.id.customerGsonRequest_textView);
	}
	
	
	private void onGetJsonClick(){
		String url = "http://www.ows.newegg.com/Stores.egg/ShopAllNavigation";
		Type messageType = new TypeToken<List<VStoreNavigationItemInfoEntity>>() {}.getType();
		
		HashMap<String, String> headers = getHaders();
		
		GsonRequest<List<VStoreNavigationItemInfoEntity>> gsonRequest = new GsonRequest<List<VStoreNavigationItemInfoEntity>>(
				Request.Method.GET, url, messageType, headers,
				new Response.Listener<List<VStoreNavigationItemInfoEntity>>() {
					@Override
					public void onResponse(List<VStoreNavigationItemInfoEntity> response) {
						Gson gson = new Gson();
						String temp = gson.toJson(response);
						textView.setText(temp);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						textView.setText(error.getMessage());
					}
				});
		
		requestQueue.add(gsonRequest);

	}

	private void onPostJsonClick(){
		String url = "http://202.153.189.188/user/login";
		String inputJSON = "{\"user\": \"herdyboy\", \"password\": \"123456\", \"device_id\":\"0900000015\"}";

		Type userInfoEntityType = new TypeToken<UserInfoEntity>() {}.getType();
		
		HashMap<String, String> headers = getHaders();
		
		GsonRequest<UserInfoEntity> gsonRequest = new GsonRequest<UserInfoEntity>(
				Request.Method.POST, url, inputJSON, userInfoEntityType, headers,
				new Response.Listener<UserInfoEntity>() {
					@Override
					public void onResponse(UserInfoEntity response) {
						Gson gson = new Gson();
						String temp = gson.toJson(response);
						textView.setText(temp);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						textView.setText(error.getMessage());
					}
				});
		
		requestQueue.add(gsonRequest);
		
	}
	
	private HashMap<String, String> getHaders(){
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
