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

import java.util.LinkedList;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;

/**
 * @author Daniel Kurka
 *
 */
public class TabBar extends Composite implements HasSelectionHandlers<Integer> {

	private FlowPanel container;
	private LinkedList<TabBarButtonBase> children;
	private LinkedList<HandlerRegistration> handlers = new LinkedList<HandlerRegistration>();

	public TabBar() {
		children = new LinkedList<TabBarButtonBase>();
		container = new FlowPanel();
		container.setStylePrimaryName("mgwt-TabBar");
		initWidget(container);
	}

	private class InternalTouchHandler implements SimpleTouchHandler {

		private final TabBarButtonBase button;

		public InternalTouchHandler(TabBarButtonBase button) {
			this.button = button;

		}

		@Override
		public void onTouch() {
			setSelectedButton(getIndexForWidget(button));
		}

	}

	public void add(TabBarButtonBase w) {
		if (children.size() == 0) {
			w.setSelected(true);
		}
		container.add(w);
		children.add(w);
		handlers.add(w.addSimpleTouchHandler(new InternalTouchHandler(w)));

	}

	public void clear() {
		container.clear();
		children.clear();
		handlers.clear();

	}

	private int getIndexForWidget(TabBarButtonBase w) {
		return children.indexOf(w);
	}

	public boolean remove(TabBarButtonBase w) {
		children.remove(w);
		int indexForWidget = getIndexForWidget(w);
		if (indexForWidget != -1) {
			handlers.get(indexForWidget).removeHandler();
			handlers.remove(indexForWidget);
		}

		return container.remove(w);

	}

	public void setSelectedButton(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("invalud index");
		}

		if (index >= children.size()) {
			throw new IllegalArgumentException("invalud index");
		}
		int count = 0;
		for (TabBarButtonBase button : children) {
			if (count == index) {
				button.setSelected(true);
			} else {
				button.setSelected(false);
			}
			count++;
		}
		SelectionEvent.fire(this, Integer.valueOf(index));
	}

	public void setBottom(boolean bottom) {
		if (bottom) {
			addStyleDependentName("bottom");
		} else {
			removeStyleDependentName("bottom");
		}
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

}
