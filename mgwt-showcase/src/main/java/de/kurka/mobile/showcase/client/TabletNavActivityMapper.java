package de.kurka.mobile.showcase.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.kurka.mobile.showcase.client.activities.AboutPlace;
import de.kurka.mobile.showcase.client.activities.ShowCaseListActivity;
import de.kurka.mobile.showcase.client.activities.UIActivity;
import de.kurka.mobile.showcase.client.activities.UIPlace;
import de.kurka.mobile.showcase.client.activities.animation.AnimationActivity;
import de.kurka.mobile.showcase.client.activities.animation.AnimationPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationDissolvePlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationFadePlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationFlipPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationPopPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSlidePlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSlideUpPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSwapPlace;
import de.kurka.mobile.showcase.client.activities.button.ButtonPlace;
import de.kurka.mobile.showcase.client.activities.buttonbar.ButtonBarPlace;
import de.kurka.mobile.showcase.client.activities.elements.ElementsPlace;
import de.kurka.mobile.showcase.client.activities.popup.PopupPlace;
import de.kurka.mobile.showcase.client.activities.progressbar.ProgressBarPlace;
import de.kurka.mobile.showcase.client.activities.scrollwidget.ScrollWidgetPlace;
import de.kurka.mobile.showcase.client.activities.searchbox.SearchBoxPlace;
import de.kurka.mobile.showcase.client.activities.slider.SliderPlace;
import de.kurka.mobile.showcase.client.activities.tabbar.TabBarPlace;
import de.kurka.mobile.showcase.client.places.HomePlace;

public class TabletNavActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public TabletNavActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	private UIActivity uiActivity;
	private ShowCaseListActivity showCaseListActivity;
	private AnimationActivity animationActivity;

	private Activity getUIActivity() {
		if (uiActivity == null) {
			uiActivity = new UIActivity(clientFactory);
		}
		return uiActivity;
	}

	private Activity getShowCaseListActivity() {
		if (showCaseListActivity == null) {
			showCaseListActivity = new ShowCaseListActivity(clientFactory);
		}
		return showCaseListActivity;
	}

	private Activity getAnimationActicity() {
		if (animationActivity == null) {
			animationActivity = new AnimationActivity(clientFactory);
		}
		return animationActivity;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace || place instanceof AboutPlace) {
			return getShowCaseListActivity();
		}

		if (place instanceof UIPlace || place instanceof ScrollWidgetPlace || place instanceof ElementsPlace || place instanceof ButtonBarPlace || place instanceof SearchBoxPlace
				|| place instanceof TabBarPlace || place instanceof ButtonPlace || place instanceof PopupPlace || place instanceof ProgressBarPlace || place instanceof SliderPlace) {
			return getUIActivity();
		}

		if (place instanceof AnimationPlace) {
			return getAnimationActicity();
		}

		if (place instanceof AnimationSlidePlace || place instanceof AnimationSlideUpPlace || place instanceof AnimationDissolvePlace || place instanceof AnimationFadePlace
				|| place instanceof AnimationFlipPlace || place instanceof AnimationPopPlace || place instanceof AnimationSwapPlace) {
			return getAnimationActicity();
		}
		return new ShowCaseListActivity(clientFactory);
	}
}
