package com.exposure101.geo.core;

import static com.exposure101.geo.orientation.OrientationSensorKeys.AZIMUTH;
import static com.exposure101.geo.light.LightSensorKeys.LIGHT;
import com.exposure101.geo.location.LocationKeys;

public class Controller {

	private final Model model;

	public Controller(final Model model) {
		this.model = model;
	}

	public void handleLocationChanged(final LocationKeys locationKey,
			final String locationValue) {
		final StringBuilder sb = new StringBuilder();
		switch (locationKey) {
		case DDMMSS:
			sb.append("DMS: ");
			break;
		case DECIMAL:
			sb.append("DD: ");
			break;
		case UTM:
			sb.append("UTM: ");
			break;
		case MGRS:
			sb.append("MGRS: ");
			break;
		}
		sb.append(locationValue);
		model.setData(locationKey, sb.toString());
	}

	public String cardinalDirection(float azimuth) {
		if (azimuth < 6) {
			return "N";
		} else if (azimuth < 17) {
			return "NbE";
		} else if (azimuth < 28) {
			return "NNE";
		} else if (azimuth < 39) {
			return "NEbN";
		} else if (azimuth < 51) {
			return "NE";
		} else if (azimuth < 62) {
			return "NEbE";
		} else if (azimuth < 73) {
			return "ENE";
		} else if (azimuth < 84) {
			return "EbN";
		} else if (azimuth < 96) {
			return "E";
		} else if (azimuth < 107) {
			return "EbS";
		} else if (azimuth < 118) {
			return "ESE";
		} else if (azimuth < 129) {
			return "SEbE";
		} else if (azimuth < 141) {
			return "SE";
		} else if (azimuth < 152) {
			return "SEbS";
		} else if (azimuth < 163) {
			return "SSE";
		} else if (azimuth < 174) {
			return "SbE";
		} else if (azimuth < 186) {
			return "S";
		} else if (azimuth < 197) {
			return "SbW";
		} else if (azimuth < 208) {
			return "SSW";
		} else if (azimuth < 219) {
			return "SWbS";
		} else if (azimuth < 231) {
			return "SW";
		} else if (azimuth < 242) {
			return "SWbW";
		} else if (azimuth < 253) {
			return "WSW";
		} else if (azimuth < 264) {
			return "WbS";
		} else if (azimuth < 276) {
			return "W";
		} else if (azimuth < 287) {
			return "WbN";
		} else if (azimuth < 298) {
			return "WNW";
		} else if (azimuth < 309) {
			return "NWbW";
		} else if (azimuth < 321) {
			return "NW";
		} else if (azimuth < 332) {
			return "NWbN";
		} else if (azimuth < 343) {
			return "NNW";
		} else if (azimuth < 354) {
			return "NbW";
		} else if (azimuth < 361) {
			return "N";
		} else {
			return "";
		}
	}

	public void handleOrientationSensorChanged(float azimuth) {
		final StringBuilder sb = new StringBuilder();
		sb.append("AZ: ");
		sb.append(String.valueOf(azimuth));
		sb.append('\u00B0');
		sb.append(" ");
		sb.append(cardinalDirection(azimuth));
		model.setData(AZIMUTH, sb.toString());
	}

	public void handleLightSensorChanged(float light) {
		model.setData(LIGHT, String.valueOf(light));
	}
}
