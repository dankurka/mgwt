package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.DetailActivity;
import com.googlecode.mgwt.ui.client.widget.event.ReloadEvent;
import com.googlecode.mgwt.ui.client.widget.event.ReloadHandler;
import com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedEvent;
import com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedEvent.State;
import com.googlecode.mgwt.ui.client.widget.event.ReloadStateChangedHandler;

public class PullToRefreshActivity extends DetailActivity {

	private final ClientFactory clientFactory;

	public PullToRefreshActivity(ClientFactory clientFactory) {
		super(clientFactory.getPullToRefreshDisplay(), "nav");
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);

		final PullToRefreshDisplay display = clientFactory.getPullToRefreshDisplay();

		display.getHeader().setText("Pulldown to Refresh");

		addHandlerRegistration(display.getReload().addReloadHandler(new ReloadHandler() {

			@Override
			public void onReload(ReloadEvent event) {
				display.setLoading(true);
				new Timer() {

					@Override
					public void run() {
						display.setLoading(false);

					}
				}.schedule(5000);

			}
		}));

		addHandlerRegistration(display.getReloadState().addReloadStateChangeHandler(new ReloadStateChangedHandler() {

			@Override
			public void onReloadStateChanged(ReloadStateChangedEvent event) {
				State state = event.getState();
				switch (state) {
				case RELOAD:
					System.out.println("reload");
					break;
				case NO_RELOAD:
					System.out.println("no reload");
				default:
					break;
				}

			}
		}));

		panel.setWidget(display);
	}
}
