/*
 * Copyright 2012 Daniel Kurka
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

import com.google.gwt.core.client.JavaScriptObject;

public class JsTouch extends JavaScriptObject implements Touch {
	/**
	 * <p>
	 * Constructor for Touch.
	 * </p>
	 */
	protected JsTouch() {
	}

	/**
	 * The X position of the touch within the current document
	 * 
	 * @return the current x position of the touch
	 */
	public final native int getPageX() /*-{
		return this.pageX;
	}-*/;

	/**
	 * The Y position of the touch within the current document
	 * 
	 * @return the current y position of the touch
	 */
	public final native int getPageY() /*-{
		return this.pageY;
	}-*/;

	@Override
	public native int getIdentifier() /*-{
		return this.identifier;
	}-*/;
}
