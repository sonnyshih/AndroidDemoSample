package com.example.ServiceDemo.activity.BroadcastReceiverDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SimpleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String MSG = intent.getStringExtra("msg");
		Toast.makeText(context, "Intent:" + intent + " \nThe MSG:" + MSG,
				Toast.LENGTH_LONG).show();
	}

}
