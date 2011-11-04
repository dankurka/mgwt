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

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;

/**
 * A HeaderPanel is usually at the top of a page containing navigation elements
 * 
 * it can contain three child elements. A left Widget, a center widget and a
 * right widget.
 * 
 * @author Daniel Kurka
 * 
 */
public class HeaderPanel extends Composite {

	private Widget left;
	private Widget right;
	private Widget title;

	private FlowPanel container;
	private final HeaderCss css;

	/**
	 * Construct a HeaderPanel
	 */
	public HeaderPanel() {
		this(MGWTStyle.getDefaultClientBundle().getHeaderCss());
	}

	/**
	 * Construct a headerPanel with a given css
	 * 
	 * @param css the css to use
	 */
	public HeaderPanel(HeaderCss css) {
		this.css = css;
		this.css.ensureInjected();
		container = new FlowPanel();
		container.setStylePrimaryName(this.css.headerPanel());

		initWidget(container);

	}

	/**
	 * Set a text to appear in the middle of the HeaderPanel
	 * 
	 * @param text the text to render
	 */
	public void setCenter(String text) {
		HTML wrapper = new HTML();
		wrapper.setHTML(text);
		setCenterWidget(wrapper);
	}

	/**
	 * Set a widget that should appear in the center of the header panel
	 * 
	 * @param w the widget that is displayed in the center
	 */
	@UiChild(limit = 1, tagname = "center")
	public void setCenterWidget(Widget w) {
		if (title != null) {
			container.remove(title);
		}
		title = w;
		if (title != null) {
			w.addStyleName(this.css.center());
			container.add(w);

		}

	}

	/**
	 * Set the left widget of the header panel
	 * 
	 * @param newLeft the widget that should be displayed on the left side
	 */
	@UiChild(limit = 1, tagname = "left")
	public void setLeftWidget(Widget newLeft) {
		if (left != null) {
			container.remove(left);
		}

		left = newLeft;

		if (left != null) {
			left.addStyleName(this.css.left());
			container.add(left);
		}
	}

	/**
	 * Set the right widget of the header panel
	 * 
	 * @param newRight the widget that should be displayed on the right side
	 */
	@UiChild(limit = 1, tagname = "right")
	public void setRightWidget(Widget newRight) {
		if (right != null) {
			container.remove(right);
		}

		right = newRight;

		if (right != null) {
			right.addStyleName(this.css.right());
			container.add(right);
		}
	}

}
