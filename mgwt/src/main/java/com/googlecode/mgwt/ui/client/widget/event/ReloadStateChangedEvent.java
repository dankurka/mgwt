package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.GwtEvent;

public class ReloadStateChangedEvent extends GwtEvent<ReloadStateChangedHandler> {

	private static final Type<ReloadStateChangedHandler> TYPE = new Type<ReloadStateChangedHandler>();
	private final State state;

	public enum State {
		RELOAD, NO_RELOAD
	};

	public ReloadStateChangedEvent(State state) {
		this.state = state;

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ReloadStateChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ReloadStateChangedHandler handler) {
		handler.onReloadStateChanged(this);

	}

	public static Type<ReloadStateChangedHandler> getType() {
		return TYPE;
	}

	public State getState() {
		return state;
	}

}
