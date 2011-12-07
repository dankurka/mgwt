package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.user.client.Element;

public interface CssUtilImpl {

	public void translate(Element el, int x, int y);

	public void setDelay(Element el, int milliseconds);

	public void setOpacity(Element el, double opacity);

	public void setDuration(Element el, int time);

	public void rotate(Element element, int degree);

}
