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
 * @author Daniel Kurka
 * @version $Id: $
 */
public class TapEvent extends GwtEvent<TapHandler> {

	private static final Type<TapHandler> TYPE = new Type<TapHandler>();

	/**
	 * <p>Constructor for TapEvent.</p>
	 */
	public TapEvent() {

	}

	/**
	 * {@inheritDoc}
	 *
	 * Gets the event type associated with animation end events.
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TapHandler> getAssociatedType() {
		return TYPE;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatch(TapHandler handler) {
		handler.onTap(this);

	}

	/**
	 * <p>getType</p>
	 *
	 * @return a Type object.
	 */
	public static Type<TapHandler> getType() {
		return TYPE;
	}

}
