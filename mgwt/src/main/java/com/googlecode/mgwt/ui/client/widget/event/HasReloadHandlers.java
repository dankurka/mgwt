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
 * widgets that implement this interface fire {@link ReloadEvent} events.
 * 
 * @author Daniel Kurka
 * 
 */
public interface HasReloadHandlers {
	/**
	 * Adds a {@link ReloadHandler} handler.
	 * 
	 * @param handler the handler to add
	 * @return {@link HandlerRegistration} used to remove this handler
	 */
	public HandlerRegistration addReloadHandler(ReloadHandler handler);

}
