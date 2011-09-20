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
import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.TabBarCss;
import de.kurka.gwt.mobile.ui.client.widget.TabBarButton;

/**
 * @author Daniel Kurka
 * 
 */
public class TabBar extends Composite implements HasSelectionHandlers<Integer> {

	private FlowPanel container;
	private LinkedList<TabBarButton> children;
	private LinkedList<HandlerRegistration> handlers = new LinkedList<HandlerRegistration>();
	protected final TabBarCss css;

	public TabBar() {
		this(MGWTStyle.getDefaultClientBundle().getTabBarCss());
	}

	public TabBar(TabBarCss css) {
		this.css = css;
		css.ensureInjected();
		children = new LinkedList<TabBarButton>();
		container = new FlowPanel();
		container.setStylePrimaryName(css.tabbar());
		initWidget(container);
	}

	private class InternalTouchHandler implements SimpleTouchHandler {

		private final TabBarButton button;

		public InternalTouchHandler(TabBarButton button) {
			this.button = button;

		}

		@Override
		public void onTouch() {
			setSelectedButton(getIndexForWidget(button));
		}

	}

	public void add(TabBarButton w) {
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

	private int getIndexForWidget(TabBarButton w) {
		return children.indexOf(w);
	}

	public boolean remove(TabBarButton w) {
		children.remove(w);
		int indexForWidget = getIndexForWidget(w);
		if (indexForWidget != -1) {
			handlers.get(indexForWidget).removeHandler();
			handlers.remove(indexForWidget);
		}

		return container.remove(w);

	}

	public void setSelectedButton(int index) {
		setSelectedButton(index, false);
	}

	public void setSelectedButton(int index, boolean suppressEvent) {
		if (index < 0) {
			throw new IllegalArgumentException("invalud index");
		}

		if (index >= children.size()) {
			throw new IllegalArgumentException("invalud index");
		}
		int count = 0;
		for (TabBarButton button : children) {
			if (count == index) {
				button.setSelected(true);
			} else {
				button.setSelected(false);
			}
			count++;
		}
		if (!suppressEvent)
			SelectionEvent.fire(this, Integer.valueOf(index));
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	/**
	 * @param index
	 */
	public void remove(int index) {
		TabBarButton w = getWidgetForIndex(index);
		remove(w);
	}

	/**
	 * @param index
	 * @return
	 */
	private TabBarButton getWidgetForIndex(int index) {
		return children.get(index);
	}

}
