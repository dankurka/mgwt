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
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.panel.ScrollPanel;
import de.kurka.gwt.mobile.ui.client.widget.base.MCheckBox;
import de.kurka.gwt.mobile.ui.client.widget.base.MListBox;
import de.kurka.gwt.mobile.ui.client.widget.base.MPasswordTextBox;
import de.kurka.gwt.mobile.ui.client.widget.base.MRadioButton;
import de.kurka.gwt.mobile.ui.client.widget.base.MTextArea;
import de.kurka.gwt.mobile.ui.client.widget.base.MTextBox;
import de.kurka.gwt.mobile.ui.client.widget.list.WidgetList;

/**
 * @author Daniel Kurka
 *
 */
public class ElementsViewImpl implements ElementsView {

	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton headerBackButton;
	private ScrollPanel scrollPanel;

	public ElementsViewImpl() {

		scrollPanel = new ScrollPanel();

		//scrollPanel.setSize("320px", "320px");

		scrollPanel.addStyleName("constrainWidth");
		scrollPanel.addStyleName("constrainHeight");
		scrollPanel.setScrollingEnabledX(false);

		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		headerBackButton = new HeaderBackButton();
		headerBackButton.setText("UI");
		headerPanel.setLeftWidget(headerBackButton);
		headerPanel.getTitleWidget().setText("Elements");

		main.add(headerPanel);

		WidgetList widgetList = new WidgetList();
		scrollPanel.setWidget(widgetList);

		FormPanel formPanel = new FormPanel();
		formPanel.setWidget(scrollPanel);

		main.add(formPanel);

		MTextBox mTextBox = new MTextBox();
		mTextBox.setPlaceHolder("textbox");
		widgetList.add(mTextBox);

		MPasswordTextBox mPasswordTextBox = new MPasswordTextBox();
		mPasswordTextBox.setPlaceHolder("password box");
		widgetList.add(mPasswordTextBox);

		MTextArea mTextArea = new MTextArea();
		mTextArea.setPlaceHolder("text area");
		widgetList.add(mTextArea);

		MListBox mListBox = new MListBox();
		mListBox.addItem("iPhone");
		mListBox.addItem("iPad");
		mListBox.addItem("iPod");
		widgetList.add(mListBox);

		MCheckBox mCheckBox = new MCheckBox();
		//mCheckBox.setText("smeeee");
		widgetList.add(mCheckBox);

		MCheckBox mCheckBox1 = new MCheckBox();
		//mCheckBox1.setText("again");
		mCheckBox1.setImportant(true);
		widgetList.add(mCheckBox1);

		MRadioButton androidRadioButton = new MRadioButton("os");
		androidRadioButton.setText("Android");
		widgetList.add(androidRadioButton);

		MRadioButton iOSRadioButton = new MRadioButton("os");
		iOSRadioButton.setText("iOS");
		widgetList.add(iOSRadioButton);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return headerBackButton;
	}

}
