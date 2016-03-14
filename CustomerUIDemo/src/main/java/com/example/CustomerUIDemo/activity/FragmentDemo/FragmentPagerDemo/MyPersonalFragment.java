package com.example.CustomerUIDemo.activity.FragmentDemo.FragmentPagerDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.CustomerUIDemo.R;

public class MyPersonalFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return (LinearLayout) inflater.inflate(R.layout.my_personal_fragment,
				container, false);
	}
}