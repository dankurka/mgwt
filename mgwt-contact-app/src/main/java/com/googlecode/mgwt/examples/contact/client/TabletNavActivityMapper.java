package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.examples.contact.client.activities.GroupOverViewActivity;
import com.googlecode.mgwt.examples.contact.client.activities.ShowGroupPlace;
import com.googlecode.mgwt.examples.contact.client.places.AddGroupPlace;
import com.googlecode.mgwt.examples.contact.client.places.HomePlace;

public class TabletNavActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public TabletNavActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	private GroupOverViewActivity overviewActivity;

	private Activity getOverViewActivity() {
		if (overviewActivity == null) {
			overviewActivity = new GroupOverViewActivity(clientFactory);
		}
		return overviewActivity;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace || place instanceof AddGroupPlace || place instanceof ShowGroupPlace) {
			return getOverViewActivity();
		}
		return null;
	}
}
