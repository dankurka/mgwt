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

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.PopoverCss;

/**
 * @author Daniel Kurka
 * 
 */
public class IpadMenu extends Composite {

	private FlowPanel main;

	private FlowPanel menuArrow;

	private FlowPanel content;

	public IpadMenu() {
		this(MGWTStyle.getDefaultClientBundle().getPopoverCss());
	}

	public IpadMenu(PopoverCss css) {
		main = new FlowPanel();
		css.ensureInjected();
		initWidget(main);

		setStylePrimaryName(css.main());

		//arrow
		menuArrow = new FlowPanel();
		menuArrow.setStylePrimaryName(css.arrow());
		main.add(menuArrow);

		content = new FlowPanel();
		content.addStyleName(css.content());
		content.getElement().getStyle().setOverflow(Overflow.HIDDEN);
		main.add(content);

	}

	public FlowPanel getBody() {
		return content;
	}
}
