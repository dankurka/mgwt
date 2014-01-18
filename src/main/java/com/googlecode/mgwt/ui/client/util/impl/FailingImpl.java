package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.dom.client.Element;


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

	@Override
	public boolean hasTransform() {
		return false;
	}

	@Override
	public boolean hasTransistionEndEvent() {
		return false;
	}

	@Override
	public boolean has3d() {
		return false;
	}

	@Override
	public String getTransformProperty() {
		return null;
	}

	@Override
	public int[] getPositionFromTransForm(Element element) {
		// TODO Auto-generated method stub
		return new int[0];
	}

	@Override
	public int getTopPositionFromCssPosition(Element element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeftPositionFromCssPosition(Element element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetTransform(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTransistionProperty(Element element, String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTransFormOrigin(Element element, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTransistionTimingFunction(Element element, String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTranslateAndZoom(Element element, int x, int y, double scale) {
		// TODO Auto-generated method stub

	}

  @Override
  public void translatePercent(Element el, double x, double y) {
    // TODO Auto-generated method stub

  }

}
