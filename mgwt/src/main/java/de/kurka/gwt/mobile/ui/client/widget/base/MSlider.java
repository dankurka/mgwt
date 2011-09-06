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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;

import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouch;
import de.kurka.gwt.mobile.ui.client.util.CssUtil;
import de.kurka.gwt.mobile.ui.client.widget.touch.TouchWidget;

/**
 * @author Daniel Kurka
 *
 */
public class MSlider extends Composite implements HasValue<Integer> {

	private static class SliderWidget extends TouchWidget {

		private Element slider;

		public SliderWidget() {
			setElement(DOM.createDiv());
			slider = DOM.createDiv();
			slider.setClassName("pointer");
			getElement().appendChild(slider);

		}

		public void setPos(int x) {
			CssUtil.translate(slider, x, 0);
		}
	}

	private SliderWidget sliderWidget;

	private static final int POINTER_WIDTH = 10;

	private class SliderTouchHandler implements TouchHandler {

		private boolean moving;

		@Override
		public void onTouchStart(TouchStartEvent event) {
			moving = false;
			//is the touch in the area of the slider part
			int x = event.touches().get(0).getPageX() - POINTER_WIDTH;

			if (Math.abs(x - sliderPos) < SimpleTouch.TOUCH_RADIUS) {
				//yes we are
				moving = true;
			}

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			if (!moving)
				return;

			int x = event.touches().get(0).getPageX() - POINTER_WIDTH;
			System.out.println("" + x);
			if (x < 0) {
				x = 0;
			}

			int width = sliderWidget.getOffsetWidth() - 2 * POINTER_WIDTH;

			if (x > width) {
				x = width;
			}

			//scale it to max
			x = x * max / width;
			System.out.println("" + x);
			setValue(x, true);
		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {
			if (!moving) {
				return;
			}

			moving = false;

		}

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			moving = false;

		}

	}

	private int sliderPos;

	public MSlider() {
		sliderWidget = new SliderWidget();
		initWidget(sliderWidget);
		setStylePrimaryName("mgwt-Slider");

		sliderWidget.addTouchHandler(new SliderTouchHandler());

		max = 100;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	private int max;

	public void setMax(int max) {
		if (max <= 0) {
			throw new IllegalArgumentException("max > 0");
		}
		this.max = max;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	@Override
	public Integer getValue() {
		return sliderPos * max / (sliderWidget.getOffsetWidth() - 2 * POINTER_WIDTH);
	}

	@Override
	public void setValue(Integer value) {
		setValue(value, true);

	}

	@Override
	public void setValue(Integer value, boolean fireEvents) {
		if (value == null) {
			throw new IllegalArgumentException("value can not be null");
		}
		int width = sliderWidget.getOffsetWidth() - 2 * POINTER_WIDTH;

		int oldValue = sliderPos * max / width;

		sliderPos = value * width / max;
		sliderWidget.setPos(sliderPos);

		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
		}

	}

}
