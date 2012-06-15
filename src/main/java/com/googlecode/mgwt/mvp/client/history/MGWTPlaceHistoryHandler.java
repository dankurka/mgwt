/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.mvp.client.history;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class MGWTPlaceHistoryHandler {

	protected class DefaultHistoryHandler implements HistoryHandler {

		@Override
		public void replaceCurrentPlace(Place place) {
			String tokenForPlace = tokenForPlace(place);
			replaceToken(tokenForPlace);

		}

		@Override
		public void pushPlace(Place place) {
			String tokenForPlace = tokenForPlace(place);
			pushToken(tokenForPlace);

		}

		@Override
		public void goTo(Place place) {
			placeController.goTo(place);

		}

		@Override
		public void goTo(Place place, boolean ignore) {
			MGWTPlaceHistoryHandler.this.ignore = ignore;
			placeController.goTo(place);

		}

	}

	protected final static Logger log = Logger.getLogger(MGWTPlaceHistoryHandler.class.getName());

	private EventBus eventBus;

	private final PlaceHistoryMapper placeHistoryMapper;

	private static final Historian GWT_historian = (Historian) GWT.create(DefaultHistorian.class);

	private static final Html5Historian historian = HTML5HistorianFactory.getHistorian();

	private PlaceController placeController;

	private boolean ignore;

	private final HistoryObserver historyObserver;

	private DefaultHistoryHandler defaultHistoryHandler;

	public MGWTPlaceHistoryHandler(PlaceHistoryMapper placeHistoryMapper, HistoryObserver historyObserver) {

		this.placeHistoryMapper = placeHistoryMapper;
		this.historyObserver = historyObserver;
		defaultHistoryHandler = new DefaultHistoryHandler();

	}

	public HandlerRegistration register(PlaceController placeController, EventBus eventBus, Place defaultPlace) {
		this.placeController = placeController;
		this.eventBus = eventBus;
		this.defaultPlace = defaultPlace;

		final HandlerRegistration bind = bind();

		final HandlerRegistration handlerRegistration = historyObserver.bind(eventBus, defaultHistoryHandler);

		return new HandlerRegistration() {
			public void removeHandler() {

				bind.removeHandler();
				handlerRegistration.removeHandler();
			}
		};
	}

	protected HandlerRegistration bind() {

		final HandlerRegistration popHandler = historian.addPopStateHandler(new PopStateHandler() {

			@Override
			public void onPopStateEvent(PopStateEvent event) {
				onPopStateEventOccured(event.getData());
			}
		});

		final HandlerRegistration placeChangeHandler = eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {

			@Override
			public void onPlaceChange(PlaceChangeEvent event) {
				onPlaceChangeEvent(event);

			}
		});

		return new HandlerRegistration() {
			public void removeHandler() {
				MGWTPlaceHistoryHandler.this.defaultPlace = Place.NOWHERE;
				MGWTPlaceHistoryHandler.this.placeController = null;
				popHandler.removeHandler();
				placeChangeHandler.removeHandler();
			}
		};

	}

	protected void onPlaceChangeEvent(PlaceChangeEvent event) {

		if (ignore) {
			ignore = false;
			return;
		}

		Place newPlace = event.getNewPlace();

		historyObserver.onPlaceChange(newPlace, defaultHistoryHandler);

		pushToken(tokenForPlace(newPlace));
	}

	protected void onPopStateEventOccured(String token) {

		Place place = getPlaceForToken(token);

		historyObserver.onHistoryChanged(place, defaultHistoryHandler);
		// TODO maybe handle differently?
		ignore = true;
		placeController.goTo(place);
	}

	protected void replaceToken(String token) {
		if (token.length() > 0) {
			historian.replaceState(token, Window.getTitle(), "#" + token);
		} else {
			historian.replaceState(token, Window.getTitle(), "");
		}
	}

	protected void pushToken(String token) {
		historian.pushState(token, Window.getTitle(), "#" + token);
	}

	public void handleCurrentHistory() {
		Place place = getPlaceForToken(GWT_historian.getToken());

		historyObserver.onAppStarted(place, defaultHistoryHandler);
		if (defaultPlace.equals(place)) {
			ignore = true;
		}

		placeController.goTo(place);
	}

	private Place defaultPlace = Place.NOWHERE;

	protected Place getPlaceForToken(String token) {

		Place newPlace = null;

		if ("".equals(token)) {
			newPlace = defaultPlace;
		}

		if (newPlace == null) {
			newPlace = placeHistoryMapper.getPlace(token);
		}

		if (newPlace == null) {
			log.warning("Unrecognized history token: " + token);
			newPlace = defaultPlace;
		}
		return newPlace;

	}

	private String tokenForPlace(Place newPlace) {

		if (defaultPlace.equals(newPlace)) {
			return "";
		}

		String token = placeHistoryMapper.getToken(newPlace);
		if (token != null) {
			return token;
		}

		log.warning("Place not mapped to a token: " + newPlace);
		return "";
	}
}
