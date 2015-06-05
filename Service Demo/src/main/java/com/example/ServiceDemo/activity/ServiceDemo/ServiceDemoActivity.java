package com.example.ServiceDemo.activity.ServiceDemo;

import com.example.ServiceDemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Service
 * When Activity/App is finished, the service still keep running.
 * */
public class ServiceDemoActivity extends Activity implements OnClickListener{
	
	private Button startServiceButton;
	private Button stopServiceButton;
	private Button getCountButton;
	private Button finishButton;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_demo_activity);
		
		startServiceButton = (Button) findViewById(R.id.serviceDemo_startServiceButton);
		startServiceButton.setOnClickListener(this);
		
		stopServiceButton = (Button) findViewById(R.id.serviceDemo_stopServiceButton);
		stopServiceButton.setOnClickListener(this);
		
		getCountButton = (Button) findViewById(R.id.serviceDemo_getCountButton);
		getCountButton.setOnClickListener(this);
		
		finishButton = (Button) findViewById(R.id.serviceDemo_finishButton);
		finishButton.setOnClickListener(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		Intent intent = new Intent(this, CountService.class);
//		stopService(intent);
//		Log.d("Mylog", "onDestroy-out");
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(this, CountService.class);
		
		switch (view.getId()) {
		case R.id.serviceDemo_startServiceButton:
			// startSevice
			startService(intent);
			Log.d("Mylog", "Start");
			
			break;

		case R.id.serviceDemo_stopServiceButton:
			// stopService
			stopService(intent);
			Log.d("Mylog", "Stop");
			
			break;

		case R.id.serviceDemo_getCountButton:
			Log.d("Mylog", "Get count value="+CountService.getCount());
			
			break;
		
		case R.id.serviceDemo_finishButton:
			finish();
			Log.d("Mylog", "Finish");
			
		default:
			break;
		}
	}
	
}
