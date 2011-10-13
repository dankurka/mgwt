/*
 * Copyright 2010 Daniel Kurka
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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.panel.Scrollbar;
import com.googlecode.mgwt.ui.client.panel.Scrollbar.Orientation;
import com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.util.FeatureDetection;
import com.googlecode.mgwt.ui.client.widget.event.HasScrollHandlers;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEndHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollEvent;
import com.googlecode.mgwt.ui.client.widget.event.ScrollHandler;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartEvent;
import com.googlecode.mgwt.ui.client.widget.event.ScrollStartHandler;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class ScrollPanel extends Composite implements HasOneWidget, HasWidgets, HasScrollHandlers {

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
	private int directionX = 0;
	private int directionY = 0;

	//private boolean experimental = true;

	private boolean usePos = false;

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

	public ScrollPanel() {
		this(MGWTStyle.getDefaultClientBundle().getScrollPanelCss());
	}

	public ScrollPanel(ScrollPanelCss css) {
		this.css = css;
		css.ensureInjected();
		main = new TouchPanel();
		main.addStyleName(css.scrollPanel());

		initWidget(main);

		touchObserver = new TouchObserver();

		has3d = FeatureDetection.has3d();

	}

	private void updateScrollBars() {
		if (hScrollbar != null) {
			main.remove(hScrollbar);
		}

		if (vScrollbar != null) {
			main.remove(vScrollbar);
		}

		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

			@Override
			public void execute() {
				if (scrollingEnabledX && widgetToScroll.getOffsetWidth() > 0) {
					hScrollbar = new Scrollbar(css, Orientation.HORIZONTAL, has3d, main.getOffsetWidth(), widgetToScroll.getOffsetWidth());
					if (main.getOffsetWidth() < widgetToScroll.getOffsetWidth())
						main.add(hScrollbar);
				}

				if (scrollingEnabledY && widgetToScroll.getOffsetHeight() > 0) {
					vScrollbar = new Scrollbar(css, Orientation.VERTICAL, has3d, main.getOffsetHeight(), widgetToScroll.getOffsetHeight());
					if (main.getOffsetHeight() < widgetToScroll.getOffsetHeight())
						main.add(vScrollbar);
				}

			}
		});

	}

	/**
	 * for uibinder...
	 * 
	 * @param w
	 */
	@Override
	public void add(Widget w) {
		if (widgetToScroll != null) {
			throw new IllegalStateException("scrollpanel can only have one child");
		}
		setWidget(w);
	}

	@Override
	public void setWidget(IsWidget child) {

		setWidget(child.asWidget());

	}

	@Override
	public Widget getWidget() {
		return widgetToScroll;
	}

	public void refresh() {
		updateScrollBars();
	}

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

	private class TransistionEndListener implements TransitionEndHandler {

		@Override
		public void onTransitionEnd(TransitionEndEvent event) {
			ScrollPanel.this.onTransistionEnd();
		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();

		touchRegistration = main.addTouchHandler(touchObserver);

		if (widgetToScroll != null) {

			updateScrollBars();
			transEndHandler = widgetToScroll.addDomHandler(new TransistionEndListener(), TransitionEndEvent.getType());

		}
	}

	@Override
	protected void onDetach() {
		super.onDetach();

		touchRegistration.removeHandler();
		if (transEndHandler != null) {
			transEndHandler.removeHandler();
			transEndHandler = null;
		}

	}

	private class TouchObserver implements TouchHandler {

		@Override
		public void onTouchStart(TouchStartEvent event) {
			if (widgetToScroll == null)
				return;

			EventTarget eventTarget = event.getNativeEvent().getEventTarget();
			if (eventTarget != null) {
				//no textnode or element node
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

					}
				}
			}

			event.preventDefault();
			event.stopPropagation();
			currentlyScrolling = true;
			//event.stopPropagation();

			moved = false;
			distX = 0;
			distY = 0;

			setTransistionTime(0);

			Touch touch = event.touches().get(0);
			touchStartX = touch.getPageX();
			touchStartY = touch.getPageY();

			scrollStartX = position_x;
			scrollStartY = position_y;

			touchStartTime = System.currentTimeMillis();

			directionX = 0;
			directionY = 0;

			//TODO...
			fireEvent(new ScrollStartEvent(0, 0));

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
			if (widgetToScroll == null)
				return;
			if (!currentlyScrolling)
				return;

			event.preventDefault();
			event.stopPropagation();

			Touch touch = event.touches().get(0);

			//calculate delta
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

			//event.stopPropagation();

			touchStartX = touch.getPageX();
			touchStartY = touch.getPageY();

			if (distX + distY < SCROLL_THRESHOLD) {
				distX = Math.abs(leftDelta);
				distY = Math.abs(topDelta);
			} else {
				//lock scroll
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
				//fire scroll event to world
				fireEvent(new ScrollEvent(newPosX, newPosY));
				moved = true;

				if (leftDelta > 0) {
					directionX = -1;
				} else {
					directionX = 1;
				}

				if (topDelta > 0) {
					directionY = -1;
				} else {
					directionY = 1;
				}

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
				resetPosition();
				return;
			}

			long scrollTime = System.currentTimeMillis() - touchStartTime;

			int newPosX = position_x;
			int newPosY = position_y;

			int newDuration = 1;

			long touchTime = touchStartTime - System.currentTimeMillis();

			if (touchTime < 300 && momentum) {
				if (scrollingEnabledX) {
					Momentum m = calculateMomentum(position_x - scrollStartX, scrollTime, -position_x, position_x + widgetToScroll.getOffsetWidth() - main.getOffsetWidth());
					newDuration = Math.max(m.getTime(), newDuration);
					newPosX = position_x + m.getDist();
				}

				if (scrollingEnabledY) {
					Momentum m = calculateMomentum(position_y - scrollStartY, scrollTime, -position_y, position_y + widgetToScroll.getOffsetHeight() - main.getOffsetHeight());
					newDuration = Math.max(m.getTime(), newDuration);
					newPosY = position_y + m.getDist();

				}
			}

			ScrollEndEvent scrollEndEvent = new ScrollEndEvent(newPosX, newPosY, newDuration, position_x, position_y);
			fireEvent(scrollEndEvent);
			if (!scrollEndEvent.isPreventDefault()) {
				scrollTo(newPosX, newPosY, newDuration);
			}
		}

		@Override
		public void onTouchCanceled(TouchCancelEvent event) {
			currentlyScrolling = false;
		}

	}

	/**
	 * @param newPosX
	 * @param newPosY
	 */
	public void setPosition(int newPosX, int newPosY) {
		position_x = newPosX;
		position_y = newPosY;

		if (usePos) {
			widgetToScroll.getElement().getStyle().setPosition(Position.RELATIVE);
			widgetToScroll.getElement().getStyle().setTop(newPosY, Unit.PX);
			widgetToScroll.getElement().getStyle().setLeft(newPosX, Unit.PX);

			new Timer() {

				@Override
				public void run() {
					onTransistionEnd();

				}
			}.schedule(1);

		} else {
			CssUtil.translate(widgetToScroll.getElement(), newPosX, newPosY);
		}

		if (scrollingEnabledX && hScrollbar != null) {
			hScrollbar.setPosition(newPosX);
		}
		if (scrollingEnabledY && vScrollbar != null) {
			vScrollbar.setPosition(newPosY);
		}

	}

	private void setTransistionTime(int milliseconds) {

		if (!usePos)
			widgetToScroll.getElement().getStyle().setProperty("webkitTransitionDuration", milliseconds + "ms");
		if (scrollingEnabledX && hScrollbar != null) {
			hScrollbar.setTransitionTime(milliseconds);
		}
		if (scrollingEnabledY && vScrollbar != null) {
			vScrollbar.setTransitionTime(milliseconds);
		}

	}

	public void setUsePos(boolean pos) {
		this.usePos = pos;
	}

	/**
	 * @param newPosX
	 * @param newPosY
	 * @param newDuration
	 */
	public void scrollTo(int destX, int destY, int newDuration) {

		if (position_x == destX && position_y == destY) {
			resetPosition();
			return;
		}

		moved = true;

		setTransistionTime(newDuration);
		setPosition(destX, destY);

		if (newDuration == 0) {
			resetPosition();

		} else {
			listenForTransitionEnd = true;

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
	private int getMaxScrollY() {
		return main.getOffsetHeight() - widgetToScroll.getOffsetHeight() + offsetY;

	}

	/**
	 * 
	 */
	private int getMaxScrollX() {

		return main.getOffsetWidth() - widgetToScroll.getOffsetWidth() - offsetX;

	}

	private class Momentum {
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

	public boolean isScrollingEnabledX() {
		return scrollingEnabledX;
	}

	public void setScrollingEnabledX(boolean scrollingEnabledX) {
		this.scrollingEnabledX = scrollingEnabledX;
	}

	public boolean isScrollingEnabledY() {
		return scrollingEnabledY;
	}

	public void setScrollingEnabledY(boolean scrollingEnabledY) {
		this.scrollingEnabledY = scrollingEnabledY;
	}

	@Override
	public HandlerRegistration addScrollhandler(ScrollHandler scrollHandler) {
		return addHandler(scrollHandler, ScrollEvent.getType());
	}

	@Override
	public void clear() {
		widgetToScroll = null;
		main.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return main.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		if (w == widgetToScroll) {
			widgetToScroll = null;
			return main.remove(w);
		}
		return false;
	}

	@Override
	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler) {
		return addHandler(handler, ScrollEndEvent.getType());
	}

	@Override
	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler) {
		return addHandler(handler, ScrollStartEvent.getType());
	}

	public void setOffset(int offsetX, int offsetY) {
		this.offsetY = offsetY;
		this.offsetX = offsetX;

		scrollTo(position_x, position_y, 200);

	}

}
