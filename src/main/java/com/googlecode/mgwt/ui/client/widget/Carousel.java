/*
 * Copyright 2012 Daniel Kurka
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.CarouselCss;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class Carousel extends Composite implements HasWidgets, HasSelectionHandlers<Integer> {

	private static class CarouselIndicatorContainer extends Composite {
		private FlowPanel main;
		private final CarouselCss css;
		private LinkedList<CarouselIndicator> indicators;
		private int selectedIndex;

		public CarouselIndicatorContainer(CarouselCss css, int numberOfPages) {
			if (numberOfPages < 0) {
				throw new IllegalArgumentException();
			}
			this.css = css;
			main = new FlowPanel();
			initWidget(main);

			indicators = new LinkedList<Carousel.CarouselIndicator>();
			selectedIndex = -1;

			for (int i = 0; i < numberOfPages; i++) {
				CarouselIndicator indicator = new CarouselIndicator(css);
				indicators.add(indicator);
				main.add(indicator);

			}
		}

		public void setSelectedIndex(int index) {
			if (selectedIndex != -1) {
				indicators.get(selectedIndex).setActive(false);
			}
			selectedIndex = index;
			if (selectedIndex != -1) {
				indicators.get(selectedIndex).setActive(true);
			}
		}
	}

	private static class CarouselIndicator extends TouchWidget {
		private final CarouselCss css;

		public CarouselIndicator(CarouselCss css) {
			this.css = css;
			setElement(DOM.createDiv());
		}

		public void setActive(boolean active) {
			if (active) {
				addStyleName(css.indicatorActive());
			} else {
				removeStyleName(css.indicatorActive());
			}
		}
	}

	private FlowPanel main;
	private final CarouselCss css;
	private ScrollPanel scrollPanel;
	private FlowPanel container;
	private CarouselIndicatorContainer carouselIndicatorContainer;
	private DIRECTION direction;
	private int currentPage;

	private Map<Widget, Widget> childToHolder;

	public Carousel() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getCarouselCss());
	}

	public Carousel(CarouselCss css) {
		this.css = css;
		this.css.ensureInjected();

		childToHolder = new HashMap<Widget, Widget>();
		main = new FlowPanel();
		initWidget(main);

		main.addStyleName(css.carousel());

		scrollPanel = new ScrollPanel();
		scrollPanel.addStyleName(css.carouselScroller());

		main.add(scrollPanel);

		container = new FlowPanel();
		container.addStyleName(css.carouselContainer());

		scrollPanel.setWidget(container);

		scrollPanel.setSnap(true);
		scrollPanel.setMomentum(false);
		scrollPanel.setShowScrollBarX(false);
		scrollPanel.setShowScrollBarY(false);
		scrollPanel.setScrollingEnabledY(true);

		currentPage = -1;

		direction = DIRECTION.HORIZONTAL;

		scrollPanel.addScrollEndHandler(new ScrollEndEvent.Handler() {

			@Override
			public void onScrollEnd(ScrollEndEvent event) {
				int page;
				if (direction == DIRECTION.HORIZONTAL) {
					page = scrollPanel.getCurrentPageX();
				} else {
					page = scrollPanel.getCurrentPageY();
				}

				carouselIndicatorContainer.setSelectedIndex(page);
				currentPage = page;
				SelectionEvent.fire(Carousel.this, currentPage);

			}
		});

		MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

			@Override
			public void onOrientationChanged(OrientationChangeEvent event) {
				// TODO set page

			}
		});

	}

	public enum DIRECTION {
		HORIZONTAL, VERTICAL
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
		if (direction == DIRECTION.HORIZONTAL) {
			container.removeStyleName(css.vertical());
			container.addStyleName(css.horizontal());
		} else {
			container.removeStyleName(css.horizontal());
			container.addStyleName(css.vertical());
		}
	}

	private static class WidgetHolder extends FlowPanel {

		public WidgetHolder() {

			getElement().getStyle().setPosition(Position.RELATIVE);

			getElement().getStyle().setProperty("display", "-webkit-box");
		}

		@Override
		public void add(Widget w) {
			super.add(w);
			if (w instanceof ScrollPanel) {
				w.getElement().getStyle().setProperty("WebkitBoxFlex", "1");
			}
		}

	}

	@Override
	public void add(Widget w) {

		WidgetHolder widgetHolder = new WidgetHolder();
		widgetHolder.add(w);

		childToHolder.put(w, widgetHolder);

		container.add(widgetHolder);

	}

	@Override
	public void clear() {
		container.clear();
		childToHolder.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		Set<Widget> keySet = childToHolder.keySet();
		return keySet.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		Widget holder = childToHolder.remove(w);
		if (holder != null) {
			return container.remove(w);
		}
		return false;

	}

	public void refresh() {

		int widgetCount = container.getWidgetCount();

		double width = 100d / widgetCount;

		for (int i = 0; i < widgetCount; i++) {
			container.getWidget(i).setWidth(width + "%");
		}

		container.setWidth((widgetCount * 100) + "%");

		if (carouselIndicatorContainer != null) {
			carouselIndicatorContainer.removeFromParent();

		}

		carouselIndicatorContainer = new CarouselIndicatorContainer(css, widgetCount);
		if (direction == DIRECTION.HORIZONTAL) {
			carouselIndicatorContainer.addStyleName(css.indicatorHorizontal());
		} else {
			carouselIndicatorContainer.addStyleName(css.indicatorVertical());
		}

		scrollPanel.refresh();

	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

}
