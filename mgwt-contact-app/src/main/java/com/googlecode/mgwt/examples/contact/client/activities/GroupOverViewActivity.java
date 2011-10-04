package com.googlecode.mgwt.examples.contact.client.activities;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.contact.client.ClientFactory;
import com.googlecode.mgwt.examples.contact.client.StoreException;
import com.googlecode.mgwt.examples.contact.client.activities.GroupOverViewDisplay.GroupOverViewPresenter;
import com.googlecode.mgwt.examples.contact.client.module.Group;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class GroupOverViewActivity extends MGWTAbstractActivity implements GroupOverViewPresenter {

	private final ClientFactory clientFactory;

	public GroupOverViewActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final GroupOverViewDisplay display = clientFactory.getOverviewDisplay();

		display.setPresenter(this);

		loadGroups(display);

		eventBus.addHandler(GroupAddedEvent.getType(), new GroupAddedHandler() {

			@Override
			public void onGroupAdded(GroupAddedEvent event) {
				loadGroups(display);

			}
		});

		panel.setWidget(display);

	}

	@Override
	public void onStop() {
		super.onStop();
		clientFactory.getOverviewDisplay().setPresenter(null);
	}

	private List<Group> currentList;

	private void loadGroups(GroupOverViewDisplay display) {
		try {
			currentList = clientFactory.getStorage().getGroups();
			display.renderTopics(currentList);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onPlusButton() {
		clientFactory.getPlaceController().goTo(new AddGroupPlace());

	}

	@Override
	public void onListItemSelected(int index) {
		Group group = currentList.get(index);

		clientFactory.getEventBus().fireEvent(new GroupSelectedEvent(group.getId()));
		clientFactory.getPlaceController().goTo(new ShowGroupPlace(group.getId()));

	}

}
