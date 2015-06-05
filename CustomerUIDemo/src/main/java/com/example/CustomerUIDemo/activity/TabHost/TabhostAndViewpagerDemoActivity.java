package com.example.CustomerUIDemo.activity.TabHost;

import java.util.ArrayList;
import java.util.List;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.ui.adapter.PagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class TabhostAndViewpagerDemoActivity extends FragmentActivity implements
		OnTabChangeListener, OnPageChangeListener {

	private TabHost tabHost;
	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost_and_viewpager_demo_activity);

		initTabHost();
		initViewPager();

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	
	private void initTabHost() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();


		setupTab("Android", R.drawable.tabhost_and_viewpager_demo_tab_icon_tab1);

		setupTab("Apple", R.drawable.tabhost_and_viewpager_demo_tab_icon_tab2);

		tabHost.setOnTabChangedListener(this);

	}
	
	
	private void setupTab(String tabName, Integer iconId) {

		View tabView = LayoutInflater.from(this).inflate(
				R.layout.tabhost_and_viewpager_demo_tab_item, null);
		
		TextView textView = (TextView) tabView
				.findViewById(R.id.content_tab_TextView);
		textView.setText(tabName);
		
		ImageView imageView = (ImageView) tabView
				.findViewById(R.id.content_Tab_imageView);
		imageView.setImageResource(iconId);

		TabSpec setContent = tabHost.newTabSpec(tabName).setIndicator(tabView)
				.setContent(new DummyTabContent(this));
		tabHost.addTab(setContent);

	}

	
	private void initViewPager() {

		List<Fragment> fragmentList = new ArrayList<Fragment>();
		fragmentList.add(new AndroidFragment());
		fragmentList.add(new AppleFragment());

		pagerAdapter = new PagerAdapter(getSupportFragmentManager(),
				fragmentList);

		viewPager = (ViewPager) findViewById(R.id.tabhostAndViewpagerDemo_viewpager);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onTabChanged(String tag) {
		int position = tabHost.getCurrentTab();
		viewPager.setCurrentItem(position);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		tabHost.setCurrentTab(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}
