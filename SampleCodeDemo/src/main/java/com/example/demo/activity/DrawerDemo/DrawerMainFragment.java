package com.example.demo.activity.DrawerDemo;

import com.example.demo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawerMainFragment extends Fragment{

	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater
				.inflate(R.layout.drawer_main_fragment, container, false);
		
		return rootView;
	}

	
}
