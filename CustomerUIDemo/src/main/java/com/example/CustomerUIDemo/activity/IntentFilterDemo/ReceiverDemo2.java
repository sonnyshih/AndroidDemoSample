package com.example.CustomerUIDemo.activity.IntentFilterDemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverDemo2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MessageEntity messageEntity = (MessageEntity) intent.getSerializableExtra(
                IntentFilterDemoActivity.BUNDLE_SERILIZABLE_MESSAGE);

        Toast.makeText(context, "From: " + messageEntity.getFrom() +
                        "\nTo: " + messageEntity.getTo() +
                        "\nMessage: " + messageEntity.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}
