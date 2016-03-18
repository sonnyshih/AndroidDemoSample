package com.example.demo.Database;

import com.example.demo.manage.ApplicationManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private static DBManager instance;
	private static final String DB_FILE = "customer.db";
	private static int DB_VERSION = 1;

	private Context context;
	private DbHelper dbHelper;
	private SQLiteDatabase sqLiteDatabase;
	
	public DBManager(){
		context = ApplicationManager.getInstance().getContext();
		DB_VERSION = ApplicationManager.getInstance().getSqliteDBVersion();
		dbHelper = new DbHelper(context, DB_FILE, null, DB_VERSION); // Create the SQLite database
	}
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	// Just for checking the version of the database and update the database
	// schema - Sonny Shih 2014/10/30
	public void startDatabase(){
		sqLiteDatabase = dbHelper.getWritableDatabase();
	}
	
	public SQLiteDatabase getWritableDatabase(){
		sqLiteDatabase = dbHelper.getWritableDatabase();
		return sqLiteDatabase;
	}
	
	public SQLiteDatabase getReadableDatabase(){
		sqLiteDatabase = dbHelper.getReadableDatabase();
		return sqLiteDatabase;
	}
	
	public SQLiteDatabase getSQLiteDatabase(){
		return sqLiteDatabase;
	}
	
	public void closeDatabase(){
		sqLiteDatabase.close();
	}
}
