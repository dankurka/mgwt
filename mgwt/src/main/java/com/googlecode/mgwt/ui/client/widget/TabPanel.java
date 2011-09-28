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

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.tabbar.TabBar;
import com.googlecode.mgwt.ui.client.tabbar.TabContainer;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;


/**
 * @author Daniel Kurka
 * 
 */
public class TabPanel extends Composite implements HasSelectionHandlers<Integer> {

	private LayoutPanel container;
	private TabContainer tabContainer;
	private TabBar tabBar;

	public TabPanel() {
		this(MGWTStyle.getDefaultClientBundle().getTabBarCss());
	}

	public TabPanel(TabBarCss css) {
		container = new LayoutPanel();
		initWidget(container);
		container.addStyleName(LayoutPanel.LayoutPanelBundle.INSTANCE.getLayoutCss().mainChild());

		tabContainer = new TabContainer();
		container.add(tabContainer);
		tabContainer.addStyleName(LayoutPanel.LayoutPanelBundle.INSTANCE.getLayoutCss().mainChild());
		tabBar = new TabBar(css);

		container.add(tabBar);

		tabBar.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				tabContainer.setSelectedChild(event.getSelectedItem());

			}
		});

	}

	public void setScrollingEnabledX(boolean enabled) {
		tabContainer.setScrollingEnabledX(enabled);
	}

	public void setScrollingEnabledY(boolean enabled) {
		tabContainer.setScrollingEnabledY(enabled);
	}

	public void setSelectedChild(int index) {
		tabBar.setSelectedButton(index, true);
		tabContainer.setSelectedChild(index);
	}

	public void add(TabBarButton button, Widget child) {
		tabContainer.add(child);
		tabBar.add(button);
	}

	public void remove(int index) {
		tabContainer.remove(index);
		tabBar.remove(index);
	}

	public void remove(Widget w) {
		int childIndex = tabContainer.getChildIndex(w);
		tabContainer.remove(childIndex);
		tabBar.remove(childIndex);
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return tabBar.addSelectionHandler(handler);
	}
}
