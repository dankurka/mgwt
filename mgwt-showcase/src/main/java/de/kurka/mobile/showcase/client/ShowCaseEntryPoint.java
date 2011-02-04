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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;

import de.kurka.gwt.mobile.mvp.client.AnimatableDisplay;
import de.kurka.gwt.mobile.mvp.client.AnimatableDisplayImpl;
import de.kurka.gwt.mobile.mvp.client.AnimatingActivityManager;
import de.kurka.gwt.mobile.ui.client.MGWT;
import de.kurka.gwt.mobile.ui.client.MGWTSettings;
import de.kurka.mobile.showcase.client.places.HomePlace;

/**
 * @author kurt
 *
 */
public class ShowCaseEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {

		MGWTSettings settings = new MGWTSettings();
		settings.setAddGlosToIcon(true);
		settings.setFixViewPort(true);
		settings.setFullscreen(true);
		settings.setPreventScrolling(true);

		MGWT mgwt = new MGWT();
		mgwt.applySettings(settings);

		ClientFactory clientFactory = new ClientFactoryImpl();

		AnimatableDisplay display = new AnimatableDisplayImpl();

		AppActivityMapper appActivityMapper = new AppActivityMapper(clientFactory);

		AppAnimationMapper appAnimationMapper = new AppAnimationMapper();

		AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, clientFactory.getEventBus());

		activityManager.setDisplay(display);

		RootPanel.get().add(display);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());

		historyHandler.handleCurrentHistory();

	}

}
