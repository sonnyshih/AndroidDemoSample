package com.example.demo.activity.base;

import android.app.Application;

import com.example.demo.Database.DBManager;
import com.example.demo.R;
import com.example.demo.activity.ManagerDemo.manager.AppManagerCenter;
import com.example.demo.activity.ManagerDemo.manager.AppManagerType;
import com.example.demo.activity.ManagerDemo.manager.LoginAppManager;
import com.example.demo.activity.ManagerDemo.manager.SettingManager;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.DeviceType;
import com.example.demo.manage.ApplicationManager;

public class SampleCodeDemoApp extends Application{
	private static boolean isDebugMode = false;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		setupDebugMode(true);
		
		ApplicationManager.getInstance().setContext(this);
		ApplicationManager.getInstance().setAppVersion(getAppVersion());
		ApplicationManager.getInstance().setDeviceType(getDeviceType());
		ApplicationManager.getInstance().setSqliteDBVersion(getSqliteDBVersion());

		// Create and check the database schema - Sonny Shih 2014/10/30
		DBManager.getInstance().startDatabase();

		registerAppManager();
	}

	private String getAppVersion() {
		return getString(R.string.app_version);
	}


	private DeviceType getDeviceType() {

		if (getResources().getBoolean(R.bool.is_tablet)) {
			return DeviceType.Tablet;
		} else {
			return DeviceType.Phone;
		}

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


	private void registerAppManager() {
		AppManagerCenter.getInstance().register(AppManagerType.Setting, SettingManager.getInstance());
		AppManagerCenter.getInstance().register(AppManagerType.Login, new LoginAppManager(this));

	}
}
