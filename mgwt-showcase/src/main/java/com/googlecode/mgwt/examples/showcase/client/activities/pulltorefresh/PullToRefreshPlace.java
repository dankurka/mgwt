package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PullToRefreshPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<PullToRefreshPlace> {

		@Override
		public PullToRefreshPlace getPlace(String token) {
			return new PullToRefreshPlace();
		}

		@Override
		public String getToken(PullToRefreshPlace place) {
			return null;
		}

	}
}