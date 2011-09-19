package de.kurka.mobile.contact.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.mvp.client.MGWTAbstractActivity;
import de.kurka.mobile.contact.client.ClientFactory;

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
