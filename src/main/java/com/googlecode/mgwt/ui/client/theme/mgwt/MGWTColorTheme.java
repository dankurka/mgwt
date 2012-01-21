package com.googlecode.mgwt.ui.client.theme.mgwt;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.MGWTTheme;

public class MGWTColorTheme implements MGWTTheme {

	private MGWTClientBundle bundle;

	public MGWTColorTheme() {
		if (MGWT.getOsDetection().isIOs()) {
			if (MGWT.getOsDetection().isRetina()) {
				bundle = (MGWTColorBundleRetina) GWT.create(MGWTColorBundleRetina.class);
			} else {
				bundle = (MGWTColorBundleNonRetina) GWT.create(MGWTColorBundleNonRetina.class);
			}
		} else {
			bundle = (MGWTColorBundleNonRetina) GWT.create(MGWTColorBundleNonRetina.class);
		}

	}

	@Override
	public MGWTClientBundle getMGWTClientBundle() {
		return bundle;
	}

}
