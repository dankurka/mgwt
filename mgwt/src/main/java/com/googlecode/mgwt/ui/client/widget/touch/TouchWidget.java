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
package com.googlecode.mgwt.ui.client.widget.touch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapToNativeTouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;

/**
 * @author Daniel Kurka
 * 
 */
public abstract class TouchWidget extends Widget implements HasTouchHandlers {

	private static final TouchWidgetImpl impl = GWT.create(TouchWidgetImpl.class);

	public TouchWidget() {

	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return impl.addTouchStartHandler(this, handler);

	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return impl.addTouchMoveHandler(this, handler);

	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return impl.addTouchCancelHandler(this, handler);

	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return impl.addTouchEndHandler(this, handler);

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

	protected HandlerRegistration addTapHandler(TapHandler handler) {
		TapToNativeTouchHandler touchHandler = new TapToNativeTouchHandler(handler);

		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(touchHandler));
		return handlerRegistrationCollection;
	}

}
