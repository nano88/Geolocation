package com.exposure101.geo.core;

import static com.exposure101.geo.location.LocationKeys.DDMMSS;
import static com.exposure101.geo.location.LocationKeys.DECIMAL;
import static com.exposure101.geo.location.LocationKeys.MGRS;
import static com.exposure101.geo.location.LocationKeys.UTM;
import static com.exposure101.geo.orientation.OrientationSensorKeys.AZIMUTH;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Color;
import android.widget.TextView;

import com.exposure101.geo.Geo;
import com.exposure101.geo.common.MapKey;
import com.exposure101.geo.common.Observer;
import com.exposure101.geo.location.LocationKeys;
import com.exposure101.geo.orientation.OrientationSensorKeys;
import com.exposure101.geo.R;

public final class View implements Observer {

	private static final float TEXT_SIZE = 16.0F;
	
	private final Map<MapKey, TextView> text;

	public View(final Geo activity, final Controller controller,
			final Model model) {
		int capacity = 0;
		capacity += OrientationSensorKeys.values().length;
		capacity += LocationKeys.values().length;
		text = new IdentityHashMap<MapKey, TextView>(capacity);
		text.put(AZIMUTH, (TextView) activity.findViewById(R.id.heading));
		text.put(DDMMSS, (TextView) activity.findViewById(R.id.ddmmss));
		text.put(DECIMAL, (TextView) activity.findViewById(R.id.decimal));
		text.put(UTM, (TextView) activity.findViewById(R.id.utm));
		text.put(MGRS, (TextView) activity.findViewById(R.id.mgrs));
		for (final TextView t : text.values()) {
			t.setTextSize(TEXT_SIZE);
			t.setTextColor(Color.argb(200, 255, 0, 0));
		}
		model.addObserver(this);
	}

	@Override
	public void update(final Map<MapKey, String> data) {
		for (final Entry<MapKey, String> e : data.entrySet()) {
			text.get(e.getKey()).setText(e.getValue());
		}
	}
}
