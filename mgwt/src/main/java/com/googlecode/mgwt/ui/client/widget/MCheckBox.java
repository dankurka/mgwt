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

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasValue;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;


/**
 * @author Daniel Kurka
 * 
 */
public class MCheckBox extends TouchWidget implements HasValue<Boolean>, IsEditor<LeafValueEditor<Boolean>> {

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

	private static final int CONTAINER_WIDTH = 54;
	private static final int DRAG_DEADZONE = 8;
	protected final CheckBoxCss css;
	private LeafValueEditor<Boolean> editor;

	public MCheckBox() {
		this(MGWTStyle.getDefaultClientBundle().getCheckBoxCss());
	}

	public MCheckBox(CheckBoxCss css) {
		this.css = css;
		css.ensureInjected();
		setElement(DOM.createDiv());
		addStyleName(css.checkBox());

		onDiv = DOM.createDiv();
		onDiv.setClassName(css.on());
		onDiv.setInnerText("I");
		getElement().appendChild(onDiv);

		middleDiv = DOM.createDiv();
		middleDiv.setClassName(css.middle());
		Element middleContent = DOM.createDiv();
		middleContent.setClassName(css.content());
		middleDiv.appendChild(middleContent);
		getElement().appendChild(middleDiv);

		offDiv = DOM.createDiv();
		offDiv.setClassName(css.off());
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
			addStyleName(css.checked());
			removeStyleName(css.notChecked());

		} else {
			addStyleName(css.notChecked());
			removeStyleName(css.checked());

		}

		if (fireEvents)
			ValueChangeEvent.fireIfNotEqual(this, oldValue, this.value);

	}

	public void setImportant(boolean important) {
		if (important) {
			addStyleName(css.important());
		} else {
			removeStyleName(css.important());
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

	@Override
	public LeafValueEditor<Boolean> asEditor() {
		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}
}
