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
 * A scroll end event is fired after a scroll has finished
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class ScrollEndEvent extends GwtEvent<ScrollEndHandler> {

	private static final Type<ScrollEndHandler> TYPE = new Type<ScrollEndHandler>();
	private final int x;
	private final int y;
	private final int duration;
	private final int currentX;
	private final int currentY;
	private boolean preventDefault;

	/**
	 * Construct a scroll end event
	 * 
	 * @param x
	 *            the end position of the scroll
	 * @param y
	 *            the end position of the scroll
	 * @param duration
	 *            the remaining time to reach the end position
	 * @param currentX
	 *            the current x position
	 * @param currentY
	 *            the current y position
	 */
	public ScrollEndEvent(int x, int y, int duration, int currentX, int currentY) {
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.currentX = currentX;
		this.currentY = currentY;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollEndHandler> getAssociatedType() {
		return TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dispatch(ScrollEndHandler handler) {
		handler.onScrollEnd(this);

	}

	/**
	 * get the x end position of the scroll
	 * 
	 * @return the x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * get the y end position of the scroll
	 * 
	 * @return the y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * get the type of the event
	 * 
	 * @return the type of the event
	 */
	public static Type<ScrollEndHandler> getType() {
		return TYPE;
	}

	/**
	 * get the current x position of the scroller
	 * 
	 * @return the current x position
	 */
	public int getCurrentX() {
		return currentX;
	}

	/**
	 * get the current y position of the scroller
	 * 
	 * @return the current y position
	 */
	public int getCurrentY() {
		return currentY;
	}

	/**
	 * the duration in ms to reach to end position
	 * 
	 * @return the duration in ms to reach the end position
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * should scrolling the end position be prevented?
	 */
	public void preventDefault() {
		preventDefault = true;
	}

	/**
	 * should scrolling the end position be prevented?
	 * 
	 * @return true if someone called {@link #preventDefault()}
	 */
	public boolean isPreventDefault() {
		return preventDefault;
	}

}
