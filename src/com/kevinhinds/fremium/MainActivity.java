package com.kevinhinds.fremium;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	/**
	 * Assign the market where this application will live
	 */
	public MarketPlace market_type = MarketPlace.GOOGLE;

	/**
	 * Marketplace enumerated type
	 */
	public enum MarketPlace {
		GOOGLE, AMAZON, STANDARD;
	}

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

			switch (market_type) {
			case GOOGLE:
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://search?q=pub:\"Kevin Hinds\""));
				startActivity(intent);
				break;
			case AMAZON:

				// http://www.amazon.com/gp/mas/dl/android?p=[packagename]&showAll=1

				// /**
				// * Returns intent that opens app in Google Play or Amazon Appstore
				// * @param context
				// * @param packageName
				// * @return null if no market available, otherwise intent
				// */
				// public static Intent showApp(Activity activity, String packageName)
				// {
				// Intent i = new Intent(Intent.ACTION_VIEW);
				// String url = "market://details?id=" + packageName;
				// i.setData(Uri.parse(url));
				//
				// if (isIntentAvailable(activity, i))
				// {
				// return i;
				// }
				//
				// i.setData(Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + packageName));
				// if (isIntentAvailable(activity, i))
				// {
				// return i;
				// }
				// return null;
				//
				// }
				//
				// public static boolean isIntentAvailable(Context context, Intent intent) {
				// final PackageManager packageManager = context.getPackageManager();
				// List<ResolveInfo> list =
				// packageManager.queryIntentActivities(intent,
				// PackageManager.MATCH_DEFAULT_ONLY);
				// return list.size() > 0;
				// }

				break;
			default:

				break;
			}
			break;
		}
		return true;
	}
}