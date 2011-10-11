package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.GwtEvent;

public class ScrollStartEvent extends GwtEvent<ScrollStartHandler> {

	private static final Type<ScrollStartHandler> TYPE = new Type<ScrollStartHandler>();

	private final int x;
	private final int y;

	public ScrollStartEvent(int x, int y) {
		this.x = x;
		this.y = y;

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollStartHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ScrollStartHandler handler) {
		handler.onStartScroll(this);

	}

	public static Type<ScrollStartHandler> getType() {
		return TYPE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
