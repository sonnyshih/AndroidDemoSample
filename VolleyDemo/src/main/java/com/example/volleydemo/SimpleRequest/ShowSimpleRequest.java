package com.example.volleydemo.SimpleRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowSimpleRequest extends Activity{
	private RequestQueue requestQueue;
	private TextView textView;
	public static final String TAG = "MyTag";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_simple_request_activity);
		
		
		textView = (TextView) findViewById(R.id.textView);

		// Instantiate the RequestQueue.
		requestQueue = Volley.newRequestQueue(this);
		String url = "http://www.pchome.com.tw";

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET,
				url, new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// Display the first 500 characters of the response
						// string.
						textView.setText("Response is: "
								+ response.substring(0, 500));

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
