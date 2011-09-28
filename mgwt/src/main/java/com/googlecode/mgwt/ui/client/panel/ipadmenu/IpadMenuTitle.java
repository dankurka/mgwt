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
package com.googlecode.mgwt.ui.client.panel.ipadmenu;

import com.google.gwt.user.client.ui.HTML;

/**
 * @author Daniel Kurka
 *
 */
public class IpadMenuTitle extends HTML {
	/**
	 * 
	 */
	public IpadMenuTitle() {
		setStylePrimaryName("mgwt-DropDownMenu-title");
	}

	/**
	 * @param string
	 */
	public IpadMenuTitle(String string) {
		this();
		setText(string);
	}
}
