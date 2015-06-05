package com.example.fragmentdemo.FragmentPagerDemo;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/** FragmentPagerAdapter 與  FragmentStatePagerAdapter 使用時機
 * - FragmentPagerAdapter
  * 是把所有的 fragment存在 memory裡
  * 適合少量、靜態的 fragment

 * - FragmentStatePagerAdapter
  * 只是暫存，如果不在畫面上時，就被destory
  * 適合多量、動態的 fragment
 * 
 * */

public class MyPagerAdapter extends FragmentPagerAdapter {

	private final List<SingleViewPage> viewPageList;

	public MyPagerAdapter(FragmentManager fragmentManager, List<SingleViewPage> viewPageList) {
		super(fragmentManager);
		this.viewPageList = viewPageList;
	}

	@Override
	public Fragment getItem(int position) { // Display every fragment - Sonny
											// Shih 05/17/2013
		return viewPageList.get(position).getFragment();
	}

	@Override
	public int getCount() {
		return viewPageList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) { // Show the title of every
														// page - Sonny Shih
														// 05/17/2013
		return viewPageList.get(position).getPageTitle();
	}
}