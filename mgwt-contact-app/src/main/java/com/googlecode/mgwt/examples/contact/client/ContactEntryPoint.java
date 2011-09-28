package com.googlecode.mgwt.examples.contact.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.examples.contact.client.activities.HomePlace;
import com.googlecode.mgwt.examples.contact.client.css.AppBundle;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.mvp.client.display.AnimatableDisplayBaseImpl;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTUtil;


public class ContactEntryPoint implements EntryPoint {

	private void start() {
		MGWTSettings settings = new MGWTSettings();
		settings.setAddGlosToIcon(true);
		settings.setFixViewPort(true);
		settings.setFullscreen(true);
		settings.setPreventScrolling(true);

		MGWT mgwt = new MGWT();
		mgwt.applySettings(settings);

		final ClientFactory clientFactory = new ClientFactoryGwtImpl();

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		ContactPlaceHistoryMapper historyMapper = GWT.create(ContactPlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);

		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());

		if ((MGWTUtil.getFeatureDetection().isIPad() || MGWTUtil.getFeatureDetection().isDesktop())) {
			// workaround
			StyleInjector.inject(AppBundle.INSTANCE.css().getText());

			createTabletDisplay(clientFactory);
		} else {
			createPhoneDisplay(clientFactory);

		}
		historyHandler.handleCurrentHistory();

	}

	private void createPhoneDisplay(ClientFactory clientFactory) {
		AnimatableDisplay display = GWT.create(AnimatableDisplay.class);

		PhoneActivityMapper appActivityMapper = new PhoneActivityMapper(clientFactory);

		PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();

		AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, clientFactory.getEventBus());

		activityManager.setDisplay(display);

		RootPanel.get().add(display);

	}

	private void createTabletDisplay(ClientFactory clientFactory) {
		SimplePanel navContainer = new SimplePanel();
		navContainer.getElement().setId("nav");
		navContainer.getElement().addClassName("landscapeonly");
		AnimatableDisplayBaseImpl navDisplay = GWT.create(AnimatableDisplay.class);

		ActivityMapper navActivityMapper = new TabletNavActivityMapper(clientFactory);

		AnimationMapper navAnimationMapper = new TabletNavAnimationMapper();

		AnimatingActivityManager navActivityManager = new AnimatingActivityManager(navActivityMapper, navAnimationMapper, clientFactory.getEventBus());

		navActivityManager.setDisplay(navDisplay);
		navContainer.setWidget(navDisplay);

		RootPanel.get().add(navContainer);

		SimplePanel mainContainer = new SimplePanel();
		mainContainer.getElement().setId("main");
		AnimatableDisplayBaseImpl mainDisplay = GWT.create(AnimatableDisplay.class);

		TabletMainActivityMapper tabletMainActivityMapper = new TabletMainActivityMapper(clientFactory);

		AnimationMapper tabletMainAnimationMapper = new TabletMainAnimationMapper();

		AnimatingActivityManager mainActivityManager = new AnimatingActivityManager(tabletMainActivityMapper, tabletMainAnimationMapper, clientFactory.getEventBus());

		mainActivityManager.setDisplay(mainDisplay);
		mainContainer.setWidget(mainDisplay);

		RootPanel.get().add(mainContainer);

	}

	@Override
	public void onModuleLoad() {

		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void onUncaughtException(Throwable e) {
				Window.alert("uncaught: " + e.getMessage());
				e.printStackTrace();

			}
		});

		new Timer() {
			@Override
			public void run() {
				start();

			}
		}.schedule(1);

	}

}
