package de.kurka.mobile.contact.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import de.kurka.mobile.contact.client.activities.AddGroupDisplay;
import de.kurka.mobile.contact.client.activities.AddGroupDisplayGwtImpl;
import de.kurka.mobile.contact.client.activities.OverviewDisplay;
import de.kurka.mobile.contact.client.activities.OverviewDisplayGwtImpl;

public class ClientFactoryGwtImpl implements ClientFactory {

	private PlaceController placeController;
	private SimpleEventBus eventBus;
	private OverviewDisplayGwtImpl overviewDisplay;
	private AddGroupDisplay addGroupDisplay;

	@Override
	public PlaceController getPlaceController() {
		if (placeController == null) {
			placeController = new PlaceController(getEventBus());
		}
		return placeController;
	}

	@Override
	public EventBus getEventBus() {
		if (eventBus == null) {
			eventBus = new SimpleEventBus();
		}
		return eventBus;
	}

	@Override
	public OverviewDisplay getOverviewDisplay() {
		if (overviewDisplay == null) {
			overviewDisplay = new OverviewDisplayGwtImpl();
		}
		return overviewDisplay;
	}

	@Override
	public AddGroupDisplay getAddGroupDisplay() {
		if (addGroupDisplay == null) {
			addGroupDisplay = new AddGroupDisplayGwtImpl();
		}
		return addGroupDisplay;
	}

}
