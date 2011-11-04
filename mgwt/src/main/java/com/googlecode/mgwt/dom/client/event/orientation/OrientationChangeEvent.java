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
package com.googlecode.mgwt.dom.client.event.orientation;

import com.google.gwt.event.shared.GwtEvent;

/**
 * <p>OrientationChangeEvent class.</p>
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class OrientationChangeEvent extends GwtEvent<OrientationChangeHandler> {

	private static final GwtEvent.Type<OrientationChangeHandler> TYPE = new Type<OrientationChangeHandler>();
	private final ORIENTATION orientation;

	public enum ORIENTATION {
		PORTRAIT, LANDSCAPE
	}

	/**
	 * <p>Constructor for OrientationChangeEvent.</p>
	 *
	 * @param orientation a {@link com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION} object.
	 */
	public OrientationChangeEvent(ORIENTATION orientation) {
		this.orientation = orientation;

	}

	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<OrientationChangeHandler> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	/** {@inheritDoc} */
	@Override
	protected void dispatch(OrientationChangeHandler handler) {
		handler.onOrientationChanged(this);

	}

	/**
	 * <p>Getter for the field <code>orientation</code>.</p>
	 *
	 * @return a {@link com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION} object.
	 */
	public ORIENTATION getOrientation() {
		return orientation;
	}

	/**
	 * <p>getType</p>
	 *
	 * @return a Type object.
	 */
	public static Type<OrientationChangeHandler> getType() {
		return TYPE;
	}

	/**
	 * @return
	 */

}
