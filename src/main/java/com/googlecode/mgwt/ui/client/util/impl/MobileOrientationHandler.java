package com.googlecode.mgwt.ui.client.util.impl;

import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.ui.client.util.OrientationHandler;

public class MobileOrientationHandler extends BaseOrientationHandler implements
		OrientationHandler {

	@Override
	public void doSetupOrientation() {
		setupNativeBrowerOrientationHandler();
	}

	@Override
	public ORIENTATION getOrientation() {
		return getBrowserOrientation();
	}
}
