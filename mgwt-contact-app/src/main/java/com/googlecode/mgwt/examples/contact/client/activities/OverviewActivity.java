package com.googlecode.mgwt.examples.contact.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.contact.client.ClientFactory;
import com.googlecode.mgwt.examples.contact.client.Topic;
import com.googlecode.mgwt.examples.contact.client.activities.OverviewDisplay.OverviewPresenter;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;


public class OverviewActivity extends MGWTAbstractActivity implements OverviewPresenter {

	private final ClientFactory clientFactory;

	public OverviewActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		OverviewDisplay display = clientFactory.getOverviewDisplay();

		display.setPresenter(this);

		List<Topic> list = new ArrayList<Topic>();

		list.add(new Topic("test"));
		list.add(new Topic("test"));
		list.add(new Topic("test"));

		display.renderTopics(list);

		panel.setWidget(display);

	}

	@Override
	public void onPlusButton() {
		clientFactory.getPlaceController().goTo(new AddGroupPlace());

	}

}
