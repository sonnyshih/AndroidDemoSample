package com.example.demo.manage;

import android.content.Context;
import android.os.Environment;

import com.example.demo.activity.WebServiceTaskManagerDemo.type.DeviceType;


public class ApplicationManager {
	private static ApplicationManager instance;
	private Context context;
	private String appVersion;
	private DeviceType deviceType;
	private int sqliteDBVersion;
	private String bulidNumber;
	private String date;
	
	private ApplicationManager() {
	}

	public static ApplicationManager getInstance() {
		if (instance == null) {
			instance = new ApplicationManager();
		}
		return instance;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}


	public String getAuthToken() {
		return "";
//		if (AppManagerCenter.getLoginAppManager().isLogin()) {
//			String authToken = AppManagerCenter.getLoginAppManager().getAuthToken();
//			return authToken == null ? "" : authToken;
//		}
//
//		String guestAuthToken = AppManagerCenter.getCheckFlowSwitcherManger().getAuthToken();
//		return guestAuthToken == null ? "" : guestAuthToken;
	}

	public int getSqliteDBVersion() {
		return sqliteDBVersion;
	}

	public void setSqliteDBVersion(int sqliteDBVersion) {
		this.sqliteDBVersion = sqliteDBVersion;
	}

	public String getForderRootPath() {

		String sdCardDir = null;
		String phoneDataDir = null;
		String path;

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // SD card is exist or
														// not

		if (sdCardExist) {
			// Get the SD Card root: /mnt/sdcard - Sonny Shih 2014/09/26
			sdCardDir = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			path = sdCardDir;

		} else {
			// Get the phone root: /data - Sonny Shih 2014/09/26
			phoneDataDir = Environment.getDataDirectory().getAbsolutePath();

			path = phoneDataDir;
		}

		return path;
	}	
}

