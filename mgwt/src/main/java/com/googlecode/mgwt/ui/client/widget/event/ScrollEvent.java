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
 * <p>ScrollEvent class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class ScrollEvent extends GwtEvent<ScrollHandler> {

	/** Constant <code>TYPE</code> */
	public static final GwtEvent.Type<ScrollHandler> TYPE = new Type<ScrollHandler>();
	private final int posX;
	private final int posY;

	/**
	 * <p>Constructor for ScrollEvent.</p>
	 *
	 * @param posX a int.
	 * @param posY a int.
	 */
	public ScrollEvent(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

	}

	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollHandler> getAssociatedType() {
		return TYPE;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatch(ScrollHandler handler) {
		handler.onScroll(this);

	}

	/**
	 * <p>getType</p>
	 *
	 * @return a {@link com.google.gwt.event.shared.GwtEvent.Type} object.
	 */
	public static GwtEvent.Type<ScrollHandler> getType() {
		return TYPE;
	}

	/**
	 * <p>getX</p>
	 *
	 * @return a int.
	 */
	public int getX() {
		return posX;
	}

	/**
	 * <p>getY</p>
	 *
	 * @return a int.
	 */
	public int getY() {
		return posY;
	}

}
