package com.walter.csdn.adapter;

import com.walter.csdn.fragment.MainFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

	public static final String[] TITLES = new String[] { "ҵ��", "�ƶ�", "�з�",
			"����Ա��־", "�Ƽ���" };

	public TabAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		MainFragment fm = new MainFragment(arg0);
		return fm;
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position % TITLES.length];
	}

}
