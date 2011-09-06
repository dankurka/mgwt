package de.kurka.gwt.mobile.ui.client.widget.touch;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.mouse.HandlerRegistrationCollection;
import de.kurka.gwt.mobile.dom.client.event.mouse.TouchEndToMouseUpHandler;
import de.kurka.gwt.mobile.dom.client.event.mouse.TouchMoveToMouseMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.mouse.TouchStartToMouseDownHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartHandler;

public class TouchWidgetDesktopImpl implements TouchWidgetImpl {

	@Override
	public HandlerRegistration addTouchStartHandler(Widget w, TouchStartHandler handler) {
		return w.addDomHandler(new TouchStartToMouseDownHandler(handler), MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchMoveHandler(Widget w, TouchMoveHandler handler) {
		TouchMoveToMouseMoveHandler touchMoveToMouseMoveHandler = new TouchMoveToMouseMoveHandler(handler);
		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();
		handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseDownEvent.getType()));
		handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseUpEvent.getType()));
		handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseMoveEvent.getType()));
		return handlerRegistrationCollection;
	}

	@Override
	public HandlerRegistration addTouchCancelHandler(Widget w, TouchCancelHandler handler) {
		return new HandlerRegistration() {

			@Override
			public void removeHandler() {

			}
		};
	}

	@Override
	public HandlerRegistration addTouchEndHandler(Widget w, TouchEndHandler handler) {
		return w.addDomHandler(new TouchEndToMouseUpHandler(handler), MouseUpEvent.getType());
	}

}
