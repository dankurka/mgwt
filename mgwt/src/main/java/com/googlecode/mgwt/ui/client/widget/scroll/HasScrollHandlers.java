package com.googlecode.mgwt.ui.client.widget.scroll;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasScrollHandlers {

	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler);

	public HandlerRegistration addScrollhandler(ScrollHandler scrollHandler);

	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler);
}
