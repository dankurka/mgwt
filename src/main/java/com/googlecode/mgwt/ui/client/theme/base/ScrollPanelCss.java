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
 * The css interface for mgwt scroll panel
 * 
 * @author Daniel Kurka
 * 
 */
public interface ScrollPanelCss extends CssResource {

	@ClassName("mgwt-ScrollPanel")
	String scrollPanel();

	@ClassName("mgwt-ScrollPanel-container")
	String container();

	@ClassName("mgwt-Scrollbar")
	String scrollBar();

	@ClassName("mgwt-Scrollbar-horizontal")
	String horizontal();

	@ClassName("mgwt-Scrollbar-vertical")
	String vertical();

	@ClassName("mgwt-Scrollbar-Bar")
	String scrollBarBar();

}
