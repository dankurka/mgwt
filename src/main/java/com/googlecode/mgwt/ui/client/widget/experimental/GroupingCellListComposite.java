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
package com.googlecode.mgwt.ui.client.widget.experimental;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.widget.RoundPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class GroupingCellListComposite extends Composite {

	private static class LeftBar extends TouchWidget implements TouchHandler, HasSelectionHandlers<Integer> {
		private String[] labels;

		private int selectedIndex;

		public LeftBar() {
			setElement(Document.get().createULElement());

			getElement().getStyle().setPosition(Position.ABSOLUTE);
			getElement().getStyle().setTop(20, Unit.PX);
			getElement().getStyle().setRight(5, Unit.PX);
			getElement().getStyle().setBottom(20, Unit.PX);
			getElement().getStyle().setZIndex(1);
			getElement().getStyle().setProperty("display", "-webkit-box");
			getElement().getStyle().setProperty("WebkitBoxOrient", "vertical");

			labels = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };

			StringBuffer buffer = new StringBuffer();

			for (int i = 0; i < labels.length; i++) {
				buffer.append("<li style='-webkit-box-flex: 1'>" + labels[i] + "</li>");
			}
			getElement().setInnerHTML(buffer.toString());
			selectedIndex = 0;

			addTouchHandler(this);
		}

		@Override
		public void onTouchStart(TouchStartEvent event) {
			calculateSelection(event.getTouches().get(0).getPageY());

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			calculateSelection(event.getTouches().get(0).getPageY());
		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {

		}

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {

		}

		protected void calculateSelection(int y) {

			int absoluteTop = getElement().getAbsoluteTop();
			int absoluteBottom = getElement().getAbsoluteBottom();

			int normalized_y = y - absoluteTop;
			int height = absoluteBottom - absoluteTop;

			int index = (normalized_y * labels.length) / height;

			if (index != selectedIndex) {
				SelectionEvent.fire(this, index);
			}

			selectedIndex = index;

		}

		@Override
		public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
			return addHandler(handler, SelectionEvent.getType());
		}
	}

	private FlowPanel main;
	private ScrollPanel scrollPanel;

	public GroupingCellListComposite() {
		main = new FlowPanel();
		initWidget(main);

		getElement().getStyle().setProperty("display", "-webkit-box");
		getElement().getStyle().setPosition(Position.RELATIVE);

		LeftBar leftBar = new LeftBar();
		main.add(leftBar);

		scrollPanel = new ScrollPanel();
		scrollPanel.getElement().getStyle().setProperty("WebkitBoxFlex", "1");
		main.add(scrollPanel);

		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setHeight("2000px");
		scrollPanel.setWidget(roundPanel);

	}
}
