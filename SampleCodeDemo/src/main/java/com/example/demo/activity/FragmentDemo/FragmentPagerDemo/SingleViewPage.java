package com.example.demo.activity.FragmentDemo.FragmentPagerDemo;

import android.support.v4.app.Fragment;

public class SingleViewPage {
	private String pageTitle;
	private Fragment fragment;

	public SingleViewPage(String pageTitle, Fragment fragment) {
		this.pageTitle = pageTitle;
		this.fragment = fragment;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public Fragment getFragment() {
		return fragment;
	}

}
