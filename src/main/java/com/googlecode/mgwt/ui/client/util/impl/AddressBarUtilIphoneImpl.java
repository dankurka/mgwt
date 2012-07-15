/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.util.AddressBarUtil;

public class AddressBarUtilIphoneImpl implements AddressBarUtil {

  private static HandlerRegistration resizeHandler;

  public AddressBarUtilIphoneImpl() {

  }

  @Override
  public void showAddressBar() {
    Window.scrollTo(0, 0);

  }

  @Override
  public void hideAddressBar() {
    ensureResizeListener();

    updateSizeAndScrollBar();

  }

  protected void ensureResizeListener() {
    if (resizeHandler != null)
      return;

    resizeHandler = Window.addResizeHandler(new ResizeHandler() {

      @Override
      public void onResize(ResizeEvent event) {
        updateSizeAndScrollBar();
      }

    });

  }

  protected void updateSizeAndScrollBar() {
    int innerHeight = getInnerHeight();

    ORIENTATION orientation = MGWT.getOrientation();

    // ios
    switch (orientation) {
      case LANDSCAPE:
        if (innerHeight < 269) {
          updateSize(269); // 208 + 60 +1
        }

        break;

      case PORTRAIT:
        if (innerHeight < 417) {
          updateSize(417); // 356 + 60 +1
        }
      default:
        break;
    }

    doScroll();
  }

  private static void updateSize(int y) {
    Document.get().getBody().getStyle().setPropertyPx("minHeight", y);
  }

  private static void doScroll() {
    new Timer() {

      @Override
      public void run() {
        Window.scrollTo(0, 1);

      }
    }.schedule(1);
  }

  private native static int getInnerHeight()/*-{
		return $wnd.innerHeight;
  }-*/;

}
