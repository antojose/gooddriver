package com.angelhack.gooddriver.fragment;

import com.angelhack.gooddriver.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TrackLocation extends Fragment {

	private GoogleMap googleMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
		View view = inflater.inflate(R.layout.track_location_fragment,
				container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

	}

	// 8.558110, 76.880745
	private void initilizeMap() {
		FragmentManager fm = getChildFragmentManager();
		if (googleMap == null) {
			googleMap = ((SupportMapFragment) fm.findFragmentById(R.id.map))
					.getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getActivity(), "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			} else {
				placeMarker(8.558110, 76.880745);
				googleMap.getUiSettings().setMyLocationButtonEnabled(true);
				googleMap.getUiSettings().setZoomGesturesEnabled(false);
				googleMap.getUiSettings().setZoomControlsEnabled(true);
				googleMap.getUiSettings().setCompassEnabled(true);
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		initilizeMap();
	}

	private void placeMarker(double latitude, double longitude) {
		double latitude1 = latitude;
		double longitude1 = longitude;

		// create marker
		MarkerOptions marker = new MarkerOptions().position(
				new LatLng(latitude1, longitude1)).title("Hackathon ");

		// adding marker
		googleMap.addMarker(marker);
	}
}