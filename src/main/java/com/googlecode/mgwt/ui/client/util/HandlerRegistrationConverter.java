/*
 *
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
package com.googlecode.mgwt.ui.client.util;

import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Considered internal
 *
 * @author Daniel Kurka
 */
public class HandlerRegistrationConverter implements HandlerRegistration, com.google.gwt.event.shared.HandlerRegistration {
	private final HandlerRegistration hr;
	private final HandlerRegistration hrOld;

	public HandlerRegistrationConverter(com.google.gwt.event.shared.HandlerRegistration hr) {
		this.hr = hr;
		this.hrOld = null;
	}

	public HandlerRegistrationConverter(HandlerRegistration hrOld) {
		this.hrOld = hrOld;
		this.hr = null;
	}

	@Override
	public void removeHandler() {
		if (hr != null) {
			hr.removeHandler();
		}
		if (hrOld != null) {
			hrOld.removeHandler();
		}
	}
}
