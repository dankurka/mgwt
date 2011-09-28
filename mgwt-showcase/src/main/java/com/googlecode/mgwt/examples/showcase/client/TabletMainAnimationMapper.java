package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.AnimationPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationDissolvePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationFadePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationFlipPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationPopPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSlidePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSlideUpPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSwapPlace;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;


public class TabletMainAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace == null) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_FADE);
			return animation;
		}

		// animation

		if (oldPlace instanceof AnimationSlidePlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (newPlace instanceof AnimationSlideUpPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE_UP);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationSlideUpPlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE_UP);
			animation.setDirection(true);
			return animation;
		}

		if (newPlace instanceof AnimationDissolvePlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_DISSOLVE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationDissolvePlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_DISSOLVE);
			animation.setDirection(true);
			return animation;
		}

		if (newPlace instanceof AnimationFadePlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_FADE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationFadePlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_FADE);
			animation.setDirection(true);
			return animation;
		}
		if (newPlace instanceof AnimationFlipPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_FLIP);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationFlipPlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_FLIP);
			animation.setDirection(true);
			return animation;
		}

		if (newPlace instanceof AnimationPopPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_POP);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationPopPlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_POP);
			animation.setDirection(true);
			return animation;
		}

		if (newPlace instanceof AnimationSwapPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SWAP);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationSwapPlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SWAP);
			animation.setDirection(true);
			return animation;
		}

		return new Animation();
	}

}
