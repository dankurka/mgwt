package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;

public class DetailActivity extends MGWTAbstractActivity {

	private final DetailView detailView;
	private final String eventId;

	public DetailActivity(DetailView detailView, String eventId) {
		this.detailView = detailView;
		this.eventId = eventId;

	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		addHandlerRegistration(detailView.getMainButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				eventBus.fireEvent(new ShowMasterEvent(eventId));

			}
		}));

	}

}
