package com.googlecode.mgwt.ui.client.theme;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPhone;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeRetina;

public class MGWTThemeBaseThemeStandardImpl implements MGWTTheme {

	private MGWTClientBundle bundle;

	public MGWTThemeBaseThemeStandardImpl() {
		if (MGWT.getOsDetection().isIOs()) {
			if (MGWT.getOsDetection().isRetina()) {
				bundle = GWT.create(MGWTClientBundleBaseThemeRetina.class);
			} else {
				bundle = GWT.create(MGWTClientBundleBaseThemeIPhone.class);
			}
		} else {
			// TODO maybe icons for android
			MGWTClientBundleBaseThemeIPhone nonRetina = (MGWTClientBundleBaseThemeIPhone) GWT.create(MGWTClientBundleBaseThemeIPhone.class);
			bundle = nonRetina;
		}
	}

	@Override
	public MGWTClientBundle getMGWTClientBundle() {
		return bundle;
	}

}
