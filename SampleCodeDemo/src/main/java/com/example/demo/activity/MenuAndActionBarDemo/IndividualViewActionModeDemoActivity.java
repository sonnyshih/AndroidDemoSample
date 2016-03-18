package com.example.demo.activity.MenuAndActionBarDemo;

import com.example.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;

public class IndividualViewActionModeDemoActivity extends Activity implements OnLongClickListener{
	private ActionMode mActionMode;
	private Button actionModeForIndividualViewsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_view_actiom_mode_demo_activity);

		actionModeForIndividualViewsButton = (Button) findViewById(R.id.individualViewActiomModeDemo_actionModeForIndividualViewsButton);
		actionModeForIndividualViewsButton.setOnLongClickListener(this);
		
	}
	
	private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

	    // Called when the action mode is created; startActionMode() was called
	    @Override
	    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
	        // Inflate a menu resource providing context menu items
	        MenuInflater inflater = actionMode.getMenuInflater();
	        inflater.inflate(R.menu.context_menu, menu);
	        actionMode.setTitle("Hello action Mode");
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
//	                shareCurrentItem();
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
	    }
	};

	@Override
	public boolean onLongClick(View view) {
		if (mActionMode != null) {
			return false;
		}

		// Start the CAB using the ActionMode.Callback defined above
		mActionMode = IndividualViewActionModeDemoActivity.this
				.startActionMode(actionModeCallback);
		view.setSelected(true);
		return true;
	}

}
