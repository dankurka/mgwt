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
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.googlecode.mgwt.collection.client.JsLightArray;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.JsTouch;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;

/**
 * A simulated TouchEndEvent is really a mouseup event. This is used mostly in
 * dev mode and for blackberry devices to handle them equally to real touch
 * devices
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class SimulatedTouchEndEvent extends TouchEndEvent {

	private final int x;
	private final int y;

	/**
	 * Construct a simluated TouchEndEvent from a {@link MouseUpEvent}
	 * 
	 * @param mouseUpEvent the data for the simulated event;
	 */
	public SimulatedTouchEndEvent(MouseUpEvent mouseUpEvent) {
		x = mouseUpEvent.getClientX();
		y = mouseUpEvent.getClientY();
		setNativeEvent(mouseUpEvent.getNativeEvent());
		setSource(mouseUpEvent.getSource());
	}

	@Override
	public LightArray<Touch> getTouches() {
		return new JsLightArray<Touch>(touches(getNativeEvent()));
	}

	@Override
	public LightArray<Touch> getChangedTouches() {
		return new JsLightArray<Touch>(changedTouches(getNativeEvent()));
	}

	/** {@inheritDoc} */
	@Override
	protected native JsArray<JsTouch> touches(NativeEvent nativeEvent) /*-{
		var touch = {};

		touch.pageX = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchEndEvent::x;
		touch.pageY = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchEndEvent::y;

		var toucharray = [];

		toucharray.push(touch);

		return toucharray;
	}-*/;

	/** {@inheritDoc} */
	@Override
	protected native JsArray<JsTouch> changedTouches(NativeEvent nativeEvent) /*-{
		var touch = {};

		touch.pageX = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchEndEvent::x;
		touch.pageY = this.@com.googlecode.mgwt.dom.client.event.mouse.SimulatedTouchEndEvent::y;

		var toucharray = [];

		toucharray.push(touch);

		return toucharray;
	}-*/;
}
