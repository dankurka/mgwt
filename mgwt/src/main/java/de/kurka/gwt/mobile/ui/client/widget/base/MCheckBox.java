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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasValue;

import de.kurka.gwt.mobile.dom.client.event.touch.Touch;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.ui.client.util.CssUtil;
import de.kurka.gwt.mobile.ui.client.widget.touch.TouchWidget;

/**
 * @author Daniel Kurka
 *
 */
public class MCheckBox extends TouchWidget implements HasValue<Boolean> {

	/**
	 * @author Daniel Kurka
	 *
	 */
	private final class TouchHandlerImplementation implements TouchHandler {
		private int x_start;
		private int x_min;
		private int x_max;
		private int offset;
		private boolean moved;
		private int now_x;

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			event.stopPropagation();
			event.preventDefault();
			setValue(getValue());
		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {

			event.stopPropagation();
			event.preventDefault();
			if (!moved) {
				setValue(!getValue());
			} else {
				setValue(now_x >= x_start);
			}
		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			event.stopPropagation();
			event.preventDefault();
			Touch touch = event.touches().get(0);
			now_x = touch.getPageX();

			if (!moved) {
				if (Math.abs(now_x - x_start) < DRAG_DEADZONE) {
					return;
				}
			}

			moved = true;

			int translate_x = now_x - x_start;

			if (translate_x < x_min) {
				return;
			}

			if (translate_x > x_max) {
				return;
			}

			translate(translate_x + offset);

		}

		@Override
		public void onTouchStart(TouchStartEvent event) {
			event.stopPropagation();
			event.preventDefault();
			Touch touch = event.touches().get(0);
			x_start = touch.getPageX();
			moved = false;
			if (value) {
				x_min = -CONTAINER_WIDTH;
				x_max = 0;
				offset = 0;
			} else {
				x_min = 0;
				x_max = CONTAINER_WIDTH;
				offset = -CONTAINER_WIDTH;
			}

		}
	}

	private boolean value;
	private Element onDiv;
	private Element middleDiv;
	private Element offDiv;

	private static final String CSS_PRIMARY_NAME = "mgwt-CheckBox";
	private static final String CSS_DEPENDENTNAME_CHECKED = "checked";
	private static final String CSS_DEPENDENTNAME_NOTCHECKED = "notchecked";
	private static final String CSS_DEPENDENTNAME_CONTAINER_ON = "on";
	private static final String CSS_DEPENDENTNAME_CONTAINER_OFF = "off";
	private static final String CSS_DEPENDENTNAME_CONTAINER_MIDDLE = "middle";
	private static final String CSS_DEPENDENTNAME_CONTAINER_MIDDLE_CONTENT = "middle-content";

	private static final String CSS_DEPENDENTNAME_IMPORTANT = "important";

	private static final int CONTAINER_WIDTH = 54;
	private static final int DRAG_DEADZONE = 8;

	public MCheckBox() {
		setElement(DOM.createDiv());
		setStylePrimaryName(CSS_PRIMARY_NAME);

		onDiv = DOM.createDiv();
		onDiv.setClassName(CSS_PRIMARY_NAME + "-" + CSS_DEPENDENTNAME_CONTAINER_ON);
		onDiv.setInnerText("I");
		getElement().appendChild(onDiv);

		middleDiv = DOM.createDiv();
		middleDiv.setClassName(CSS_PRIMARY_NAME + "-" + CSS_DEPENDENTNAME_CONTAINER_MIDDLE);
		Element middleContent = DOM.createDiv();
		middleContent.setClassName(CSS_PRIMARY_NAME + "-" + CSS_DEPENDENTNAME_CONTAINER_MIDDLE_CONTENT);
		middleDiv.appendChild(middleContent);
		getElement().appendChild(middleDiv);

		offDiv = DOM.createDiv();
		offDiv.setClassName(CSS_PRIMARY_NAME + "-" + CSS_DEPENDENTNAME_CONTAINER_OFF);
		offDiv.setInnerText("O");
		getElement().appendChild(offDiv);

		addTouchHandler(new TouchHandlerImplementation());

		setValue(true, false);

	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public void setValue(Boolean value) {
		setValue(value, true);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {

		if (value == null) {
			throw new IllegalArgumentException("value can not be null");
		}
		boolean oldValue = value;
		this.value = value;

		clearStyles();
		if (value) {
			addStyleDependentName(CSS_DEPENDENTNAME_CHECKED);
			removeStyleDependentName(CSS_DEPENDENTNAME_NOTCHECKED);

		} else {
			addStyleDependentName(CSS_DEPENDENTNAME_NOTCHECKED);
			removeStyleDependentName(CSS_DEPENDENTNAME_CHECKED);

		}

		if (fireEvents)
			ValueChangeEvent.fireIfNotEqual(this, oldValue, this.value);

	}

	public void setImportant(boolean important) {
		if (important) {
			addStyleDependentName(CSS_DEPENDENTNAME_IMPORTANT);
		} else {
			removeStyleDependentName(CSS_DEPENDENTNAME_IMPORTANT);
		}
	}

	private void translate(int x) {
		CssUtil.translate(onDiv, x, 0);
		CssUtil.translate(middleDiv, x, 0);
		CssUtil.translate(offDiv, x, 0);
	}

	private void clearStyles() {
		middleDiv.setAttribute("style", "");
		onDiv.setAttribute("style", "");
		offDiv.setAttribute("style", "");

	}
}
