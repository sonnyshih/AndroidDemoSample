
package com.example.demo.activity.VolleyDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.CustomerGsonRequestActivity;
import com.example.demo.activity.VolleyDemo.FileDownloadDemo.FileDownloadVolleyRequestActivity;
import com.example.demo.activity.VolleyDemo.ImageLoaderDemo.ImageLoaderActivity;
import com.example.demo.activity.VolleyDemo.JsonOjbectRequestDemo.JsonObjectRequestActivity;
import com.example.demo.activity.VolleyDemo.ShowSimpleRequestDemo.ShowSimpleRequestActivity;
import com.example.demo.activity.VolleyDemo.SingletonPatternDemo.SingletonPatternActivity;

public class VolleyDemoActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_demo_activity);

        Button showSimpleRequestButton = (Button) findViewById(R.id.volleyDemo_showSimpleRequestButton);
        showSimpleRequestButton.setOnClickListener(this);

        Button singletonPatternButton = (Button) findViewById(R.id.volleyDemo_singletonPatternButton);
        singletonPatternButton.setOnClickListener(this);

        Button imageLoaderButton = (Button) findViewById(R.id.volleyDemo_imageLoaderButton);
        imageLoaderButton.setOnClickListener(this);

        Button jsonObjectRequestButton = (Button) findViewById(R.id.volleyDemo_jsonObjectRequestButton);
        jsonObjectRequestButton.setOnClickListener(this);

        Button customerGsonRequestButton = (Button) findViewById(R.id.volleyDemo_customerGsonRequestButton);
        customerGsonRequestButton.setOnClickListener(this);

        Button fileDownloadRequestButton = (Button) findViewById(R.id.volleyDemo_fileDownloadRequestButton);
        fileDownloadRequestButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.volleyDemo_showSimpleRequestButton:
                startActivity(new Intent(this, ShowSimpleRequestActivity.class));
                break;

            case R.id.volleyDemo_singletonPatternButton:
                startActivity(new Intent(this, SingletonPatternActivity.class));
                break;

            case R.id.volleyDemo_imageLoaderButton:
                startActivity(new Intent(this, ImageLoaderActivity.class));
                break;

            case R.id.volleyDemo_jsonObjectRequestButton:
                startActivity(new Intent(this, JsonObjectRequestActivity.class));
                break;

            case R.id.volleyDemo_customerGsonRequestButton:
                startActivity(new Intent(this, CustomerGsonRequestActivity.class));
                break;

            case R.id.volleyDemo_fileDownloadRequestButton:
                startActivity(new Intent(this, FileDownloadVolleyRequestActivity.class));
                break;

            default:
                break;
        }
    }
}
