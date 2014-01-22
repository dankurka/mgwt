package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.ui.client.util.OrientationHandler;

public class ResizeOrientationHandler extends BaseOrientationHandler implements
		OrientationHandler {

	@Override
	public void doSetupOrientation() {

		if (!orientationEventSupported()) {
			Window.addResizeHandler(new ResizeHandler() {

				@Override
				public void onResize(ResizeEvent event) {
					ORIENTATION orientation = getOrientation();
					if (orientation != currentOrientation) {
						currentOrientation = orientation;
						fireOrientationChangedEvent(orientation);
					}
				}
			});
		} else {
			setupNativeBrowerOrientationHandler();
		}

	}

	private native static boolean orientationEventSupported()/*-{
		return "onorientationchange" in $wnd;
	}-*/;

	/**
	 * Get the current orientation of the device
	 * 
	 * @return the current orientation of the device
	 */
	public ORIENTATION getOrientation() {

		if (!orientationSupport()) {
			int height = Window.getClientHeight();
			int width = Window.getClientWidth();

			if (width > height) {
				return ORIENTATION.LANDSCAPE;
			} else {
				return ORIENTATION.PORTRAIT;
			}

		} else {
			return getBrowserOrientation();
		}

	}

	private static native boolean orientationSupport() /*-{
		return "orientation" in $wnd;
	}-*/;

}
