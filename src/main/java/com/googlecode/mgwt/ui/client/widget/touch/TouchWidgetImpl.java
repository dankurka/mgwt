/*
 * Copyright 2011 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.touch;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.mouse.TouchEndToMouseUpHandler;
import com.googlecode.mgwt.dom.client.event.mouse.TouchMoveToMouseMoveHandler;
import com.googlecode.mgwt.dom.client.event.mouse.TouchStartToMouseDownHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.util.NoopHandlerRegistration;

/**
 * The touch widget interface is used to abstract implementation details for
 * adding touch handlers on touch devices / mouse devices
 *
 * @author Daniel Kurka
 */
public abstract class TouchWidgetImpl {

  private static class TouchWidgetMobileImpl extends TouchWidgetImpl {

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

    @Override
    public HandlerRegistration addTouchHandler(Widget w, TouchHandler handler) {
      HandlerRegistrationCollection hrc = new HandlerRegistrationCollection();
      hrc.addHandlerRegistration(addTouchStartHandler(w, handler));
      hrc.addHandlerRegistration(addTouchMoveHandler(w, handler));
      hrc.addHandlerRegistration(addTouchEndHandler(w, handler));
      hrc.addHandlerRegistration(addTouchCancelHandler(w, handler));
      return hrc;
    }
  }

  // Used with deffered binding
  @SuppressWarnings("unused")
  private static class TouchWidgetRuntimeImpl extends TouchWidgetImpl {
    private static boolean hasTouchSupport;
    private static TouchWidgetImpl delegate;

    static {
      hasTouchSupport = hasTouch();
      if (hasTouchSupport) {
        delegate = new TouchWidgetMobileImpl();
      }
    }

    private static native boolean hasTouch() /*-{
      return 'ontouchstart' in $doc.documentElement;
    }-*/;


    @Override
    public HandlerRegistration addTouchStartHandler(Widget w, TouchStartHandler handler) {
      if (hasTouchSupport) {
        return delegate.addTouchStartHandler(w, handler);
      }
      return w.addDomHandler(new TouchStartToMouseDownHandler(handler), MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(Widget w, TouchMoveHandler handler) {
      if (hasTouchSupport) {
        return delegate.addTouchMoveHandler(w, handler);
      }
      TouchMoveToMouseMoveHandler touchMoveToMouseMoveHandler = new TouchMoveToMouseMoveHandler(handler);
      HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();
      handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseDownEvent.getType()));
      handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseUpEvent.getType()));
      handlerRegistrationCollection.addHandlerRegistration(w.addDomHandler(touchMoveToMouseMoveHandler, MouseMoveEvent.getType()));
      return handlerRegistrationCollection;
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(Widget w, TouchCancelHandler handler) {
      if (hasTouchSupport) {
        return delegate.addTouchCancelHandler(w, handler);
      }
      return new NoopHandlerRegistration();
    }

    @Override
    public HandlerRegistration addTouchEndHandler(Widget w, TouchEndHandler handler) {
      if (hasTouchSupport) {
        return delegate.addTouchEndHandler(w, handler);
      }
      return w.addDomHandler(new TouchEndToMouseUpHandler(handler), MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchHandler(Widget w, TouchHandler handler) {
      if (hasTouchSupport) {
        return delegate.addTouchHandler(w, handler);
      }
      HandlerRegistrationCollection hrc = new HandlerRegistrationCollection();
      hrc.addHandlerRegistration(addTouchStartHandler(w, handler));
      hrc.addHandlerRegistration(addTouchMoveHandler(w, handler));
      hrc.addHandlerRegistration(addTouchEndHandler(w, handler));
      hrc.addHandlerRegistration(addTouchCancelHandler(w, handler));
      return hrc;
    }
  }

	/**
	 * Add a touch start handler to a widget
	 *
	 * @param w the widget that the handler should be added to
	 * @param handler the handler to add
	 * @return the handlerregistration
	 */
	public abstract HandlerRegistration addTouchStartHandler(Widget w, TouchStartHandler handler);

	/**
	 * Add a touch move handler to a widget
	 *
	 * @param w the widget that the handler should be added to
	 * @param handler the handler to add
	 * @return the handlerregistration
	 */
	public abstract HandlerRegistration addTouchMoveHandler(Widget w, TouchMoveHandler handler);

	/**
	 * Add a touch cancel handler to a widget
	 *
	 * @param w the widget that the handler should be added to
	 * @param handler the handler to add
	 * @return the handlerregistration
	 */
	public abstract HandlerRegistration addTouchCancelHandler(Widget w, TouchCancelHandler handler);

	/**
	 * Add a touch end handler to a widget
	 *
	 * @param w the widget that the handler should be added to
	 * @param handler the handler to add
	 * @return the handlerregistration
	 */
	public abstract HandlerRegistration addTouchEndHandler(Widget w, TouchEndHandler handler);

  public abstract HandlerRegistration addTouchHandler(Widget w, TouchHandler handler);
}
