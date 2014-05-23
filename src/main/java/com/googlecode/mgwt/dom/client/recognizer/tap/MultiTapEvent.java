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
package com.googlecode.mgwt.dom.client.recognizer.tap;

import com.google.gwt.event.shared.GwtEvent;

import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.TouchCopy;

/**
 * A {@link MultiTapEvent} occurs if the taps multiple times on the screen
 *
 * @author Daniel Kurka
 *
 */
public class MultiTapEvent extends GwtEvent<MultiTapHandler> {

	private static final GwtEvent.Type<MultiTapHandler> TYPE = new Type<MultiTapHandler>();

	public static GwtEvent.Type<MultiTapHandler> getType() {
		return TYPE;
	}

	private final int numberOfFingers;
	private final LightArray<LightArray<TouchCopy>> touchStarts;
	private final int numberOfTaps;

	/**
	 * Construct a Multitap event
	 *
	 * @param numberOfFingers the number of fingers that tapped on the screen
	 * @param numberOfTaps the number of times the screen was tapped
	 * @param touchStarts the position of the fingers that started the taps
	 */
	public MultiTapEvent(int numberOfFingers, int numberOfTaps, LightArray<LightArray<TouchCopy>> touchStarts) {
		this.numberOfFingers = numberOfFingers;
		this.numberOfTaps = numberOfTaps;
		this.touchStarts = touchStarts;
	}

	/**
	 * get the number of fingers that tapped on the screen
	 *
	 * @return the number of fingers that tapped on the screen
	 */
	public int getNumberOfFinders() {
		return numberOfFingers;
	}

	/**
	 * get the position of the fingers that started the taps
	 *
	 * @return the position of the fingers that started the taps
	 */
	public LightArray<LightArray<TouchCopy>> getTouchStarts() {
		return touchStarts;
	}

	/**
	 * get the number of times the screen was tapped
	 *
	 * @return the number of times the screen was tapped
	 */
	public int getNumberOfTabs() {
		return numberOfTaps;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MultiTapHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MultiTapHandler handler) {
		handler.onMultiTap(this);
	}
}
