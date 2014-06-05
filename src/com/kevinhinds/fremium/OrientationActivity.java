package com.kevinhinds.fremium;

import com.pollfish.constants.Position;
import com.pollfish.main.PollFish;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OrientationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orientation);

		// back to main activity

		Button activityBackBtn = (Button) findViewById(R.id.activity_back_btn);
		activityBackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();

		 PollFish.init(this, "YOUR_API_KEY", Position.BOTTOM_RIGHT, 5);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		 PollFish.init(this, "YOUR_API_KEY", Position.BOTTOM_RIGHT, 5);
	}
}
