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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * This is a convenience interface that includes all touch handlers defined by
 * mgwt.
 * 
 * @author kurt
 * @version $Id: $
 */
public interface HasTouchHandlers extends HasHandlers {
	/**
	 * <p>
	 * addTouchStartHandler
	 * </p>
	 * 
	 * @param handler a
	 *            {@link com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler}
	 *            object.
	 * @return a {@link com.google.gwt.event.shared.HandlerRegistration} object.
	 */
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler);

	/**
	 * <p>
	 * addTouchMoveHandler
	 * </p>
	 * 
	 * @param handler a
	 *            {@link com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler}
	 *            object.
	 * @return a {@link com.google.gwt.event.shared.HandlerRegistration} object.
	 */
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler);

	/**
	 * <p>
	 * addTouchCancelHandler
	 * </p>
	 * 
	 * @param handler a
	 *            {@link com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler}
	 *            object.
	 * @return a {@link com.google.gwt.event.shared.HandlerRegistration} object.
	 */
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler);

	/**
	 * <p>
	 * addTouchEndHandler
	 * </p>
	 * 
	 * @param handler a
	 *            {@link com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler}
	 *            object.
	 * @return a {@link com.google.gwt.event.shared.HandlerRegistration} object.
	 */
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler);

	/**
	 * <p>
	 * addTouchHandler
	 * </p>
	 * 
	 * @param handler a
	 *            {@link com.googlecode.mgwt.dom.client.event.touch.TouchHandler}
	 *            object.
	 * @return a {@link com.google.gwt.event.shared.HandlerRegistration} object.
	 */
	public HandlerRegistration addTouchHandler(TouchHandler handler);
}
