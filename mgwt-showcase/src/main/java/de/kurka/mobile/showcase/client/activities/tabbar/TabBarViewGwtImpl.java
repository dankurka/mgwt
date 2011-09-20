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
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarBookmarkButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarContactsButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarDownloadsButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarFavoritesButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarFeaturedButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarHistoryButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarMoreButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarMostRecentButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarMostViewedButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabBarSearchButton;
import de.kurka.gwt.mobile.ui.client.menu.tabbar.TabPanel;
import de.kurka.gwt.mobile.ui.client.widget.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.widget.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.LayoutPanel;

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

		tabPanel.add(new TabBarBookmarkButton(), new Label("Bookmark"));
		tabPanel.add(new TabBarContactsButton(), new Label("Contacts"));
		tabPanel.add(new TabBarDownloadsButton(), new Label("Downloads"));
		tabPanel.add(new TabBarFavoritesButton(), new Label("Favorites"));
		tabPanel.add(new TabBarFeaturedButton(), new Label("Featured"));
		tabPanel.add(new TabBarHistoryButton(), new Label("History"));
		tabPanel.add(new TabBarMoreButton(), new Label("More"));
		tabPanel.add(new TabBarMostRecentButton(), new Label("Most Recent"));
		tabPanel.add(new TabBarMostViewedButton(), new Label("Most Viewed"));
		tabPanel.add(new TabBarSearchButton(), new Label("Search"));

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
