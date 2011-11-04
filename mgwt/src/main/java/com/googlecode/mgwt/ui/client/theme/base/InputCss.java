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
 * <p>InputCss interface.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public interface InputCss extends CssResource {

	/**
	 * <p>cover</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String cover();

	/**
	 * <p>textBox</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-TextBox")
	String textBox();

	/**
	 * <p>textArea</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-TextArea")
	String textArea();

	/**
	 * <p>passwordBox</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-PasswordTextBox")
	String passwordBox();

	/**
	 * <p>radioButton</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-RadioButton")
	String radioButton();

	/**
	 * <p>box</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String box();

	/**
	 * <p>disabled</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String disabled();

	/**
	 * <p>listBox</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-ListBox")
	String listBox();

}
