/**
 * 14.11.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.celllist;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author kurt
 *
 */
public interface CellSelectedHandler extends EventHandler {
	public void onCellSelected(CellSelectedEvent event);
}
