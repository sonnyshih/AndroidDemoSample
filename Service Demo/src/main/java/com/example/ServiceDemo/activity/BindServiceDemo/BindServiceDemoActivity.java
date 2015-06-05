package com.example.ServiceDemo.activity.BindServiceDemo;

import com.example.ServiceDemo.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * BindService
 * When Activity is finished, the BindService will be destroyed.
 * */
public class BindServiceDemoActivity extends Activity implements OnClickListener{

	private Button startServiceButton;
	private Button stopServiceButton;
	private Button getCountButton;
	private Button finishButton;

	private CountBindService countBindService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bind_service_demo_activity);

		startServiceButton = (Button) findViewById(R.id.bindServiceDemo_startServiceButton);
		startServiceButton.setOnClickListener(this);

		stopServiceButton = (Button) findViewById(R.id.bindServiceDemo_stopServiceButton);
		stopServiceButton.setOnClickListener(this);

		getCountButton = (Button) findViewById(R.id.bindServiceDemo_getCountButton);
		getCountButton.setOnClickListener(this);

		finishButton = (Button) findViewById(R.id.bindServiceDemo_finishButton);
		finishButton.setOnClickListener(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		unbindService(serviceConnection);
		Log.d("Mylog", "onDestroy-out");
	}
	
	

	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			countBindService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			countBindService = ((CountBindService.ServiceBinder) service)
					.getService();
		}
	};

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bindServiceDemo_startServiceButton:
			// start Bind Sevice
			Intent intent = new Intent(this, CountBindService.class);
			bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
			Log.d("Mylog", "Start");

			break;

		case R.id.bindServiceDemo_stopServiceButton:
			// stop Bind Service
			this.unbindService(serviceConnection);			
			Log.d("Mylog", "Stop");

			break;

		case R.id.bindServiceDemo_getCountButton:
			 Log.d("Mylog", "Get count value="+countBindService.getCount());

			break;

		case R.id.bindServiceDemo_finishButton:
			finish();
			Log.d("Mylog", "Finish");

		default:
			break;
		}

	}

}
