package com.exposure101.geo.location;

import static com.exposure101.geo.location.LocationKeys.DDMMSS;
import static com.exposure101.geo.location.LocationKeys.DECIMAL;
import static com.exposure101.geo.location.LocationKeys.MGRS;
import static com.exposure101.geo.location.LocationKeys.UTM;

import java.util.IdentityHashMap;
import java.util.Map;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import com.exposure101.geo.common.ActivityStateListener;
import com.exposure101.geo.converters.DDMMSSConverter;
import com.exposure101.geo.converters.DecimalConverter;
import com.exposure101.geo.converters.LocationConverter;
import com.exposure101.geo.converters.MGRSConverter;
import com.exposure101.geo.converters.UTMConverter;
import com.exposure101.geo.core.Controller;
import com.exposure101.geo.core.Model;

public final class LocationSensor implements ActivityStateListener,
		LocationListener {

	private final LocationManager locationManager;
	private final LocationListener locationListener;
	private final Map<LocationKeys, LocationConverter> locationConverters;

	public LocationSensor(final LocationManager locationManager,
			final Controller controller, final Model model) {
		final int capacity = LocationKeys.values().length;
		this.locationManager = locationManager;
		locationConverters = new IdentityHashMap<LocationKeys, LocationConverter>(
				capacity);
		locationConverters.put(DDMMSS, new DDMMSSConverter());
		locationConverters.put(DECIMAL, new DecimalConverter());
		locationConverters.put(UTM, new UTMConverter());
		locationConverters.put(MGRS, new MGRSConverter());
		locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onProviderDisabled(String provider) {
			}

			@Override
			public void onLocationChanged(Location location) {
				if (location != null) {
					for (final LocationKeys lt : LocationKeys.values()) {
						final String locationValue = locationConverters.get(lt)
								.getText(location);
						controller.handleLocationChanged(lt, locationValue);
					}
				}
			}
		};
	}


	@Override
	public void onCreate() {
		//turn on location findingness every 1 second / 3 meters
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 3f, locationListener);
	}

	@Override
	public void onDestroy() {
		//kill tracking
		locationManager.removeUpdates(locationListener);
	}

	@Override
	public void onPause() {
		//kill tracking
		locationManager.removeUpdates(locationListener);
	}

	@Override
	public void onResume() {
		//turn back on location findingness every 1 second / 3 meters
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 3f, locationListener);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}
