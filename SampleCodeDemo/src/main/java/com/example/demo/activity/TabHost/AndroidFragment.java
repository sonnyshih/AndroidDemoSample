package com.example.demo.activity.TabHost;

import com.example.demo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AndroidFragment extends Fragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);	// Set the option menu
	}
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.android_fragement, container, false);
		
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//	    switch (item.getItemId()) {
//	    case R.id.activity_menu_item:
//	        // Not implemented here
//	        return false;
//	    case R.id.fragment_menu_item:
//	        // Do Fragment menu item stuff here
//	        return true;
//	    default:
//	        break;
//	    }

	    return false;
	}
	
}
