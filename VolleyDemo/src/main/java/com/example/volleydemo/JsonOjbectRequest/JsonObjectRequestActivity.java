package com.example.volleydemo.JsonOjbectRequest;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleydemo.R;
import com.example.volleydemo.SingletonPattern.MySingleton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class JsonObjectRequestActivity extends Activity implements
		OnClickListener {

	private Button getButton;
	private Button postButton;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json_object_request_activity);

		getButton = (Button) findViewById(R.id.jsonObjectRequest_getButton);
		getButton.setOnClickListener(this);

		postButton = (Button) findViewById(R.id.jsonObjectRequest_postButton);
		postButton.setOnClickListener(this);

		textView = (TextView) findViewById(R.id.jsonObjectRequest_textView);

	}

	private void getMethod() {
		String url = "http://www.ows.newegg.com/configuration.egg/iphoneclient";

		JsonObjectRequest jsObjRequest = new JsonObjectRequest(
				Request.Method.GET, url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						textView.setText("Response: " + response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						textView.setText("error=" + error.getMessage());
					}
				});

		// Access the RequestQueue through your singleton class.
		MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

	}

	private void postMethod() {
		String url = "http://202.153.189.188/user/login";
		String inputJSON = "{\"user\": \"herdyboy\", \"password\": \"123456\", \"device_id\":\"0900000015\"}";
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
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						textView.setText("error=" + error.getMessage());
					}
				}) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				return getHaders();
			}

		};

		// Access the RequestQueue through your singleton class.
		MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

	}

	private HashMap<String, String> getHaders(){
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Accept-Charset", "utf-8");
  	    headers.put("Accept-Encoding", "gzip,deflate");
		headers.put("X-HighResolution", "false");
//		headers.put("Content-type", "application/json"); // For JSON 加了會產生 http code 400
//		headers.put("Content-type", "application/x-www-form-urlencoded"); // For UrlEncoded 加了會產生 http code 400
		
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
