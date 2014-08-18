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

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

/**
 * Experimental to get TabPanel work with UIBinder
 *
 * at the moment theres no support for custom parsers:
 * http://code.google.com/p/google-web-toolkit/issues/detail?id=4461
 *
 * @author Daniel Kurka
 */
public class Tab extends Widget {
	private Widget widget;
	private TabBarButtonBase button;

	/**
	 * <p>Constructor for Tab.</p>
	 */
	public Tab() {
		setElement(Document.get().createDivElement());
	}

	/**
	 * <p>Setter for the field <code>button</code>.</p>
	 *
	 * @param button a {@link com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase} object.
	 */
	@UiChild(limit = 1, tagname = "button")
	public void setButton(TabBarButtonBase button) {
		this.button = button;

	}

	/**
	 * <p>Setter for the field <code>widget</code>.</p>
	 *
	 * @param w a {@link com.google.gwt.user.client.ui.Widget} object.
	 */
	@UiChild(limit = 1, tagname = "widget")
	public void setWidget(Widget w) {
		this.widget = w;

	}

	/**
	 * <p>Getter for the field <code>widget</code>.</p>
	 *
	 * @return a {@link com.google.gwt.user.client.ui.Widget} object.
	 */
	public Widget getWidget() {
		return widget;
	}

	/**
	 * <p>Getter for the field <code>button</code>.</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase} object.
	 */
	public TabBarButtonBase getButton() {
		return button;
	}
}
