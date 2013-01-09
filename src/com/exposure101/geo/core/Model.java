package com.exposure101.geo.core;

import static com.exposure101.geo.location.LocationKeys.DDMMSS;
import static com.exposure101.geo.location.LocationKeys.DECIMAL;
import static com.exposure101.geo.location.LocationKeys.MGRS;
import static com.exposure101.geo.location.LocationKeys.UTM;
import static com.exposure101.geo.orientation.OrientationSensorKeys.AZIMUTH;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import com.exposure101.geo.common.MapKey;
import com.exposure101.geo.common.Observable;
import com.exposure101.geo.common.Observer;
import com.exposure101.geo.light.LightSensorKeys;
import com.exposure101.geo.location.LocationKeys;
import com.exposure101.geo.orientation.OrientationSensorKeys;

public final class Model implements Observable {

	private final Set<Observer> observers;
	private final Map<MapKey, String> data;

	public Model() {
		int capacity = 0;
		capacity += OrientationSensorKeys.values().length;
		capacity += LocationKeys.values().length;
		capacity += LightSensorKeys.values().length;
		observers = new HashSet<Observer>();
		data = new IdentityHashMap<MapKey, String>(capacity);
		data.put(DDMMSS, "DMS: ");
		data.put(DECIMAL, "DD: ");
		data.put(UTM, "UTM: ");
		data.put(MGRS, "MGRS: ");
		data.put(AZIMUTH, "AZ: ");
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	@Override
	public void notifyObservers() {
		for (final Observer o : observers) {
			o.update(data);
		}
	}

	public void setData(final MapKey key, final String value) {
		data.put(key, value);
		notifyObservers();
	}
}
