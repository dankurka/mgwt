/*
 * Copyright 2014 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.button;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;

import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.util.IconHandler;
import com.googlecode.mgwt.ui.client.widget.base.IsSizeable;

/**
 * A simple button containing an image.
 */
public class ImageButton extends ButtonBase implements IsSizeable {

  private static final ImageButtonAppearance DEFAULT_BUTTON_APPEARANCE = GWT
      .create(ImageButtonAppearance.class);

  private final ImageButtonAppearance appearance;

  @UiField
  Element text;

  @UiField
  Element image;

  private ImageResource icon;

  public ImageButton() {
    this(DEFAULT_BUTTON_APPEARANCE, "");
  }

  public ImageButton(String text) {
    this(DEFAULT_BUTTON_APPEARANCE, text);
  }

  public ImageButton(ImageResource icon) {
    this(DEFAULT_BUTTON_APPEARANCE, icon, "");
  }

  public ImageButton(ImageButtonAppearance appearance, String text) {
    this(appearance, null, text);
  }

  public ImageButton(ImageButtonAppearance appearance, ImageResource iconImage, String text) {
    super(appearance);
    this.appearance = appearance;
    setElement(appearance.uiBinder().createAndBindUi(this));
    setIcon(iconImage);

    // iOS6 and old android have problems with the aligning in flexible box model with inline-block
    // elements
    if (MGWT.getOsDetection().isAndroid4_3_orLower() || MGWT.getOsDetection().isIOS6()) {
      this.text.getStyle().setDisplay(Display.BLOCK);
    }

    addTouchHandler(new TouchHandler() {

      @Override
      public void onTouchCancel(TouchCancelEvent event) {

        IconHandler.setIcons(image, icon, ImageButton.this.appearance.css()
            .ICON_BACKGROUND_COLOR());
      }

      @Override
      public void onTouchEnd(TouchEndEvent event) {

        IconHandler.setIcons(image, icon, ImageButton.this.appearance.css()
            .ICON_BACKGROUND_COLOR());
      }

      @Override
      public void onTouchMove(TouchMoveEvent event) {
      }

      @Override
      public void onTouchStart(TouchStartEvent event) {

        IconHandler.setIcons(image, icon, ImageButton.this.appearance.css()
            .ICON_BACKGROUND_COLOR_HIGHLIGHT());
      }
    });
  }

  @UiFactory
  protected ImageButtonAppearance getAppearance() {
    return appearance;
  }

  @Override
  public String getText() {
    return text.getInnerText();
  }

  @Override
  public void setText(String text) {
    this.text.setInnerText(text);
  }

  public void setIcon(ImageResource icon) {
    this.icon = icon;
    IconHandler.setIcons(image, icon, appearance.css().ICON_BACKGROUND_COLOR());
  }

  @Override
  public void setSmall(boolean small) {
    if (small) {
      addStyleName(appearance.css().small());
    } else {
      removeStyleName(appearance.css().small());
    }
  }
}
