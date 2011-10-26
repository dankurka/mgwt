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
package com.googlecode.mgwt.dom.client.event.touch;

import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * This is a convenience interface that includes all touch handlers defined by
 * mgwt.
 */
public interface HasTouchHandlers {
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler);

	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler);

	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler);

	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler);

	public HandlerRegistration addTouchHandler(TouchHandler handler);
}
