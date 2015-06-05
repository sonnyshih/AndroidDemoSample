package com.example.CustomerUIDemo.activity.MenuAndActionBarDemo;

import com.example.CustomerUIDemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuAndActionBarDemoActivity extends Activity implements OnClickListener{
	
	private Button actionBarDemoButton;
	private Button customerActionBarDemoButton;
	private Button floatContextMenuDemoButton;
	private Button individualViewActionModeDemoButton;
	private Button listviewActionModeDemoButton;
	private Button customListviewActionModeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_and_action_bar_demo_activity);

		actionBarDemoButton = (Button) findViewById(R.id.menuAndActionBarDemo_ActionBarDemoButton);
		actionBarDemoButton.setOnClickListener(this);
		
		customerActionBarDemoButton = (Button) findViewById(R.id.menuAndActionBarDemo_customerActionBarDemoButton);
		customerActionBarDemoButton.setOnClickListener(this);
		
		floatContextMenuDemoButton = (Button) findViewById(R.id.menuAndActionBarDemo_floatContextMenuDemoButton);
		floatContextMenuDemoButton.setOnClickListener(this);
		
		individualViewActionModeDemoButton = (Button) findViewById(R.id.menuAndActionBarDemo_individualViewActionModeDemoButton);
		individualViewActionModeDemoButton.setOnClickListener(this);
		
		listviewActionModeDemoButton = (Button) findViewById(R.id.menuAndActionBarDemo_listviewActionModeDemoButton);
		listviewActionModeDemoButton.setOnClickListener(this);
		
		customListviewActionModeButton = (Button) findViewById(R.id.menuAndActionBarDemo_customListviewActionModeButton);
		customListviewActionModeButton.setOnClickListener(this);
		
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void onClick(View view) {
		
		Intent intent = new Intent();
		
		switch (view.getId()) {
		case R.id.menuAndActionBarDemo_ActionBarDemoButton:
			intent.setClass(this, ActionBarDemoActivity.class);
			break;

		case R.id.menuAndActionBarDemo_customerActionBarDemoButton:
			intent.setClass(this, CustomerActionBarActivity.class);
			break;
			
		case R.id.menuAndActionBarDemo_floatContextMenuDemoButton:
			intent.setClass(this, FloatContextMenuDemoActivity.class);
			break;

		case R.id.menuAndActionBarDemo_individualViewActionModeDemoButton:
			intent.setClass(this, IndividualViewActionModeDemoActivity.class);
			break;
			
		case R.id.menuAndActionBarDemo_listviewActionModeDemoButton:
			intent.setClass(this, ListViewActionModeDemoActivity.class);
			break;
			
		case R.id.menuAndActionBarDemo_customListviewActionModeButton:
			intent.setClass(this, CustomListViewActionModeActivity.class);
			break;
			
			
		default:
			break;
		}
		
		startActivity(intent);
	}

	
}
