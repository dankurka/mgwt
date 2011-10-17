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
package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouch;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss;
import com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * @author Daniel Kurka Date: 30.05.2010
 * 
 */
public class MSearchBox extends Composite implements HasChangeHandlers, HasText, HasName, HasValue<String>, HasPlaceHolder, HasAllKeyHandlers {
	private TextBox box;
	private TouchWidget clearButton;
	private HandlerRegistration clearButtonHandler;
	private HandlerRegistration boxHandler;
	private FlowPanel roundDiv;
	protected final MSearchBoxCss css;

	public MSearchBox() {
		this(MGWTStyle.getDefaultClientBundle().getSearchBoxCss());
	}

	public MSearchBox(MSearchBoxCss css) {
		this.css = css;
		this.css.ensureInjected();
		TouchPanel main = new TouchPanel();

		main.addStyleName(css.searchBox());

		initWidget(main);

		roundDiv = new FlowPanel();
		roundDiv.addStyleName(css.round());
		main.add(roundDiv);

		box = new TextBox();
		box.addStyleName(css.input());

		//TODO 
		box.getElement().setAttribute("autocapitalize", "off");
		box.getElement().setAttribute("autocorrect", "off");
		box.getElement().setAttribute("type", "search");

		roundDiv.add(box);

		clearButton = new ClearButton();

		clearButton.addStyleName(css.clear());

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
		private int x;
		private int y;

		@Override
		public void onTouchStart(TouchStartEvent event) {
			clearButton.addStyleName(css.clearActive());
			moved = false;

			Touch touch = event.touches().get(0);
			x = touch.getPageX();
			y = touch.getPageY();

			event.preventDefault();
			event.stopPropagation();

		}

		/* (non-Javadoc)
		 * @see com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler#onTouchMove(com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent)
		 */
		@Override
		public void onTouchMove(TouchMoveEvent event) {
			Touch touch = event.touches().get(0);

			if (Math.abs(touch.getPageX() - x) > SimpleTouch.TOUCH_RADIUS || Math.abs(touch.getPageY() - y) > SimpleTouch.TOUCH_RADIUS) {
				moved = true;
				clearButton.removeStyleName(css.clearActive());
			}
			event.preventDefault();
			event.stopPropagation();

		}

		/* (non-Javadoc)
		 * @see com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler#onTouchEnd(com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent)
		 */
		@Override
		public void onTouchEnd(TouchEndEvent event) {
			clearButton.removeStyleName(css.clearActive());

			if (!moved) {
				clearSearchField();
			}

			event.preventDefault();
			event.stopPropagation();

		}

		/* (non-Javadoc)
		 * @see com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler#onTouchCanceled(com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent)
		 */
		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			clearButton.removeStyleName(css.clearActive());

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

	public com.google.gwt.event.shared.HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> stringValueChangeHandler) {
		return box.addValueChangeHandler(stringValueChangeHandler);
	}

	public void setName(String name) {
		box.setName(name);
	}

	public String getName() {
		return box.getName();
	}

	public com.google.gwt.event.shared.HandlerRegistration addChangeHandler(ChangeHandler handler) {
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

	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return box.addKeyUpHandler(handler);
	}

	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return box.addKeyDownHandler(handler);
	}

	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return box.addKeyPressHandler(handler);
	}

}
