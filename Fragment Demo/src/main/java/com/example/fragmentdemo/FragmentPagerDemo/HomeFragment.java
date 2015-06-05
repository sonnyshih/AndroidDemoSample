package com.example.fragmentdemo.FragmentPagerDemo;

import com.example.fragmentdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return (LinearLayout) inflater.inflate(R.layout.home_fragment,
				container, false);
	}
}