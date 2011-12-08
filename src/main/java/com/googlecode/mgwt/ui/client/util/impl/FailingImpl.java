package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.user.client.Element;

public class FailingImpl implements CssUtilImpl {

	public FailingImpl() {
		throw new RuntimeException();
	}

	@Override
	public void translate(Element el, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDelay(Element el, int milliseconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOpacity(Element el, double opacity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDuration(Element el, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotate(Element element, int degree) {
		// TODO Auto-generated method stub

	}

}
