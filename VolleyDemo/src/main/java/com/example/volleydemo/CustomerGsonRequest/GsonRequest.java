package com.example.volleydemo.CustomerGsonRequest;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.lang.reflect.Type;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonRequest<T> extends Request<T> {
	
	private static final String PROTOCOL_CHARSET = "utf-8";
	private final Gson gson = new Gson();
	private final Type clazz;
	private final Map<String, String> headers;
	private final Listener<T> listener;
	private String requestBody;

	/**
	 * Make a GET request and return a parsed object from JSON.
	 *
	 * @param url
	 *            URL of the request to make
	 * @param clazz
	 *            Relevant class object, for Gson's reflection
	 * @param headers
	 *            Map of request headers
	 */
	// For http get 
	public GsonRequest(int method, String url, Type clazz, Map<String, String> headers,
			Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.headers = headers;
		this.listener = listener;
	}

	// For http post
	public GsonRequest(int method, String url, String requestBody, Type clazz, Map<String, String> headers,
			Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.headers = headers;
		this.listener = listener;
		this.requestBody = requestBody;
	}
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}

	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));

			return Response.success((T) gson.fromJson(json, clazz),
					HttpHeaderParser.parseCacheHeaders(response));
			
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
			
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}


	@Override
	public byte[] getBody() throws AuthFailureError {
		
		try {
			
			if (requestBody != null) {
				return requestBody.getBytes(PROTOCOL_CHARSET);
			} else {
				return null;
			}
			
		} catch (UnsupportedEncodingException e) {
			VolleyLog
					.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
							requestBody, PROTOCOL_CHARSET);
			return null;

		}
		
	}
	
	
}