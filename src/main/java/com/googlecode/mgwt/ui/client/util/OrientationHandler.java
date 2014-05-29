package com.googlecode.mgwt.ui.client.util;

import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;

public interface OrientationHandler {

	public abstract ORIENTATION getOrientation();

	void maybeSetupOrientation(EventBus manager);
}
