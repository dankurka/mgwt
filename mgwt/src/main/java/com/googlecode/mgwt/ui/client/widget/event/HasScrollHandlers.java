package com.googlecode.mgwt.ui.client.widget.event;

import com.google.web.bindery.event.shared.HandlerRegistration;

public interface HasScrollHandlers {

	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler);

	public HandlerRegistration addScrollhandler(ScrollHandler scrollHandler);

	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler);
}
