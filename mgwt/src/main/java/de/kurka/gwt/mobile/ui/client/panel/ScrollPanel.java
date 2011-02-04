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
package de.kurka.gwt.mobile.ui.client.panel;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.animation.TransitionEndEvent;
import de.kurka.gwt.mobile.dom.client.event.animation.TransitionEndHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.Touch;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchCancelEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchEndEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchHandler;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchMoveEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.TouchStartEvent;
import de.kurka.gwt.mobile.ui.client.panel.Scrollbar.Orientation;
import de.kurka.gwt.mobile.ui.client.util.CssUtil;
import de.kurka.gwt.mobile.ui.client.util.FeatureDetection;
import de.kurka.gwt.mobile.ui.client.widget.TouchPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class ScrollPanel extends Composite implements HasOneWidget {

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

	public ScrollPanel() {
		main = new TouchPanel();
		main.setStylePrimaryName("mgwt-ScrollPanel");

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
				if (scrollingEnabledX) {
					hScrollbar = new Scrollbar(Orientation.HORIZONTAL, has3d, main.getOffsetWidth(), widgetToScroll.getOffsetWidth());
					main.add(hScrollbar);
				}

				if (scrollingEnabledY) {
					vScrollbar = new Scrollbar(Orientation.VERTICAL, has3d, main.getOffsetHeight(), widgetToScroll.getOffsetHeight());
					main.add(vScrollbar);
				}

			}
		});

	}

	@Override
	public void setWidget(IsWidget child) {

		setWidget(child.asWidget());

	}

	@Override
	public Widget getWidget() {
		return widgetToScroll;
	}

	@Override
	public void setWidget(Widget w) {
		if (widgetToScroll != null) {
			if (isAttached()) {
				transEndHandler.removeHandler();

			}
			main.remove(widgetToScroll);
		}
		main.add(w);
		widgetToScroll = w;

		if (isAttached()) {
			transEndHandler = widgetToScroll.addDomHandler(new TransistionEndListener(), TransitionEndEvent.getType());
			updateScrollBars();
		}

		widgetToScroll.getElement().getStyle().setProperty("webkitTransitionProperty", "-webkit-transform");
		widgetToScroll.getElement().getStyle().setProperty("webkitTransitionTimingFunction", "cubic-bezier(0,0,0.25,1)");

	}

	private void onTransistionEnd() {
		System.out.println("trans end");

		if (listenForTransitionEnd) {
			System.out.println("listenering");
			listenForTransitionEnd = false;
			resetPosition();
		} else {
			System.out.println("not listening");
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
		transEndHandler.removeHandler();

	}

	private class TouchObserver implements TouchHandler {

		@Override
		public void onTouchStart(TouchStartEvent event) {

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

			System.out.println("touchstartX : " + touchStartX + " touchstartY: " + touchStartY);

		}

		@Override
		public void onTouchMove(TouchMoveEvent event) {
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
			System.out.println("touchend");
			if (!currentlyScrolling)
				return;

			event.preventDefault();
			event.stopPropagation();

			currentlyScrolling = false;

			if (!moved) {
				resetPosition();
				return;
			}

			System.out.println("touchsendX : " + position_x + " touchendY: " + position_y);

			long scrollTime = System.currentTimeMillis() - touchStartTime;

			int newPosX = position_x;
			int newPosY = position_y;

			int newDuration = 1;

			if (momentum) {
				if (scrollingEnabledX) {

					Momentum m = calculateMomentum(position_x - scrollStartX, scrollTime, -position_x, position_x + widgetToScroll.getOffsetWidth() - main.getOffsetWidth());
					newDuration = Math.max(m.getTime(), newDuration);
					newPosX = position_x + m.getDist();
					System.out.println("newDur: " + newDuration);
				}

				if (scrollingEnabledY) {
					Momentum m = calculateMomentum(position_y - scrollStartY, scrollTime, -position_y, position_y + widgetToScroll.getOffsetHeight() - main.getOffsetHeight());
					newDuration = Math.max(m.getTime(), newDuration);
					System.out.println("newDur: " + newDuration);
					newPosY = position_y + m.getDist();

				}
			}

			scrollTo(newPosX, newPosY, newDuration);
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

		CssUtil.translate(widgetToScroll.getElement(), newPosX, newPosY);

		if (scrollingEnabledX) {
			hScrollbar.setPosition(newPosX);
		}
		if (scrollingEnabledY) {
			vScrollbar.setPosition(newPosY);
		}

	}

	private void setTransistionTime(int milliseconds) {
		System.out.println("webkit transition duration: " + milliseconds);
		widgetToScroll.getElement().getStyle().setProperty("webkitTransitionDuration", milliseconds + "ms");
		if (scrollingEnabledX) {
			hScrollbar.setTransitionTime(milliseconds);
		}
		if (scrollingEnabledY) {
			vScrollbar.setTransitionTime(milliseconds);
		}

	}

	/**
	 * @param newPosX
	 * @param newPosY
	 * @param newDuration
	 */
	public void scrollTo(int destX, int destY, int newDuration) {
		System.out.println("destX: " + destX + " destY: " + destY + " dur: " + newDuration);
		if (position_x == destX && position_y == destY) {
			resetPosition();
			System.out.println("returning");
			return;
		}

		moved = true;

		setTransistionTime(newDuration);
		setPosition(destX, destY);

		if (newDuration == 0) {
			System.out.println("no duration");
			resetPosition();

		} else {
			System.out.println("transistend needs to be listened to!!!");
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
			System.out.println("maxScroll: " + maxScrollX);
			if (position_x < maxScrollX) {
				resetX = maxScrollX;
			}
		}

		int maxScrollY = getMaxScrollY();
		if (position_y >= 0 || maxScrollY > 0) {
			resetY = 0;
		} else if (position_y < maxScrollY) {
			resetY = maxScrollY;
		}

		if (resetX != position_x || resetY != position_y) {
			System.out.println("need to scroll");
			scrollTo(resetX, resetY, 300);
		} else {
			if (scrollingEnabledX) {
				hScrollbar.hide();
			}
			if (scrollingEnabledY) {
				vScrollbar.hide();
			}

		}

	}

	/**
	 * 
	 */
	private int getMaxScrollY() {
		return main.getOffsetHeight() - widgetToScroll.getOffsetHeight();

	}

	/**
	 * 
	 */
	private int getMaxScrollX() {

		return main.getOffsetWidth() - widgetToScroll.getOffsetWidth();

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

		System.out.println("calculate Momentum: dist: " + dist + " time: " + time + " upper: " + maxDistUpper + " lower: " + maxDistLower);

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

		System.out.println("momentum: " + newDist + " time: " + newTime);

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

}
