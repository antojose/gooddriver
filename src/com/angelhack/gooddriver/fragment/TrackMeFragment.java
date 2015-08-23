package com.angelhack.gooddriver.fragment;

import com.angelhack.gooddriver.R;
import com.angelhack.gooddriver.widget.Speedometer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class TrackMeFragment extends Fragment implements SensorEventListener {
	private float mLastX, mLastY, mLastZ;
	private float mLastestX, mLastestY, mLastestZ;
	private boolean mInitialized;
	private boolean malertShowing = false;
	private boolean mStarted = false;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private final float NOISE = (float) 2.0;
	private Speedometer speedometer;
	private MediaPlayer m;
	private RelativeLayout container;
	private Button startButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
		View view = inflater.inflate(R.layout.activity_main, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		container = (RelativeLayout) view.findViewById(R.id.container);
		m = new MediaPlayer();
		mInitialized = false;
		mSensorManager = (SensorManager) getActivity().getSystemService(
				Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		speedometer = (Speedometer) view.findViewById(R.id.Speedometer);

		startButton = (Button) view.findViewById(R.id.start_button);

		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				if (mStarted) {
					startButton.setText("Start");
					mStarted = false;
					if (m.isPlaying()) {
						m.stop();
						m.release();
						m = new MediaPlayer();
					}
				} else {
					startButton.setText("Stop");
					mStarted = true;
				}

			}

		});

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

		if (mStarted) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			if (!mInitialized) {
				mLastX = x;
				mLastY = y;
				mLastZ = z;
				mInitialized = true;
			} else {
				float deltaX = Math.abs(mLastX - x);
				float deltaY = Math.abs(mLastY - y);
				float deltaZ = Math.abs(mLastZ - z);
				if (deltaX < NOISE)
					deltaX = (float) 0.0;
				if (deltaY < NOISE)
					deltaY = (float) 0.0;
				if (deltaZ < NOISE)
					deltaZ = (float) 0.0;
				mLastestX = x;
				mLastestY = y;
				mLastestZ = z;
				if (deltaX > deltaY) {
					speedometer.onSpeedChanged(speedometer.getCurrentSpeed()
							+ deltaX);
					if ((speedometer.getCurrentSpeed() + deltaX) > 20) {
						if (!malertShowing) {
							alert();
						}
					}
				} else if (deltaY > deltaX) {
					speedometer.onSpeedChanged(speedometer.getCurrentSpeed()
							- deltaY);
					if ((speedometer.getCurrentSpeed() - deltaY) > 20) {
						if (!malertShowing) {
							alert();
						}
					}
				}
			}
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	public static double getDistance(float x1, float y1, float z1, float x2,
			float y2, float z2) {
		float dx = x1 - x2;
		float dy = y1 - y2;
		float dz = z1 - z2;

		// We should avoid Math.pow or Math.hypot due to perfomance reasons
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	private synchronized void alert() {
		playBeep();
		malertShowing = true;
		int i;
		System.out.println("over...");
		for (i = 0; i < 1000; i++) {
			if (i % 100 == 0) {
				System.out.println("over...100" + i);
				startButton.setBackgroundColor(getResources().getColor(
						R.color.gd_orenge));
			} else {
				System.out.println("over..." + i);
				startButton.setBackgroundColor(getResources().getColor(
						R.color.gd_red));
			}
		}
		if (i == 1000) {
			malertShowing = false;
			playBeep();
		}
		startButton.setBackgroundColor(getResources().getColor(
				R.color.gd_orenge));
	}

	public void playBeep() {
		try {
			if (m.isPlaying()) {
				m.stop();
				m.release();
				m = new MediaPlayer();
			}
			if (mStarted) {
				AssetFileDescriptor descriptor = getActivity().getAssets()
						.openFd("alert.mp3");
				m.setDataSource(descriptor.getFileDescriptor(),
						descriptor.getStartOffset(), descriptor.getLength());
				descriptor.close();

				m.prepare();
				m.setVolume(1f, 1f);
				m.setLooping(true);
				m.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}