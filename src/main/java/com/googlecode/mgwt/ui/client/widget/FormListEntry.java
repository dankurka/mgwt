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

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.widget.base.MTextBoxBase;

/**
 * A {@link FormListEntry} entry renders a widget together with a describing label
 * 
 * @author Daniel Kurka
 * 
 */
public class FormListEntry extends Composite implements HasWidgets, HasHTML {
	private FlowPanel main;
	private HTML fieldLabel;
	private SimplePanel widgetContainer;

  /**
   * Construct an empty {@link FormListEntry}
   */
	public FormListEntry() {
		this("", null);

	}

  /**
   * Construct a {@link FormListEntry} with a label and a widget
   * 
   * @param fieldName the text of the label
   * @param w the widget
   */
	public FormListEntry(String fieldName, Widget w) {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getListCss(), fieldName, w);
	}

  /**
   * Construct a {@link FormListEntry} with a label, a widget and a given css
   * 
   * @param fieldName the text of the label
   * @param w the widget
   * @param listCss the css to use
   */
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

  /**
   * set the label and the widget
   * 
   * @param fieldName the text of the label
   * @param w the widget
   */
	public void setWidget(String fieldName, Widget w) {
		fieldLabel.setHTML(fieldName);
		if (w instanceof MTextBoxBase) {

		}
		widgetContainer.setWidget(w);
	}

	@Override
	public String getText() {
		return fieldLabel.getText();
	}

	@Override
	public void setText(String text) {
		fieldLabel.setText(text);

	}

	@Override
	public String getHTML() {
		return fieldLabel.getHTML();
	}

	@Override
	public void setHTML(String html) {
		fieldLabel.setHTML(html);

	}

	// only for uibinder

	public void add(Widget w) {
		widgetContainer.setWidget(w);
	}

	@Override
	public void clear() {
		widgetContainer.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return widgetContainer.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return widgetContainer.remove(w);
	}

}
