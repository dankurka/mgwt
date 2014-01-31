package com.googlecode.mgwt.ui.client.layout;

import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;
import com.googlecode.mgwt.ui.client.event.ShowMasterHandler;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialog;

/**
 * <p>MasterRegionHandler class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class MasterRegionHandler {

	private final EventBus eventBus;
	private final String id;
	private final Dialog dialog;

	/**
	 * <p>Constructor for MasterRegionHandler.</p>
	 *
	 * @param eventBus a {@link com.google.web.bindery.event.shared.EventBus} object.
	 * @param id a {@link java.lang.String} object.
	 * @param dialog a {@link com.googlecode.mgwt.ui.client.widget.dialog.Dialog} object.
	 */
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

	/**
	 * <p>showMaster</p>
	 */
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
