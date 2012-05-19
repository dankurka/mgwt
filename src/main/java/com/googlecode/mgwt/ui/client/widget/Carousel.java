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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
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
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollRefreshEvent;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class Carousel extends Composite implements HasWidgets, HasSelectionHandlers<Integer> {

	private static class CarouselIndicatorContainer extends Composite {
		private FlowPanel main;
		private final CarouselCss css;
		private ArrayList<CarouselIndicator> indicators;
		private int selectedIndex;

		public CarouselIndicatorContainer(CarouselCss css, int numberOfPages) {
			if (numberOfPages < 0) {
				throw new IllegalArgumentException();
			}
			this.css = css;
			main = new FlowPanel();
			initWidget(main);

			main.addStyleName(this.css.indicatorContainer());

			indicators = new ArrayList<Carousel.CarouselIndicator>(numberOfPages);
			selectedIndex = 0;

			for (int i = 0; i < numberOfPages; i++) {
				CarouselIndicator indicator = new CarouselIndicator(css);
				indicators.add(indicator);
				main.add(indicator);

			}

			setSelectedIndex(selectedIndex);
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

			addStyleName(css.indicator());

		}

		public void setActive(boolean active) {
			if (active) {
				addStyleName(css.indicatorActive());
			} else {
				removeStyleName(css.indicatorActive());
			}
		}
	}

	private static class WidgetHolder extends FlowPanel {

		public WidgetHolder(CarouselCss css) {
			addStyleName(css.carouselHolder());
		}

		@Override
		public void add(Widget w) {
			super.add(w);
			if (w instanceof ScrollPanel) {
				w.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
			}
		}

	}

	private FlowPanel main;
	private final CarouselCss css;
	private ScrollPanel scrollPanel;
	private FlowPanel container;
	private CarouselIndicatorContainer carouselIndicatorContainer;

	private int currentPage;

	private Map<Widget, Widget> childToHolder;
	private com.google.web.bindery.event.shared.HandlerRegistration refreshHandler;

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
		scrollPanel.setAutoHandleResize(false);

		currentPage = 0;

		scrollPanel.addScrollEndHandler(new ScrollEndEvent.Handler() {

			@Override
			public void onScrollEnd(ScrollEndEvent event) {
				int page;

				page = scrollPanel.getCurrentPageX();

				carouselIndicatorContainer.setSelectedIndex(page);
				currentPage = page;
				SelectionEvent.fire(Carousel.this, currentPage);

			}
		});

		MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

			@Override
			public void onOrientationChanged(OrientationChangeEvent event) {
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {

					@Override
					public void execute() {
						refresh();

					}
				});

			}
		});

		addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				System.out.println("selection event!");
				carouselIndicatorContainer.setSelectedIndex(currentPage);

			}
		});

		if (MGWT.getOsDetection().isDesktop()) {
			Window.addResizeHandler(new ResizeHandler() {

				@Override
				public void onResize(ResizeEvent event) {
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {

						@Override
						public void execute() {
							refresh();

						}
					});

				}
			});
		}

	}

	@Override
	public void add(Widget w) {

		WidgetHolder widgetHolder = new WidgetHolder(css);
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

	@Override
	protected void onAttach() {
		super.onAttach();
		refresh();
	}

	public void refresh() {

		int widgetCount = container.getWidgetCount();

		double sizeFactor = 100d / widgetCount;

		for (int i = 0; i < widgetCount; i++) {
			container.getWidget(i).setWidth(sizeFactor + "%");
		}

		container.setWidth((widgetCount * 100) + "%");

		scrollPanel.setScrollingEnabledX(true);
		scrollPanel.setScrollingEnabledY(false);

		scrollPanel.setShowScrollBarX(false);
		scrollPanel.setShowScrollBarY(false);

		if (carouselIndicatorContainer != null) {
			carouselIndicatorContainer.removeFromParent();

		}

		carouselIndicatorContainer = new CarouselIndicatorContainer(css, widgetCount);

		main.add(carouselIndicatorContainer);

		if (currentPage >= widgetCount) {
			currentPage = widgetCount - 1;
		}

		carouselIndicatorContainer.setSelectedIndex(currentPage);

		scrollPanel.refresh();

		refreshHandler = scrollPanel.addScrollRefreshHandler(new ScrollRefreshEvent.Handler() {

			@Override
			public void onScrollRefresh(ScrollRefreshEvent event) {
				refreshHandler.removeHandler();
				refreshHandler = null;

				scrollPanel.scrollToPage(currentPage, 0, 0);

			}
		});

	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

}
