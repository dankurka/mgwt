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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBase;

/**
 * The base class for all tab bar buttonss
 *
 * @author Daniel Kurka
 */

public class TabBarButtonBase extends ButtonBase {

  public interface TabBarButtonStylerHandler {
    /**
     * set an image as background
     *
     * @param element the element to set the image on
     * @param imageResource the image to use
     */
    public void applyImage(Element element, ImageResource imageResource);
  }

  private static class TabBarButtonStylerHandlerWebkitBase implements TabBarButtonStylerHandler {

    private double factor;

    public TabBarButtonStylerHandlerWebkitBase(double factor) {
      this.factor = factor;
    }

    @Override
    public void applyImage(Element element, ImageResource imageResource) {
      if (imageResource == null)
        return;

      int height = (int) (imageResource.getHeight() / factor);
      int width = (int) (imageResource.getWidth() / factor);

      element.getStyle().setProperty("WebkitMaskBoxImage",
          "url(" + imageResource.getSafeUri().asString() + ")");
      element.getStyle().setWidth(width, Unit.PX);
      element.getStyle().setHeight(height, Unit.PX);
      element.getStyle().setProperty("WebkitMaskSize", width + "px, " + height + "px");
    }
  }

  //used by deferred binding...
  @SuppressWarnings("unused")
  private static class TabBarButtonStylerHandlerWebkit extends
      TabBarButtonStylerHandlerWebkitBase {
    public TabBarButtonStylerHandlerWebkit() {
      super(1);
    }
  }

  //used by deferred binding...
  @SuppressWarnings("unused")
  private static class TabBarButtonStylerHandlerWebkitHighDPI extends
      TabBarButtonStylerHandlerWebkitBase {
    public TabBarButtonStylerHandlerWebkitHighDPI() {
      super(1.5d);
    }
  }

  //used by deferred binding...
  @SuppressWarnings("unused")
  private static class TabBarButtonStylerHandlerWebkitXHighDPI extends
      TabBarButtonStylerHandlerWebkitBase {
    public TabBarButtonStylerHandlerWebkitXHighDPI() {
      super(2);
    }
  }

  @SuppressWarnings("unused")
  private static class TabBarButtonStylerHandlerBackground implements TabBarButtonStylerHandler {

    @Override
    public void applyImage(Element element, ImageResource imageResource) {
      if (imageResource == null)
        return;
      element.getStyle().setBackgroundImage("url(" + imageResource.getSafeUri().asString() + ")");
      element.getStyle().setHeight(imageResource.getHeight(), Unit.PX);
      element.getStyle().setWidth(imageResource.getWidth(), Unit.PX);
    }
  }

  protected static final TabBarButtonStylerHandler tabBarButtonStylerHandler = GWT
      .create(TabBarButtonStylerHandler.class);

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

    tabBarButtonStylerHandler.applyImage(icon, imageResource);
  }

  public void setSelected(boolean selected) {
    if (selected) {
      addStyleName(this.appearance.css().selected());
      if (selectedResource != null) {
        tabBarButtonStylerHandler.applyImage(icon, selectedResource);
      }
    } else {
      removeStyleName(this.appearance.css().selected());
      if (selectedResource != null) {
        tabBarButtonStylerHandler.applyImage(icon, imageResource);
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
