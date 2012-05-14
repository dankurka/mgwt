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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.dom.client.event.tap.Tap;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss;
import com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * A search widget
 * 
 * <h2>Styling</h2> The DOM structure looks like:
 * 
 * <pre>
 * &lt;div class="mgwt-SearchBox">
 * 	&lt;form class="mgwt-SearchBox-round">
 * 		&lt;input class="mgwt-SearchBox-input" />
 * 	&lt;/form>
 * 	&lt;div class="mgwt-SearchBox-clear"/>
 * &lt;/div>
 * </pre>
 * 
 * 
 * 
 * If the clear element is touched its class is changed to
 * .mgwt-SearchBox-clear-active
 * 
 * @author Daniel Kurka Date: 30.05.2010
 * 
 */

public class MSearchBox extends Composite implements HasChangeHandlers, HasText, HasName, HasValue<String>, HasPlaceHolder, HasAllKeyHandlers {
	private class SearchBoxChangeHandler implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (!MGWT.getOsDetection().isDesktop()) {
				if (box.getValue().length() > 0) {
					roundDiv.add(clearButton);
				} else {
					roundDiv.remove(clearButton);
				}
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

			Touch touch = event.getTouches().get(0);
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
			Touch touch = event.getTouches().get(0);

			if (Math.abs(touch.getPageX() - x) > Tap.RADIUS || Math.abs(touch.getPageY() - y) > Tap.RADIUS) {
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

	private static class ClearButton extends TouchWidget {

		public ClearButton() {
			setElement(DOM.createDiv());
		}
	}

	private TextBox box;
	private TouchWidget clearButton;
	private HandlerRegistration clearButtonHandler;
	private HandlerRegistration boxHandler;
	private FlowPanel roundDiv;
	protected final MSearchBoxCss css;

	/**
	 * Construct a search box
	 */
	public MSearchBox() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getSearchBoxCss());
	}

	/**
	 * Construct a search box with a given css
	 * 
	 * @param css the css to use
	 */
	public MSearchBox(MSearchBoxCss css) {
		this.css = css;
		this.css.ensureInjected();
		TouchPanel main = new TouchPanel();

		main.addStyleName(css.searchBox());

		initWidget(main);

		// search box needs this on ios
		roundDiv = new FlowPanel();
		roundDiv.addStyleName(css.round());
		main.add(roundDiv);

		box = new TextBox();
		box.addStyleName(css.input());

		// TODO
		box.getElement().setAttribute("autocapitalize", "off");
		box.getElement().setAttribute("autocorrect", "off");
		box.getElement().setAttribute("type", "search");

		roundDiv.add(box);

		clearButton = new ClearButton();

		clearButton.addStyleName(css.clear());

		setPlaceHolder("Search");

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#onAttach()
	 */
	/** {@inheritDoc} */
	@Override
	protected void onAttach() {
		super.onAttach();

		clearButtonHandler = clearButton.addTouchHandler(new ClearButtonTouchHandler());

		boxHandler = box.addKeyUpHandler(new SearchBoxChangeHandler());

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#onDetach()
	 */
	/** {@inheritDoc} */
	@Override
	protected void onDetach() {
		super.onDetach();
		clearButtonHandler.removeHandler();
		boxHandler.removeHandler();
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder#setPlaceHolder(java.lang.String)
	 */
	/** {@inheritDoc} */
	public void setPlaceHolder(String text) {
		box.getElement().setAttribute("placeholder", text);
	}

	private void clearSearchField() {
		box.setValue("");
		roundDiv.remove(clearButton);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder#getPlaceHolder()
	 */
	/**
	 * <p>
	 * getPlaceHolder
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getPlaceHolder() {
		return box.getElement().getAttribute("placeholder");
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#getText()
	 */
	/**
	 * <p>
	 * getText
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getText() {
		return box.getText();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
	 */
	/** {@inheritDoc} */
	public void setText(String text) {
		box.setText(text);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	/** {@inheritDoc} */
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> stringValueChangeHandler) {
		return box.addValueChangeHandler(stringValueChangeHandler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasName#setName(java.lang.String)
	 */
	/** {@inheritDoc} */
	public void setName(String name) {
		box.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasName#getName()
	 */
	/**
	 * <p>
	 * getName
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getName() {
		return box.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasChangeHandlers#addChangeHandler(com.google.gwt.event.dom.client.ChangeHandler)
	 */
	/** {@inheritDoc} */
	public com.google.gwt.event.shared.HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return box.addChangeHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#getValue()
	 */
	/**
	 * <p>
	 * getValue
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getValue() {
		return box.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object)
	 */
	/**
	 * <p>
	 * setValue
	 * </p>
	 * 
	 * @param value a {@link java.lang.String} object.
	 */
	public void setValue(String value) {
		box.setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
	 */
	/** {@inheritDoc} */
	public void setValue(String value, boolean fireEvents) {
		box.setValue(value, fireEvents);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyUpHandlers#addKeyUpHandler(com.google.gwt.event.dom.client.KeyUpHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return box.addKeyUpHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyDownHandlers#addKeyDownHandler(com.google.gwt.event.dom.client.KeyDownHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return box.addKeyDownHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyPressHandlers#addKeyPressHandler(com.google.gwt.event.dom.client.KeyPressHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return box.addKeyPressHandler(handler);
	}

}
