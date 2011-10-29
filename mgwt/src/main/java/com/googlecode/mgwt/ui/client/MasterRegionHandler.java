package com.googlecode.mgwt.ui.client;

import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.ui.client.dialog.Dialog;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;
import com.googlecode.mgwt.ui.client.event.ShowMasterHandler;

public class MasterRegionHandler {

	private final EventBus eventBus;
	private final String id;
	private final Dialog dialog;

	public MasterRegionHandler(EventBus eventBus, String id, Dialog dialog) {
		this.eventBus = eventBus;
		this.id = id;
		this.dialog = dialog;
		if (id == null) {
			throw new IllegalArgumentException();
		}
		if (dialog == null) {
			throw new IllegalArgumentException();
		}

		if (eventBus == null) {
			throw new IllegalArgumentException();
		}
		this.eventBus.addHandler(ShowMasterEvent.getType(), new ShowHandlerImpl());
	}

	protected void showMaster() {
		dialog.show();
	}

	private class ShowHandlerImpl implements ShowMasterHandler {

		@Override
		public void onShowMaster(ShowMasterEvent event) {
			if (id.equals(event.getId())) {
				showMaster();
			}
		}

	}
}
