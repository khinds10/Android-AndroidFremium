package com.kevinhinds.fremium;

import com.pollfish.constants.Position;
import com.pollfish.main.PollFish;
import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishSurveyCompletedListener;
import com.pollfish.interfaces.PollfishSurveyReceivedListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.kevinhinds.fremium.marketplace.MarketPlace;
import com.kevinhinds.fremium.updates.LatestUpdates;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements PollfishSurveyCompletedListener, PollfishOpenedListener, PollfishClosedListener, PollfishSurveyReceivedListener,
		PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// show Pollfish if available anywhere within the activity LifeCycle

		Button showBtn = (Button) findViewById(R.id.show_btn);
		showBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PollFish.show();
			}
		});

		// hide Pollfish if available anywhere within the activity LifeCycle

		Button hideBtn = (Button) findViewById(R.id.hide_btn);
		hideBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PollFish.hide();
			}
		});

		// start activity to see orientation handle

		Button activityBtn = (Button) findViewById(R.id.activity_btn);
		activityBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, OrientationActivity.class);

				startActivity(intent);

			}
		});

		// start activity to see implementation with incentivization

		Button incentivize_act_Btn = (Button) findViewById(R.id.incent_act_btn);
		incentivize_act_Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, IncentivizeActivity.class);

				startActivity(intent);

			}
		});
		/** show the latest update notes if the application was just installed */
		LatestUpdates.showFirstInstalledNotes(this);
	}

	@Override
	public void onResume() {
		super.onResume();

		/**
		 * Start the library. Set the api key. Choose position to place the indicator
		 * (Position.TOP_LEFT, Position.BOTTOM_LEFT,MIDDLE_LEFT, Position.TOP_RIGHT,
		 * Position.BOTTOM_RIGHT, MIDDLE_RIGHT). Set padding for the indicator position from
		 * bottom/middle or top according to position, 0 if default.
		 * 
		 * @param act
		 *            - Your current activity
		 * @param apiKey
		 *            - The api Key for your app (register at www.pollfish.com)
		 * @param p
		 *            - The position to place the pollfish indicator
		 * @param indPadding
		 *            - padding between the indicator and the selected position in the screen - 0
		 *            for default
		 * 
		 *            e.g PollFish.init(this, "2ad6e857-2995-4668-ab95-39e068faa558",
		 *            Position.BOTTOM_RIGHT,5);
		 */

		PollFish.init(this, getString(R.string.pollfish_api_key), Position.BOTTOM_LEFT, 5);
	}

	@Override
	public void onPollfishSurveyReceived() {
		Log.d("Pollfish", "onPollfishSurveyReceived()");
	}

	@Override
	public void onPollfishClosed() {
		Log.d("Pollfish", "onPollfishClosed()");
	}

	@Override
	public void onPollfishOpened() {
		Log.d("Pollfish", "onPollfishOpened()");
	}

	@Override
	public void onPollfishSurveyCompleted() {
		Log.d("Pollfish", "onPollfishSurveyCompleted()");
	}

	@Override
	public void onPollfishSurveyNotAvailable() {
		Log.d("Pollfish", "onPollfishSurveyNotAvailable()");

	}

	@Override
	public void onUserNotEligible() {
		Log.d("Pollfish", "onUserNotEligible()");

	}

	/** make parts of the menu invisible based on settings */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		menu.findItem(R.id.menu_fullversion).setVisible(!Boolean.parseBoolean(getResources().getString(R.string.is_full_version)));
		menu.findItem(R.id.menu_suggested).setVisible(Boolean.parseBoolean(getResources().getString(R.string.has_suggested_app)));
		return true;
	}

	/** create the main menu based on if the app is the full version or not */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** handle user selecting a menu item */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_bitstreet:
			viewAllPublisherApps();
			break;
		case R.id.menu_fullversion:
			viewPremiumApp();
			break;
		case R.id.menu_suggested:
			viewSuggestedApp();
			break;
		}
		return true;
	}

	/**
	 * get the suggested app intent
	 */
	public void viewSuggestedApp() {
		MarketPlace marketPlace = new MarketPlace(this);
		Intent intent = marketPlace.getViewSuggestedAppIntent(this);
		if (intent != null) {
			startActivity(intent);
		}
	}

	/**
	 * view all apps on the device marketplace for current publisher
	 */
	public void viewAllPublisherApps() {
		MarketPlace marketPlace = new MarketPlace(this);
		Intent intent = marketPlace.getViewAllPublisherAppsIntent(this);
		if (intent != null) {
			startActivity(intent);
		}
	}

	/**
	 * view the premium version of this app
	 */
	public void viewPremiumApp() {
		MarketPlace marketPlace = new MarketPlace(this);
		Intent intent = marketPlace.getViewPremiumAppIntent(this);
		if (intent != null) {
			startActivity(intent);
		}
	}
}
