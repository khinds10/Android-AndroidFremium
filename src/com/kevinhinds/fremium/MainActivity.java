package com.kevinhinds.fremium;

import com.kevinhinds.fremium.marketplace.MarketPlace;
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
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