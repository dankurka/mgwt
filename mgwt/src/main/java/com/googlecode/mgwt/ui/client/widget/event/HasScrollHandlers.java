/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.event;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * widgets that implement this interface fire {@link ScrollStartEvent},
 * {@link ScrollHandler} and {@link ScrollEndEvent} events.
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public interface HasScrollHandlers {
	/**
	 * Adds a {@link ScrollStartHandler} handler.
	 * 
	 * @param handler the handler to add
	 * @return {@link HandlerRegistration} used to remove this handler
	 */
	public HandlerRegistration addScrollStartHandler(ScrollStartHandler handler);

	/**
	 * Adds a {@link ScrollHandler} handler.
	 * 
	 * @return {@link HandlerRegistration} used to remove this handler
	 * @param handler a
	 *            {@link com.googlecode.mgwt.ui.client.widget.event.ScrollHandler}
	 *            object.
	 */
	public HandlerRegistration addScrollhandler(ScrollHandler handler);

	/**
	 * Adds a {@link ScrollEndHandler} handler.
	 * 
	 * @param handler the handler to add
	 * @return {@link HandlerRegistration} used to remove this handler
	 */
	public HandlerRegistration addScrollEndHandler(ScrollEndHandler handler);
}
