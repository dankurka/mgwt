package com.googlecode.mgwt.ui.client.widget.impl;

import java.util.Iterator;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartHandler;

public class ScrollPanelBlackberryImpl extends ScrollPanelImpl {

	private SimplePanel main;
	private boolean scrollingEnabledX;
	private boolean scrollingEnabledY;

	public ScrollPanelBlackberryImpl() {
		main = new SimplePanel();
		initWidget(main);
	}

	@Override
	public void add(Widget w) {
		main.add(w);

	}

	@Override
	public void clear() {
		main.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return main.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return main.remove(w);
	}

	@Override
	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler) {
		return new NoopHandlerRegistration();
	}

	@Override
	public HandlerRegistration addScrollhandler(ScrollHandler scrollHandler) {
		return new NoopHandlerRegistration();
	}

	@Override
	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler) {
		return new NoopHandlerRegistration();
	}

	@Override
	public void setPosition(int newPosX, int newPosY) {

	}

	@Override
	public void setUsePos(boolean pos) {

	}

	@Override
	public void scrollTo(int destX, int destY, int newDuration) {

	}

	@Override
	public boolean isScrollingEnabledX() {
		return scrollingEnabledX;
	}

	@Override
	public void setScrollingEnabledX(boolean scrollingEnabledX) {
		this.scrollingEnabledX = scrollingEnabledX;

	}

	@Override
	public boolean isScrollingEnabledY() {
		return scrollingEnabledY;
	}

	@Override
	public void setScrollingEnabledY(boolean scrollingEnabledY) {
		this.scrollingEnabledY = scrollingEnabledY;

	}

	@Override
	public void setOffset(int offsetX, int offsetY) {

	}

	@Override
	public void setWidget(IsWidget child) {
		main.setWidget(child);

	}

	@Override
	public void refresh() {

	}

	private static class NoopHandlerRegistration implements HandlerRegistration {

		@Override
		public void removeHandler() {

		}

	}

}
