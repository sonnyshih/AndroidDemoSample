package com.example.demo.activity.GCMDemo.DownstreamMessagingDemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class DownstreamMessagingReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {

            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {

                Toast.makeText(context, " GCM ERROR = " + extras.toString(),
                        Toast.LENGTH_LONG).show();

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {

                Toast.makeText(context, " GCM DELETE = " + extras.toString(),
                        Toast.LENGTH_LONG).show();

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
//                Toast.makeText(context, " GCM MESSAGE = " + extras.getString("message"),
//                        Toast.LENGTH_LONG).show();

                Toast.makeText(context, " GCM MESSAGE = " + extras.toString(),
                        Toast.LENGTH_LONG).show();

                Log.d("Mylog", "Get JSON=" + extras.toString());
            }

        }

    }
}
