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

	/**
	 * <p>Constructor for ScrollEndEvent.</p>
	 *
	 * @param x a int.
	 * @param y a int.
	 * @param duration a int.
	 * @param currentX a int.
	 * @param currentY a int.
	 */
	public ScrollEndEvent(int x, int y, int duration, int currentX, int currentY) {
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.currentX = currentX;
		this.currentY = currentY;

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollEndHandler> getAssociatedType() {
		return TYPE;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatch(ScrollEndHandler handler) {
		handler.onScrollEnd(this);

	}

	/**
	 * <p>Getter for the field <code>x</code>.</p>
	 *
	 * @return a int.
	 */
	public int getX() {
		return x;
	}

	/**
	 * <p>Getter for the field <code>y</code>.</p>
	 *
	 * @return a int.
	 */
	public int getY() {
		return y;
	}

	/**
	 * <p>getType</p>
	 *
	 * @return a Type object.
	 */
	public static Type<ScrollEndHandler> getType() {
		return TYPE;
	}

	/**
	 * <p>Getter for the field <code>currentX</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCurrentX() {
		return currentX;
	}

	/**
	 * <p>Getter for the field <code>currentY</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCurrentY() {
		return currentY;
	}

	/**
	 * <p>Getter for the field <code>duration</code>.</p>
	 *
	 * @return a int.
	 */
	public int getDuration() {
		return duration;
	}

	private boolean preventDefault;

	/**
	 * <p>preventDefault</p>
	 */
	public void preventDefault() {
		preventDefault = true;
	}

	/**
	 * <p>isPreventDefault</p>
	 *
	 * @return a boolean.
	 */
	public boolean isPreventDefault() {
		return preventDefault;
	}

}
