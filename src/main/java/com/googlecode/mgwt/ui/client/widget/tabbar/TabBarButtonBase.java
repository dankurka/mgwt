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
package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.TabBarButtonBaseCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * <p>
 * TabBarButtonBase class.
 * </p>
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class TabBarButtonBase extends ButtonBase {

	protected final TabBarButtonBaseCss css;
	private Element icon;
	private Element text;

	/**
	 * <p>
	 * Constructor for TabBarButtonBase.
	 * </p>
	 * 
	 * @param css a
	 *            {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.TabBarButtonBaseCss}
	 *            object.
	 */
	public TabBarButtonBase(TabBarButtonBaseCss css) {
		super(css);
		this.css = css;
		addStyleName(css.button());

		icon = DOM.createDiv();
		icon.addClassName(css.icon());
		getElement().appendChild(icon);

		text = DOM.createDiv();
		text.addClassName(css.text());
		getElement().appendChild(text);
	}

	/**
	 * <p>
	 * setSelected
	 * </p>
	 * 
	 * @param selected a boolean.
	 */
	public void setSelected(boolean selected) {
		if (selected) {
			addStyleName(css.selected());
		} else {
			removeStyleName(css.selected());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText() {
		return icon.getInnerText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setText(String newText) {
		text.setInnerText(newText);
	}

}
