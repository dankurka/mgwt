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

import com.googlecode.mgwt.ui.client.widget.tabbar.resources.TabBarImageHolder;

/**
 * A simple Contacts Button for a tab bar.
 *
 * @author Daniel Kurka
 *
 */
public class ContactsTabBarButton extends TabBarButton {

	public ContactsTabBarButton() {
		this(TabPanel.DEFAULT_APPEARANCE);
	}

	public ContactsTabBarButton(TabBarAppearance appearance) {
		super(appearance, TabBarImageHolder.get().contacts(),
		    TabBarImageHolder.get().contactsSelected());

		setText("Contacts");
		text.getStyle().setTop(2, Unit.PX);
	}
}
