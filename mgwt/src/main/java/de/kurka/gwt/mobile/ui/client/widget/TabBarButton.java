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
package de.kurka.gwt.mobile.ui.client.widget;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.button.ButtonBase;
import de.kurka.gwt.mobile.ui.client.theme.base.TabBarCss;

/**
 * @author Daniel Kurka
 * 
 */
public class TabBarButton extends ButtonBase {

	protected final TabBarCss css;

	public enum TYPE {
		BOOKMARK, CONTACTS, DOWNLOAD, FAVORITES, FEATURED, HISTORY, MORE, MOST_RECENT, MOST_VIEWED, SEARCH, CUSTOM
	};

	public TabBarButton() {
		this(MGWTStyle.getDefaultClientBundle().getTabBarCss());
	}

	public TabBarButton(TabBarCss css) {
		this(TYPE.CUSTOM, css);
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
		case BOOKMARK:
			addStyleName(css.bookmarkButton());
			setText("Bookmark");
			break;
		case CONTACTS:
			addStyleName(css.contactsButton());
			setText("Contacts");
			break;
		case DOWNLOAD:
			addStyleName(css.downloadsButton());
			setText("Downloads");
			break;
		case FAVORITES:
			addStyleName(css.favoritesButton());
			setText("Favorites");
			break;
		case FEATURED:
			addStyleName(css.featuredButton());
			setText("Featured");
			break;
		case HISTORY:
			addStyleName(css.historyButton());
			setText("History");
			break;
		case MORE:
			addStyleName(css.moreButton());
			setText("More");
			break;
		case MOST_RECENT:
			addStyleName(css.mostRecentButton());
			setText("Most Recent");
			break;
		case MOST_VIEWED:
			addStyleName(css.mostViewedButton());
			setText("Most Viewed");
			break;
		case SEARCH:
			addStyleName(css.searchButton());
			setText("Search");
			break;
		case CUSTOM:
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
