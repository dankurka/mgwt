package com.googlecode.mgwt.ui.client.widget.impl;

import java.util.Iterator;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.collection.shared.CollectionFactory;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.collection.shared.LightArrayInt;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartHandler;

public class ScrollPanelNewPort extends ScrollPanelImpl{

	private static double ZOOM_MIN = 1;
	private static double ZOOM_MAX = 4;
	
	private static class Step{
		
	}
	
	private boolean enabled;
	private int x;
	private int y;
	private LightArray<Step> steps;
	private int currentPageX;
	private int currentPageY;
	private LightArrayInt pagesX;
	private LightArrayInt pagesY;
	
	private SimplePanel wrapper;
	private Widget scroller;
	private double scale;
	
	private double zoomMin;
	private double zoomMax;
	private int wrapperHeight;
	private int wrapperWidth;
	
	//offset from top
	private int topOffset;
	private int minScrollY;
	private int scrollerWidth;
	private int scrollerHeight;
	private int maxScrollX;
	private int maxScrollY;
	private int dirX;
	private int dirY;
	
	//enable disable horizontal scroll
	private boolean hScroll;
	//enable disable vertical scroll
	private boolean vScroll;
	private boolean bounceLock;
	private boolean hScrollbar;
	private boolean vScrollbar;
	private int wrapperOffsetLeft;
	private int wrapperOffsetTop;
	
	

	public ScrollPanelNewPort() {
		
		wrapper = new SimplePanel();
		initWidget(wrapper);
		
		enabled = true;
		x = 0;
		y = 0;
		
		steps = CollectionFactory.constructArray();
		
		currentPageX = 0;
		currentPageY = 0;
		
		pagesX = CollectionFactory.constructIntegerArray();
		pagesY = CollectionFactory.constructIntegerArray();
		
		zoomMin = ZOOM_MIN;
		zoomMax = ZOOM_MAX;
		
		//setup events!
		
		
	}
	
	@Override
	public void add(Widget w) {
		if (scroller != null) {
			throw new IllegalStateException("scrollpanel can only have one child");
		}
		setWidget(w);
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addScrollhandler(ScrollHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(int newPosX, int newPosY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUsePos(boolean pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollTo(int destX, int destY, int newDuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isScrollingEnabledX() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setScrollingEnabledX(boolean scrollingEnabledX) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isScrollingEnabledY() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setScrollingEnabledY(boolean scrollingEnabledY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOffset(int offsetX, int offsetY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidget(IsWidget child) {
		// TODO Auto-generated method stub
		
	}
	
	public void setWidget(Widget w) {
		

	}

	@Override
	public void refresh() {
		

	if (scale < zoomMin){
		scale = zoomMin;
	}
	wrapperHeight = getClientHeight(wrapper.getElement());
	if(wrapperHeight == 0){
		wrapperHeight = 1;
	}
	wrapperWidth = getClientWidth(wrapper.getElement());
	if(wrapperWidth == 0){
		wrapperWidth = 1;
	}
	
	minScrollY = -topOffset;
	

	scrollerWidth = (int) Math.round(scroller.getOffsetWidth() * scale);
	scrollerHeight = (int) Math.round((scroller.getOffsetHeight() + minScrollY) * scale);
	
	maxScrollX = wrapperWidth - scrollerWidth;
	maxScrollY = wrapperHeight - scrollerHeight + minScrollY;
	dirX = 0;
	dirY = 0;

	//fire refresh event
	

	hScroll = hScroll && maxScrollX < 0;
	vScroll = vScroll && (!bounceLock && !hScroll || scrollerHeight > wrapperHeight);

	hScrollbar = hScroll && hScrollbar;
	vScrollbar = vScroll && vScrollbar && scrollerHeight > wrapperHeight;

	
	//TODO
	LightArrayInt array = calculateOffSet(wrapper);
	
	wrapperOffsetLeft = -array.shift();
	wrapperOffsetTop = -array.shift();

	

	//prep stuff
	
	}
	
	

	private LightArrayInt calculateOffSet(SimplePanel wrapper2) {
		// TODO calculate
		return CollectionFactory.constructIntegerArray();
	}

	//TODO move in util
	private native int getClientHeight(Element element)/*-{
		return element.clientHeight || 0;
	}-*/;
	
	private native int  getClientWidth(Element element) /*-{
		return element.clientWidth || 0;
	}-*/;

}
