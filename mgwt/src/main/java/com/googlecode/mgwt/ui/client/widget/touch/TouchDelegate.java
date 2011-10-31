package com.googlecode.mgwt.ui.client.widget.touch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapToNativeTouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;

public class TouchDelegate implements HasTouchHandlers {

	private static final TouchWidgetImpl impl = GWT.create(TouchWidgetImpl.class);
	private final Widget w;

	public TouchDelegate(Widget w) {
		this.w = w;
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return impl.addTouchStartHandler(w, handler);

	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return impl.addTouchMoveHandler(w, handler);

	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return impl.addTouchCancelHandler(w, handler);

	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return impl.addTouchEndHandler(w, handler);

	}

	@Override
	public HandlerRegistration addTouchHandler(TouchHandler handler) {
		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(handler));
		return handlerRegistrationCollection;
	}

	protected HandlerRegistration addTapHandler(TapHandler handler) {
		TapToNativeTouchHandler touchHandler = new TapToNativeTouchHandler(handler);

		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(touchHandler));
		return handlerRegistrationCollection;
	}

}
