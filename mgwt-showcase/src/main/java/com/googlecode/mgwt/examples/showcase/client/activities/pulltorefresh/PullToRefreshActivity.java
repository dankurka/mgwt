package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.DetailActivity;
import com.googlecode.mgwt.examples.showcase.client.activities.UIPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.ui.client.widget.event.PullReleasedEvent;
import com.googlecode.mgwt.ui.client.widget.event.PullReleasedHandler;

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

	private boolean failed = false;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);

		final PullToRefreshDisplay display = clientFactory.getPullToRefreshDisplay();

		display.getHeader().setText("Pulldown to Refresh");

		display.getMainButtonText().setText("Nav");
		display.getBackbuttonText().setText("UI");
		display.getHeader().setText("PullToRefresh");

		addHandlerRegistration(display.getReload().addPullReleasedHandler(new PullReleasedHandler() {

			@Override
			public void onPullReleased(PullReleasedEvent event) {

				new Timer() {

					@Override
					public void run() {
						if (failed) {
							display.onLoadingFailed();
						} else {
							for (int i = 0; i < 5; i++) {
								list.add(new Topic("Topic " + (counter + 1), counter));
								counter++;
							}
							display.render(list);
							display.onLoadingSucceeded();
						}
						failed = !failed;

					}
				}.schedule(1000);

			}
		}));

		addHandlerRegistration(display.getBackbutton().addTapHandler(new TapHandler() {

			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new UIPlace());

			}
		}));

		display.render(list);

		panel.setWidget(display);
	}
}
