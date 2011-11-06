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
package com.googlecode.mgwt.dom.client.event.tap;

/**
 * Tap is considered for elements that offer something like a normal
 * "click event". Like a button.
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class Tap {
	/**
	 * The radius that a finger can move before the touch is not considered a
	 * simple touch anymore
	 */
	public static final int RADIUS = 10;
}
