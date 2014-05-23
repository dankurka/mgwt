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

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.googlecode.mgwt.dom.client.recognizer.EventPropagator;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEvent.DIRECTION;
import com.googlecode.mgwt.helper.TouchTestUtil;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

@RunWith(GwtMockitoTestRunner.class)
public class SwipeRecognizerTest {

  @Mock
  HasHandlers hasHandlers;
  @Mock
  EventPropagator eventPropagator;

  @Captor
  ArgumentCaptor<GwtEvent<?>> eventCaptor;
  @Captor
  ArgumentCaptor<HasHandlers> hasHandlersCaptor;

  private SwipeRecognizer swipeRecognizer;

  @Before
  public void setUp() throws Exception {
    swipeRecognizer = new SwipeRecognizer(hasHandlers) {
      @Override
      EventPropagator getEventPropagator() {
        return eventPropagator;
      }
    };
  }

  @Test
  public void testSwipeRecognizerHasHandlers() {
    try {
      new SwipeRecognizer(null);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testSwipeRecognizerHasHandlersInt() {
    try {
      new SwipeRecognizer(hasHandlers, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testSwipeRecognizerHasHandlersInt1() {
    try {
      new SwipeRecognizer(hasHandlers, 2);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testSwipeRecognizerHasHandlersIntInt() {
    try {
      new SwipeRecognizer(hasHandlers, 20, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testSimpleHorizontalSwipe() {
    swipeRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(0, 0));

    boolean first = true;
    int i = 1;
    for (i = 1; i < 60; i++) {
      swipeRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(i, 0));
      if (i >= swipeRecognizer.getThreshold()) {

        verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());
        reset(eventPropagator);
        GwtEvent<?> event = eventCaptor.getValue();

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

    swipeRecognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());

    verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();

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
    swipeRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(0, 0));

    boolean first = true;
    int i = 1;
    for (i = 1; i < 60; i++) {
      swipeRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(i, 0));
      if (i >= swipeRecognizer.getThreshold()) {

        verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());
        reset(eventPropagator);
        GwtEvent<?> event = eventCaptor.getValue();

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

    swipeRecognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());

    verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();

    if (!(event instanceof SwipeEndEvent)) {
      Assert.fail("swipe end event was not fired");
    }
    SwipeEndEvent swipeEndEvent = (SwipeEndEvent) event;

    Assert.assertEquals(DIRECTION.LEFT_TO_RIGHT, swipeEndEvent.getDirection());
    Assert.assertEquals(i - 1, swipeEndEvent.getDistance());

    Assert.assertTrue(swipeEndEvent.isDistanceReached());

    // second time
    reset(eventPropagator);
    swipeRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(0, 0));

    first = true;

    for (i = 1; i < 60; i++) {
      swipeRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(i, 0));
      if (i >= swipeRecognizer.getThreshold()) {

        verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());
        reset(eventPropagator);
        event = eventCaptor.getValue();

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

    swipeRecognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());

    verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());

    event = eventCaptor.getValue();

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
    swipeRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(0, 0));

    boolean first = true;
    int i = 1;
    for (i = 1; i < 60; i++) {
      swipeRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(0, i));
      if (i >= swipeRecognizer.getThreshold()) {

        verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());
        reset(eventPropagator);
        GwtEvent<?> event = eventCaptor.getValue();

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

    swipeRecognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());

    verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();

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
    swipeRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(0, 0));
    swipeRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(1, 1));
    swipeRecognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());

    swipeRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(0, 0));

    boolean first = true;
    int i = 1;
    for (i = 1; i < 60; i++) {
      swipeRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(0, i));
      if (i >= swipeRecognizer.getThreshold()) {

        verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());
        reset(eventPropagator);
        GwtEvent<?> event = eventCaptor.getValue();

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

    swipeRecognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());

    verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();

    if (!(event instanceof SwipeEndEvent)) {
      Assert.fail("swipe end event was not fired");
    }
    SwipeEndEvent swipeEndEvent = (SwipeEndEvent) event;

    Assert.assertEquals(DIRECTION.TOP_TO_BOTTOM, swipeEndEvent.getDirection());
    Assert.assertEquals(i - 1, swipeEndEvent.getDistance());

    Assert.assertTrue(swipeEndEvent.isDistanceReached());
  }
}
