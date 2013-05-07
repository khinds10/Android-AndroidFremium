package com.kevinhinds.fremium;

import com.kevinhinds.fremium.marketplace.MarketPlace;
import com.kevinhinds.fremium.updates.LatestUpdates;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/** show the latest update notes if the application was just installed */
		LatestUpdates.showFirstInstalledNotes(this);
	}

	/** create the main menu based on if the app is the full version or not */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		String isFullVersion = getResources().getString(R.string.is_full_version);
		if (isFullVersion.toLowerCase().equals("true")) {
			getMenuInflater().inflate(R.menu.main_full, menu);
		} else {
			getMenuInflater().inflate(R.menu.main, menu);
		}
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
		}
		return true;
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