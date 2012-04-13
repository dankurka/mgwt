package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.user.client.Element;

public class FireFoxCssUtilImpl implements CssUtilImpl {

	public FireFoxCssUtilImpl() {

	}

	@Override
	public void translate(Element el, int x, int y) {
		String cssText = null;
		cssText = "translate( " + x + "px, " + y + "px )";
		el.getStyle().setProperty("MozTransform", cssText);

	}

	@Override
	public void setDelay(Element el, int milliseconds) {
		el.getStyle().setProperty("MozTransitionDelay", milliseconds + "ms");

	}

	@Override
	public void setOpacity(Element el, double opacity) {
		el.getStyle().setOpacity(opacity);
	}

	@Override
	public void setDuration(Element el, int time) {
		el.getStyle().setProperty("MozTransitionDuration", time + "ms");
	}

	@Override
	public void rotate(Element el, int degree) {
		el.getStyle().setProperty("MozTransform", "rotate(" + degree + "deg)");
	}

	@Override
	public boolean hasTransform() {
		//TODO maybe we need runtime checks for older devices, but for now this is okay!
		return true;
	}

	@Override
	public boolean hasTransistionEndEvent() {
		// TODO review this for mobile some time!
		return true;
	}

	@Override
	public boolean has3d() {
		return false;
	}

	@Override
	public String getTransformProperty() {
		return "-moz-transform";
	}

	@Override
	public int[] getPositionFromTransForm(Element element) {
		JsArrayInteger array = getPositionFromTransform(element);
		return new int[]{array.get(0), array.get(1)};
	}
	
	private native JsArrayInteger getPositionFromTransform(Element el)/*-{
		var matrix = getComputedStyle(that.scroller, null)['mozTransform'].replace(/[^0-9-.,]/g, '').split(',');
		var x = matrix[4] * 1;
		var y = matrix[5] * 1;
		return [x, y];
	}-*/;

	@Override
	public native int getTopPositionFromCssPosition(Element element) /*-{
		return getComputedStyle(that.scroller, null).top.replace(/[^0-9-]/g, '') * 1;
	}-*/;

	@Override
	public native int getLeftPositionFromCssPosition(Element element)/*-{
		return getComputedStyle(that.scroller, null).left.replace(/[^0-9-]/g, '') * 1;
	}-*/;


}
