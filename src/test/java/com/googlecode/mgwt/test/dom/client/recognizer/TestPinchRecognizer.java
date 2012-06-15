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
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.collection.shared.java.JavaLightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.recognizer.EventPropagator;
import com.googlecode.mgwt.dom.client.recognizer.pinch.OffsetProvider;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchEvent;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchRecognizer;

public class TestPinchRecognizer {

	private static class PinchRecognizerForTest extends PinchRecognizer {

		public PinchRecognizerForTest(HasHandlers source, OffsetProvider offsetProvider) {
			super(source, offsetProvider);
		}

		@Override
		public void setEventPropagator(EventPropagator eventPropagator) {
			super.setEventPropagator(eventPropagator);
		}

	}

	private MockHasHandlers hasHandlers;
	private PinchRecognizerForTest pinchRecognizer;

	@Before
	public void setUp() throws Exception {
		hasHandlers = new MockHasHandlers();

		pinchRecognizer = new PinchRecognizerForTest(hasHandlers, new OffsetProvider() {

			@Override
			public int getTop() {
				return 0;
			}

			@Override
			public int getLeft() {
				return 0;
			}
		});
		pinchRecognizer.setEventPropagator(new EventPropagatorForTests());

	}

	@Test
	public void testPinchRecognizer() {
		try {
			new PinchRecognizer(null, new OffsetProvider() {

				@Override
				public int getTop() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int getLeft() {
					// TODO Auto-generated method stub
					return 0;
				}
			});
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testPinchRecognizer1() {
		try {
			new PinchRecognizer(hasHandlers, null);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testSimplePinch() {
		pinchRecognizer.onTouchStart(new MockTouchStartEvent(1, 0, 0));

		pinchRecognizer.onTouchStart(new MockTouchStartEvent(2, 100, 100) {
			@Override
			public LightArray<Touch> getTouches() {
				JavaLightArray<Touch> array = new JavaLightArray<Touch>();

				array.push(new MockTouch(1, 0, 0));
				array.push(new MockTouch(2, 100, 100));

				return array;
			}
		});

		pinchRecognizer.onTouchMove(new MockTouchMoveEvent(1, 0, 0) {
			@Override
			public LightArray<Touch> getTouches() {
				JavaLightArray<Touch> array = new JavaLightArray<Touch>();

				array.push(new MockTouch(1, 0, 0));
				array.push(new MockTouch(2, 50, 50));

				return array;
			}
		});

		GwtEvent<?> event = hasHandlers.getEvent();

		if (!(event instanceof PinchEvent)) {
			Assert.fail("no pinch event");
		}
		PinchEvent pinchEvent = (PinchEvent) event;

		Assert.assertEquals(25, pinchEvent.getX());
		Assert.assertEquals(25, pinchEvent.getY());

		Assert.assertEquals(1.41421356237309, pinchEvent.getScaleFactor(), 0.0001);

	}
}
