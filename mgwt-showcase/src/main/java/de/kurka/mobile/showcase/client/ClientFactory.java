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
import com.google.gwt.place.shared.PlaceController;

import de.kurka.mobile.showcase.client.activities.SearchBoxView;
import de.kurka.mobile.showcase.client.activities.SliderView;
import de.kurka.mobile.showcase.client.activities.TabBarView;
import de.kurka.mobile.showcase.client.activities.UIView;
import de.kurka.mobile.showcase.client.activities.about.AboutView;
import de.kurka.mobile.showcase.client.activities.animation.AnimationView;
import de.kurka.mobile.showcase.client.activities.animationdone.AnimationDoneView;
import de.kurka.mobile.showcase.client.activities.button.ButtonView;
import de.kurka.mobile.showcase.client.activities.buttonbar.ButtonBarView;
import de.kurka.mobile.showcase.client.activities.elements.ElementsView;
import de.kurka.mobile.showcase.client.activities.home.HomeView;
import de.kurka.mobile.showcase.client.activities.popup.PopupView;
import de.kurka.mobile.showcase.client.activities.progressbar.ProgressBarView;
import de.kurka.mobile.showcase.client.activities.scrollwidget.ScrollWidgetView;

/**
 * @author Daniel Kurka
 *
 */
public interface ClientFactory {
	public HomeView getHomeView();

	public EventBus getEventBus();

	public PlaceController getPlaceController();

	/**
	 * @return
	 */
	public UIView getUIView();

	public AboutView getAboutView();

	public AnimationView getAnimationView();

	public AnimationDoneView getAnimationDoneView();

	public ScrollWidgetView getScrollWidgetView();

	public ElementsView getElementsView();

	public ButtonBarView getButtonBarView();

	public SearchBoxView getSearchBoxView();

	public TabBarView getTabBarView();

	public ButtonView getButtonView();

	/**
	 * 
	 */
	public PopupView getPopupView();

	public ProgressBarView getProgressBarView();

	public SliderView getSliderView();

}
