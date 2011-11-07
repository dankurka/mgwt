/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * A Scroll start event is fired, when the scrolling starts
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class ScrollStartEvent extends GwtEvent<ScrollStartHandler> {

	private static final Type<ScrollStartHandler> TYPE = new Type<ScrollStartHandler>();

	private final int x;
	private final int y;

	/**
	 * Construct a scroll start event
	 * 
	 * @param x
	 *            the x position of the touch where the scrolling started
	 * @param y
	 *            the y position of the touch where the scrolling started
	 */
	public ScrollStartEvent(int x, int y) {
		this.x = x;
		this.y = y;

	}

	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollStartHandler> getAssociatedType() {
		return TYPE;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatch(ScrollStartHandler handler) {
		handler.onStartScroll(this);

	}

	/**
	 * <p>
	 * getType
	 * </p>
	 * 
	 * @return a Type object.
	 */
	public static Type<ScrollStartHandler> getType() {
		return TYPE;
	}

	/**
	 * Get the x position of the scroll start
	 * 
	 * @return the x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y position of the scroll start
	 * 
	 * @return the y position
	 */
	public int getY() {
		return y;
	}

}
