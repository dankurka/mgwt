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
 * <p>ScrollStartEvent class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class ScrollStartEvent extends GwtEvent<ScrollStartHandler> {

	private static final Type<ScrollStartHandler> TYPE = new Type<ScrollStartHandler>();

	private final int x;
	private final int y;

	/**
	 * <p>Constructor for ScrollStartEvent.</p>
	 *
	 * @param x a int.
	 * @param y a int.
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
	 * <p>getType</p>
	 *
	 * @return a Type object.
	 */
	public static Type<ScrollStartHandler> getType() {
		return TYPE;
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

}
