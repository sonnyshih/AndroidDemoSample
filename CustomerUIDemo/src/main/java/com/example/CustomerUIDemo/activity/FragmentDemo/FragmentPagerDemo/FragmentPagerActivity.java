package com.example.CustomerUIDemo.activity.FragmentDemo.FragmentPagerDemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.CustomerUIDemo.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerActivity extends FragmentActivity{
	private PagerAdapter mPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_pager_activity);
		
		
		List<SingleViewPage> viewPageList = new ArrayList<SingleViewPage>();
		
		viewPageList.add(new SingleViewPage("Browse", new BrowseFragment()));
		viewPageList.add(new SingleViewPage("Home", new HomeFragment()));
		viewPageList.add(new SingleViewPage("My Personal", new MyPersonalFragment()));
		
		mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), viewPageList);
		ViewPager viewPager = (ViewPager) super.findViewById(R.id.fragmentPager_viewpager);
		viewPager.setAdapter(this.mPagerAdapter);
		
	}

	
}
