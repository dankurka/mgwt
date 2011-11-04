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
package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

/**
 * <p>ScrollPanelCss interface.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public interface ScrollPanelCss extends CssResource {

	/**
	 * <p>scrollPanel</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-ScrollPanel")
	String scrollPanel();

	/**
	 * <p>container</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("container")
	String container();

	/**
	 * <p>scrollBar</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-Scrollbar")
	String scrollBar();

	/**
	 * <p>horizontal</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("horizontal")
	String horizontal();

	/**
	 * <p>vertical</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("vertical")
	String vertical();

	/**
	 * <p>scrollBarBar</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-Scrollbar-Bar")
	String scrollBarBar();

}
