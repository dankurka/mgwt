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
package com.googlecode.mgwt.mvp.client.resources;

import com.google.gwt.resources.client.CssResource;
import com.googlecode.mgwt.mvp.client.AnimationNames;

/**
 * Considered internal
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public interface TransistionCss extends CssResource, AnimationNames {

	/**
	 * <p>display</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String display();

	/**
	 * <p>displayContainer</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String displayContainer();

	/**
	 * <p>reverse</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String reverse();

	/**
	 * <p>in</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String in();

	/**
	 * <p>out</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String out();

	/**
	 * <p>start</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String start();

	/**
	 * <p>end</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String end();

	/**
	 * <p>slide</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	String slide();

}
