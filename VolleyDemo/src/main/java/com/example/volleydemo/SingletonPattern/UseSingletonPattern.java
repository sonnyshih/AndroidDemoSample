package com.example.volleydemo.SingletonPattern;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.volleydemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UseSingletonPattern extends Activity{
	private TextView textView;
//	public static final String TAG = "MyTag";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.use_singleton_pattern_activity);

		textView = (TextView) findViewById(R.id.textView);
		
		// Get a RequestQueue
		RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
		    getRequestQueue();
		
		
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
		
		
//		stringRequest.setTag(TAG);
				
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
