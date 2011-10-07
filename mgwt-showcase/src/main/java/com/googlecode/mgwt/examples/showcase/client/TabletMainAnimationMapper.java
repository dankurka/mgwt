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
			return Animation.FADE;
		}

		// animation

		if (oldPlace instanceof AnimationSlidePlace && newPlace instanceof AnimationPlace) {
			return Animation.SLIDE_REVERSE;
		}

		if (newPlace instanceof AnimationSlideUpPlace) {
			return Animation.SLIDE_UP;
		}

		if (oldPlace instanceof AnimationSlideUpPlace && newPlace instanceof AnimationPlace) {
			return Animation.SLIDE_UP_REVERSE;
		}

		if (newPlace instanceof AnimationDissolvePlace) {
			return Animation.DISSOLVE;
		}

		if (oldPlace instanceof AnimationDissolvePlace && newPlace instanceof AnimationPlace) {
			return Animation.DISSOLVE_REVERSE;
		}

		if (newPlace instanceof AnimationFadePlace) {
			return Animation.FADE;
		}

		if (oldPlace instanceof AnimationFadePlace && newPlace instanceof AnimationPlace) {
			return Animation.FADE_REVERSE;
		}
		if (newPlace instanceof AnimationFlipPlace) {
			return Animation.FLIP;
		}

		if (oldPlace instanceof AnimationFlipPlace && newPlace instanceof AnimationPlace) {
			return Animation.FLIP_REVERSE;
		}

		if (newPlace instanceof AnimationPopPlace) {
			return Animation.POP;
		}

		if (oldPlace instanceof AnimationPopPlace && newPlace instanceof AnimationPlace) {
			return Animation.POP_REVERSE;
		}

		if (newPlace instanceof AnimationSwapPlace) {
			return Animation.SWAP;
		}

		if (oldPlace instanceof AnimationSwapPlace && newPlace instanceof AnimationPlace) {
			return Animation.SWAP_REVERSE;
		}

		return Animation.SLIDE;
	}

}
