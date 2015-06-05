package com.example.CustomerUIDemo.activity.ExpandableListviewDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.ui.adapter.ExpandableListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class ExpandableListviewDemoActivity extends Activity implements
		OnChildClickListener, OnGroupClickListener, OnItemLongClickListener {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_listview_demo_activity);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		expListView.setOnChildClickListener(this);
		expListView.setOnGroupClickListener(this);
		
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		expListView.setOnItemLongClickListener(this);
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Top 250");
		listDataHeader.add("Now Showing");
		listDataHeader.add("Coming Soon..");

		// Adding child data
		List<String> top250 = new ArrayList<String>();
		top250.add("The Shawshank Redemption");
		top250.add("The Godfather");
		top250.add("The Godfather: Part II");
		top250.add("Pulp Fiction");
		top250.add("The Good, the Bad and the Ugly");
		top250.add("The Dark Knight");
		top250.add("12 Angry Men");

		List<String> nowShowing = new ArrayList<String>();
		nowShowing.add("The Conjuring");
		nowShowing.add("Despicable Me 2");
		nowShowing.add("Turbo");
		nowShowing.add("Grown Ups 2");
		nowShowing.add("Red 2");
		nowShowing.add("The Wolverine");

		List<String> comingSoon = new ArrayList<String>();
		comingSoon.add("2 Guns");
		comingSoon.add("The Smurfs 2");
		comingSoon.add("The Spectacular Now");
		comingSoon.add("The Canyons");
		comingSoon.add("Europa Report");

		listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
		listDataChild.put(listDataHeader.get(1), nowShowing);
		listDataChild.put(listDataHeader.get(2), comingSoon);
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View view,
			int groupPosition, long id) {
//		Log.d("Mylog", "group = " + listDataHeader.get(groupPosition));
		
		TextView textview = (TextView) view.findViewById(R.id.lblListHeader);
		
		Toast.makeText(this, "group = " + textview.getText(), Toast.LENGTH_LONG)
				.show();

		return false;
	}

	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		
		String groupKey = listDataHeader.get(groupPosition);
		String childName = listDataChild.get(groupKey).get(childPosition);
//		Log.d("Mylog", "item = " + listDataChild.get(groupKey).get(childPosition));
		
		Toast.makeText(this, "child = " + childName, Toast.LENGTH_LONG)
				.show();

		
		return false;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		
		int itemType = ExpandableListView.getPackedPositionType(id);
		int childPosition;
		int groupPosition;
		boolean retVal = false;

		if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
			groupPosition = ExpandableListView.getPackedPositionGroup(id);

			String gropuName = listDataHeader.get(groupPosition); 
			
			Toast.makeText(this, "Item Long Click group name = " + gropuName,
					Toast.LENGTH_LONG).show();
			
			// true if we consumed the click, false if not - Sonny Shih
			// 2014/09/26
			retVal = true;

		} else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
			childPosition = ExpandableListView.getPackedPositionChild(id);
			groupPosition = ExpandableListView.getPackedPositionGroup(id);

			String groupName = listDataHeader.get(groupPosition);
			String childName = listDataChild.get(groupName).get(childPosition);
			
			Toast.makeText(this, "Item Long Click child name = " + childName,
					Toast.LENGTH_LONG).show();
			
			// true if we consumed the click, false if not - Sonny Shih
			// 2014/09/26
			retVal = true;
		}

		return retVal;
	}

}
