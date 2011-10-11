package com.googlecode.mgwt.ui.client.widget.event;

import com.google.web.bindery.event.shared.HandlerRegistration;

public interface HasReloadHandlers {
	public HandlerRegistration addReloadHandler(ReloadHandler handler);

}
