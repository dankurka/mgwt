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
package com.googlecode.mgwt.dom.client.recognizer.tap;

import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.googlecode.mgwt.dom.client.recognizer.TimeProvider;
import com.googlecode.mgwt.helper.TouchTestUtil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

@RunWith(GwtMockitoTestRunner.class)
public class MultiTapRecognizerTest {

  @Mock HasHandlers hasHandlers;
  @Mock TimeProvider timeProvider;

  @Captor ArgumentCaptor<GwtEvent<?>> eventCaptor;

  @Before
  public void setup() {

  }

  @Test
  public void testExceptionInConstructor() {
    try {
      new MultiTapRecognizer(null, 1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testExceptionInConstructor1() {
    try {
      new MultiTapRecognizer(hasHandlers, 0);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testExceptionInConstructor2() {
    try {
      new MultiTapRecognizer(hasHandlers, 1, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testExceptionInConstructor3() {
    try {
      new MultiTapRecognizer(hasHandlers, 1, 1, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testExceptionInConstructor4() {
    try {
      new MultiTapRecognizer(hasHandlers, 1, 1, 1, 0);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testOneFingerSingleTap() {
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 1);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));
    verify(hasHandlers).fireEvent(eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();
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

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();
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

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();
    Assert.assertNotNull(event);

    if (!(event instanceof MultiTapEvent)) {
      Assert.fail("wrong event fired");
    }
    MultiTapEvent multiTapEvent = (MultiTapEvent) event;
    Assert.assertEquals(1, multiTapEvent.getNumberOfFinders());
    Assert.assertEquals(1, multiTapEvent.getNumberOfTabs());
    Assert.assertEquals(1, multiTapEvent.getTouchStarts().get(0).get(0).getPageX());
    Assert.assertEquals(2, multiTapEvent.getTouchStarts().get(0).get(0).getPageY());

    reset(hasHandlers);

    // second input
    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());

    event = eventCaptor.getValue();
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

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));

    verifyNoMoreInteractions(hasHandlers);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(3, 4));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();
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

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(3, 4));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(
        3 + MultiTapRecognizer.DEFAULT_DISTANCE + 1, 4 + MultiTapRecognizer.DEFAULT_DISTANCE + 1));
    recognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());
    verifyNoMoreInteractions(hasHandlers);
  }

  @Test
  public void testTwoTapsWithOneFingerButMovetoofarOnSecondTapAndThenTwoTaps() {
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 2, 10);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(3, 4));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(
        3 + MultiTapRecognizer.DEFAULT_DISTANCE + 1, 4 + MultiTapRecognizer.DEFAULT_DISTANCE + 1));
    recognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());
    verifyNoMoreInteractions(hasHandlers);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));

    verifyNoMoreInteractions(hasHandlers);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(3, 4));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();
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
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 2, 10) {
      @Override
      TimeProvider getTimeProvider() {
        return timeProvider;
      }
    };

    when(timeProvider.getTime()).thenReturn(0L, MultiTapRecognizer.DEFAULT_TIME_IN_MS + 1L);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);
  }


  @Test
  public void testTwoTapsWithOneFingerInterrupedbyTouchcancel() {
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 1, 2, 10);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);

    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    recognizer.onTouchCancel(mock(TouchCancelEvent.class));
    verifyNoMoreInteractions(hasHandlers);
  }

  @Test
  public void testTwoFingerDownOneUpAndDownAgain() {
    MultiTapRecognizer recognizer = new MultiTapRecognizer(hasHandlers, 2, 1, 10);

    // first finger down
    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 2));
    verifyNoMoreInteractions(hasHandlers);

    // second finger down and up
    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(new int[] {1, 2, 3, 4}));
    recognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);

    // second finger down again and up
    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(new int[] {1, 2, 3, 4}));
    recognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());
    verifyNoMoreInteractions(hasHandlers);

    // last finger up
    recognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());
    verifyNoMoreInteractions(hasHandlers);

    // lets test if we find a correct one now...
    // first finger down
    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(10, 20));
    recognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(11, 22));
    verifyNoMoreInteractions(hasHandlers);

    // second finger down again and up
    recognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(new int[] {10, 20, 30, 40}));
    recognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());
    verifyNoMoreInteractions(hasHandlers);

    // last finger up
    recognizer.onTouchEnd(TouchTestUtil.createTouchEndEvent());

    verify(hasHandlers).fireEvent(eventCaptor.capture());

    GwtEvent<?> event = eventCaptor.getValue();
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
