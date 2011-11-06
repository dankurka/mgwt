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
package com.googlecode.mgwt.mvp.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * This event is fired at the start of an mgwt animation (if
 * {@link AnimatingActivityManager} is told to fire these events, see
 * {@link AnimatingActivityManager#setFireAnimationEvents(boolean)})
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MGWTAnimationStartEvent extends GwtEvent<MGWTAnimationStartHandler> {

	/** Constant <code>TYPE</code> */
	public static final GwtEvent.Type<MGWTAnimationStartHandler> TYPE = new Type<MGWTAnimationStartHandler>();

	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MGWTAnimationStartHandler> getAssociatedType() {
		return TYPE;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatch(MGWTAnimationStartHandler handler) {
		handler.onAnimationStartHandler(this);

	}

	/**
	 * <p>getType</p>
	 *
	 * @return a {@link com.google.gwt.event.shared.GwtEvent.Type} object.
	 */
	public static GwtEvent.Type<MGWTAnimationStartHandler> getType() {
		return TYPE;
	}

}
