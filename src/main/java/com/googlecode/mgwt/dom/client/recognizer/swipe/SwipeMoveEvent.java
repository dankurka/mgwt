/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.dom.client.recognizer.swipe;

import com.google.gwt.event.shared.GwtEvent;

public class SwipeMoveEvent extends SwipeEvent<SwipeMoveHandler> {

	private final GwtEvent.Type<SwipeMoveHandler> TYPE = new Type<SwipeMoveHandler>();
	private final boolean distanceReached;
	private final int distance;

	public GwtEvent.Type<SwipeMoveHandler> getTYPE() {
		return TYPE;
	}

	public SwipeMoveEvent(boolean distanceReached, int distance, SwipeEvent.DIRECTION direction) {
		super(direction);
		this.distanceReached = distanceReached;
		this.distance = distance;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SwipeMoveHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SwipeMoveHandler handler) {
		handler.onSwipeMove(this);

	}

	public int getDistance() {
		return distance;
	}

	public boolean isDistanceReached() {
		return distanceReached;
	}

}
