package de.kurka.mobile.contact.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.gwt.mobile.mvp.client.MGWTAbstractActivity;
import de.kurka.mobile.contact.client.ClientFactory;
import de.kurka.mobile.contact.client.Topic;

public class OverviewActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	public OverviewActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		OverviewDisplay display = clientFactory.getOverviewDisplay();

		addHandlerRegistration(display.getPlusButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				clientFactory.getPlaceController().goTo(new AddGroupPlace());
			}
		}));

		List<Topic> list = new ArrayList<Topic>();

		list.add(new Topic("test"));
		list.add(new Topic("test"));
		list.add(new Topic("test"));

		display.renderTopics(list);

		panel.setWidget(display);

	}

}
