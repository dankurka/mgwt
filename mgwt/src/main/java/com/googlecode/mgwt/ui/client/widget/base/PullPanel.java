package com.googlecode.mgwt.ui.client.widget.base;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Position;
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

public class PullPanel extends Composite implements HasWidgets, HasPullHandlers {
	private ScrollPanel scroll;
	private FlowPanel container;
	private ScrollListener scrollListener;
	private final PullToRefreshCss css;

	private State state;
	protected final PullHeader header;

	public PullPanel(PullHeader pullHeader) {
		this(MGWTStyle.getDefaultClientBundle().getPullToRefreshCss(), pullHeader);
	}

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

	@Override
	public void add(Widget w) {
		container.add(w);

	}

	@Override
	public void clear() {
		container.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return container.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return container.remove(w);
	}

	public void refresh() {
		scroll.refresh();
		scroll.setOffset(0, -header.getHeight());
	}

	public State getState() {
		return state;
	}

	private class ScrollListener implements ScrollHandler, ScrollEndHandler, ScrollStartHandler {

		public ScrollListener() {

		}

		@Override
		public void onScrollEnd(ScrollEndEvent event) {
			event.preventDefault();
			header.onScrollEnd(getState(), event.getCurrentY(), event.getDuration());
			if (getState() == State.PULL_RELEASE) {
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

	public interface PullHeader extends IsWidget {
		public void scrollStart(State state);

		public void onScroll(State state, int positionY);

		void onScrollEnd(State state, int positionY, int duration);

		public int getHeight();

		public int getStateSwitchPosition();

		public void setHTML(String html);
	}

	@Override
	public com.google.web.bindery.event.shared.HandlerRegistration addPullReleasedHandler(PullReleasedHandler handler) {
		return addHandler(handler, PullReleasedEvent.getType());
	}

	@Override
	public com.google.web.bindery.event.shared.HandlerRegistration addPullStateChangedHandler(PullStateChangedHandler handler) {
		return addHandler(handler, PullStateChangedEvent.getType());
	}

}
