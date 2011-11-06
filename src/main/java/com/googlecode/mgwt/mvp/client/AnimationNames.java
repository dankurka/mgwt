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
package com.googlecode.mgwt.mvp.client;

/**
 * Css Representation for all animations. This is considered internal atm.
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public interface AnimationNames {
	/**
	 * <p>fade</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String fade();

	/**
	 * <p>pop</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String pop();

	/**
	 * <p>swap</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String swap();

	/**
	 * <p>slide</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String slide();

	/**
	 * <p>slideup</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String slideup();

	/**
	 * <p>flip</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String flip();

	/**
	 * <p>dissolve</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String dissolve();
}
