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
package de.kurka.gwt.mobile.ui.client.widget;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;

import de.kurka.gwt.mobile.dom.client.event.mouse.HandlerRegistrationCollection;
import de.kurka.gwt.mobile.dom.client.event.mouse.TouchEndToMouseUpHandler;
import de.kurka.gwt.mobile.dom.client.event.mouse.TouchMoveToMouseMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.mouse.TouchStartToMouseDownHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.HasTouchHandlers;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchToNativeTouchHandler;

/**
 * @author Daniel Kurka
 *
 */
public class TouchPanel extends FlowPanel implements HasTouchHandlers {

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		if (WidgetUtil.hasTouchEvents()) {
			return addDomHandler(handler, TouchStartEvent.getType());

		} else {
			return addDomHandler(new TouchStartToMouseDownHandler(handler), MouseDownEvent.getType());
		}

	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		if (WidgetUtil.hasTouchEvents()) {
			return addDomHandler(handler, TouchMoveEvent.getType());

		} else {
			TouchMoveToMouseMoveHandler touchMoveToMouseMoveHandler = new TouchMoveToMouseMoveHandler(handler);
			HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();
			handlerRegistrationCollection.addHandlerRegistration(addDomHandler(touchMoveToMouseMoveHandler, MouseDownEvent.getType()));
			handlerRegistrationCollection.addHandlerRegistration(addDomHandler(touchMoveToMouseMoveHandler, MouseUpEvent.getType()));
			handlerRegistrationCollection.addHandlerRegistration(addDomHandler(touchMoveToMouseMoveHandler, MouseMoveEvent.getType()));
			return handlerRegistrationCollection;
		}

	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		if (WidgetUtil.hasTouchEvents()) {
			return addDomHandler(handler, TouchCancelEvent.getType());
		} else {
			return new HandlerRegistration() {

				@Override
				public void removeHandler() {
					// TODO Auto-generated method stub

				}
			};
		}

	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		if (WidgetUtil.hasTouchEvents()) {
			return addDomHandler(handler, TouchEndEvent.getType());
		} else {
			return addDomHandler(new TouchEndToMouseUpHandler(handler), MouseUpEvent.getType());
		}

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

	public HandlerRegistration addSimpleTouchHandler(SimpleTouchHandler handler) {
		SimpleTouchToNativeTouchHandler touchHandler = new SimpleTouchToNativeTouchHandler(handler);

		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(touchHandler));
		return handlerRegistrationCollection;
	}

}
