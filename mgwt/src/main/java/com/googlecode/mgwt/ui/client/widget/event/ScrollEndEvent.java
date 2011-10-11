package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.GwtEvent;

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
