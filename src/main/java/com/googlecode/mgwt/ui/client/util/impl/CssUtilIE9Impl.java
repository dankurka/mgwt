package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.user.client.Element;

public class CssUtilIE9Impl implements CssUtilImpl {

	@Override
	public void translate(Element el, int x, int y) {
		String cssText = "translate( " + x + "px, " + y + "px )";

		_translate(el, cssText);

	}

	@Override
	public native void setDelay(Element el, int milliseconds) /*-{
		el.style.msTransitionDelay = milliseconds + "ms";
	}-*/;

	@Override
	public native void setOpacity(Element el, double opacity) /*-{
		el.style.opacity = opacity;

	}-*/;

	@Override
	public native void setDuration(Element el, int time) /*-{
		el.style.msTransitionDuration = time + "ms";

	}-*/;

	private native void _translate(Element el, String css)/*-{
		el.style.msTransform = css;
	}-*/;

	@Override
	public void rotate(Element el, int degree) {

		el.getStyle().setProperty("MsTransform", "rotate(" + degree + "deg)");

	}

}
