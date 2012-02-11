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

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.googlecode.mgwt.dom.client.recognizer.MultiTapRecognizer;

public class TestMutliTapRecognizer {

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
			new MultiTapRecognizer(new MockHasHandlers(), 1, 1, 0);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testMultiTapRecognizerHasHandlersIntInt() {
		new MultiTapRecognizer(new MockHasHandlers(), 1, 1, 1);
	}

	@Test
	public void testOnTouchCanceled() {
		fail("Not yet implemented");
	}

}
