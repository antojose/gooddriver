package com.angelhack.gooddriver.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.angelhack.gooddriver.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TrackMyTrip extends Fragment {
	private XYPlot plot;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
		View view = inflater.inflate(R.layout.track_my_trip, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		plot = (XYPlot) view.findViewById(R.id.xyPlot);
		plot.setBackgroundColor(Color.RED);
		List s1 = getSeries(20, 10);
		XYSeries series1 = new SimpleXYSeries(s1,
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Speed");
		LineAndPointFormatter s1Format = new LineAndPointFormatter();
		s1Format.setPointLabelFormatter(new PointLabelFormatter());
		s1Format.configure(getActivity(), R.xml.lpf1);
		plot.addSeries(series1, s1Format);

		plot.setTicksPerRangeLabel(1);
		plot.getGraphWidget().setDomainLabelOrientation(-45);

	}

	private List getSeries(int count, int max) {
		List series = new ArrayList();
		Random rand = new Random();
		for (int i = 1; i <= count; i++) {
			int value = rand.nextInt(max);
			series.add(rand.nextInt(max));
		}
		return series;
	}

}