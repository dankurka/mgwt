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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class HTML5HistorianLegacyImpl implements Html5Historian, ValueChangeHandler<String> {

	public static class DefaultHistorian implements Historian {
		public com.google.gwt.event.shared.HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> valueChangeHandler) {
			return History.addValueChangeHandler(valueChangeHandler);
		}

		public String getToken() {
			return History.getToken();
		}

		public void newItem(String token, boolean issueEvent) {
			History.newItem(token, issueEvent);
		}
	}

	private DefaultHistorian defaultHistorian;
	private EventBus eventBus = new SimpleEventBus();

	public HTML5HistorianLegacyImpl() {
		defaultHistorian = new DefaultHistorian();

		defaultHistorian.addValueChangeHandler(this);
	}

	@Override
	public void forward() {
		History.forward();

	}

	@Override
	public void back() {
		History.back();

	}

	@Override
	public void go(int number) {
		if (number > 0) {
			History.forward();
		} else {
			History.back();
		}

	}

	@Override
	public int length() {
		return -1;
	}

	@Override
	public void pushState(String data, String title, String url) {
		History.newItem(data, false);

	}

	@Override
	public void replaceState(String data, String title, String url) {
		UrlBuilder builder = Window.Location.createUrlBuilder();
		builder.setHash(data);
		Window.Location.replace(builder.buildString());

	}

	@Override
	public HandlerRegistration addPopStateHandler(PopStateHandler handler) {
		return eventBus.addHandler(PopStateEvent.getType(), handler);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		eventBus.fireEvent(new PopStateEvent(event.getValue(), Window.getTitle(), Window.Location.getHref()));
	}

}
