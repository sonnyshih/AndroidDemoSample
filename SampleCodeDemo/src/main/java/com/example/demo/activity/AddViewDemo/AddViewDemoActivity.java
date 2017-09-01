package com.example.demo.activity.AddViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddViewDemoActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_view_demo_activity);

        Button scrollViewDemoButton = (Button) findViewById(R.id.addViewDemo_scrollViewDemoButton);
        scrollViewDemoButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.addViewDemo_scrollViewDemoButton:
                intent.setClass(this, ScrollViewDemoActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
