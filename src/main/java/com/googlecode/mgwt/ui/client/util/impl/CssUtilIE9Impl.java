package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.user.client.Element;

public class CssUtilIE9Impl implements CssUtilImpl {

	@Override
	public void translate(Element el, int x, int y) {
		//IE9 sucks really hard
		//as soon as we get an whitespace into the translate it does not work anymore:
		// translate(1px,2px) -> working
		// translate(1px,2px ) -> NOT working
		//please ms stop making browsers
		el.getStyle().setProperty("msTransform", "translate(" + x + "px," + y + "px)");
		

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

	

	@Override
	public void rotate(Element el, int degree) {

		el.getStyle().setProperty("MsTransform", "rotate(" + degree + "deg)");

	}

}
