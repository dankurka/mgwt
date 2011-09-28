package com.googlecode.mgwt.examples.contact.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.examples.contact.client.Topic;


public interface OverviewDisplay extends IsWidget {

	void renderTopics(List<Topic> list);

	HasSimpleTouchHandler getPlusButton();

	void setPresenter(OverviewPresenter presenter);

	public interface OverviewPresenter {
		public void onPlusButton();
	}

}
