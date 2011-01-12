/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client;

import com.google.gwt.place.shared.Place;

import de.kurka.gwt.mobile.mvp.client.Animation;
import de.kurka.gwt.mobile.mvp.client.AnimationMapper;
import de.kurka.mobile.showcase.client.places.AboutPlace;
import de.kurka.mobile.showcase.client.places.AnimationCubePlace;
import de.kurka.mobile.showcase.client.places.AnimationDissolvePlace;
import de.kurka.mobile.showcase.client.places.AnimationFadePlace;
import de.kurka.mobile.showcase.client.places.AnimationFlipPlace;
import de.kurka.mobile.showcase.client.places.AnimationPlace;
import de.kurka.mobile.showcase.client.places.AnimationPopPlace;
import de.kurka.mobile.showcase.client.places.AnimationSlidePlace;
import de.kurka.mobile.showcase.client.places.AnimationSlideUpPlace;
import de.kurka.mobile.showcase.client.places.AnimationSwapPlace;
import de.kurka.mobile.showcase.client.places.ButtonBarPlace;
import de.kurka.mobile.showcase.client.places.ButtonPlace;
import de.kurka.mobile.showcase.client.places.ElementsPlace;
import de.kurka.mobile.showcase.client.places.HomePlace;
import de.kurka.mobile.showcase.client.places.PopupPlace;
import de.kurka.mobile.showcase.client.places.ScrollWidgetPlace;
import de.kurka.mobile.showcase.client.places.SearchBoxPlace;
import de.kurka.mobile.showcase.client.places.TabBarPlace;
import de.kurka.mobile.showcase.client.places.UIPlace;

/**
 * @author kurt
 *
 */
public class AppAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {

		if (oldPlace instanceof UIPlace && newPlace instanceof HomePlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof AboutPlace && newPlace instanceof HomePlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE_UP);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof HomePlace && newPlace instanceof AboutPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE_UP);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof HomePlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationPlace && newPlace instanceof HomePlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ScrollWidgetPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof ScrollWidgetPlace && newPlace instanceof UIPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ElementsPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof ElementsPlace && newPlace instanceof UIPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ButtonBarPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof ButtonBarPlace && newPlace instanceof UIPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof SearchBoxPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof SearchBoxPlace && newPlace instanceof UIPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof TabBarPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof TabBarPlace && newPlace instanceof UIPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ButtonPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof ButtonPlace && newPlace instanceof UIPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof PopupPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof PopupPlace && newPlace instanceof UIPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		//animation

		if (oldPlace instanceof AnimationSlidePlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_SLIDE);
			animation.setDirection(true);
			return animation;
		}

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationSlideUpPlace) {
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

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationDissolvePlace) {
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

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationFadePlace) {
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
		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationFlipPlace) {
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

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationPopPlace) {
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

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationSwapPlace) {
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

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationCubePlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_CUBE);
			animation.setDirection(false);
			return animation;
		}

		if (oldPlace instanceof AnimationCubePlace && newPlace instanceof AnimationPlace) {
			Animation animation = new Animation();
			animation.setType(Animation.ANIMATION_CUBE);
			animation.setDirection(true);
			return animation;
		}

		return new Animation();
	}

}
