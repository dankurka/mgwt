package de.kurka.mobile.contact.client;

import com.google.gwt.place.shared.Place;

import de.kurka.gwt.mobile.mvp.client.Animation;
import de.kurka.gwt.mobile.mvp.client.AnimationMapper;

public class TabletNavAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {

		return new Animation();
	}

}
