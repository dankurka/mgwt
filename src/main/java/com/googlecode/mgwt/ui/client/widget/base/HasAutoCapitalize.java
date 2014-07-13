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
package com.googlecode.mgwt.ui.client.widget.base;

/**
 * Widgets that implement this interface can capitalize the beginning of words
 *
 * @author Daniel Kurka
 */
public interface HasAutoCapitalize {
	/**
	 * Should the widget capitalize every word
	 *
	 * @param capitalize true to capitalize
	 */
	void setAutoCapitalize(boolean capitalize);

	/**
	 * Should the widget capitalize every word
	 *
	 * @return true to capitalize, otherwise false
	 */
	boolean isAutoCapitalize();
}
