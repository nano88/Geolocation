package com.exposure101.geo.converters;


import android.location.Location;

public final class MGRSConverter implements LocationConverter {

	private static final LatLon2MGRS LAT_LON_2_MGRS = new LatLon2MGRS();

	@Override
	public String getText(final Location location) {
		return LAT_LON_2_MGRS.convertLatLonToMGRS(location.getLatitude(),
				location.getLongitude());
	}

}
