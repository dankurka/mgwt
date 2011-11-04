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
package com.googlecode.mgwt.dom.client.event.touch;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.EventHandler;

/**
 * BaseClass for all TouchEvents
 *
 * @author Daniel Kurka
 * @param <H> the event handler to associate with this event
 * @version $Id: $
 */
public abstract class TouchEvent<H extends EventHandler> extends DomEvent<H> {

	/**
	 * <p>touches</p>
	 *
	 * @param <H> a H object.
	 * @return a {@link com.google.gwt.core.client.JsArray} object.
	 */
	public JsArray<Touch> touches() {
		return touches(getNativeEvent());
	}

	/**
	 * <p>touches</p>
	 *
	 * @param nativeEvent a {@link com.google.gwt.dom.client.NativeEvent} object.
	 * @return a {@link com.google.gwt.core.client.JsArray} object.
	 */
	protected native JsArray<Touch> touches(NativeEvent nativeEvent) /*-{
		return nativeEvent.touches;
	}-*/;

	/**
	 * get the changed touches
	 *
	 * @return the array of changed touches
	 */
	public JsArray<Touch> changedTouches() {
		return changedTouches(getNativeEvent());
	}

	/**
	 * <p>changedTouches</p>
	 *
	 * @param nativeEvent a {@link com.google.gwt.dom.client.NativeEvent} object.
	 * @return a {@link com.google.gwt.core.client.JsArray} object.
	 */
	protected native JsArray<Touch> changedTouches(NativeEvent nativeEvent) /*-{
		return nativeEvent.changedTouches;
	}-*/;

}
