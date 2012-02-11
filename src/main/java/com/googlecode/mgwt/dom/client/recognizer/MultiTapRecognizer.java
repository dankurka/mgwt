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
package com.googlecode.mgwt.dom.client.recognizer;

import com.google.gwt.event.shared.HasHandlers;
import com.googlecode.mgwt.collection.shared.CollectionFactory;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;

public class MultiTapRecognizer implements TouchHandler {

	public static final int DEFAULT_DISTANCE = 15;
	public static final int DEFAULT_TIME_IN_MS = 300;

	private final HasHandlers source;
	private final int distance;
	private final int time;
	private final int numberOfTabs;

	private int touchCount;
	private boolean wasCanceled;

	private LightArray<Touch> touches;

	public MultiTapRecognizer(HasHandlers source, int numberOfTabs) {
		this(source, numberOfTabs, DEFAULT_DISTANCE, DEFAULT_TIME_IN_MS);
	}

	public MultiTapRecognizer(HasHandlers source, int numberOfTabs, int distance) {
		this(source, numberOfTabs, distance, DEFAULT_TIME_IN_MS);
	}

	public MultiTapRecognizer(HasHandlers source, int numberOfTabs, int distance, int time) {
		if (source == null)
			throw new IllegalArgumentException("source can not be null");

		if (numberOfTabs < 1) {
			throw new IllegalArgumentException("numberOfTabs > 0");
		}

		if (distance < 0)
			throw new IllegalArgumentException("distance > 0");

		if (time < 1) {
			throw new IllegalArgumentException("time > 0");
		}
		this.source = source;
		this.numberOfTabs = numberOfTabs;
		this.distance = distance;
		this.time = time;
		touchCount = 0;
		touches = CollectionFactory.constructArray();

	}

	@Override
	public void onTouchStart(TouchStartEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchMove(TouchMoveEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchCanceled(TouchCancelEvent event) {
		touchCount = 0;
		wasCanceled = true;

	}

}
