package com.googlecode.mgwt.ui.client;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundle;

public class MGWTStyle {

	private static MGWTClientBundle defaultClientBundle;

	public static final MGWTClientBundle getDefaultClientBundle() {
		if (defaultClientBundle == null) {
			defaultClientBundle = GWT.create(MGWTClientBundle.class);
			defaultClientBundle.getMainCss().ensureInjected();
		}
		return defaultClientBundle;
	}

	public static final void setDefaultBundle(MGWTClientBundle bundle) {
		if (defaultClientBundle == null) {
			throw new IllegalStateException("can not change default bundle if theres already an instance...");
		}
		defaultClientBundle = bundle;
	}
}
