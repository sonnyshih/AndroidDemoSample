package com.example.CustomerUIDemo.activity.MenuAndActionBarDemo;

import com.example.CustomerUIDemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.Toast;

public class FloatContextMenuDemoActivity extends Activity{

	private Button floatingContextMenuDemoButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.float_context_menu_demo_activity);

		floatingContextMenuDemoButton = (Button) findViewById(R.id.floatContextMenuDemo_floatingContextMenuDemoButton);
		registerForContextMenu(floatingContextMenuDemoButton);	// long-click to open the Context Menu

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.contextMenu_busyIcon:
			Toast.makeText(this, "float context menu: click search",
					Toast.LENGTH_LONG).show();
			return true;

		case R.id.contextMenu_cart:
			Toast.makeText(this, "float context menu: click cart",
					Toast.LENGTH_LONG).show();
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}
	
}
