package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.mgwt.examples.contact.client.activities.AddGroupDisplay;
import com.googlecode.mgwt.examples.contact.client.activities.AddGroupDisplayGwtImpl;
import com.googlecode.mgwt.examples.contact.client.activities.GroupOverViewDisplay;
import com.googlecode.mgwt.examples.contact.client.activities.GroupOverViewDisplayGwtImpl;
import com.googlecode.mgwt.examples.contact.client.activities.ShowGroupDisplay;
import com.googlecode.mgwt.examples.contact.client.activities.ShowGroupDisplayGwtImpl;

public class ClientFactoryGwtImpl implements ClientFactory {

	private PlaceController placeController;
	private SimpleEventBus eventBus;
	private GroupOverViewDisplayGwtImpl overviewDisplay;
	private AddGroupDisplay addGroupDisplay;
	private Storage storage;
	private ShowGroupDisplay showGroupDisplay;

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
	public GroupOverViewDisplay getOverviewDisplay() {
		if (overviewDisplay == null) {
			overviewDisplay = new GroupOverViewDisplayGwtImpl();
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

	@Override
	public Storage getStorage() {
		if (storage == null) {
			storage = new StorageHTML5Impl();
		}
		return storage;
	}

	@Override
	public ShowGroupDisplay getShowGroupDisplay() {
		if (showGroupDisplay == null) {
			showGroupDisplay = new ShowGroupDisplayGwtImpl();
		}
		return showGroupDisplay;
	}

}
