package com.googlecode.mgwt.ui.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowMasterEvent extends GwtEvent<ShowMasterHandler> {
	private static final Type<ShowMasterHandler> TYPE = new Type<ShowMasterHandler>();
	private final String id;

	public ShowMasterEvent(String id) {
		this.id = id;

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowMasterHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowMasterHandler handler) {
		handler.onShowMaster(this);

	}

	public static Type<ShowMasterHandler> getType() {
		return TYPE;
	}

	public String getId() {
		return id;
	}
}
