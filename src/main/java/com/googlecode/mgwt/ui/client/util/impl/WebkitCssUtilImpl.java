package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.dom.client.Element;

import com.googlecode.mgwt.ui.client.MGWT;

public class WebkitCssUtilImpl implements CssUtilImpl {

  private boolean has3d;

  public WebkitCssUtilImpl() {
    has3d = _has3d();
  }

  @Override
  public void translate(Element el, int x, int y) {
    String cssText = null;
    if (has3d() && !MGWT.getOsDetection().isDesktop()) {
      cssText = "translate3d(" + x + "px, " + y + "px, 0px)";
    } else {
      cssText = "translate( " + x + "px, " + y + "px )";
    }

    _translate(el, cssText);
  }

  @Override
  public native void setDelay(Element el, int milliseconds) /*-{
		el.style.webkitTransitionDelay = milliseconds + "ms";
  }-*/;

  @Override
  public native void setOpacity(Element el, double opacity) /*-{
		el.style.opacity = opacity;

  }-*/;

  @Override
  public native void setDuration(Element el, int time) /*-{
		el.style.webkitTransitionDuration = time + "ms";

  }-*/;

  private native void _translate(Element el, String css)/*-{
		el.style.webkitTransform = css;
  }-*/;

  @Override
  public void rotate(Element el, int degree) {
    if (MGWT.getOsDetection().isAndroid() && !MGWT.getOsDetection().isAndroid4_4_OrHigher()) {
      el.getStyle().setProperty("WebkitTransform", "rotate(" + degree + "deg)");
      // _translate(el, );
    } else {
      el.getStyle().setProperty("WebkitTransform", "rotate(" + degree + "deg) translateZ(0)");
      // _translate(el,"rotate(" + degree + "deg) translateZ(0)");
    }
  }

  @Override
  public boolean hasTransform() {
    // TODO maybe we need runtime checks for older devices, but for now this
    // is okay!
    return true;
  }

  @Override
  public boolean hasTransistionEndEvent() {
    // TODO this is okay for android from 2.1
    // so we should be okay without a runtime check
    return true;
  }

  @Override
  public boolean has3d() {
    return has3d;
  }

  private static native boolean _has3d()/*-{
		return ('WebKitCSSMatrix' in $wnd && 'm11' in new WebKitCSSMatrix())
  }-*/;

  @Override
  public String getTransformProperty() {
    return "-webkit-transform";
  }

  @Override
  public int[] getPositionFromTransForm(Element element) {
    JsArrayInteger array = getPositionFromTransform(element);
    return new int[] {array.get(0), array.get(1)};
  }

  private native JsArrayInteger getPositionFromTransform(Element el)/*-{
		var matrix = getComputedStyle(el, null)['webkitTransform'].replace(
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
  public native void resetTransform(Element el) /*-{
		el.style.webkitTransform = "";
  }-*/;

  @Override
  public native void setTransistionProperty(Element element, String string) /*-{
		element.webkitTransitionProperty = string;
  }-*/;

  @Override
  public native void setTransFormOrigin(Element el, int x, int y) /*-{
		el.webkitTransformOrigin = x + " " + y;
  }-*/;

  @Override
  public native void setTransistionTimingFunction(Element element, String string) /*-{
		el.webkitTransitionTimingFunction = string;
  }-*/;

  @Override
  public void setTranslateAndZoom(Element el, int x, int y, double scale) {
    String cssText = null;
    if (MGWT.getOsDetection().isAndroid() || MGWT.getOsDetection().isDesktop()) {
      cssText = "translate( " + x + "px, " + y + "px ) scale(" + scale + ")";
    } else {
      cssText = "translate3d(" + x + "px, " + y + "px, 0px) scale(" + scale + ")";
    }
    el.getStyle().setProperty("WebkitTransform", cssText);

  }

  @Override
  public void translatePercent(Element el, double x, double y) {
    String cssText = null;
    if (has3d() && !MGWT.getOsDetection().isDesktop()) {
      cssText = "translate3d(" + x + "%, " + y + "%, 0px)";
    } else {
      cssText = "translate( " + x + "%, " + y + "% )";
    }

    _translate(el, cssText);

  }

}
