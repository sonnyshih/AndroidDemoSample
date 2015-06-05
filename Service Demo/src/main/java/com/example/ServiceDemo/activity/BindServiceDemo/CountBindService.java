package com.example.ServiceDemo.activity.BindServiceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CountBindService extends Service {
	private boolean isThreadDisable;
	private int count;
	private final IBinder serviceBinder = new ServiceBinder();
	
	
	@Override
	public IBinder onBind(Intent intent) {
		return serviceBinder;
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
						Log.d("Mylog", "Count is " +count);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
				
			}
		}).start();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		isThreadDisable = true;
	}
	
	public int getCount(){
		return count;
	}

	class ServiceBinder extends Binder{
		public CountBindService getService(){
			return CountBindService.this;
		}
	}
}
