package com.example.CustomerUIDemo.activity.DrawerDemo;

import com.example.CustomerUIDemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawerContactFragment extends Fragment{

	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater
				.inflate(R.layout.drawer_contact_fragment, container, false);
		

		return rootView;
	}

	
}
