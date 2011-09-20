package de.kurka.mobile.contact.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.mvp.client.MGWTAbstractActivity;
import de.kurka.mobile.contact.client.ClientFactory;
import de.kurka.mobile.contact.client.Topic;
import de.kurka.mobile.contact.client.activities.OverviewDisplay.OverviewPresenter;

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
