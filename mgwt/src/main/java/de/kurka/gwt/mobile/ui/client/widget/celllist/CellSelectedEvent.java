/**
 * 14.11.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.celllist;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author kurt
 *
 */
public class CellSelectedEvent extends GwtEvent<CellSelectedHandler> {

	private static final GwtEvent.Type<CellSelectedHandler> TYPE = new GwtEvent.Type<CellSelectedHandler>();
	private final int index;

	public CellSelectedEvent(int index) {
		this.index = index;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CellSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(CellSelectedHandler handler) {
		handler.onCellSelected(this);

	}

	public static GwtEvent.Type<CellSelectedHandler> getType() {
		return TYPE;
	}

	public int getIndex() {
		return index;
	}

}
