/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.kurka.mobile.showcase.client.activities.AboutActivity;
import de.kurka.mobile.showcase.client.activities.AnimationActivity;
import de.kurka.mobile.showcase.client.activities.AnimationDoneActivity;
import de.kurka.mobile.showcase.client.activities.ButtonActivity;
import de.kurka.mobile.showcase.client.activities.ButtonBarActivity;
import de.kurka.mobile.showcase.client.activities.ElementsActivity;
import de.kurka.mobile.showcase.client.activities.HomeActivity;
import de.kurka.mobile.showcase.client.activities.PopupActivity;
import de.kurka.mobile.showcase.client.activities.ProgressBarActivity;
import de.kurka.mobile.showcase.client.activities.ScrollWidgetActivity;
import de.kurka.mobile.showcase.client.activities.SearchBoxActivity;
import de.kurka.mobile.showcase.client.activities.SliderActivity;
import de.kurka.mobile.showcase.client.activities.TabBarActivity;
import de.kurka.mobile.showcase.client.activities.UIActivity;
import de.kurka.mobile.showcase.client.places.AboutPlace;
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
import de.kurka.mobile.showcase.client.places.ProgressBarPlace;
import de.kurka.mobile.showcase.client.places.ScrollWidgetPlace;
import de.kurka.mobile.showcase.client.places.SearchBoxPlace;
import de.kurka.mobile.showcase.client.places.SliderPlace;
import de.kurka.mobile.showcase.client.places.TabBarPlace;
import de.kurka.mobile.showcase.client.places.UIPlace;

/**
 * @author kurt
 *
 */
public class AppActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HomeActivity(clientFactory);
		}

		if (place instanceof UIPlace) {
			return new UIActivity(clientFactory);
		}

		if (place instanceof AboutPlace) {
			return new AboutActivity(clientFactory);
		}

		if (place instanceof AnimationPlace) {
			return new AnimationActivity(clientFactory);
		}

		if (place instanceof ScrollWidgetPlace) {
			return new ScrollWidgetActivity(clientFactory);
		}

		if (place instanceof ElementsPlace) {
			return new ElementsActivity(clientFactory);
		}

		if (place instanceof ButtonBarPlace) {
			return new ButtonBarActivity(clientFactory);
		}

		if (place instanceof SearchBoxPlace) {
			return new SearchBoxActivity(clientFactory);
		}

		if (place instanceof TabBarPlace) {
			return new TabBarActivity(clientFactory);
		}

		if (place instanceof ButtonPlace) {
			return new ButtonActivity(clientFactory);
		}

		if (place instanceof PopupPlace) {
			return new PopupActivity(clientFactory);
		}

		if (place instanceof ProgressBarPlace) {
			return new ProgressBarActivity(clientFactory);
		}

		if (place instanceof SliderPlace) {
			return new SliderActivity(clientFactory);
		}

		if (place instanceof AnimationSlidePlace || place instanceof AnimationSlideUpPlace || place instanceof AnimationDissolvePlace || place instanceof AnimationFadePlace
				|| place instanceof AnimationFlipPlace || place instanceof AnimationPopPlace || place instanceof AnimationSwapPlace) {
			return new AnimationDoneActivity(clientFactory);
		}
		return new HomeActivity(clientFactory);
	}
}
