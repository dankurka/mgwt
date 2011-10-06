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
package com.googlecode.mgwt.examples.showcase.client.activities.tabbar;

import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.examples.showcase.client.DetailViewGwtImpl;
import com.googlecode.mgwt.ui.client.widget.TabBarButton;
import com.googlecode.mgwt.ui.client.widget.TabBarButton.TYPE;
import com.googlecode.mgwt.ui.client.widget.TabPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class TabBarViewGwtImpl extends DetailViewGwtImpl implements TabBarView {

	public TabBarViewGwtImpl() {

		TabPanel tabPanel = new TabPanel();
		tabPanel.setScrollingEnabledX(false);

		tabPanel.add(new TabBarButton(TYPE.bookmark), new Label("Bookmark"));
		tabPanel.add(new TabBarButton(TYPE.contacts), new Label("Contacts"));
		tabPanel.add(new TabBarButton(TYPE.download), new Label("Downloads"));
		tabPanel.add(new TabBarButton(TYPE.favorites), new Label("Favorites"));
		tabPanel.add(new TabBarButton(TYPE.featured), new Label("Featured"));
		tabPanel.add(new TabBarButton(TYPE.history), new Label("History"));
		tabPanel.add(new TabBarButton(TYPE.more), new Label("More"));
		tabPanel.add(new TabBarButton(TYPE.most_recent), new Label("Most Recent"));
		tabPanel.add(new TabBarButton(TYPE.most_viewed), new Label("Most Viewed"));
		tabPanel.add(new TabBarButton(TYPE.search), new Label("Search"));

		main.add(tabPanel);
	}

}
