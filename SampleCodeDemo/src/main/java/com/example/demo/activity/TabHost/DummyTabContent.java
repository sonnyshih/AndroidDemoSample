package com.example.demo.activity.TabHost;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

public class DummyTabContent implements TabContentFactory{
	private Context context;
	
	public DummyTabContent(Context context){
		this.context = context;
	}
			

	@Override
	public View createTabContent(String tag) {
		// Just create a textview to display - Sonny Shih 2014/10/08
		View v = new TextView(context);
		return v;
	}
	

}
