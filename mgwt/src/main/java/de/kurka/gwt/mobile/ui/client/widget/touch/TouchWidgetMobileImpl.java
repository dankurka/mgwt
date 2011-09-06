package de.kurka.gwt.mobile.ui.client.widget.touch;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;

public class TouchWidgetMobileImpl implements TouchWidgetImpl {

	@Override
	public HandlerRegistration addTouchStartHandler(Widget w, TouchStartHandler handler) {
		return w.addDomHandler(handler, TouchStartEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchMoveHandler(Widget w, TouchMoveHandler handler) {
		return w.addDomHandler(handler, TouchMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchCancelHandler(Widget w, TouchCancelHandler handler) {
		return w.addDomHandler(handler, TouchCancelEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchEndHandler(Widget w, TouchEndHandler handler) {
		return w.addDomHandler(handler, TouchEndEvent.getType());
	}

}
