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
package com.googlecode.mgwt.examples.showcase.client.activities.popup;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.dialog.OptionsDialog.OptionCallback;
import com.googlecode.mgwt.ui.client.dialog.OptionsDialog.OptionsDialogOption;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderBackButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class PopupViewGwtImpl implements PopupView {

	private LayoutPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;
	private Button slideUpButton;
	private Button alertButton;
	private Button confirmButton;

	/**
	 * 
	 */
	public PopupViewGwtImpl() {
		main = new LayoutPanel();

		headerPanel = new HeaderPanel();

		headerPanel.setCenter("Popups");
		backButton = new HeaderBackButton();

		backButton.setText("UI");

		if (MGWTUtil.getFeatureDetection().isPhone()) {
			headerPanel.setLeftWidget(backButton);
		}

		main.add(headerPanel);

		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledX(false);

		FlowPanel container = new FlowPanel();
		scrollPanel.setWidget(container);

		slideUpButton = new Button("Popup");
		container.add(slideUpButton);

		alertButton = new Button("Alert");

		container.add(alertButton);

		confirmButton = new Button("Confirm");
		container.add(confirmButton);

		main.add(scrollPanel);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return backButton;
	}

	@Override
	public HasSimpleTouchHandler getSlideUpButton() {
		return slideUpButton;
	}

	@Override
	public HasSimpleTouchHandler getAlertButton() {
		return alertButton;
	}

	@Override
	public void alertSomeStuff(String title, String text) {
		Dialogs.alert(title, text, null);

	}

	@Override
	public void confirmSomeStuff(String title, String text, ConfirmCallback callback) {
		Dialogs.confirm(title, text, callback);

	}

	@Override
	public void showSomeOptions(List<OptionsDialogOption> optionText, OptionCallback callback) {
		Dialogs.options(optionText, callback, main);

	}

	@Override
	public HasSimpleTouchHandler getConfirmButton() {
		return confirmButton;
	}

}
