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
package de.kurka.mobile.showcase.client.activities.elements;

import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.MGWTUtil;
import de.kurka.gwt.mobile.ui.client.widget.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.widget.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.LayoutPanel;
import de.kurka.gwt.mobile.ui.client.widget.MCheckBox;
import de.kurka.gwt.mobile.ui.client.widget.MEmailTextBox;
import de.kurka.gwt.mobile.ui.client.widget.MNumberTextBox;
import de.kurka.gwt.mobile.ui.client.widget.MPasswordTextBox;
import de.kurka.gwt.mobile.ui.client.widget.MPhoneNumberTextBox;
import de.kurka.gwt.mobile.ui.client.widget.MRadioButton;
import de.kurka.gwt.mobile.ui.client.widget.MTextArea;
import de.kurka.gwt.mobile.ui.client.widget.MTextBox;
import de.kurka.gwt.mobile.ui.client.widget.MUrlTextBox;
import de.kurka.gwt.mobile.ui.client.widget.ScrollPanel;
import de.kurka.gwt.mobile.ui.client.widget.base.MListBox;
import de.kurka.gwt.mobile.ui.client.widget.list.WidgetList;

/**
 * @author Daniel Kurka
 * 
 */
public class ElementsViewImpl implements ElementsView {

	private LayoutPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton headerBackButton;
	private ScrollPanel scrollPanel;

	public ElementsViewImpl() {
		main = new LayoutPanel();

		scrollPanel = new ScrollPanel();

		scrollPanel.setScrollingEnabledX(false);

		headerPanel = new HeaderPanel();
		headerBackButton = new HeaderBackButton();
		headerBackButton.setText("UI");
		if (MGWTUtil.getFeatureDetection().isPhone()) {
			headerPanel.setLeftWidget(headerBackButton);
		}

		headerPanel.setCenter("Elements");
		main.add(headerPanel);

		WidgetList widgetList = new WidgetList();
		widgetList.setRound(true);
		scrollPanel.setWidget(widgetList);
		//workaround for android formfields jumping around when using -webkit-transform
		scrollPanel.setUsePos(MGWTUtil.getFeatureDetection().isAndroid());

		MTextBox mTextBox = new MTextBox();
		mTextBox.setPlaceHolder("textbox");
		widgetList.add(mTextBox);

		MPasswordTextBox mPasswordTextBox = new MPasswordTextBox();
		mPasswordTextBox.setPlaceHolder("password box");
		widgetList.add(mPasswordTextBox);

		MNumberTextBox numberBox = new MNumberTextBox();
		numberBox.setPlaceHolder("number keyboard");
		widgetList.add(numberBox);

		MPhoneNumberTextBox phoneBox = new MPhoneNumberTextBox();
		mPasswordTextBox.setPlaceHolder("phonebox");
		widgetList.add(phoneBox);

		MUrlTextBox urlBox = new MUrlTextBox();
		urlBox.setPlaceHolder("url keyboard");
		widgetList.add(urlBox);

		MEmailTextBox emailBox = new MEmailTextBox();
		emailBox.setPlaceHolder("email keyboard");
		widgetList.add(emailBox);

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

		main.add(scrollPanel);
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
