/*
xw * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.examples.showcase.client.activities.elements;

import com.googlecode.mgwt.examples.showcase.client.DetailViewGwtImpl;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.widget.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.MListBox;
import com.googlecode.mgwt.ui.client.widget.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.MPhoneNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.MTextArea;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.MUrlTextBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

/**
 * @author Daniel Kurka
 * 
 */
public class ElementsViewImpl extends DetailViewGwtImpl implements ElementsView {

	public ElementsViewImpl() {

		scrollPanel.setScrollingEnabledX(false);

		WidgetList widgetList = new WidgetList();
		widgetList.setRound(true);
		scrollPanel.setWidget(widgetList);
		// workaround for android formfields jumping around when using
		// -webkit-transform
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
		phoneBox.setPlaceHolder("phonebox");
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
		// mCheckBox.setText("smeeee");
		widgetList.add(mCheckBox);

		MCheckBox mCheckBox1 = new MCheckBox();
		// mCheckBox1.setText("again");
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

}
