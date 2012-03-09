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

import com.googlecode.mgwt.dom.client.recognizer.LongTapRecognizer;

public class TestLongTapRecognizer {

	private MockHasHandlers handlers;

	@Before
	public void before() {
		handlers = new MockHasHandlers();
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
	public void testContruction() {
		new LongTapRecognizer(handlers);
	}

	@Test
	public void testOnTouchMove() {
		
	}

	@Test
	public void testOnTouchEnd() {
		
	}

	@Test
	public void testOnTouchCanceled() {
		
	}

}
