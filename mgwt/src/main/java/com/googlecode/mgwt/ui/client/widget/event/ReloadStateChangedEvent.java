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
 * a reload state changed event is fired when the state of a refresh panel
 * changes
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class ReloadStateChangedEvent extends GwtEvent<ReloadStateChangedHandler> {

	private static final Type<ReloadStateChangedHandler> TYPE = new Type<ReloadStateChangedHandler>();
	private final State state;

	public enum State {
		RELOAD, NO_RELOAD
	};

	/**
	 * <p>Constructor for ReloadStateChangedEvent.</p>
	 *
	 * @param state a {@link com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedEvent.State} object.
	 */
	public ReloadStateChangedEvent(State state) {
		this.state = state;

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ReloadStateChangedHandler> getAssociatedType() {
		return TYPE;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatch(ReloadStateChangedHandler handler) {
		handler.onReloadStateChanged(this);

	}

	/**
	 * <p>getType</p>
	 *
	 * @return a Type object.
	 */
	public static Type<ReloadStateChangedHandler> getType() {
		return TYPE;
	}

	/**
	 * <p>Getter for the field <code>state</code>.</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedEvent.State} object.
	 */
	public State getState() {
		return state;
	}

}
