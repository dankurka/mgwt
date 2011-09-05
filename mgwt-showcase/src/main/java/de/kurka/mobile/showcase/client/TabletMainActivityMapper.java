package de.kurka.mobile.showcase.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.kurka.mobile.showcase.client.activities.AboutActivity;
import de.kurka.mobile.showcase.client.activities.UIPlace;
import de.kurka.mobile.showcase.client.activities.animation.AnimationPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationDissolvePlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationDoneActivity;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationFadePlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationFlipPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationPopPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSlidePlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSlideUpPlace;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationSwapPlace;
import de.kurka.mobile.showcase.client.activities.button.ButtonActivity;
import de.kurka.mobile.showcase.client.activities.button.ButtonPlace;
import de.kurka.mobile.showcase.client.activities.buttonbar.ButtonBarActivity;
import de.kurka.mobile.showcase.client.activities.buttonbar.ButtonBarPlace;
import de.kurka.mobile.showcase.client.activities.elements.ElementsActivity;
import de.kurka.mobile.showcase.client.activities.elements.ElementsPlace;
import de.kurka.mobile.showcase.client.activities.popup.PopupActivity;
import de.kurka.mobile.showcase.client.activities.popup.PopupPlace;
import de.kurka.mobile.showcase.client.activities.progressbar.ProgressBarActivity;
import de.kurka.mobile.showcase.client.activities.progressbar.ProgressBarPlace;
import de.kurka.mobile.showcase.client.activities.scrollwidget.ScrollWidgetActivity;
import de.kurka.mobile.showcase.client.activities.scrollwidget.ScrollWidgetPlace;
import de.kurka.mobile.showcase.client.activities.searchbox.SearchBoxActivity;
import de.kurka.mobile.showcase.client.activities.searchbox.SearchBoxPlace;
import de.kurka.mobile.showcase.client.activities.slider.SliderActivity;
import de.kurka.mobile.showcase.client.activities.slider.SliderPlace;
import de.kurka.mobile.showcase.client.activities.tabbar.TabBarActivity;
import de.kurka.mobile.showcase.client.activities.tabbar.TabBarPlace;
import de.kurka.mobile.showcase.client.places.HomePlace;

public class TabletMainActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	private Place lastPlace;

	public TabletMainActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = getActivity(lastPlace, place);
		lastPlace = place;
		return activity;

	}

	private AboutActivity aboutActivity;

	private AboutActivity getAboutActivity() {
		if (aboutActivity == null) {
			aboutActivity = new AboutActivity(clientFactory);
		}

		return aboutActivity;
	}

	private Activity getActivity(Place lastPlace, Place newPlace) {
		if (newPlace instanceof HomePlace) {
			return getAboutActivity();
		}

		if (newPlace instanceof UIPlace) {
			return getAboutActivity();
		}

		if (newPlace instanceof ScrollWidgetPlace) {
			return new ScrollWidgetActivity(clientFactory);
		}

		if (newPlace instanceof ElementsPlace) {
			return new ElementsActivity(clientFactory);
		}

		if (newPlace instanceof ButtonBarPlace) {
			return new ButtonBarActivity(clientFactory);
		}

		if (newPlace instanceof SearchBoxPlace) {
			return new SearchBoxActivity(clientFactory);
		}

		if (newPlace instanceof TabBarPlace) {
			return new TabBarActivity(clientFactory);
		}

		if (newPlace instanceof ButtonPlace) {
			return new ButtonActivity(clientFactory);
		}

		if (newPlace instanceof PopupPlace) {
			return new PopupActivity(clientFactory);
		}

		if (newPlace instanceof ProgressBarPlace) {
			return new ProgressBarActivity(clientFactory);
		}

		if (newPlace instanceof SliderPlace) {
			return new SliderActivity(clientFactory);
		}

		if (newPlace instanceof AnimationPlace) {
			return new AboutActivity(clientFactory);
		}

		if (newPlace instanceof AnimationSlidePlace || newPlace instanceof AnimationSlideUpPlace || newPlace instanceof AnimationDissolvePlace || newPlace instanceof AnimationFadePlace
				|| newPlace instanceof AnimationFlipPlace || newPlace instanceof AnimationPopPlace || newPlace instanceof AnimationSwapPlace) {
			return new AnimationDoneActivity(clientFactory);
		}

		return null;
	}

}
