package com.kevinhinds.fremium;

import com.pollfish.main.PollFish;
import com.pollfish.constants.Position;
import com.kevinhinds.fremium.marketplace.MarketPlace;
import com.kevinhinds.fremium.updates.LatestUpdates;
import com.google.android.gms.ads.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.freemium);

		/** show the latest update notes if the application was just installed */
		LatestUpdates.showFirstInstalledNotes(this);

		/** Look up the AdView as a resource and load a request */
		if (Boolean.parseBoolean(getResources().getString(R.string.admob_enabled))) {
			AdView adView = (AdView) this.findViewById(R.id.adView);
			AdRequest adRequest = new AdRequest.Builder().build();
			adView.loadAd(adRequest);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (Boolean.parseBoolean(getResources().getString(R.string.pollfish_enabled))) {
			PollFish.init(this, getString(R.string.pollfish_api_key), Position.BOTTOM_RIGHT, 5);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (Boolean.parseBoolean(getResources().getString(R.string.pollfish_enabled))) {
			PollFish.init(this, getString(R.string.pollfish_api_key), Position.BOTTOM_LEFT, 5);
		}
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
			MarketPlace.viewAllPublisherApps(this);
			break;
		case R.id.menu_fullversion:
			MarketPlace.viewPremiumApp(this);
			break;
		case R.id.menu_suggested:
			MarketPlace.viewSuggestedApp(this);
			break;
		}
		return true;
	}
}