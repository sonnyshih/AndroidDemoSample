package com.example.CustomerUIDemo.activity.DrawerDemo;

import android.support.v4.app.Fragment;

public class DrawerItem {
	private int icon;
	private String name;
	private Fragment fragment;

	public DrawerItem(int icon, String name, Fragment fragment) {
		this.icon = icon;
		this.name = name;
		this.fragment = fragment;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Fragment getFragment() {
		return fragment;
	}

	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}
	
}
