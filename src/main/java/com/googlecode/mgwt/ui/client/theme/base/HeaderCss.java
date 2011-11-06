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

/**
 * <p>HeaderCss interface.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public interface HeaderCss extends ButtonBaseCss {

	/**
	 * <p>headerButton</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-HeaderButton")
	public String headerButton();

	/**
	 * <p>active</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String active();

	/**
	 * <p>back</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String back();

	/**
	 * <p>forward</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String forward();

	/**
	 * <p>round</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String round();

	/**
	 * <p>headerPanel</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-HeaderPanel")
	String headerPanel();

	/**
	 * <p>left</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-HeaderPanel-left")
	String left();

	/**
	 * <p>center</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-HeaderPanel-center")
	String center();

	/**
	 * <p>right</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-HeaderPanel-right")
	String right();

	/**
	 * <p>main</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-DropDownMenu")
	public String main();

	/**
	 * <p>content</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-DropDownMenu-content")
	public String content();

	/**
	 * <p>arrow</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-DropDownMenu-arrow")
	public String arrow();
}
