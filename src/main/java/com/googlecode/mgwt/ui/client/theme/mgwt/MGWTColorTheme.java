package com.googlecode.mgwt.ui.client.theme.mgwt;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.MGWTTheme;

public class MGWTColorTheme implements MGWTTheme {

	private MGWTColorBundleNonRetina bundle;

	public MGWTColorTheme() {
		bundle = GWT.create(MGWTColorBundleNonRetina.class);
	}

	@Override
	public MGWTClientBundle getMGWTClientBundle() {
		return bundle;
	}

}
