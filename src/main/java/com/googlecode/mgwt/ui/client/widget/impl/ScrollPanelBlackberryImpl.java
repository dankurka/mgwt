/*
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.impl;

import java.util.Iterator;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.collection.shared.LightArrayInt;

/**
 * The blackberry implementation of scrollpanel lacks any scrolling capabilities
 * because blackberry phones don't offer any input method to handle scrolling on
 * anything else than the web page itself.
 * 
 * Therefore content is just placed in a div so that we can use the scrolling of
 * the whole web page
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class ScrollPanelBlackberryImpl extends ScrollPanelImpl {

	private SimplePanel main;
	private boolean scrollingEnabledX;
	private boolean scrollingEnabledY;

	/**
	 * <p>
	 * Constructor for ScrollPanelBlackberryImpl.
	 * </p>
	 */
	public ScrollPanelBlackberryImpl() {
		main = new SimplePanel();
		initWidget(main);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public void add(Widget w) {
		main.add(w);

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	/** {@inheritDoc} */
	@Override
	public void clear() {
		main.clear();

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
	 */
	/** {@inheritDoc} */
	@Override
	public Iterator<Widget> iterator() {
		return main.iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public boolean remove(Widget w) {
		return main.remove(w);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setUsePos(boolean)
	 */
	/** {@inheritDoc} */
	@Override
	public void setUsePos(boolean pos) {

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#scrollTo(int, int, int)
	 */
	/** {@inheritDoc} */
	@Override
	public void scrollTo(int destX, int destY, int newDuration) {

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#isScrollingEnabledX()
	 */
	/** {@inheritDoc} */
	@Override
	public boolean isScrollingEnabledX() {
		return scrollingEnabledX;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setScrollingEnabledX(boolean)
	 */
	/** {@inheritDoc} */
	@Override
	public void setScrollingEnabledX(boolean scrollingEnabledX) {
		this.scrollingEnabledX = scrollingEnabledX;

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#isScrollingEnabledY()
	 */
	/** {@inheritDoc} */
	@Override
	public boolean isScrollingEnabledY() {
		return scrollingEnabledY;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setScrollingEnabledY(boolean)
	 */
	/** {@inheritDoc} */
	@Override
	public void setScrollingEnabledY(boolean scrollingEnabledY) {
		this.scrollingEnabledY = scrollingEnabledY;

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setWidget(com.google.gwt.user.client.ui.IsWidget)
	 */
	/** {@inheritDoc} */
	@Override
	public void setWidget(IsWidget child) {
		main.setWidget(child);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#refresh()
	 */
	/** {@inheritDoc} */
	@Override
	public void refresh() {

	}

	@Override
	public void setOffSetY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxScrollY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxScrollY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMinScrollY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMinScrollY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void scrollTo(int x, int y, int time, boolean relative) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scrollToPage(int pageX, int pageY, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBounce(boolean bounce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMomentum(boolean momentum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSnap(boolean snap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSnapThreshold(int threshold) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBounceFactor(double factor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setShowScrollBarX(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setShowScrollBarY(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCurrentPageX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentPageY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAutoHandleResize(boolean handle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOffSetMaxY(int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSnapSelector(String selector) {
		// TODO Auto-generated method stub

	}

	@Override
	public LightArrayInt getPagesY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LightArrayInt getPagesX() {
		// TODO Auto-generated method stub
		return null;
	}

}
