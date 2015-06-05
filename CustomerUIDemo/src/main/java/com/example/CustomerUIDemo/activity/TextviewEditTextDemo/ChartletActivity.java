package com.example.CustomerUIDemo.activity.TextviewEditTextDemo;

import java.util.HashMap;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.Database.DBManager;
import com.example.CustomerUIDemo.activity.TabHost.DummyTabContent;
import com.example.CustomerUIDemo.ui.adapter.ChartlestListAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class ChartletActivity extends FragmentActivity implements
		OnTabChangeListener, OnItemClickListener {

	public static final String BUNDLE_STRING_CHARTLET_NAME = "BUNDLE_STRING_CHARTLET_NAME";
	
	private TabHost tabHost;
	private GridView gridView;
	private ChartlestListAdapter chartlestListAdapter; 
	
	private String[] chartletGroupNameArray;
	private HashMap<String, String[]> chartletGroup;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chartlet_activity);

		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		chartletGroupNameArray = getChartletGroup();
		chartletGroup = new HashMap<String, String[]>();
		for (String chartletGroupName : chartletGroupNameArray) {
			setupTab(chartletGroupName);
			String[] chartlet = getChartlet(chartletGroupName);
			chartletGroup.put(chartletGroupName, chartlet);
		}
		
		
		tabHost.setOnTabChangedListener(this);
		
		String[] chartletList = chartletGroup.get(chartletGroupNameArray[0]);
		gridView = (GridView) findViewById(R.id.chartlet_gridView);
		chartlestListAdapter = new ChartlestListAdapter(this,chartletList);
		gridView.setAdapter(chartlestListAdapter);
		gridView.setOnItemClickListener(this);
		
	}

	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}


	private void setupTab(String tag) {

		View tabview = LayoutInflater.from(this).inflate(R.layout.chartlet_tab_item, null);
		TextView tv = (TextView) tabview.findViewById(R.id.content_tab_TextView);
		tv.setText(tag);
		
		TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview)
				.setContent(new DummyTabContent(this));
		tabHost.addTab(setContent);

	}

	private String[] getChartletGroup(){
		String LocalSqlString = "select group_serialNum, group_name from chartlet";
		LocalSqlString += " group by group_serialNum";
		SQLiteDatabase sqllitDatabase = DBManager.getInstance().getSQLiteDatabase();
		if (!sqllitDatabase.isOpen()) {
			sqllitDatabase = DBManager.getInstance().getWritableDatabase();
		}
		
		Cursor cursor = sqllitDatabase.rawQuery(LocalSqlString, null);
		int counter = cursor.getCount();
		String[] chartletGroupNameArray = new String[counter];
		
		int index = 0;
		if (counter > 0) {
			while(cursor.moveToNext()){
				chartletGroupNameArray[index] = cursor.getString(1);
				index ++;
			}
		}
		cursor.close();

		return chartletGroupNameArray;
	}
	
	private String[] getChartlet(String groupName){
		String LocalSqlString = "select chartlet_name from chartlet";
		LocalSqlString += " where group_name = \""+ groupName +"\" order by chartlet_name";
		SQLiteDatabase sqllitDatabase = DBManager.getInstance().getSQLiteDatabase();
		if (!sqllitDatabase.isOpen()) {
			sqllitDatabase = DBManager.getInstance().getWritableDatabase();
		}
		
		Cursor cursor = sqllitDatabase.rawQuery(LocalSqlString, null);
		int counter = cursor.getCount();
		String[] chartletNameArray = new String[counter];
		
		int index = 0;
		if (counter > 0) {
			while(cursor.moveToNext()){
				chartletNameArray[index] = cursor.getString(0);
				index ++;
			}
		}
		cursor.close();

		return chartletNameArray;
		
	}
	
	@Override
	public void onTabChanged(String tabId) {
		int position = tabHost.getCurrentTab();
		
		String[] chartletList = chartletGroup.get(chartletGroupNameArray[position]);
		chartlestListAdapter.setChartletList(chartletList);
		chartlestListAdapter.notifyDataSetChanged();
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		String[] chartletList = chartlestListAdapter.getChartletList();
		
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_STRING_CHARTLET_NAME, chartletList[position]);
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();
	}

	
}
