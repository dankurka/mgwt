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
 * A pull state changed event is fired when the status of a pull panel changes
 * 
 * @author Daniel Kurka
 * 
 */
public class PullStateChangedEvent extends GwtEvent<PullStateChangedHandler> {

	private static final Type<PullStateChangedHandler> TYPE = new Type<PullStateChangedHandler>();
	private final State state;

	public enum State {
		PULL_RELEASE, NO_ACTION
	};

	public PullStateChangedEvent(State state) {
		this.state = state;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PullStateChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PullStateChangedHandler handler) {
		handler.onPullStateChanged(this);

	}

	public static Type<PullStateChangedHandler> getType() {
		return TYPE;
	}

	/**
	 * The current state of the pull panel
	 * 
	 * @return
	 */
	public State getState() {
		return state;
	}

}
