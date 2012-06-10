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

import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.recognizer.LongTapRecognizer;
import com.googlecode.mgwt.dom.client.recognizer.TapRecognizer;

public class GestureUtility {
	private TapRecognizer tapRecognizer;
	private final HasTouchHandlers source;
	private LongTapRecognizer longTapRecognizer;

	public GestureUtility(HasTouchHandlers source) {
		assert source != null;
		this.source = source;
	}

	public void ensureTapRecognizer() {
		if (tapRecognizer != null)
			return;

		tapRecognizer = new TapRecognizer(source);
		source.addTouchHandler(tapRecognizer);
	}

	public void ensureLongTapRecognizer() {
		if (longTapRecognizer != null) {
			return;
		}

		longTapRecognizer = new LongTapRecognizer(source);
		source.addTouchHandler(longTapRecognizer);

	}
}
