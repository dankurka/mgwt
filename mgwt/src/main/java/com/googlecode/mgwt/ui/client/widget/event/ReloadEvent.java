package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.GwtEvent;

public class ReloadEvent extends GwtEvent<ReloadHandler> {

	private static final Type<ReloadHandler> TYPE = new Type<ReloadHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ReloadHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ReloadHandler handler) {
		handler.onReload(this);

	}

	public static Type<ReloadHandler> getType() {
		return TYPE;
	}

}
