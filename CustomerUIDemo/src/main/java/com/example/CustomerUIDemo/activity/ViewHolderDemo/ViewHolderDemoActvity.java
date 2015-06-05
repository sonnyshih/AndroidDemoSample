package com.example.CustomerUIDemo.activity.ViewHolderDemo;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.ui.adapter.ViewHolderDemoItemAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewHolderDemoActvity extends Activity{

	private ListView myListView;
	private ArrayList<HashMap<String, Object>> myList = new ArrayList<HashMap<String, Object>>();
	private ViewHolderDemoItemAdapter itemAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_holder_demo_actvity);
		
		for (int i = 0; i < 1000; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("Key "+i, "Value="+i);
			myList.add(item);
		}
		
		itemAdapter =  new ViewHolderDemoItemAdapter(this, myList);
		
		myListView = (ListView) findViewById(R.id.myListView);
		myListView.setAdapter(itemAdapter);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
