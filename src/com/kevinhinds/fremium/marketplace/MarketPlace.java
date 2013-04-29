/**
 * Generic market place specific intent manager for Bitstreet Apps
 */
package com.kevinhinds.fremium.marketplace;

import java.util.List;

import com.kevinhinds.fremium.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

/**
 * Manage intents to use within a specific device marketplace environment
 * 
 * @author khinds
 */
public class MarketPlace {

	/**
	 * Marketplace enumerated type
	 */
	public enum MarketLocale {
		GOOGLE, AMAZON, NOOK;
	}

	/**
	 * Assign the market where this application will live
	 */
	public MarketLocale marketLocale;

	/**
	 * market place standard url for a browser to open the device market place
	 */
	public String packageName;

	/**
	 * intent to gather to return for the specific device
	 */
	protected Intent intent;

	public MarketPlace(Context context, String thisPackageName) {
		String deviceMarketPlaceName = context.getResources().getString(R.string.device_market_place);
		packageName = thisPackageName;
		if (deviceMarketPlaceName.toUpperCase().equals("GOOGLE")) {
			marketLocale = MarketLocale.GOOGLE;
		} else if (deviceMarketPlaceName.toUpperCase().equals("AMAZON")) {
			marketLocale = MarketLocale.AMAZON;
		} else if (deviceMarketPlaceName.toUpperCase().equals("NOOK")) {
			marketLocale = MarketLocale.NOOK;
		}
	}

	/**
	 * user clicks the view all apps option, gather the device specific intent
	 * 
	 * @param itemId
	 * @param context
	 * @return
	 */
	public Intent viewAllApps(int itemId, Context context) {

		String appPublisherName = context.getResources().getString(R.string.app_publisher_name);
		String appPublisherNameEscaped = appPublisherName.replace(" ", "+");
		String deviceMarketPlaceWeburl = null;

		/** switch on marketLocale to get the right intent to open on the device */
		switch (marketLocale) {

		case GOOGLE:

			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("market://search?q=pub:\"" + appPublisherName + "\""));
			deviceMarketPlaceWeburl = "http://play.google.com/store/search?q=pub:" + appPublisherNameEscaped + "&c=apps";

		case AMAZON:
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("amzn://apps/android?p=" + packageName + "&showAll=1"));
			deviceMarketPlaceWeburl = "http://www.amazon.com/gp/mas/dl/android?p=" + packageName + "&showAll=1";

		case NOOK:
			intent = new Intent();
			intent.setAction("com.bn.sdk.shop.details");
			intent.putExtra("product_details_ean", "2940043350251");

		default:
			break;
		}

		/** if we don't have the marketplace for the device available as an intent, attempt to produce a standard webpage URL to open */
		if (MarketPlace.isIntentAvailable(context, intent)) {
			return intent;
		} else {
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(deviceMarketPlaceWeburl));
			if (MarketPlace.isIntentAvailable(context, intent)) {
				return null;
			}
			return intent;
		}
	}

	/**
	 * determine if the intent is available on the device to continue
	 * 
	 * @param context
	 * @param intent
	 * @return
	 */
	public static boolean isIntentAvailable(Context context, Intent intent) {
		final PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}
}