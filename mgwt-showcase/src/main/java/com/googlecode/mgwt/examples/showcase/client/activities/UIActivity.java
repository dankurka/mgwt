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
package com.googlecode.mgwt.examples.showcase.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.activities.button.ButtonPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.buttonbar.ButtonBarPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.elements.ElementsPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.popup.PopupPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.progressbar.ProgressBarPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh.PullToRefreshPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.scrollwidget.ScrollWidgetPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.searchbox.SearchBoxPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.slider.SliderPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.tabbar.TabBarPlace;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

/**
 * @author Daniel Kurka
 * 
 */
public class UIActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	private int oldIndex;

	public UIActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final UIView view = clientFactory.getUIView();

		view.setBackButtonText("Home");
		view.setTitle("UI");

		addHandlerRegistration(view.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace());

			}
		}));

		view.renderItems(createItems());

		addHandlerRegistration(view.getList().addCellSelectedHandler(new CellSelectedHandler() {

			@Override
			public void onCellSelected(CellSelectedEvent event) {
				int index = event.getIndex();

				switch (index) {
				case 0:
					clientFactory.getPlaceController().goTo(new ScrollWidgetPlace());

					break;
				case 1:
					clientFactory.getPlaceController().goTo(new ElementsPlace());

					break;

				case 2:

					clientFactory.getPlaceController().goTo(new ButtonBarPlace());
					break;

				case 3:

					clientFactory.getPlaceController().goTo(new SearchBoxPlace());
					break;

				case 4:

					clientFactory.getPlaceController().goTo(new TabBarPlace());
					break;

				case 5:

					clientFactory.getPlaceController().goTo(new ButtonPlace());
					break;

				case 6:

					clientFactory.getPlaceController().goTo(new PopupPlace());
					break;

				case 7:

					clientFactory.getPlaceController().goTo(new ProgressBarPlace());
					break;

				case 8:

					clientFactory.getPlaceController().goTo(new SliderPlace());
					break;

				case 9:

					clientFactory.getPlaceController().goTo(new PullToRefreshPlace());
					break;

				default:
					break;
				}
				view.setSelectedIndex(oldIndex, false);
				view.setSelectedIndex(index, true);
				oldIndex = index;

			}
		}));

		panel.setWidget(view);

	}

	/**
	 * @return
	 */
	private List<Item> createItems() {
		ArrayList<Item> list = new ArrayList<Item>();

		list.add(new Item("Scroll Widget"));
		list.add(new Item("Elements"));
		list.add(new Item("ButtonBar"));
		list.add(new Item("Searchbox"));
		list.add(new Item("TabBar"));
		list.add(new Item("Buttons"));
		list.add(new Item("Popups"));
		list.add(new Item("ProgressBar"));
		list.add(new Item("Slider"));
		list.add(new Item("PullToRefresh"));

		return list;
	}

}
