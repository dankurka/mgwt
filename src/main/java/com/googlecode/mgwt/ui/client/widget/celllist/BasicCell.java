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
package com.googlecode.mgwt.ui.client.widget.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;


/**
 * @author Daniel Kurka
 *
 */
public abstract class BasicCell<T> implements Cell<T> {

	private static Template TEMPLATE = GWT.create(Template.class);
	
	private String styleName;
	
	public BasicCell() {
		styleName = "";
	}

	public interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<div class=\"{0}\">{1}</div>")
		SafeHtml content(String classes, String cellContents);

	}

	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, final T model) {
		safeHtmlBuilder.append(TEMPLATE.content(styleName, SafeHtmlUtils.htmlEscape(getDisplayString(model))));

	}

	public abstract String getDisplayString(T model);

	@Override
	public boolean canBeSelected(T model) {
		return false;
	}
	
	public void setStylename(String name){
		if(name == null){
			name = "";
		}
		styleName = name;	
	}

}
