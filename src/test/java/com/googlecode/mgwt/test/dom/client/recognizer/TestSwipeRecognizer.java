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
package com.googlecode.mgwt.test.dom.client.recognizer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.googlecode.mgwt.dom.client.recognizer.EventPropagator;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEvent.DIRECTION;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeMoveEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeRecognizer;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeStartEvent;

public class TestSwipeRecognizer {

	private static class SwipeRecognizerForTest extends SwipeRecognizer {

		public SwipeRecognizerForTest(HasHandlers source) {
			super(source);
		}

		@Override
		public void setEventPropagator(EventPropagator eventPropagator) {
			super.setEventPropagator(eventPropagator);
		}

	}

	private MockHasHandlers hasHandlers;
	private SwipeRecognizerForTest swipeRecognizer;

	@Before
	public void setUp() throws Exception {
		hasHandlers = new MockHasHandlers();

		swipeRecognizer = new SwipeRecognizerForTest(hasHandlers);
		swipeRecognizer.setEventPropagator(new EventPropagatorForTests());
	}

	@Test
	public void testSwipeRecognizerHasHandlers() {
		try {
			new SwipeRecognizer(null);
			Assert.fail("expected exception did not occur");

		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testSwipeRecognizerHasHandlersInt() {
		try {
			new SwipeRecognizer(hasHandlers, -1);
			Assert.fail("expected exception did not occur");

		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testSwipeRecognizerHasHandlersInt1() {
		try {
			new SwipeRecognizer(hasHandlers, 2);
			Assert.fail("expected exception did not occur");

		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testSwipeRecognizerHasHandlersIntInt() {
		try {
			new SwipeRecognizer(hasHandlers, 20, -1);
			Assert.fail("expected exception did not occur");

		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testSimpleHorizontalSwipe() {
		swipeRecognizer.onTouchStart(new MockTouchStartEvent(1, 0, 0));

		boolean first = true;
		int i = 1;
		for (i = 1; i < 60; i++) {
			swipeRecognizer.onTouchMove(new MockTouchMoveEvent(1, i, 0));
			if (i >= swipeRecognizer.getThreshold()) {
				GwtEvent<?> event = hasHandlers.getEvent();

				if (first) {
					first = false;
					if (!(event instanceof SwipeStartEvent)) {
						Assert.fail("swipe start event was not fired");
					}
					SwipeStartEvent swipeStartEvent = (SwipeStartEvent) event;

					Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeStartEvent.getDirection());
					Assert.assertEquals(swipeRecognizer.getThreshold(), swipeStartEvent.getDistance());

				} else {
					if (!(event instanceof SwipeMoveEvent)) {
						Assert.fail("swipe move event was not fired");
					}
					SwipeMoveEvent swipeMoveEvent = (SwipeMoveEvent) event;

					Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeMoveEvent.getDirection());
					Assert.assertEquals(i, swipeMoveEvent.getDistance());

					if (swipeMoveEvent.getDistance() > swipeRecognizer.getMinDistance()) {
						Assert.assertTrue(swipeMoveEvent.isDistanceReached());
					} else {
						Assert.assertFalse(swipeMoveEvent.isDistanceReached());
					}

				}
			}
		}

		swipeRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = hasHandlers.getEvent();

		if (!(event instanceof SwipeEndEvent)) {
			Assert.fail("swipe end event was not fired");
		}
		SwipeEndEvent swipeEndEvent = (SwipeEndEvent) event;

		Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeEndEvent.getDirection());
		Assert.assertEquals(i - 1, swipeEndEvent.getDistance());

		Assert.assertTrue(swipeEndEvent.isDistanceReached());

	}

	@Test
	public void testSimpleHorizontalSwipe2Times() {
		swipeRecognizer.onTouchStart(new MockTouchStartEvent(1, 0, 0));

		boolean first = true;
		int i = 1;
		for (i = 1; i < 60; i++) {
			swipeRecognizer.onTouchMove(new MockTouchMoveEvent(1, i, 0));
			if (i >= swipeRecognizer.getThreshold()) {
				GwtEvent<?> event = hasHandlers.getEvent();

				if (first) {
					first = false;
					if (!(event instanceof SwipeStartEvent)) {
						Assert.fail("swipe start event was not fired");
					}
					SwipeStartEvent swipeStartEvent = (SwipeStartEvent) event;

					Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeStartEvent.getDirection());
					Assert.assertEquals(swipeRecognizer.getThreshold(), swipeStartEvent.getDistance());

				} else {
					if (!(event instanceof SwipeMoveEvent)) {
						Assert.fail("swipe move event was not fired");
					}
					SwipeMoveEvent swipeMoveEvent = (SwipeMoveEvent) event;

					Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeMoveEvent.getDirection());
					Assert.assertEquals(i, swipeMoveEvent.getDistance());

					if (swipeMoveEvent.getDistance() > swipeRecognizer.getMinDistance()) {
						Assert.assertTrue(swipeMoveEvent.isDistanceReached());
					} else {
						Assert.assertFalse(swipeMoveEvent.isDistanceReached());
					}

				}
			}
		}

		swipeRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = hasHandlers.getEvent();

		if (!(event instanceof SwipeEndEvent)) {
			Assert.fail("swipe end event was not fired");
		}
		SwipeEndEvent swipeEndEvent = (SwipeEndEvent) event;

		Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeEndEvent.getDirection());
		Assert.assertEquals(i - 1, swipeEndEvent.getDistance());

		Assert.assertTrue(swipeEndEvent.isDistanceReached());

		swipeRecognizer.onTouchStart(new MockTouchStartEvent(1, 0, 0));

		first = true;

		for (i = 1; i < 60; i++) {
			swipeRecognizer.onTouchMove(new MockTouchMoveEvent(1, i, 0));
			if (i >= swipeRecognizer.getThreshold()) {
				event = hasHandlers.getEvent();

				if (first) {
					first = false;
					if (!(event instanceof SwipeStartEvent)) {
						Assert.fail("swipe start event was not fired");
					}
					SwipeStartEvent swipeStartEvent = (SwipeStartEvent) event;

					Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeStartEvent.getDirection());
					Assert.assertEquals(swipeRecognizer.getThreshold(), swipeStartEvent.getDistance());

				} else {
					if (!(event instanceof SwipeMoveEvent)) {
						Assert.fail("swipe move event was not fired");
					}
					SwipeMoveEvent swipeMoveEvent = (SwipeMoveEvent) event;

					Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeMoveEvent.getDirection());
					Assert.assertEquals(i, swipeMoveEvent.getDistance());

					if (swipeMoveEvent.getDistance() > swipeRecognizer.getMinDistance()) {
						Assert.assertTrue(swipeMoveEvent.isDistanceReached());
					} else {
						Assert.assertFalse(swipeMoveEvent.isDistanceReached());
					}

				}
			}
		}

		swipeRecognizer.onTouchEnd(new MockTouchEndEvent());

		event = hasHandlers.getEvent();

		if (!(event instanceof SwipeEndEvent)) {
			Assert.fail("swipe end event was not fired");
		}
		swipeEndEvent = (SwipeEndEvent) event;

		Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeEndEvent.getDirection());
		Assert.assertEquals(i - 1, swipeEndEvent.getDistance());

		Assert.assertTrue(swipeEndEvent.isDistanceReached());

	}

	@Test
	public void testSimpleVerticalSwipe() {
		swipeRecognizer.onTouchStart(new MockTouchStartEvent(1, 0, 0));

		boolean first = true;
		int i = 1;
		for (i = 1; i < 60; i++) {
			swipeRecognizer.onTouchMove(new MockTouchMoveEvent(1, 0, i));
			if (i >= swipeRecognizer.getThreshold()) {
				GwtEvent<?> event = hasHandlers.getEvent();

				if (first) {
					first = false;
					if (!(event instanceof SwipeStartEvent)) {
						Assert.fail("swipe start event was not fired");
					}
					SwipeStartEvent swipeStartEvent = (SwipeStartEvent) event;

					Assert.assertEquals(DIRECTION.TOP_TO_BOTTOM, swipeStartEvent.getDirection());
					Assert.assertEquals(swipeRecognizer.getThreshold(), swipeStartEvent.getDistance());

				} else {
					if (!(event instanceof SwipeMoveEvent)) {
						Assert.fail("swipe move event was not fired");
					}
					SwipeMoveEvent swipeMoveEvent = (SwipeMoveEvent) event;

					Assert.assertEquals(DIRECTION.TOP_TO_BOTTOM, swipeMoveEvent.getDirection());
					Assert.assertEquals(i, swipeMoveEvent.getDistance());

					if (swipeMoveEvent.getDistance() > swipeRecognizer.getMinDistance()) {
						Assert.assertTrue(swipeMoveEvent.isDistanceReached());
					} else {
						Assert.assertFalse(swipeMoveEvent.isDistanceReached());
					}

				}
			}
		}

		swipeRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = hasHandlers.getEvent();

		if (!(event instanceof SwipeEndEvent)) {
			Assert.fail("swipe end event was not fired");
		}
		SwipeEndEvent swipeEndEvent = (SwipeEndEvent) event;

		Assert.assertEquals(DIRECTION.TOP_TO_BOTTOM, swipeEndEvent.getDirection());
		Assert.assertEquals(i - 1, swipeEndEvent.getDistance());

		Assert.assertTrue(swipeEndEvent.isDistanceReached());

	}

	@Test
	public void testSimpleVerticalSwipeAfterRandomInput() {

		swipeRecognizer.onTouchStart(new MockTouchStartEvent(2, 0, 0));
		swipeRecognizer.onTouchMove(new MockTouchMoveEvent(2, 4, 4));
		swipeRecognizer.onTouchEnd(new MockTouchEndEvent());

		swipeRecognizer.onTouchStart(new MockTouchStartEvent(1, 0, 0));

		boolean first = true;
		int i = 1;
		for (i = 1; i < 60; i++) {
			swipeRecognizer.onTouchMove(new MockTouchMoveEvent(1, 0, i));
			if (i >= swipeRecognizer.getThreshold()) {
				GwtEvent<?> event = hasHandlers.getEvent();

				if (first) {
					first = false;
					if (!(event instanceof SwipeStartEvent)) {
						Assert.fail("swipe start event was not fired");
					}
					SwipeStartEvent swipeStartEvent = (SwipeStartEvent) event;

					Assert.assertEquals(DIRECTION.TOP_TO_BOTTOM, swipeStartEvent.getDirection());
					Assert.assertEquals(swipeRecognizer.getThreshold(), swipeStartEvent.getDistance());

				} else {
					if (!(event instanceof SwipeMoveEvent)) {
						Assert.fail("swipe move event was not fired");
					}
					SwipeMoveEvent swipeMoveEvent = (SwipeMoveEvent) event;

					Assert.assertEquals(DIRECTION.TOP_TO_BOTTOM, swipeMoveEvent.getDirection());
					Assert.assertEquals(i, swipeMoveEvent.getDistance());

					if (swipeMoveEvent.getDistance() > swipeRecognizer.getMinDistance()) {
						Assert.assertTrue(swipeMoveEvent.isDistanceReached());
					} else {
						Assert.assertFalse(swipeMoveEvent.isDistanceReached());
					}

				}
			}
		}

		swipeRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = hasHandlers.getEvent();

		if (!(event instanceof SwipeEndEvent)) {
			Assert.fail("swipe end event was not fired");
		}
		SwipeEndEvent swipeEndEvent = (SwipeEndEvent) event;

		Assert.assertEquals(DIRECTION.TOP_TO_BOTTOM, swipeEndEvent.getDirection());
		Assert.assertEquals(i - 1, swipeEndEvent.getDistance());

		Assert.assertTrue(swipeEndEvent.isDistanceReached());

	}

}
