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
package com.googlecode.mgwt.dom.client.recognizer.pinch;

import com.google.gwt.event.shared.GwtEvent;

public class PinchEvent extends GwtEvent<PinchHandler> {

	private static final GwtEvent.Type<PinchHandler> TYPE = new Type<PinchHandler>();
	private final int x;
	private final int y;
	private final double scaleFactor;

	public static GwtEvent.Type<PinchHandler> getType() {
		return TYPE;
	}

	public PinchEvent(int x, int y, double scaleFactor) {
		this.x = x;
		this.y = y;
		this.scaleFactor = scaleFactor;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PinchHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PinchHandler handler) {
		handler.onPinch(this);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

}
