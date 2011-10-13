package com.googlecode.mgwt.ui.client.widget.base;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasWidgets;
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
	private RefreshWidget refreshWidget;
	private final PullToRefreshCss css;

	public PullPanel() {
		this(MGWTStyle.getDefaultClientBundle().getPullToRefreshCss());
	}

	public PullPanel(PullToRefreshCss css) {
		this.css = css;
		this.css.ensureInjected();
		scroll = new ScrollPanel();

		initWidget(scroll);

		scroll.addStyleName(MGWTStyle.getDefaultClientBundle().getLayoutCss().fillPanelExpandChild());

		FlowPanel main = new FlowPanel();
		scroll.setWidget(main);
		scroll.setOffset(0, -40);
		scroll.setScrollingEnabledX(false);

		main.getElement().getStyle().setPosition(Position.RELATIVE);

		refreshWidget = new RefreshWidget(css);
		main.add(refreshWidget);

		scroll.addScrollhandler(refreshWidget);
		scroll.addScrollEndHandler(refreshWidget);
		scroll.addScrollStartHandler(refreshWidget);

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

	public void showArrow(boolean arrow) {
		if (arrow) {
			refreshWidget.arrow.addClassName(css.spinner());
			refreshWidget.arrow.removeClassName(css.arrow());

		} else {
			refreshWidget.arrow.removeClassName(css.spinner());
			refreshWidget.arrow.addClassName(css.arrow());
		}
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

		scroll.setOffset(0, -40);

	}

	public State getState() {
		return refreshWidget.state;
	}

	private class RefreshWidget extends Widget implements ScrollHandler, ScrollEndHandler, ScrollStartHandler {

		private Element main;

		private Element arrow;

		private Element textContainer;

		private int lastY;

		private State state;

		public RefreshWidget(PullToRefreshCss css) {

			state = State.NO_ACTION;

			main = DOM.createDiv();
			main.addClassName(css.pullToRefresh());
			setElement(main);

			arrow = DOM.createDiv();
			arrow.addClassName(css.arrow());
			main.appendChild(arrow);

			textContainer = DOM.createDiv();
			textContainer.addClassName(css.text());
			main.appendChild(textContainer);

		}

		@Override
		public void onScrollEnd(ScrollEndEvent event) {
			if (state == State.PULL_RELEASE) {
				showArrow(true);
				event.preventDefault();
				scroll.setOffset(0, 0);
				startLoading();

			} else {
				event.preventDefault();
				scroll.setOffset(0, -40);
				int degree = getRotation(event.getY());
				arrow.setAttribute("style", "-webkit-transform: rotate(" + degree + "deg) translateZ(0);-webkit-transition: all " + event.getDuration() + "ms linear;");

			}

		}

		protected int getRotation(int y) {
			int degree = (y - 30) * -10;
			if (degree < -90)
				degree = -90;
			if (degree > 90) {
				degree = 90;
			}
			return degree;

		}

		@Override
		public void onScroll(ScrollEvent event) {

			lastY = event.getY();

			int degree = getRotation(lastY);
			arrow.setAttribute("style", "-webkit-transform: rotate(" + degree + "deg) translateZ(0);");

			if (event.getY() > 40) {

				if (state != State.PULL_RELEASE) {
					state = State.PULL_RELEASE;
					fireStateChangedEvent(state);
				}
			} else {
				if (state != State.NO_ACTION) {
					state = State.NO_ACTION;
					fireStateChangedEvent(state);
				}

			}

		}

		@Override
		public void onStartScroll(ScrollStartEvent event) {
			arrow.removeClassName(css.spinner());
			arrow.addClassName(css.arrow());
			if (state != State.NO_ACTION) {
				state = State.NO_ACTION;
				fireStateChangedEvent(state);
			}
		}
	}

	private void fireStateChangedEvent(State state) {
		fireEvent(new PullStateChangedEvent(state));
	}

	private void startLoading() {
		fireEvent(new PullReleasedEvent());

	}

	public HasHTML getHeader() {
		return new HasHtmlWrapper(refreshWidget.textContainer);
	}

	private class HasHtmlWrapper implements HasHTML {

		private final Element element;

		public HasHtmlWrapper(Element element) {
			this.element = element;
		}

		@Override
		public String getText() {
			return element.getInnerText();
		}

		@Override
		public void setText(String text) {
			element.setInnerText(text);

		}

		@Override
		public String getHTML() {
			return element.getInnerHTML();
		}

		@Override
		public void setHTML(String html) {
			element.setInnerHTML(html);

		}

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
