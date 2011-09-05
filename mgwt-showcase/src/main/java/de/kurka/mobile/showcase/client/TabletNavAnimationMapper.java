package de.kurka.mobile.showcase.client;

import com.google.gwt.place.shared.Place;

import de.kurka.gwt.mobile.mvp.client.Animation;
import de.kurka.gwt.mobile.mvp.client.AnimationMapper;
import de.kurka.mobile.showcase.client.activities.UIPlace;
import de.kurka.mobile.showcase.client.places.HomePlace;

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
