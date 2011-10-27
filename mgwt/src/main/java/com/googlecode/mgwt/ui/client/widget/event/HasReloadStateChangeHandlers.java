package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasReloadStateChangeHandlers {
	public HandlerRegistration addReloadStateChangeHandler(ReloadStateChangedHandler handler);
}
