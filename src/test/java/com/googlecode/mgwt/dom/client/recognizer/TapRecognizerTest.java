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
package com.googlecode.mgwt.dom.client.recognizer;


import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.helper.TouchTestUtil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

@RunWith(GwtMockitoTestRunner.class)
public class TapRecognizerTest {
	private static final int distance = 10;

	@Mock HasHandlers hasHandlers;

	@Captor ArgumentCaptor<GwtEvent<?>> eventCaptor;

	private TapRecognizer tapRecognizer;

  @Before
  public void before() {
    tapRecognizer = new TapRecognizer(hasHandlers, distance) {
      protected com.googlecode.mgwt.dom.client.recognizer.EventPropagator getEventPropagator() {
        return new EventPropagatorStandardImpl();
      }
    };
  }

  @Test
  public void testTapRecognizerHasHandlers() {
    try {
      new TapRecognizer(null);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testTapRecognizerHasHandlersInt() {
    try {
      new TapRecognizer(hasHandlers, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testSimpleTouchStartAndEnd() {
    TouchStartEvent touchStartEvent = TouchTestUtil.createTouchStartEvent(1, 2);
    tapRecognizer.onTouchStart(touchStartEvent);

    TouchEndEvent touchEndEvent = mock(TouchEndEvent.class);
    tapRecognizer.onTouchEnd(touchEndEvent);

    verify(hasHandlers).fireEvent(eventCaptor.capture());
    GwtEvent<?> event = eventCaptor.getValue();
    TapEvent tapEvent = (TapEvent) event;
    Assert.assertEquals(1, tapEvent.getStartX());
    Assert.assertEquals(2, tapEvent.getStartY());
    Assert.assertSame(hasHandlers, tapEvent.getSource());
  }

  @Test
  public void testSimpleTouchStartAndCancel() {
    TouchStartEvent touchStartEvent = TouchTestUtil.createTouchStartEvent(1, 2);
    tapRecognizer.onTouchStart(touchStartEvent);
    tapRecognizer.onTouchCancel(mock(TouchCancelEvent.class));

    verifyNoMoreInteractions(hasHandlers);
  }

  @Test
  public void testSimpleTouchStartWithMoveAndEnd() {
    tapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    tapRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2, 3));
    tapRecognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());
    GwtEvent<?> event = eventCaptor.getValue();
    TapEvent tapEvent = (TapEvent) event;
    Assert.assertEquals(1, tapEvent.getStartX());
    Assert.assertEquals(2, tapEvent.getStartY());
    Assert.assertSame(hasHandlers, tapEvent.getSource());
  }

  @Test
  public void testSimpleTouchStartWithMoveAndEnd1() {

    tapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    tapRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(1 + distance, 2 + distance));
    tapRecognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());
    GwtEvent<?> event = eventCaptor.getValue();
    TapEvent tapEvent = (TapEvent) event;
    Assert.assertEquals(1, tapEvent.getStartX());
    Assert.assertEquals(2, tapEvent.getStartY());
    Assert.assertSame(hasHandlers, tapEvent.getSource());
  }

  @Test
  public void testSimpleTouchStartWithMoveAndEnd2() {
    tapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    tapRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(1 - distance, 2 - distance));
    tapRecognizer.onTouchEnd(mock(TouchEndEvent.class));

    verify(hasHandlers).fireEvent(eventCaptor.capture());
    GwtEvent<?> event = eventCaptor.getValue();
    TapEvent tapEvent = (TapEvent) event;
    Assert.assertEquals(1, tapEvent.getStartX());
    Assert.assertEquals(2, tapEvent.getStartY());
    Assert.assertSame(hasHandlers, tapEvent.getSource());
  }

  @Test
  public void testSimpleTouchStartWithMoveAndEndNoTapRcognized() {
    tapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    tapRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(2 + distance, 3 + distance));
    tapRecognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);
  }

  @Test
  public void testSimpleTouchStartWithMoveAndEndNoTapRcognized1() {
    tapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    tapRecognizer.onTouchMove(TouchTestUtil
        .createTouchMoveEvent(1 - distance - 1, 2 - distance - 1));
    tapRecognizer.onTouchEnd(mock(TouchEndEvent.class));
    verifyNoMoreInteractions(hasHandlers);
  }

  @Test
  public void testFail() {
    Assert.fail("fail the jenkins build");
  }
}
