package com.example.CustomerUIDemo.activity.MenuAndActionBarDemo;

import com.example.CustomerUIDemo.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerActionBarActivity extends Activity implements
		OnQueryTextListener {
	
	ActionBar actionBar;
	TextView searchTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_action_bar_activity);

		actionBar = getActionBar();
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// Set Logo and Title
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setTitle("The Action bar");

		// Customer Action Bar
		// Set the theme from style.xml and xxxxManifest.xml
//		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setDisplayShowCustomEnabled(true);
//
//		ViewGroup actionBarViewGroup = (ViewGroup) getLayoutInflater().inflate(
//				R.layout.actionbar, null);
//		actionBar.setCustomView(actionBarViewGroup);
//		TextView actionBarTitleTextView = (TextView) actionBarViewGroup
//				.findViewById(R.id.actionbar_titleTextView);
//		actionBarTitleTextView.setText("Drawer Menu");

		
		searchTextView = (TextView) findViewById(R.id.customer_action_bar_showSearchTextView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		// 設定Search icon 的 Listener (使用 OnQueryTextListener)
		// 在 res/menu/main.xml 中， 在 Search 這個item裡，
		// 要多設定 android:actionViewClass="android.widget.SearchView", 才能使用search
		// action.
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		searchView.setOnQueryTextListener(this);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:
			Toast.makeText(getApplicationContext(), "Click \"Icon\" button ",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.search:
			Toast.makeText(getApplicationContext(), "Click \"Search\" button ",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.cart:
			Toast.makeText(getApplicationContext(), "Click \"cart\" button ",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.menuHome:
			Toast.makeText(getApplicationContext(),
					"Click \"Submenu Home\" button ", Toast.LENGTH_SHORT)
					.show();
			break;

		case R.id.menuMyAccount:
			Toast.makeText(getApplicationContext(),
					"Click \"Submenu My Account\" button ", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (newText.isEmpty()) {
			newText = "";
		} else {
			newText = "Query so far: " + newText;
		}
		searchTextView.setText(newText);
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		Toast.makeText(this, "Searching for: " + query + "...",
				Toast.LENGTH_SHORT).show();
		return true;
	}

}
