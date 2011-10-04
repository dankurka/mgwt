package com.googlecode.mgwt.examples.contact.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.examples.contact.client.module.Group;

public interface GroupOverViewDisplay extends IsWidget {

	void renderTopics(List<Group> list);

	HasSimpleTouchHandler getPlusButton();

	void setPresenter(GroupOverViewPresenter presenter);

	public interface GroupOverViewPresenter {
		public void onPlusButton();

		public void onListItemSelected(int index);
	}

}
