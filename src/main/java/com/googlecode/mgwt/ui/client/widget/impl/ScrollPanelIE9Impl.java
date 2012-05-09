package com.googlecode.mgwt.ui.client.widget.impl;

import java.util.Iterator;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.util.FeatureDetection;
import com.googlecode.mgwt.ui.client.widget.base.Scrollbar;
import com.googlecode.mgwt.ui.client.widget.base.Scrollbar.Orientation;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

public class ScrollPanelIE9Impl extends ScrollPanelImpl {

	private Widget widgetToScroll;

	private TouchPanel main;

	private int position_x = 0;
	private int position_y = 0;

	private int distX;
	private int distY;
	private boolean moved = false;
	private int touchStartX;
	private int touchStartY;

	private int scrollStartX;
	private int scrollStartY;

	private long touchStartTime;
	// private int directionX = 0;
	// private int directionY = 0;

	// private boolean experimental = true;

	// private boolean usePos = false;

	private TouchObserver touchObserver;

	private HandlerRegistration touchRegistration;

	private boolean currentlyScrolling = false;

	private boolean scrollingEnabledX = true;
	private boolean scrollingEnabledY = true;
	private boolean momentum = true;

	private boolean listenForTransitionEnd = false;

	private static final int SCROLL_THRESHOLD = 5;

	private static final int SCROLL_LOCK_THRESHOLD = 3;

	private boolean has3d;

	private Scrollbar hScrollbar;

	private Scrollbar vScrollbar;

	private HandlerRegistration transEndHandler;

	protected final ScrollPanelCss css;

	private int offsetY;

	private int offsetX;

	private HandlerRegistration mouseWheelHandlerRegistration;

	private HandlerRegistration orientationHandlerRegistration;

	/**
	 * <p>
	 * Constructor for ScrollPanelTouchImpl.
	 * </p>
	 */
	public ScrollPanelIE9Impl() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getScrollPanelCss());
	}

	/**
	 * <p>
	 * Constructor for ScrollPanelTouchImpl.
	 * </p>
	 * 
	 * @param css a
	 *            {@link com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss}
	 *            object.
	 */
	public ScrollPanelIE9Impl(ScrollPanelCss css) {
		this.css = css;
		css.ensureInjected();
		main = new TouchPanel();
		main.addStyleName(css.scrollPanel());

		initWidget(main);

		touchObserver = new TouchObserver();

		has3d = FeatureDetection.has3d();

	}

	private void updateScrollBars() {

		if (!isAttached()) {
			return;
		}
		if (widgetToScroll == null) {
			return;
		}
		if (hScrollbar != null) {
			main.remove(hScrollbar);
		}

		if (vScrollbar != null) {
			main.remove(vScrollbar);
		}

		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

			@Override
			public void execute() {

				if (scrollingEnabledX && getWidgetToScrollWidth() > 0) {
					hScrollbar = new Scrollbar(css, Orientation.HORIZONTAL, has3d, main.getOffsetWidth(), getWidgetToScrollWidth());
					if (getClientWidth(main.getElement()) < getWidgetToScrollWidth())
						main.add(hScrollbar);
				}

				if (scrollingEnabledY && getWidgetToScrollHeight() > 0) {
					vScrollbar = new Scrollbar(css, Orientation.VERTICAL, has3d, main.getOffsetHeight(), getWidgetToScrollHeight());
					if (getClientHeight(main.getElement()) < getWidgetToScrollHeight())
						main.add(vScrollbar);
				}

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public void add(Widget w) {
		if (widgetToScroll != null) {
			throw new IllegalStateException("scrollpanel can only have one child");
		}
		setWidget(w);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setWidget(com.google.gwt.user.client.ui.IsWidget)
	 */
	/** {@inheritDoc} */
	@Override
	public void setWidget(IsWidget child) {
		setWidget(child.asWidget());
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#getWidget()
	 */
	/** {@inheritDoc} */
	@Override
	public Widget getWidget() {
		return widgetToScroll;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#refresh()
	 */
	/**
	 * <p>
	 * refresh
	 * </p>
	 */
	public void refresh() {
		updateScrollBars();
		if (widgetToScroll == null) {
			return;
		}
		resetPosition();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#setWidget(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public void setWidget(Widget w) {
		if (widgetToScroll != null) {
			if (isAttached()) {
				if (transEndHandler != null) {
					transEndHandler.removeHandler();
					transEndHandler = null;
				}

			}
			widgetToScroll.removeStyleName(css.container());
			main.remove(widgetToScroll);
		}
		widgetToScroll = w;
		if (w != null) {
			main.add(w);

			if (isAttached()) {
				transEndHandler = widgetToScroll.addDomHandler(new TransistionEndListener(), TransitionEndEvent.getType());
				updateScrollBars();

				mouseWheelHandlerRegistration = main.addDomHandler(new MouseWheelHandlerImplementation(), MouseWheelEvent.getType());

			}
			widgetToScroll.addStyleName(css.container());

		}

	}

	private void onTransistionEnd() {
		if (listenForTransitionEnd) {
			listenForTransitionEnd = false;
			resetPosition();
		}

	}

	private final class MouseWheelHandlerImplementation implements MouseWheelHandler {
		@Override
		public void onMouseWheel(MouseWheelEvent event) {
			int velocityX = 0;
			int velocityY = 0;

			if (isScrollingEnabledX()) {
				velocityX = getMouseWheelVelocityX(event.getNativeEvent()) / 10;
			}

			if (isScrollingEnabledY()) {
				velocityY = getMouseWheelVelocityY(event.getNativeEvent()) / 10;
			}

			scrollTo(position_x + velocityX, position_y + velocityY, 100);

		}
	}

	private class TransistionEndListener implements TransitionEndHandler {

		@Override
		public void onTransitionEnd(TransitionEndEvent event) {
			ScrollPanelIE9Impl.this.onTransistionEnd();
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void onAttach() {
		super.onAttach();

		orientationHandlerRegistration = MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

			@Override
			public void onOrientationChanged(OrientationChangeEvent event) {
				refresh();

			}
		});

		touchRegistration = main.addTouchHandler(touchObserver);

		if (widgetToScroll != null) {

			updateScrollBars();
			// transEndHandler = widgetToScroll.addDomHandler(new
			// TransistionEndListener(), TransitionEndEvent.getType());

			mouseWheelHandlerRegistration = main.addDomHandler(new MouseWheelHandlerImplementation(), MouseWheelEvent.getType());

		}
		refresh();
	}

	private native int getMouseWheelVelocityX(NativeEvent evt)/*-{
		return Math.round(-evt.wheelDeltaX) || 0;
	}-*/;

	private native int getMouseWheelVelocityY(NativeEvent evt)/*-{

		var val = (evt.detail * 40) || -evt.wheelDeltaY || 0;
		return Math.round(val);
	}-*/;

	/** {@inheritDoc} */
	@Override
	protected void onDetach() {
		super.onDetach();

		touchRegistration.removeHandler();
		if (transEndHandler != null) {
			transEndHandler.removeHandler();
			transEndHandler = null;
		}

		if (mouseWheelHandlerRegistration != null) {
			mouseWheelHandlerRegistration.removeHandler();
			mouseWheelHandlerRegistration = null;
		}

		if (orientationHandlerRegistration != null) {
			orientationHandlerRegistration.removeHandler();
			orientationHandlerRegistration = null;
		}

	}

	private class TouchObserver implements TouchHandler {

		@Override
		public void onTouchStart(TouchStartEvent event) {
			if (widgetToScroll == null)
				return;

			EventTarget eventTarget = event.getNativeEvent().getEventTarget();
			if (eventTarget != null) {
				// no textnode or element node
				if (Node.is(eventTarget)) {
					if (Element.is(eventTarget)) {
						Element target = eventTarget.cast();

						String nodeName = target.getNodeName();

						if ("INPUT".equals(nodeName)) {
							return;
						}

						if ("TEXTAREA".equals(nodeName)) {
							return;
						}

						if ("SELECT".equals(nodeName)) {
							return;
						}

						if ("A".equals(nodeName)) {
							return;
						}

					}
				}
			}

			event.preventDefault();
			event.stopPropagation();

			currentlyScrolling = true;
			// event.stopPropagation();

			moved = false;
			distX = 0;
			distY = 0;

			setTransistionTime(0);

			Touch touch = event.getTouches().get(0);
			touchStartX = touch.getPageX();
			touchStartY = touch.getPageY();

			scrollStartX = position_x;
			scrollStartY = position_y;

			touchStartTime = System.currentTimeMillis();

			// fireEvent(new ScrollStartEvent(position_x, position_y));

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			if (widgetToScroll == null)
				return;
			if (!currentlyScrolling)
				return;

			event.preventDefault();
			event.stopPropagation();

			Touch touch = event.getTouches().get(0);

			// calculate delta
			int leftDelta = 0;
			int topDelta = 0;

			if (scrollingEnabledX) {
				leftDelta = touch.getPageX() - touchStartX;
			}

			if (scrollingEnabledY) {
				topDelta = touch.getPageY() - touchStartY;
			}

			int newPosX = position_x + leftDelta;
			int newPosY = position_y + topDelta;

			// event.stopPropagation();

			touchStartX = touch.getPageX();
			touchStartY = touch.getPageY();

			if (distX + distY < SCROLL_THRESHOLD) {
				distX = Math.abs(leftDelta);
				distY = Math.abs(topDelta);
			} else {
				// lock scroll
				if (distX - SCROLL_LOCK_THRESHOLD > distY) {
					newPosY = position_y;
					topDelta = 0;
				} else {
					if (distY - SCROLL_LOCK_THRESHOLD > distX) {
						newPosX = position_x;
						leftDelta = 0;
					}
				}

				setPosition(newPosX, newPosY);
				// fire scroll event to world
				// fireEvent(new ScrollEvent(newPosX, newPosY));
				moved = true;

			}

		}

		@Override
		public void onTouchEnd(TouchEndEvent event) {
			if (widgetToScroll == null)
				return;

			if (!currentlyScrolling)
				return;

			event.preventDefault();
			event.stopPropagation();

			currentlyScrolling = false;

			if (!moved) {
				// ScrollEndEvent scrollEndEvent = new
				// ScrollEndEvent(position_x, position_y, 1, position_x,
				// position_y);
				// fireEvent(scrollEndEvent);
				resetPosition();

				return;
			}

			long scrollTime = System.currentTimeMillis() - touchStartTime;

			int newPosX = position_x;
			int newPosY = position_y;

			int newDuration = 1;

			long touchTime = System.currentTimeMillis() - touchStartTime;

			if (touchTime < 300 && momentum) {

				if (scrollingEnabledX && getWidgetToScrollWidth() > getClientWidth(main.getElement())) {
					Momentum m = calculateMomentum(position_x - scrollStartX, scrollTime, -position_x, position_x + getWidgetToScrollWidth() - getClientWidth(main.getElement()));
					newDuration = Math.max(m.getTime(), newDuration);
					newPosX = position_x + m.getDist();
				}

				if (scrollingEnabledY && getWidgetToScrollHeight() > getClientHeight(main.getElement())) {
					Momentum m = calculateMomentum(position_y - scrollStartY, scrollTime, -position_y, position_y + getWidgetToScrollHeight() - getClientHeight(main.getElement()));
					newDuration = Math.max(m.getTime(), newDuration);
					newPosY = position_y + m.getDist();

				}
			}

			// ScrollEndEvent scrollEndEvent = new ScrollEndEvent(newPosX,
			// newPosY, newDuration, position_x, position_y);
			// fireEvent(scrollEndEvent);

			scrollTo(newPosX, newPosY, newDuration);

		}

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			currentlyScrolling = false;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setPosition(int, int)
	 */
	/** {@inheritDoc} */
	public void setPosition(int newPosX, int newPosY) {
		position_x = newPosX;
		position_y = newPosY;

		widgetToScroll.getElement().getStyle().setPosition(Position.RELATIVE);
		widgetToScroll.getElement().getStyle().setTop(newPosY, Unit.PX);
		widgetToScroll.getElement().getStyle().setLeft(newPosX, Unit.PX);

		new Timer() {

			@Override
			public void run() {
				onTransistionEnd();

			}
		}.schedule(1);

		if (scrollingEnabledX && hScrollbar != null) {
			hScrollbar.setPosition(newPosX);
		}
		if (scrollingEnabledY && vScrollbar != null) {
			vScrollbar.setPosition(newPosY);
		}

	}

	private void setTransistionTime(int milliseconds) {

		if (scrollingEnabledX && hScrollbar != null) {
			hScrollbar.setTransitionTime(milliseconds);
		}
		if (scrollingEnabledY && vScrollbar != null) {
			vScrollbar.setTransitionTime(milliseconds);
		}

	}

	public void setUsePos(boolean pos) {

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#scrollTo(int, int, int)
	 */
	/** {@inheritDoc} */
	public void scrollTo(int destX, int destY, int newDuration) {
		if (position_x == destX && position_y == destY) {
			resetPosition();
			return;
		}

		moved = true;

		if (newDuration == 0) {
			resetPosition();

		} else {
			listenForTransitionEnd = true;
			setTransistionTime(newDuration);
			setPosition(destX, destY);

		}

	}

	private void resetPosition() {
		int resetX = position_x;
		int resetY = position_y;

		if (position_x >= 0) {
			resetX = 0;
		} else {
			int maxScrollX = getMaxScrollX();
			if (position_x < maxScrollX) {
				resetX = maxScrollX;

			}
		}

		int maxScrollY = getMaxScrollY();
		if (position_y >= offsetY || maxScrollY > offsetY) {
			resetY = offsetY;
		} else if (position_y < maxScrollY) {
			resetY = maxScrollY;
		}

		if (resetX != position_x || resetY != position_y) {
			scrollTo(resetX, resetY, 300);
		} else {
			if (scrollingEnabledX && hScrollbar != null) {
				hScrollbar.hide();
			}
			if (scrollingEnabledY && vScrollbar != null) {
				vScrollbar.hide();
			}

		}

	}

	/**
	 * 
	 */
	public int getMaxScrollY() {
		return getClientHeight(main.getElement()) - getWidgetToScrollHeight();

	}

	/**
	 * 
	 */
	private int getMaxScrollX() {
		return getClientWidth(main.getElement()) - getWidgetToScrollWidth() - offsetX;

	}

	private native int getClientWidth(Element el)/*-{
		return el.clientWidth || 1;
	}-*/;

	private native int getClientHeight(Element el)/*-{
		return el.clientHeight || 1;
	}-*/;

	private static class Momentum {
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

	private Momentum calculateMomentum(double dist, double time, int maxDistUpper, double maxDistLower) {

		double friction = 2.5;
		double deceleration = 1.2;
		double speed = (Math.abs(dist) / time) * 1000;

		double calcNewDist = ((speed * speed) / friction) / 1000;

		if (dist > 0 && calcNewDist > maxDistUpper) {
			speed = Math.abs(((speed * maxDistUpper) / calcNewDist) / friction);
			calcNewDist = maxDistUpper;
		} else if (dist < 0 && calcNewDist > maxDistLower) {
			speed = Math.abs(speed * maxDistLower / calcNewDist / friction);
			calcNewDist = maxDistLower;
		}

		if (dist < 0)
			calcNewDist *= -1;

		int newTime = (int) Math.round(speed / deceleration);
		int newDist = (int) Math.round(calcNewDist);

		return new Momentum(newDist, newTime);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#isScrollingEnabledX()
	 */
	/**
	 * <p>
	 * isScrollingEnabledX
	 * </p>
	 * 
	 * @return a boolean.
	 */
	public boolean isScrollingEnabledX() {
		return scrollingEnabledX;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setScrollingEnabledX(boolean)
	 */
	/** {@inheritDoc} */
	public void setScrollingEnabledX(boolean scrollingEnabledX) {
		this.scrollingEnabledX = scrollingEnabledX;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#isScrollingEnabledY()
	 */
	/**
	 * <p>
	 * isScrollingEnabledY
	 * </p>
	 * 
	 * @return a boolean.
	 */
	public boolean isScrollingEnabledY() {
		return scrollingEnabledY;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setScrollingEnabledY(boolean)
	 */
	/** {@inheritDoc} */
	public void setScrollingEnabledY(boolean scrollingEnabledY) {
		this.scrollingEnabledY = scrollingEnabledY;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	/** {@inheritDoc} */
	@Override
	public void clear() {
		widgetToScroll = null;
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
		if (w == widgetToScroll) {
			widgetToScroll = null;
			return main.remove(w);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.impl.ScrollPanelImpl#setOffset(int, int)
	 */
	/** {@inheritDoc} */
	public void setOffset(final int offsetX, final int offsetY) {
		ScrollPanelIE9Impl.this.offsetY = offsetY;
		ScrollPanelIE9Impl.this.offsetX = offsetX;
		//
		// scrollTo(position_x, position_y, 200);

	}

	private int getWidgetToScrollHeight() {
		return widgetToScroll.getOffsetHeight() + getMarginHeight(widgetToScroll.getElement());
	}

	private int getWidgetToScrollWidth() {

		// TODO discuss fix later
		return widgetToScroll.getOffsetWidth();// +
												// getMarginWidth(widgetToScroll.getElement());
	}

	private native int getMarginWidth(com.google.gwt.user.client.Element el)/*-{
		var left = 0;
		var right = 0;
		var style = $wnd.getComputedStyle(el);

		left = parseInt(style.marginLeft, 10) || 0;
		right = parseInt(style.marginRight, 10) || 0;

		return left + right;
	}-*/;

	private native int getMarginHeight(com.google.gwt.user.client.Element el)/*-{

		var top = 0;
		var bottom = 0;
		var style = $wnd.getComputedStyle(el);

		top = parseInt(style.marginTop, 10) || 0;
		bottom = parseInt(style.marginBottom, 10) || 0;

		return top + bottom;
	}-*/;

	@Override
	public void setOffSetY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxScrollY(int y) {
		// TODO Auto-generated method stub

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

}
