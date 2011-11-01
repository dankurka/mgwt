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

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.event.HasScrollHandlers;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartHandler;
import com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl;

/**
 * @author Daniel Kurka
 * 
 */
public class ScrollPanel extends Composite implements HasWidgets, HasScrollHandlers {

	protected final ScrollPanelImpl impl = GWT.create(ScrollPanelImpl.class);

	public ScrollPanel() {
		initWidget(impl);
	}

	public void setWidget(IsWidget w) {
		impl.setWidget(w);
	}

	@Override
	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler) {
		return impl.addScrollStartHandler(handler);
	}

	@Override
	public HandlerRegistration addScrollhandler(ScrollHandler scrollHandler) {
		return impl.addScrollhandler(scrollHandler);
	}

	@Override
	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler) {
		return impl.addScrollEndHandler(handler);
	}

	/**
	 * Methods only exists to make scroll panel work with UiBinder @use
	 * {@link #setWidget(IsWidget)}
	 */
	@Override
	public void add(Widget w) {
		impl.add(w);

	}

	@Override
	public void clear() {
		impl.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return impl.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return impl.remove(w);
	}

	public void setOffset(int x, int y) {
		impl.setOffset(x, y);

	}

	public void setScrollingEnabledX(boolean enabled) {
		impl.setScrollingEnabledX(enabled);

	}

	public void refresh() {
		impl.refresh();

	}

	public void setScrollingEnabledY(boolean enabled) {
		impl.setScrollingEnabledY(enabled);

	}

	public void setUsePos(boolean android) {
		impl.setUsePos(android);

	}

}
