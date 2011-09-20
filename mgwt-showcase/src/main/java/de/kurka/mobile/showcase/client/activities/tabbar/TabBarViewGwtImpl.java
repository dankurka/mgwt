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
package de.kurka.mobile.showcase.client.activities.tabbar;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.MGWTUtil;
import de.kurka.gwt.mobile.ui.client.widget.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.widget.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.LayoutPanel;
import de.kurka.gwt.mobile.ui.client.widget.TabBarButton;
import de.kurka.gwt.mobile.ui.client.widget.TabPanel;
import de.kurka.gwt.mobile.ui.client.widget.TabBarButton.TYPE;

/**
 * @author Daniel Kurka
 * 
 */
public class TabBarViewGwtImpl implements TabBarView {

	private LayoutPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;

	public TabBarViewGwtImpl() {
		main = new LayoutPanel();

		headerPanel = new HeaderPanel();

		headerPanel.setCenter("TabBar");
		backButton = new HeaderBackButton();

		backButton.setText("UI");

		if (MGWTUtil.getFeatureDetection().isPhone()) {
			headerPanel.setLeftWidget(backButton);
		}

		main.add(headerPanel);

		TabPanel tabPanel = new TabPanel();
		tabPanel.setScrollingEnabledX(false);

		tabPanel.add(new TabBarButton(TYPE.BOOKMARK), new Label("Bookmark"));
		tabPanel.add(new TabBarButton(TYPE.CONTACTS), new Label("Contacts"));
		tabPanel.add(new TabBarButton(TYPE.DOWNLOAD), new Label("Downloads"));
		tabPanel.add(new TabBarButton(TYPE.FAVORITES), new Label("Favorites"));
		tabPanel.add(new TabBarButton(TYPE.FEATURED), new Label("Featured"));
		tabPanel.add(new TabBarButton(TYPE.HISTORY), new Label("History"));
		tabPanel.add(new TabBarButton(TYPE.MORE), new Label("More"));
		tabPanel.add(new TabBarButton(TYPE.MOST_RECENT), new Label("Most Recent"));
		tabPanel.add(new TabBarButton(TYPE.MOST_VIEWED), new Label("Most Viewed"));
		tabPanel.add(new TabBarButton(TYPE.SEARCH), new Label("Search"));

		main.add(tabPanel);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return backButton;
	}

}
