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
import com.googlecode.mgwt.ui.client.widget.PullToRefresh;

/**
 * The pull to refresh css interface
 * 
 * For an explanation of styling @see {@link PullToRefresh}
 * 
 * @author Daniel Kurka
 * 
 */
public interface PullToRefreshCss extends CssResource {
	@ClassName("mgwt-PullToRefresh")
	public String pullToRefresh();

	@ClassName("mgwt-PullToRefresh-arrow")
	public String arrow();

	@ClassName("mgwt-PullToRefresh-text")
	public String text();

	@ClassName("mgwt-PullToRefresh-spinner")
	public String spinner();

	@ClassName("mgwt-PullToRefresh-error")
	public String error();

}
