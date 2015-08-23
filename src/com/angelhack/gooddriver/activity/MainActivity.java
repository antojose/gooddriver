package com.angelhack.gooddriver.activity;

import com.angelhack.gooddriver.R;
import com.angelhack.gooddriver.adapter.ViewPagerAdapter;
import com.example.slidingtab.PagerSlidingTabStrip;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.WindowManager;

public class MainActivity extends FragmentActivity {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.pagertab_stip_main_activity);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.sliding_tabs);
		pager = (ViewPager) findViewById(R.id.view_pager);
		tabs.setShouldExpand(true);
		tabs.setIndicatorColorResource(R.color.gd_orenge);
		tabs.setUnderlineColor(Color.TRANSPARENT);
		tabs.setAllCaps(false);
		tabs.setTextColor(Color.BLACK);
		pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

	}

}
