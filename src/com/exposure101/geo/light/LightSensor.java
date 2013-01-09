package com.exposure101.geo.light;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.exposure101.geo.common.ActivityStateListener;
import com.exposure101.geo.core.Controller;
import com.exposure101.geo.core.Model;

public class LightSensor implements ActivityStateListener {

	private final SensorEventListener sensorListener;
	private final SensorManager sensorManager;

	public LightSensor(final SensorManager sensorManager,
			final Controller controller, final Model model) {
		this.sensorManager = sensorManager;
		sensorListener = new SensorEventListener() {

			@Override
			public void onSensorChanged(SensorEvent event) {
				controller.handleLightSensorChanged(Math.round(event.values[0]));
			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {

			}
		};
	}

	@Override
	public void onCreate() {
		sensorManager.registerListener(sensorListener, sensorManager
				.getDefaultSensor(Sensor.TYPE_LIGHT), 0);
	}

	@Override
	public void onDestroy() {
		sensorManager.unregisterListener(sensorListener);
	}

	@Override
	public void onPause() {
		sensorManager.unregisterListener(sensorListener);
	}

	@Override
	public void onResume() {
		sensorManager.registerListener(sensorListener, sensorManager
				.getDefaultSensor(Sensor.TYPE_LIGHT), 0);
	}
}
