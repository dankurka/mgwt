/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.touch;

import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.recognizer.longtap.HasLongTapHandlers;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapEvent;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapHandler;
import com.googlecode.mgwt.dom.client.recognizer.pinch.HasPinchHandlers;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchEvent;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.HasSwipeHandlers;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeMoveEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeMoveHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeStartEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeStartHandler;

/**
 * A TouchDelegate can be used to source touch events from a widget that does
 * not support {@link HasTouchHandlers}
 *
 * @author Daniel Kurka
 */

public class TouchDelegate implements HasTouchHandlers, HasTapHandlers, HasSwipeHandlers, HasPinchHandlers, HasLongTapHandlers {

	protected final GestureUtility gestureUtility;
	private static final TouchWidgetImpl impl = TouchWidgetImpl.get();
	private final Widget w;

	public TouchDelegate(Widget w) {
		this.w = w;
		gestureUtility = new GestureUtility(this);
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return impl.addTouchStartHandler(w, handler);
	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return impl.addTouchMoveHandler(w, handler);
	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return impl.addTouchCancelHandler(w, handler);
	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return impl.addTouchEndHandler(w, handler);
	}

	@Override
	public HandlerRegistration addTapHandler(TapHandler handler) {
		gestureUtility.ensureTapRecognizer();
		return w.addHandler(handler, TapEvent.getType());
	}

  @Override
  public HandlerRegistration addTouchHandler(TouchHandler handler) {
		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(handler));
		return handlerRegistrationCollection;
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		w.fireEvent(event);
	}

  @Override
  public HandlerRegistration addSwipeStartHandler(SwipeStartHandler handler) {
		gestureUtility.ensureSwipeRecognizer();
		return w.addHandler(handler, SwipeStartEvent.getType());
	}

  @Override
  public HandlerRegistration addSwipeMoveHandler(SwipeMoveHandler handler) {
		gestureUtility.ensureSwipeRecognizer();
		return w.addHandler(handler, SwipeMoveEvent.getType());
	}

  @Override
  public HandlerRegistration addSwipeEndHandler(SwipeEndHandler handler) {
		gestureUtility.ensureSwipeRecognizer();
		return w.addHandler(handler, SwipeEndEvent.getType());
	}

	@Override
	public HandlerRegistration addPinchHandler(PinchHandler handler) {
		gestureUtility.ensurePinchRecognizer(w);
		return w.addHandler(handler, PinchEvent.getType());
	}

	@Override
	public HandlerRegistration addLongTapHandler(LongTapHandler handler) {
		gestureUtility.ensureLongTapHandler();
		return w.addHandler(handler, LongTapEvent.getType());
	}
}
