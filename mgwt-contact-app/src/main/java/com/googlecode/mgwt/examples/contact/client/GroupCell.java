package com.googlecode.mgwt.examples.contact.client;

import com.googlecode.mgwt.examples.contact.client.module.Group;

public class GroupCell extends BasicCell<Group> {

	@Override
	public String getDisplayString(Group model) {
		return model.getName();
	}

	@Override
	public boolean canBeSelected(Group model) {
		return true;
	}

}
