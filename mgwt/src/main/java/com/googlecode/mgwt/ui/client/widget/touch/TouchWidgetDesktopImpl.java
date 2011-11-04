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

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.mouse.TouchEndToMouseUpHandler;
import com.googlecode.mgwt.dom.client.event.mouse.TouchMoveToMouseMoveHandler;
import com.googlecode.mgwt.dom.client.event.mouse.TouchStartToMouseDownHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;

/**
 * The implementation for mouse devices of {@link TouchWidgetImpl}
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class TouchWidgetDesktopImpl implements TouchWidgetImpl {

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchStartHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchStartHandler(Widget w, TouchStartHandler handler) {
		return w.addDomHandler(new TouchStartToMouseDownHandler(handler), MouseDownEvent.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchMoveHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchMoveHandler(Widget w, TouchMoveHandler handler) {
		TouchMoveToMouseMoveHandler touchMoveToMouseMoveHandler = new TouchMoveToMouseMoveHandler(handler);
		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();
		handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseDownEvent.getType()));
		handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseUpEvent.getType()));
		handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseMoveEvent.getType()));
		return handlerRegistrationCollection;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchCancelHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchCancelHandler(Widget w, TouchCancelHandler handler) {
		return new HandlerRegistration() {

			@Override
			public void removeHandler() {

			}
		};
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchEndHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchEndHandler(Widget w, TouchEndHandler handler) {
		return w.addDomHandler(new TouchEndToMouseUpHandler(handler), MouseUpEvent.getType());
	}

}
