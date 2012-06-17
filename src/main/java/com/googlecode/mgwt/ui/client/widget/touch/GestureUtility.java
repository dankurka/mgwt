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
package com.googlecode.mgwt.ui.client.widget.touch;

import com.google.gwt.user.client.ui.UIObject;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.recognizer.TapRecognizer;
import com.googlecode.mgwt.dom.client.recognizer.longtap.LongTapRecognizer;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchRecognizer;
import com.googlecode.mgwt.dom.client.recognizer.pinch.UIObjectToOffsetProvider;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeRecognizer;

/**
 * {@link GestureUtility} is a utility class for adding standard recognizers to a widget
 * 
 * @author Daniel Kurka
 * 
 */
public class GestureUtility {
	private TapRecognizer tapRecognizer;
	private final HasTouchHandlers source;
	private LongTapRecognizer longTapRecognizer;
	private SwipeRecognizer swipeRecognizer;
	private PinchRecognizer pinchRecognizer;

  /**
   * Construct a {@link GestureUtility} for a given source
   * 
   * @param source the source to use
   */
	public GestureUtility(HasTouchHandlers source) {
		assert source != null;
		this.source = source;
	}

  /**
   * ensure that there is a registered {@link TapRecognizer} on the source
   */
	public void ensureTapRecognizer() {
		if (tapRecognizer != null)
			return;

		tapRecognizer = new TapRecognizer(source);
		source.addTouchHandler(tapRecognizer);
	}

  /**
   * ensure that there is a registered {@link LongTapRecognizer} on the source
   */
	public void ensureLongTapRecognizer() {
		if (longTapRecognizer != null) {
			return;
		}

		longTapRecognizer = new LongTapRecognizer(source);
		source.addTouchHandler(longTapRecognizer);

	}

  /**
   * ensure that there is a registered {@link SwipeRecognizer} on the source
   */
	public void ensureSwipeRecognizer() {
		if (swipeRecognizer != null) {
			return;
		}

		swipeRecognizer = new SwipeRecognizer(source);
		source.addTouchHandler(swipeRecognizer);

	}

  /**
   * ensure that there is a registered {@link PinchRecognizer} on the source
   * 
   * @param object the {@link UIObject} that is used for offset
   * 
   */
	public void ensurePinchRecognizer(UIObject object) {
		if (pinchRecognizer != null) {
			return;
		}

		pinchRecognizer = new PinchRecognizer(source, new UIObjectToOffsetProvider(object));
		source.addTouchHandler(pinchRecognizer);

	}

  /**
   * ensure that there is a registered {@link LongTapRecognizer} on the source
   */
	public void ensureLongTapHandler() {
		if (longTapRecognizer != null) {
			return;
		}

		longTapRecognizer = new LongTapRecognizer(source);
		source.addTouchHandler(longTapRecognizer);

	}
}
