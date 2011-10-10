package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.DetailActivity;

public class PullToRefreshActivity extends DetailActivity {

	private final ClientFactory clientFactory;

	public PullToRefreshActivity(ClientFactory clientFactory) {
		super(clientFactory.getPullToRefreshDisplay(), "nav");
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);

		PullToRefreshDisplay display = clientFactory.getPullToRefreshDisplay();

		panel.setWidget(display);
	}

}
