package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.contact.client.ClientFactory;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;


public class AddGroupActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	public AddGroupActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(clientFactory.getAddGroupDisplay());
	}

}
