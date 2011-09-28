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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;


/**
 * @author Daniel Kurka
 *
 */
public class SimulatedTouchStartEvent extends TouchStartEvent {
	private int x;
	private int y;

	/**
	 * 
	 */
	public SimulatedTouchStartEvent(MouseDownEvent event) {
		x = event.getClientX();
		y = event.getClientY();
		setNativeEvent(event.getNativeEvent());

	}

	@Override
	protected native JsArray<Touch> touches(NativeEvent nativeEvent) /*-{
		var touch = {};

		touch.pageX = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchStartEvent::x;
		touch.pageY = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchStartEvent::y;

		var toucharray = [];

		toucharray.push(touch);

		return toucharray;
	}-*/;

	@Override
	protected native JsArray<Touch> changedTouches(NativeEvent nativeEvent) /*-{
		var touch = {};

		touch.pageX = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchStartEvent::x;
		touch.pageY = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchStartEvent::y;

		var toucharray = [];

		toucharray.push(touch);

		return toucharray;
	}-*/;
}
