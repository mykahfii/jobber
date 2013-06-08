package angelhack.jobber;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * This is the Splash activity which is loaded when the application is invoked
 */
public class JBSplash extends Activity implements LocationListener {
	// Set the display time, in milliseconds (or extract it out as a
	// configurable parameter)
	private final int SPLASH_DISPLAY_LENGTH = 3000;

	private LocationManager locManager;
	private String provider;
	private double latitude, longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jbsplash);

		// Get the location manager
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		provider = locManager.getBestProvider(criteria, false);
		Location myLoc = locManager.getLastKnownLocation(provider);

		if (myLoc != null) {
			Log.d(Utils.TAG, "provider : " + provider);
			onLocationChanged(myLoc);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		// Obtain the sharedPreference, default to true if not available
		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

		if (isSplashEnabled) {
			new Handler().postDelayed(new Runnable() {
				public void run() {
					if (latitude != 0.0 && longitude != 0.0) {
						JBSplash.this.finish();
						Intent mainIntent = new Intent(JBSplash.this, JBMain.class);
						mainIntent.putExtra("latitude", latitude);
						mainIntent.putExtra("longitude", longitude);
						JBSplash.this.startActivity(mainIntent);
					}

				}
			}, SPLASH_DISPLAY_LENGTH);
		} else {
			// if the splash is not enabled, then finish the activity
			// immediately and go to main.
			finish();
			Intent mainIntent = new Intent(JBSplash.this, JBMain.class);
			JBSplash.this.startActivity(mainIntent);
		}
	}

	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}