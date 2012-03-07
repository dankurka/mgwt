package com.googlecode.mgwt.ui.client.theme;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeAndroid;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeAndroidTablet;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeBlackberry;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIE9;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPad;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPhone;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeRetina;

public class MGWTThemeBaseThemeStandardImpl implements MGWTTheme {

	private MGWTClientBundle bundle;

	public MGWTThemeBaseThemeStandardImpl() {

		if (MGWT.getOsDetection().isAndroidPhone()) {
			bundle = GWT.create(MGWTClientBundleBaseThemeAndroid.class);
		}

		if (MGWT.getOsDetection().isAndroidTablet()) {
			bundle = GWT.create(MGWTClientBundleBaseThemeAndroidTablet.class);
		}

		if (MGWT.getOsDetection().isIPhone()) {
			if (MGWT.getOsDetection().isRetina()) {
				bundle = GWT.create(MGWTClientBundleBaseThemeRetina.class);
			} else {
				bundle = GWT.create(MGWTClientBundleBaseThemeIPhone.class);
			}
		}

		if (MGWT.getOsDetection().isIPad()) {
			bundle = GWT.create(MGWTClientBundleBaseThemeIPad.class);
		}

		if (MGWT.getOsDetection().isBlackBerry()) {
			bundle = GWT.create(MGWTClientBundleBaseThemeBlackberry.class);
		}

		//TODO change
		if (MGWT.getOsDetection().isDesktop()) {
			bundle = GWT.create(MGWTClientBundleBaseThemeIE9.class);
		}

	}

	@Override
	public MGWTClientBundle getMGWTClientBundle() {
		return bundle;
	}

}
