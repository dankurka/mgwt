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
package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * @author Daniel Kurka
 * 
 */
public class TabBarButton extends ButtonBase {

	protected final TabBarCss css;

	public enum TYPE {
		bookmark, contacts, download, favorites, featured, history, more, most_recent, most_viewed, search, custom
	};

	public TabBarButton() {
		this(MGWTStyle.getDefaultClientBundle().getTabBarCss());
	}

	public TabBarButton(TabBarCss css) {
		this(TYPE.custom, css);
	}

	public TabBarButton(TYPE type) {
		this(type, MGWTStyle.getDefaultClientBundle().getTabBarCss());
	}

	public TabBarButton(TYPE type, TabBarCss css) {
		super(css);
		this.css = css;
		css.ensureInjected();
		setType(type);

	}

	public void setType(TYPE type) {
		switch (type) {
		case bookmark:
			addStyleName(css.bookmarkButton());
			setText("Bookmark");
			break;
		case contacts:
			addStyleName(css.contactsButton());
			setText("Contacts");
			break;
		case download:
			addStyleName(css.downloadsButton());
			setText("Downloads");
			break;
		case favorites:
			addStyleName(css.favoritesButton());
			setText("Favorites");
			break;
		case featured:
			addStyleName(css.featuredButton());
			setText("Featured");
			break;
		case history:
			addStyleName(css.historyButton());
			setText("History");
			break;
		case more:
			addStyleName(css.moreButton());
			setText("More");
			break;
		case most_recent:
			addStyleName(css.mostRecentButton());
			setText("Most Recent");
			break;
		case most_viewed:
			addStyleName(css.mostViewedButton());
			setText("Most Viewed");
			break;
		case search:
			addStyleName(css.searchButton());
			setText("Search");
			break;
		case custom:
			break;
		default:

			break;
		}
	}

	public void setSelected(boolean selected) {
		if (selected) {
			addStyleName(css.selected());
		} else {
			removeStyleName(css.selected());
		}
	}

}
