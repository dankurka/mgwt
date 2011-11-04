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
 * A scroll end event is fired after a scroll has finished
 * 
 * @author Daniel Kurka
 * 
 */
public class ScrollEndEvent extends GwtEvent<ScrollEndHandler> {

	private static final Type<ScrollEndHandler> TYPE = new Type<ScrollEndHandler>();
	private final int x;
	private final int y;
	private final int duration;
	private final int currentX;
	private final int currentY;

	public ScrollEndEvent(int x, int y, int duration, int currentX, int currentY) {
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.currentX = currentX;
		this.currentY = currentY;

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollEndHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ScrollEndHandler handler) {
		handler.onScrollEnd(this);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static Type<ScrollEndHandler> getType() {
		return TYPE;
	}

	public int getCurrentX() {
		return currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public int getDuration() {
		return duration;
	}

	private boolean preventDefault;

	public void preventDefault() {
		preventDefault = true;
	}

	public boolean isPreventDefault() {
		return preventDefault;
	}

}
