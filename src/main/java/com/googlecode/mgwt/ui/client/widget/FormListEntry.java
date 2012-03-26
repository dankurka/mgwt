/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.widget.base.MTextBoxBase;

public class FormListEntry extends Composite {
	private FlowPanel main;
	private HTML fieldLabel;
	private SimplePanel widgetContainer;

	public FormListEntry() {
		this("", null);

	}

	public FormListEntry(String fieldName, Widget w) {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getListCss(), fieldName, w);
	}

	public FormListEntry(ListCss listCss, String fieldName, Widget w) {
		listCss.ensureInjected();
		main = new FlowPanel();
		main.addStyleName(listCss.formListElement());

		initWidget(main);

		fieldLabel = new HTML(fieldName);
		fieldLabel.addStyleName(listCss.formListElementLabel());
		main.add(fieldLabel);

		widgetContainer = new SimplePanel();
		widgetContainer.addStyleName(listCss.formListElementContainer());

		main.add(widgetContainer);

		setWidget(fieldName, w);
	}

	public void setWidget(String fieldName, Widget w) {
		fieldLabel.setHTML(fieldName);
		if (w instanceof MTextBoxBase) {

		}
		widgetContainer.setWidget(w);
	}

}
