package com.example.ServiceDemo.activity.home;

import com.example.ServiceDemo.R;
import com.example.ServiceDemo.activity.BindServiceDemo.BindServiceDemoActivity;
import com.example.ServiceDemo.activity.BroadcastReceiverDemo.BroadcastReceiverDemoActivity;
import com.example.ServiceDemo.activity.MixServiceDemo.MixServiceDemoActivity;
import com.example.ServiceDemo.activity.NotificationServiceDemo.NotificationActivity;
import com.example.ServiceDemo.activity.ServiceDemo.ServiceDemoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button startServiceDemoButton;
	private Button bindServiceDemoButton;
	private Button mixServiceDemoButton;
	private Button notificationServiceDemoButton;
	private Button broadcastReceiverDemoButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		startServiceDemoButton = (Button) findViewById(R.id.main_startServiceDemoButton);
		startServiceDemoButton.setOnClickListener(this);
		
		bindServiceDemoButton = (Button) findViewById(R.id.main_bindServiceDemoButton);
		bindServiceDemoButton.setOnClickListener(this);
		
		mixServiceDemoButton = (Button) findViewById(R.id.main_mixServiceDemoButton);
		mixServiceDemoButton.setOnClickListener(this);
		
		notificationServiceDemoButton = (Button) findViewById(R.id.main_notificationServiceDemoButton);
		notificationServiceDemoButton.setOnClickListener(this);
		
		broadcastReceiverDemoButton = (Button) findViewById(R.id.main_broadcastReceiverDemoButton);
		broadcastReceiverDemoButton.setOnClickListener(this);
		
		
	}

	
	@Override
	public void onClick(View view) {
		
		Intent intent = new Intent();
		
		switch (view.getId()) {
		case R.id.main_startServiceDemoButton:
			intent.setClass(this, ServiceDemoActivity.class);
			break;

		case R.id.main_bindServiceDemoButton:
			intent.setClass(this, BindServiceDemoActivity.class);
			break;
			
		case R.id.main_mixServiceDemoButton:
			intent.setClass(this, MixServiceDemoActivity.class);
			break;
			
		case R.id.main_notificationServiceDemoButton:
			intent.setClass(this, NotificationActivity.class);
			break;
			
		case R.id.main_broadcastReceiverDemoButton:
			intent.setClass(this, BroadcastReceiverDemoActivity.class);
			break;
						
		default:
			break;
		}
		
		startActivity(intent);
		
	}

}
