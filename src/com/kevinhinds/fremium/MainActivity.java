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
		String packageName = getApplicationContext().getPackageName();
		MarketPlace marketPlace = new MarketPlace(this, packageName);
		Intent intent = marketPlace.viewAllPublisherAppsIntent(this);
		if (intent != null) {
			startActivity(intent);
		}
		return false;
	}
}