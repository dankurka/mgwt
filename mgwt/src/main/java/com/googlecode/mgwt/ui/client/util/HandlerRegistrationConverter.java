package com.googlecode.mgwt.ui.client.util;

import com.google.web.bindery.event.shared.HandlerRegistration;

public class HandlerRegistrationConverter implements HandlerRegistration, com.google.gwt.event.shared.HandlerRegistration {
	private final HandlerRegistration hr;

	public HandlerRegistrationConverter(com.google.gwt.event.shared.HandlerRegistration hr) {
		this.hr = hr;

	}

	@Override
	public void removeHandler() {
		hr.removeHandler();

	}
}
