package com.example.CustomerUIDemo.activity.GCMDemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.activity.GCMDemo.GCMFirstDemo.GcmFirstDemoActivity;

public class GCMDemoActivity extends Activity implements OnClickListener{

    private Button firstDemo;
    private Button secondDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcm_demo_activity);

        firstDemo = (Button) findViewById(R.id.gcmDemo_firstDemo);
        firstDemo.setOnClickListener(this);

        secondDemo = (Button) findViewById(R.id.gcmDemo_secondDemo);
        secondDemo.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.gcmDemo_firstDemo:
                startActivity(new Intent(this, GcmFirstDemoActivity.class));
                break;

            case R.id.gcmDemo_secondDemo:
                startActivity(new Intent(this, GcmSecondDemoActivity.class));
                break;

            default:
                break;
        }


    }
}
