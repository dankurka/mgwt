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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import de.kurka.mobile.showcase.client.activities.AnimationDoneView;
import de.kurka.mobile.showcase.client.activities.AnimationDoneViewImpl;
import de.kurka.mobile.showcase.client.activities.ButtonBarView;
import de.kurka.mobile.showcase.client.activities.ButtonBarViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.ButtonView;
import de.kurka.mobile.showcase.client.activities.ButtonViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.ElementsView;
import de.kurka.mobile.showcase.client.activities.ElementsViewImpl;
import de.kurka.mobile.showcase.client.activities.PopupView;
import de.kurka.mobile.showcase.client.activities.PopupViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.ProgressBarView;
import de.kurka.mobile.showcase.client.activities.ProgressBarViewImpl;
import de.kurka.mobile.showcase.client.activities.ScrollWidgetView;
import de.kurka.mobile.showcase.client.activities.ScrollWidgetViewImpl;
import de.kurka.mobile.showcase.client.activities.SearchBoxView;
import de.kurka.mobile.showcase.client.activities.SearchBoxViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.SliderView;
import de.kurka.mobile.showcase.client.activities.SliderViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.TabBarView;
import de.kurka.mobile.showcase.client.activities.TabBarViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.UIView;
import de.kurka.mobile.showcase.client.activities.UIViewImpl;
import de.kurka.mobile.showcase.client.activities.about.AboutView;
import de.kurka.mobile.showcase.client.activities.about.AboutViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.animation.AnimationView;
import de.kurka.mobile.showcase.client.activities.animation.AnimationViewGwtImpl;
import de.kurka.mobile.showcase.client.activities.home.HomeView;
import de.kurka.mobile.showcase.client.activities.home.HomeViewGwtImpl;

/**
 * @author Daniel Kurka
 *
 */
public class ClientFactoryImpl implements ClientFactory {

	private EventBus eventBus;
	private PlaceController placeController;
	private HomeView homeViewImpl;
	private UIView uiView;
	private AboutView aboutView;
	private AnimationView animationView;
	private AnimationDoneView animationDoneView;
	private ScrollWidgetView scrollWidgetView;
	private ElementsView elementsView;
	private ButtonBarViewGwtImpl footerPanelView;
	private SearchBoxViewGwtImpl searchBoxViewGwtImpl;
	private TabBarView tabBarView;
	private ButtonView buttonView;
	private PopupView popupView;
	private ProgressBarView progressBarView;

	private SliderView sliderView;

	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();

		placeController = new PlaceController(eventBus);

		homeViewImpl = new HomeViewGwtImpl();
	}

	@Override
	public HomeView getHomeView() {
		if (homeViewImpl == null) {
			homeViewImpl = new HomeViewGwtImpl();
		}
		return homeViewImpl;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public UIView getUIView() {
		if (uiView == null) {
			uiView = new UIViewImpl();
		}
		return uiView;
	}

	@Override
	public AboutView getAboutView() {
		if (aboutView == null) {
			aboutView = new AboutViewGwtImpl();
		}

		return aboutView;
	}

	@Override
	public AnimationView getAnimationView() {
		if (animationView == null) {
			animationView = new AnimationViewGwtImpl();
		}
		return animationView;
	}

	@Override
	public AnimationDoneView getAnimationDoneView() {
		if (animationDoneView == null) {
			animationDoneView = new AnimationDoneViewImpl();
		}
		return animationDoneView;
	}

	@Override
	public ScrollWidgetView getScrollWidgetView() {
		if (scrollWidgetView == null) {
			scrollWidgetView = new ScrollWidgetViewImpl();
		}
		return scrollWidgetView;
	}

	@Override
	public ElementsView getElementsView() {
		if (elementsView == null) {
			elementsView = new ElementsViewImpl();
		}
		return elementsView;
	}

	@Override
	public ButtonBarView getButtonBarView() {
		if (footerPanelView == null) {
			footerPanelView = new ButtonBarViewGwtImpl();
		}
		return footerPanelView;
	}

	@Override
	public SearchBoxView getSearchBoxView() {
		if (searchBoxViewGwtImpl == null) {
			searchBoxViewGwtImpl = new SearchBoxViewGwtImpl();
		}
		return searchBoxViewGwtImpl;
	}

	@Override
	public TabBarView getTabBarView() {
		if (tabBarView == null) {
			tabBarView = new TabBarViewGwtImpl();
		}
		return tabBarView;
	}

	@Override
	public ButtonView getButtonView() {
		if (buttonView == null) {
			buttonView = new ButtonViewGwtImpl();
		}
		return buttonView;
	}

	@Override
	public PopupView getPopupView() {
		if (popupView == null) {
			popupView = new PopupViewGwtImpl();
		}
		return popupView;
	}

	@Override
	public ProgressBarView getProgressBarView() {
		if (progressBarView == null) {
			progressBarView = new ProgressBarViewImpl();
		}
		return progressBarView;
	}

	@Override
	public SliderView getSliderView() {
		if (sliderView == null) {
			sliderView = new SliderViewGwtImpl();
		}
		return sliderView;
	}

}
