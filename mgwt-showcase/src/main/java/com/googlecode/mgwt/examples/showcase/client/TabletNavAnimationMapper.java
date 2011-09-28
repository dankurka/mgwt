package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.examples.showcase.client.activities.UIPlace;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;


public class TabletNavAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace == null) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_FADE);
			return animation;
		}

		if (oldPlace instanceof HomePlace && newPlace instanceof UIPlace) {
			return new Animation();
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof HomePlace) {
			Animation animation = new Animation();
			animation.setDirection(true);
			animation.setType(Animation.ANIMATION_SLIDE);
			return animation;
		}

		return new Animation();
	}

}
