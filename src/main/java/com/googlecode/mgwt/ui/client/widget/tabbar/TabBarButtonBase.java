/*
 * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;

import com.googlecode.mgwt.ui.client.util.IconHandler;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBase;

/**
 * The base class for all tab bar buttonss
 *
 * @author Daniel Kurka
 */

public class TabBarButtonBase extends ButtonBase {
  @UiField
  protected Element icon;
  @UiField
  protected Element text;

  private TabBarAppearance appearance;

  protected final ImageResource selectedResource;
  protected final ImageResource imageResource;

  public TabBarButtonBase(TabBarAppearance appearance, ImageResource imageResource, ImageResource selectedResource) {
    super(appearance);
    this.appearance = appearance;
    this.imageResource = imageResource;
    this.selectedResource = selectedResource;
    setElement(this.appearance.uiBinder().createAndBindUi(this));

    IconHandler.setIcons(icon, imageResource, appearance.css().BUTTON_BACKGROUND_COLOR());
  }

  public void setSelected(boolean selected) {
    if (selected) {
      addStyleName(this.appearance.css().selected());
      if (selectedResource != null) {
        IconHandler.setIcons(icon, selectedResource, appearance.css().BUTTON_BACKGROUND_COLOR());
      }
    } else {
      removeStyleName(this.appearance.css().selected());
      if (selectedResource != null) {
        IconHandler.setIcons(icon, imageResource, appearance.css().BUTTON_BACKGROUND_COLOR());
      }
    }
  }

  @Override
  public String getText() {
    return icon.getInnerText();
  }

  @Override
  public void setText(String newText) {
    text.setInnerText(newText);
  }

  @UiFactory
  protected TabBarAppearance getAppearance() {
	  return appearance;
  }
}
