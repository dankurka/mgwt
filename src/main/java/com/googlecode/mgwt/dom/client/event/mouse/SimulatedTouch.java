/*
 * Copyright 2014 Daniel Kurka
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

public class SimulatedTouch extends Touch {

  public static native SimulatedTouch createTouch() /*-{
    // need to native for GwtMockito to work
    return {};
  }-*/;

  public native static JsArray<Touch> createTouchArray() /*-{
    return [];
  }-*/;

  protected SimulatedTouch() {
  }

  public final native void setClientX(int clientX) /*-{
    this.clientX = clientX;
  }-*/;

  public final native void setClientY(int clientY) /*-{
    this.clientY = clientY;
  }-*/;

  public final native void setPageX(int pageX) /*-{
    this.pageX = pageX;
  }-*/;

  public final native void setPageY(int pageY) /*-{
    this.pageY = pageY;
  }-*/;

  public final native void setScreenX(int screenX) /*-{
    this.screenX = screenX;
  }-*/;

  public final native void setScreenY(int screenY) /*-{
    this.screenY = screenY;
  }-*/;

  public final native void setId(int touchId) /*-{
    this.identifier = touchId
  }-*/;
}
