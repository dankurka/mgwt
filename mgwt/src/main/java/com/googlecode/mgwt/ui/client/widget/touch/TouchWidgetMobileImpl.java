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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;

/**
 * The implementation for touch devices of {@link TouchWidgetImpl}
 * 
 * @author Daniel Kurka
 * 
 */
public class TouchWidgetMobileImpl implements TouchWidgetImpl {

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchStartHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler)
	 */
	@Override
	public HandlerRegistration addTouchStartHandler(Widget w, TouchStartHandler handler) {
		return w.addDomHandler(handler, TouchStartEvent.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchMoveHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler)
	 */
	@Override
	public HandlerRegistration addTouchMoveHandler(Widget w, TouchMoveHandler handler) {
		return w.addDomHandler(handler, TouchMoveEvent.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchCancelHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler)
	 */
	@Override
	public HandlerRegistration addTouchCancelHandler(Widget w, TouchCancelHandler handler) {
		return w.addDomHandler(handler, TouchCancelEvent.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl#addTouchEndHandler(com.google.gwt.user.client.ui.Widget, com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler)
	 */
	@Override
	public HandlerRegistration addTouchEndHandler(Widget w, TouchEndHandler handler) {
		return w.addDomHandler(handler, TouchEndEvent.getType());
	}

}
