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
package de.kurka.gwt.mobile.dom.client.event.touch.simple;

import de.kurka.gwt.mobile.dom.client.event.touch.Touch;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;

/**
 * @author Daniel Kurka
 * 
 */
public class SimpleTouchToNativeTouchHandler implements TouchCancelHandler, TouchEndHandler, TouchMoveHandler, TouchStartHandler {

	private final SimpleTouchHandler simpleTouchHandler;

	private boolean touchCanceled = false;
	private boolean hasMoved = false;
	private int x;
	private int y;

	/**
	 * 
	 */
	public SimpleTouchToNativeTouchHandler(SimpleTouchHandler simpleTouchHandler) {
		this.simpleTouchHandler = simpleTouchHandler;

	}

	@Override
	public void onTouchStart(TouchStartEvent event) {

		touchCanceled = false;
		hasMoved = false;
		x = event.touches().get(0).getPageX();
		y = event.touches().get(0).getPageY();

	}

	@Override
	public void onTouchMove(TouchMoveEvent event) {
		Touch touch = event.touches().get(0);
		if (Math.abs(touch.getPageX() - x) > SimpleTouch.TOUCH_RADIUS || Math.abs(touch.getPageY() - y) > SimpleTouch.TOUCH_RADIUS) {
			hasMoved = true;
		}

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		if (!hasMoved && !touchCanceled)
			simpleTouchHandler.onTouch(new SimpleTouchEvent());

	}

	@Override
	public void onTouchCanceled(TouchCancelEvent event) {
		touchCanceled = true;

	}

}
