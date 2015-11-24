package com.walter.csdn;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;
import com.walter.csdn.adapter.TabAdapter;


public class MainActivity extends FragmentActivity {

	private TabPageIndicator mTabPageIndicator;
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabPageIndicator=(TabPageIndicator) findViewById(R.id.id_indicator);
        mViewPager=(ViewPager) findViewById(R.id.id_pager);
        mAdapter=new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabPageIndicator.setViewPager(mViewPager,0);
    }
}
