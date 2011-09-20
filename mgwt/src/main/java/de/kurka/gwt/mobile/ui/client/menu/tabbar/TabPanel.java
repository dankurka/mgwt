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
package de.kurka.gwt.mobile.ui.client.menu.tabbar;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.TabBarCss;
import de.kurka.gwt.mobile.ui.client.widget.LayoutPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class TabPanel extends Composite {

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
}
