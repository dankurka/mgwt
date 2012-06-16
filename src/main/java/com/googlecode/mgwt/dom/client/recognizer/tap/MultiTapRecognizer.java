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
package com.googlecode.mgwt.dom.client.recognizer.tap;

import com.google.gwt.event.shared.HasHandlers;
import com.googlecode.mgwt.collection.shared.CollectionFactory;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.recognizer.SystemTimeProvider;
import com.googlecode.mgwt.dom.client.recognizer.TimeProvider;

/**
 * A {@link MultiTapRecognizer} recognizes multiple taps with multiple fingers
 * on the screen
 * 
 * @author Daniel Kurka
 * 
 */
public class MultiTapRecognizer implements TouchHandler {

	public static final int DEFAULT_DISTANCE = 15;
	public static final int DEFAULT_TIME_IN_MS = 300;

	private final HasHandlers source;
	private final int distance;
	private final int time;
	private final int numberOfTabs;

	private int touchCount;

	private LightArray<Touch> touches;
	private final int numberOfFingers;

	private TimeProvider timeProvider;

	private enum State {
		READY, FINGERS_GOING_DOWN, FINGERS_GOING_UP, INVALID
	}

	private State state;
	private int foundTaps;
	private int touchMax;
	private long lastTime;

	private LightArray<LightArray<Touch>> savedStartTouches;

	/**
	 * Construct a {@link MultiTapRecognizer}
	 * 
	 * @param source the source on which behalf to fire events on
	 * @param numberOfFingers the number of fingers needed for a tap
	 */
	public MultiTapRecognizer(HasHandlers source, int numberOfFingers) {
		this(source, numberOfFingers, 1, DEFAULT_DISTANCE, DEFAULT_TIME_IN_MS);
	}

	/**
	 * Construct a {@link MultiTapRecognizer}
	 * 
	 * @param source the source on which behalf to fire events on
	 * @param numberOfFingers the number of fingers needed for a tap
	 * @param numberOfTabs the number of times all fingers have to touch and
	 *            leave the display
	 */
	public MultiTapRecognizer(HasHandlers source, int numberOfFingers, int numberOfTabs) {
		this(source, numberOfFingers, numberOfTabs, DEFAULT_DISTANCE, DEFAULT_TIME_IN_MS);
	}

	/**
	 * Construct a {@link MultiTapRecognizer}
	 * 
	 * @param source the source on which behalf to fire events on
	 * @param numberOfFingers the number of fingers needed for a tap
	 * @param numberOfTabs the number of times all fingers have to touch and
	 *            leave the display
	 * @param distance the maximum distance a finger can move on the display
	 */
	public MultiTapRecognizer(HasHandlers source, int numberOfFingers, int numberOfTabs, int distance) {
		this(source, numberOfFingers, numberOfTabs, distance, DEFAULT_TIME_IN_MS);
	}

	/**
	 * Construct a {@link MultiTapRecognizer}
	 * 
	 * @param source the source on which behalf to fire events on
	 * @param numberOfFingers the number of fingers needed for a tap
	 * @param numberOfTabs the number of times all fingers have to touch and
	 *            leave the display
	 * @param distance the maximum distance a finger can move on the display
	 * @param time the maximum amount of time for the gesture to happen
	 */
	public MultiTapRecognizer(HasHandlers source, int numberOfFingers, int numberOfTabs, int distance, int time) {

		if (source == null)
			throw new IllegalArgumentException("source can not be null");

		if (numberOfFingers < 1) {
			throw new IllegalArgumentException("numberOfFingers > 0");
		}

		if (numberOfTabs < 1) {
			throw new IllegalArgumentException("numberOfTabs > 0");
		}

		if (distance < 0)
			throw new IllegalArgumentException("distance > 0");

		if (time < 1) {
			throw new IllegalArgumentException("time > 0");
		}
		this.source = source;
		this.numberOfFingers = numberOfFingers;
		this.numberOfTabs = numberOfTabs;
		this.distance = distance;
		this.time = time;
		touchCount = 0;
		touches = CollectionFactory.constructArray();
		savedStartTouches = CollectionFactory.constructArray();
		state = State.READY;
		foundTaps = 0;
		timeProvider = new SystemTimeProvider();

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler#onTouchStart(com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent)
	 */
	@Override
	public void onTouchStart(TouchStartEvent event) {
		touchCount++;
		LightArray<Touch> currentTouches = event.getTouches();

		switch (state) {
		case READY:
			touches.push(currentTouches.get(touchCount - 1));
			state = State.FINGERS_GOING_DOWN;
			break;

		case FINGERS_GOING_DOWN:
			touches.push(currentTouches.get(touchCount - 1));
			break;

		case FINGERS_GOING_UP:
		default:
			state = State.INVALID;
			break;
		}

		if (touchCount > numberOfFingers) {
			state = State.INVALID;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler#onTouchMove(com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent)
	 */
	@Override
	public void onTouchMove(TouchMoveEvent event) {
		switch (state) {
		case FINGERS_GOING_DOWN:
		case FINGERS_GOING_UP:
			// compare positions
			LightArray<Touch> currentTouches = event.getTouches();
			for (int i = 0; i < currentTouches.length(); i++) {
				Touch currentTouch = currentTouches.get(i);
				for (int j = 0; j < touches.length(); j++) {
					Touch startTouch = touches.get(j);
					if (currentTouch.getIdentifier() == startTouch.getIdentifier()) {
						if (Math.abs(currentTouch.getPageX() - startTouch.getPageX()) > distance || Math.abs(currentTouch.getPageY() - startTouch.getPageY()) > distance) {
							state = State.INVALID;
							break;
						}
					}
					if (state == State.INVALID) {
						break;
					}
				}
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

		switch (state) {
		case FINGERS_GOING_DOWN:
			state = State.FINGERS_GOING_UP;
			touchMax = touchCount;

			touchCount--;
			handleTouchEnd();
			break;
		case FINGERS_GOING_UP:
			touchCount--;
			handleTouchEnd();
			break;

		case INVALID:
		case READY:
			savedStartTouches = CollectionFactory.constructArray();
			if (event.getTouches().length() == 0)
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
		state = State.INVALID;
		reset();

	}

	protected void handleTouchEnd() {
		if (touchCount == 0) {
			// found one successful tap
			if (foundTaps > 0) {
				// check time otherwise invalid
				if (timeProvider.getTime() - lastTime > time) {
					savedStartTouches = CollectionFactory.constructArray();
					reset();
					return;
				}
			}
			foundTaps++;
			lastTime = timeProvider.getTime();

			// remember touches
			savedStartTouches.push(touches);

			if (foundTaps == numberOfTabs) {

				MultiTapEvent multiTapEvent = new MultiTapEvent(touchMax, numberOfTabs, savedStartTouches);
				source.fireEvent(multiTapEvent);
				savedStartTouches = CollectionFactory.constructArray();
				reset();
			} else {
				state = State.READY;
				touches = CollectionFactory.constructArray();
			}

		}
	}

	protected void reset() {
		touchCount = 0;
		foundTaps = 0;
		touches = CollectionFactory.constructArray();
		state = State.READY;
	}

	protected void setTimeProvider(TimeProvider timeProvider) {
		if (timeProvider == null) {
			throw new IllegalArgumentException("timeprovider can not be null");
		}
		this.timeProvider = timeProvider;
	}

}
