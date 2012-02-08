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

import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.collection.shared.java.JavaLightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;

public class MockTouchStartEvent extends TouchStartEvent {

	private final int x;
	private final int y;

	public MockTouchStartEvent(int x, int y) {
		this.x = x;
		this.y = y;

	}

	@Override
	public LightArray<Touch> getTouches() {
		JavaLightArray<Touch> array = new JavaLightArray<Touch>();

		array.push(new MockTouch(x, y));

		return array;
	}

}
