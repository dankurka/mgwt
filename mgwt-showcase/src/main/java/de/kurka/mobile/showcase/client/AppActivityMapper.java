/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.kurka.mobile.showcase.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import de.kurka.mobile.showcase.client.activities.about.AboutActivity;
import de.kurka.mobile.showcase.client.activities.about.AboutPlace;
import de.kurka.mobile.showcase.client.activities.animation.AnimationActivity;
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
import de.kurka.mobile.showcase.client.activities.home.HomeActivity;
import de.kurka.mobile.showcase.client.activities.home.HomePlace;
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
import de.kurka.mobile.showcase.client.activities.ui.UIActivity;
import de.kurka.mobile.showcase.client.activities.ui.UIPlace;

/**
 * @author Daniel Kurka
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
