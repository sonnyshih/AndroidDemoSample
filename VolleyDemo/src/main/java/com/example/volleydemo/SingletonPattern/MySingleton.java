package com.example.volleydemo.SingletonPattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MySingleton {
	private static MySingleton instance;
	private RequestQueue requestQueue;
//	private ImageLoader imageLoader;
	private Context context;

	private MySingleton(Context context) {
		this.context = context;
//		requestQueue = getRequestQueue();
//
//		imageLoader = new ImageLoader(requestQueue,
//				new ImageLoader.ImageCache() {
//					private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
//							20);
//
//					@Override
//					public Bitmap getBitmap(String url) {
//						return cache.get(url);
//					}
//
//					@Override
//					public void putBitmap(String url, Bitmap bitmap) {
//						cache.put(url, bitmap);
//					}
//				});
	}

	public static synchronized MySingleton getInstance(Context context) {
		if (instance == null) {
			instance = new MySingleton(context);
		}
		return instance;
	}

	public RequestQueue getRequestQueue() {
		if (requestQueue == null) {
			// getApplicationContext() is key, it keeps you from leaking the
			// Activity or BroadcastReceiver if someone passes one in.
			requestQueue = Volley
					.newRequestQueue(context.getApplicationContext());
		}
		return requestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	public void cancelAll(Object tag){
		requestQueue.cancelAll(tag);
	}
	
//	public ImageLoader getImageLoader() {
//		return imageLoader;
//	}

}
