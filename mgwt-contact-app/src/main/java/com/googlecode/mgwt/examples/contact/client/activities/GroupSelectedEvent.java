package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.event.shared.GwtEvent;

public class GroupSelectedEvent extends GwtEvent<GroupSelectedHandler> {

	private final String groupId;

	private static final GwtEvent.Type<GroupSelectedHandler> TYPE = new GwtEvent.Type<GroupSelectedHandler>();

	public GroupSelectedEvent(String groupId) {
		this.groupId = groupId;

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GroupSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GroupSelectedHandler handler) {
		handler.onGroupSelected(this);

	}

	public String getGroupId() {
		return groupId;
	}

	public static GwtEvent.Type<GroupSelectedHandler> getType() {
		return TYPE;
	}

}
