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
package com.googlecode.mgwt.dom.client.recognizer.pinch;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.googlecode.mgwt.dom.client.recognizer.EventPropagator;
import com.googlecode.mgwt.helper.TouchTestUtil;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

@RunWith(GwtMockitoTestRunner.class)
public class PinchRecognizerTest {

  @Mock
  HasHandlers hasHandlers;
  @Mock
  EventPropagator eventPropagator;

  @Captor
  ArgumentCaptor<GwtEvent<?>> eventCaptor;
  @Captor
  ArgumentCaptor<HasHandlers> hasHandlersCaptor;

	private PinchRecognizer pinchRecognizer;

	@Before
	public void setUp() throws Exception {
		pinchRecognizer = new PinchRecognizer(hasHandlers, mock(OffsetProvider.class)) {
		  @Override
		  EventPropagator getEventPropagator() {
		    return eventPropagator;
		  }
		};
	}

	@Test
	public void testSimplePinch() {
		pinchRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(0, 0));
		pinchRecognizer.onTouchStart(TouchTestUtil.createTouchStartEvent(new int[]{0,0, 200, 200}));

		pinchRecognizer.onTouchMove(TouchTestUtil.createTouchMoveEvent(new int[]{0,0, 50, 50}));

		verify(eventPropagator).fireEvent(eq(hasHandlers), eventCaptor.capture());

		GwtEvent<?> event = eventCaptor.getValue();

		if (!(event instanceof PinchEvent)) {
			Assert.fail("no pinch event");
		}
		PinchEvent pinchEvent = (PinchEvent) event;

		Assert.assertEquals(25, pinchEvent.getX());
		Assert.assertEquals(25, pinchEvent.getY());

		// TODO
		//Assert.assertEquals(1.41421356237309, pinchEvent.getScaleFactor(), 0.0001);
	}
}
