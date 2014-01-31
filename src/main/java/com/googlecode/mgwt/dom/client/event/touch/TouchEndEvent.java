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
package com.googlecode.mgwt.dom.client.event.touch;

import com.google.gwt.dom.client.NativeEvent;

/**
 * This represents a native touch end Event
 * 
 * @author Daniel Kurka
 */
public class TouchEndEvent extends TouchEvent<TouchEndHandler> {

  private static final Type<TouchEndHandler> TYPE = new Type<TouchEndHandler>("touchend",
      new TouchEndEvent());

  public static TouchEndEvent from(NativeEvent nativeEvent) {
    TouchEndEvent touchEndEvent = new TouchEndEvent();
    touchEndEvent.setNativeEvent(nativeEvent);
    return touchEndEvent;
  }

  /**
   * <p>
   * getType
   * </p>
   * 
   * @return a Type object.
   */
  public static Type<TouchEndHandler> getType() {
    return TYPE;
  }

  /**
   * {@inheritDoc}
   * 
   * Gets the event type associated with animation end events.
   */
  @Override
  public com.google.gwt.event.dom.client.DomEvent.Type<TouchEndHandler> getAssociatedType() {
    return TYPE;
  }

  /**
   * <p>
   * Constructor for TouchEndEvent.
   * </p>
   */
  protected TouchEndEvent() {

  }

  /** {@inheritDoc} */
  @Override
  protected void dispatch(TouchEndHandler handler) {
    handler.onTouchEnd(this);

  }

}
