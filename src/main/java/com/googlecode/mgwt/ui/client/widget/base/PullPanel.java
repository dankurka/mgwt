/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.base;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.event.HasPullHandlers;
import com.googlecode.mgwt.ui.client.widget.event.PullReleasedEvent;
import com.googlecode.mgwt.ui.client.widget.event.PullReleasedHandler;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEvent;
import com.googlecode.mgwt.ui.client.widget.event.ScrollHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartEvent;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartHandler;

/**
 * A panel that has scrollable content with a header, that can be pulled
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class PullPanel extends Composite implements HasWidgets, HasPullHandlers {
	/**
	 * A header for a pull panel
	 * 
	 * @author Daniel Kurka
	 * 
	 */
	public interface PullHeader extends IsWidget {
		/**
		 * called when a scroll starts
		 * 
		 * @param state
		 *            the current state of the pull panel
		 */
		public void scrollStart(State state);

		/**
		 * continously called when scrolling
		 * 
		 * @param state
		 *            the state of the pull panel
		 * @param positionY
		 *            the current scroll position
		 */
		public void onScroll(State state, int positionY);

		/**
		 * called when a scroll ends
		 * 
		 * @param state
		 *            the current state of the pull panel
		 * @param positionY
		 *            the end scroll position
		 * @param duration
		 *            the duration to scroll took
		 */
		void onScrollEnd(State state, int positionY, int duration);

		/**
		 * get the height of the pullheader
		 * 
		 * @return the height of the pull header
		 */
		public int getHeight();

		/**
		 * get the position in px that triggers a state change
		 * 
		 * @return the position in px that triggers the state change
		 */
		public int getStateSwitchPosition();

		/**
		 * set the html of a pull header
		 * 
		 * @param html
		 *            the html as String
		 */
		public void setHTML(String html);
	}

	private class ScrollListener implements ScrollHandler, ScrollEndHandler, ScrollStartHandler {

		public ScrollListener() {

		}

		@Override
		public void onScrollEnd(ScrollEndEvent event) {

			header.onScrollEnd(getState(), event.getCurrentY(), event.getDuration());
			if (getState() == State.PULL_RELEASE) {
				event.preventDefault();
				scroll.setOffset(0, 0);
				startLoading();

			} else {
				scroll.setOffset(0, -header.getHeight());
			}

		}

		@Override
		public void onScroll(ScrollEvent event) {
			if (event.getY() > header.getStateSwitchPosition()) {
				setState(State.PULL_RELEASE);
			} else {
				setState(State.NO_ACTION);

			}
			header.onScroll(getState(), event.getY());

		}

		@Override
		public void onStartScroll(ScrollStartEvent event) {
			setState(State.NO_ACTION);
			header.scrollStart(getState());
		}
	}

	protected ScrollPanel scroll;
	private FlowPanel container;
	private ScrollListener scrollListener;
	private final PullToRefreshCss css;

	private State state;
	protected final PullHeader header;

	/**
	 * Construct a pull panel with a given header
	 * 
	 * @param pullHeader
	 *            the header to use for this panel
	 */
	public PullPanel(PullHeader pullHeader) {
		this(MGWTStyle.getDefaultClientBundle().getPullToRefreshCss(), pullHeader);
	}

	/**
	 * Construct a pull panel with a given header an css.
	 * 
	 * @param css
	 *            the css to use
	 * @param header
	 *            the header to use for this panel
	 */
	public PullPanel(PullToRefreshCss css, PullHeader header) {
		this.css = css;
		this.header = header;
		this.css.ensureInjected();
		state = State.NO_ACTION;
		scroll = new ScrollPanel();

		initWidget(scroll);

		scroll.addStyleName(MGWTStyle.getDefaultClientBundle().getLayoutCss().fillPanelExpandChild());

		FlowPanel main = new FlowPanel();
		scroll.setWidget(main);
		scroll.setOffset(0, -header.getHeight());
		scroll.setScrollingEnabledX(false);
		main.getElement().getStyle().setPosition(Position.RELATIVE);

		scrollListener = new ScrollListener();
		scroll.addScrollhandler(scrollListener);
		scroll.addScrollEndHandler(scrollListener);
		scroll.addScrollStartHandler(scrollListener);

		main.add(header);

		container = new FlowPanel();
		main.add(container);

	}

	/** {@inheritDoc} */
	@Override
	protected void onAttach() {
		super.onAttach();

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public void add(Widget w) {
		container.add(w);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.event.HasPullHandlers#addPullReleasedHandler(com.googlecode.mgwt.ui.client.widget.event.PullReleasedHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addPullReleasedHandler(PullReleasedHandler handler) {
		return addHandler(handler, PullReleasedEvent.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.event.HasPullHandlers#addPullStateChangedHandler(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addPullStateChangedHandler(PullStateChangedHandler handler) {
		return addHandler(handler, PullStateChangedEvent.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	/** {@inheritDoc} */
	@Override
	public void clear() {
		container.clear();

	}

	/**
	 * get the state of the pull panel
	 * 
	 * @return the state of the panel
	 */
	public State getState() {
		return state;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
	 */
	/** {@inheritDoc} */
	@Override
	public Iterator<Widget> iterator() {
		return container.iterator();
	}

	/**
	 * refresh the pull panel. Recalculate content size and adjust scrollbars
	 * (needs to be called after content size has changed)
	 */
	public void refresh() {
		scroll.refresh();

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public boolean remove(Widget w) {
		return container.remove(w);
	}

	/**
	 * show the header of the panel
	 * 
	 * @param show
	 *            true to show otherwise hide
	 */
	public void showHeader(boolean show) {
		if (show) {
			scroll.setOffset(0, 0);
		} else {
			scroll.setOffset(0, -header.getHeight());
		}

	}

	/**
	 * <p>
	 * Setter for the field <code>state</code>.
	 * </p>
	 * 
	 * @param state
	 *            a
	 *            {@link com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State}
	 *            object.
	 */
	protected void setState(State state) {
		State lastState = this.state;
		this.state = state;

		if (lastState != this.state) {
			fireStateChangedEvent(this.state);
		}

	}

	private void fireStateChangedEvent(State state) {
		fireEvent(new PullStateChangedEvent(state));
	}

	private void startLoading() {
		fireEvent(new PullReleasedEvent());

	}

}
