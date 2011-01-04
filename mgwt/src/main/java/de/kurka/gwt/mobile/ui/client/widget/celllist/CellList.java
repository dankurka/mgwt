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
package de.kurka.gwt.mobile.ui.client.widget.celllist;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Composite;

import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;
import de.kurka.gwt.mobile.ui.client.widget.TouchWidget;

/**
 * @author Daniel Kurka
 *
 */
public class CellList<T> extends Composite implements HasCellSelectedHandler {
	interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<li __idx=\"{0}\" class=\"{1}\">{2}</li>")
		SafeHtml li(int idx, String classes, SafeHtml cellContents);

	}

	private static final Template LI_TEMPLATE = GWT.create(Template.class);

	private static class UlTouchWidget extends TouchWidget {

		public UlTouchWidget() {
			super();
			setElement(Document.get().createULElement());
		}

	}

	private class InternalTouchHandler implements TouchStartHandler, TouchEndHandler, TouchMoveHandler, TouchCancelHandler {

		private boolean moved;
		private int index;
		private Element node;

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			moved = true;

		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {
			if (node != null)
				node.removeClassName("active");
			if (!moved && index != -1) {
				fireSelectionAtIndex(index);
			}
			node = null;

		}

		@Override
		public void onTouchStart(TouchStartEvent event) {
			if (node != null) {
				node.removeClassName("active");
			}
			moved = false;
			index = -1;
			// Get the event target.
			EventTarget eventTarget = event.getNativeEvent().getEventTarget();
			if (!Element.is(eventTarget)) {
				return;
			}
			Element target = eventTarget.cast();

			// Find cell
			String idxString = "";
			while ((target != null) && ((idxString = target.getAttribute("__idx")).length() == 0)) {
				target = target.getParentElement();
			}
			if (idxString.length() > 0) {

				try {
					index = Integer.parseInt(idxString);
					node = target;
					node.addClassName("active");
				} catch (Exception e) {

				}

			}

		}
	}

	private UlTouchWidget main;
	private InternalTouchHandler internalTouchHandler;

	private LinkedList<HandlerRegistration> handlers = new LinkedList<HandlerRegistration>();
	private final Cell<T> cell;

	public CellList(Cell<T> cell) {

		this.cell = cell;
		main = new UlTouchWidget();

		initWidget(main);

		internalTouchHandler = new InternalTouchHandler();

		setStylePrimaryName("mgwt-List");
	}

	private void fireSelectionAtIndex(int index) {
		fireEvent(new CellSelectedEvent(index));
	}

	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler) {
		return addHandler(cellSelectedHandler, CellSelectedEvent.getType());
	}

	@Override
	protected void onAttach() {

		super.onAttach();

		handlers.add(main.addTouchCancelHandler(internalTouchHandler));
		handlers.add(main.addTouchEndHandler(internalTouchHandler));
		handlers.add(main.addTouchStartHandler(internalTouchHandler));
		handlers.add(main.addTouchMoveHandler(internalTouchHandler));

	}

	@Override
	protected void onDetach() {

		super.onDetach();

		for (HandlerRegistration reg : handlers) {
			reg.removeHandler();
		}
		handlers.clear();
	}

	public void render(List<T> models) {

		SafeHtmlBuilder sb = new SafeHtmlBuilder();

		for (int i = 0; i < models.size(); i++) {

			T model = models.get(i);

			SafeHtmlBuilder cellBuilder = new SafeHtmlBuilder();

			String clazz = "";
			if (cell.canBeSelected(model)) {
				clazz = "group ";
			}

			cell.render(cellBuilder, model);

			sb.append(LI_TEMPLATE.li(i, clazz, cellBuilder.toSafeHtml()));
		}

		getElement().setInnerHTML(sb.toSafeHtml().asString());

	}
}
