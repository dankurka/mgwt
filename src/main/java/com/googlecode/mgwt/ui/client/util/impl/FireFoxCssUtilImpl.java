package com.googlecode.mgwt.ui.client.util.impl;

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

}
