package com.exposure101.geo.converters;

import java.text.DecimalFormat;
import android.location.Location;

public class DecimalConverter implements LocationConverter {

	private static final DecimalFormat latFormatter = new DecimalFormat("00.000000");
	private static final DecimalFormat lonFormatter = new DecimalFormat("00.000000");
	
	@Override
	public String getText(final Location location) {
		final StringBuilder sb = new StringBuilder();
		final double lat = location.getLatitude();
		final double lon = location.getLongitude();
		if (lat < 0) {
			sb.append(latFormatter.format(lat * -1));
			sb.append("S");
		} else {
			sb.append(latFormatter.format(lat));
			sb.append("N");
		}
		sb.append(" ");
		if (lon < 0) {
			sb.append(lonFormatter.format(lon * -1));
			sb.append("W");
		} else {
			sb.append(lonFormatter.format(lon));
			sb.append("E");
		}
		return sb.toString();
	}
}
