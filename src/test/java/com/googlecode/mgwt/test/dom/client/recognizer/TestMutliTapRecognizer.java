/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.test.dom.client.recognizer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.googlecode.mgwt.collection.shared.CollectionFactory;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.recognizer.TimeProvider;
import com.googlecode.mgwt.dom.client.recognizer.tap.MultiTapEvent;
import com.googlecode.mgwt.dom.client.recognizer.tap.MultiTapRecognizer;

public class TestMutliTapRecognizer {

  private static class MultiTapRecognizerForTests extends MultiTapRecognizer {

    public MultiTapRecognizerForTests(HasHandlers source, int numberOfFingers, int numberOfTabs, int distance) {
      super(source, numberOfFingers, numberOfTabs, distance);
    }

    public MultiTapRecognizerForTests(MockHasHandlers hasHandlers, int i) {
      super(hasHandlers, i);
    }

    public void setTimeProvider(TimeProvider timeProvider) {
      super.setTimeProvider(timeProvider);
    }

  }

  private MockHasHandlers hasHandlers;

  @Before
  public void setup() {
    hasHandlers = new MockHasHandlers();
  }

  @Test
  public void testExceptionInConstructor() {
    try {
      new MultiTapRecognizer(null, 1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void testExceptionInConstructor1() {
    try {
      new MultiTapRecognizer(new MockHasHandlers(), 0);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void testExceptionInConstructor2() {
    try {
      new MultiTapRecognizer(new MockHasHandlers(), 1, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void testExceptionInConstructor3() {
    try {
      new MultiTapRecognizer(new MockHasHandlers(), 1, 1, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void testExceptionInConstructor4() {
    try {
      new MultiTapRecognizer(new MockHasHandlers(), 1, 1, 1, 0);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void testMultiTapRecognizerHasHandlersIntInt() {
    new MultiTapRecognizer(new MockHasHandlers(), 1, 1, 1);
  }

  @Test
  public void testOneFingerSingleTap() {
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 1);

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 1));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    GwtEvent<?> event = hasHandlers.getEvent();
    Assert.assertNotNull(event);

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    MultiTapEvent multiTapEvent = (MultiTapEvent) event;

    Assert.assertEquals(1, multiTapEvent.getNumberOfFinders());

  }

  @Test
  public void testOneFingerSingleTapWithMove() {
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 1, 10);

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    GwtEvent<?> event = hasHandlers.getEvent();
    Assert.assertNotNull(event);

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    MultiTapEvent multiTapEvent = (MultiTapEvent) event;

    Assert.assertEquals(1, multiTapEvent.getNumberOfFinders());
    Assert.assertEquals(1, multiTapEvent.getNumberOfTabs());

    Assert.assertEquals(1, multiTapEvent.getTouchStarts().get(0).get(0).getPageX());
    Assert.assertEquals(2, multiTapEvent.getTouchStarts().get(0).get(0).getPageY());

  }

  @Test
  public void testOneFingerSingleTapWithMoveTwice() {
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 1, 10);

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    GwtEvent<?> event = hasHandlers.getEvent();
    Assert.assertNotNull(event);

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    MultiTapEvent multiTapEvent = (MultiTapEvent) event;

    Assert.assertEquals(1, multiTapEvent.getNumberOfFinders());
    Assert.assertEquals(1, multiTapEvent.getNumberOfTabs());

    Assert.assertEquals(1, multiTapEvent.getTouchStarts().get(0).get(0).getPageX());
    Assert.assertEquals(2, multiTapEvent.getTouchStarts().get(0).get(0).getPageY());

    // second input
    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    event = hasHandlers.getEvent();
    Assert.assertNotNull(event);

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    multiTapEvent = (MultiTapEvent) event;

    Assert.assertEquals(1, multiTapEvent.getNumberOfFinders());
    Assert.assertEquals(1, multiTapEvent.getNumberOfTabs());

    Assert.assertEquals(1, multiTapEvent.getTouchStarts().get(0).get(0).getPageX());
    Assert.assertEquals(2, multiTapEvent.getTouchStarts().get(0).get(0).getPageY());

  }

  @Test
  public void testTwoTapsWithOneFinger() {

    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 2, 10);

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());
    recognizer.onTouchStart(new MockTouchStartEvent(1, 3, 4));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(1, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    GwtEvent<?> event = hasHandlers.getEvent();
    Assert.assertNotNull(event);

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    MultiTapEvent multiTapEvent = (MultiTapEvent) event;

    Assert.assertEquals(1, multiTapEvent.getNumberOfFinders());
    Assert.assertEquals(2, multiTapEvent.getNumberOfTabs());

    Assert.assertEquals(1, multiTapEvent.getTouchStarts().get(0).get(0).getPageX());
    Assert.assertEquals(2, multiTapEvent.getTouchStarts().get(0).get(0).getPageY());

    Assert.assertEquals(3, multiTapEvent.getTouchStarts().get(1).get(0).getPageX());
    Assert.assertEquals(4, multiTapEvent.getTouchStarts().get(1).get(0).getPageY());

  }

  @Test
  public void testTwoTapsWithOneFingerButMovetoofarOnSecondTap() {

    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 2, 10);

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());
    recognizer.onTouchStart(new MockTouchStartEvent(1, 3, 4));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(1, 3 + MultiTapRecognizer.DEFAULT_DISTANCE + 1, 4 + MultiTapRecognizer.DEFAULT_DISTANCE + 1));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());

  }

  @Test
  public void testTwoTapsWithOneFingerButMovetoofarOnSecondTapAndThenTwoTaps() {

    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 2, 10);

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());
    recognizer.onTouchStart(new MockTouchStartEvent(1, 3, 4));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(1, 3 + MultiTapRecognizer.DEFAULT_DISTANCE + 1, 4 + MultiTapRecognizer.DEFAULT_DISTANCE + 1));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());
    recognizer.onTouchStart(new MockTouchStartEvent(1, 3, 4));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(1, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    GwtEvent<?> event = hasHandlers.getEvent();
    Assert.assertNotNull(event);

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    MultiTapEvent multiTapEvent = (MultiTapEvent) event;

    Assert.assertEquals(1, multiTapEvent.getNumberOfFinders());
    Assert.assertEquals(2, multiTapEvent.getNumberOfTabs());

    Assert.assertEquals(1, multiTapEvent.getTouchStarts().get(0).get(0).getPageX());
    Assert.assertEquals(2, multiTapEvent.getTouchStarts().get(0).get(0).getPageY());

    Assert.assertEquals(3, multiTapEvent.getTouchStarts().get(1).get(0).getPageX());
    Assert.assertEquals(4, multiTapEvent.getTouchStarts().get(1).get(0).getPageY());

  }

  @Test
  public void testTimeoutWithTwoSingleFingerTaps() {
    MultiTapRecognizerForTests recognizer = new MultiTapRecognizerForTests(hasHandlers, 1, 2, 10);

    recognizer.setTimeProvider(new TimeProvider() {

      private int count = 0;

      @Override
      public long getTime() {
        count++;
        if (count == 1)
          return 0;
        return MultiTapRecognizer.DEFAULT_TIME_IN_MS + 1;
      }
    });

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());
    recognizer.onTouchStart(new MockTouchStartEvent(1, 3, 4));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(1, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());

  }

  @Test
  public void testExceptionOnNullTimeProvider() {
    MultiTapRecognizerForTests multiTapRecognizer = new MultiTapRecognizerForTests(hasHandlers, 1);
    try {
      multiTapRecognizer.setTimeProvider(null);
      Assert.fail("expected excetion did not occur");
    } catch (IllegalArgumentException e) {

    }

  }

  @Test
  public void testTwoTapsWithOneFingerInterrupedbyTouchcancel() {

    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 2, 10);

    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNull(hasHandlers.getEvent());
    recognizer.onTouchStart(new MockTouchStartEvent(1, 3, 4));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(1, 3 + MultiTapRecognizer.DEFAULT_DISTANCE + 1, 4 + MultiTapRecognizer.DEFAULT_DISTANCE + 1));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    recognizer.onTouchCanceled(new MockTouchCancelEvent());
    Assert.assertNull(hasHandlers.getEvent());

  }

  @Test
  public void testTwoFingerDownOneUpAndDownAgain() {

    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 2, 1, 10);

    // first finger down
    recognizer.onTouchStart(new MockTouchStartEvent(0, 1, 2));
    LightArray<Touch> touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(0, 2, 2));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    Assert.assertNull(hasHandlers.getEvent());

    // second finger down and up

    LightArray<Touch> touches1 = CollectionFactory.constructArray();
    touches1.push(new MockTouch(0, 1, 2));
    touches1.push(new MockTouch(1, 3, 4));

    recognizer.onTouchStart(new MockTouchStartEvent(touches1));
    recognizer.onTouchEnd(new MockTouchEndEvent(touches));

    Assert.assertNull(hasHandlers.getEvent());

    // second finger down again and up
    recognizer.onTouchStart(new MockTouchStartEvent(2, 3, 4));
    recognizer.onTouchEnd(new MockTouchEndEvent(touches));

    Assert.assertNull(hasHandlers.getEvent());

    // last finger up
    recognizer.onTouchEnd(new MockTouchEndEvent());
    Assert.assertNull(hasHandlers.getEvent());

    // lets test if we find a correct one now...
    recognizer.onTouchStart(new MockTouchStartEvent(3, 10, 20));
    touches = CollectionFactory.constructArray();
    touches.push(new MockTouch(3, 11, 20));
    recognizer.onTouchMove(new MockMultiTouchMoveEvent(touches));
    Assert.assertNull(hasHandlers.getEvent());

    LightArray<Touch> touchStartArray = CollectionFactory.constructArray();
    touchStartArray.push(new MockTouch(3, 11, 20));
    touchStartArray.push(new MockTouch(4, 30, 40));

    // second finger down and up
    recognizer.onTouchStart(new MockMultiTouchStartEvent(touchStartArray));
    recognizer.onTouchEnd(new MockTouchEndEvent(touches));
    recognizer.onTouchEnd(new MockTouchEndEvent());

    Assert.assertNotNull(hasHandlers.getEvent());
    GwtEvent<?> event = hasHandlers.getEvent();

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    MultiTapEvent multiTapEvent = (MultiTapEvent) event;

    Assert.assertEquals(2, multiTapEvent.getNumberOfFinders());
    Assert.assertEquals(1, multiTapEvent.getNumberOfTabs());

    Assert.assertEquals(10, multiTapEvent.getTouchStarts().get(0).get(0).getPageX());
    Assert.assertEquals(20, multiTapEvent.getTouchStarts().get(0).get(0).getPageY());

    Assert.assertEquals(30, multiTapEvent.getTouchStarts().get(0).get(1).getPageX());
    Assert.assertEquals(40, multiTapEvent.getTouchStarts().get(0).get(1).getPageY());

  }

}
