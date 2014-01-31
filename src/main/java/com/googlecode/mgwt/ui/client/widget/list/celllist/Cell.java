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
package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A Cell represents a small amount of html when rendered
 * 
 * @author Daniel Kurka
 */
public interface Cell<T> {
	/**
	 * Called when the cell needs to render itself
	 * 
	 * @param safeHtmlBuilder the builder to append the html of the cell to
	 * @param model the current model for the cell
	 */
	public void render(SafeHtmlBuilder safeHtmlBuilder, T model);

	/**
	 * Can this cell be selected
	 * 
	 * @param model the model of this cell
	 * @return true if the cell can be selected, otherwise false
	 */
	public boolean canBeSelected(T model);

}
