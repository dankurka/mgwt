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
	private GroupOverViewDisplay display;

	public GroupOverViewActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		display = clientFactory.getOverviewDisplay();

		display.setPresenter(this);

		loadGroups(null);

		eventBus.addHandler(GroupAddedEvent.getType(), new GroupAddedHandler() {

			@Override
			public void onGroupAdded(GroupAddedEvent event) {
				loadGroups(event.getGroup().getId());

			}
		});

		eventBus.addHandler(GroupUpdatedEvent.getType(), new GroupUpdatedHandler() {

			@Override
			public void onGroupUpdated(GroupUpdatedEvent event) {

				loadGroups(event.getGroupId());

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
	private int oldIndex;

	private int getIndexForId(String id) {
		if (id == null)
			return -1;
		int count = 0;
		for (Group group : currentList) {
			if (group.getId().equals(id)) {
				return count;
			}
			count++;
		}
		return -1;
	}

	private void loadGroups(String id) {
		try {
			currentList = clientFactory.getStorage().getGroups();
			display.renderTopics(currentList);
			int index = getIndexForId(id);
			oldIndex = -1;
			if (index != -1) {
				display.setSelected(index);

			}
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
		if (oldIndex != -1) {
			display.setSelected(oldIndex, false);
		}
		display.setSelected(index);
		oldIndex = index;

		clientFactory.getEventBus().fireEvent(new GroupSelectedEvent(group.getId()));
		clientFactory.getPlaceController().goTo(new ShowGroupPlace(group.getId()));

	}

}
