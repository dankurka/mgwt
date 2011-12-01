package com.googlecode.mgwt.mvp.client.history;

import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public interface HistoryObserver {
	public void onPlaceChange(Place place, HistoryHandler handler);

	public void onHistoryChanged(Place place, HistoryHandler handler);

	public void onAppStarted(Place place, HistoryHandler historyHandler);

	public HandlerRegistration bind(EventBus eventBus, HistoryHandler historyHandler);
}
