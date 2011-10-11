package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.DetailActivity;
import com.googlecode.mgwt.examples.showcase.client.activities.UIPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.ui.client.widget.event.ReloadEvent;
import com.googlecode.mgwt.ui.client.widget.event.ReloadHandler;
import com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedEvent;
import com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedEvent.State;
import com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedHandler;

public class PullToRefreshActivity extends DetailActivity {

	private final ClientFactory clientFactory;

	private int counter;
	private List<Topic> list = new LinkedList<Topic>();

	public PullToRefreshActivity(ClientFactory clientFactory) {
		super(clientFactory.getPullToRefreshDisplay(), "nav");
		this.clientFactory = clientFactory;

		list = new LinkedList<Topic>();
		while (counter < 5) {
			list.add(new Topic("Topic " + (counter + 1), counter));
			counter++;
		}

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);

		final PullToRefreshDisplay display = clientFactory.getPullToRefreshDisplay();

		display.getHeader().setText("Pulldown to Refresh");

		display.getMainButtonText().setText("Nav");
		display.getBackbuttonText().setText("UI");
		display.getHeader().setText("PullToRefresh");

		addHandlerRegistration(display.getReload().addReloadHandler(new ReloadHandler() {

			@Override
			public void onReload(ReloadEvent event) {

				new Timer() {

					@Override
					public void run() {

						for (int i = 0; i < 5; i++) {
							list.add(new Topic("Topic " + (counter + 1), counter));
							counter++;
						}
						display.render(list);
						display.reload();

					}
				}.schedule(1000);

			}
		}));

		addHandlerRegistration(display.getReloadState().addReloadStateChangeHandler(new ReloadStateChangedHandler() {

			@Override
			public void onReloadStateChanged(ReloadStateChangedEvent event) {
				State state = event.getState();
				switch (state) {
				case RELOAD:
					display.getTextHeader().setHTML("<div style=''>Release to reload</div>");
					break;
				case NO_RELOAD:
					display.getTextHeader().setText("<div style=''>No Reload</div>");
				default:
					break;
				}

			}
		}));

		addHandlerRegistration(display.getBackbutton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				clientFactory.getPlaceController().goTo(new UIPlace());

			}
		}));

		display.render(list);

		panel.setWidget(display);
	}
}
