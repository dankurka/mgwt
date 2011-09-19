package de.kurka.mobile.contact.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.mobile.contact.client.Topic;

public interface OverviewDisplay extends IsWidget {

	void renderTopics(List<Topic> list);

	HasSimpleTouchHandler getPlusButton();

}
