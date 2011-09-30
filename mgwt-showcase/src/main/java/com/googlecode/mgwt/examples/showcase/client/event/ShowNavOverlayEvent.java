package com.googlecode.mgwt.examples.showcase.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowNavOverlayEvent extends GwtEvent<ShowNavOverlayHandler> {

	private static final Type<ShowNavOverlayHandler> TYPE = new Type<ShowNavOverlayHandler>();

	public ShowNavOverlayEvent() {

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowNavOverlayHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowNavOverlayHandler handler) {
		handler.onShowNavOverlay(this);

	}

	public static Type<ShowNavOverlayHandler> getType() {
		return TYPE;
	}

}
