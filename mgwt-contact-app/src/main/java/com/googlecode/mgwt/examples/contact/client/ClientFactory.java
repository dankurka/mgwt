package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.contact.client.activities.AddGroupDisplay;
import com.googlecode.mgwt.examples.contact.client.activities.GroupOverViewDisplay;
import com.googlecode.mgwt.examples.contact.client.activities.ShowGroupDisplay;

public interface ClientFactory {

	PlaceController getPlaceController();

	EventBus getEventBus();

	GroupOverViewDisplay getOverviewDisplay();

	AddGroupDisplay getAddGroupDisplay();

	Storage getStorage();

	ShowGroupDisplay getShowGroupDisplay();

}
