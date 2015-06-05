package com.example.CustomerUIDemo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

	public DbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		createMessageTable(sqLiteDatabase);
		createChartletTable(sqLiteDatabase);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	
	private void createMessageTable(SQLiteDatabase sqLiteDatabase){
		String Fields;
		
		// Create the message table	- Sonny Shih 2014/09/22
		Fields = "_id INTEGER PRIMARY KEY";		// Auto create ID - Sonny Shih 2014/09/22
		Fields += ", date INTEGER";				// Time stamp - Sonny Shih 2014/09/22
		Fields += ", jid VARCHAR";
		Fields += ", is_from_me INTEGER";		// 0: Not from me, 1: from me - Sonny Shih 2014/09/22
		Fields += ", message TEXT";
		Fields += ", read INTEGER";				// 0: Not read, 1: readed - Sonny Shih 2014/09/22
		
		String sqlString = "create table if not exists message("+ Fields +")";	
		sqLiteDatabase.execSQL(sqlString);

	}

	private void createChartletTable(SQLiteDatabase sqLiteDatabase){
		String Fields;
		
		// Create the chart table	- Sonny Shih 2014/09/22
		Fields = "_id INTEGER PRIMARY KEY";		// Auto create ID - Sonny Shih 2014/10/08
		Fields += ", date INTEGER";				// Time stamp - Sonny Shih 2014/10/08
		Fields += ", is_default INTEGER";		// 0: new chartlet, 1: default chartlet - Sonny Shih 2014/10/08
		Fields += ", group_serialNum INTEGER";	// Group serial number - Sonny Shih 2014/10/14
		Fields += ", group_name TEXT";
		Fields += ", chartlet_name TEXT";
		
		String sqlString = "create table if not exists chartlet("+ Fields +")";	
		sqLiteDatabase.execSQL(sqlString);

		insertDefaultChartletIntoTable(sqLiteDatabase);
	}

	private void insertDefaultChartletIntoTable(SQLiteDatabase sqLiteDatabase){
		
		// Clean the default chartlet - Sonny Shih 2014/10/08
//		String deleteSqlString = "delete from chartlet where type = 0";
//		sqLiteDatabase.execSQL(deleteSqlString);

		// insert the default chartlet into the table -  Sonny Shih 2014/10/08
		String[] defaultChartletArray = getDefaultChartlet();
		for (String chartlet : defaultChartletArray) {
			String insertSqlString = generateInsertDefaultChartletSql(chartlet);
			sqLiteDatabase.execSQL(insertSqlString);
		}
		
		String[] defaultChartletArray2 = getDefaultChartlet2();
		for (String chartlet : defaultChartletArray2) {
			String insertSqlString = generateInsertDefaultChartletSql2(chartlet);
			sqLiteDatabase.execSQL(insertSqlString);
		}

		String[] defaultChartletArray3 = getDefaultChartlet3();
		for (String chartlet : defaultChartletArray3) {
			String insertSqlString = generateInsertDefaultChartletSql3(chartlet);
			sqLiteDatabase.execSQL(insertSqlString);
		}

	}
	
	private String generateInsertDefaultChartletSql(String chartletName){
		String defaultGroupName = "group 1";
		long currentTimeMillis = System.currentTimeMillis();
		String insertSqlString = "insert into chartlet(date, is_default, group_serialNum, group_name, chartlet_name)";
		insertSqlString += " values(\"" + currentTimeMillis + "\", 1, 1, \""
				+ defaultGroupName + "\",\"" + chartletName + "\")";

		return insertSqlString;
	}

	private String generateInsertDefaultChartletSql2(String chartletName){
		String defaultGroupName = "group 2";
		long currentTimeMillis = System.currentTimeMillis();
		String insertSqlString = "insert into chartlet(date, is_default, group_serialNum, group_name, chartlet_name)";
		insertSqlString += " values(\"" + currentTimeMillis + "\", 1, 2, \""
				+ defaultGroupName + "\",\"" + chartletName + "\")";

		return insertSqlString;
	}

	private String generateInsertDefaultChartletSql3(String chartletName){
		String defaultGroupName = "group 3";
		long currentTimeMillis = System.currentTimeMillis();
		String insertSqlString = "insert into chartlet(date, is_default, group_serialNum, group_name, chartlet_name)";
		insertSqlString += " values(\"" + currentTimeMillis + "\", 1, 3,\""
				+ defaultGroupName + "\",\"" + chartletName + "\")";

		return insertSqlString;
	}
	
	
	// List chartlet file name - Sonny Shih 2014/10/09
	private String[] getDefaultChartlet(){
		String[] defaultChartletList = { "f_static_000", "f_static_001", "f_static_002", "f_static_003", "f_static_004",
				"f_static_005", "f_static_006", "f_static_007", "f_static_008", "f_static_009", "f_static_010", "f_static_012", "f_static_013", "f_static_014",
				"f_static_015", "f_static_016", "f_static_017", "f_static_018", "f_static_019", "f_static_020" };
		
		return defaultChartletList;
	}
	

	private String[] getDefaultChartlet2(){
		String[] defaultChartletList = { "f_static_021", "f_static_022", "f_static_023", "f_static_024", "f_static_025",
				"f_static_026", "f_static_027", "f_static_028", "f_static_029", "f_static_030", "f_static_031", "f_static_032", "f_static_033", "f_static_034",
				"f_static_035", "f_static_036", "f_static_037", "f_static_038", "f_static_039", "f_static_040" };
		
		return defaultChartletList;
	}

	private String[] getDefaultChartlet3(){
		String[] defaultChartletList = { "f_static_041", "f_static_042", "f_static_043", "f_static_044", "f_static_045",
				"f_static_046", "f_static_047", "f_static_048", "f_static_049", "f_static_050", "f_static_051", "f_static_052", "f_static_053", "f_static_054",
				"f_static_055", "f_static_056", "f_static_057", "f_static_058", "f_static_059", "f_static_060" };
		
		return defaultChartletList;
	}

}
