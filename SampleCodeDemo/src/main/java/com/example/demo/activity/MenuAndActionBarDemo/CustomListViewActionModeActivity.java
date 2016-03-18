package com.example.demo.activity.MenuAndActionBarDemo;

import java.util.ArrayList;

import com.example.demo.R;
import com.example.demo.ui.adapter.ActionModeListAdapter;
import com.example.demo.util.StringUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class CustomListViewActionModeActivity extends Activity implements
		OnItemLongClickListener, OnItemClickListener, OnItemSelectedListener {

	private String[] items = { "Hello world", "Happy time", "Good Day",
			"Nice Ball", "fire fox", "Chrome", "IE", "Linux", "Unix", "FreeBsd", "Mac",  "Windows"};
	
	private ActionMode mActionMode;
	
	private Spinner spinner;
	private ArrayList<ActionModeItemEntity> itemEntities;
	private ListView listView;
	private ActionModeListAdapter actionModeListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_action_mode_demo_activity);

		itemEntities = getArrayList();
		actionModeListAdapter = new ActionModeListAdapter(this, itemEntities);
		listView = (ListView) findViewById(R.id.listviewActionModeDemo_listView);
		listView.setAdapter(actionModeListAdapter);
		listView.setOnItemLongClickListener(this);
		listView.setOnItemClickListener(this);
		registerForContextMenu(listView);	// long-click to open the Context Menu
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	private ArrayList<ActionModeItemEntity> getArrayList(){
		
		ArrayList<ActionModeItemEntity> arrayList = new ArrayList<ActionModeItemEntity>();
		
		for (String item : items) {
			ActionModeItemEntity actionModeItemEntity = new ActionModeItemEntity();
			actionModeItemEntity.setName(item);
			arrayList.add(actionModeItemEntity);
		}
	
		return arrayList;
	}

	private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
	
		    // Called when the action mode is created; startActionMode() was called
		    @Override
		    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
		        // Inflate a menu resource providing context menu items
		        MenuInflater inflater = actionMode.getMenuInflater();
		        inflater.inflate(R.menu.context_menu, menu);
		        actionMode.setTitle("Hello action Mode");
		        
		        
				View view = (View) getLayoutInflater().inflate(
						R.layout.listview_action_mode_demo_actionbar, null);
				actionMode.setCustomView(view);
				
				ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter
						.createFromResource(CustomListViewActionModeActivity.this,
								R.array.itemSelectArray,
								android.R.layout.simple_spinner_item);
				arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner = (Spinner) view.findViewById(R.id.listviewActionModeDemo_spinner);
				spinner.setAdapter(arrayAdapter);
				spinner.setOnItemSelectedListener(CustomListViewActionModeActivity.this);
	
		        
		        return true;
		    }
	
		    // Called each time the action mode is shown. Always called after onCreateActionMode, but
		    // may be called multiple times if the mode is invalidated.
		    @Override
		    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		        return false; // Return false if nothing is done
		    }
	
		    // Called when the user selects a contextual menu item
		    @Override
		    public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
		        switch (item.getItemId()) {
		            case R.id.contextMenu_busyIcon:
		                actionMode.finish(); // Action picked, so close the CAB
		                return true;
		            default:
		                return false;
		        }
		    }
	
		    // Called when the user exits the action mode
		    @Override
		    public void onDestroyActionMode(ActionMode mode) {
		        mActionMode = null;
		        actionModeListAdapter.cleanAllSelected();
		    }
		    
		    
		};

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		String option = parent.getItemAtPosition(position).toString();

		Log.d("Mylog", "option = " + option);

		boolean isChecked = false;
		if (option.equals("select all")) {
			actionModeListAdapter.selectAll();
			isChecked = true;
		}

		if (option.equals("unselect all")) {
			actionModeListAdapter.cleanAllSelected();
			isChecked = false;
		}

		if (!StringUtil.isEmpty(option)) {
			for (int i = 0; i < itemEntities.size(); i++) {
				listView.setItemChecked(i, isChecked);
			}
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		Log.d("Mylog", "onNothingSelected");
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		if (mActionMode != null) {
			return false;
		}

		// Start the CAB using the ActionMode.Callback defined above
		mActionMode = this
				.startActionMode(actionModeCallback);
		view.setSelected(true);
		return true;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		itemEntities = actionModeListAdapter.getItemEntities();
		
		if (itemEntities.get(position).isCheck()) {
			actionModeListAdapter.unselected(position);
		} else {
			actionModeListAdapter.selected(position);
		}
		
	}

}
