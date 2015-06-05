package com.example.CustomerUIDemo.activity.MenuAndActionBarDemo;

import java.util.ArrayList;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.ui.adapter.ActionModeListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;

public class ListViewActionModeDemoActivity extends Activity implements
		MultiChoiceModeListener {

	private String[] items = { "Hello world", "Happy time", "Good Day",
			"Nice Ball", "fire fox", "Chrome", "IE", "Linux", "Unix", "FreeBsd", "Mac",  "Windows"};
	
	private ArrayList<ActionModeItemEntity> itemEntities;
	private ListView listView;
	private ActionModeListAdapter actionModeListAdapter;
	private int itemCounter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_action_mode_demo_activity);

		itemEntities = getArrayList();
		actionModeListAdapter = new ActionModeListAdapter(this, itemEntities);
		listView = (ListView) findViewById(R.id.listviewActionModeDemo_listView);
		listView.setAdapter(actionModeListAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(this);
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	private void onCartClick(){
		Toast.makeText(this, "Click cart button ", Toast.LENGTH_SHORT).show();
	}

	private void onDefaultClick(){
		Toast.makeText(this, "Click default ", Toast.LENGTH_SHORT).show();
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

	@Override
	public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        // Inflate the menu for the CAB
		
		itemCounter = 0;
		
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        actionMode.setTitle("ListView Title");
        return true;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        // Here you can perform updates to the CAB due to
        // an invalidate() request
        return false;
	}

	@Override
	public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
        // Respond to clicks on the actions in the CAB
        switch (item.getItemId()) {
		case R.id.contextMenu_cart:
			onCartClick();
			itemCounter = 0;
			actionModeListAdapter.cleanAllSelected();
			actionMode.finish(); // Action picked, so close the CAB
			return true;
			
		default:
			return false;
		}
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
        // Here you can make any necessary updates to the activity when
        // the CAB is removed. By default, selected items are deselected/unchecked.
		Log.d("Mylog", "close");
		onDefaultClick();
		actionModeListAdapter.cleanAllSelected();
	}

	@Override
	public void onItemCheckedStateChanged(ActionMode actionMode,
			int position, long id, boolean isChecked) {
        // Here you can do something when items are selected/de-selected,
        // such as update the title in the CAB
		Log.d("Mylog", "click:" + items[position]);
		
		if (isChecked) {
			itemCounter++;
			actionModeListAdapter.selected(position);
		} else {
			itemCounter--;
			actionModeListAdapter.unselected(position);
		}
		
		actionMode.setTitle(itemCounter + " selected");

	}
	
}
