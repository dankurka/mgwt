package com.googlecode.mgwt.examples.contact.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class GroupUpdatedEvent extends GwtEvent<GroupUpdatedHandler> {

	private static final Type<GroupUpdatedHandler> TYPE = new Type<GroupUpdatedHandler>();
	private final String groupId;

	public GroupUpdatedEvent(String groupId) {
		this.groupId = groupId;

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GroupUpdatedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GroupUpdatedHandler handler) {
		handler.onGroupUpdated(this);

	}

	public String getGroupId() {
		return groupId;
	}

	public static Type<GroupUpdatedHandler> getType() {
		return TYPE;
	}

}
