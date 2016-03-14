package com.example.CustomerUIDemo.activity.FragmentDemo.DynamicDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.CustomerUIDemo.R;


public class NewFragment extends Fragment{
	
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.new_fragment, container, false);
		
		return view;
	}

}
