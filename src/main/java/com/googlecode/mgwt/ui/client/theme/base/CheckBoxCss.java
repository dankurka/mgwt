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
import com.googlecode.mgwt.ui.client.widget.MCheckBox;

/**
 * The CSS interface for the mgwt checkbox
 * 
 * See explanations for classes {@link MCheckBox}
 * 
 * @author Daniel Kurka
 * 
 */
public interface CheckBoxCss extends CssResource {

	@ClassName("mgwt-CheckBox")
	public String checkBox();

	@ClassName("mgwt-CheckBox-middle")
	public String middle();

	@ClassName("mgwt-CheckBox-middle-content")
	public String content();

	@ClassName("mgwt-CheckBox-on")
	public String on();

	@ClassName("mgwt-CheckBox-off")
	public String off();

	@ClassName("mgwt-CheckBox-important")
	public String important();

	@ClassName("mgwt-CheckBox-checked")
	public String checked();

	@ClassName("mgwt-CheckBox-notchecked")
	public String notChecked();

}
