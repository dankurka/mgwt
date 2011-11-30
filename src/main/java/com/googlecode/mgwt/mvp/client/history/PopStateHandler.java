package com.googlecode.mgwt.mvp.client.history;

import com.google.gwt.event.shared.EventHandler;

public interface PopStateHandler extends EventHandler {
	public void onPopStateEvent(PopStateEvent event);
}
