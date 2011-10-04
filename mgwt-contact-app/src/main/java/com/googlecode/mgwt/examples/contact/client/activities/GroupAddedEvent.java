package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.event.shared.GwtEvent;
import com.googlecode.mgwt.examples.contact.client.module.Group;

public class GroupAddedEvent extends GwtEvent<GroupAddedHandler> {

	private static GwtEvent.Type<GroupAddedHandler> TYPE = new Type<GroupAddedHandler>();
	private final Group group;

	public GroupAddedEvent(Group group) {
		this.group = group;

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GroupAddedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GroupAddedHandler handler) {
		handler.onGroupAdded(this);

	}

	public static GwtEvent.Type<GroupAddedHandler> getType() {
		return TYPE;
	}

	public Group getGroup() {
		return group;
	}

}
