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
 * The scroll event is fired while scrolling
 * 
 * @author Daniel Kurka
 * 
 */
public class ScrollEvent extends GwtEvent<ScrollHandler> {

	private static final GwtEvent.Type<ScrollHandler> TYPE = new Type<ScrollHandler>();
	private final int posX;
	private final int posY;

	/**
	 * Construct a scroll event
	 * 
	 * @param posX
	 *            the x position of the scroller
	 * @param posY
	 *            the y position of the scroller
	 */
	public ScrollEvent(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollHandler> getAssociatedType() {
		return TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dispatch(ScrollHandler handler) {
		handler.onScroll(this);

	}

	/**
	 * Get the type of the event for generic handler registration
	 * 
	 * @return the type of the event
	 */
	public static GwtEvent.Type<ScrollHandler> getType() {
		return TYPE;
	}

	/**
	 * The x position of the scroll event
	 * 
	 * @return the x position of the scroll event
	 */
	public int getX() {
		return posX;
	}

	/**
	 * get the y position of the scroll event
	 * 
	 * @return the y position of the scroll event
	 */
	public int getY() {
		return posY;
	}

}
