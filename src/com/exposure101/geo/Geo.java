package com.exposure101.geo;

import java.util.HashSet;
import java.util.Set;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import com.exposure101.geo.common.ActivityStateListener;
import com.exposure101.geo.core.Controller;
import com.exposure101.geo.core.Model;
import com.exposure101.geo.core.View;
import com.exposure101.geo.location.LocationSensor;
import com.exposure101.geo.orientation.OrientationSensor;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

@SuppressWarnings("unused")
public class Geo extends MapActivity {

	private static final Set<ActivityStateListener> activityStateListeners;
	static {
		activityStateListeners = new HashSet<ActivityStateListener>();
	}

	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;
	private MapController mapController;
	private SensorManager sensorManager;
	private int sensor = SensorManager.SENSOR_ORIENTATION;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActivity();
		final Model model = new Model();
		final Controller controller = new Controller(model);
		final View view = new View(this, controller, model);
		activityStateListeners.add(new OrientationSensor(
				(SensorManager) getSystemService(SENSOR_SERVICE), controller,
				model));
		activityStateListeners.add(new LocationSensor(
				(LocationManager) getSystemService(Context.LOCATION_SERVICE),
				controller, model));
		for (final ActivityStateListener asl : activityStateListeners) {
			asl.onCreate();
		}
	}

/*	Starting point to plot points on the map, never had a chance to finish
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, "Edit Points").setIcon(
				android.R.drawable.ic_menu_myplaces);
		menu.add(0, 2, 0, "Toggle Map").setIcon(
				android.R.drawable.ic_menu_mapmode);
		menu.add(0, 3, 0, "Options").setIcon(
				android.R.drawable.ic_menu_preferences);
		menu.add(0, 4, 0, "Quit").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			return true;
		case 2:
			if (mapView.isEnabled() == false) {
				mapView.setEnabled(true);
			} else {
				mapView.setEnabled(false);
			}
			;
			return true;
		case 3:
			return true;
		case 4:
			this.finish();
			return true;
		}
		return false;
	}*/

	private void initActivity() {
		setContentView(R.layout.main);
		initMap();
	}

	private void initMap() {
		mapView = (MapView) findViewById(R.id.mainmapview);
		mapController = mapView.getController();
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		mapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapController.setZoom(18);
				mapView.preLoad();
				mapController.setCenter(myLocationOverlay.getMyLocation());
			}
		});
		mapView.setEnabled(true);
		mapView.setClickable(true);
		mapView.setSatellite(true);
		mapView.displayZoomControls(true);
		mapView.setKeepScreenOn(true);
		mapView.setBuiltInZoomControls(true);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		for (final ActivityStateListener asl : activityStateListeners) {
			asl.onResume();
		}
	}
	
	@Override
	public void onDestroy() {
		finish();
		super.onStop();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		for (final ActivityStateListener asl : activityStateListeners) {
			asl.onPause();
		}
	}
}