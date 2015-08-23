package com.angelhack.gooddriver.activity;

import com.angelhack.gooddriver.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends Activity {

	protected boolean mbActive;
	protected static final int TIMER_RUNTIME = 3000;
	protected ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash_screen);
		Thread timer = new Thread() {

			public void run() {

				mbActive = true;
				try {
					int waited = 0;
					while (mbActive && (waited < TIMER_RUNTIME)) {
						sleep(200);
						if (mbActive) {
							waited += 200;

						}
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent mainactivity = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(mainactivity);
					finish();
				}
			}
		};
		timer.start();
	}
}
