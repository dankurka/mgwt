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
package de.kurka.gwt.mobile.ui.client.widget.base;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.ui.client.widget.TouchWidget;

/**
 * @author Daniel Kurka
 *  Date: 30.05.2010
 *
 */
public class MSearchBox extends Composite implements HasChangeHandlers, HasText, HasName, HasValue<String>, HasPlaceHolder {
	private TextBox box;
	private TouchWidget clearButton;
	private HandlerRegistration clearButtonHandler;
	private HandlerRegistration boxHandler;
	private FlowPanel roundDiv;

	public MSearchBox() {
		FlowPanel main = new FlowPanel();
		main.addStyleName("mgwt-SearchBox");

		initWidget(main);

		roundDiv = new FlowPanel();
		roundDiv.addStyleName("round");
		main.add(roundDiv);

		box = new TextBox();
		box.addStyleName("input");

		//TODO 
		box.getElement().setAttribute("autocapitalize", "off");
		box.getElement().setAttribute("autocorrect", "off");
		box.getElement().setAttribute("type", "search");

		roundDiv.add(box);

		clearButton = new ClearButton();

		clearButton.addStyleName("clear");

		setPlaceHolder("Search");

	}

	@Override
	protected void onAttach() {
		super.onAttach();

		clearButtonHandler = clearButton.addTouchHandler(new ClearButtonTouchHandler());

		boxHandler = box.addKeyUpHandler(new SearchBoxChangeHandler());

	}

	@Override
	protected void onDetach() {
		super.onDetach();
		clearButtonHandler.removeHandler();
		boxHandler.removeHandler();
	}

	private class SearchBoxChangeHandler implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (box.getValue().length() > 0) {
				roundDiv.add(clearButton);
			} else {
				roundDiv.remove(clearButton);
			}

		}

	}

	private class ClearButtonTouchHandler implements TouchHandler {

		private boolean moved;

		@Override
		public void onTouchStart(TouchStartEvent event) {
			clearButton.addStyleDependentName("active");
			moved = false;

		}

		/* (non-Javadoc)
		 * @see de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler#onTouchMove(de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent)
		 */
		@Override
		public void onTouchMove(TouchMoveEvent event) {
			clearButton.removeStyleDependentName("active");
			moved = true;

		}

		/* (non-Javadoc)
		 * @see de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler#onTouchEnd(de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent)
		 */
		@Override
		public void onTouchEnd(TouchEndEvent event) {
			clearButton.removeStyleDependentName("active");

			if (!moved) {
				clearSearchField();
			}

		}

		/* (non-Javadoc)
		 * @see de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler#onTouchCanceled(de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent)
		 */
		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			clearButton.removeStyleDependentName("active");

		}
	}

	private class ClearButton extends TouchWidget {

		public ClearButton() {
			setElement(DOM.createDiv());
		}
	}

	public void setPlaceHolder(String text) {
		box.getElement().setAttribute("placeholder", text);
	}

	/**
	 * 
	 */
	private void clearSearchField() {
		box.setValue("");
		roundDiv.remove(clearButton);

	}

	public String getPlaceHolder() {
		return box.getElement().getAttribute("placeholder");
	}

	public String getText() {
		return box.getText();
	}

	public void setText(String text) {
		box.setText(text);
	}

	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> stringValueChangeHandler) {
		return box.addValueChangeHandler(stringValueChangeHandler);
	}

	public void setName(String name) {
		box.setName(name);
	}

	public String getName() {
		return box.getName();
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return box.addChangeHandler(handler);
	}

	public String getValue() {
		return box.getValue();
	}

	public void setValue(String value) {
		box.setValue(value);
	}

	public void setValue(String value, boolean fireEvents) {
		box.setValue(value, fireEvents);
	}

}
