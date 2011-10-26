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
package com.googlecode.mgwt.dom.client.event.touch.simple;

import com.google.gwt.event.shared.GwtEvent;

/**
 * SimpleTouchEvent is considered an activation event something like a normal
 * "click event". Like a button, but with touch events.
 * 
 * @author Daniel Kurka
 * 
 */
public class SimpleTouchEvent extends GwtEvent<SimpleTouchHandler> {

	private static final Type<SimpleTouchHandler> TYPE = new Type<SimpleTouchHandler>();

	public SimpleTouchEvent() {

	}

	/**
	 * Gets the event type associated with animation end events.
	 * 
	 * @return the handler type
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SimpleTouchHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SimpleTouchHandler handler) {
		handler.onTouch(this);

	}

	public static Type<SimpleTouchHandler> getType() {
		return TYPE;
	}

}
