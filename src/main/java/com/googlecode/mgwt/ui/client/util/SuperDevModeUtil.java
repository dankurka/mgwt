package com.googlecode.mgwt.ui.client.util;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.util.impl.SuperDevModeHelper;

public class SuperDevModeUtil {

	private static final SuperDevModeHelper HELPER = GWT.create(SuperDevModeHelper.class);

	public static void showDevMode() {
		HELPER.showDevMode();
	}
}
