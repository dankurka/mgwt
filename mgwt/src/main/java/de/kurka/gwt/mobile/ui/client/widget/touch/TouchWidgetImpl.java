package de.kurka.gwt.mobile.ui.client.widget.touch;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;

public interface TouchWidgetImpl {
	public HandlerRegistration addTouchStartHandler(Widget w, TouchStartHandler handler);

	public HandlerRegistration addTouchMoveHandler(Widget w, TouchMoveHandler handler);

	public HandlerRegistration addTouchCancelHandler(Widget w, TouchCancelHandler handler);

	public HandlerRegistration addTouchEndHandler(Widget w, TouchEndHandler handler);

}
