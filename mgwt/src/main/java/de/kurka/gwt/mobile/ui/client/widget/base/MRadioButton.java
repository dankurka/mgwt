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

import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWordWrap;

import de.kurka.gwt.mobile.dom.client.event.touch.Touch;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouch;
import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;
import de.kurka.gwt.mobile.ui.client.widget.touch.TouchWidget;

/**
 * @author Daniel Kurka
 * 
 */
public class MRadioButton extends TouchWidget implements HasText, HasEnabled, HasValueChangeHandlers<Boolean>, HasName, HasValue<Boolean>, HasWordWrap, IsEditor<LeafValueEditor<Boolean>> {

	private InputElement inputRadio;
	private LabelElement labelElement;
	private LeafValueEditor<Boolean> editor;
	private final InputCss css;

	public MRadioButton(String name) {
		this(MGWTStyle.getDefaultClientBundle().getInputCss(), name);
	}

	/**
	 * @param name
	 */
	public MRadioButton(InputCss css, String name) {
		this.css = css;
		css.ensureInjected();
		setElement(DOM.createSpan());

		sinkEvents(Event.ONCHANGE);

		labelElement = LabelElement.as(DOM.createLabel());
		getElement().appendChild(labelElement);
		inputRadio = InputElement.as(DOM.createInputRadio(name));
		getElement().appendChild(inputRadio);

		addStyleName(css.radioButton());

		addTouchHandler(new TouchHandler() {

			private boolean ignore;
			private boolean labelOrContainer;
			private int start_x;
			private int start_y;

			private int last_x;
			private int last_y;

			@Override
			public void onTouchCanceled(TouchCancelEvent event) {
				if (ignore)
					return;
				System.out.println("cancel" + inputRadio.isChecked());

			}

			@Override
			public void onTouchEnd(TouchEndEvent event) {
				if (ignore)
					return;

				if (Math.abs(last_x - start_x) < SimpleTouch.TOUCH_RADIUS && Math.abs(last_y - start_y) < SimpleTouch.TOUCH_RADIUS) {
					if (labelOrContainer) {
						inputRadio.setChecked(true);
						ValueChangeEvent.fire(MRadioButton.this, true);
					}
				}

			}

			@Override
			public void onTouchMove(TouchMoveEvent event) {
				if (ignore)
					return;
				Touch touch = event.touches().get(0);
				last_x = touch.getPageX();
				last_y = touch.getPageY();
				System.out.println("move" + inputRadio.isChecked());

			}

			@Override
			public void onTouchStart(TouchStartEvent event) {

				ignore = inputRadio.isChecked();

				if (ignore)
					return;

				Touch touch = event.touches().get(0);
				start_x = touch.getPageX();
				start_y = touch.getPageY();
				last_x = start_x;
				last_y = start_y;

				EventTarget eventTarget = event.getNativeEvent().getEventTarget();
				labelOrContainer = true;
				if (com.google.gwt.dom.client.Element.is(eventTarget)) {
					com.google.gwt.dom.client.Element el = com.google.gwt.dom.client.Element.as(eventTarget);

					if (inputRadio.isOrHasChild(el)) {
						labelOrContainer = false;
					}
				}

				System.out.println("start" + inputRadio.isChecked());

			}
		});

		addHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				System.out.println("value change " + inputRadio.isChecked());

			}
		}, ValueChangeEvent.getType());

		addHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ValueChangeEvent.fire(MRadioButton.this, true);

			}
		}, ChangeEvent.getType());
	}

	@Override
	public String getText() {
		return labelElement.getInnerText();
	}

	@Override
	public void setText(String text) {
		labelElement.setInnerText(text);

	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public LeafValueEditor<Boolean> asEditor() {
		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}

	@Override
	public boolean getWordWrap() {
		return !getElement().getStyle().getProperty("whiteSpace").equals("nowrap");
	}

	@Override
	public void setWordWrap(boolean wrap) {
		getElement().getStyle().setProperty("whiteSpace", wrap ? "normal" : "nowrap");

	}

	@Override
	public Boolean getValue() {
		if (isAttached()) {
			return inputRadio.isChecked();
		} else {
			return inputRadio.isDefaultChecked();
		}
	}

	@Override
	public void setValue(Boolean value) {
		setValue(value, false);

	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		if (value == null) {
			value = Boolean.FALSE;
		}

		Boolean oldValue = getValue();
		inputRadio.setChecked(value);
		inputRadio.setDefaultChecked(value);
		if (value.equals(oldValue)) {
			return;
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}

	}

	@Override
	public void setName(String name) {
		replaceInputElement(DOM.createInputRadio(name));

	}

	private void replaceInputElement(com.google.gwt.user.client.Element elem) {
		InputElement newInputElem = InputElement.as(elem);
		// Collect information we need to set

		boolean checked = getValue();
		boolean enabled = isEnabled();
		String formValue = getFormValue();
		String uid = inputRadio.getId();
		String accessKey = inputRadio.getAccessKey();
		int sunkEvents = Event.getEventsSunk(inputRadio);

		// Clear out the old input element
		setEventListener(asOld(inputRadio), null);

		getElement().replaceChild(newInputElem, inputRadio);

		// Sink events on the new element
		Event.sinkEvents(elem, Event.getEventsSunk(inputRadio));
		Event.sinkEvents(inputRadio, 0);
		inputRadio = newInputElem;

		// Setup the new element
		Event.sinkEvents(inputRadio, sunkEvents);
		inputRadio.setId(uid);
		if (!"".equals(accessKey)) {
			inputRadio.setAccessKey(accessKey);
		}

		setValue(checked);
		setEnabled(enabled);
		setFormValue(formValue);

		// Set the event listener
		if (isAttached()) {
			setEventListener(asOld(inputRadio), this);
		}

	}

	public void setFormValue(String formValue) {
		inputRadio.setAttribute("value", formValue);

	}

	@Override
	public String getName() {
		return inputRadio.getName();
	}

	@Override
	public boolean isEnabled() {
		return !inputRadio.isDisabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		inputRadio.setDisabled(!enabled);
		if (enabled) {
			removeStyleDependentName(css.disabled());
		} else {
			addStyleDependentName(css.disabled());
		}

	}

	public String getFormValue() {
		return inputRadio.getValue();
	}

	private Element asOld(com.google.gwt.dom.client.Element elem) {
		Element oldSchool = elem.cast();
		return oldSchool;
	}

	private void setEventListener(com.google.gwt.dom.client.Element e, EventListener listener) {
		DOM.setEventListener(asOld(e), listener);
	}

}
