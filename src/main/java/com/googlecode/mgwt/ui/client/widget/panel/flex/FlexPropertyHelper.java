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
package com.googlecode.mgwt.ui.client.widget.panel.flex;

import com.google.gwt.dom.client.Element;

public final class FlexPropertyHelper {

  public static enum Alignment {
    START("flex-start"), END("flex-end"), CENTER("center"), STRETCH("stretch"), BASELINE("baseline");

    private final String cssValue;

    private Alignment(String cssValue) {
      this.cssValue = cssValue;
    }

    private static String getCssProperty() {
      return "AlignItems";
    }

    private String getCssValue() {
      return cssValue;
    }
  }

  public static enum Justification {
    START("flex-start"), END("flex-end"), CENTER("center"), SPACE_BETWEEN("space-between");

    private final String cssValue;

    private Justification(String cssValue) {
      this.cssValue = cssValue;
    }

    private static String getCssProperty() {
      return "JustifyContent";
    }

    private String getCssValue() {
      return cssValue;
    }
  }

  public static enum Orientation {
    HORIZONTAL("row"), VERTICAL("column");

    private final String cssValue;

    private Orientation(String cssValue) {
      this.cssValue = cssValue;
    }

    private static String getCssProperty() {
      return "Direction";
    }

    private String getCssValue() {
      return cssValue;
    }
  }

  public static void setFlex(Element el, double flex) {
    /* iOS < 7 && Android < 4.4*/
    el.getStyle().setProperty("WebkitBoxFlex", Double.toString(flex));

    el.getStyle().setProperty("MozFlex", Double.toString(flex));
    el.getStyle().setProperty("WebkitFlex", Double.toString(flex));
    el.getStyle().setProperty("flex", Double.toString(flex));
  }

  private static void setFlexProperty(Element el, String name, String value) {
    setStyleProperty(el, "MozFlex" + name, value);
    setStyleProperty(el, "webkitFlex" + name, value);
    setStyleProperty(el, "flex" + name, value);
  }

  private static void setProperty(Element el, String name, String value) {
    setStyleProperty(el, "Moz" + name, value);
    setStyleProperty(el, "webkit" + name, value);
    setStyleProperty(el, name, value);
  }

  public static void setOrientation(Element el, Orientation value) {
    // iOS6 & Android < 4.4
    switch (value) {
      case HORIZONTAL:
        el.getStyle().setProperty("WebkitBoxOrient", "horizontal");
        break;
      case VERTICAL:
        el.getStyle().setProperty("WebkitBoxOrient", "vertical");
        break;
      default:
        throw new RuntimeException();
    }
    setFlexProperty(el, Orientation.getCssProperty(), value.getCssValue());
  }

  public static void setAlignment(Element el, Alignment value) {
    // iOS6 & Android < 4.4
    switch (value) {
      case START:
        el.getStyle().setProperty("WebkitBoxAlign", "start");
        break;
      case CENTER:
        el.getStyle().setProperty("WebkitBoxAlign", "center");
        break;
      case END:
        el.getStyle().setProperty("WebkitBoxAlign", "end");
        break;
      case BASELINE:
        el.getStyle().setProperty("WebkitBoxAlign", "baseline");
        break;
      case STRETCH:
        el.getStyle().setProperty("WebkitBoxAlign", "stretch");
        break;
      default:
        throw new RuntimeException();
    }
    setProperty(el, Alignment.getCssProperty(), value.getCssValue());
  }

  public static void setJustification(Element el, Justification value) {
    // iOS6 & Android < 4.4
    switch (value) {
      case START:
        el.getStyle().setProperty("WebkitBoxPack", "start");
        break;
      case CENTER:
        el.getStyle().setProperty("WebkitBoxPack", "center");
        break;
      case END:
        el.getStyle().setProperty("WebkitBoxPack", "end");
        break;
      case SPACE_BETWEEN:
        el.getStyle().setProperty("WebkitBoxPack", "justify");
        break;
      default:
        throw new RuntimeException();
    }
    setProperty(el, Justification.getCssProperty(), value.getCssValue());
  }

  private static void setStyleProperty(Element el, String property, String value) {
    el.getStyle().setProperty(property, value);
  }

  private FlexPropertyHelper() {
  }

  public static void clearAlignment(Element element) {
    setProperty(element, Alignment.getCssProperty(), "");
  }

  public static void clearJustification(Element element) {
    setProperty(element, Justification.getCssProperty(), "");
  }
}
