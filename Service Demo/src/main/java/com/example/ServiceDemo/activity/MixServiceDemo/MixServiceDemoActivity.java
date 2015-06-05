package com.example.ServiceDemo.activity.MixServiceDemo;

import com.example.ServiceDemo.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
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
import android.widget.EditText;

public class MixServiceDemoActivity extends Activity implements OnClickListener {
	private Button startServiceButton;
	private Button stopServiceButton;
	private Button getValueButton;
	private Button setValueButton;
	private Button finishButton;

	EditText setValueEditText;

	private CountBindService countBindService;
	private String ClassName = CountBindService.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mix_service_demo_activity);

		startServiceButton = (Button) findViewById(R.id.mixServiceDemo_startServiceButton);
		startServiceButton.setOnClickListener(this);

		stopServiceButton = (Button) findViewById(R.id.mixServiceDemo_stopServiceButton);
		stopServiceButton.setOnClickListener(this);

		getValueButton = (Button) findViewById(R.id.mixServiceDemo_getValueButton);
		getValueButton.setOnClickListener(this);

		setValueButton = (Button) findViewById(R.id.mixServiceDemo_setValueButton);
		setValueButton.setOnClickListener(this);

		setValueEditText = (EditText) findViewById(R.id.mixServiceDemo_setValueEditText);

		finishButton = (Button) findViewById(R.id.mixServiceDemo_finishButton);
		finishButton.setOnClickListener(this);

		if (isServiceRunning(ClassName)) {
			Intent intent = new Intent(this, CountBindService.class);
			bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (isServiceRunning(ClassName)) {
			this.unbindService(serviceConnection);
		}
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

	private boolean isServiceRunning(String className) {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager
				.getRunningServices(Integer.MAX_VALUE)) {
			if (className.equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(this, CountBindService.class);

		switch (view.getId()) {
		case R.id.mixServiceDemo_startServiceButton:
			// Start and Bind service
			startService(intent);
			bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
			Log.d("Mylog", "Start");

			break;

		case R.id.mixServiceDemo_stopServiceButton:
			
			// Unbind and stop Service
			unbindService(serviceConnection);
			stopService(intent);
			Log.d("Mylog", "Stop");

			break;

		case R.id.mixServiceDemo_getValueButton:
			if (isServiceRunning(ClassName)) {
				Log.d("Mylog", "Get Value is " + countBindService.getCount());
			} else {
				Log.d("Mylog", ClassName + " is not running!!");
			}

			break;

		case R.id.mixServiceDemo_setValueButton:
			String tmpValue = setValueEditText.getText().toString();
			int value = 0;
			if (!tmpValue.equals("")) {
				value = Integer.parseInt(tmpValue);
			}

			if (isServiceRunning(ClassName)) {
				countBindService.setCount(value);
			} else {
				Log.d("Mylog", ClassName + " is not running!!");
			}

			break;

		case R.id.mixServiceDemo_finishButton:
			finish();
			break;

		default:
			break;
		}
	}


}
