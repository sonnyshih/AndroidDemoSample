package com.sonny.exaple.firebaseperformancemonitor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.Trace;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private static final String TAG = "FirebasePerformance-log";

    private OkHttpClient client;
    private Call call;

    private Trace mTrace;
    private String STARTUP_TRACE_NAME = "startup_trace";
    private String REQUESTS_COUNTER_NAME = "Get a photo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Begin tracing app startup tasks.
        mTrace = FirebasePerformance.getInstance().newTrace(STARTUP_TRACE_NAME);


        Button getPicButton = (Button) findViewById(R.id.main_getPicButton);
        getPicButton.setOnClickListener(this);

        initClientWithApplicationUseAddInterceptor();       // usually use this.

    }

    private void initClientWithApplicationUseAddInterceptor(){
        client = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }


    private void onGetPicButtonClick(){
        Log.d(TAG, "Starting trace");
        mTrace.start();

        // Increment the counter of number of requests sent in the trace.
        Log.d(TAG, "Incrementing number of requests counter in trace");
        mTrace.incrementCounter(REQUESTS_COUNTER_NAME);

        layoutImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

//        String url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
        String url = "https://c1.neweggimages.com/ProductImageCompressAll300/22-178-422-02.jpg";
        Request request = new Request.Builder()
                .url(url)
                .build();
        requestGetImageAsynchronous(request);
    }

    @AddTrace(name = "requestGetImageAsynchronousTrace", enabled = true/*Optional*/)
    private void requestGetImageAsynchronous(Request request){
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.d(TAG, "Stopping trace");
                mTrace.stop();

                if (response.isSuccessful()) {
                    final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            layoutImage(bitmap);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call call, final IOException e) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        layoutTextView("Failure:"+e.getMessage());
                    }
                });
            }

        });
    }

    private void layoutTextView(String text) {
        TextView textView = (TextView) findViewById(R.id.main_getDataTextView);
        textView.setText(text);
    }


    private void layoutImage(Bitmap bitmap){
        ImageView imageView = (ImageView) findViewById(R.id.main_getImageView);
        imageView.setImageBitmap(bitmap);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_getPicButton:
                onGetPicButtonClick();
                break;

            default:
                break;
        }
    }
}
