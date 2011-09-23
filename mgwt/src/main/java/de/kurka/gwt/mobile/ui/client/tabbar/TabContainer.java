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
package de.kurka.gwt.mobile.ui.client.tabbar;

import java.util.LinkedList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.ui.client.widget.ScrollPanel;

/**
 * @author Daniel Kurka
 * 
 * 
 */
public class TabContainer extends Composite {

	private ScrollPanel container;
	private LinkedList<Widget> children = new LinkedList<Widget>();
	private Widget activeWidget;

	public TabContainer() {
		container = new ScrollPanel();
		initWidget(container);

	}

	public void add(Widget w) {
		if (children.size() == 0) {
			container.setWidget(w);
			activeWidget = w;
		}
		children.add(w);

	}

	public void setScrollingEnabledX(boolean enabled) {
		container.setScrollingEnabledX(enabled);
	}

	public void setScrollingEnabledY(boolean enabled) {
		container.setScrollingEnabledY(enabled);
	}

	public void clear() {
		children.clear();
		container.setWidget(null);
		activeWidget = null;

	}

	public void setSelectedChild(int index) {
		activeWidget = children.get(index);
		container.setWidget(activeWidget);
	}

	public boolean remove(Widget w) {
		int index = getChildIndex(w);
		boolean remove = children.remove(w);
		if (w == activeWidget) {
			activeWidget = null;
			if (index - 1 >= 0) {
				setSelectedChild(index);
			}
		}

		return remove;
	}

	/**
	 * @param w
	 */
	public int getChildIndex(Widget w) {

		return children.indexOf(w);

	}

	/**
	 * @param childIndex
	 */
	public void remove(int childIndex) {
		Widget w = getWidgetForIndex(childIndex);
		remove(w);
	}

	/**
	 * @param childIndex
	 * @return
	 */
	private Widget getWidgetForIndex(int childIndex) {
		return children.get(childIndex);
	}
}
