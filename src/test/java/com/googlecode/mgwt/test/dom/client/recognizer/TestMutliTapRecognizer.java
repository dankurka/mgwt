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
import com.googlecode.mgwt.dom.client.recognizer.MultiTapEvent;
import com.googlecode.mgwt.dom.client.recognizer.MultiTapRecognizer;

public class TestMutliTapRecognizer {

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

}
