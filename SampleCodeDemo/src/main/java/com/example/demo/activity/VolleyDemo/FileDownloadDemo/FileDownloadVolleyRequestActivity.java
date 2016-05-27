
package com.example.demo.activity.VolleyDemo.FileDownloadDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.demo.R;
import com.example.demo.activity.VolleyDemo.SingletonPatternDemo.MySingleton;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class FileDownloadVolleyRequestActivity extends AppCompatActivity implements OnClickListener,
        Listener<byte[]>, ErrorListener {

    private FileDownloadVolleyRequest fileDownloadVolleyRequest;
    private ImageView imageView;
    private ProgressBar progressBar;

    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean isDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_download_volley_request_activity);

        Button downloadButton = (Button) findViewById(R.id.fileDownloadVolleyRequest_downloadButton);
        downloadButton.setOnClickListener(this);

        Button cancelDownloadButton = (Button) findViewById(R.id.fileDownloadVolleyRequest_cancelDownloadButton);
        cancelDownloadButton.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.fileDownloadVolleyRequest_fileImageView);

        progressBar = (ProgressBar) findViewById(R.id.fileDownloadVolleyRequest_progressBar);
        progressBar.setMax(100);
    }

    @Override
    protected void onDestroy() {
        onDownloadCancelClick();
        super.onDestroy();
    }

    private void onDownloadClick() {
        String url = "http://developer.android.com/images/home/android-jellybean.png";
        progressBar.setProgress(0);
        isDownloading = true;

        fileDownloadVolleyRequest = new FileDownloadVolleyRequest(Request.Method.GET,
                url, this, this, getHeader());

        fileDownloadVolleyRequest.setTag(this);

        MySingleton.getInstance(this).getRequestQueue().add(fileDownloadVolleyRequest);
    }

    private void onDownloadCancelClick() {
        isDownloading = false;
        MySingleton.getInstance(this).getRequestQueue().cancelAll(this);
    }

    private HashMap<String, String> getHeader() {
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
            case R.id.fileDownloadVolleyRequest_downloadButton:
                onDownloadClick();
                break;

            case R.id.fileDownloadVolleyRequest_cancelDownloadButton:
                onDownloadCancelClick();
                break;

            default:
                break;

        }
    }

    @Override
    public void onResponse(final byte[] response) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (response != null) {
                        Log.d("Mylog", "ResponseCode = " + fileDownloadVolleyRequest.getResponseCode());

//                      Map<String, String> headerMap = fileDownloadVolleyRequest.getResponseHeader();
//                      for (Map.Entry entry : headerMap.entrySet()) {
//                          Log.d("Mylog", "Key : " + entry.getKey() + " Value : " + entry.getValue());
//                      }

                        final String filename = "local_temp_image";
                        // the file will be created in /data/data/packageName/files/
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

                        final String path = getFileStreamPath(filename).getAbsolutePath();

                        byte[] buf = new byte[8196];
                        final long total = response.length;
                        int seg = 0;
                        inputStream = new ByteArrayInputStream(response);
                        long current = 0;

                        while ((seg = inputStream.read(buf)) != -1) {

                            if (!isDownloading) {
                                break;
                            }

                            outputStream.write(buf, 0, seg);
                            current += seg;
                            final int progress = (int) ((float) current / (float) total * 100f);

                            SystemClock.sleep(100);
                            progressBar.setProgress(progress);

                            Log.d("Mylog", "progress = " + progress);
                        }

                        // When the UI is changed in thread , use another thread to change the UI.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Bitmap bitmap = BitmapFactory.decodeFile(path);
                                imageView.setImageBitmap(bitmap);
                            }
                        });

                    }


                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }

                } catch (Exception e) {
                    Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


}
