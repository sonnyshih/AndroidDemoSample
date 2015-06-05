package com.example.ServiceDemo.activity.ServiceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CountService extends Service {

	private boolean isThreadDisable;
	private static int count;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!isThreadDisable) {
					try {
						Thread.sleep(1000);
						count++;
						Log.d("Mylog", "Count is "+ count);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isThreadDisable = true;
		Log.d("Mylog", "Destroy CountService");
	}
	
	public static int getCount(){
		return count;
	}
}
