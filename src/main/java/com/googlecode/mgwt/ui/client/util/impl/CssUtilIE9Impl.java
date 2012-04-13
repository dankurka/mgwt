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

	@Override
	public boolean hasTransform() {
		//TODO this is okay for IE9 review this for IE10
		return false;
	}

	@Override
	public boolean hasTransistionEndEvent() {
		// TODO IE9 no support IE10 should be okay!
		return false;
	}

	@Override
	public boolean has3d() {
		return false;
	}

	@Override
	public String getTransformProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getPositionFromTransForm(Element element) {
		throw new RuntimeException("no ie support!");
	}
	
	

	@Override
	public native int getTopPositionFromCssPosition(Element element) /*-{
		return getComputedStyle(that.scroller, null).top.replace(/[^0-9-]/g, '') * 1;
	}-*/;

	@Override
	public native int getLeftPositionFromCssPosition(Element element)/*-{
		return getComputedStyle(that.scroller, null).left.replace(/[^0-9-]/g, '') * 1;
	}-*/;


}
