package com.example.CustomerUIDemo.activity.base;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.Database.DBManager;
import com.example.CustomerUIDemo.manage.ApplicationManager;

import android.app.Application;

public class CustomerUIDemoApp extends Application{
	private static boolean isDebugMode = false;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		setupDebugMode(true);
		
		ApplicationManager.getInstance().setContext(this);
		ApplicationManager.getInstance().setAppVersion(getAppVersion());
		ApplicationManager.getInstance().setSqliteDBVersion(getSqliteDBVersion());

		// Create and check the database schema - Sonny Shih 2014/10/30
		DBManager.getInstance().startDatabase();

	}

	private String getAppVersion() {
		return getString(R.string.app_version);
	}

	private int getSqliteDBVersion(){
		return Integer.parseInt(getString(R.string.sqlite_version));
	}

	private void setupDebugMode(boolean isDebug) {
		isDebugMode = isDebug;
	}

	public static boolean isDebugMode() {
		return isDebugMode;
	}
	

}
