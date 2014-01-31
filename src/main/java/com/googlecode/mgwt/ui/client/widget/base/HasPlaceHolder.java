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
package com.googlecode.mgwt.ui.client.widget.base;

/**
 * Widgets that implement this interface can display a text when they have no
 * text set
 *
 * @author Daniel Kurka
 */
public interface HasPlaceHolder {

	/**
	 * set a place holder
	 *
	 * @param text the text to display when empty
	 */
	public void setPlaceHolder(String text);

	/**
	 * get the place holder
	 *
	 * @return the text of the place holder
	 */
	public String getPlaceHolder();

}
