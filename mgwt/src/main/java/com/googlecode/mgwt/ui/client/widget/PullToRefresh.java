package com.googlecode.mgwt.ui.client.widget;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class PullToRefresh extends Composite implements HasWidgets {
	private ScrollPanel scroll;
	private FlowPanel container;
	private RefreshWidget refreshWidget;

	public PullToRefresh() {
		scroll = new ScrollPanel();

		initWidget(scroll);

		FlowPanel main = new FlowPanel();
		scroll.setWidget(main);

		refreshWidget = new RefreshWidget();
		main.add(refreshWidget);

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

	private class RefreshWidget extends FlowPanel {
		public RefreshWidget() {
			add(new HTML("asdf"));
		}
	}
}
