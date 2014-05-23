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
package com.googlecode.mgwt.ui.client.widget.input.checkbox;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasValue;

import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * <h1>A checkbox widget</h1>
 *
 *
 *
 *
 * <h2>Styling</h2>
 *
 * The DOM structre of a checkbox looks like this:
 *
 * <pre>
 * &lt;div class="mgwt-CheckBox">
 * 	&lt;div class="mgwt-CheckBox-on">&lt;/div>
 * 	&lt;div class="mgwt-CheckBox-middle">
 * 		&lt;div class="mgwt-CheckBox-middle-content ">&lt;/div>
 * 	&lt;/div>
 * 	&lt;div class="mgwt-CheckBox-off">&lt;/div>
 * &lt;/div>
 * </pre>
 *
 *
 * The folling classes are applied dynamically:
 *
 * <ul>
 * <li>.mgwt-CheckBox-checked- is applied to the main div if the box is checked</li>
 * <li>.mgwt-CheckBox-notchecked- is applied to the main div if the box is NOT
 * checked</li>
 * </ul>
 *
 *
 *
 *
 *
 */
public class MCheckBox extends TouchWidget implements HasValue<Boolean>, IsEditor<LeafValueEditor<Boolean>> {

  public static final MCheckBoxAppearance DEFAULT_APPEARANCE = GWT.create(MCheckBoxAppearance.class);

	private final class TouchHandlerImplementation implements TouchHandler {
		private int x_start;
		private int x_min;
		private int x_max;
		private int offset;
		private boolean moved;
		private int now_x;

		@Override
		public void onTouchCancel(TouchCancelEvent event) {
			if (isReadOnly()) {
				return;
			}
			event.stopPropagation();
			event.preventDefault();
			if (MGWT.getOsDetection().isDesktop()) {
				DOM.releaseCapture(getElement());
			}
			setValue(getValue());
		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {
			if (isReadOnly()) {
				return;
			}

			event.stopPropagation();
			event.preventDefault();
			if (MGWT.getOsDetection().isDesktop()) {
				DOM.releaseCapture(getElement());
			}

			if (!moved) {
				setValue(!getValue());
			} else {
				setValue(now_x >= x_start);
			}
		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			if (isReadOnly()) {
				return;
			}
			event.stopPropagation();
			event.preventDefault();
			Touch touch = event.getTouches().get(0);
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
			if (isReadOnly()) {
				return;
			}
			event.stopPropagation();
			event.preventDefault();
			if (MGWT.getOsDetection().isDesktop()) {
				DOM.setCapture(getElement());
			}

			Touch touch = event.getTouches().get(0);
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


  private static final int CONTAINER_WIDTH = 54;
  private static final int DRAG_DEADZONE = 8;

	private boolean value;
	@UiField
	protected Element on;
	@UiField
	protected Element middle;
	@UiField
	protected Element off;

	private LeafValueEditor<Boolean> editor;
	private boolean readonly;
  private MCheckBoxAppearance appearance;

	public MCheckBox() {
		this(DEFAULT_APPEARANCE);
	}

	public MCheckBox(MCheckBoxAppearance appearance) {
		this.appearance = appearance;
		setElement(appearance.uiBinder().createAndBindUi(this));
		addTouchHandler(new TouchHandlerImplementation());
		setValue(true, false);
	}

	@Override
	public com.google.gwt.event.shared.HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
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
		boolean oldValue = this.value;
		this.value = value;

		clearStyles();
		if (value) {
			addStyleName(appearance.css().checked());
			removeStyleName(appearance.css().notChecked());
		} else {
			addStyleName(appearance.css().notChecked());
			removeStyleName(appearance.css().checked());
		}

		if (fireEvents){
		  ValueChangeEvent.fireIfNotEqual(this, oldValue, this.value);
		}
	}

	/**
	 * Should this check box be rendered as important
	 *
	 * @param important true to render the check box as important
	 */
	public void setImportant(boolean important) {
		if (important) {
			addStyleName(appearance.css().important());
		} else {
			removeStyleName(appearance.css().important());
		}
	}

	@Override
	public LeafValueEditor<Boolean> asEditor() {
		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}

	/**
	 * Should the checkbox be readonly
	 *
	 * @param readonly true to be read only
	 */
	public void setReadOnly(boolean readonly) {
		this.readonly = readonly;
	}

  /**
   * Is the checkbox currently read only?
   *
   * @return true if the checkbox is readonly
   */
	public boolean isReadOnly() {
		return readonly;
	}

	@UiFactory
	protected MCheckBoxAppearance getAppearance() {
		return appearance;
	}

	private void translate(int x) {
		CssUtil.translate(on, x, 0);
		CssUtil.translate(middle, x, 0);
		CssUtil.translate(off, x, 0);
	}

	private void clearStyles() {
		middle.setAttribute("style", "");
		on.setAttribute("style", "");
		off.setAttribute("style", "");
	}
}
