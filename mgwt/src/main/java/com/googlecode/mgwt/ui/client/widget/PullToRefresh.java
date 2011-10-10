package com.googlecode.mgwt.ui.client.widget;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.scroll.ScrollEndHandler;
import com.googlecode.mgwt.ui.client.widget.scroll.ScrollEvent;
import com.googlecode.mgwt.ui.client.widget.scroll.ScrollHandler;
import com.googlecode.mgwt.ui.client.widget.scroll.ScrollStartEvent;
import com.googlecode.mgwt.ui.client.widget.scroll.ScrollStartHandler;

/**
 * Experimental don`t use right now
 * 
 * @author Daniel Kurka
 * 
 */
public class PullToRefresh extends Composite implements HasWidgets {
	private ScrollPanel scroll;
	private FlowPanel container;
	private RefreshWidget refreshWidget;

	public PullToRefresh() {
		scroll = new ScrollPanel();

		initWidget(scroll);

		scroll.addStyleName(MGWTStyle.getDefaultClientBundle().getLayoutCss().fillPanelExpandChild());

		FlowPanel main = new FlowPanel();
		scroll.setWidget(main);

		main.getElement().getStyle().setPosition(Position.RELATIVE);

		refreshWidget = new RefreshWidget();
		main.add(refreshWidget);
		refreshWidget.getElement().getStyle().setPosition(Position.ABSOLUTE);
		refreshWidget.getElement().getStyle().setBottom(0, Unit.PX);
		refreshWidget.getElement().getStyle().setLeft(0, Unit.PX);
		refreshWidget.getElement().getStyle().setWidth(100, Unit.PCT);
		refreshWidget.getElement().getStyle().setHeight(40, Unit.PX);

		scroll.addScrollhandler(refreshWidget);
		scroll.addScrollEndHandler(refreshWidget);

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

	private class RefreshWidget extends FlowPanel implements ScrollHandler, ScrollEndHandler, ScrollStartHandler {
		private HTML html;

		private boolean startReload;

		public RefreshWidget() {
			html = new HTML("asdf");

			add(html);
		}

		@Override
		public void onScrollEnd(ScrollEndEvent event) {
			if (startReload) {
				startLoading();
				startReload = false;
			}
			html.setText("end: " + event.getX() + " " + event.getY());

		}

		@Override
		public void onScroll(ScrollEvent event) {
			if (event.getY() > 50) {
				html.setText("release to reload: " + event.getX() + " " + event.getY());
				startReload = true;
			} else {
				html.setText("scrolling: " + event.getX() + " " + event.getY());
				startReload = false;
			}

		}

		@Override
		public void onStartScroll(ScrollStartEvent event) {
			startReload = false;

		}
	}

	public void startLoading() {
		System.out.println("reload und so ");

	}
}
