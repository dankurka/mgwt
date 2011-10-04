package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ShowGroupPlace extends Place {
	private final String id;

	public ShowGroupPlace(String id) {
		this.id = id;

	}

	public String getId() {
		return id;
	}

	public static final String UNKNOWN_ID = "UNKNOWN";

	public static class ShowGroupPlaceTokenizer implements PlaceTokenizer<ShowGroupPlace> {

		@Override
		public ShowGroupPlace getPlace(String token) {
			if ("".equals(token)) {
				return new ShowGroupPlace(UNKNOWN_ID);
			}
			return new ShowGroupPlace(token);
		}

		@Override
		public String getToken(ShowGroupPlace place) {
			return place.getId();
		}

	}
}
