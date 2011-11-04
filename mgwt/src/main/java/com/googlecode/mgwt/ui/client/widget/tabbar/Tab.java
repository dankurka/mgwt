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

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Experimental to get TabPanel work with UIBinder
 * 
 * at the moment theres no support for custom parsers:
 * http://code.google.com/p/google-web-toolkit/issues/detail?id=4461
 * 
 * @author Daniel Kurka
 * 
 */
public class Tab extends Widget {
	private Widget widget;
	private TabBarButtonBase button;

	public Tab() {
		setElement(DOM.createDiv());
	}

	@UiChild(limit = 1, tagname = "button")
	public void setButton(TabBarButtonBase button) {
		this.button = button;

	}

	@UiChild(limit = 1, tagname = "widget")
	public void setWidget(Widget w) {
		this.widget = w;

	}

	public Widget getWidget() {
		return widget;
	}

	public TabBarButtonBase getButton() {
		return button;
	}
}
