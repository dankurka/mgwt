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

import com.googlecode.mgwt.ui.client.widget.Button;

/**
 * The CSS interface for Buttons in mgwt.
 * 
 * for explanation see: {@link Button}
 * 
 * @author Daniel Kurka
 * 
 */
public interface ButtonCss extends ButtonBaseCss {

	@ClassName("mgwt-Button")
	public String button();

	@ClassName("mgwt-Button-active")
	public String active();

	@ClassName("mgwt-Button-small")
	public String small();

	@ClassName("mgwt-Button-round")
	public String round();

	@ClassName("mgwt-Button-important")
	public String important();

	@ClassName("mgwt-Button-confirm")
	public String confirm();
	
	@ClassName("mgwt-Button-disabled")
   public String disabled();
}
