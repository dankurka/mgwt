package de.kurka.mobile.contact.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import de.kurka.mobile.contact.client.activities.AddGroupDisplay;
import de.kurka.mobile.contact.client.activities.OverviewDisplay;

public interface ClientFactory {

	PlaceController getPlaceController();

	EventBus getEventBus();

	OverviewDisplay getOverviewDisplay();

	AddGroupDisplay getAddGroupDisplay();

}
