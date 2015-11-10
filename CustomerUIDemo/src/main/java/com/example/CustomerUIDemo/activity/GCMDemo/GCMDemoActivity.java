package com.example.CustomerUIDemo.activity.GCMDemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.CustomerUIDemo.R;

public class GCMDemoActivity extends Activity implements OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcm_demo_activity);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.gcmDemo_firstDemo:
                startActivity(new Intent(this, FirstDemoActivity.class));
                break;

            case R.id.gcmDemo_secondDemo:
                startActivity(new Intent(this, SecondDemoActivity.class));
                break;

            default:
                break;
        }


    }
}
