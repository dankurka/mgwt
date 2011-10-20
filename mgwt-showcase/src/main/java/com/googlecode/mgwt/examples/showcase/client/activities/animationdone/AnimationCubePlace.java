package com.googlecode.mgwt.examples.showcase.client.activities.animationdone;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AnimationCubePlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<AnimationCubePlace> {

		@Override
		public AnimationCubePlace getPlace(String token) {
			return new AnimationCubePlace();
		}

		@Override
		public String getToken(AnimationCubePlace place) {
			return null;
		}

	}
}
