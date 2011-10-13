package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.GwtEvent;

public class PullReleasedEvent extends GwtEvent<PullReleasedHandler> {

	private static final Type<PullReleasedHandler> TYPE = new Type<PullReleasedHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PullReleasedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PullReleasedHandler handler) {
		handler.onPullReleased(this);

	}

	public static Type<PullReleasedHandler> getType() {
		return TYPE;
	}

}
