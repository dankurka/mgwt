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
package com.googlecode.mgwt.dom.client.recognizer.tap;

import com.google.gwt.event.shared.EventHandler;

/**
 * A {@link MultiTapHandler} receives {@link MultiTapEvent}s
 * 
 * @author Daniel Kurka
 * 
 */
public interface MultiTapHandler extends EventHandler {
	/**
	 * called when an {@link MultiTapEvent} occurs.
	 * 
	 * @param event the event
	 */
	public void onMultiTap(MultiTapEvent event);
}
