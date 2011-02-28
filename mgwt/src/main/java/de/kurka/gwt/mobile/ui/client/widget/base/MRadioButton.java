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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasText;

import de.kurka.gwt.mobile.dom.client.event.touch.Touch;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouch;
import de.kurka.gwt.mobile.ui.client.widget.TouchWidget;

/**
 * @author Daniel Kurka
 *
 */
public class MRadioButton extends TouchWidget implements HasText, HasValueChangeHandlers<Boolean> {

	private InputElement inputRadio;
	private LabelElement labelElement;

	/**
	 * @param name
	 */
	public MRadioButton(String name) {
		setElement(DOM.createSpan());

		sinkEvents(Event.ONCHANGE);

		labelElement = LabelElement.as(DOM.createLabel());
		getElement().appendChild(labelElement);
		inputRadio = InputElement.as(DOM.createInputRadio(name));
		getElement().appendChild(inputRadio);

		setStylePrimaryName("mgwt-RadioButton");

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
				if (Element.is(eventTarget)) {
					Element el = Element.as(eventTarget);

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

}
