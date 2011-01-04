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

import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;

/**
 * @author kurt
 *
 */
public class SimpleTouchToNativeTouchHandler implements TouchCancelHandler, TouchEndHandler, TouchMoveHandler, TouchStartHandler {

	private final SimpleTouchHandler simpleTouchHandler;

	private boolean touchCanceled = false;
	private boolean hasMoved = false;

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
		//event.preventDefault();

	}

	@Override
	public void onTouchMove(TouchMoveEvent event) {

		hasMoved = true;
		//event.preventDefault();

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {

		//event.preventDefault();
		if (!hasMoved && !touchCanceled)
			simpleTouchHandler.onTouch();

	}

	@Override
	public void onTouchCanceled(TouchCancelEvent event) {
		//event.preventDefault();
		touchCanceled = true;

	}

}
