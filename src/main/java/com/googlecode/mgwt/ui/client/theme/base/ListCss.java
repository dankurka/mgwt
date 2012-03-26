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
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

/**
 * This is the css interface for lists in mgwt.
 * 
 * It is used by {@link CellList} and {@link WidgetList}
 * 
 * For styling explanations @see {@link CellList}
 * 
 * @author Daniel Kurka
 * 
 */
public interface ListCss extends CssResource {

	@ClassName("mgwt-List")
	public String listCss();

	@ClassName("mgwt-List-round")
	public String round();

	@ClassName("mgwt-List-group")
	public String group();

	@ClassName("mgwt-List-selected")
	public String selected();

	@ClassName("mgwt-List-first")
	public String first();

	@ClassName("mgwt-List-last")
	public String last();

	@ClassName("mgwt-List-Header")
	public String listHeader();

	@ClassName("mgwt-FormList-Element")
	public String formListElement();

	@ClassName("mgwt-FormList-Element-label")
	public String formListElementLabel();

	@ClassName("mgwt-FormList-Element-container")
	public String formListElementContainer();

}
