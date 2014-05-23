/*
 * Copyright 2010 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.dom.client.event.mouse;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;

/**
 * A simulated TouchEndEvent that is actually a mouse up event.
 * <p>
 * This is used for testing in desktop browsers.
 *
 * @author Daniel Kurka
 */
public class SimulatedTouchEndEvent extends TouchEndEvent {

  private final int clientX;
  private final int clientY;
  private final int pageX;
  private final int pageY;

  /**
   * Construct a simulated TouchEndEvent from a {@link MouseUpEvent}
   *
   * @param mouseUpEvent the data for the simulated event;
   * @param multiTouch
   */
  public SimulatedTouchEndEvent(MouseUpEvent mouseUpEvent) {
    clientX = mouseUpEvent.getClientX();
    clientY = mouseUpEvent.getClientY();
    pageX = mouseUpEvent.getScreenX();
    pageY = mouseUpEvent.getScreenY();

    setNativeEvent(mouseUpEvent.getNativeEvent());
    setSource(mouseUpEvent.getSource());
  }

  @Override
  public JsArray<Touch> getChangedTouches() {
    JsArray<Touch> array = SimulatedTouch.createTouchArray();
    SimulatedTouch touch = SimulatedTouch.createTouch();
    touch.setClientX(clientX);
    touch.setClientY(clientY);
    touch.setPageX(pageX);
    touch.setPageY(pageY);
    array.push(touch);
    return array;
  }

  @Override
  public JsArray<Touch> getTouches() {
    JsArray<Touch> array = SimulatedTouch.createTouchArray();
    SimulatedTouch touch = SimulatedTouch.createTouch();
    touch.setClientX(clientX);
    touch.setClientY(clientY);
    touch.setPageX(pageX);
    touch.setPageY(pageY);
    array.push(touch);
    return array;
  }
}
