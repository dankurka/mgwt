package com.googlecode.mgwt.ui.client.widget.scroll;

import com.google.gwt.event.shared.GwtEvent;

public class ScrollEvent extends GwtEvent<ScrollHandler> {

	public static final GwtEvent.Type<ScrollHandler> TYPE = new Type<ScrollHandler>();
	private final int posX;
	private final int posY;

	public ScrollEvent(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ScrollHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ScrollHandler handler) {
		handler.onScroll(this);

	}

	public static GwtEvent.Type<ScrollHandler> getType() {
		return TYPE;
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

}
