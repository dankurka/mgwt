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
package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.collection.shared.LightArrayInt;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellListAppearance.CellListCss;
import com.googlecode.mgwt.ui.client.widget.list.celllist.GroupingCellList.CellGroup;
import com.googlecode.mgwt.ui.client.widget.list.celllist.GroupingCellListAppearance.GroupingListCss;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.IsFlexible;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollAnimationMoveEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollMoveEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollRefreshEvent;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This widget uses a {@link GroupingCellList} to render children within groups.
 * On top of that it provides a fast selection through a bar on the right by
 * displaying symbols for group names.
 * 
 * It also animates a header group so that the user can always see which group
 * he is currently scrolling in.
 * 
 * @author Daniel Kurka
 * 
 * @param <G> type of the group
 * @param <T> type of the children1
 */
public class HeaderList<G, T> extends Composite implements IsFlexible {

	private static class SelectionBar<G, T> extends TouchWidget implements TouchHandler, HasSelectionHandlers<Integer> {

		private int selectedIndex;
		private int renderedEntries;
		private final GroupingListCss css;

		public SelectionBar(GroupingListCss css) {
			this.css = css;
			setElement(Document.get().createULElement());

			addStyleName(css.selectionBar());

			selectedIndex = 0;

			mapping = new HashMap<Integer, Integer>();

			addTouchHandler(this);
		}

		@Override
		public void onTouchStart(TouchStartEvent event) {
			calculateSelection(event.getTouches().get(0).getPageY());
			addStyleName(css.selectionBarActive());

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			calculateSelection(event.getTouches().get(0).getPageY());
			if (MGWT.getOsDetection().isAndroid()) {
				event.preventDefault();
			}
		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {
			removeStyleName(css.selectionBarActive());

		}

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			removeStyleName(css.selectionBarActive());

		}

		protected void calculateSelection(int y) {

			int absoluteTop = getElement().getAbsoluteTop();
			int absoluteBottom = getElement().getAbsoluteBottom();

			int normalized_y = y - absoluteTop;
			int height = absoluteBottom - absoluteTop;

			int index = (normalized_y * renderedEntries) / height;

			if (index != selectedIndex) {
				SelectionEvent.fire(this, mapping.get(index));
			}

			selectedIndex = index;

		}

		@Override
		public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
			return addHandler(handler, SelectionEvent.getType());
		}

		public void render(List<CellGroup<G, T>> list) {

			mapping.clear();
			StringBuffer buffer = new StringBuffer();

			renderedEntries = 0;
			int target = 0;
			for (CellGroup<?, ?> cellGroup : list) {
				buffer.append("<li>" + cellGroup.getKey() + "</li>");

				mapping.put(renderedEntries, target);

				if (!cellGroup.getMember().isEmpty()) {
					target++;
				}

				renderedEntries++;

			}

			getElement().setInnerHTML(buffer.toString());
		}

		private Map<Integer, Integer> mapping;

	}

	private static class MovingHeader extends Widget {

		public MovingHeader(CellListCss listCss, GroupingListCss css) {

			setElement(Document.get().createDivElement());
			addStyleName(listCss.listHeadElement());
			addStyleName(css.movingHeader());

		}

		public void setHTML(String string) {
			getElement().setInnerHTML(string);

		}
	}

	private FlexPanel main;
	private ScrollPanel scrollPanel;
	private MovingHeader movingHeader;
	private LightArrayInt pagesY;

	private int currentPage;
	private final GroupingCellList<G, T> cellList;

	private boolean needReset = false;

	private int lastPage = -1;
	private SelectionBar<G, T> selectionBar;
	private List<CellGroup<G, T>> list;

	private boolean headerVisible = true;
  private GroupingListCss css;

	/**
	 * Construct a HeaderList
	 * 
	 * @param cellList the cell list that renders its children inside this
	 *            widget.
	 */
	public HeaderList(GroupingCellList<G, T> cellList) {
		this(cellList, GroupingCellList.DEFAULT_APPEARANCE);
	}

	/**
	 * Construct a cell list with a given cell list and css
	 * 
	 * @param cellList the cell list that renders its children inside this
	 *            widget
	 * @param css the css to use
	 */
	public HeaderList(GroupingCellList<G, T> cellList, GroupingCellListAppearance appearance) {

		this.cellList = cellList;
		this.css = appearance.groupCss();

		css.ensureInjected();
		main = new FlexPanel();
		initWidget(main);

		main.addStyleName(css.groupingHeaderList());

		currentPage = 0;

		selectionBar = new SelectionBar<G, T>(css);
		main.add(selectionBar);

		scrollPanel = new ScrollPanel();

		scrollPanel.setWidget(cellList);
		scrollPanel.setSnapSelector(cellList.getHeaderSelector());

		scrollPanel.setShowScrollBarX(false);

		main.add(scrollPanel);
		movingHeader = new MovingHeader(cellList.getListCss(), css);
		main.add(movingHeader);

		scrollPanel.addScrollRefreshHandler(new ScrollRefreshEvent.Handler() {

			@Override
			public void onScrollRefresh(ScrollRefreshEvent event) {
				pagesY = scrollPanel.getPagesY();
			}
		});

		selectionBar.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {

				scrollPanel.scrollToPage(0, event.getSelectedItem(), 0);
				currentPage = event.getSelectedItem();
				updateCurrentPage(scrollPanel.getY());
				updateHeaderPositionAndTitle(scrollPanel.getY());

			}
		});

		scrollPanel.addScrollMoveHandler(new ScrollMoveEvent.Handler() {

			@Override
			public void onScrollMove(ScrollMoveEvent event) {
				updateCurrentPage(scrollPanel.getY());
				updateHeaderPositionAndTitle(scrollPanel.getY());

			}
		});

		scrollPanel.addScrollAnimationMoveHandler(new ScrollAnimationMoveEvent.Handler() {

			@Override
			public void onScrollAnimationMove(ScrollAnimationMoveEvent event) {
				updateCurrentPage(scrollPanel.getY());
				updateHeaderPositionAndTitle(scrollPanel.getY());

			}
		});
	}

  /**
   * Render the list of models
   * 
   * @param list the models to render
   */
	public void render(List<CellGroup<G, T>> list) {
		this.list = list;
		selectionBar.render(list);
		cellList.renderGroup(list);

		scrollPanel.refresh();

	}

	private void updateCurrentPage(int y) {
		int i;
		for (i = 0; i < pagesY.length(); i++) {
			int page_start = pagesY.get(i);
			if (page_start < y) {
				break;
			}
		}

		currentPage = i - 1;

		if (currentPage < 0)
			currentPage = 0;
		if (currentPage > pagesY.length() - 1) {
			currentPage = pagesY.length() - 1;
		}

	}

	private void updateHeaderPositionAndTitle(int y) {

		int headerHeight = movingHeader.getOffsetHeight();

		if (lastPage != currentPage) {
			lastPage = currentPage;
			int modelIndex = cellList.getMapping().get(currentPage);
			movingHeader.setHTML(cellList.renderGroupHeader(list.get(modelIndex).getGroup()));
		}

		if (y > 0) {
			if (headerVisible) {
				headerVisible = false;
				movingHeader.setVisible(false);
			}

			return;
		} else {
			if (!headerVisible) {
				headerVisible = true;
				movingHeader.setVisible(true);
			}

		}

		if (currentPage + 1 < pagesY.length()) {

			int nextHeader = pagesY.get(currentPage + 1);

			if (nextHeader + headerHeight - y > 0) {
				int move = -(nextHeader + headerHeight - y);
				CssUtil.translate(movingHeader.getElement(), 0, move);

				needReset = true;
			} else {
				if (needReset) {
					needReset = false;
					CssUtil.translate(movingHeader.getElement(), 0, 0);
				}

			}
		}
	}

}
