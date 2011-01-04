/**
 * 24.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.celllist;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * @author kurt
 *
 */
public interface HasCellSelectedHandler {
	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler);
}
