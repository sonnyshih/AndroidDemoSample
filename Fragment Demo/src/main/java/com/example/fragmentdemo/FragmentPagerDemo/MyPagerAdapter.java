package com.example.fragmentdemo.FragmentPagerDemo;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/** FragmentPagerAdapter �P  FragmentStatePagerAdapter �ϥήɾ�
 * - FragmentPagerAdapter
  * �O��Ҧ��� fragment�s�b memory��
  * �A�X�ֶq�B�R�A�� fragment

 * - FragmentStatePagerAdapter
  * �u�O�Ȧs�A�p�G���b�e���W�ɡA�N�Qdestory
  * �A�X�h�q�B�ʺA�� fragment
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