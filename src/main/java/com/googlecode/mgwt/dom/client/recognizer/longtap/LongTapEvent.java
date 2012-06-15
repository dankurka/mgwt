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
package com.googlecode.mgwt.dom.client.recognizer.longtap;

import com.google.gwt.event.shared.GwtEvent;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;

public class LongTapEvent extends GwtEvent<LongTapHandler> {

	private static final Type<LongTapHandler> TYPE = new Type<LongTapHandler>();
	private final LightArray<Touch> startPositions;
	private final int numberOfFingers;
	private final int time;

	public LongTapEvent(Object source, int numberOfFingers, int time, LightArray<Touch> startPositions) {
		this.numberOfFingers = numberOfFingers;
		this.time = time;
		this.startPositions = startPositions;
		setSource(source);

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LongTapHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LongTapHandler handler) {
		handler.onLongTap(this);

	}

	public static Type<LongTapHandler> getType() {
		return TYPE;
	}

	public int getNumberOfFingers() {
		return numberOfFingers;
	}

	public LightArray<Touch> getStartPositions() {
		return startPositions;
	}

	public int getTime() {
		return time;
	}

}
