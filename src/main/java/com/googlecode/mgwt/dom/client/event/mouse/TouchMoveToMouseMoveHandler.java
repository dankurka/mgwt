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
import com.google.gwt.event.dom.client.TouchMoveHandler;

/**
 * Convert TouchMoveHandlers to MouseMoveHandlers for non touch devices or dev
 * mode
 *
 * @author Daniel Kurka
 */
public class TouchMoveToMouseMoveHandler implements MouseMoveHandler, MouseDownHandler, MouseUpHandler {

	private boolean ignoreEvent;
	private final TouchMoveHandler touchMoveHandler;

	public TouchMoveToMouseMoveHandler(TouchMoveHandler touchMoveHandler) {
		this.touchMoveHandler = touchMoveHandler;
		ignoreEvent = true;
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (ignoreEvent)
			return;
		touchMoveHandler.onTouchMove(new SimulatedTouchMoveEvent(event));
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		ignoreEvent = true;
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		ignoreEvent = false;
	}
}
