package com.example.CustomerUIDemo.activity.IntentFilterDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverDemo1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String searialNumber = intent.getStringExtra(IntentFilterDemoActivity.BUNDL_KEY_SEARIAL_NUMBER);

        Toast.makeText(context, "searial Number:" + searialNumber,
                Toast.LENGTH_LONG).show();
    }

}
