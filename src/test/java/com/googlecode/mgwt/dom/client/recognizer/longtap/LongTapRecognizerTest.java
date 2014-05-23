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
package com.googlecode.mgwt.dom.client.recognizer.longtap;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.googlecode.mgwt.dom.client.recognizer.EventPropagator;
import com.googlecode.mgwt.dom.client.recognizer.TimerExecutor;
import com.googlecode.mgwt.dom.client.recognizer.TimerExecutor.CodeToRun;
import com.googlecode.mgwt.helper.TouchTestUtil;

import static org.mockito.Matchers.eq;
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
public class LongTapRecognizerTest {

  @Mock HasHandlers handlers;
  @Mock EventPropagator eventPropagator;
  @Mock TimerExecutor timerExecutor;

  @Captor ArgumentCaptor<CodeToRun> codeToRunCaptor;
  @Captor ArgumentCaptor<GwtEvent<?>> eventCaptor;
  @Captor ArgumentCaptor<HasHandlers> hasHandlersCaptor;

  private LongTapRecognizer longTapRecognizer;

  @Before
  public void before() {
    longTapRecognizer = new LongTapRecognizer(handlers) {
      @Override
      EventPropagator getEventPropagator() {
        return eventPropagator;
      }
      @Override
      TimerExecutor getTimerExecutor() {
        return timerExecutor;
      }
    };
  }

  @Test
  public void testLongTapRecognizerHasHandlers() {
    try {
      new LongTapRecognizer(null);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testLongTapRecognizerHasHandlersInt() {
    try {
      new LongTapRecognizer(handlers, 0);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testLongTapRecognizerHasHandlersIntInt1() {
    try {
      new LongTapRecognizer(handlers, 1, -1);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testSimpleLongTouch() {

    longTapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    verify(timerExecutor).execute(codeToRunCaptor.capture(), eq(1500));
    // simulate wait...
    codeToRunCaptor.getValue().onExecution();

    verify(eventPropagator).fireEvent(hasHandlersCaptor.capture(), eventCaptor.capture());
    GwtEvent<?> event = eventCaptor.getValue();
    LongTapEvent tapEvent = (LongTapEvent) event;
    Assert.assertEquals(1, tapEvent.getStartPositions().get(0).getPageX());
    Assert.assertEquals(2, tapEvent.getStartPositions().get(0).getPageY());
    Assert.assertSame(handlers, tapEvent.getSource());
    Assert.assertSame(handlers, hasHandlersCaptor.getValue());
  }

  @Test
  public void testSimpleLongTouchWithTwoFingers() {
    longTapRecognizer = new LongTapRecognizer(handlers, 2) {
      @Override
      EventPropagator getEventPropagator() {
        return eventPropagator;
      }
      @Override
      TimerExecutor getTimerExecutor() {
        return timerExecutor;
      }
    };

    longTapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(2, 3));
    longTapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(new int[] {2, 3, 4, 5}));

    verify(timerExecutor).execute(codeToRunCaptor.capture(), eq(1500));
    // simulate wait...
    codeToRunCaptor.getValue().onExecution();

    verify(eventPropagator).fireEvent(hasHandlersCaptor.capture(), eventCaptor.capture());
    GwtEvent<?> event = eventCaptor.getValue();
    LongTapEvent tapEvent = (LongTapEvent) event;
    Assert.assertEquals(2, tapEvent.getStartPositions().get(0).getPageX());
    Assert.assertEquals(3, tapEvent.getStartPositions().get(0).getPageY());
    Assert.assertEquals(4, tapEvent.getStartPositions().get(1).getPageX());
    Assert.assertEquals(5, tapEvent.getStartPositions().get(1).getPageY());
    Assert.assertSame(handlers, tapEvent.getSource());
    Assert.assertSame(handlers, hasHandlersCaptor.getValue());
  }

  @Test
  public void testSimpleLongTouchWithBigMoveNotFiring() {
    longTapRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(1, 2));
    longTapRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(20, 50));
    verify(timerExecutor).execute(codeToRunCaptor.capture(), eq(1500));
    // simulate wait...
    codeToRunCaptor.getValue().onExecution();

    verifyNoMoreInteractions(eventPropagator);
  }
}
