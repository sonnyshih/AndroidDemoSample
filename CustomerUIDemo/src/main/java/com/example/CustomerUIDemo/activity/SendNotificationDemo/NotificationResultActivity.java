package com.example.CustomerUIDemo.activity.SendNotificationDemo;

import com.example.CustomerUIDemo.R;

import android.app.Activity;
import android.os.Bundle;

public class NotificationResultActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_result_activity);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	
	
}
