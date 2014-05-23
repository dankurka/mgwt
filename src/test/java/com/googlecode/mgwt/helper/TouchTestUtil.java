package com.googlecode.mgwt.helper;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;

public class TouchTestUtil {

  public static TouchStartEvent createTouchStartEvent(int x, int y) {
    TouchStartEvent touchStartEvent = mock(TouchStartEvent.class);
    setupTouchEvenMock(touchStartEvent, new int[] {x, y});
    return touchStartEvent;
  }

  public static TouchStartEvent createTouchStartEvent(int[] positions) {
    TouchStartEvent touchStartEvent = mock(TouchStartEvent.class);
    setupTouchEvenMock(touchStartEvent, positions);
    return touchStartEvent;
  }


  public static TouchMoveEvent createTouchMoveEvent(int x, int y) {
    TouchMoveEvent touchStartEvent = mock(TouchMoveEvent.class);
    setupTouchEvenMock(touchStartEvent, new int[] {x, y});
    return touchStartEvent;
  }

  public static TouchMoveEvent createTouchMoveEvent(int[] positions) {
    TouchMoveEvent touchStartEvent = mock(TouchMoveEvent.class);
    setupTouchEvenMock(touchStartEvent, positions);
    return touchStartEvent;
  }


  private static void setupTouchEvenMock(TouchEvent<?> event, int[] positions) {
    Assert.assertTrue(positions.length % 2 == 0);
    @SuppressWarnings("unchecked")
    JsArray<Touch> touchArray = mock(JsArray.class);
    when(event.getTouches()).thenReturn(touchArray);
    int count = 0;
    for(; count < positions.length; count +=2) {
      Touch touch = mock(Touch.class);
      when(touchArray.get(count / 2)).thenReturn(touch);
      when(touch.getPageX()).thenReturn(positions[count]);
      when(touch.getPageY()).thenReturn(positions[count + 1]);
    }
    when(touchArray.length()).thenReturn(count / 2);
  }

  public static TouchEndEvent createTouchEndEvent() {
    return createTouchEndEvent(new int[]{});
  }

  public static TouchEndEvent createTouchEndEvent(int[] positions) {
    TouchEndEvent touchEndEvent = mock(TouchEndEvent.class);
    setupTouchEvenMock(touchEndEvent, positions);
    return touchEndEvent;
  }
}
