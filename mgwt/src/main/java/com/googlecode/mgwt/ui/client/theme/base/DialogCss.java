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
package com.googlecode.mgwt.ui.client.theme.base;

/**
 * <p>DialogCss interface.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public interface DialogCss extends ButtonBaseCss {

	/**
	 * <p>getDialogPanel</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-DialogPanel")
	String getDialogPanel();

	/**
	 * <p>getBottomPanel</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-BottomPanel")
	String getBottomPanel();

	/**
	 * <p>container</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String container();

	/**
	 * <p>title</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String title();

	/**
	 * <p>content</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String content();

	/**
	 * <p>footer</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String footer();

	/**
	 * <p>okbutton</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String okbutton();

	/**
	 * <p>cancelbutton</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String cancelbutton();

	/**
	 * <p>active</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String active();

	/**
	 * <p>animationContainer</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-DialogAnimationContainer")
	String animationContainer();

	/**
	 * <p>animationContainerShadow</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-DialogAnimationContainer-Shadow")
	String animationContainerShadow();

	/**
	 * <p>z_index</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String z_index();

	/**
	 * <p>animationContainerCenter</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@ClassName("mgwt-DialogAnimationContainer-center")
	String animationContainerCenter();

}
