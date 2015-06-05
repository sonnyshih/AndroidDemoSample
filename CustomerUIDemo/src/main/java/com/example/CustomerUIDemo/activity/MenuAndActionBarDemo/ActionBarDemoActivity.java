package com.example.CustomerUIDemo.activity.MenuAndActionBarDemo;

import com.example.CustomerUIDemo.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ActionBarDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.action_bar_demo_activity);

		// enable ActionBar app icon to behave as action to toggle nav drawer 
		// Set the Theme style in style.xml and xxxxManifest.xml
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
    	case android.R.id.home:
			Toast.makeText(this, "Click Home Icon button ", Toast.LENGTH_SHORT).show();				
    		return true;

		case R.id.cart:
			Toast.makeText(this, "click cart", Toast.LENGTH_LONG).show();
			return true;

		case R.id.menuHome:
			Toast.makeText(this, "click home", Toast.LENGTH_LONG).show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
