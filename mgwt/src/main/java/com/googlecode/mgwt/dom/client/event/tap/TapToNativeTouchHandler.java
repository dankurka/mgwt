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
package com.googlecode.mgwt.dom.client.event.tap;

import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;

/**
 * Utility class for adding a simple touch handler on a widget that only
 * supports {@link HasTouchHandlers}
 * 
 * @author Daniel Kurka
 * 
 */
public class TapToNativeTouchHandler implements TouchCancelHandler, TouchEndHandler, TouchMoveHandler, TouchStartHandler {

	private final TapHandler tapHandler;

	private boolean touchCanceled = false;
	private boolean hasMoved = false;
	private int x;
	private int y;

	/**
	 * 
	 */
	public TapToNativeTouchHandler(TapHandler tapHandler) {
		this.tapHandler = tapHandler;

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
		if (Math.abs(touch.getPageX() - x) > Tap.RADIUS || Math.abs(touch.getPageY() - y) > Tap.RADIUS) {
			hasMoved = true;
		}

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		if (!hasMoved && !touchCanceled)
			tapHandler.onTap(new TapEvent());

	}

	@Override
	public void onTouchCanceled(TouchCancelEvent event) {
		touchCanceled = true;

	}

}
