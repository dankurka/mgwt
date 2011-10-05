package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.contact.client.ClientFactory;
import com.googlecode.mgwt.examples.contact.client.StoreException;
import com.googlecode.mgwt.examples.contact.client.events.GroupUpdatedEvent;
import com.googlecode.mgwt.examples.contact.client.module.Group;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class ShowGroupActivity extends MGWTAbstractActivity implements ShowGroupDisplay.Presenter {

	private final ClientFactory clientFactory;

	public ShowGroupActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Place place = clientFactory.getPlaceController().getWhere();

		if (place instanceof ShowGroupPlace) {
			ShowGroupPlace showGroupPlace = (ShowGroupPlace) place;
			String groupId = showGroupPlace.getId();

			try {
				Group group = clientFactory.getStorage().getGroup(groupId);
				ShowGroupDisplay display = clientFactory.getShowGroupDisplay();

				panel.setWidget(display);
				display.setPresenter(this);
				display.edit(group);
			} catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Window.alert("doh!");
		}
	}

	@Override
	public void onErrors() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEditComplete(Group group) {
		try {
			clientFactory.getStorage().updateGroup(group);

			clientFactory.getEventBus().fireEvent(new GroupUpdatedEvent(group.getId()));
			clientFactory.getPlaceController().goTo(new ShowGroupPlace(group.getId()));

		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
