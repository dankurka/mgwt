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
package de.kurka.gwt.mobile.ui.client.menu.tabbar;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.TabBarCss;

/**
 * @author Daniel Kurka
 * 
 */
public class TabBarMostViewedButton extends TabBarButtonBase {

	public TabBarMostViewedButton() {
		this(MGWTStyle.getDefaultClientBundle().getTabBarCss());
	}

	public TabBarMostViewedButton(TabBarCss css) {
		addStyleName(css.mostViewedButton());

		setText("Most Viewed");
	}
}
