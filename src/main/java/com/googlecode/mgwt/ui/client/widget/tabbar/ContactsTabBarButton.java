/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.dom.client.Style.Unit;

import com.googlecode.mgwt.ui.client.MGWT;

/**
 * A simple Contacts Button for a tab bar.
 * 
 * @author Daniel Kurka
 * 
 */
public class ContactsTabBarButton extends TabBarButton {

  /**
   * Construct a BookmarkTabBarButton
   * 
   */
	public ContactsTabBarButton() {
		this(TabPanel.DEFAULT_APPEARANCE);
	}

  /**
   * Construct a BookmarkTabBarButton with a given css
   * 
   * @param css the css to use
   */
	public ContactsTabBarButton(TabBarAppearance appearance) {
		super(appearance, MGWT.getOsDetection().isIOs() || MGWT.getOsDetection().isDesktop() ? appearance.icons().contactsImage() : null);

		setText("Contacts");
		if (imageResource != null)
			text.getStyle().setPaddingTop(3, Unit.PX);

	}
}
