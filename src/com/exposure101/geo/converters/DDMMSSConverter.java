package com.exposure101.geo.converters;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.location.Location;

public final class DDMMSSConverter implements LocationConverter {

	private static final NumberFormat minuteFormat = new DecimalFormat("00");
	private static final NumberFormat secondFormat = new DecimalFormat("00");
	
	private void insertText(final StringBuilder sb, final int minute,
			final int second) {
		sb.append(minuteFormat.format(minute));
		sb.append("'");
		sb.append(secondFormat.format(second));
		sb.append("\"");
	}

	@Override
	public String getText(final Location location) {
		final StringBuilder sb = new StringBuilder();

		double lat = location.getLatitude();
		final String latDirection;
		if (lat < 0) {
			lat *= -1;
			latDirection = "S";
		} else {
			latDirection = "N";
		}
		final int latDegree = Double.valueOf(Math.floor(lat)).intValue();
		final int latMinute = Double
				.valueOf(Math.floor((lat - latDegree) * 60)).intValue();
		final int latSecond = Double.valueOf(
				(((lat - latDegree) * 60) - latMinute) * 60).intValue();

		double lon = location.getLongitude();
		final String lonDirection;
		if (lon < 0) {
			lon *= -1;
			lonDirection = "W";
		} else {
			lonDirection = "E";
		}
		final int lonDegree = Double.valueOf(Math.floor(lon)).intValue();
		final int lonMinute = Double
				.valueOf(Math.floor((lon - lonDegree) * 60)).intValue();
		final int lonSecond = Double.valueOf(
				(((lon - lonDegree) * 60) - lonMinute) * 60).intValue();
		sb.append(latDegree);
		sb.append('\u00B0');
		insertText(sb, latMinute, latSecond);
		sb.append(latDirection);

		sb.append(" ");

		sb.append(lonDegree);
		sb.append('\u00B0');
		insertText(sb, lonMinute, lonSecond);
		sb.append(lonDirection);
		return sb.toString();
	}
}
