package com.example.demo.activity.DrawerDemo;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.ui.adapter.LeftDrawerMenuAdapter;

import java.util.ArrayList;

public class DrawerDemoActivity extends FragmentActivity implements OnItemClickListener{

	private DrawerLayout mainDrawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private RelativeLayout menuLayout;
	private ListView leftDrawerMenuListView;
	private TextView actionBarTitleTextView;
	private ArrayList<DrawerItem> arrayList = new ArrayList<DrawerItem>();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_demo_activity);
		
		initActionBar();
		
		// Contact fragment
		DrawerItem contactDrawerItem = new DrawerItem(R.drawable.ic_contact,
				"Contact", new DrawerContactFragment());
		arrayList.add(contactDrawerItem);

		// Approve fragment
		DrawerItem approveDrawerItem = new DrawerItem(R.drawable.ic_contact_approve,
				"Approve", new DrawerApproveFragment());
		arrayList.add(approveDrawerItem);

		
		menuLayout = (RelativeLayout) findViewById(R.id.drawer_demo_menuLayout);

		LeftDrawerMenuAdapter leftDrawerMenuAdapter = new LeftDrawerMenuAdapter(
				this, arrayList);
		leftDrawerMenuListView = (ListView) findViewById(R.id.drawer_demo_leftDrawerMenuListView);
		leftDrawerMenuListView.setAdapter(leftDrawerMenuAdapter);
		leftDrawerMenuListView.setOnItemClickListener(this);

		mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
//		mainDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
//				GravityCompat.START);

		actionBarDrawerToggle = new ActionBarDrawerToggle(
				this, 						/* host Activity */
				mainDrawerLayout, 			/* DrawerLayout object */
				R.drawable.ic_drawer, 		/* nav drawer image to replace 'Up' caret */
				R.string.main_drawer_open, 	/* "open drawer" description for accessibility */
				R.string.main_drawer_close 	/* "close drawer" description for accessibility*/
				) {

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// getActionBar().setTitle(drawerTitle);
				// Log.d("Mylog", "open-title="+drawerTitle);
			}

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				// getActionBar().setTitle(title);
				// Log.d("Mylog", "close-title="+title);
			}

		};

		mainDrawerLayout.setDrawerListener(actionBarDrawerToggle);

		// enable ActionBar app icon to behave as action to toggle nav drawer 
		// Set the Theme style in style.xml and xxxxManifest.xml
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.drawer_demo_content_frame, new DrawerMainFragment()).commit();

	}

	private void initActionBar() {
		ViewGroup actionBarViewGroup = (ViewGroup) getLayoutInflater().inflate(
				R.layout.actionbar, null);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(actionBarViewGroup);
		actionBarTitleTextView = (TextView) actionBarViewGroup
				.findViewById(R.id.actionbar_titleTextView);
		actionBarTitleTextView.setText("Drawer Menu");

	}
	
	
	// Setting for drawer
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Setting for drawer
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		// Setting for drawer
		// clear single choice ListView selection
		leftDrawerMenuListView.setItemChecked(-1, true);
		
		actionBarTitleTextView.setText("Drawer Menu");
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		actionBarTitleTextView.setText(arrayList.get(position).getName());

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		fragmentManager.popBackStack();

		transaction.replace(R.id.drawer_demo_content_frame, arrayList.get(position)
				.getFragment());
		transaction.addToBackStack(null);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.commit();

		leftDrawerMenuListView.setItemChecked(position, true);
		leftDrawerMenuListView.setSelection(position);
		setTitle(arrayList.get(position).getName());
		mainDrawerLayout.closeDrawer(menuLayout);

	}

	
}
