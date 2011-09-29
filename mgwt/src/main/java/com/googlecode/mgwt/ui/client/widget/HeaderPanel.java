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
 * @author Daniel Kurka
 * 
 */
public class HeaderPanel extends Composite {

	private Widget left;
	private Widget right;
	private Widget title;

	private FlowPanel container;
	private final HeaderCss css;

	public HeaderPanel() {
		this(MGWTStyle.getDefaultClientBundle().getHeaderCss());
	}

	public HeaderPanel(HeaderCss css) {
		this.css = css;
		this.css.ensureInjected();
		container = new FlowPanel();
		container.setStylePrimaryName(this.css.headerPanel());

		initWidget(container);

	}

	public void setCenter(String text) {
		HTML wrapper = new HTML();
		wrapper.setHTML(text);
		setCenterWidget(wrapper);
	}

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
