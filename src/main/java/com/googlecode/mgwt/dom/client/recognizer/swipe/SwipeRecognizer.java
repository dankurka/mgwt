/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.dom.client.recognizer.swipe;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HasHandlers;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.recognizer.EventPropagator;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEvent.DIRECTION;

public class SwipeRecognizer implements TouchHandler {

	private static EventPropagator DEFAULT_EVENT_PROPAGATOR;

	private final HasHandlers source;

	private EventPropagator eventPropagator;

	private final int minDistance;

	private final int threshold;

	private int touchCount;

	private enum State {
		INVALID, READY, FINDER_DOWN, FOUND_DIRECTION
	}

	private State state;

	private Touch touchStart;

	private DIRECTION direction;

	private int lastDistance;

	/**
	 * construct a swipe recognizer
	 * 
	 * @param source the source to fire events on
	 */
	public SwipeRecognizer(HasHandlers source) {
		this(source, 40);
	}

	/**
	 * construct a swipe recognizer
	 * 
	 * @param source the source to fire events on
	 * @param minDistance the minimum distance to cover before this counts as a
	 *            swipe
	 */
	public SwipeRecognizer(HasHandlers source, int minDistance) {
		this(source, minDistance, 10);
	}

	/**
	 * construct a swipe recognizer
	 * 
	 * @param source the source to fire events on
	 * @param minDistance the minimum distance to cover before this counts as a
	 *            swipe
	 * @param threshold the initial threshold before swipe start is fired
	 */
	public SwipeRecognizer(HasHandlers source, int minDistance, int threshold) {
		if (source == null)
			throw new IllegalArgumentException("source can not be null");

		if (minDistance <= 0 || minDistance < threshold) {
			throw new IllegalArgumentException("minDistance > 0 and minDistance > threshold");
		}

		if (threshold <= 0) {
			throw new IllegalArgumentException("threshold > 0");
		}

		this.source = source;
		this.minDistance = minDistance;
		this.threshold = threshold;
		this.touchCount = 0;
		state = State.READY;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler#onTouchStart(com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent)
	 */
	@Override
	public void onTouchStart(TouchStartEvent event) {
		touchCount++;

		switch (state) {
		case INVALID:
			break;

		case READY:
			state = State.FINDER_DOWN;
			touchStart = event.getTouches().get(0);
			break;

		case FINDER_DOWN:
		default:
			state = State.INVALID;
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler#onTouchMove(com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent)
	 */
	@Override
	public void onTouchMove(TouchMoveEvent event) {
		Touch touch = event.getTouches().get(0);

		switch (state) {
		case INVALID:

			break;
		case READY:
			//WTF?
			state = State.INVALID;
			break;
		case FINDER_DOWN:

			if (Math.abs(touch.getPageX() - touchStart.getPageX()) >= threshold) {
				state = State.FOUND_DIRECTION;

				direction = touch.getPageX() - touchStart.getPageX() > 0 ? DIRECTION.LEFT_TO_RIGHT : DIRECTION.RIGHT_TO_LEFT;

				SwipeStartEvent swipeStartEvent = new SwipeStartEvent(touch.getPageX() - touchStart.getPageX(), direction);

				getEventPropagator().fireEvent(source, swipeStartEvent);

			} else {
				if (Math.abs(touch.getPageY() - touchStart.getPageY()) >= threshold) {
					state = State.FOUND_DIRECTION;

					direction = touch.getPageY() - touchStart.getPageY() > 0 ? DIRECTION.TOP_TO_BOTTOM : DIRECTION.BOTTOM_TO_TOP;

					SwipeStartEvent swipeStartEvent = new SwipeStartEvent(touch.getPageY() - touchStart.getPageY(), direction);

					getEventPropagator().fireEvent(source, swipeStartEvent);

				}

			}
			break;

		case FOUND_DIRECTION:

			switch (direction) {
			case TOP_TO_BOTTOM:
			case BOTTOM_TO_TOP:
				lastDistance = Math.abs(touch.getPageY() - touchStart.getPageY());
				getEventPropagator().fireEvent(source, new SwipeMoveEvent(lastDistance > minDistance, lastDistance, direction));
				break;

			case LEFT_TO_RIGHT:
			case RIGHT_TO_LEFT:
				lastDistance = Math.abs(touch.getPageX() - touchStart.getPageX());
				getEventPropagator().fireEvent(source, new SwipeMoveEvent(lastDistance > minDistance, lastDistance, direction));

				break;

			default:
				break;
			}

			break;

		default:
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler#onTouchEnd(com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent)
	 */
	@Override
	public void onTouchEnd(TouchEndEvent event) {
		touchCount--;

		switch (state) {
		case FOUND_DIRECTION:
			getEventPropagator().fireEvent(source, new SwipeEndEvent(lastDistance > minDistance, lastDistance, direction));
			reset();
			break;

		default:
			reset();
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler#onTouchCanceled(com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent)
	 */
	@Override
	public void onTouchCanceled(TouchCancelEvent event) {
		touchCount--;
		if (touchCount <= 0)
			reset();

	}

	/**
	 * the threshold before an event is fired (deadzone)
	 * 
	 * @return the threshold in px
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * the distance that needs to be covered before counting as a swipe
	 * 
	 * @return the distance in px
	 */
	public int getMinDistance() {
		return minDistance;
	}

	protected EventPropagator getEventPropagator() {
		if (eventPropagator == null) {
			if (DEFAULT_EVENT_PROPAGATOR == null) {
				DEFAULT_EVENT_PROPAGATOR = GWT.create(EventPropagator.class);
			}
			eventPropagator = DEFAULT_EVENT_PROPAGATOR;
		}
		return eventPropagator;
	}

	protected void setEventPropagator(EventPropagator eventPropagator) {
		this.eventPropagator = eventPropagator;

	}

	private void reset() {
		state = State.READY;
		touchCount = 0;

	}

}
