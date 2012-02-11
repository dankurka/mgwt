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
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.recognizer.TapRecognizer;

public class TapRecognizerTest {
	private static final int distance = 10;
	private MockHasHandlers mockHasHandlers;
	private TapRecognizer tapRecognizer;

	@Before
	public void before() {
		mockHasHandlers = new MockHasHandlers();
		tapRecognizer = new TapRecognizer(mockHasHandlers, distance);
	}

	@Test
	public void testTapRecognizerHasHandlers() {
		try {
			new TapRecognizer(null);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testTapRecognizerHasHandlersInt() {
		try {
			new TapRecognizer(new MockHasHandlers(), -1);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testTapRecognizerDistance() {
		Assert.assertEquals(distance, tapRecognizer.getDistance());
	}

	@Test
	public void testTapRecognizerDistance1() {
		new TapRecognizer(mockHasHandlers);
	}

	@Test
	public void testSimpleTouchStartAndEnd() {
		tapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2));
		tapRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = mockHasHandlers.getEvent();

		if (!(event instanceof TapEvent)) {
			Assert.fail("no tap recognized");
		}
		TapEvent tapEvent = (TapEvent) event;

		Assert.assertEquals(1, tapEvent.getStartX());
		Assert.assertEquals(2, tapEvent.getStartY());

		Assert.assertSame(mockHasHandlers, tapEvent.getSource());

	}

	@Test
	public void testSimpleTouchStartAndCancel() {
		tapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2));
		tapRecognizer.onTouchCanceled(new MockTouchCancelEvent());

		GwtEvent<?> event = mockHasHandlers.getEvent();

		Assert.assertNull(event);

	}

	@Test
	public void testSimpleTouchStartWithMoveAndEnd() {
		tapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2));
		tapRecognizer.onTouchMove(new MockTouchMoveEvent(2, 3));
		tapRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = mockHasHandlers.getEvent();

		if (!(event instanceof TapEvent)) {
			Assert.fail("no tap recognized");
		}
		TapEvent tapEvent = (TapEvent) event;

		Assert.assertEquals(1, tapEvent.getStartX());
		Assert.assertEquals(2, tapEvent.getStartY());

		Assert.assertSame(mockHasHandlers, tapEvent.getSource());

	}

	@Test
	public void testSimpleTouchStartWithMoveAndEnd1() {
		tapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2));
		tapRecognizer.onTouchMove(new MockTouchMoveEvent(1 + distance, 2 + distance));
		tapRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = mockHasHandlers.getEvent();

		if (!(event instanceof TapEvent)) {
			Assert.fail("no tap recognized");
		}
		TapEvent tapEvent = (TapEvent) event;

		Assert.assertEquals(1, tapEvent.getStartX());
		Assert.assertEquals(2, tapEvent.getStartY());

		Assert.assertSame(mockHasHandlers, tapEvent.getSource());

	}

	@Test
	public void testSimpleTouchStartWithMoveAndEnd2() {
		tapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2));
		tapRecognizer.onTouchMove(new MockTouchMoveEvent(1 - distance, 2 - distance));
		tapRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = mockHasHandlers.getEvent();

		if (!(event instanceof TapEvent)) {
			Assert.fail("no tap recognized");
		}
		TapEvent tapEvent = (TapEvent) event;

		Assert.assertEquals(1, tapEvent.getStartX());
		Assert.assertEquals(2, tapEvent.getStartY());

		Assert.assertSame(mockHasHandlers, tapEvent.getSource());

	}

	@Test
	public void testSimpleTouchStartWithMoveAndEndNoTapRcognized() {
		tapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2));
		tapRecognizer.onTouchMove(new MockTouchMoveEvent(1 + distance + 1, 2 + distance + 1));
		tapRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = mockHasHandlers.getEvent();

		Assert.assertNull(event);

	}

	@Test
	public void testSimpleTouchStartWithMoveAndEndNoTapRcognized1() {
		tapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2));
		tapRecognizer.onTouchMove(new MockTouchMoveEvent(1 - distance - 1, 2 - distance - 1));
		tapRecognizer.onTouchEnd(new MockTouchEndEvent());

		GwtEvent<?> event = mockHasHandlers.getEvent();

		Assert.assertNull(event);

	}

}
