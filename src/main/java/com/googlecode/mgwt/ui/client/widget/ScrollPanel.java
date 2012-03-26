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
 * A scroll panel that can handle touch input and has momentum
 * 
 * 
 * <h2>Styling</h2>
 * 
 * The DOM structure looks like:
 * 
 * <pre>
 * &lt;div class="mgwt-ScrollPanel">
 * 	&lt;yourChild class="mgwt-ScrollPanel-container">....&lt;/yourChild>
 * 	&lt;div class="mgwt-ScrollBar mgwt-ScrollBar-vertical">
 * 		&ltdiv class="mgwt-ScrollBar-Bar mgwt-ScrollBar-vertical">&lt;/div>
 * 	&lt;/div>
 * 	&lt;div class="mgwt-ScrollBar mgwt-ScrollBar-horizontal">
 * 		&ltdiv class="mgwt-ScrollBar-Bar mgwt-ScrollBar-horizontal">&lt;/div>
 * 	&lt;/div>
 * &lt;/div>
 * </pre>
 * 
 * The scrollbars maybe missing from the DOM if scrolling is disabled for a
 * certain direction.
 * 
 * @author Daniel Kurka
 * 
 */
public class ScrollPanel extends Composite implements HasWidgets, HasScrollHandlers {

	protected final ScrollPanelImpl impl = GWT.create(ScrollPanelImpl.class);

	/**
	 * Construct a ScrollPanel
	 */
	public ScrollPanel() {
		initWidget(impl);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * set the widget that needs scrolling
	 */
	@Override
	public void setWidget(Widget w) {
		impl.setWidget(w);
	}

	/**
	 * set the widget that needs scrolling
	 * 
	 * @param w the widget to scroll
	 */
	public void setWidget(IsWidget w) {
		impl.setWidget(w);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.event.HasScrollHandlers#addScrollStartHandler(com.googlecode.mgwt.ui.client.widget.event.ScrollStartHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler) {
		return impl.addScrollStartHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.event.HasScrollHandlers#addScrollhandler(com.googlecode.mgwt.ui.client.widget.event.ScrollHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addScrollhandler(ScrollHandler scrollHandler) {
		return impl.addScrollhandler(scrollHandler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.event.HasScrollHandlers#addScrollEndHandler(com.googlecode.mgwt.ui.client.widget.event.ScrollEndHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler) {
		return impl.addScrollEndHandler(handler);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Methods only exists to make scroll panel work with UiBinder @use
	 * {@link #setWidget(IsWidget)}
	 */
	@Override
	public void add(Widget w) {
		impl.add(w);

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	/** {@inheritDoc} */
	@Override
	public void clear() {
		impl.clear();

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
	 */
	/** {@inheritDoc} */
	@Override
	public Iterator<Widget> iterator() {
		return impl.iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public boolean remove(Widget w) {
		return impl.remove(w);
	}

	/**
	 * Set the offset that the scroll panel should use
	 * 
	 * This is useful for hiding parts of the child widget
	 * 
	 * @param x the offset in x-axis
	 * @param y the offset in y-axis
	 */
	public void setOffset(int x, int y) {
		impl.setOffset(x, y);

	}

	/**
	 * Should scrolling in x-axis be enabled
	 * 
	 * @param enabled true to enable
	 */
	public void setScrollingEnabledX(boolean enabled) {
		impl.setScrollingEnabledX(enabled);

	}

	/**
	 * Refresh the scroll panel
	 * 
	 * This method needs to be called if the content of the child widget has
	 * changed without calling {@link #setWidget(IsWidget)}
	 * 
	 * ScrollPanel needs to recalculate sizes.
	 */
	public void refresh() {
		impl.refresh();

	}

	/**
	 * Should scrolling in y-axis be enabled
	 * 
	 * @param enabled true to enable
	 */
	public void setScrollingEnabledY(boolean enabled) {
		impl.setScrollingEnabledY(enabled);

	}

	/**
	 * Use position absolute instead of -webkit-translate
	 * 
	 * This is required on android if the scrolling area contains input elements
	 * 
	 * default: false
	 * 
	 * @param android a boolean.
	 */
	public void setUsePos(boolean android) {
		impl.setUsePos(android);

	}

	public void scrollTo(int x, int y) {
		impl.scrollTo(x, y, 1);
	}

}
