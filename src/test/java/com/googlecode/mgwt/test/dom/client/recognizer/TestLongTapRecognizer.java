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
import com.googlecode.mgwt.dom.client.recognizer.TimerExecutor;
import com.googlecode.mgwt.dom.client.recognizer.TimerExecutor.CodeToRun;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapEvent;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapRecognizer;

public class TestLongTapRecognizer {

	private static class LongTapRecognizerForTest extends LongTapRecognizer {

		public LongTapRecognizerForTest(HasHandlers source) {
			super(source);

		}

		public LongTapRecognizerForTest(HasHandlers source, int fingers) {
			super(source, fingers);
		}

		@Override
		public void setEventPropagator(EventPropagator eventPropagator) {
			super.setEventPropagator(eventPropagator);
		}

		@Override
		public void setTimerExecutor(TimerExecutor timerExecutor) {
			super.setTimerExecutor(timerExecutor);
		}

	}

	private MockHasHandlers handlers;
	private LongTapRecognizerForTest longTapRecognizer;

	@Before
	public void before() {
		handlers = new MockHasHandlers();
		longTapRecognizer = new LongTapRecognizerForTest(handlers);
		longTapRecognizer.setEventPropagator(new EventPropagatorForTests());
	}

	@Test
	public void testLongTapRecognizerHasHandlers() {
		try {
			new LongTapRecognizer(null);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testLongTapRecognizerHasHandlersInt() {
		try {
			new LongTapRecognizer(handlers, 0);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testLongTapRecognizerHasHandlersIntInt() {
		try {
			new LongTapRecognizer(handlers, 1, 199);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testLongTapRecognizerHasHandlersIntInt1() {
		try {
			new LongTapRecognizer(handlers, 1, -1);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testLongTapRecognizerHasHandlersIntInt2() {
		try {
			new LongTapRecognizer(handlers, 1, 1, 0);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testContruction() {
		new LongTapRecognizer(handlers);
	}

	private CodeToRun codeToRun;

	@Test
	public void testSimpleLongTouch() {

		longTapRecognizer.setTimerExecutor(new TimerExecutor() {

			@Override
			public void execute(CodeToRun codeToRun, int time) {
				TestLongTapRecognizer.this.codeToRun = codeToRun;

			}
		});

		longTapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2, 3));

		//simulate wait...
		codeToRun.onExecution();

		GwtEvent<?> event = handlers.getEvent();

		if (!(event instanceof LongTapEvent)) {
			Assert.fail("no longtap recognized");
		}
		LongTapEvent tapEvent = (LongTapEvent) event;

		Assert.assertEquals(2, tapEvent.getStartPositions().get(0).getPageX());
		Assert.assertEquals(3, tapEvent.getStartPositions().get(0).getPageY());

		Assert.assertSame(handlers, tapEvent.getSource());

	}

	@Test
	public void testSimpleLongTouchWithTwoFingers() {

		LongTapRecognizerForTest longTapRecognizer2 = new LongTapRecognizerForTest(handlers, 2);
		longTapRecognizer2.setEventPropagator(new EventPropagatorForTests());

		longTapRecognizer2.setTimerExecutor(new TimerExecutor() {

			@Override
			public void execute(CodeToRun codeToRun, int time) {
				TestLongTapRecognizer.this.codeToRun = codeToRun;

			}
		});

		longTapRecognizer2.onTouchStart(new MockTouchStartEvent(1, 2, 3));
		longTapRecognizer2.onTouchStart(new MockTouchStartEvent(2, 4, 5) {
			@Override
			public LightArray<Touch> getTouches() {
				JavaLightArray<Touch> array = new JavaLightArray<Touch>();

				array.push(new MockTouch(1, 2, 3));
				array.push(new MockTouch(2, 4, 5));

				return array;
			}
		});

		//simulate wait...
		codeToRun.onExecution();

		GwtEvent<?> event = handlers.getEvent();

		if (!(event instanceof LongTapEvent)) {
			Assert.fail("no longtap recognized");
		}
		LongTapEvent tapEvent = (LongTapEvent) event;

		Assert.assertEquals(2, tapEvent.getStartPositions().get(0).getPageX());
		Assert.assertEquals(3, tapEvent.getStartPositions().get(0).getPageY());

		Assert.assertEquals(4, tapEvent.getStartPositions().get(1).getPageX());
		Assert.assertEquals(5, tapEvent.getStartPositions().get(1).getPageY());

		Assert.assertSame(handlers, tapEvent.getSource());

	}

	@Test
	public void testSimpleLongTouchWithBigMoveNotFiring() {

		longTapRecognizer.setTimerExecutor(new TimerExecutor() {

			@Override
			public void execute(CodeToRun codeToRun, int time) {
				TestLongTapRecognizer.this.codeToRun = codeToRun;

			}
		});

		longTapRecognizer.onTouchStart(new MockTouchStartEvent(1, 2, 3));

		longTapRecognizer.onTouchMove(new MockTouchMoveEvent(1, 20, 50));

		//simulate wait...
		codeToRun.onExecution();

		GwtEvent<?> event = handlers.getEvent();

		Assert.assertNull(event);

	}

}
