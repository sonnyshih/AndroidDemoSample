package com.example.CustomerUIDemo.activity.GCMDemo.GCMFirstDemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMFirstReceiver extends BroadcastReceiver {


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

                Toast.makeText(context, " GCM MESSAGE = " + extras.getString("message"),
                        Toast.LENGTH_LONG).show();

//                Intent i = new Intent(context, MainActivity.class);
//                i.setAction("android.intent.action.MAIN");
//                i.addCategory("android.intent.category.LAUNCHER");
//                MagicLenGCM.sendLocalNotification(context, NOTIFICATION_ID,
//                        R.drawable.ic_launcher, "GCM 通知", extras
//                                .getString("message"), "magiclen.org", false,
//                        PendingIntent.getActivity(context, 0, i,
//                                PendingIntent.FLAG_CANCEL_CURRENT));
            }

        }

    }
}
