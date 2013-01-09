package com.exposure101.geo.common;

public interface Observable {

	public void addObserver(final Observer o);
	
	public void removeObserver(final Observer o);
	
	public void notifyObservers();
}
