/*
 * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.mvp.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link MGWTAnimationEndEvent} events:
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public interface MGWTAnimationEndHandler extends EventHandler {
	/**
	 * Called when a mgwt animation end event is fired.
	 * 
	 * @param event the {@link MGWTAnimationEndEvent} that was fired
	 */
	public void onAnimationEnd(MGWTAnimationEndEvent event);
}
