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

import com.google.web.bindery.event.shared.Event;

public class PopStateEvent extends Event<PopStateHandler> {

	private static final Type<PopStateHandler> TYPE = new Type<PopStateHandler>();
	private final String data;
	private final String title;
	private final String url;

	public static Type<PopStateHandler> getType() {
		return TYPE;
	}

	public PopStateEvent(String data, String title, String url) {
		this.data = data;
		this.title = title;
		this.url = url;
	}

	@Override
	public com.google.web.bindery.event.shared.Event.Type<PopStateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PopStateHandler handler) {
		handler.onPopStateEvent(this);

	}

	public String getData() {
		return data;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

}
