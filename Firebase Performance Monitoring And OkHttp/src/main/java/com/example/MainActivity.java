package com.example;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class MainActivity extends AppCompatActivity implements OnClickListener{
    // Original Lib: http://square.github.io/okhttp/

    private OkHttpClient client;
    private Call call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getDataButton = (Button) findViewById(R.id.main_getDataButton);
        getDataButton.setOnClickListener(this);

        Button getJsonDataButton = (Button) findViewById(R.id.main_getJsonDataButton);
        getJsonDataButton.setOnClickListener(this);

        Button postJsonDataButton = (Button) findViewById(R.id.main_postJsonDataButton);
        postJsonDataButton.setOnClickListener(this);

        Button getPicButton = (Button) findViewById(R.id.main_getPicButton);
        getPicButton.setOnClickListener(this);

        initClientWithApplicationUseAddInterceptor();       // usually use this.
//        initClientWithNetworkUseAddNetworkInterceptor();
    }

    private void onGetDataButtonClick() {

        layoutTextView("Getting Data....");

        String url = "http://www.publicobject.com/helloworld.txt";
        Request request = new Request.Builder()
                .url(url)
                .build();
        requestAsynchronous(request);
    };

    private void onGetJsonDataButtonClick() {

        layoutTextView("Getting Data....");

        String url = "https://www.ows.newegg.com/Store.egg/ShopAllNavigation";
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Newegg Android App / 4.7.2")
                .header("Accept-Encoding", "gzip,deflate")
                .header("Accept-Charset", "utf-8")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
        requestGetJsonDataAsynchronous(request);
    };

    private void onPostJsonDataButtonClick() {

        layoutTextView("Getting Data....");

        String url = "http://www.ows.newegg.com/Search.egg/Query";
        String postBody = "{\n" +
                "  \"BrandId\":-1,\n" +
                "  \"BrandList\":[],\n" +
                "  \"CategoryId\":-1,\n" +
                "  \"Keyword\":\"acer\",\n" +
                "  \"MaxPrice\":\"\",\n" +
                "  \"MinPrice\":\"\",\n" +
                "  \"ModuleParams\":\"\",\n" +
                "  \"NValue\":\"\",\n" +
                "  \"NodeId\":-1,\n" +
                "  \"PageNumber\":1,\n" +
                "  \"ProductType\":[],\n" +
                "  \"SearchProperties\":[],\n" +
                "  \"StoreId\":-1,\n" +
                "  \"StoreType\":-1,\n" +
                "  \"SubCategoryId\":-1}";

        MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(jsonMediaType, postBody);


        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Newegg Android App / 4.7.2")
                .header("Accept-Encoding", "gzip,deflate")
                .header("Accept-Charset", "utf-8")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .post(requestBody)
                .build();
        requestGetJsonDataAsynchronous(request);
    };

    private void onGetPicButtonClick(){
        layoutImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        String url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
        Request request = new Request.Builder()
                .url(url)
                .build();
        requestGetImageAsynchronous(request);
    }

    private void initClientWithApplicationUseAddInterceptor(){
        client = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private void initClientWithNetworkUseAddNetworkInterceptor(){
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new RequestInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    // Use Synchronous
    private void requestSynchronous(final Request request) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    call = client.newCall(request);
                    Response response = call.execute();
                    System.out.print("requestSynchronous\n" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //Use asynchronous
    private void requestAsynchronous(Request request){
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                if (response.isSuccessful()) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                layoutTextView(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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

    private void requestGetJsonDataAsynchronous(Request request){
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String responseJson = convertByteToString(response);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            layoutTextView(responseJson);
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

    private void requestGetImageAsynchronous(Request request){
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {

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

    private String convertByteToString(Response response) {
        String contentEncoding = response.header("Content-Encoding");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;

        try {
            if (contentEncoding == null && contentEncoding == "") {
                return response.body().string();
            } else if (contentEncoding.equalsIgnoreCase("gzip")) {
                GZIPInputStream gzip = new GZIPInputStream(response.body().byteStream());
                reader = new BufferedReader(new InputStreamReader(gzip));
            } else if (contentEncoding.equalsIgnoreCase("deflate")) {
                DeflaterInputStream deflate = new DeflaterInputStream(response.body().byteStream());
                reader = new BufferedReader(new InputStreamReader(deflate));
            } else {
                reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
            }

            String stringHolder;
            while ((stringHolder = reader.readLine()) != null) {
                stringBuilder.append(stringHolder);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    private void layoutTextView(String text) {
        TextView textView = (TextView) findViewById(R.id.main_getDataTextView);
        textView.setText(text);
    }

    private void layoutImage(Bitmap bitmap){
        ImageView  imageView = (ImageView) findViewById(R.id.main_getImageView);
        imageView.setImageBitmap(bitmap);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_getDataButton:
                onGetDataButtonClick();
                break;

            case R.id.main_getJsonDataButton:
                onGetJsonDataButtonClick();
                break;

            case R.id.main_postJsonDataButton:
                onPostJsonDataButtonClick();
                break;

            case R.id.main_getPicButton:
                onGetPicButtonClick();
                break;

            default:
                break;
        }
    }
}
