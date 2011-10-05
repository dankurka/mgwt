package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

public class TabletMainAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {

		Animation animation = new Animation();
		animation.setType(Animation.ANIMATION_FADE);

		return animation;
	}

}
