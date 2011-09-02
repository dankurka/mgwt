package de.kurka.mobile.showcase.client;

import com.google.gwt.place.shared.Place;

import de.kurka.gwt.mobile.mvp.client.Animation;
import de.kurka.gwt.mobile.mvp.client.AnimationMapper;

public class TabletMainAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace == null) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_FADE);
			return animation;
		}
		return new Animation();
	}

}
