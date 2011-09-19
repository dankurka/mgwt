package de.kurka.mobile.contact.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.kurka.mobile.contact.client.activities.AddGroupPlace;
import de.kurka.mobile.contact.client.activities.HomePlace;
import de.kurka.mobile.contact.client.activities.OverviewActivity;

public class TabletNavActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public TabletNavActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	private OverviewActivity overviewActivity;

	private Activity getOverViewActivity() {
		if (overviewActivity == null) {
			overviewActivity = new OverviewActivity(clientFactory);
		}
		return overviewActivity;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace || place instanceof AddGroupPlace) {
			return getOverViewActivity();
		}
		return null;
	}
}
