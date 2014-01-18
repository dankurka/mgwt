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

import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.dom.client.Element;

public class OperaCssUtilImpl implements CssUtilImpl {

  @Override
  public void translate(Element el, int x, int y) {
    String cssText = null;
    cssText = "translate( " + x + "px, " + y + "px )";
    el.getStyle().setProperty("OTransform", cssText);

  }

  @Override
  public void setDelay(Element el, int milliseconds) {
    el.getStyle().setProperty("OTransitionDelay", milliseconds + "ms");

  }

  @Override
  public void setOpacity(Element el, double opacity) {
    el.getStyle().setOpacity(opacity);
  }

  @Override
  public void setDuration(Element el, int time) {
    el.getStyle().setProperty("OTransitionDuration", time + "ms");
  }

  @Override
  public void rotate(Element el, int degree) {
    el.getStyle().setProperty("OTransform", "rotate(" + degree + "deg)");
  }

  @Override
  public boolean hasTransform() {
    return true;
  }

  @Override
  public boolean hasTransistionEndEvent() {
    return true;
  }

  @Override
  public boolean has3d() {
    return true;
  }

  @Override
  public String getTransformProperty() {
    return "-o-transform";
  }

  @Override
  public int[] getPositionFromTransForm(Element element) {
    JsArrayInteger array = getPositionFromTransform(element);
    return new int[] {array.get(0), array.get(1)};
  }

  private native JsArrayInteger getPositionFromTransform(Element el)/*-{
		var matrix = getComputedStyle(el, null)['OTransform'].replace(
				/[^0-9-.,]/g, '').split(',');
		var x = matrix[4] * 1;
		var y = matrix[5] * 1;
		return [ x, y ];
  }-*/;

  @Override
  public native int getTopPositionFromCssPosition(Element element) /*-{
		return getComputedStyle(element, null).top.replace(/[^0-9-]/g, '') * 1;
  }-*/;

  @Override
  public native int getLeftPositionFromCssPosition(Element element)/*-{
		return getComputedStyle(element, null).left.replace(/[^0-9-]/g, '') * 1;
  }-*/;

  @Override
  public void resetTransform(Element element) {
    element.getStyle().setProperty("OTransform", "");

  }

  @Override
  public native void setTransistionProperty(Element element, String string) /*-{
		element.oTransitionProperty = string;
  }-*/;

  @Override
  public native void setTransFormOrigin(Element el, int x, int y) /*-{
		el.oTransformOrigin = x + " " + y;
  }-*/;

  @Override
  public native void setTransistionTimingFunction(Element element, String string) /*-{
		el.oTransitionTimingFunction = string;
  }-*/;

  @Override
  public void setTranslateAndZoom(Element el, int x, int y, double scale) {
    String cssText = null;

    cssText = "translate( " + x + "px, " + y + "px ) scale( + " + scale + ")";

    el.getStyle().setProperty("oTransform", cssText);

  }

  @Override
  public void translatePercent(Element el, double x, double y) {
    // TODO Auto-generated method stub

  }
}
