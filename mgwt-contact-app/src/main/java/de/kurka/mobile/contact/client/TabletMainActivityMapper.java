package de.kurka.mobile.contact.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.kurka.mobile.contact.client.activities.AddGroupActivity;
import de.kurka.mobile.contact.client.activities.AddGroupPlace;

public class TabletMainActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	private Place lastPlace;

	public TabletMainActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = getActivity(lastPlace, place);
		lastPlace = place;
		return activity;

	}

	private Activity getActivity(Place lastPlace, Place newPlace) {
		if (newPlace instanceof AddGroupPlace) {
			return new AddGroupActivity(clientFactory);
		}
		return null;
	}

}
