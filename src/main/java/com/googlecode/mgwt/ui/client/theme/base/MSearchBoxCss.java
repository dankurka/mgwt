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
 * <p>MSearchBoxCss interface.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public interface MSearchBoxCss extends CssResource {

	/**
	 * <p>searchBox</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-SearchBox")
	public String searchBox();

	/**
	 * <p>round</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String round();

	/**
	 * <p>clear</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String clear();

	/**
	 * <p>clearActive</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("clear-active")
	public String clearActive();

	/**
	 * <p>input</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String input();
}
