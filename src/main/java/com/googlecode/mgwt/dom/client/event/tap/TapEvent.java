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

import com.google.gwt.event.shared.GwtEvent;

/**
 * TapEvent is considered an activation event something like a normal
 * "click event". Like a button, but with touch events.
 * 
 * 
 * @author Daniel Kurka
 */

public class TapEvent extends GwtEvent<TapHandler> {

	private static final Type<TapHandler> TYPE = new Type<TapHandler>();
	private final int startX;
	private final int startY;

	public TapEvent(Object source, int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
		setSource(source);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TapHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(TapHandler handler) {
		handler.onTap(this);

	}

	public static Type<TapHandler> getType() {
		return TYPE;
	}

	/**
	 * get the x start position of the tap
	 * 
	 * @return the x start position of the tap
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * get the y start position of the tap
	 * 
	 * @return the y start position of the tap
	 */
	public int getStartY() {
		return startY;
	}

}
