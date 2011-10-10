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
package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.mgwt.examples.showcase.client.activities.AboutView;
import com.googlecode.mgwt.examples.showcase.client.activities.AboutViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.ShowCaseListView;
import com.googlecode.mgwt.examples.showcase.client.activities.ShowCaseListViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.UIView;
import com.googlecode.mgwt.examples.showcase.client.activities.UIViewImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.AnimationView;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.AnimationViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationDoneView;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationDoneViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.button.ButtonView;
import com.googlecode.mgwt.examples.showcase.client.activities.button.ButtonViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.buttonbar.ButtonBarView;
import com.googlecode.mgwt.examples.showcase.client.activities.buttonbar.ButtonBarViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.elements.ElementsView;
import com.googlecode.mgwt.examples.showcase.client.activities.elements.ElementsViewImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.popup.PopupView;
import com.googlecode.mgwt.examples.showcase.client.activities.popup.PopupViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.progressbar.ProgressBarView;
import com.googlecode.mgwt.examples.showcase.client.activities.progressbar.ProgressBarViewImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh.PullToRefreshDisplay;
import com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh.PullToRefreshDisplayGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.scrollwidget.ScrollWidgetView;
import com.googlecode.mgwt.examples.showcase.client.activities.scrollwidget.ScrollWidgetViewImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.searchbox.SearchBoxView;
import com.googlecode.mgwt.examples.showcase.client.activities.searchbox.SearchBoxViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.slider.SliderView;
import com.googlecode.mgwt.examples.showcase.client.activities.slider.SliderViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.tabbar.TabBarView;
import com.googlecode.mgwt.examples.showcase.client.activities.tabbar.TabBarViewGwtImpl;

/**
 * @author Daniel Kurka
 * 
 */
public class ClientFactoryImpl implements ClientFactory {

	private EventBus eventBus;
	private PlaceController placeController;
	private ShowCaseListView homeViewImpl;
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
	private PullToRefreshDisplayGwtImpl pullToRefreshView;

	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();

		placeController = new PlaceController(eventBus);

		homeViewImpl = new ShowCaseListViewGwtImpl();
	}

	@Override
	public ShowCaseListView getHomeView() {
		if (homeViewImpl == null) {
			homeViewImpl = new ShowCaseListViewGwtImpl();
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
			animationDoneView = new AnimationDoneViewGwtImpl();
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

	@Override
	public PullToRefreshDisplay getPullToRefreshDisplay() {
		if (pullToRefreshView == null) {
			pullToRefreshView = new PullToRefreshDisplayGwtImpl();
		}
		return pullToRefreshView;
	}

}
