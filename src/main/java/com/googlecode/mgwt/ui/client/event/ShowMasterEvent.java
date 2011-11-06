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
package com.googlecode.mgwt.ui.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * <p>ShowMasterEvent class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class ShowMasterEvent extends GwtEvent<ShowMasterHandler> {
	private static final Type<ShowMasterHandler> TYPE = new Type<ShowMasterHandler>();
	private final String id;

	/**
	 * <p>Constructor for ShowMasterEvent.</p>
	 *
	 * @param id a {@link java.lang.String} object.
	 */
	public ShowMasterEvent(String id) {
		this.id = id;

	}

	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowMasterHandler> getAssociatedType() {
		return TYPE;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatch(ShowMasterHandler handler) {
		handler.onShowMaster(this);

	}

	/**
	 * <p>getType</p>
	 *
	 * @return a Type object.
	 */
	public static Type<ShowMasterHandler> getType() {
		return TYPE;
	}

	/**
	 * <p>Getter for the field <code>id</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getId() {
		return id;
	}
}
