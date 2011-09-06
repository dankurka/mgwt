package de.kurka.gwt.mobile.ui.client;

import com.google.gwt.core.client.GWT;

import de.kurka.gwt.mobile.ui.client.theme.base.MGWTClientBundle;

public class MGWTStyle {

	private static MGWTClientBundle defaultClientBundle;

	public static final MGWTClientBundle getDefaultClientBundle() {
		if (defaultClientBundle == null) {
			defaultClientBundle = GWT.create(MGWTClientBundle.class);
		}
		return defaultClientBundle;
	}
}
