/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.kurka.mobile.showcase.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import de.kurka.gwt.mobile.mvp.client.AnimatableDisplay;
import de.kurka.gwt.mobile.mvp.client.AnimatingActivityManager;
import de.kurka.gwt.mobile.mvp.client.AnimationMapper;
import de.kurka.gwt.mobile.mvp.client.display.AnimatableDisplayBaseImpl;
import de.kurka.gwt.mobile.ui.client.MGWT;
import de.kurka.gwt.mobile.ui.client.MGWTSettings;
import de.kurka.gwt.mobile.ui.client.MGWTUtil;
import de.kurka.mobile.showcase.client.places.HomePlace;

/**
 * @author Daniel Kurka
 * 
 */
public class ShowCaseEntryPoint implements EntryPoint {

	private void start() {
		MGWTSettings settings = new MGWTSettings();
		settings.setAddGlosToIcon(true);
		settings.setFixViewPort(true);
		settings.setFullscreen(true);
		settings.setPreventScrolling(true);

		MGWT mgwt = new MGWT();
		mgwt.applySettings(settings);

		mgwt.loadStyle();

		final ClientFactory clientFactory = new ClientFactoryImpl();

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);

		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());

		if (MGWTUtil.getFeatureDetection().isIPad() || MGWTUtil.getFeatureDetection().isDesktop()) {
			MGWTUtil.loadCss("ipad.css", new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					historyHandler.handleCurrentHistory();

				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("can not load css");

				}
			});

			createTabletDisplay(clientFactory);
		} else {
			createPhoneDisplay(clientFactory);
			historyHandler.handleCurrentHistory();
		}

	}

	private void createPhoneDisplay(ClientFactory clientFactory) {
		AnimatableDisplayBaseImpl display = GWT.create(AnimatableDisplay.class);

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

			}
		});

		new Timer() {
			@Override
			public void run() {
				start();

			}
		}.schedule(1);

		// alertSomeStuff();

	}

	private native void alertSomeStuff()/*-{
		alert($wnd.navigator.userAgent);
	}-*/;

}
