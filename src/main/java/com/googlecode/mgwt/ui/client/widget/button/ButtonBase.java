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
package com.googlecode.mgwt.ui.client.widget.button;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;

import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * Base class for all buttons
 */
public abstract class ButtonBase extends TouchWidget implements HasText {

  private final ButtonBaseAppearance baseAppearance;

  /**
   * Construct a button with a given element and css
   * 
   * @param element the element to use
   * @param css the css to use
   */
  public ButtonBase(ButtonBaseAppearance appearance) {
    this.baseAppearance = appearance;

    addTouchHandler(new TouchHandler() {

      @Override
      public void onTouchCanceled(TouchCancelEvent event) {
        event.stopPropagation();
        event.preventDefault();
        removeStyleName(ButtonBase.this.baseAppearance.css().active());
        if (MGWT.getOsDetection().isDesktop()) {
          DOM.releaseCapture(getElement());
        }
      }

      @Override
      public void onTouchEnd(TouchEndEvent event) {
        event.stopPropagation();
        event.preventDefault();
        removeStyleName(ButtonBase.this.baseAppearance.css().active());
        if (MGWT.getOsDetection().isDesktop()) {
          DOM.releaseCapture(getElement());
        }
      }

      @Override
      public void onTouchMove(TouchMoveEvent event) {
        event.preventDefault();
        event.stopPropagation();
      }

      @Override
      public void onTouchStart(TouchStartEvent event) {
        event.stopPropagation();
        event.preventDefault();
        addStyleName(ButtonBase.this.baseAppearance.css().active());
        if (MGWT.getOsDetection().isDesktop()) {
          DOM.setCapture(getElement());
        }

      }
    });

    addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        removeStyleName(ButtonBase.this.baseAppearance.css().active());
      }
    });
  }

  @Override
  public String getText() {
    return getElement().getInnerText();
  }

  @Override
  public void setText(String text) {
    getElement().setInnerText(text);
  }

  @Override
  public HandlerRegistration addTapHandler(TapHandler handler) {
    return super.addTapHandler(handler);
  }
}
