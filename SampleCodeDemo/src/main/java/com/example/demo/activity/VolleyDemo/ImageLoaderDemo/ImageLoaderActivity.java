package com.example.demo.activity.VolleyDemo.ImageLoaderDemo;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.demo.R;
import com.example.demo.activity.VolleyDemo.SingletonPatternDemo.MySingleton;

public class ImageLoaderActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_loader_activity);

        showPhotoMethod1();
        showPhotoMethod2();
        showPhotoMethod3();
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

    // Use the ImageRequest class
    private void showPhotoMethod1() {

        String imageUrl = "http://i.imgur.com/7spzG.png";
        final ImageView imageView = (ImageView) findViewById(R.id.imageLoader_requestIageView);

        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);

                    }
                }, 0, 0, null, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.drawable.icon_error);
            }
        });

        request.setTag(this);

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(request);
    }


    // Use the NetworkImageView object
    private void showPhotoMethod2() {
        NetworkImageView networkImageView;

        RequestQueue requestQueue = MySingleton.getInstance(this).getRequestQueue();

        ImageLoader imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
                            20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

        String imageUrl = "http://developer.android.com/images/training/system-ui.png";

        networkImageView = (NetworkImageView) findViewById(R.id.imageLoader_NetworkImageView);
        networkImageView.setImageUrl(imageUrl, imageLoader);

    }

    // Show photo Method 3
    private void showPhotoMethod3() {
        ImageView imageView;

        String imageUrl = "http://3.bp.blogspot.com/-mb8uHMvQMOo/T59bLzjsteI/AAAAAAAAACg/ZAuK74oBLgg/s1600/4764009642_1def6af94c.jpg";

        RequestQueue requestQueue = MySingleton.getInstance(this).getRequestQueue();

        // BitmapCache: your can limit the cache size.
        ImageLoader imageLoader = new ImageLoader(requestQueue,
                new LruBitmapCache(
                        LruBitmapCache.getCacheSize(this)));

        imageView = (ImageView) findViewById(R.id.imageLoader_imageView);
        imageLoader.get(imageUrl, ImageLoader.getImageListener(imageView,
                R.drawable.icon_loading, R.drawable.icon_error));
    }
}
