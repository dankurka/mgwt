package com.googlecode.mgwt.ui.client.widget.scroll;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasScrollHandlers {
	public HandlerRegistration addScrollhandler(ScrollHandler scrollHandler);
}
