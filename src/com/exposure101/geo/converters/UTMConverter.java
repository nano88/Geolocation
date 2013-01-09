package com.exposure101.geo.converters;

import android.location.Location;


public class UTMConverter implements LocationConverter {

	private static final LatLon2UTM LAT_LON_2_UTM = new LatLon2UTM();

	@Override
	public String getText(final Location location) {
		return LAT_LON_2_UTM.convertLatLonToUTM(location.getLatitude(),
				location.getLongitude());
	}
}
