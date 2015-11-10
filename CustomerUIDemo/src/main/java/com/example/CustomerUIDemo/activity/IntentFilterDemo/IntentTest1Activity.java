
package com.example.CustomerUIDemo.activity.IntentFilterDemo;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.CustomerUIDemo.R;

public class IntentTest1Activity extends Activity implements OnClickListener {
    private String ACTION_VIEW = "com.flysnow.intent.ACTION_VIEW";

    private Button sendEmptyIntentButton;
    private Button startActivityButton;
    private Button showAllAppButton;
    private Button sendBroadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_test1_activity);

        sendEmptyIntentButton = (Button) findViewById(R.id.intentTest1_sendEmptyIntentButton);
        sendEmptyIntentButton.setOnClickListener(this);

        startActivityButton = (Button) findViewById(R.id.intentTest1_startActivityButton);
        startActivityButton.setOnClickListener(this);

        showAllAppButton = (Button) findViewById(R.id.intentTest1_showAllAppButton);
        showAllAppButton.setOnClickListener(this);

        sendBroadcastButton = (Button) findViewById(R.id.intentTest1_sendBroadcastButton);
        sendBroadcastButton.setOnClickListener(this);


        // Register a receiver
        super.registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "the Intent Filter of Broadcast has set an Action", Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(ACTION_VIEW));


    }

    private void sendEmptyIntent() {
        Intent intent = new Intent();
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.d("Mylog", "Exception = " + e);
        }
    }

    private void sendIntent() {
        Intent intent = new Intent(ACTION_VIEW);
        startActivity(intent);
    }

    private void sendIntentToShowAllApp(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        startActivity(intent);
    }

    private void sendBroadcast() {
        Intent intent = new Intent(ACTION_VIEW);
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.intentTest1_sendEmptyIntentButton:
                sendEmptyIntent();
                break;

            case R.id.intentTest1_startActivityButton:
                sendIntent();
                break;

            case R.id.intentTest1_showAllAppButton:
                sendIntentToShowAllApp();
                break;

            case R.id.intentTest1_sendBroadcastButton:
                sendBroadcast();
                break;

            default:
                break;
        }
    }
}
