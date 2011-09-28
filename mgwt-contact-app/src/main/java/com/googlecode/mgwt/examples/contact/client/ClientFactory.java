package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.contact.client.activities.AddGroupDisplay;
import com.googlecode.mgwt.examples.contact.client.activities.OverviewDisplay;


public interface ClientFactory {

	PlaceController getPlaceController();

	EventBus getEventBus();

	OverviewDisplay getOverviewDisplay();

	AddGroupDisplay getAddGroupDisplay();

}
