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
package com.googlecode.mgwt.mvp.client;

import java.util.LinkedList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author Daniel Kurka
 * 
 */
public abstract class MGWTAbstractActivity extends AbstractActivity {

	private LinkedList<HandlerRegistration> handlers;

	public MGWTAbstractActivity() {
		handlers = new LinkedList<HandlerRegistration>();
	}

	protected void addHandlerRegistration(HandlerRegistration handlerRegistration) {
		handlers.add(handlerRegistration);
	}

	@Override
	public void onStop() {
		super.onStop();

		cancelAllHandlerRegistrations();
	}

	protected void cancelAllHandlerRegistrations() {
		for (HandlerRegistration hr : handlers) {
			hr.removeHandler();
		}
		handlers.clear();
	}

}
