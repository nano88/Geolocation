package com.exposure101.geo.common;

import java.util.Map;

public interface Observer {

	public void update(final Map<MapKey, String> data);
}
