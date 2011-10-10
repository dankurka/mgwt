package com.googlecode.mgwt.ui.client.widget.scroll;

import com.google.gwt.event.shared.GwtEvent;

public class ScrollEndEvent extends GwtEvent<ScrollEndHandler> {

	private static final Type<ScrollEndHandler> TYPE = new Type<ScrollEndHandler>();
	private final int x;
	private final int y;

	public ScrollEndEvent(int x, int y) {
		this.x = x;
		this.y = y;

	}

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

}
