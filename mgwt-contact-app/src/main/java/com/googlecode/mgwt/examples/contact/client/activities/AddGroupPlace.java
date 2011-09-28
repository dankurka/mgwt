package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AddGroupPlace extends Place {
	public static class HomePlaceTokenizer implements PlaceTokenizer<AddGroupPlace> {

		@Override
		public AddGroupPlace getPlace(String token) {
			return new AddGroupPlace();
		}

		@Override
		public String getToken(AddGroupPlace place) {
			return null;
		}

	}
}
