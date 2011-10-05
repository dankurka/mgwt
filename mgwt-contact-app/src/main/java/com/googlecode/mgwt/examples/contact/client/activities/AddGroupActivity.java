package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.contact.client.ClientFactory;
import com.googlecode.mgwt.examples.contact.client.StoreException;
import com.googlecode.mgwt.examples.contact.client.events.GroupAddedEvent;
import com.googlecode.mgwt.examples.contact.client.module.Group;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;

public class AddGroupActivity extends MGWTAbstractActivity implements AddGroupDisplay.Presenter {

	private final ClientFactory clientFactory;
	private AddGroupDisplay view;

	public AddGroupActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getAddGroupDisplay();
		panel.setWidget(view);

		view.setPresenter(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		view.setPresenter(null);
	}

	@Override
	public void onLeftButton() {
		if (MGWTUtil.getFeatureDetection().isPhone()) {
			//clientFactory.getPlaceController().goTo();
		} else {
			clientFactory.getEventBus().fireEvent(new ShowMasterEvent("nav"));
		}

	}

	@Override
	public void onAddButtonPressed() {
		String groupName = view.getGroupName().getText();

		try {
			Group group = clientFactory.getStorage().addGroup(groupName);
			clientFactory.getEventBus().fireEvent(new GroupAddedEvent(group));
			clientFactory.getPlaceController().goTo(new ShowGroupPlace(group.getId()));
			view.getGroupName().setText("");
		} catch (StoreException e) {
			e.printStackTrace();
			view.showError("Can not create group");
		}
	}

}
