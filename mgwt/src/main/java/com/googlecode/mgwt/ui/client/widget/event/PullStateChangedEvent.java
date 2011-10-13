package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.GwtEvent;

public class PullStateChangedEvent extends GwtEvent<PullStateChangedHandler> {

	private static final Type<PullStateChangedHandler> TYPE = new Type<PullStateChangedHandler>();
	private final State state;

	public enum State {
		PULL_RELEASE, NO_ACTION
	};

	public PullStateChangedEvent(State state) {
		this.state = state;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PullStateChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PullStateChangedHandler handler) {
		handler.onPullStateChanged(this);

	}

	public static Type<PullStateChangedHandler> getType() {
		return TYPE;
	}

	public State getState() {
		return state;
	}

}
