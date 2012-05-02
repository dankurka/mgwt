package com.googlecode.mgwt.ui.client.widget.impl;

import java.util.Iterator;
import java.util.logging.Logger;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.animation.client.AnimationScheduler.AnimationHandle;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.collection.shared.CollectionFactory;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.collection.shared.LightArrayInt;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartHandler;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

public class ScrollPanelNewPort extends ScrollPanelImpl {
	
	private static Logger logger = Logger.getLogger(ScrollPanelNewPort.class.getName()); 

	private static double ZOOM_MIN = 1;
	private static double ZOOM_MAX = 4;

	private class TouchListener implements TouchHandler {

		@Override
		public void onTouchStart(TouchStartEvent event) {
			start(event);

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			move(event);

		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {
			end(event);

		}

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			end(event);

		}

	}

	private static class Step {
		private final int x;

		private final int y;
		private int time;

		public Step(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;

		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int i) {
			this.time = 0;

		}
	}

	private static class Momentum {

		public static final Momentum ZERO_MOMENTUM = new Momentum(0, 0);

		private final int time;
		private final int dist;

		/**
		 * 
		 */
		public Momentum(int dist, int time) {
			this.dist = dist;
			this.time = time;

		}

		public int getTime() {
			return time;
		}

		public int getDist() {
			return dist;
		}

	}

	private static class Snap {
		private final int x;
		private final int y;
		private final int time;

		public Snap(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;

		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getTime() {
			return time;
		}
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
	private boolean moved;
	private boolean zoomed;
	private boolean animating;
	private boolean useTransform;
	private boolean zoom;
	private boolean useTransistion;
	private int distX;
	private int distY;
	private int absDistX;
	private int absDistY;
	private double touchesDistStart;
	private int originX;
	private int originY;
	private boolean momentum;
	private int absStartX;
	private int absStartY;
	private int startX;
	private int startY;
	private int pointX;
	private int pointY;
	private long startTime;
	private double touchesDist;
	private double lastScale;
	private boolean bounce;
	private boolean lockDirection;
	private Timer doubleTapTimer;
	private boolean snap;
	private int snapThreshold;
	private boolean wheelActionZoom;
	private int wheelZoomCount;
	protected AnimationHandle aniTime;
	private int currPageX;
	private int currPageY;
	private String snapSelector;
	private TouchListener touchListener;
	private HandlerRegistration transistionEndRegistration;

	public ScrollPanelNewPort() {

		wrapper = new SimplePanel();

		//TODO 
		wrapper.getElement().getStyle().setOverflow(Overflow.HIDDEN);

		initWidget(wrapper);

		touchListener = new TouchListener();

		enabled = true;
		steps = CollectionFactory.constructArray();
		scale = 1.0;
		currentPageX = 0;
		currentPageY = 0;
		pagesX = CollectionFactory.constructIntegerArray();
		pagesY = CollectionFactory.constructIntegerArray();
		wheelZoomCount = 0;

		//setup events!

		//setting standard options
		this.hScroll = true;
		this.vScroll = true;
		this.x = 0;
		this.y = 0;
		this.bounce = true;
		this.bounceLock = false;
		this.momentum = true;
		this.lockDirection = true;
		setUseTransform(true);
		setUseTransistion(false);
		this.topOffset = 0;

		//Zoom
		setZoom(false);
		this.zoomMin = ZOOM_MIN;
		this.zoomMax = ZOOM_MAX;

		//snap
		this.snap = false;
		this.snapSelector = null;
		this.snapThreshold = 1;

	}

	public void setUseTransistion(boolean useTransistion) {
		this.useTransistion = CssUtil.hasTransistionEndEvent() && useTransistion;
	}

	public void setUseTransform(boolean useTransform) {
		this.useTransform = CssUtil.hasTransform() && useTransform;
	}

	public void setZoom(boolean zoom) {
		this.zoom = zoom && useTransform;
	}

	private void checkDOMChanges() {
		if (moved || zoomed || animating || (scrollerWidth == scroller.getOffsetWidth() * scale && scrollerHeight == scroller.getOffsetHeight() * scale)) {
			return;
		}

		refresh();
	}

	private void scrollBar(int direction) {
		//TODO
	}

	private void resize() {
		new Timer() {

			@Override
			public void run() {
				refresh();

			}

		}.schedule(MGWT.getOsDetection().isAndroid() ? 200 : 1);
	}

	private void pos(int x, int y) {
		x = hScroll ? x : 0;
		y = vScroll ? y : 0;

		if (useTransform) {
			CssUtil.translate(scroller.getElement(), x, y);
		} else {
			//TODO
			scroller.getElement().getStyle().setLeft(x, Unit.PX);
			scroller.getElement().getStyle().setTop(y, Unit.PX);
			
		}

		this.x = x;
		this.y = y;

		//TODO
		//update scrollerbars

	}

	private void scrollbarPos(String dir, boolean hidden) {
		//TODO
	}

	private void start(TouchStartEvent event) {
		int x, y;
		if (!this.enabled) {
			return;
		}
		
		System.out.println("scrolling started");
		
		//TODO call onbefore scroll start

		if (this.useTransistion || this.zoom) {
			setTransistionTime(0);
		}

		this.moved = false;
		this.animating = false;
		this.zoomed = false;
		this.distX = 0;
		this.distY = 0;
		this.absDistX = 0;
		this.absDistY = 0;
		this.dirX = 0;
		this.dirY = 0;

		LightArray<Touch> touches = event.getTouches();

		if (this.zoom && touches.length() > 1) {
			int c1 = Math.abs(touches.get(0).getPageX() - touches.get(1).getPageX());
			int c2 = Math.abs(touches.get(0).getPageY() - touches.get(1).getPageY());
			this.touchesDistStart = Math.sqrt(c1 * c1 + c2 * c2);

			this.originX = Math.abs(touches.get(0).getPageX() + touches.get(1).getPageX() - this.wrapperOffsetLeft * 2 / 2 - this.x);
			this.originY = Math.abs(touches.get(0).getPageY() + touches.get(1).getPageY() - this.wrapperOffsetTop * 2 / 2 - this.y);

			//call on zoom start
		}

		if (this.momentum) {
			if (this.useTransform) {
				int[] pos = CssUtil.getPositionFromTransForm(scroller.getElement());
				x = pos[0];
				y = pos[1];
			} else {
				x = CssUtil.getLeftPositionFromCssPosition(scroller.getElement());
				y = CssUtil.getTopPositionFromCssPosition(scroller.getElement());
			}

			if (x != this.x || y != this.y) {
				if (this.useTransistion) {
					unbindTransistionEnd();
				} else {
					cancelAnimationFrame();
				}
				this.steps = CollectionFactory.constructArray();
				pos(x, y);
			}
		}

		this.absStartX = this.x;
		this.absStartY = this.y;

		this.startX = this.x;
		this.startY = this.y;
		this.pointX = touches.get(0).getPageX();
		this.pointY = touches.get(0).getPageY();

		this.startTime = System.currentTimeMillis();

		//fire on scrollStart event

		bindMoveEvent();
		bindEndEvent();
		bindCancelEvent();
	}

	private void move(TouchMoveEvent event) {
		System.out.println("touch move");
		
		LightArray<Touch> touches = event.getTouches();
		int deltaX = touches.get(0).getPageX() - this.pointX;
		int deltaY = touches.get(0).getPageY() - this.pointY;
		int newX = this.x + deltaX;
		int newY = this.y = deltaY;
		long timeStamp = System.currentTimeMillis();

		//fire onbeforescroll event

		if (zoom && touches.length() > 1) {
			int c1 = Math.abs(touches.get(0).getPageX() - touches.get(1).getPageX());
			int c2 = Math.abs(touches.get(0).getPageY() - touches.get(1).getPageY());
			this.touchesDist = Math.sqrt(c1 * c1 + c2 * c2);
			this.zoomed = true;

			double scale = 1 / this.touchesDistStart * this.touchesDist * this.scale;
			if (scale < this.zoomMin) {
				scale = 0.5 * zoomMin * Math.pow(2.0, scale / zoomMin);
			} else {
				if (scale > zoomMax) {
					scale = 2.0 * zoomMax * Math.pow(0.5, zoomMax / scale);
				}
			}
			this.lastScale = scale / this.scale;

			newX = (int) Math.round(this.originX - this.originX * this.lastScale + this.x);
			newY = (int) Math.round(this.originY - this.originY * this.lastScale + this.y);

			//update scroller

			//call on zoom
			return;
		}

		this.pointX = touches.get(0).getPageX();
		this.pointY = touches.get(0).getPageY();
		
		System.out.println("newX: " + newX + " newY " + newY);

		//slower outside the bounds!
		if (newX > 0 || newX < this.maxScrollX) {
			if (bounce) {
				newX = this.x + deltaX / 2;
			} else {
				if (newX >= 0 || this.maxScrollX >= 0) {
					newX = 0;
				} else {
					newX = this.maxScrollX;
				}
			}
		}
		if (newY > this.minScrollY || newY < this.maxScrollY) {
			if (bounce) {
				newY = this.y + deltaY / 2;
			} else {
				if (newY >= this.minScrollY || this.maxScrollY >= 0) {
					newY = this.minScrollY;
				} else {
					newY = this.maxScrollY;
				}
			}
		}

		this.distX += deltaX;
		this.distY += deltaY;
		this.absDistX = Math.abs(this.distX);
		this.absDistY = Math.abs(this.distY);

		if (this.absDistX < 6 && this.absDistY < 6) {
			return;
		}

		if (lockDirection) {
			if (this.absDistX > this.absDistY + 5) {
				newY = this.y;
				deltaY = 0;
			} else {
				if (this.absDistY > this.absDistX + 5) {
					newX = this.x;
					deltaX = 0;
				}
			}
		}

		this.moved = true;
		System.out.println("newX: " + newX + " newY " + newY);
		pos(newX, newY);

		this.dirX = deltaX > 0 ? -1 : deltaX < 0 ? 1 : 0;
		this.dirY = deltaY > 0 ? -1 : deltaY < 0 ? 1 : 0;

		if (timeStamp - this.startTime > 300) {
			this.startTime = timeStamp;
			this.startX = this.x;
			this.startY = this.y;
		}

		//call on scroll move
	}

	private void end(final TouchEvent<?> event) {
		System.out.println("touch end");
		
		if (event != null && event.getTouches().length() != 0) {
			return;
		}
		System.out.println("touch end 0 ");
		

		long duration = System.currentTimeMillis() - this.startTime;
		int newPosX = this.x;
		int newPosY = this.y;
		Momentum momentumX = Momentum.ZERO_MOMENTUM;
		Momentum momentumY = Momentum.ZERO_MOMENTUM;

		unbindMoveEvent();
		unbindEndEvent();
		unbindCancelEvent();

		//fire on before scroll end

		if (zoomed) {
			double scale = this.scale * this.lastScale;
			scale = Math.max(this.zoomMin, scale);
			scale = Math.min(this.zoomMax, scale);
			this.lastScale = scale / this.scale;

			this.x = (int) Math.round(this.originX - this.originX * this.lastScale + this.x);
			this.y = (int) Math.round(this.originY - this.originY * this.lastScale + this.y);

			//update scrollbars

			this.zoomed = false;
			this.refresh();

			//fire onzoomend

			return;

		}

		if (!this.moved) {
			if (this.doubleTapTimer != null && zoom) {
				doubleTapTimer.cancel();
				doubleTapTimer = null;

				//fire on zoom start

				//fire zoom end after duration
			} else {
				this.doubleTapTimer = new Timer() {

					@Override
					public void run() {
						doubleTapTimer = null;

						//dispatch tap event

					}
				};
				this.doubleTapTimer.schedule(this.zoom ? 250 : 1);
			}

			resetPos(200);

			//fire touchend!
			return;
		}

		if (duration < 300 && momentum) {
			if (newPosX != 0) {
				momentumX = momentum(newPosX - this.startX, duration, -this.x, this.scrollerWidth - this.wrapperWidth + this.x, this.bounce ? this.wrapperWidth : 0);
			}
			if (newPosY != 0) {
				momentumY = momentum(newPosY - this.startY, duration, -this.y, (this.maxScrollY < 0 ? this.scrollerHeight - this.wrapperHeight + this.y - this.minScrollY : 0),
						this.bounce ? this.wrapperHeight : 0);
			}

			newPosX = this.x + momentumX.getDist();
			newPosY = this.y = momentumY.getDist();

			if ((this.x > 0 && newPosX > 0) || (this.x < this.maxScrollX && newPosX < this.maxScrollX)) {
				momentumX = Momentum.ZERO_MOMENTUM;
			}

			if ((this.y > this.minScrollY && newPosY > this.minScrollY) || (this.y < this.maxScrollY && newPosY < this.maxScrollY)) {
				momentumY = Momentum.ZERO_MOMENTUM;
			}

		}

		int distX = 0;
		int distY = 0;

		if (momentumX.getDist() != 0 || momentumY.getDist() != 0) {
			int newDuration = Math.max(Math.max(momentumX.getTime(), momentumY.getTime()), 10);

			if (this.snap) {
				distX = newPosX - this.absStartX;
				distY = newPosY - this.absStartY;

				if (Math.abs(distX) < this.snapThreshold && Math.abs(distY) < this.snapThreshold) {
					scrollTo(this.absStartX, this.absStartY, 200);
				} else {
					Snap snap = snap(newPosX, newPosY);
					newPosX = snap.getX();
					newPosY = snap.getY();
					newDuration = Math.max(snap.getTime(), newDuration);
				}

			}
			scrollTo(newPosY, newPosY, newDuration);

			//fire touch end!
			return;
		}

		if (this.snap) {
			distX = newPosX - this.absDistX;
			distY = newPosY - this.absDistY;

			if (Math.abs(distX) < this.snapThreshold && Math.abs(distY) < this.snapThreshold) {
				scrollTo(this.absStartX, this.absStartY, 200);
			} else {
				Snap snap = snap(this.x, this.y);
				if (snap.x != this.x || snap.y != this.y) {
					scrollTo(snap.x, snap.y, snap.time);
				}
			}

			//fire on touch end
			return;

		}

		resetPos(200);
		//fire on touch end

	}

	private void resetPos(int time) {

		int resetX = this.x >= 0 ? 0 : this.x < this.maxScrollX ? this.maxScrollX : this.x;

		int resetY = this.y >= this.minScrollY || this.maxScrollY > 0 ? this.minScrollY : this.y < this.maxScrollY ? this.maxScrollY : this.y;

		if (resetX == this.x && resetY == this.y) {
			if (this.moved) {
				this.moved = false;
				//fire on scroll end
			}

			//update scrollbars

			return;
		}

		scrollTo(resetX, resetY, time);

	}

	private void wheel(int wheelDeltaX, int wheelDeltaY, int pageX, int pageY) {

		if (wheelActionZoom) {
			double deltaScale = this.scale * Math.pow(2, 1.0 / 3 * (wheelDeltaY != 0 ? wheelDeltaY / Math.abs(wheelDeltaY) : 0));
			if (deltaScale < this.zoomMin)
				deltaScale = this.zoomMin;
			if (deltaScale > this.zoomMax)
				deltaScale = this.zoomMax;

			if (deltaScale != this.scale) {
				if (this.wheelZoomCount == 0) {
					//TODO maybe fire on zoom start
				}
				this.wheelZoomCount++;

				zoom(pageX, pageY, deltaScale, 400);

				new Timer() {

					@Override
					public void run() {
						ScrollPanelNewPort.this.wheelZoomCount--;
						if (ScrollPanelNewPort.this.wheelZoomCount == 0) {
							//TODO maybe fire zoom end
						}

					}

				}.schedule(400);
			}
			return;
		}

		int deltaX = this.x + wheelDeltaX;
		int deltaY = this.y = wheelDeltaY;

		if (deltaX > 0)
			deltaX = 0;
		else if (deltaX < this.maxScrollX)
			deltaX = this.maxScrollX;

		if (deltaY > this.minScrollY)
			deltaY = this.minScrollY;
		else if (deltaY < this.maxScrollY)
			deltaY = this.maxScrollY;

		scrollTo(deltaX, deltaY, 0);

	}

	private void mouseOut(MouseOutEvent event) {
		//TODO
//		EventTarget relatedTarget = event.getRelatedTarget();
//
//		if (relatedTarget == null) {
//
//			end(null);
//			return;
//		}
//
//		Node tmp;
//
//		while (true) {
//			if (Node.is(relatedTarget)) {
//				Node n = relatedTarget.cast();
//				tmp = n.getParentNode();
//
//				if (tmp == this.wrapper.getElement()) {
//					return;
//				}
//
//				if (tmp == null) {
//					break;
//				}
//
//			}
//		}

		this.end(null);

	}

	private void onTransistionEnd(TransitionEndEvent event) {
		EventTarget eventTarget = event.getNativeEvent().getEventTarget();
		if (Node.is(eventTarget)) {
			if (Element.is(eventTarget)) {
				Element target = eventTarget.cast();
				Element scrollerElement = this.scroller.getElement();
				//reference id should be okay according to http://google-web-toolkit.googlecode.com/svn/javadoc/latest/com/google/gwt/user/client/DOM.html#compare(com.google.gwt.user.client.Element, com.google.gwt.user.client.Element)
				if (target != scrollerElement) {
					return;
				}

			}
		}

		unbindTransistionEnd();

		startAnimation();

	}

	private void startAnimation() {
		if (this.animating)
			return;

		System.out.println("start animating");
		
		final int startX = this.x;
		final int startY = this.y;

		if (this.steps.length() == 0) {
			resetPos(400);
			return;
		}

		final Step step = this.steps.shift();

		if (step.getX() == startX && step.getY() == startY) {
			step.setTime(0);
		}

		this.animating = true;
		this.moved = true;

		if (this.useTransistion) {
			setTransistionTime(step.getTime());
			pos(step.getX(), step.getY());
			this.animating = false;
			if (step.getTime() != 0) {
				bindTransistionEndEvent();
			} else {
				resetPos(0);
			}
			return;
		}

		final long startTime = System.currentTimeMillis();

		final AnimationCallback animationCallback = new AnimationCallback() {

			@Override
			public void execute(double now) {
				System.out.println("animation callback");
				if (now >= startTime + step.getTime()) {
					ScrollPanelNewPort.this.pos(step.x, step.y);
					ScrollPanelNewPort.this.animating = false;
					//TODO maybe fire animation end event...
					ScrollPanelNewPort.this.startAnimation();
					return;
				}

				now = (now - startTime) / step.getTime() - 1;
				double easeOut = Math.sqrt(1 - now * now);
				int newX = (int) Math.round((step.getX() - startX) * easeOut + startX);
				int newY = (int) Math.round((step.getY() - startY) * easeOut + startY);
				ScrollPanelNewPort.this.pos(newX, newY);
				if (ScrollPanelNewPort.this.animating)
					ScrollPanelNewPort.this.aniTime = AnimationScheduler.get().requestAnimationFrame(this);

			}
		};

		animationCallback.execute(startTime);

	}

	private void setTransistionTime(int time) {

		CssUtil.setTransitionDuration(scroller.getElement(), time);

		//TODO update scrollbars

	}

	private Momentum momentum(int dist, long time, int maxDistUpper, int maxDistLower, int size) {
		double deceleration = 0.006;
		double speed = ((double) (Math.abs(dist))) / time;
		double newDist = (speed * speed) / (2 * deceleration);
		double newTime = 0;
		double outSideDist = 0;

		// Proportinally reduce speed if we are outside of the boundaries 
		if (dist > 0 && newDist > maxDistUpper) {
			outSideDist = size / (6 / (newDist / speed * deceleration));
			maxDistUpper = (int) (maxDistUpper + outSideDist);
			speed = speed * maxDistUpper / newDist;
			newDist = maxDistUpper;
		} else if (dist < 0 && newDist > maxDistLower) {
			outSideDist = size / (6 / (newDist / speed * deceleration));
			maxDistLower = (int) (maxDistLower + outSideDist);
			speed = speed * maxDistLower / newDist;
			newDist = maxDistLower;
		}

		newDist = newDist * (dist < 0 ? -1 : 1);
		newTime = speed / deceleration;

		return new Momentum((int) Math.round(newDist), (int) Math.round(newTime));
	}

	private int[] offSet(com.google.gwt.dom.client.Element el) {
		int left = -el.getOffsetLeft();
		int top = -el.getOffsetTop();
		//TODO
		com.google.gwt.dom.client.Element domElem = null;
		while (true) {
			domElem = el.getOffsetParent();
			if (domElem == null)
				break;
			left -= domElem.getOffsetLeft();
			top -= domElem.getOffsetTop();
		}

		if (el != this.wrapper.getElement()) {
			left *= this.scale;
			top *= this.scale;
		}

		return new int[] { left, top };
	}

	private Snap snap(int x, int y) {

		// Check page X
		int page = this.pagesX.length() - 1;
		for (int i = 0, l = this.pagesX.length(); i < l; i++) {
			if (x >= this.pagesX.get(i)) {
				page = i;
				break;
			}
		}
		if (page == this.currPageX && page > 0 && this.dirX < 0)
			page--;
		x = this.pagesX.get(page);
		int sizeX = Math.abs(x - this.pagesX.get(this.currPageX));
		sizeX = sizeX != 0 ? Math.abs(this.x - x) / sizeX * 500 : 0;
		this.currPageX = page;

		// Check page Y
		page = this.pagesY.length() - 1;
		for (int i = 0; i < page; i++) {
			if (y >= this.pagesY.get(i)) {
				page = i;
				break;
			}
		}
		if (page == this.currPageY && page > 0 && this.dirY < 0)
			page--;
		y = this.pagesY.get(page);
		int sizeY = Math.abs(y - this.pagesY.get(this.currPageY));
		sizeY = sizeY != 0 ? Math.abs(this.y - y) / sizeY * 500 : 0;
		this.currPageY = page;

		// Snap with constant speed (proportional duration)
		int time = Math.round(Math.max(sizeX, sizeY));
		if (time == 0)
			time = 200;

		return new Snap(x, y, time);

	}

	private void bindTransistionEndEvent() {
		if (CssUtil.hasTransistionEndEvent()) {
			transistionEndRegistration = scroller.addDomHandler(new TransitionEndHandler() {

				@Override
				public void onTransitionEnd(TransitionEndEvent event) {
					onTransistionEnd(event);

				}
			}, TransitionEndEvent.getType());
		}

	}

	private void unbindTransistionEnd() {
		if (transistionEndRegistration != null) {
			transistionEndRegistration.removeHandler();
			transistionEndRegistration = null;
		}

	}

	@Override
	public void refresh() {

		if (scale < zoomMin) {
			scale = zoomMin;
		}
		wrapperHeight = getClientHeight(wrapper.getElement());
		if (wrapperHeight == 0) {
			wrapperHeight = 1;
		}
		wrapperWidth = getClientWidth(wrapper.getElement());
		if (wrapperWidth == 0) {
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

		int[] offSet = offSet(this.wrapper.getElement());

		wrapperOffsetLeft = -offSet[0];
		wrapperOffsetTop = -offSet[1];

		//prep stuff
		if (this.snapSelector != null) {
			this.pagesX = CollectionFactory.constructIntegerArray();
			this.pagesY = CollectionFactory.constructIntegerArray();

			JsArray<com.google.gwt.dom.client.Element> elements = querySelectorAll(this.scroller.getElement(), snapSelector);

			for (int i = 0; i < elements.length(); i++) {
				int[] pos = offSet(elements.get(i));
				int left = pos[0] + this.wrapperOffsetLeft;
				int top = pos[1] + this.wrapperOffsetTop;
				this.pagesX.push((int) (left < this.maxScrollX ? this.maxScrollX : left * this.scale));
				this.pagesY.push((int) (top < this.maxScrollY ? this.maxScrollY : top * this.scale));
			}
		} else {
			if (this.snap) {
				int pos = 0;
				int page = 0;
				this.pagesX = CollectionFactory.constructIntegerArray();
				while (pos >= this.maxScrollX) {
					this.pagesX.set(page, pos);
					pos = pos - this.wrapperWidth;
					page++;
				}
				if (this.maxScrollX % this.wrapperWidth != 0)
					this.pagesX.set(this.pagesX.length(), this.maxScrollX - this.pagesX.get(this.pagesX.length() - 1) + this.pagesX.get(this.pagesX.length() - 1));

				pos = 0;
				page = 0;
				this.pagesY = CollectionFactory.constructIntegerArray();
				while (pos >= this.maxScrollY) {
					this.pagesY.set(page, pos);
					pos = pos - this.wrapperHeight;
					page++;
				}
				if (this.maxScrollY % this.wrapperHeight != 0)
					this.pagesY.set(this.pagesY.length(), this.maxScrollY - this.pagesY.get(this.pagesY.length() - 1) + this.pagesY.get(this.pagesY.length() - 1));
			}
		}

		//TODO
		//prep scrollbars

		if (!this.zoomed) {
			CssUtil.setTransitionDuration(this.scroller.getElement(), 0);
			resetPos(200);
		}

	}

	@Override
	public void scrollTo(int x, int y, int time) {
		scrollTo(x, y, time, false);

	}

	public void scrollTo(int x, int y, int time, boolean relative) {
		stop();

		int destX;
		int destY;

		if (relative) {
			destX = this.x - x;
			destY = this.y - y;
		} else {
			destX = x;
			destY = y;
		}

		this.steps.push(new Step(destX, destY, time));

		startAnimation();

	}

	public void scrollToElement(com.google.gwt.dom.client.Element el, int time) {

		int[] offSet = offSet(el);
		int left = offSet[0] + this.wrapperOffsetLeft;
		int top = offSet[1] + this.wrapperOffsetTop;

		left = left > 0 ? 0 : left < this.maxScrollX ? this.maxScrollX : left;
		top = top > this.minScrollY ? this.minScrollY : top < this.maxScrollY ? this.maxScrollY : top;

		scrollTo(left, top, time);
	}

	public void scrollToPage(int pageX, int pageY) {
		scrollToPage(pageX, pageY, 400);
	}

	public void scrollToPage(int pageX, int pageY, int time) {
		//TODO
		//fire on scroll start
		int x, y;
		if (this.snap) {

			pageX = pageX < 0 ? 0 : pageX > this.pagesX.length() - 1 ? this.pagesX.length() - 1 : pageX;
			pageY = pageY < 0 ? 0 : pageY > this.pagesY.length() - 1 ? this.pagesY.length() - 1 : pageY;

			this.currPageX = pageX;
			this.currPageY = pageY;
			x = this.pagesX.get(pageX);
			y = this.pagesY.get(pageY);
		} else {
			x = -this.wrapperWidth * pageX;
			y = -this.wrapperHeight * pageY;
			if (x < this.maxScrollX)
				x = this.maxScrollX;
			if (y < this.maxScrollY)
				y = this.maxScrollY;
		}

		scrollTo(x, y, time);

	}

	public void disable() {
		stop();
		resetPos(0);
		this.enabled = false;

		unbindMoveEvent();
		unbindEndEvent();
		unbindCancelEvent();
	}

	public void enable() {
		this.enabled = true;
	}

	public void stop() {
		if (this.useTransistion) {
			unbindTransistionEnd();
		} else {
			if (this.aniTime != null)
				this.aniTime.cancel();
		}

		this.steps = CollectionFactory.constructArray();
		this.moved = false;
		this.animating = false;
	}

	public void zoom(int x, int y, double scale, int time) {

		if (!this.useTransform)
			return;

		double relScale = scale / this.scale;

		this.zoomed = true;

		x = x - this.wrapperOffsetLeft - this.x;
		y = y - this.wrapperOffsetTop - this.y;

		this.x = (int) Math.round(x - x * relScale + this.x);
		this.y = (int) Math.round(y - y * relScale + this.y);

		this.scale = scale;
		this.refresh();

		this.x = this.x > 0 ? 0 : this.x < this.maxScrollX ? this.maxScrollX : this.x;
		this.y = this.y > this.minScrollY ? this.minScrollY : this.y < this.maxScrollY ? this.maxScrollY : this.y;

		//TODO scroller zoom style!
		//	that.scroller.style[vendor + 'TransitionDuration'] = time + 'ms';
		//	that.scroller.style[vendor + 'Transform'] = trnOpen + that.x + 'px,' + that.y + 'px' + trnClose + ' scale(' + scale + ')';

		this.zoomed = true;

	}

	public boolean isReady() {
		return !this.moved && !this.zoomed && !this.animating;
	}

	/*
	 * Helpers!
	 */

	//TODO move in util
	private native int getClientHeight(Element element)/*-{
		return element.clientHeight || 0;
	}-*/;

	private native int getClientWidth(Element element) /*-{
		return element.clientWidth || 0;
	}-*/;

	private native JsArray<com.google.gwt.dom.client.Element> querySelectorAll(Element el, String selector)/*-{
		return el.querySelectorAll(selector);
	}-*/;

	@Override
	public void add(Widget w) {
		if (scroller != null) {
			throw new IllegalStateException("scrollpanel can only have one child");
		}
		setWidget(w);

	}

	/*
	 * GWT stuff
	 *  
	 */

	@Override
	public void clear() {
		setWidget(null);

	}

	@Override
	public Iterator<Widget> iterator() {
		return wrapper.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		if (w == scroller) {
			scroller = null;
			return wrapper.remove(w);
		}
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
		//TODO

	}

	@Override
	public void setUsePos(boolean pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isScrollingEnabledX() {
		return hScroll;
	}

	@Override
	public void setScrollingEnabledX(boolean scrollingEnabledX) {
		this.hScroll = scrollingEnabledX;

	}

	@Override
	public boolean isScrollingEnabledY() {
		return this.vScroll;
	}

	@Override
	public void setScrollingEnabledY(boolean scrollingEnabledY) {
		this.vScroll = scrollingEnabledY;

	}

	@Override
	public void setOffset(int offsetX, int offsetY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWidget(IsWidget child) {
		setWidget(child.asWidget() != null ? child.asWidget() : null);

	}

	private HandlerRegistration touchStartRegistration;
	private HandlerRegistration orientationChangeRegistration;
	private TouchDelegate touchDelegate;
	private HandlerRegistration mouseOutRegistration;
	private HandlerRegistration mouseWheelRegistration;
	private HandlerRegistration touchCancelRegistration;
	private HandlerRegistration touchEndRegistration;
	private HandlerRegistration touchMoveRegistration;

	public void setWidget(Widget w) {

		//clear old event handlers
		unbindStartEvent();
		unbindResizeEvent();
		if (MGWT.getOsDetection().isDesktop()) {
			unbindMouseoutEvent();
			unbindMouseWheelEvent();
		}

		if (scroller != null) {
			//TODO clean up

			remove(scroller);
			touchDelegate = null;
		}

		scroller = w;

		if (scroller != null) {
			wrapper.setWidget(scroller);
			touchDelegate = new TouchDelegate(scroller);

			if (isAttached()) {
				bindResizeEvent();
				bindStartEvent();
				if (MGWT.getOsDetection().isDesktop()) {
					bindMouseoutEvent();
					bindMouseWheelEvent();
				}
			}

			updateDefaultStyles();
		}

	}

	@Override
	protected void onAttach() {
		super.onAttach();

		if (scroller != null) {
			//bind events
			bindResizeEvent();
			bindStartEvent();
			if (MGWT.getOsDetection().isDesktop()) {
				bindMouseoutEvent();
				bindMouseWheelEvent();
			}
		}

	}

	private void bindMouseWheelEvent() {
		mouseWheelRegistration = scroller.addDomHandler(new MouseWheelHandler() {

			@Override
			public void onMouseWheel(MouseWheelEvent event) {
				//TODO
				//wheel(wheelDeltaX, wheelDeltaY, pageX, pageY)

			}
		}, MouseWheelEvent.getType());

	}

	private void unbindMouseWheelEvent() {
		if (mouseWheelRegistration != null) {
			mouseWheelRegistration.removeHandler();
			mouseWheelRegistration = null;
		}

	}

	private void bindMouseoutEvent() {
		mouseOutRegistration = this.wrapper.addDomHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				mouseOut(event);

			}
		}, MouseOutEvent.getType());

	}

	private void unbindMouseoutEvent() {
		if (mouseOutRegistration != null) {
			mouseOutRegistration.removeHandler();
			mouseOutRegistration = null;
		}

	}

	private void bindStartEvent() {
		touchStartRegistration = touchDelegate.addTouchStartHandler(touchListener);

	}

	private void unbindStartEvent() {
		if (touchStartRegistration != null) {
			touchStartRegistration.removeHandler();
			touchStartRegistration = null;
		}

	}

	private void bindCancelEvent() {
		touchCancelRegistration = touchDelegate.addTouchCancelHandler(touchListener);

	}

	private void bindEndEvent() {
		System.out.println("bind touch end");
		touchEndRegistration = touchDelegate.addTouchEndHandler(touchListener);

	}

	private void bindMoveEvent() {
		logger.info("bind move event");
		System.out.println("bind move event");
		touchMoveRegistration = touchDelegate.addTouchMoveHandler(touchListener);

	}

	private void unbindCancelEvent() {
		if (touchCancelRegistration != null) {
			touchCancelRegistration.removeHandler();
			touchCancelRegistration = null;
		}

	}

	private void unbindEndEvent() {
		System.out.println("unbind touch end");
		if (touchEndRegistration != null) {
			touchEndRegistration.removeHandler();
			touchEndRegistration = null;
		}
	}

	private void unbindMoveEvent() {
		System.out.println("unbind move event");
		if (touchMoveRegistration != null) {
			logger.info("unbind move event");
			
			touchMoveRegistration.removeHandler();
			touchMoveRegistration = null;
		}
	}

	/**
	 * 
	 */
	private void bindResizeEvent() {
		orientationChangeRegistration = MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

			@Override
			public void onOrientationChanged(OrientationChangeEvent event) {
				resize();
			}
		});
	}

	private void unbindResizeEvent() {
		if (orientationChangeRegistration != null) {
			orientationChangeRegistration.removeHandler();
			orientationChangeRegistration = null;
		}
	}

	private void updateDefaultStyles() {
		if (scroller != null) {

			CssUtil.setTransistionProperty(scroller.getElement(), useTransform ? CssUtil.getTransformProperty() : "top left");
			CssUtil.setTransitionDuration(scroller.getElement(), 0);
			CssUtil.setTransFormOrigin(scroller.getElement(), 0, 0);
			if (useTransistion) {
				//TODO
				CssUtil.setTransistionTimingFunction(scroller.getElement(), "cubic-bezier(0.33,0.66,0.66,1)");
			}
			if (useTransform) {
				CssUtil.translate(scroller.getElement(), this.x, this.y);
			} else {
				scroller.getElement().getStyle().setPosition(Position.ABSOLUTE);
				scroller.getElement().getStyle().setLeft(this.x, Unit.PX);
				scroller.getElement().getStyle().setTop(this.y, Unit.PX);

			}

			if (useTransistion) {
				//TODO fixed scrollbars!
			}

		}

	}

	private void cancelAnimationFrame() {
		if (aniTime != null) {
			aniTime.cancel();
			aniTime = null;
		}

	}

}
