package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.user.client.Element;
import com.googlecode.mgwt.ui.client.util.FeatureDetection;

public class WebkitCssUtilImpl implements CssUtilImpl {

	public WebkitCssUtilImpl() {

	}

	@Override
	public void translate(Element el, int x, int y) {
		String cssText = null;
		if (FeatureDetection.has3d()) {
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

}
