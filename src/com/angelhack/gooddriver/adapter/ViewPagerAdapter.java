package com.angelhack.gooddriver.adapter;

import com.angelhack.gooddriver.fragment.TrackLocation;
import com.angelhack.gooddriver.fragment.TrackMeFragment;
import com.angelhack.gooddriver.fragment.TrackMyTrip;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 3;
	// Tab Titles
	private String tabtitles[] = new String[] { "TrackMe", "LocateMe",
			"TrackMyTrip" };
	Context context;

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {

		// Open FragmentTab1.java
		case 0:
			TrackMeFragment fragmenttab1 = new TrackMeFragment();
			return fragmenttab1;

			// Open FragmentTab2.java
		case 1:
			TrackLocation fragmenttab2 = new TrackLocation();
			return fragmenttab2;
		case 2:
			TrackMyTrip fragmenttab3 = new TrackMyTrip();
			return fragmenttab3;

		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabtitles[position];
	}
}