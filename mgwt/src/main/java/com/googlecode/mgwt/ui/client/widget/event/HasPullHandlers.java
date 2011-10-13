package com.googlecode.mgwt.ui.client.widget.event;

import com.google.web.bindery.event.shared.HandlerRegistration;

public interface HasPullHandlers {
	public HandlerRegistration addPullReleasedHandler(PullReleasedHandler handler);

	public HandlerRegistration addPullStateChangedHandler(PullStateChangedHandler handler);
}
