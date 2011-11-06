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
package com.googlecode.mgwt.dom.client.event.mouse;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;

/**
 * Convert TouchMoveHandlers to MouseMoveHandlers for non touch devices or dev
 * mode
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class TouchMoveToMouseMoveHandler implements MouseMoveHandler, MouseDownHandler, MouseUpHandler {

	private boolean ignoreEvent;
	private final TouchMoveHandler touchMoveHandler;

	/**
	 * <p>Constructor for TouchMoveToMouseMoveHandler.</p>
	 *
	 * @param touchMoveHandler a {@link com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler} object.
	 */
	public TouchMoveToMouseMoveHandler(TouchMoveHandler touchMoveHandler) {
		this.touchMoveHandler = touchMoveHandler;
		ignoreEvent = true;
	}

	/** {@inheritDoc} */
	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (ignoreEvent)
			return;
		touchMoveHandler.onTouchMove(new SimulatedTouchMoveEvent(event));

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.MouseUpHandler#onMouseUp(com.google.gwt.event.dom.client.MouseUpEvent)
	 */
	/** {@inheritDoc} */
	@Override
	public void onMouseUp(MouseUpEvent event) {
		ignoreEvent = true;

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google.gwt.event.dom.client.MouseDownEvent)
	 */
	/** {@inheritDoc} */
	@Override
	public void onMouseDown(MouseDownEvent event) {
		ignoreEvent = false;

	}

}
